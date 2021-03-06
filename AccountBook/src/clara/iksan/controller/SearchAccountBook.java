package clara.iksan.controller;

import clara.iksan.MainApp;
import clara.iksan.model.BankStatement;
import clara.iksan.manager.DbManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Daeyoung Han on 2017. 4. 30..
 */
public class SearchAccountBook {

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
        classNameColumn.setCellValueFactory(cellData -> cellData.getValue().groupNameProperty());
        subClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().groupDetailNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        remarkColumn.setCellValueFactory(cellData -> cellData.getValue().remarkProperty());
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

        DbManager dbMan = new DbManager();
        dbMan.open();
        accountChoiceBox.setItems(dbMan.getBankAccounts());
        dbMan.close();

        // set listener
//        accountChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
//                new ChangeListener<Number>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                        handleSearch();
//                    }
//                }
//        );
    }

    @FXML
    private void handleSearch() {
        String searchKeyword = searchTextField.getText();
        //String startDate = startDatePicker.getConverter().toString();

        String account = accountChoiceBox.getSelectionModel().getSelectedItem().toString();

        bankStatementData.clear();

        DbManager dbMgr = new DbManager();
        dbMgr.open();
        bankStatementData = dbMgr.searchBankStatement(searchKeyword, account);
        dbMgr.close();

        bankStatementTable.setItems(bankStatementData);

    }

}
