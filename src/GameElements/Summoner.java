package GameElements;

import API.Session;
import GameElements.GameModeData.RankedData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

@JsonIgnoreProperties({ "accountId", "puuid", "revisionDate" })
public class Summoner {
    private static Logger LOGGER = initializeLogger(Summoner.class.getName());

    @JsonProperty("name") private String name;
    @JsonProperty("id") private String encryptedId;

    @JsonProperty("summonerLevel") private int level;

    @JsonProperty("profileIconId") private int iconId;
    private ArrayList<RankedData>       rankedData;

    public Summoner(String name, String encryptedId, int level, int iconId) {
        this.name = name;
        this.encryptedId = encryptedId;
        this.level = level;
        this.iconId = iconId;
        this.rankedData = new ArrayList<>();
    }

    /**
     * Stub constructor for when we get the initial request with just the summoner name. This effectively is cache-able
     * since none of the information will ever become stale. When data is requested, or caches need to be regenerated,
     * a request can be made from the data in this stub.
     * @param name If you don't know what this is you have bigger problems
     * @param encryptedId Pulled from the initial request response
     */
    @JsonCreator
    public Summoner(
            @JsonProperty("name") String name,
            @JsonProperty("id") String encryptedId
    ) {
        this.name = name;
        this.encryptedId = encryptedId;
        this.level = -1;
        this.iconId = -1;
        this.rankedData = new ArrayList<>();
    }

    /**
     * Constructs an empty, invalid Summoner
     */
    public Summoner(String name) {
        this.name = name;
        this.encryptedId = null;
        this.level = -1;
        this.iconId = -1;
        this.rankedData = new ArrayList<>();
    }

    public Summoner() {
        this.name = null;
        this.encryptedId = null;
        this.level = -1;
        this.iconId = -1;
        this.rankedData = new ArrayList<>();
    }

    public static Summoner retrieveFromCache(String summonerName) {
        return Session.getInstance().getSummoner(summonerName);
    }

    public boolean isValid() {
        return this.encryptedId != null && this.name != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public ArrayList<RankedData> getRankedData() {
        return rankedData;
    }

    @Override
    public String toString() {
        String toReturn = "SUMMONER: " + getName() + "\nEncryptedId: " + getEncryptedId() + "\nLevel: " + getLevel()
                + "\nIcon: " + getIconId();

        return toReturn;
    }
}
