package MVC.Widgets;

public class NameWidget extends Widget {

    public NameWidget() {
        setType();
        setName();
        setRowSpan();
        setColSpan();
    }


    @Override
    public void setName() {
        this.name = "Name";
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
        this.rowSpan = 1;
    }
}
