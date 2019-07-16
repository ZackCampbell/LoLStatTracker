package Main.Controllers;

import API.Session;
import GameElements.Summoner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent);
    }

    @FXML
    private void handleLogin(MouseEvent event) throws IOException {
        System.out.println("Pressed Login");
//        Session session = Session.getInstance();
//        initializeSummoner(session.getSummoner(summNameInput.getText()));
//        Parent summonerFXML = FXMLLoader.load(getClass().getResource("./Views/Summoner.fxml"));
//        content.getChildren().removeAll();
//        content.getChildren().setAll(summonerFXML);
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
