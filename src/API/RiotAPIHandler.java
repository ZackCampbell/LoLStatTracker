package API;

import GameElements.Summoner;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static Utils.Utils.*;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RiotAPIHandler {

    private static Logger LOGGER;
    private String summonerName = "KashyyykNative";             // Hardcoded for now
    private final String RANKED_SOLO = "RANKED_SOLO_5x5";
    private final String RANKED_FLEX = "RANKED_FLEX_SR";
    private final String RANKED_TT = "RANKED_FLEX_TT";

    private static final String INTERMEDIARY_URL = "summoner/v4/summoners/by-name/";
    private static final String BASE_URL = "https://na1.api.riotgames.com/lol/";
    private static final String api_key = "RGAPI-e01adcb4-1167-484c-94c5-768094c7af50"; // Expires: Sat, Jul 13th, 2019 @ 3:45pm (CT)

    private String region = "na1";

    public RiotAPIHandler() {
        LOGGER = initializeLogger(RiotAPIHandler.class.getName());
    }


    public JSONObject getAPIData(String summonerName) {

        this.summonerName = summonerName;

        JSONObject result = null;
        String data = " ";

        try {
            URL riotGamesUrl = new URL(BASE_URL + INTERMEDIARY_URL + summonerName);
            HttpURLConnection urlConnection = (HttpURLConnection)riotGamesUrl.openConnection();
            setRequestHeaders(urlConnection);
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()) {
                data += scanner.nextLine();
            }
        } catch (IOException e) {
            e.getMessage();
            LOGGER.log(Level.SEVERE, e.toString());
        }
        if (!data.equals(" ")) {
            result = new JSONObject(data);
        }
        handleResponse(result);
        return result;
    }



    public void handleResponse(JSONObject response) {
        if (response == null) {
            LOGGER.log(Level.WARNING, "Invalid response from Riot API");
            return;
        }

        int profileIconId = response.getInt("profileIconId");
        int summonerLevel = response.getInt("summonerLevel");
        String id = response.getString("id");
        String summonerName = response.getString("name");

        Summoner summoner = new Summoner(summonerName, id, summonerLevel, profileIconId);

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
        urlConnection.setRequestProperty("api_key", api_key);
    }

}
