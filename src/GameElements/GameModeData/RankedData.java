package GameElements.GameModeData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "summonerId", "summonerName" })
public class RankedData {
    private String      queueType;
    private String      leagueId;
    private String      tier;
    private String      rank;
    private int         leaguePoints;
    private int         wins;
    private int         losses;
    private boolean     veteran;
    private boolean     inactive;
    private boolean     freshBlood;
    private boolean     hotStreak;

    private double      winRatio;

    @JsonCreator
    public RankedData(
            @JsonProperty("queueType") String queueType,
            @JsonProperty("tier") String tier,
            @JsonProperty("rank") String rank,
            @JsonProperty("leaguePoints") int leaguePoints,
            @JsonProperty("wins") int wins,
            @JsonProperty("losses") int losses,
            @JsonProperty("veteran") boolean veteran,
            @JsonProperty("inactive") boolean inactive,
            @JsonProperty("freshBlood") boolean freshBlood,
            @JsonProperty("hotStreak") boolean hotStreak,
            @JsonProperty("leagueId") String leagueId
    ) {
        this.queueType = queueType;
        this.leagueId = leagueId;
        this.freshBlood = freshBlood;
        this.hotStreak = hotStreak;
        this.inactive = inactive;
        this.leaguePoints = leaguePoints;
        this.losses = losses;
        this.rank = rank;
        this.wins = wins;
        this.tier = tier;
        this.veteran = veteran;
    }

    public double getWinRatio() {
        if (losses == 0)
            this.winRatio = 100.0;
        else
            this.winRatio = Math.round(((double)wins / (double)(wins + losses)) * 1000.0) / 10.0;
        return this.winRatio;
    }

    public String getLeagueId() {
        return this.leagueId;
    }

    public String getRank() {
        return this.rank;
    }

    public String getTier() {
        return this.tier;
    }

    public String getQueueType() {
        return this.queueType;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public int getLeaguePoints() {
        return this.leaguePoints;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public boolean isVeteran() {
        return this.veteran;
    }

    public boolean isInactive() {
        return this.inactive;
    }

    public boolean isFreshBlood() {
        return this.freshBlood;
    }

    public boolean isHotStreak() {
        return this.hotStreak;
    }

    @Override
    public String toString() {
        return getQueueType() + "\nRank: " + getTier() + " " + getRank();
    }
}
