package Main;

import API.DTO.*;
import API.MatchEndpoint;
import API.RiotAPIHandler;
import API.SummonerEndpoint;
import GameElements.Summoner;
import Main.Controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Base64;
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
                MatchEndpoint me = new MatchEndpoint("na1", apiHandler.getApi_key());

                // Simple endpoint test
                SummonerDTO s = se.getSummonerByName("Seer");

                if (s != null) {
                    MatchListDTO ml = me.getMatchListByAccountId(s.accountId);
                    MatchReferenceDTO ref = ml.matches.get(0);
                    MatchTimelineDTO timeline = me.getMatchTimelinesById(ref.gameId);

                    System.out.println(ref.champion);
                }

        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = Session.getInstance();
        System.out.print("Starting with GUI enabled...");
        Summoner cachedSummoner = (Summoner)session.getCache().getLast();
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
        System.out.println("success");
    }
}
