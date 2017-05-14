package clara.iksan.controller;

import clara.iksan.MainApp;
import clara.iksan.convert.CreateTables;
import clara.iksan.convert.ImportCsv;
import clara.iksan.convert.Tps2Csv;
import clara.iksan.manager.PrefManager;
import javafx.fxml.FXML;

import java.io.File;

/**
 * Created by Daeyoung Han on 2017. 5. 3..
 */
public class RootLayout {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleConfig() {
        mainApp.showConfigDialog();
    }

    @FXML
    private void handleReload() {
        PrefManager prefMan = new PrefManager();

        // remove previous csv files
        File dir = new File(prefMan.getDataPath() + "/csv");
        for (File file: dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        // convert tps to csv
        Tps2Csv conv = new Tps2Csv();
        conv.convertAll(prefMan.getTpsPath(), prefMan.getDataPath());

        // import cvs into database
        CreateTables ct = new CreateTables();
        ct.createAllTables();
        ImportCsv imp = new ImportCsv(prefMan.getDataPath());
        imp.importAll();

        // save lastUpdate into preferences
        prefMan.setLastUpdate();
        prefMan.store();
    }

    @FXML
    private void handleAbout() {
        mainApp.showAboutDialog();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleBackup() {

    }

    @FXML
    private void handleCategory() {
        mainApp.showCategoryDialog();
    }

    @FXML
    private void handleClassItem() {
        mainApp.showClassDialog();
    }
}
