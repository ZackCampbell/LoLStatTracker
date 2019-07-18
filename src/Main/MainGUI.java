package Main;

import Utils.*;
import API.RiotAPIHandler;
import API.SummonerEndpoint;
import GameElements.Summoner;
import Main.Controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
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

        RiotAPIHandler apiHandler = new RiotAPIHandler();
        SummonerEndpoint se = new SummonerEndpoint("na1", apiHandler.getApi_key());

        // Simple SummonerEndpoint test
        try {
            Summoner s = se.getSummonerByName("Seer");
            System.out.println(s);
            System.out.println(se.getSummonerByEncryptedId(s.getEncryptedId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
