package MVC.Widgets;

import MVC.Widgets.Widget;
import javafx.scene.control.Label;

public class SummIconWidget extends Widget {

    public SummIconWidget() {
        setName();
        setType();
        setRowSpan();
        setColSpan();
    }

    @Override
    public void setName() {
        this.name = "SummonerIcon";
        setListName("  SummonerIcon");
    }

    @Override
    public void setType() {
        this.type = WIDGET_TYPE.STANDARD;
    }

    @Override
    void setRowSpan() {
        this.rowSpan = 1;
    }

    @Override
    void setColSpan() {
        this.colSpan = 1;
    }
}
