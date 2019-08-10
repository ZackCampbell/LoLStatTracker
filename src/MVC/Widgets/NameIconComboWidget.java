package MVC.Widgets;

import MVC.Widgets.Widget;

public class NameIconComboWidget extends Widget {

    public NameIconComboWidget() {
        setName();
        setType();
        setRowSpan();
        setColSpan();
    }

    @Override
    public void setName() {
        this.name = "NameIconCombo";
        this.setListName("  NameIconCombo");
    }

    @Override
    public void setType() {
        this.type = WIDGET_TYPE.STANDARD;
    }

    @Override
    void setRowSpan() {
        this.rowSpan = 2;
    }

    @Override
    void setColSpan() {
        this.colSpan = 1;
    }
}
