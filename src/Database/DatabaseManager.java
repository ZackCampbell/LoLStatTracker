package Database;

import GameElements.Match;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import org.bson.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
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

    public List<Match> getMatchesWithChampions(List<Long> champIds) {
        return this.datastore.createQuery(Match.class)
                .field("participatingChampionIds")
                .hasAllOf(champIds)
                .find()
                .toList();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }
}
