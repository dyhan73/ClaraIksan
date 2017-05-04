package clara.iksan;

import clara.iksan.controller.AboutDialog;
import clara.iksan.controller.RootLayout;
import clara.iksan.controller.SearchAccountBook;
import clara.iksan.controller.ConfigDialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang.SystemUtils;

import java.io.IOException;

/**
 * Created by Daeyoung Han on 2017. 4. 30..
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public MainApp() {
        // TODO : set start date of search

        // TODO : set bank account selector

        // get default bank statement data

    }

    public static void main(String[] argv) {
        System.out.println(SystemUtils.USER_DIR);
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("입출금 내역 조회");

        // set Application icon
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/images/Saint_Clara_Icon.jpg")));

        initRootLayout();

        showSearchAccountBook();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = loader.load();

            // show scene which include top layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // give MainApp access privilege to controller
            RootLayout controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchAccountBook() {
        try {
            // get main layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SearchAccountBook.fxml"));
            VBox searchAccountBook = loader.load();

            // set inner scene
            rootLayout.setCenter(searchAccountBook);

            // give MainApp access privilege to controller
            SearchAccountBook controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConfigDialog() {
        try {
            // get main layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ConfigDialog.fxml"));
            AnchorPane dialog = (AnchorPane) loader.load();

            // create dialog (modal)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            // set person on controller
            ConfigDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // show dialog and wait
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAboutDialog() {
        try {
            // get main layout from fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/AboutDialog.fxml"));
            AnchorPane dialog = (AnchorPane) loader.load();

            // create dialog (modal)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("About");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            // set stage
            AboutDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // show dialog and wait
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() { return primaryStage; }


}
