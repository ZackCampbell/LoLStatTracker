package Main;

import API.RiotAPIHandler;
import Main.Controllers.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

public class MainGUI extends Application {

    private static Logger LOGGER;

    public static void main(String[] args) {
        String guiEnabled = null;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOGGER = initializeLogger(MainGUI.class.getName());
        System.out.print("Testing API...");
        RiotAPIHandler riotAPIHandler = new RiotAPIHandler();
        riotAPIHandler.getSummonerData("KashyyykNative");
        System.out.println("Success");
        Parent root = FXMLLoader.load(getClass().getResource("Views/Home.fxml"));
        MasterController masterController = new MasterController(primaryStage, root);
        masterController.showHomePage();
    }
}
