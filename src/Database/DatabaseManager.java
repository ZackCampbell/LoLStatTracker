package Database;

import GameElements.Match;
import GameElements.MatchParticipantTimeline;
import com.mifmif.common.regex.Generex;
import com.mifmif.common.regex.util.Iterator;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import dev.morphia.query.Sort;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.bson.Document;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import Utils.Utils;
public class DatabaseManager {
    public static String latestDDVersion;
    private static final String DATASTORE_NAME = "test";
    private static DatabaseManager instance;
    private Morphia morphia;
    private Datastore datastore;

    public DatabaseManager() {
        /// This is a localhost client.. connection string can be specified in create()
        /// TODO: close the connection
        this.morphia = new Morphia();

        this.morphia.mapPackage("GameElements");

        this.datastore = this.morphia.createDatastore(new MongoClient(), DATASTORE_NAME);
        this.datastore.ensureIndexes();
    }

    public void addMatches(List<Match> matches) {
        this.datastore.save(matches);
    }

    public void addMatch(Match match) {
        this.addMatches(Collections.singletonList(match));
    }

    public List<Match> getAllMatches() {
        Query<Match> q = this.datastore.createQuery(Match.class);

        return q.find().toList();
    }

    public List<Long> getAllMatchIds() {
        return this.datastore.createQuery(Match.class)
                .project("_id", true)
                .find()
                .toList()
                .stream()
                .map(Match::getId)
                .collect(Collectors.toList());
    }

    public Map<Date, MatchParticipantTimeline> getMatchTimelinesForChampion(Long champId) {
        List<Match> matches = this.datastore.createQuery(Match.class)
                .project("teams", true)
                .project("timeline", true)
                .field("participatingChampionIds")
                .hasThisOne(champId)
                .order(Sort.descending("timestamp"))
                .find()
                .toList();

        Map<Date, MatchParticipantTimeline> timelines = new HashMap<>();

        for (Match m : matches) {
            if (m.getTimeline() == null) {
                continue;
            }
            timelines.put(m.getTimestamp(), m.getChampionTimeline(champId));
        }

        return timelines;
    }

    public List<Match> getMatchesWithChampions(List<Long> champIds) {
        return this.datastore.createQuery(Match.class)
                .field("participatingChampionIds")
                .hasAllOf(champIds)
                .find()
                .toList();
    }

    public List<Match> getMatchesWithChampions(List<Long> champIds, int limit) {
        FindOptions options = new FindOptions();
        options.limit(limit);

        return this.datastore.createQuery(Match.class)
                .field("participatingChampionIds")
                .hasAllOf(champIds)
                .order(Sort.descending("timestamp"))
                .find(options)
                .toList();
    }

    public List<Match> getMatchesWithChampionsFromPatch(List<Long> champIds, String patch) {
        return this.datastore.createQuery(Match.class)
                .field("participatingChampionIds")
                .hasAllOf(champIds)
                .field("gameVersion")
                .startsWith(patch)
                .order(Sort.descending("timestamp"))
                .find()
                .toList();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    @SuppressWarnings("all")
    public static void updateDataDragonResources() {
        try {
            int majorVersion = 0;
            int minorVersion = 0;
            int patches = 0;
            String latestVersion = null;
            Generex generex = new Generex("([0-9]\\.[0-9]{2}\\.[0-9])");
            List<String> matchingStrings = generex.getAllMatchedStrings();
            for (String version : matchingStrings) {
                File testtgzFile = new File(Utils.getRelativePath() + "/lib/DataDragon/dragontail-" + version + ".tgz");
                if (testtgzFile.exists()) {
                    int currMajorVersion = Integer.parseInt(version.split("\\.")[0]);
                    int currMinorVersion = Integer.parseInt(version.split("\\.")[1]);
                    int currPatches = Integer.parseInt(version.split("\\.")[2]);
                    if (currMajorVersion >= majorVersion && currMinorVersion >= minorVersion && currPatches >= patches) {
                        latestVersion = version;
                        majorVersion = currMajorVersion;
                        minorVersion = currMinorVersion;
                        patches = currPatches;
                    }

                }

            }
            boolean containsFiles = false;
            File ddDirectory = new File(Utils.getRelativePath() + "/lib/DataDragon");
            if (ddDirectory.isDirectory() && ddDirectory.list().length > 0) {
                containsFiles = true;
            }
            if (latestVersion == null && !containsFiles) {
                throw new FileNotFoundException();
            } else if (latestVersion == null) {
                return;
            }
            latestDDVersion = latestVersion;
            File newDDtgzFile = new File(Utils.getRelativePath() + "/lib/DataDragon/dragontail-" + latestVersion + ".tgz");
            unTar(unGzip(newDDtgzFile, new File(Utils.getRelativePath() + "/lib/DataDragon/tars")), new File(Utils.getRelativePath() + "/lib/DataDragon/data"));
            newDDtgzFile.delete() ;

        } catch (IOException | ArchiveException e) {
            System.out.println("Exception caught on updating DataDragon resources: " + e.getMessage());
            e.printStackTrace();
        }


    }

    private static File unGzip(final File inputFile, final File outputDir) throws FileNotFoundException, IOException {
        final File outputFile = new File(outputDir, inputFile.getName().substring(0, inputFile.getName().length() - 3));

        final GZIPInputStream in = new GZIPInputStream(new FileInputStream(inputFile));
        final FileOutputStream out = new FileOutputStream(outputFile);

        IOUtils.copy(in, out);

        in.close();
        out.close();

        return outputFile;
    }

    private static List<File> unTar(final File inputFile, final File outputDir) throws FileNotFoundException, IOException, ArchiveException {

        final List<File> untaredFiles = new LinkedList<>();
        final InputStream is = new FileInputStream(inputFile);
        final TarArchiveInputStream debInputStream = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", is);
        TarArchiveEntry entry;
        while ((entry = (TarArchiveEntry)debInputStream.getNextEntry()) != null) {
            final File outputFile = new File(outputDir, entry.getName());
            if (entry.isDirectory()) {
                if (!outputFile.exists()) {
                    if (!outputFile.mkdirs()) {
                        throw new IllegalStateException(String.format("Couldn't create directory %s.", outputFile.getAbsolutePath()));
                    }
                }
            } else {
                final OutputStream outputFileStream = new FileOutputStream(outputFile);
                IOUtils.copy(debInputStream, outputFileStream);
                outputFileStream.close();
            }
            untaredFiles.add(outputFile);
        }
        debInputStream.close();

        return untaredFiles;
    }

}
