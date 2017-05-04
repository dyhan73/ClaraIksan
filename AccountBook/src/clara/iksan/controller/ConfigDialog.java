package clara.iksan.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by 1001235 on 2017. 4. 30..
 */
public class ConfigDialog {

    @FXML
    private TextField dataPath;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(dialogStage);
        if (selectedDirectory == null) {
            dataPath.setText("No Directory selected");
        } else {
            dataPath.setText(selectedDirectory.getAbsolutePath());
        }

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {

        dialogStage.close();
    }

}
