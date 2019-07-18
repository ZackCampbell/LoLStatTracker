package Main.Controllers;

import API.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the "Login" page of the GUI providing methods to interface the back-end with the buttons and display elements
 */
public class LoginController extends MasterController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML private HBox top;
    @FXML private Pane content;
    @FXML private TextField summNameInput;
    @FXML private Button loginButton;
    @FXML private Pane logoPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent);
    }

    /**
     * Handler for the login button - get's the input summoner's information and loads their summoner page
     *
     * @param event is the press of the login button
     * @throws IOException on FXMLLoader
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Session session = Session.getInstance();
        initializeSummoner(session.getSummoner(summNameInput.getText()));
        Parent summonerFXML = FXMLLoader.load(getClass().getResource("../Views/Summoner.fxml"));
        parent.getChildren().removeAll();
        parent.getChildren().setAll(summonerFXML);
    }

    @FXML
    void minimizeStage(MouseEvent event) {
        super.minimizeStage(getStage());
    }

    @FXML
    void closeApp(MouseEvent event) {
        super.closeApp();
    }

}
