package MVC.Layouts;

import MVC.Widgets.Widget;
import MVC.Widgets.WidgetException;
import Utils.Utils;
import javafx.scene.layout.GridPane;

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
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPrefSize(width, height);
        Utils.addGridConstraints(gridPane, numRows, numCols);
        // Maybe for adding borders below for edit mode... not sure yet
//        for (int i = 0; i < numCols; i++) {
//            for (int j = 0; j < numRows; j++) {
//                Pane pane = new Pane();
//                pane.getStyleClass().add("empty-grid-cell");
//                gridPane.add(pane, i, j);
//            }
//        }




        return gridPane;
    }

}
