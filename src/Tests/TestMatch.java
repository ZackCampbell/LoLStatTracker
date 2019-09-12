package Tests;

import GameElements.Match;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class TestMatch {

    public static boolean testMatchRoundTrip() {
        long matchId = 3129259621L;

        try {
            Match match = new Match("na1", matchId);

            if (!match.isValid()) {
                return false;
            }

            Morphia morphia = new Morphia();
            morphia.mapPackage("GameElements");
            Datastore datastore = morphia.createDatastore(new MongoClient(), "unit_tests");
            datastore.ensureIndexes();

            System.out.println(match);

            DBObject dbo = morphia.toDBObject(match);
            Match fromDbo = morphia.fromDBObject(datastore, Match.class, dbo);

            System.out.println(fromDbo);

            /// I have no idea why these don't pass an equality test when every attribute is equal
            return match.equals(fromDbo);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
