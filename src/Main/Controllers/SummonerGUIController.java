package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the "Summoner" page of the GUI providing methods to interface the back-end with the buttons and display elements
 */
public class SummonerGUIController extends MasterController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML private HBox top;
    @FXML private Pane content;
    @FXML private GridPane summonerGrid;
    @FXML private Label reportBugBtn;
    @FXML private Label feedbackBtn;
    @FXML private Accordion menuAccordion;
    @FXML private Accordion editAccordion;


    private ArrayList<String> tiles = new ArrayList<>();        // TODO: Convert to arraylist of objects that represent tiles
    private ArrayList<String> tileTypes = new ArrayList<>();
    private static Popup popup = new Popup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent, top);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent loginFXML = FXMLLoader.load(getClass().getResource("Main/Views/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(loginFXML);
    }

    static Popup getPopup() {
        return popup;
    }

    /**
     * User hit the save button to save their tile layout
     *
     * @param event occurs when the save button is pressed
     */
    @FXML
    private void save(ActionEvent event) {

    }

    /**
     * Displays bug report popup
     *
     * @param event
     */
    @FXML
    private void getBug(MouseEvent event) throws IOException {
        AnchorPane bugPane = FXMLLoader.load(getClass().getResource("../Views/BugReportPopUp.fxml"));

        popup.getContent().add(bugPane);
//        popup.setAnchorX();
//        popup.setAnchorY();
        popup.show(getStage());

    }

    /**
     * Displays the feedback popup
     *
     * @param event
     */
    @FXML
    private void getFeedback(MouseEvent event) {

    }

    @FXML
    private void sendFeedback(MouseEvent event) {

        // TODO: Send "feedback" to the server using the local client socket

    }

    /**
     * User hit the edit cog so that they can manipulate their tiles
     *
     * @param event
     */
    @FXML
    private void editLayout(ActionEvent event) {

    }

    private void buildDefaultLayout() {

    }

    private void buildCustomLayout() {

    }

    private void getSavedLayout() {

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
