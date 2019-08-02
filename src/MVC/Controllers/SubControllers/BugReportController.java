package MVC.Controllers.SubControllers;

import MVC.Controllers.MasterController;
import MVC.Controllers.SummonerGUIController;
import Main.NetworkClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class BugReportController extends MasterController implements Initializable {

    @FXML private TextArea bugDescription;
    @FXML private TextArea bugEncounterInfo;

    private Popup popup;
    private static Pane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popup = SummonerGUIController.getPopup();
    }

    @FXML
    private void sendBugReport(ActionEvent event) {
        String description = bugDescription.getText();
        String howEncountered = bugEncounterInfo.getText();

        NetworkClient client = NetworkClient.getInstance();
        client.sendBugReport("bugReport", description, howEncountered);

        popup.getContent().removeAll();

        // TODO: Add new "check mark" icon and text saying "Thank you!" to the popup

    }

    public static void setContent(Pane content) {
        contentPane = content;
    }

}
