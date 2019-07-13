package GameElements;

import GameElements.GameModeData.FlexData;
import GameElements.GameModeData.RankedData;
import GameElements.GameModeData.TwistedTreelineData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Summoner {

    private String                  name;
    private String                  encryptedId;
    private int                     level;
    private int                     iconId;
    private ArrayList<String>       queuesCompleted;

    private RankedData              rankedData;
    private FlexData                flexData;
    private TwistedTreelineData     ttData;

    private JSONArray               rankedInfo;

    public Summoner(String name, String encryptedId, int level, int iconId, JSONArray rankedInfo) {
        this.name = name;
        this.encryptedId = encryptedId;
        this.level = level;
        this.iconId = iconId;
        this.rankedInfo = rankedInfo;
        extractRankedInfo();
    }

    private void extractRankedInfo() {
        queuesCompleted = new ArrayList<>();
        for (Object data : rankedInfo) {
            JSONObject dataObj = (JSONObject) data;
            switch (dataObj.getString("queueType")) {
                case ("RANKED_SOLO_5x5"):
                    queuesCompleted.add("RANKED_SOLO_5x5");
                    rankedData = new RankedData(dataObj.getString("tier"), dataObj.getString("rank"),
                            dataObj.getInt("leaguePoints"), dataObj.getInt("wins"), dataObj.getInt("losses"),
                            dataObj.getBoolean("veteran"), dataObj.getBoolean("inactive"),
                            dataObj.getBoolean("freshBlood"), dataObj.getBoolean("hotStreak"),
                            dataObj.getString("leagueId"));
                    break;
                case ("RANKED_FLEX_SR"):
                    queuesCompleted.add("RANKED_FLEX_SR");
                    flexData = new FlexData(dataObj.getString("tier"), dataObj.getString("rank"),
                            dataObj.getInt("leaguePoints"), dataObj.getInt("wins"), dataObj.getInt("losses"),
                            dataObj.getBoolean("veteran"), dataObj.getBoolean("inactive"),
                            dataObj.getBoolean("freshBlood"), dataObj.getBoolean("hotStreak"),
                            dataObj.getString("leagueId"));
                    break;
                case ("RANKED_FLEX_TT"):
                    queuesCompleted.add("RANKED_FLEX_TT");
                    ttData = new TwistedTreelineData(dataObj.getString("tier"), dataObj.getString("rank"),
                            dataObj.getInt("leaguePoints"), dataObj.getInt("wins"), dataObj.getInt("losses"),
                            dataObj.getBoolean("veteran"), dataObj.getBoolean("inactive"),
                            dataObj.getBoolean("freshBlood"), dataObj.getBoolean("hotStreak"),
                            dataObj.getString("leagueId"));
                    break;
            }
        }
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

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
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

    public RankedData getRankedData() {
        return rankedData;
    }

    public FlexData getFlexData() {
        return flexData;
    }

    public TwistedTreelineData getTtData() {
        return ttData;
    }

    @Override
    public String toString() {
        String toReturn = "SUMMONER: " + getName() + "\nEncryptedId: " + getEncryptedId() + "\nLevel: " + getLevel()
                + "\nIcon: " + getIconId();
        for (String queueType : queuesCompleted) {
            switch (queueType) {
                case ("RANKED_SOLO_5x5"):
                    toReturn += "\nRanked Solo: " + getRankedData().getTier() + " " + getRankedData().getRank()
                            + " - Win Ratio: " + getRankedData().getWinRatio();
                    break;
                case ("RANKED_FLEX_SR"):
                    toReturn += "\nRanked Flex: " + getFlexData().getTier() + " " + getFlexData().getRank()
                            + " - Win Ratio: " + getFlexData().getWinRatio();
                    break;
                case ("RANKED_FLEX_TT"):
                    toReturn += "\nRanked Twisted Treeline: " + getTtData().getTier() + " " + getTtData().getRank()
                            + " - Win Ratio: " + getTtData().getWinRatio();
                    break;
            }
        }

        return toReturn;
    }
}
