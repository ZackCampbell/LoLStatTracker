package MVC.Widgets;

import javafx.scene.control.ListCell;

public class WidgetCell extends ListCell<Widget> {

    @Override
    public void updateItem(Widget item, boolean empty) {
        super.updateItem(item, empty);

        String name = null;
        if (item == null || empty) {}
        else {
            name = item.getListName();
        }
        this.setText(name);
        setGraphic(null);
    }

}
