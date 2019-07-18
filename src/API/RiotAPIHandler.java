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
    private String api_key;
    private String region = "na1";

    public RiotAPIHandler() {
        LOGGER = initializeLogger(RiotAPIHandler.class.getName());
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

    public String getApi_key() {
        return api_key;
    }

}
