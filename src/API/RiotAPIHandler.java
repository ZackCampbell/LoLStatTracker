package API;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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

    // -------------------------------- GETTERS AND SETTERS -----------------------------------------

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getApi_key() {
        return api_key;
    }

}
