package clara.iksan.controller;

import clara.iksan.MainApp;
import clara.iksan.manager.DbManager;
import clara.iksan.model.BankStatement;
import clara.iksan.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by Daeyoung Han on 2017. 5. 14..
 */
public class CategoryDialog {

    private MainApp mainApp;
    private Stage dialogStage;
    private ObservableList<Category> categoryData = FXCollections.observableArrayList();

    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Integer> catNoColumn;
    @FXML
    private TableColumn<Category, String> catNameColumn;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public ObservableList<Category> getCategoryData() {
        return categoryData;
    }

    // call initialize after fxml loaded automatically
    @FXML
    private void initialize() {
        catNoColumn.setCellValueFactory(cellData -> cellData.getValue().noProperty().asObject());
        catNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        DbManager dbMgr = new DbManager();
        dbMgr.open();
        categoryData = dbMgr.getCategory();
        dbMgr.close();

        categoryTable.setItems(categoryData);
    }

    @FXML
    private void handleEdit() {

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
