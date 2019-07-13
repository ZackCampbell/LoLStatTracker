package API;

import GameElements.Summoner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static Utils.Utils.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RiotAPIHandler {

    private static Logger LOGGER;
    private String summonerName = "KashyyykNative";             // Hardcoded for now
    private ArrayList<String> queueTypes;
    private ArrayList<String> divisions;
    private ArrayList<String> basicTiers;
    private ArrayList<String> eliteTiers;

    private String api_key; // Expires: Sat, Jul 13th, 2019 @ 3:45pm (CT)

    private String region = "na1";

    public RiotAPIHandler() {
        LOGGER = initializeLogger(RiotAPIHandler.class.getName());
        initializeLists();
        try {
            FileReader reader = new FileReader(new File("././src/API/ApiKey.json"));
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject obj = new JSONObject(tokener);
            api_key = obj.getString("api_key");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find ApiKey.json");
            LOGGER.log(Level.SEVERE, e.getMessage());
            api_key = null;
        }

    }


    public Summoner getSummonerData(String summonerName) {
        this.summonerName = summonerName;

        JSONObject initialInfo = null;
        JSONArray rankedInfo = null;
        String initialData = " ", rankedData = " ";

        try {
            URL riotGamesUrl = new URL(buildInitialURL());
            initialData = getData(initialData, riotGamesUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        if (!initialData.equals(" ")) {
            initialInfo = new JSONObject(initialData);
        } else {
            LOGGER.log(Level.SEVERE, "Null response from initial api call");
            return null;
        }

        try {
            URL riotGamesUrl = new URL(buildRankedURL(getSummonerId(initialInfo)));
            rankedData = getData(rankedData, riotGamesUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        if (!rankedData.equals(" ")) {
            rankedInfo = new JSONArray(rankedData);
        } else {
            LOGGER.log(Level.SEVERE, "Null response from ranked api call");
            return null;
        }

        return buildSummoner(initialInfo, rankedInfo);
    }

    private String getData(String data, URL riotGamesUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)riotGamesUrl.openConnection();
        setRequestHeaders(urlConnection);
        InputStream inputStream = urlConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            data += scanner.nextLine();
        }
        urlConnection.disconnect();
        return data;
    }

    private Summoner buildSummoner(JSONObject initialInfo, JSONArray rankedInfo) {
        if (initialInfo == null) {
            LOGGER.log(Level.WARNING, "Invalid response from Riot API");
            return null;
        }

        int profileIconId = initialInfo.getInt("profileIconId");
        int summonerLevel = initialInfo.getInt("summonerLevel");
        String summonerId = initialInfo.getString("id");
        String summonerName = initialInfo.getString("name");

        return new Summoner(summonerName, summonerId, summonerLevel, profileIconId, rankedInfo);
    }

    private String buildInitialURL() {
        return "https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName;
    }

    private String buildRankedURL(String summonerId) {
        return "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId;
    }

    private void initializeLists() {
        queueTypes = new ArrayList<>() {
            {
                add("RANKED_SOLO_5x5");
                add("RANKED_FLEX_SR");
                add("RANKED_FLEX_TT");
            }
        };

        divisions = new ArrayList<>() {
            {
                add("I");
                add("II");
                add("III");
                add("IV");
            }
        };

        basicTiers = new ArrayList<>() {
            {
                add("DIAMOND");
                add("PLATINUM");
                add("GOLD");
                add("SILVER");
                add("BRONZE");
                add("IRON");
            }
        };

        eliteTiers = new ArrayList<>() {
            {
                add("CHALLENGER");
                add("GRANDMASTER");
                add("MASTER");
            }
        };
    }

    private String getSummonerId(JSONObject initialInfo) {
        if (initialInfo == null) {
            LOGGER.log(Level.WARNING, "Invalid response from Riot API");
            return null;
        }
        return initialInfo.getString("id");
    }

    // -------------------------------- GETTERS AND SETTERS -----------------------------------------

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private void setRequestHeaders(HttpURLConnection urlConnection) throws ProtocolException {
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Riot-Token", api_key);
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public ArrayList<String> getQueueTypes() {
        return queueTypes;
    }

    public void addQueueType(String queueType) {
        this.queueTypes.add(queueType);
    }

    public ArrayList<String> getDivisions() {
        return divisions;
    }

    public void addDivision(String division) {
        this.divisions.add(division);
    }

    public ArrayList<String> getBasicTiers() {
        return basicTiers;
    }

    public void addBasicTier(String basicTier) {
        this.basicTiers.add(basicTier);
    }

    public ArrayList<String> getEliteTiers() {
        return eliteTiers;
    }

    public void addEliteTier(String eliteTier) {
        this.eliteTiers.add(eliteTier);
    }

    String getApi_key() {
        return api_key;
    }

}
