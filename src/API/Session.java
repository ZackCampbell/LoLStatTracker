package API;

import GameElements.Summoner;

import static Utils.Utils.*;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

// TODO: Consider refactoring preferences to its own interface/package

public class Session {
    private Logger LOGGER = initializeLogger(Session.class.getName());
    private static Session instance = null;

    private HashMap<String, Summoner> loadedSummoners;

    private Session() {
        this.loadedSummoners = new HashMap<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }

    void addSummoner(Summoner summoner) {
        if (summoner.isValid()) {
            loadedSummoners.put(summoner.getName(), summoner);
        } else {
            LOGGER.log(Level.WARNING, "Tried to store an invalid Summoner in session state");
        }
    }

    public Summoner getSummoner(String name) {
        Summoner cached = loadedSummoners.getOrDefault(name, new Summoner());

        if (!cached.isValid()) {
            LOGGER.log(Level.INFO, "No session stored value for: %s", name);
        }

        return cached;
    }
}