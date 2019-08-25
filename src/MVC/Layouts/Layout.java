package MVC.Layouts;

import MVC.Widgets.DummyWidget;
import MVC.Widgets.Widget;
import MVC.Widgets.WidgetException;
import Utils.Utils;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layout {

    private int numWidgets = 0;
    private LinkedList<Widget> widgets = new LinkedList<>();
    private int numCols;
    private int numRows;

    public Layout(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }


    public void addWidget(Widget widget) {
        if (!widgets.contains(widget)) {
            numWidgets++;
            widgets.add(widget);
        }
    }

    public boolean removeWidget(Widget widget) {
        if (widgets.contains(widget)) {
            numWidgets--;
            widgets.remove(widget);
            return true;
        }
        return false;               // Unsuccessful
    }

    public Widget getWidget(String widgetName) throws WidgetException {
        for (Widget widget : widgets) {
            if (widget.getName().equals(widgetName))
                return widget;
        }
        throw new WidgetException("Widget with name: " + widgetName + " not found in current layout");
    }

    public static void deleteLayout(String fileName) {
        // TODO: Delete the file with the input file name; check if that is the layout being used currently and switch to a default layout
    }

    public void saveLayout(String fileName) {
        // TODO: Save to an XML File with the input name and mark as the most recently saved
    }

    // @params Input a new gridpane and the width and height of the parent
    public GridPane loadOntoGridpane(GridPane gridPane, double width, double height) {
        ArrayList<Widget> addedWidgets = new ArrayList<>();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPrefSize(width, height);
        Utils.addGridConstraints(gridPane, numRows, numCols);

        for (Widget widget : widgets) {
            gridPane.add(widget.getPane(), widget.getRowIndex(), widget.getColIndex(), widget.getRowSpan(), widget.getColSpan());
            addedWidgets.add(widget);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                if (getNodeByRowColumnIndex(i, j, gridPane) == null) {
                    Point coords = null;
                    try {
                        coords = Utils.getNextGridCoords(gridPane, addedWidgets, 1, 1);
                    } catch (WidgetException e) {
                        System.out.println("Cannot add a widget of that size to the grid");
                    }
                    if (coords != null && coords.x == i && coords.y == j) {
                        Pane pane = new Pane();
                        pane.getStyleClass().add("empty-grid-cell");
                        gridPane.add(pane, i, j);
                        addedWidgets.add(new DummyWidget(coords.x, coords.y));
                    }
                }

            }
        }

        return gridPane;
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}
