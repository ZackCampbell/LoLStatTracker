package API;

import GameElements.GameModeData.RankedData;
import GameElements.Summoner;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static Utils.Utils.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RiotAPIHandler {

    private static Logger LOGGER;
//    private ArrayList<String> queueTypes;
//    private ArrayList<String> divisions;
//    private ArrayList<String> basicTiers;
//    private ArrayList<String> eliteTiers;

    private String api_key; // Expires: Sat, Jul 13th, 2019 @ 3:45pm (CT)

    private String region = "na1";

    public RiotAPIHandler() {
        LOGGER = initializeLogger(RiotAPIHandler.class.getName());
//        initializeLists();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map apiKey = mapper.readValue(new File("././src/API/ApiKey.json"), Map.class);
            api_key = (String) apiKey.getOrDefault("api_key", null);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find ApiKey.json");
            LOGGER.log(Level.SEVERE, e.getMessage());
            api_key = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Summoner getSummonerStub(String summonerName) {
        Summoner summoner = Summoner.retrieveFromCache(summonerName);

        if (summoner.isValid()) {
            return summoner;
        }

        // Didn't get from cache, make a request for it
        try {
            summoner = getResponseData(new URL(buildInitialURL(summonerName)), Summoner.class);

            Session.getInstance().addSummoner(summoner);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return new Summoner();
    }

    public RankedData[] getRankedData(String summonerName) {
        Summoner summoner = getSummonerStub(summonerName);

        if (summoner.isValid()) {
            try {
                URL riotGamesUrl = new URL(buildRankedURL(summoner.getEncryptedId()));
                return getResponseData(riotGamesUrl, RankedData[].class);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                return new RankedData[]{};
            }
        } else {
            LOGGER.log(Level.SEVERE, "Could not retrieve summoner from cache or API request");
            return new RankedData[]{};
        }
    }

    public RankedData[] getRankedData(Summoner summoner) {
        if (summoner.isValid()) {
            return getRankedData(summoner.getName());
        } else {
            LOGGER.log(Level.SEVERE, "Can't retrieve ranked info for invalid summoner");
            return new RankedData[]{};
        }
    }

    private <T> T getResponseData(URL riotGamesUrl, Class<T> expectedClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        HttpURLConnection urlConnection = (HttpURLConnection)riotGamesUrl.openConnection();
        setRequestHeaders(urlConnection);
        InputStream inputStream = urlConnection.getInputStream();

        return mapper.readValue(inputStream, expectedClass);
    }

    private String buildInitialURL(String summonerName) {
        return "https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName;
    }

    private String buildRankedURL(String summonerId) {
        return "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId;
    }

//    private void initializeLists() {
//        queueTypes = new ArrayList<>() {
//            {
//                add("RANKED_SOLO_5x5");
//                add("RANKED_FLEX_SR");
//                add("RANKED_FLEX_TT");
//            }
//        };
//
//        divisions = new ArrayList<>() {
//            {
//                add("I");
//                add("II");
//                add("III");
//                add("IV");
//            }
//        };
//
//        basicTiers = new ArrayList<>() {
//            {
//                add("DIAMOND");
//                add("PLATINUM");
//                add("GOLD");
//                add("SILVER");
//                add("BRONZE");
//                add("IRON");
//            }
//        };
//
//        eliteTiers = new ArrayList<>() {
//            {
//                add("CHALLENGER");
//                add("GRANDMASTER");
//                add("MASTER");
//            }
//        };
//    }

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

//    public ArrayList<String> getQueueTypes() {
//        return queueTypes;
//    }
//
//    public void addQueueType(String queueType) {
//        this.queueTypes.add(queueType);
//    }
//
//    public ArrayList<String> getDivisions() {
//        return divisions;
//    }
//
//    public void addDivision(String division) {
//        this.divisions.add(division);
//    }
//
//    public ArrayList<String> getBasicTiers() {
//        return basicTiers;
//    }
//
//    public void addBasicTier(String basicTier) {
//        this.basicTiers.add(basicTier);
//    }
//
//    public ArrayList<String> getEliteTiers() {
//        return eliteTiers;
//    }
//
//    public void addEliteTier(String eliteTier) {
//        this.eliteTiers.add(eliteTier);
//    }

    String getApi_key() {
        return api_key;
    }

}
