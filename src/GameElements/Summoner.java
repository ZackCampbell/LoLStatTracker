package GameElements;

import Main.Session;
import GameElements.GameModeData.RankedData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

@JsonIgnoreProperties({ "revisionDate" })
public class Summoner {
    private static Logger LOGGER = initializeLogger(Summoner.class.getName());

    @JsonProperty("name") private String name;
    @JsonProperty("accountId") private String encryptedId;
    @JsonProperty("id") private String summonerId;
    @JsonProperty("puuid") private String puuidId;
    @JsonProperty("summonerLevel") private long level;
    @JsonProperty("profileIconId") private long iconId;

    private String region = "NA";
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
            @JsonProperty("accountId") String encryptedId
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

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getIconId() {
        return iconId;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIconId(long iconId) {
        this.iconId = iconId;
    }

    public void setEncryptedId(String id) {
        this.encryptedId = id;
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
