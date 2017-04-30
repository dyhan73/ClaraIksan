package clara.iksan.account;

import clara.iksan.MainApp;
import clara.iksan.convert.CreateTables;
import clara.iksan.convert.ImportCsv;
import clara.iksan.convert.Tps2Csv;
import clara.iksan.model.BankStatement;
import clara.iksan.util.DbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;

/**
 * Created by dyhan on 2017. 4. 30..
 */
public class SearchAccountBookController {

    private MainApp mainApp;
    private ObservableList<BankStatement> bankStatementData = FXCollections.observableArrayList();

    @FXML
    private TextField searchTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ChoiceBox accountChoiceBox;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<BankStatement> bankStatementTable;
    @FXML
    private TableColumn<BankStatement, String> entryDateColumn;
    @FXML
    private TableColumn<BankStatement, String> accountColumn;
    @FXML
    private TableColumn<BankStatement, String> classNameColumn;
    @FXML
    private TableColumn<BankStatement, String> subClassNameColumn;
    @FXML
    private TableColumn<BankStatement, Integer> priceColumn;
    @FXML
    private TableColumn<BankStatement, String> remarkColumn;
    @FXML
    private TableColumn<BankStatement, String> noteColumn;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        bankStatementTable.setItems(bankStatementData);
    }

    public ObservableList<BankStatement> getBankStatementData() {
        return bankStatementData;
    }

    // call initialize after fxml loaded automatically
    @FXML
    private void initialize() {
        entryDateColumn.setCellValueFactory(cellData -> cellData.getValue().entryDateProperty());
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountProperty());
        classNameColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        subClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().subClassNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        remarkColumn.setCellValueFactory(cellData -> cellData.getValue().remarkProperty());
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

        //entryDateColumn.setStyle("-fx-alignment: CENTER-RIGHT;")

        // set listener
        // not needed yet
    }

    @FXML
    private void handleSearch() {
        String searchKeyword = searchTextField.getText();
        //String startDate = startDatePicker.getConverter().toString();
        //String account = accountChoiceBox.getSelectionModel().getSelectedItem().toString();

        bankStatementData.clear();

        DbManager dbMgr = new DbManager();
        dbMgr.open();
        bankStatementData = dbMgr.searchBankStatement(searchKeyword);
        dbMgr.close();

        bankStatementTable.setItems(bankStatementData);

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
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleAbout() {
        // TODO : create about dialog
    }
}
