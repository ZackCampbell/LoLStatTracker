package Main;

import API.RiotAPIHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

public class MainGUI extends Application {

    private static Logger LOGGER;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER = initializeLogger(MainGUI.class.getName());
//        LOGGER.log(Level.INFO, "Starting LoL stats application...");
        System.out.println("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        System.out.println(riotAPIHandler.getSummonerData("KashyyykNative"));
    }
}
