package MVC.Controllers;

import MVC.Widgets.*;
import MVC.Widgets.NameIconComboWidget;
import MVC.Widgets.NameWidget;
import MVC.Widgets.SummIconWidget;
import Utils.Utils;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
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
    @FXML private JFXDrawer menuDrawer;
    @FXML private Label reportBugBtn;
    @FXML private Label feedbackBtn;
    @FXML private Label logoutBtn;
    @FXML private Label editLayoutToggle;


    private ArrayList<Widget> standardWidgets = new ArrayList<>();
    private ArrayList<Widget> customWidgets = new ArrayList<>();
    private ArrayList<Widget> selectedWidgets = new ArrayList<>();
    private static Popup popup = new Popup();
    private Accordion menuAccordion;
    private boolean editEnabled = false;
    private Label saveButton = new Label();
    private static final Color FILL_COLOR = Color.web("#DDB905");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent, top);
        createStandardWidgets();
        createCustomWidgets();
        updateMenuAccordion();      // TODO: Extract methods from this
        initSaveButton();
    }

    @FXML
    private void handleLogout(MouseEvent event) throws IOException {
        Parent loginFXML = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        parent.getChildren().removeAll();
        parent.getChildren().setAll(loginFXML);
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
    private void save(MouseEvent event) {
        // TODO: Save the current layout in an XML file(?)
        editEnabled = false;
        content.getChildren().remove(saveButton);
        for (Widget widget : standardWidgets) {
            widget.setEditEnabled(false);
        }
        for (Widget widget : customWidgets) {
            widget.setEditEnabled(false);
        }
        for (Widget widget : selectedWidgets) {
            widget.setEditEnabled(false);
        }

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

    @FXML
    private void menuButtonClicked(MouseEvent event) {
        if (menuDrawer.isOpened() || menuDrawer.isOpening())
            menuDrawer.close();
        else if (menuDrawer.isClosed() || menuDrawer.isClosing())
            menuDrawer.open();
    }

    @FXML
    private void editLayout(MouseEvent event) {
        if (!editEnabled) {
            content.getChildren().add(saveButton);
            editEnabled = true;
        } else {
            content.getChildren().remove(saveButton);
            editEnabled = false;
        }
        for (Widget widget : selectedWidgets) {
            widget.setEditEnabled(true);
        }
        // TODO: Save the current layout and set the grid + the widgets in the grid to edit mode
    }

    private void initSaveButton() {
        FontAwesomeIconView iconView = new FontAwesomeIconView();
        iconView.setGlyphName("SAVE");
        iconView.setFill(FILL_COLOR);
        iconView.setSize("18");
        saveButton.setTextAlignment(TextAlignment.CENTER);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setLayoutY(80);
        saveButton.setPrefWidth(40);
        saveButton.setPrefHeight(40);
        saveButton.setGraphic(iconView);
        saveButton.setOnMouseClicked(this::save);
    }

    private void buildDefaultLayout() {

    }

    private void buildCustomLayout() {

    }

    private void getSavedLayout() {

    }

    private void updateMenuAccordion() {
        menuAccordion = new Accordion();
        menuAccordion.getStyleClass().add("popup");
        menuAccordion.getStylesheets().add(getClass().getResource("../Stylesheets/AccordionStylesheet.css").toExternalForm());
        TitledPane standardPane, customPane;
        if (menuAccordion.getPanes().size() == 0) {
            standardPane = createTitledPane("Standard");
            customPane = createTitledPane("Custom");
        } else {
            standardPane = menuAccordion.getPanes().get(0);
            customPane = menuAccordion.getPanes().get(1);
        }
        menuAccordion.getPanes().removeAll();
        menuAccordion.getPanes().add(standardPane);
        menuAccordion.getPanes().add(customPane);
        menuDrawer.setSidePane(menuAccordion);
        // TODO: Add functionality to search for and add custom widgets to the custom menu (menuAccordion.getPanes().get(1))
    }

    private TitledPane createTitledPane(String title) {
        TitledPane standardPane = new TitledPane();
        standardPane.setText(title);
        JFXListView<Widget> standardContent = new JFXListView<>();
        standardContent.setEditable(false);
        standardContent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        switch (title) {
            case ("Standard"):
                standardContent.setItems(FXCollections.observableArrayList(standardWidgets));
                break;
            case ("Custom"):
                standardContent.setItems(FXCollections.observableArrayList(customWidgets));
                break;
        }
        standardContent.setCellFactory(new WidgetCellFactory());
        standardContent.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            Node node = event.getPickResult().getIntersectedNode();
            while (node != null && node != standardContent && !(node instanceof WidgetCell)) {
                node = node.getParent();
            }
            if (node instanceof WidgetCell) {
                event.consume();
                WidgetCell cell = (WidgetCell)node;
                JFXListView lv = (JFXListView)cell.getListView();
                lv.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (cell.isSelected()) {
                        lv.getSelectionModel().clearSelection(index);
                        removeWidget(cell.getItem());
                    } else {
                        lv.getSelectionModel().select(index);
                        addWidget(cell.getItem());
                    }
                }
            }
        });

        standardPane.setContent(standardContent);
        return standardPane;
    }

    private void addWidget(Widget widget) {
        widget.setVisible(true);
        selectedWidgets.add(widget);
        widget.setRowIndex(0);
        widget.setColIndex(0);
        // TODO: Change row and column coordinates with calculated values based on what else is in the grid and the span
        summonerGrid.getChildren().add(widget.getPane());   // TODO: Test this
        summonerGrid.add(widget.getPane(), 0, 0, widget.getRowSpan(), widget.getColSpan());

    }

    @SuppressWarnings("all")
    private void removeWidget(Widget widget) {
        widget.setVisible(false);
        selectedWidgets.remove(widget);
        ObservableList<Node> children = summonerGrid.getChildren();
        for (Node node : children) {
            if (node instanceof AnchorPane && summonerGrid.getRowIndex(node) == widget.getRowIndex() &&
                    summonerGrid.getColumnIndex(node) == widget.getColIndex()) {
                summonerGrid.getChildren().remove(node);
                break;
            }
        }
    }

    private void createStandardWidgets() {
        standardWidgets.add(new NameWidget());
        standardWidgets.add(new SummIconWidget());
        standardWidgets.add(new NameIconComboWidget());
    }

    private void createCustomWidgets() {

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
