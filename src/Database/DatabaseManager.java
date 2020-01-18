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
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import Utils.Utils;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import static Utils.Utils.initializeLogger;

// TODO: Move most of this functionality to the server - only maintain this as a client to make requests to the server
public class DatabaseManager {
    public static String latestDDVersion;
    private static final String DATASTORE_NAME = "test";
    private static DatabaseManager instance;
    private static Logger logger = initializeLogger(DatabaseManager.class.getName());
    private Datastore datastore;

    private DatabaseManager() {
        /// This is a localhost client.. connection string can be specified in create()
        /// TODO: close the connection
        //Morphia morphia = new Morphia();

        //morphia.mapPackage("GameElements");

        //this.datastore = morphia.createDatastore(new MongoClient(), DATASTORE_NAME);
        //this.datastore.ensureIndexes();
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

    // TODO: Test these client methods for interacting with the API and update the URLs to connect to the correct database

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
            sb.append((char) cp);
        return sb.toString();
    }

    @Nullable
    private static JSONObject getMatch(long id) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("<Insert URL Here>/matches/match/" + id).openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            logger.info("GET on match " + id + " successful");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            String jsonText = readAll(reader);
            return new JSONObject(jsonText);
        }
        else if (responseCode >= 400 && responseCode < 500)
            logger.severe("Post failed for match " + id + " - Client side error: " + responseCode + "\n" + connection.getResponseMessage());
        else if (responseCode >= 500)
            logger.severe("Post failed for match " + id + " - Server side error: " + responseCode + "\n" + connection.getResponseMessage());
        return null;
    }

    private static void postMatch(Match match) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("<Insert URL Here>/matches/match/" + match.getId()).openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        ObjectOutputStream writer = new ObjectOutputStream(connection.getOutputStream());
        writer.writeObject(match);
        writer.flush();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200)
            logger.info("Post on match " + match.getId() + " successful");
        else if (responseCode >= 400 && responseCode < 500)
            logger.severe("Post failed for match " + match.getId() + " - Client side error: " + responseCode + "\n" + connection.getResponseMessage());
        else if (responseCode >= 500)
            logger.severe("Post failed for match " + match.getId() + " - Server side error: " + responseCode + "\n" + connection.getResponseMessage());

        writer.close();
    }

}
