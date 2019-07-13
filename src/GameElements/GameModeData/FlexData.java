package GameElements.GameModeData;

public class FlexData extends GameData{

    public FlexData(
            String tier,
            String rank,
            int leaguePoints,
            int wins,
            int losses,
            boolean veteran,
            boolean inactive,
            boolean freshBlood,
            boolean hotStreak,
            String leagueId
    ) {
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

    @Override
    public String getLeagueId() {
        return this.leagueId;
    }

    @Override
    public String getRank() {
        return this.rank;
    }

    @Override
    public String getTier() {
        return this.tier;
    }

    @Override
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    @Override
    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    @Override
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    @Override
    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    @Override
    public int getLeaguePoints() {
        return this.leaguePoints;
    }

    @Override
    public int getWins() {
        return this.wins;
    }

    @Override
    public int getLosses() {
        return this.losses;
    }

    @Override
    public boolean isVeteran() {
        return this.veteran;
    }

    @Override
    public boolean isInactive() {
        return this.inactive;
    }

    @Override
    public boolean isFreshBlood() {
        return this.freshBlood;
    }

    @Override
    public boolean isHotStreak() {
        return this.hotStreak;
    }
}
