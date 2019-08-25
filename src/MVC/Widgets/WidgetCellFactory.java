package MVC.Widgets;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class WidgetCellFactory implements Callback<ListView<Widget>, ListCell<Widget>> {
    @Override
    public ListCell<Widget> call(ListView<Widget> widgetListView) {
        return new WidgetCell();
    }
}
