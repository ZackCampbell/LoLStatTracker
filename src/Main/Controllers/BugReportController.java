package Main.Controllers;

import Main.NetworkClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BugReportController extends MasterController implements Initializable {

    @FXML private TextArea bugDescription;
    @FXML private TextArea bugEncounterInfo;

    private Popup popup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popup = SummonerGUIController.getPopup();
//        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (popup.isShowing()) {
//                    popup.hide();
//                }
//                MasterController.getStage().setOpacity(1);
//                MasterController.getStage().removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
//            }
//        };
//        MasterController.getStage().addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

    }

    @FXML
    private void sendBugReport(ActionEvent event) {
        String description = bugDescription.getText();
        String howEncountered = bugEncounterInfo.getText();

        NetworkClient client = NetworkClient.getInstance();
        client.sendBugReport("bugReport", description, howEncountered);
        // TODO: Send the bug report to the server for processing and forwarding

        popup.hide();

    }

}
