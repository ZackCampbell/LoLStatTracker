package MVC.Controllers;

import MVC.Layouts.Layout;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Pair;

import java.awt.*;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStage(parent, top);
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

    private Layout getSavedLayout() {

        // TODO: Look through XML(?) files for marked file - if found, load that xml as a Layout and store in currentLayout else load from one of the default layouts
        return currentLayout;       // TODO: Placeholder for actual logic
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

    // TODO: Actually update the grid to show the changes - Might have to create new gridpanes...
    private boolean addWidget(Widget widget) {
        Point coords;
        try {
            coords = getNextGridCoords(widget.getRowSpan(), widget.getColSpan());
        } catch (WidgetException e) {
            System.out.println("Cannot add a widget of that size to the grid");
            return false;
        }
        widget.setVisible(true);
        widget.setRowIndex(coords.x);
        widget.setColIndex(coords.y);
        selectedWidgets.add(widget);
        // TODO: Maybe move the adding to the layout and then only go between the layout and the anchorpane?
        currentLayout.addWidget(widget);
        gridAnchorPane.getChildren().remove(0);
        summonerGrid.add(widget.getPane(), coords.x, coords.y, widget.getRowSpan(), widget.getColSpan());
//        currentLayout.loadOntoGridpane(summonerGrid, gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight());
        gridAnchorPane.getChildren().add(summonerGrid);
        return true;
    }

    // TODO: Actually update the grid to show the changes using the layout
    private void removeWidget(Widget widget) {
        widget.setVisible(false);
        if (selectedWidgets.contains(widget))
            selectedWidgets.remove(widget);
        ObservableList<Node> children = summonerGrid.getChildren();
        for (Node node : children) {
            if (node instanceof AnchorPane && widget.getPane().getId().equals(node.getId())) {
                summonerGrid.getChildren().remove(node);
                break;
            }
        }
        currentLayout.removeWidget(widget);
    }

    private Point getNextGridCoords(int rowSpan, int colSpan) throws WidgetException {
        ArrayList<Point> coords = new ArrayList<>();
        ArrayList<Pair<Point, Point>> lines = new ArrayList<>();
        for (Widget widget : selectedWidgets) {
            coords.add(new Point(widget.getRowIndex(), widget.getColIndex()));

            /* Key for adding the lines below
             * 0-------------0
             * |      2      |
             * | 1         3 |
             * |      4      |
             * 0-------------0
             */

            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex()),
                                 new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex())));     // 1
            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex()),
                                 new Point(widget.getRowIndex(), widget.getColIndex() + widget.getColSpan() - 1)));     // 2
            lines.add(new Pair<>(new Point(widget.getRowIndex(), widget.getColIndex() + widget.getColSpan() - 1),
                                 new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex() + widget.getColSpan() - 1)));   // 3
            lines.add(new Pair<>(new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex()),
                                 new Point(widget.getRowIndex() + widget.getRowSpan() - 1, widget.getColIndex() + widget.getColSpan() - 1)));   // 4

        }
        for (int i = 0; i < summonerGrid.getRowCount(); i++) {
            for (int j = 0; j < summonerGrid.getColumnCount(); j++) {
                Point homeIndex = new Point(i, j);
                if (coords.contains(homeIndex))
                    continue;
                if (rowSpan == 1 && colSpan == 1)
                    return homeIndex;
                Pair<Point, Point> line1 = new Pair<>(homeIndex, new Point(i + rowSpan - 1, j));
                Pair<Point, Point> line2 = new Pair<>(homeIndex, new Point(i, j + colSpan - 1));
                Pair<Point, Point> line3 = new Pair<>(new Point(i, j + colSpan - 1), new Point(i + rowSpan - 1, j + colSpan - 1));
                Pair<Point, Point> line4 = new Pair<>(new Point(i + rowSpan - 1, j), new Point(i + rowSpan - 1, j + colSpan - 1));
                boolean intersecting = false;
                for (Pair<Point, Point> line : lines) {
                    if (doIntersect(line.getKey(), line.getValue(), line1.getKey(), line1.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line2.getKey(), line2.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line3.getKey(), line3.getValue()) ||
                            doIntersect(line.getKey(), line.getValue(), line4.getKey(), line4.getValue())) {
                        intersecting = true;
                        break;
                    }
                }
                if (!intersecting) {
                    return homeIndex;
                }
            }
        }
        throw new WidgetException("No space available for new widget with row span: " + rowSpan + " and colspan: " + colSpan);
    }

    // Given three colinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    // To find orientation of ordered triplet (p, q, r)
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // colinear

        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    private void createStandardWidgets() {
        standardWidgets.add(new NameWidget());
        standardWidgets.add(new SummIconWidget());
        standardWidgets.add(new NameIconComboWidget());
    }

    private void createCustomWidgets() {

    }

    private void initGridPane() {
        currentLayout = getSavedLayout();
        summonerGrid = new GridPane();
        currentLayout.loadOntoGridpane(summonerGrid, gridAnchorPane.getPrefWidth(), gridAnchorPane.getPrefHeight());
    }

    private void addToGridPane(Widget widget) {

    }

    private void removeFromGridPane(Widget widget) {

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
