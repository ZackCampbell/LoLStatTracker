package MVC.Widgets;

import MVC.Widgets.Widget;
import javafx.scene.control.Label;

public class SummIconWidget extends Widget {

    public SummIconWidget() {
        setName();
        setType();
        setRowSpan();
        setColSpan();
        this.getPane().getStylesheets().add(getClass().getResource("../Stylesheets/WidgetStylesheet.css").toExternalForm());
        this.getPane().getStyleClass().addAll("widget", "widget-edit-disabled");
    }

    @Override
    public void setName() {
        this.name = "SummonerIcon";
        setListName("  SummonerIcon");
        this.getPane().setId("SummonerIconWidget");
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
