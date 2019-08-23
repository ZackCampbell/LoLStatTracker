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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Pair;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    private Label saveButton = new Label();
    private static final Color FILL_COLOR = Color.web("#DDB905");
    private GridPane summonerGrid;
    private final int numRows = 8;
    private final int numCols = 11;
    private Layout currentLayout = new Layout(numRows, numCols);
    private Layout prevLayout;
    private boolean isMaximized = false;

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
        saveButton.setTextAlignment(TextAlignment.CENTER);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setLayoutY(80);
        saveButton.setPrefWidth(40);
        saveButton.setPrefHeight(40);
        saveButton.setGraphic(iconView);
        saveButton.setOnMouseClicked(this::save);
    }

    /**
     * User hit the save button to save their tile layout
     *
     * @param event occurs when the save button is pressed
     */
    @FXML
    private void save(MouseEvent event) {
        editEnabled = false;
        // TODO: Show a new menu for save or save as (save will overwrite the current layout)
        // TODO: Save as will pop up a new window for creating a new layout or overwriting an exiting layout

        for (Widget widget : standardWidgets) {
            widget.setEditEnabled(false);
        }
        for (Widget widget : customWidgets) {
            widget.setEditEnabled(false);
        }
        for (Widget widget : selectedWidgets) {
            widget.setEditEnabled(false);
        }

        // TODO: Move the code below to happen on the new save buttons
        String layoutName = "<placeholder>";        // TODO: Fill with the real layout name that the user inputs
        content.getChildren().remove(saveButton);

//        Layout newLayout = Layout.createLayout(gridAnchorPane, numRows, numCols);
//        newLayout.saveLayout(layoutName);
//        currentLayout = newLayout;
    }

    // ----------------------- Popup Functions -----------------------------

    public static Popup getPopup() {
        return popup;
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

        prevLayout = currentLayout;
        // TODO: Save the current layout and set the grid + the widgets in the grid to edit mode
    }

    // ----------------------- Layout Functions ---------------------------
    // TODO: Create layout txt files for the default layouts

    private Layout getSavedLayout() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new File("src/MVC/Layouts/layoutconfig.xml"));

            String fileName = getLayoutFileName(document);

            FileInputStream fileInputStream = new FileInputStream(new File("../Layouts/" + fileName + ".txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            currentLayout = (Layout)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println("Unable to find the layout file");
//            e.printStackTrace();
        }
        return currentLayout;
    }

    private String getLayoutFileName(Document document) {
        NodeList nodeList = document.getElementsByTagName("file");
        for (int i = 0; i < nodeList.getLength(); i++) {
            org.w3c.dom.Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element fileElement = (Element)currentNode;
                if (fileElement.getElementsByTagName("mostrecent").item(0).getTextContent().equals("true")) {
                    return fileElement.getElementsByTagName("name").item(0).getTextContent();
                }
            }
        }
        return document.getElementsByTagName("file").item(0).getTextContent();         // Return the first default layout if it doesn't find a "true" value
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

    // TODO: Actually update the grid to show the changes - Might have to create new gridpanes...
    private boolean addWidget(Widget widget) {
        Point coords;
        try {
            coords = getNextGridCoords(summonerGrid, selectedWidgets, widget.getRowSpan(), widget.getColSpan());
        } catch (WidgetException e) {
            System.out.println("Cannot add a widget of that size to the grid");
            return false;
        }
        widget.setVisible(true);
        widget.setRowIndex(coords.x);
        widget.setColIndex(coords.y);
        selectedWidgets.add(widget);
        currentLayout.addWidget(widget);
        if (!gridAnchorPane.getChildren().isEmpty())
            gridAnchorPane.getChildren().remove(0);
        summonerGrid = currentLayout.loadOntoGridpane(new GridPane(), gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight());
        gridAnchorPane.getChildren().add(summonerGrid);
        return true;
    }

    // TODO: Actually update the grid to show the changes using the layout
    private void removeWidget(Widget widget) {
        widget.setVisible(false);
        if (selectedWidgets.contains(widget))
            selectedWidgets.remove(widget);
        currentLayout.removeWidget(widget);
        gridAnchorPane.getChildren().remove(0);
        summonerGrid = currentLayout.loadOntoGridpane(new GridPane(), gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight());
        gridAnchorPane.getChildren().add(summonerGrid);
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
        currentLayout = getSavedLayout();
        summonerGrid = new GridPane();
        currentLayout.loadOntoGridpane(summonerGrid, gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight());
    }

    private void addToGridPane(Widget widget) {

    }

    private void removeFromGridPane(Widget widget) {

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
