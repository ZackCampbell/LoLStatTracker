package Main.Controllers;

import API.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import Utils.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
    @FXML private AnchorPane loginAnchorPane;
    @FXML private Label errorLabel;
    @FXML private ImageView bgImageLogin;

    private boolean loginActive;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent);
        errorLabel.setTextFill(Paint.valueOf("red"));
        loginActive = true;
        initBgImage();
    }

    private void initBgImage() {
        String path = Utils.getRelativePath();
        File[] files = new File(path + "/img/champion/splash").listFiles();
        Random random = new Random();
        Thread backgroundThread = new Thread(() -> {
            while (loginActive) {
                if (files != null && files.length != 0) {
                    Image image = new Image(files[random.nextInt(files.length)].toURI().toString());
                    bgImageLogin.setOpacity(0.1);
                    bgImageLogin.fitHeightProperty().bind(content.heightProperty());
                    bgImageLogin.fitWidthProperty().bind(content.widthProperty());
                    bgImageLogin.setImage(image);
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                }
            }
        });
        backgroundThread.start();
    }

    /**
     * Handler for the login button - get's the input summoner's information and loads their summoner page
     *
     * @param event is the press of the login button
     * @throws IOException on FXMLLoader
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        errorLabel.setText("");
        Session session = Session.getInstance();
        boolean success = initializeSummoner(session.getSummoner(summNameInput.getText()));
        if (!success) {
            errorLabel.setText("Error Retrieving Input Summoner");
            return;
        }
        loginActive = false;
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
