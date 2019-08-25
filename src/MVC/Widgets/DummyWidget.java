package MVC.Widgets;

public class DummyWidget extends Widget {

    public DummyWidget(int rowIndex, int colIndex) {
        this.setRowIndex(rowIndex);
        this.setColIndex(colIndex);
        setRowSpan();
        setColSpan();
        setName();
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
