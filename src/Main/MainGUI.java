package Main;

import API.RiotAPIHandler;
import GameElements.Summoner;
import Main.Controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

public class MainGUI extends Application {

    private static Logger LOGGER = initializeLogger(MainGUI.class.getName());

    public static void main(String[] args) {
        String guiEnabled = "true";
        if (args.length > 0) {
            guiEnabled = args[0];
        }
        if (guiEnabled.equals("false")) {
            startWithoutGUI();
        } else {
            launch(args);
        }
    }

    public static void startWithoutGUI() {
        System.out.println("Started without GUI");

        // Add any functions here


        System.exit(0);
    }

    // TODO: Should check cached data to see if user needs to log in or can directly display their summoner information

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.print("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        //riotAPIHandler.getSummonerData("KashyyykNative");
        //System.out.println("Success");
        boolean isSummonerCached = false;       // Set this based on whether the summoner data was cached or not (maybe pass as a param?)
        MasterController masterController;
        Parent root;
        if (isSummonerCached) {
            Summoner summoner = null;
            root = FXMLLoader.load(getClass().getResource("./Views/Summoner.fxml"));
            masterController = new MasterController(primaryStage, root, summoner);
        } else {
            root = FXMLLoader.load(getClass().getResource("./Views/Login.fxml"));
            masterController = new MasterController(primaryStage, root);
        }
        masterController.showStage();
    }
}
