package Main;

import API.RiotAPIHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

public class MainGUI extends Application {

    private static Logger LOGGER;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOGGER = initializeLogger(MainGUI.class.getName());
//        LOGGER.log(Level.INFO, "Starting LoL stats application...");
        System.out.println("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        System.out.println(riotAPIHandler.getSummonerData("KashyyykNative"));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocs/Home.fxml"));
        primaryStage.setTitle("LoL Stat Tracker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
