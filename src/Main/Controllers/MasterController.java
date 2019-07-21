package Main.Controllers;

import API.RiotAPIHandler;
import API.SummonerEndpoint;
import GameElements.Summoner;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.logging.Logger;

import static Utils.Utils.initializeLogger;

/**
 * Controls the controllers and decides which window to display, keeps track of the window sizes and constants
 */
public class MasterController {

    private static Stage stage = null;
    private String currentPage;
    private final int windowX = 1200;
    private final int windowY = 800;
    private double xOffset = 0.0;
    private double yOffset = 0.0;
    private final StageStyle STAGE_STYLE = StageStyle.UNDECORATED;
    private Summoner currentSummoner;
    private RiotAPIHandler apiHandler;
    private SummonerEndpoint summonerEndpoint;

    private static Logger LOGGER = initializeLogger(MasterController.class.getName());

    MasterController() {}

    public MasterController(Stage initialStage, Parent root) {
        initializeMasterController(initialStage, root);
        currentPage = "login";
    }

    public MasterController(Stage initialStage, Parent root, Summoner summoner) {
        initializeMasterController(initialStage, root);
        getInitSummoner(summoner);
        currentPage = "summoner";
    }

    boolean getInitSummoner(Summoner summoner) {
        if (!summoner.isValid()) {

            // TODO: Make initial API call to get encryptedSummId and populate summoner, add them to the cache
            // TODO: If still not a valid response from the API (ie. Summoner doesn't exist), return false
        }
        // TODO: Get all the information pertaining to the input summoner for displaying on the GUI
        summonerEndpoint = new SummonerEndpoint(summoner.getRegion(), apiHandler.getApi_key());
        currentSummoner = summoner;
        System.out.println(currentSummoner);
        return false;
    }

    boolean getInputSummoner(Summoner summoner, String region) {
        if (!summoner.isValid()) {
            // TODO: Make initial API call to get encryptedSummId and populate summoner, add them to the cache
            // TODO: If still not a valid response from the API (ie. Summoner doesn't exist), return false
//            return false;
        }
        // TODO: Get all the information pertaining to the input summoner for displaying on the GUI
        summonerEndpoint = new SummonerEndpoint(region, apiHandler.getApi_key());
        currentSummoner = summoner;
        System.out.println(currentSummoner);
//        return true;
        return false;
    }

    private void initializeMasterController(Stage initialStage, Parent root) {
        LOGGER = initializeLogger(MasterController.class.getName());
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(STAGE_STYLE);
        stage = initialStage;
        stage.setScene(new Scene(root));
        apiHandler = new RiotAPIHandler();
    }

    void initializeStage(AnchorPane parent) {
        parent.setPrefHeight(getWindowY());
        parent.setPrefWidth(getWindowX());
        makeStageDraggable(parent);
    }

    private void makeStageDraggable(AnchorPane parent) {
        parent.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {
            getStage().setX(event.getScreenX() - xOffset);
            getStage().setY(event.getScreenY() - yOffset);
        });
    }

    void minimizeStage(Stage stage) {
        stage.setIconified(true);
    }

    void closeApp() {
        System.exit(0);
    }

    public void showStage() {
        stage.show();
    }

    String getPage() {
        return currentPage;
    }

    Stage getStage() {
        return stage;
    }

    private int getWindowX() {
        return windowX;
    }

    private int getWindowY() {
        return windowY;
    }

}
