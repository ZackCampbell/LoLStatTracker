package Main.Controllers.SubControllers;

import Main.Controllers.MasterController;
import Main.Controllers.SummonerGUIController;
import Main.NetworkClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackController extends MasterController implements Initializable {

    @FXML private TextArea feedback;

    private Popup popup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popup = SummonerGUIController.getPopup();
    }

    @FXML
    private void sendFeedbackReport(ActionEvent event) {
        String feedbackText = feedback.getText();
        NetworkClient client = NetworkClient.getInstance();
        client.sendMessage("feedback", feedbackText);

        popup.getContent().removeAll();

        // TODO: Add new "check mark" icon and text saying "Thank you!" to the popup

    }

}
