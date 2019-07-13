package GameElements.GameModeData;

public abstract class GameData {

    protected String      leagueId;
    protected String      queueType;
    protected String      tier;
    protected String      rank;
    protected String      summonerId;
    protected String      summonerName;
    protected int         leaguePoints;
    protected int         wins;
    protected int         losses;
    protected boolean     veteran;
    protected boolean     inactive;
    protected boolean     freshBlood;
    protected boolean     hotStreak;

    abstract public String      getLeagueId();
    abstract public String      getSummonerName();
    abstract public String      getSummonerId();
    abstract public String      getRank();
    abstract public String      getTier();
    abstract public String      getQueueType();
    abstract public void        setLeagueId(String leagueId);
    abstract public void        setQueueType(String queueType);
    abstract public void        setTier(String tier);
    abstract public void        setRank(String rank);
    abstract public void        setSummonerId(String summonerId);
    abstract public void        setSummonerName(String summonerName);
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

}
