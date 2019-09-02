package MVC.Controllers;

import MVC.Layouts.Layout;
import MVC.Widgets.*;
import MVC.Widgets.NameIconComboWidget;
import MVC.Widgets.NameWidget;
import MVC.Widgets.SummIconWidget;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Window;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Utils.Utils.getNextGridCoords;

/**
 * Controls the "Summoner" page of the GUI providing methods to interface the back-end with the buttons and display elements
 */
public class SummonerGUIController extends MasterController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML private HBox top;
    @FXML private Pane content;
    @FXML private AnchorPane gridAnchorPane;
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
    private static final Color FILL_COLOR = Color.web("#DDB905");
    private GridPane summonerGrid;
    private final int numRows = 11;
    private final int numCols = 8;
    private Layout currentLayout;
    private Layout prevLayout;
    private boolean isMaximized = false;
    private MenuButton saveMenu = new MenuButton();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent, top, getPopup());
        createStandardWidgets();
        createCustomWidgets();
        updateMenuAccordion();
        initSaveButton();
        initGridPane();
    }

    @FXML
    private void handleLogout(MouseEvent event) throws IOException {
        Parent loginFXML = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        parent.getChildren().removeAll();
        parent.getChildren().setAll(loginFXML);
    }

    // ----------------------- Sidebar Buttons -----------------------------

    private void initSaveButton() {
        FontAwesomeIconView iconView = new FontAwesomeIconView();
        iconView.setGlyphName("SAVE");
        iconView.setFill(FILL_COLOR);
        iconView.setSize("18");
        saveMenu.setPopupSide(Side.RIGHT);
        saveMenu.setTextAlignment(TextAlignment.CENTER);
        saveMenu.setAlignment(Pos.CENTER);
        saveMenu.setLayoutY(80);
        saveMenu.setPrefHeight(40);
        saveMenu.setPrefWidth(40);
        saveMenu.setGraphic(iconView);
        saveMenu.setText("");

        MenuItem overwriteSave = new MenuItem("Save");
        overwriteSave.setOnAction(event -> overwriteSave());

        MenuItem saveAs = new MenuItem("Save As");
        saveAs.setOnAction(event -> saveAs());
        saveMenu.getItems().addAll(overwriteSave, saveAs);
    }

    private void save() {
        editEnabled = false;
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

    private void overwriteSave() {
        saveMenu.hide();
        save();
        content.getChildren().remove(saveMenu);
        Layout newLayout = Layout.createLayout(currentLayout.getLayoutName(), selectedWidgets, numRows, numCols);
        newLayout.saveLayout(currentLayout.getLayoutName());
        currentLayout = newLayout;
    }

    private void saveAs() {
        // TODO: Load the save as popup with menu for selecting which to overwrite or the option to create an entirely new layout
        saveMenu.hide();

    }

    private void saveAsNewLayout(MouseEvent event) {
        String layoutName = "<placeholder>";        // TODO: Fill with the real layout name that the user inputs
        save();
        content.getChildren().remove(saveMenu);
        Layout newLayout = Layout.createLayout(layoutName, selectedWidgets, numRows, numCols);
        newLayout.saveLayout(layoutName);
        currentLayout = newLayout;
    }

    // ----------------------- Popup Functions -----------------------------

    public static Popup getPopup() {
        return popup;
    }

    @FXML
    private void getBug(MouseEvent event) throws IOException {
        AnchorPane bugPane = FXMLLoader.load(getClass().getResource("../Views/BugReportPopUp.fxml"));
        initPopup(bugPane);
    }

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
    private void editLayout(MouseEvent event) {
        if (!editEnabled) {                         // Set everything to edit mode
            content.getChildren().add(saveMenu);
            editEnabled = true;
            prevLayout = currentLayout;
            updateGridPane(true);
        } else {                                    // Set everything to static mode
            content.getChildren().remove(saveMenu);
            editEnabled = false;
            currentLayout = prevLayout;
            updateGridPane(false);
        }


        // TODO: Set the grid + the widgets in the grid to edit mode
    }

    private void updateGridPane(boolean edit) {
        if (!gridAnchorPane.getChildren().isEmpty())
            gridAnchorPane.getChildren().remove(0);
        summonerGrid = currentLayout.loadOntoGridpane(new GridPane(), gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight(), edit);
        gridAnchorPane.getChildren().add(summonerGrid);
    }

    // ------------------ Menu Functions -----------------------------

    @FXML
    private void menuButtonClicked(MouseEvent event) {
        if (menuDrawer.isOpened() || menuDrawer.isOpening())
            menuDrawer.close();
        else if (menuDrawer.isClosed() || menuDrawer.isClosing())
            menuDrawer.open();
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
    }

    private TitledPane createTitledPane(String title) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(title);
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
                        Widget item = cell.getItem();
                        item.setListName("  " + cell.getText().substring(2));
                        cell.updateItem(item, false);
                        lv.getSelectionModel().clearSelection(index);
                        removeWidget(cell.getItem());
                    } else {
                        Widget item = cell.getItem();
                        boolean success = addWidget(cell.getItem());
                        if (success) {
                            item.setListName("> " + cell.getText().substring(2));
                            cell.updateItem(item, false);
                            lv.getSelectionModel().select(index);
                        } else {
                            item.setListName("  " + cell.getText().substring(2));
                            cell.updateItem(item, false);
                            lv.getSelectionModel().clearSelection(index);
                        }
                    }
                }
            }
        });

        titledPane.setContent(standardContent);
        return titledPane;
    }

    // ------------------ Widget Functions ---------------------------

    private boolean addWidget(Widget widget) {
        Point coords = getNextGridCoords(summonerGrid, selectedWidgets, widget.getRowSpan(), widget.getColSpan());
        if (coords == null) {
            System.out.println("Cannot add a widget of that size to the grid");
            return false;
        }
        widget.setVisible(true);
        widget.setRowIndex(coords.x);
        widget.setColIndex(coords.y);
        selectedWidgets.add(widget);
        currentLayout.addWidget(widget);
        updateGridPane(false);
        return true;
    }

    private void removeWidget(Widget widget) {
        widget.setVisible(false);
        if (selectedWidgets.contains(widget))
            selectedWidgets.remove(widget);
        currentLayout.removeWidget(widget);
        updateGridPane(false);
    }

    private void createStandardWidgets() {
        standardWidgets.add(new NameWidget());
        standardWidgets.add(new SummIconWidget());
        standardWidgets.add(new NameIconComboWidget());
    }

    private void createCustomWidgets() {

    }

    // ------------------ Grid Functions -----------------------------

    private void initGridPane() {
        currentLayout = Layout.getSavedLayout();
        summonerGrid = new GridPane();
        updateGridPane(false);
    }

    // ------------------- Stage Functions (Superclass) ---------------------------

    @FXML
    void maximizeStage(MouseEvent event) {
        // TODO: Change the icon to be maximize or restore respectively
        if (isMaximized) {
            super.restoreStage(getStage());
            isMaximized = false;
        } else {
            super.maximizeStage(getStage());
            isMaximized = true;
        }

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
