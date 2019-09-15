package MVC.Widgets;

public class DummyWidget extends Widget {

    public DummyWidget(int rowIndex, int colIndex) {
        this.setRowIndex(rowIndex);
        this.setColIndex(colIndex);
        setRowSpan();
        setColSpan();
        setName();
        this.getPane().getStylesheets().add(getClass().getResource("/stylesheets/WidgetStylesheet.css").toExternalForm());
    }

    @Override
    public void setEditEnabled(boolean enabled) {
        if (enabled) {
            if (this.getPane().getStyleClass().contains("dummy-widget-edit-disabled"))
                this.getPane().getStyleClass().removeAll("dummy-widget-edit-disabled");
            this.getPane().getStyleClass().addAll("dummy-widget-edit-enabled");
        } else {
            if (this.getPane().getStyleClass().contains("dummy-widget-edit-enabled"))
                this.getPane().getStyleClass().removeAll("dummy-widget-edit-enabled");
            this.getPane().getStyleClass().addAll("dummy-widget-edit-disabled");
        }
    }

    @Override
    void setName() {
        this.name = "Dummy";
    }

    @Override
    void setType() {
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
