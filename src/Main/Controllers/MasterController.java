package Main.Controllers;

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

    private static Logger LOGGER = initializeLogger(MasterController.class.getName());

    MasterController() {}

    public MasterController(Stage initialStage, Parent root) {
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(STAGE_STYLE);
        stage = initialStage;
        stage.setScene(new Scene(root));
        currentPage = "login";
    }

    public MasterController(Stage initialStage, Parent root, Summoner summoner) {
        LOGGER = initializeLogger(MasterController.class.getName());
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(STAGE_STYLE);
        stage = initialStage;
        stage.setScene(new Scene(root));
        initializeSummoner(summoner);
        currentPage = "summoner";
    }

    void initializeSummoner(Summoner summoner) {
        if (!summoner.isValid()) {
            // Make initial API call to get encryptedSummId and populate summoner
        }
        // Get all the information pertaining to the input summoner for displaying on the GUI
        currentSummoner = summoner;
    }

    void initializeStage(AnchorPane parent) {
        parent.setPrefHeight(getWindowY());
        parent.setPrefWidth(getWindowX());
        makeStageDraggable(parent);
    }

    void makeStageDraggable(AnchorPane parent) {
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

    int getWindowX() {
        return windowX;
    }

    int getWindowY() {
        return windowY;
    }

}
