package Main.Controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controls the controllers and decides which window to display, keeps track of the window sizes and constants
 */
public class MasterController {

    private static Stage mainStage = null;
    private static Stage currentStage = null;
    private int windowX = 1200;
    private int windowY = 800;
    private final StageStyle STAGE_STYLE = StageStyle.UNDECORATED;

    MasterController() {}

    // TODO: Should check cached data to see if user needs to log in or can directly display their summoner information

    public MasterController(Stage initialStage, Parent root) {
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(STAGE_STYLE);
        mainStage = initialStage;
        mainStage.setScene(new Scene(root));
    }

    public void showHomePage() {
        mainStage.show();
    }

    public void showCurrentPage() {
        currentStage.show();
    }

    // UNTESTED - This probably won't work
    void setStage(Stage stage, Parent root) {
        currentStage = stage;
        currentStage.setScene(new Scene(root));
    }

    Stage getCurrentStage() {
        return currentStage;
    }

    Stage getMainStage() {
        return mainStage;
    }

    int getWindowX() {
        return windowX;
    }

    int getWindowY() {
        return windowY;
    }

}
