package Main;

import API.DTO.*;
import Database.DatabaseManager;
import GameElements.Summoner;
import MVC.Controllers.MasterController;
import Utils.Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static Utils.Utils.*;

public class MainGUI extends Application {

    private static Logger LOGGER = initializeLogger(MainGUI.class.getName());

    public static void main(String[] args) {
        String guiEnabled = "true";
        if (args.length > 0) {
            guiEnabled = args[0];
        }

        Utils.initRegionCodes();
        ChampionDTO.init();
        RuneTreeDTO.init();
        ItemDTO.init();
        DatabaseManager.updateDataDragonResources();

        if (guiEnabled.equals("false")) {
            startWithoutGUI();
        } else {
            launch(args);
        }
    }

    public static void startWithoutGUI() {
        System.out.println("Started without GUI");

//        Set<Long> matches = MatchScraper.getMatchesFromTopOfLadderForPatch("na1", new String[]{"9.16"}, Match.PatchMatchMode.MINOR_VERSION);
//        System.out.println("Found: " + matches.size() + " matches.");

        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = Session.getInstance();
        System.out.print("Starting with GUI enabled...");
        Summoner cachedSummoner = (Summoner)session.getCache().getLast();
        MasterController masterController;
        Parent root;
//        root = FXMLLoader.load(getClass().getResource("../MVC/Views/Summoner.fxml"));
//        masterController = new MasterController(primaryStage, root, cachedSummoner);
        if (cachedSummoner != null) {
            root = FXMLLoader.load(getClass().getResource("../MVC/Views/Summoner.fxml"));
            masterController = new MasterController(primaryStage, root, cachedSummoner);
        } else {
            root = FXMLLoader.load(getClass().getResource("../MVC/Views/Login.fxml"));
            masterController = new MasterController(primaryStage, root);
        }
        masterController.showStage();
        System.out.println("success");
    }
}
