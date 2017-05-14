package clara.iksan.controller;

import clara.iksan.MainApp;
import clara.iksan.manager.DbManager;
import clara.iksan.model.AccountGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Created by Daeyoung Han on 2017. 5. 14..
 */
public class AccountGroupDialog {

    private MainApp mainApp;
    private Stage dialogStage;
    private ObservableList<AccountGroup> accountGroupData = FXCollections.observableArrayList();

    @FXML
    private TableView<AccountGroup> accountGroupTable;
    @FXML
    private TableColumn<AccountGroup, Integer> grpNoColumn;
    @FXML
    private TableColumn<AccountGroup, String> grpNameColumn;
    @FXML
    private TableColumn<AccountGroup, String> catNameColumn;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public ObservableList<AccountGroup> getAccountGroupData() {
        return accountGroupData;
    }

    // call initialize after fxml loaded automatically
    @FXML
    private void initialize() {
        grpNoColumn.setCellValueFactory(cellData -> cellData.getValue().grpNoProperty().asObject());
        grpNameColumn.setCellValueFactory(cellData -> cellData.getValue().grpNameProperty());
        catNameColumn.setCellValueFactory(cellData -> cellData.getValue().catNameProperty());

        DbManager dbMgr = new DbManager();
        dbMgr.open();
        accountGroupData = dbMgr.getAccountGroup();
        dbMgr.close();

        accountGroupTable.setItems(accountGroupData);
    }

    @FXML
    private void handleAdd() {

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
