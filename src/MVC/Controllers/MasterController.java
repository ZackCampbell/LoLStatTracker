package MVC.Controllers;

import API.DTO.SummonerDTO;
import API.RiotAPIHandler;
import API.SummonerEndpoint;
import GameElements.Summoner;
import Main.Session;
import Utils.Cache;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;

import java.util.logging.Level;
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
    private RiotAPIHandler apiHandler = new RiotAPIHandler();
    private SummonerEndpoint summonerEndpoint;
    static HBox top;
    private double prevMinX;
    private double prevMinY;
    private double prevWidth;
    private double prevHeight;

    private static Logger LOGGER = initializeLogger(MasterController.class.getName());

    public MasterController() {}

    public MasterController(Stage initialStage, Parent root) {
        initializeMasterController(initialStage, root);
        getStage().setResizable(true);
        currentPage = "login";
    }

    public MasterController(Stage initialStage, Parent root, Summoner summoner) {
        initializeMasterController(initialStage, root);
        getStage().setResizable(true);
        getInitSummoner(summoner);
        currentPage = "summoner";
    }

    // TODO: Come back and update once summoner updates himself
    boolean getInitSummoner(Summoner summoner) {
        if (!summoner.isValid()) {
            summonerEndpoint = new SummonerEndpoint(summoner.getRegion(), apiHandler.getApi_key());
            return testSummonerDTO(summoner, summoner.getRegion());
        }
        currentSummoner = summoner;
        return true;
    }

    private boolean testSummonerDTO(Summoner summoner, String region) {
        SummonerDTO summDTO = summonerEndpoint.getSummonerByName(summoner.getName());
        if (summDTO == null) {
            LOGGER.log(Level.WARNING, "Summoner: " + summoner.getName() + " does not exist");
            return false;
        }
        setSummonerInfo(summoner, region, summDTO);
        Session session = Session.getInstance();
        Cache cache = session.getCache();
        cache.put(summoner.getName(), summoner);
        return true;
    }

    // TODO: Come back and update once summoner updates himself
    boolean getInputSummoner(Summoner summoner, String region) {
        if (!summoner.isValid()) {
            summonerEndpoint = new SummonerEndpoint(region, apiHandler.getApi_key());
            if (region.equalsIgnoreCase("na")) {
                summonerEndpoint = new SummonerEndpoint("na1", apiHandler.getApi_key());
                return testSummonerDTO(summoner, "na1");
            }
            return testSummonerDTO(summoner, region);
        }
        currentSummoner = summoner;
        return true;
    }

    private void setSummonerInfo(Summoner summoner, String region, SummonerDTO summDTO) {
        summoner.setRegion(region);
        summoner.setLevel(summDTO.getSummonerLevel());
        summoner.setEncryptedId(summDTO.getAccountId());
        summoner.setIconId(summDTO.getProfileIconId());
    }

    private void initializeMasterController(Stage initialStage, Parent root) {
        LOGGER = initializeLogger(MasterController.class.getName());
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(STAGE_STYLE);
        stage = initialStage;
        stage.setScene(new Scene(root));

    }

    void initializeStage(AnchorPane parent, HBox top, Popup popup) {
        parent.setPrefHeight(getWindowY());
        parent.setPrefWidth(getWindowX());
        makeStageDraggable(top, popup);
    }

    private void makeStageDraggable(HBox top, Popup popup) {
        top.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        top.setOnMouseDragged(event -> {
            getStage().setX(event.getScreenX() - xOffset);
            getStage().setY(event.getScreenY() - yOffset);
            if (popup != null) {
                Window parent = getStage().getScene().getWindow();
                double initX = parent.getX() + (parent.getWidth() / 2) - (popup.getWidth() / 2);
                double initY = parent.getY() + (parent.getHeight() / 2) - (popup.getHeight() / 2);
                popup.setX(initX + event.getSceneX() - xOffset);
                popup.setY(initY + event.getSceneY() - yOffset);
            }
        });
    }

    void maximizeStage(Stage stage) {
        Screen screen = Screen.getPrimary();
        prevMinX = stage.getX();
        prevMinY = stage.getY();
        prevWidth = stage.getWidth();
        prevHeight = stage.getHeight();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
//        stage.setMaximized(true);
    }

    void restoreStage(Stage stage) {
        stage.setX(prevMinX);
        stage.setY(prevMinY);
        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);
//        stage.setMaximized(false);
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

    static Stage getStage() {
        return stage;
    }

    private int getWindowX() {
        return windowX;
    }

    private int getWindowY() {
        return windowY;
    }

}
