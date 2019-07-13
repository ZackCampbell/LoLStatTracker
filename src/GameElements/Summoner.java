package GameElements;

import GameElements.GameModeData.FlexData;
import GameElements.GameModeData.RankedData;
import GameElements.GameModeData.TwistedTreelineData;
import org.json.JSONArray;

import java.util.HashMap;

public class Summoner {

    private String name;
    private String encryptedId;
    private int level;
    private int iconId;

    private RankedData rankedData;
    private FlexData flexData;
    private TwistedTreelineData ttData;

    private JSONArray rankedInfo;

    public Summoner(String name, String encryptedId, int level, int iconId, JSONArray rankedInfo) {
        this.name = name;
        this.encryptedId = encryptedId;
        this.level = level;
        this.iconId = iconId;
        this.rankedInfo = rankedInfo;
    }

    private void extractRankedInfo() {
        // TODO: Take the information from rankedInfo, parse and store it into rankedData, flexData, and ttData respectively
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

    @Override
    public String toString() {
        return "SUMMONER: " + getName() + "\nEncryptedId: " + getEncryptedId() + "\nLevel: " + getLevel()
                + "\nIcon: " + getIconId();
    }
}
