package MVC.Widgets;

public class NameWidget extends Widget {

    public NameWidget() {
        setType();
        setName();
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
