package ClientApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Calls the main function to open the login form.
 */
public class Main extends Application {

    public Main() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        double SCALE_FACTOR = 0.8;
        ResourceBundle RB = ResourceBundle.getBundle("Languages/ResourceBundle_RB", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("../view/login_form.fxml"));
        if(Locale.getDefault().getLanguage().equals("fr")){
            primaryStage.setTitle(RB.getString("Consulty App"));
        }
        else {
            primaryStage.setTitle("Consulty App");
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
