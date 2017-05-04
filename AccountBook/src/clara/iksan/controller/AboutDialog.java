package clara.iksan.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Daeyoung Han on 2017. 5. 3..
 */
public class AboutDialog {

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleExit() {
        dialogStage.close();
    }

}
