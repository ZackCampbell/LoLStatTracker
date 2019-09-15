package MVC.Widgets;

import API.DTO.ItemDTO;
import Database.DatabaseManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import Utils.Utils;

public class ItemBuildsWidget extends Widget {

    private FlowPane flowPane = new FlowPane();

    public ItemBuildsWidget(List<ItemDTO> items) {
        setType();
        setName();
        setRowSpan();
        setColSpan();
        this.getPane().getStylesheets().add(getClass().getResource("/stylesheets/WidgetStylesheet.css").toExternalForm());
        this.getPane().getStyleClass().addAll("widget", "widget-edit-disabled", "item-builds-widget");

        flowPane.setId("ItemBuildFlowPane");
        flowPane.getStyleClass().add("item-flow-pane");
        flowPane.setVgap(3);
        flowPane.setHgap(3);
        if (items == null) {
            return;
        }
        for (ItemDTO item : items) {
            Pane itemPane = new Pane();
            Label itemLabel = new Label();
            itemLabel.setText("");
            String url =
                    null;
            try {
                url = new File(Utils.getRelativePath() + "/lib/DataDragon/" + DatabaseManager.latestDDVersion + "/img/item/" + item.getImage().getFull()).toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            itemLabel.setGraphic(new ImageView(url));
            itemLabel.setTextAlignment(TextAlignment.CENTER);
            itemLabel.setAlignment(Pos.CENTER);
            itemPane.getChildren().add(itemLabel);
            flowPane.getChildren().add(itemPane);
        }
        this.getPane().getChildren().add(flowPane);
    }

    @Override
    void setName() {
        this.name = "ItemBuildsWidget";
        this.setListName("  ItemBuildsWidget");
        this.getPane().setId("ItemBuildsWidget");
    }

    @Override
    void setType() {
        this.type = WIDGET_TYPE.STANDARD;
    }

    @Override
    void setRowSpan() {
        this.rowSpan = 6;
    }

    @Override
    void setColSpan() {
        this.colSpan = 1;
    }
}
