package GameElements;

public class Summoner {

    private String name;
    private String encryptedId;
    private int level;
    private int iconId;

    public Summoner(String name, String encryptedId, int level, int iconId) {
        this.name = name;
        this.encryptedId = encryptedId;
        this.level = level;
        this.iconId = iconId;
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

}
