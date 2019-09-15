package Main;

import API.DTO.*;
import Database.DatabaseManager;
import GameElements.Summoner;
import MVC.Controllers.MasterController;
import Stats.ChampionStats;
import Tests.TestMatch;
import Utils.Utils;
import Utils.MatchScraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

        RiotAPIHandler a = new RiotAPIHandler();
        SummonerEndpoint se = new SummonerEndpoint("na1", a.getApi_key());

        DatabaseManager db = DatabaseManager.getInstance();
//
//        ObjectMapper m = new ObjectMapper();
//
//        try {
//            long[] matchids = m.readValue(new File("D:/Users/spaouellet/Documents/Coding/tmp/ids.json"), long[].class);
//
//            for (int i = 0 ; i < matchids.length; ++i) {
//                Match match = new Match(matchids[i]);
//
//                if (match.isValid()) {
//                    db.addMatch(match);
//                }
//            }
//            System.out.println(matchids.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(se.getSummonerByName("Seer"));

//        MatchScraper.getMatchesFromTopOfLadderForPatch("9", Match.PatchMatchMode.MAJOR_VERSION);
//        System.out.println("Found: " + matches.size() + " matches.");

        Long vk = Long.valueOf(ChampionDTO.getByName("viktor").getKey());

//        db.getMatchTimelinesForChampion(vk);

        ChampionStats cs = new ChampionStats(vk, "9.17");

//        String token = "2fFd9zgMi8baywoLT58MVw";
//        String auth = "riot:" + token;
//        System.out.println(Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII"))));

        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = Session.getInstance();
        System.out.print("Starting with GUI enabled...");
        Summoner cachedSummoner = (Summoner)session.getCache().getLast();
        MasterController masterController;
        Parent root;
//        root = FXMLLoader.load(getClass().getResource("../MVC/fxml/Summoner.fxml"));
//        masterController = new MasterController(primaryStage, root, cachedSummoner);
        if (cachedSummoner != null) {
            root = FXMLLoader.load(getClass().getResource("/fxml/Summoner.fxml"));
            masterController = new MasterController(primaryStage, root, cachedSummoner);
        } else {
            root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            masterController = new MasterController(primaryStage, root);
        }
        masterController.showStage();
        System.out.println("success");
    }
}
