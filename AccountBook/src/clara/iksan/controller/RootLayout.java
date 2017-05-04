package clara.iksan.controller;

import clara.iksan.MainApp;
import clara.iksan.convert.CreateTables;
import clara.iksan.convert.ImportCsv;
import clara.iksan.convert.Tps2Csv;
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
        String dataRootPath = "/Users/1001235/MyDev/ws_github/ClaraIksan/AccountBook/data/";

        // remove previous csv files
        File dir = new File(dataRootPath + "csv");
        for (File file: dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        // convert tps to csv
        Tps2Csv conv = new Tps2Csv();
        conv.convertAll(dataRootPath);

        // import cvs
        CreateTables ct = new CreateTables();
        ct.createAllTables();
        ImportCsv imp = new ImportCsv(dataRootPath);
        imp.importAll();
    }

    @FXML
    private void handleAbout() {
        mainApp.showAboutDialog();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
