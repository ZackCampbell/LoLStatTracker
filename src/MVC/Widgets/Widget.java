package MVC.Widgets;

import javafx.scene.layout.AnchorPane;

public abstract class Widget {

    private int rowIndex;      // The x coordinate of the widget
    private int colIndex;      // The y coordinate of the widget
    private int rowSpan;       // The number of rows taken up by this widget
    private int colSpan;       // The number of columns taken up by this widget
    private String name;
    private boolean draggable;
    private boolean editEnabled;
    private boolean visible;
    private AnchorPane root = new AnchorPane();

    public abstract String setName();

    public String getName() {
        return this.name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isEditEnabled() {
        return editEnabled;
    }

    public void setEditEnabled(boolean enabled) {
        this.editEnabled = enabled;
    }

    public void exit() {

        // TODO: Destroy the current widget and remove it from the layout

    }




}
