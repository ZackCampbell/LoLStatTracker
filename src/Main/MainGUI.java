package Main;

import Utils.*;
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
        System.out.println(Utils.getRelativePath());

        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.print("Starting with GUI enabled...");
//        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        Summoner cachedSummoner = (Summoner)Session.getCache().getLast();
        MasterController masterController;
        Parent root;
        if (cachedSummoner != null) {
            root = FXMLLoader.load(getClass().getResource("./Views/Summoner.fxml"));
            masterController = new MasterController(primaryStage, root, cachedSummoner);
        } else {
            root = FXMLLoader.load(getClass().getResource("./Views/Login.fxml"));
            masterController = new MasterController(primaryStage, root);
        }
        masterController.showStage();
    }
}
