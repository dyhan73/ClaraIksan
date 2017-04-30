package clara.iksan;

import clara.iksan.account.SearchAccountBookController;
import clara.iksan.config.ConfigDialogController;
import clara.iksan.model.BankStatement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

/**
 * Created by dyhan on 2017. 4. 30..
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane searchAccountBook;

    public MainApp() {
        // TODO : set start date of search

        // TODO : set bank account selector

        // get default bank statement data

    }

    public static void main(String[] argv) {
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("입출금 내역 조회");

        // set Application icon
        this.primaryStage.getIcons().add(new Image("file:resources/images/Saint_Clara_Icon.jpg"));

        showSearchAccountBook();

    }

    private void showSearchAccountBook() {
        try {
            // get main layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("account/SearchAccountBook.fxml"));
            searchAccountBook = (BorderPane) loader.load();

            // show scene which include main layout
            Scene scene = new Scene(searchAccountBook);
            primaryStage.setScene(scene);

            // give MainApp access privilege to controller
            SearchAccountBookController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConfigDialog() {
        try {
            // get main layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("config/ConfigDialog.fxml"));
            AnchorPane dialog = (AnchorPane) loader.load();

            // create dialog (modal)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            // set person on controller
            ConfigDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // show dialog and wait
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() { return primaryStage; }


}
