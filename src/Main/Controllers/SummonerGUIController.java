package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the "Summoner" page of the GUI providing methods to interface the back-end with the buttons and display elements
 */
public class SummonerGUIController extends MasterController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private HBox top;
    @FXML
    private Pane content;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent);
    }

    @FXML
    void minimizeStage(MouseEvent event) {
        super.minimizeStage(getStage());
    }

    @FXML
    void closeApp(MouseEvent event) {
        super.closeApp();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent loginFXML = FXMLLoader.load(getClass().getResource("./Views/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(loginFXML);
    }

}
