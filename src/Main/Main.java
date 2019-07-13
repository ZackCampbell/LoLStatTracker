package Main;


import API.RiotAPIHandler;
import Logging.LoggingHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import static Utils.Utils.*;

public class Main {
    private static Logger LOGGER;



    public static void main(String[] args) {
        LOGGER = initializeLogger(Main.class.getName());
//        LOGGER.log(Level.INFO, "Starting LoL stats application...");
        System.out.println("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        System.out.println(riotAPIHandler.getAPIData());
    }
}
