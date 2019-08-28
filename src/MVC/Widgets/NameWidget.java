package MVC.Widgets;

public class NameWidget extends Widget {

    public NameWidget() {
        setType();
        setName();
        setRowSpan();
        setColSpan();
        this.getPane().getStylesheets().add(getClass().getResource("../Stylesheets/WidgetStylesheet.css").toExternalForm());
        this.getPane().getStyleClass().add("widget");
    }


    @Override
    public void setName() {
        this.name = "Name";
        this.setListName("  Name");
        this.getPane().setId("NameWidget");
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
