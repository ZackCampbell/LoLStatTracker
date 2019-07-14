package Main.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controls the controllers and decides which window to display, keeps track of the window sizes and constants
 */
public class MasterController {

    private static Stage mainStage = null;
    private int windowX = 1200;
    private int windowY = 800;

    MasterController() {}

    public MasterController(Stage initialStage, Parent root) throws Exception {
        initialStage.setTitle("LoL Stat Tracker");
        initialStage.initStyle(StageStyle.UNDECORATED);
        mainStage = initialStage;
        mainStage.setScene(new Scene(root));
    }

    public void showHomePage() {
        mainStage.show();
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
