package MVC.Widgets;

public abstract class Widget {

    private int xSize;      // The number of gridboxes wide
    private int ySize;      // The number of gridboxes tall
    private boolean draggable;
    private boolean editEnabled;

    public boolean isDraggable() {
        return draggable;
    }

    public boolean isEditEnabled() {
        return editEnabled;
    }

    public void exit() {

        // TODO: Destroy the current widget and remove it from the layout

    }


}
