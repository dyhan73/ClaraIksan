package clara.iksan.controller;

import clara.iksan.manager.PrefManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by Daeyoung Han on 2017. 4. 30..
 */
public class ConfigDialog {

    @FXML
    private TextField tpsPath;
    @FXML
    private TextField dataPath;

    private Stage dialogStage;

    private PrefManager prefManager;

    /**
     * Constructor
     * Get default paths from Preferences
     */
    public ConfigDialog() {
        prefManager = new PrefManager();
        tpsPath.setText(prefManager.getTpsPath());
        dataPath.setText(prefManager.getDataPath());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleTpsChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(dialogStage);
        if (selectedDirectory == null) {
            tpsPath.setText("No Directory is selected");
        } else {
            tpsPath.setText(selectedDirectory.getAbsolutePath());
            prefManager.setTpsPath(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handleDataChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(dialogStage);
        if (selectedDirectory == null) {
            dataPath.setText("No Directory is selected");
        } else {
            dataPath.setText(selectedDirectory.getAbsolutePath());
            prefManager.setDataPath(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        prefManager.store();
        dialogStage.close();
    }

}
