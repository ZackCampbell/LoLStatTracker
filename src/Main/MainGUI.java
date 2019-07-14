package Main;

import API.RiotAPIHandler;
import Main.Controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        System.out.print("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        riotAPIHandler.getSummonerData("KashyyykNative");
        System.out.println("Success");
        Parent root = FXMLLoader.load(getClass().getResource("Views/Home.fxml"));
        MasterController masterController = new MasterController(primaryStage, root);
        masterController.showHomePage();
    }
}
