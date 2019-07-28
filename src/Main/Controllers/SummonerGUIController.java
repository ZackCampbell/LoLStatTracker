package Main.Controllers;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Window;

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
    @FXML private JFXDrawer menuDrawer;
    @FXML private JFXDrawer editDrawer;


    private ArrayList<String> tiles = new ArrayList<>();        // TODO: Convert to arraylist of objects that represent tiles
    private ArrayList<String> tileTypes = new ArrayList<>();
    private static Popup popup = new Popup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent, top);
        initEditDrawer();
        initMenuDrawer();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent loginFXML = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(loginFXML);
    }

    public static Popup getPopup() {
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
        initPopup(bugPane);
    }

    /**
     * Displays the feedback popup
     *
     * @param event
     */
    @FXML
    private void getFeedback(MouseEvent event) throws IOException {
        AnchorPane feedbackPane = FXMLLoader.load(getClass().getResource("../Views/FeedbackPopUp.fxml"));
        initPopup(feedbackPane);
    }

    private void initPopup(AnchorPane pane) {
        if (popup.isShowing()) {
            popup.hide();
            popup.getContent().removeAll();
        }
        EventHandler<MouseEvent> closeHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                popup.hide();
                content.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
            }
        };
        try {
            content.removeEventHandler(MouseEvent.MOUSE_PRESSED, closeHandler);
        } catch (Exception e) {}

        popup.getContent().add(pane);
        Window parent = getStage().getScene().getWindow();
        popup.setHideOnEscape(true);
        content.addEventHandler(MouseEvent.MOUSE_PRESSED, closeHandler);
        popup.show(getStage());
        double initX = parent.getX() + (parent.getWidth() / 2) - (popup.getWidth() / 2);
        double initY = parent.getY() + (parent.getHeight() / 2) - (popup.getHeight() / 2);
        popup.setX(initX);
        popup.setY(initY);
    }

    private void initMenuDrawer() {
        try {
            Accordion menuAccordion = FXMLLoader.load(getClass().getResource("../Views/MenuAccordion.fxml"));
            menuDrawer.setSidePane(menuAccordion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initEditDrawer() {
        try {
            Accordion editAccordion = FXMLLoader.load(getClass().getResource("../Views/EditAccordion.fxml"));
            editDrawer.setSidePane(editAccordion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuButtonClicked(MouseEvent event) {
        if (menuDrawer.isOpened() || menuDrawer.isOpening())
            menuDrawer.close();
        else if (menuDrawer.isClosed() || menuDrawer.isClosing())
            menuDrawer.open();
    }

    @FXML
    private void editLayout(MouseEvent event) {
        if (editDrawer.isOpening() || editDrawer.isOpened())
            editDrawer.close();
        else if (editDrawer.isClosed() || editDrawer.isClosing())
            editDrawer.open();
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
