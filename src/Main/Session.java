package Main;

import GameElements.Summoner;
import Utils.Cache;

import static Utils.Utils.*;

import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Consider refactoring preferences to its own interface/package

public class Session {
    private Logger LOGGER = initializeLogger(Session.class.getName());
    private static Session instance = null;
    private static Cache<String, Summoner> cache;
    private final long TIME_TO_LIVE = 3600;
    private final long TIMER_INTERVAL = 120;
    private final int MAX_ITEMS = 5;

    // TODO: Hook this up
    private String currentSummoner = "Seer";
    private String currentRegion = "na1";

    private Session() {
         cache = new Cache<String, Summoner>(TIME_TO_LIVE, TIMER_INTERVAL, MAX_ITEMS);
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }

    public Cache getCache() {
        return cache;
    }

    public void addSummoner(Summoner summoner) {
        if (summoner.isValid()) {
            cache.put(summoner.getName(), summoner);
        } else {
            LOGGER.log(Level.WARNING, "Tried to store an invalid Summoner in session state");
        }
    }

    public Summoner getSummoner(String name) {
        Summoner cached = cache.getOrDefault(name, new Summoner(name));

        if (!cached.isValid()) {
            LOGGER.log(Level.INFO, "No session stored value for: %s", name);
        }

        return cached;
    }

    public String getCurrentSummoner() {
        return currentSummoner;
    }

    public String getCurrentRegion() {
        return currentRegion;
    }
}