package Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the "Home" page of the GUI providing methods to interface the back-end with the buttons and display elements
 */
public class MainGUIController extends MasterController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private HBox top;

    private double xOffset = 0.0;
    private double yOffset = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parent.prefWidth(getWindowX());
        parent.prefHeight(getWindowY());
        makeStageDraggable();
    }

    private void makeStageDraggable() {
        parent.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {
            getMainStage().setX(event.getScreenX() - xOffset);
            getMainStage().setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    private void minimizeStage(MouseEvent event) {
        getMainStage().setIconified(true);
    }

    @FXML
    private void closeApp(MouseEvent event) {
        System.exit(0);
    }

}
