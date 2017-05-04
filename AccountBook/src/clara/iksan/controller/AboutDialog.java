package clara.iksan.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by 1001235 on 2017. 5. 3..
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
