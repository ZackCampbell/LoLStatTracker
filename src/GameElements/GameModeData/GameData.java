package GameElements.GameModeData;

public abstract class GameData {

    protected String      leagueId;
    protected String      tier;
    protected String      rank;
    protected int         leaguePoints;
    protected int         wins;
    protected int         losses;
    protected boolean     veteran;
    protected boolean     inactive;
    protected boolean     freshBlood;
    protected boolean     hotStreak;
    protected double      winRatio;

    abstract public String      getLeagueId();
    abstract public String      getRank();
    abstract public String      getTier();
    abstract public void        setLeagueId(String leagueId);
    abstract public void        setTier(String tier);
    abstract public void        setRank(String rank);
    abstract public void        setWins(int wins);
    abstract public void        setLeaguePoints(int leaguePoints);
    abstract public void        setLosses(int losses);
    abstract public void        setVeteran(boolean veteran);
    abstract public void        setInactive(boolean inactive);
    abstract public void        setFreshBlood(boolean freshBlood);
    abstract public void        setHotStreak(boolean hotStreak);
    abstract public int         getLeaguePoints();
    abstract public int         getWins();
    abstract public int         getLosses();
    abstract public boolean     isVeteran();
    abstract public boolean     isInactive();
    abstract public boolean     isFreshBlood();
    abstract public boolean     isHotStreak();

    public double getWinRatio() {
        if (losses == 0)
            this.winRatio = 100.0;
        else
            this.winRatio = Math.round(((double)wins / (double)(wins + losses)) * 1000.0) / 10.0;
        return this.winRatio;
    }

}
