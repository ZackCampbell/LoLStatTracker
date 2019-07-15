package Main;


import API.RiotAPIHandler;
import GameElements.GameModeData.RankedData;
import GameElements.Summoner;

import java.util.logging.Logger;

import static Utils.Utils.*;

public class Main {
    private static Logger LOGGER;

    public static void main(String[] args) {
        LOGGER = initializeLogger(Main.class.getName());
//        LOGGER.log(Level.INFO, "Starting LoL stats application...");
        System.out.println("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();

        Summoner summoner = riotAPIHandler.getSummonerStub("KashyyykNative");

        if (summoner.isValid()) {
            System.out.println(summoner);

            for (RankedData data : riotAPIHandler.getRankedData(summoner)) {
                System.out.println(data);
            }
        } else {
            System.out.println("Could not find summoner");
        }
    }
}
