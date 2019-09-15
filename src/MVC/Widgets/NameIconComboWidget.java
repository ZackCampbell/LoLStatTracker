package MVC.Widgets;

public class NameIconComboWidget extends Widget {

    public NameIconComboWidget() {
        setName();
        setType();
        setRowSpan();
        setColSpan();
        this.getPane().getStylesheets().add(getClass().getResource("/stylesheets/WidgetStylesheet.css").toExternalForm());
        this.getPane().getStyleClass().addAll("widget", "widget-edit-disabled");
    }

    @Override
    public void setName() {
        this.name = "NameIconCombo";
        this.setListName("  NameIconCombo");
        this.getPane().setId("NameIconComboWidget");
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
        this.colSpan = 2;
    }
}
