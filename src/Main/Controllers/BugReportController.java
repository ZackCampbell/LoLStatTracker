package Main.Controllers;

import Main.NetworkClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BugReportController implements Initializable {

    @FXML private TextArea bugDescription;
    @FXML private TextArea bugEncounterInfo;

    private Popup popup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage stage = MasterController.getStage();
        popup = SummonerGUIController.getPopup();
        popup.setAnchorX(800.0);
        popup.setAnchorY(250.0);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);

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
