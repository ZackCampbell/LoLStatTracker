package MVC.Widgets;

import MVC.Widgets.Widget;
import javafx.scene.control.Label;

public class SummIconWidget extends Widget {

    public SummIconWidget() {
        setName();
        setType();
    }

    @Override
    public String setName() {
        return null;
    }

    @Override
    public WIDGET_TYPE setType() {
        return WIDGET_TYPE.STANDARD;
    }
}
