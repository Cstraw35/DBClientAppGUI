package ClientApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle RB = ResourceBundle.getBundle("Languages/ResourceBundle_RB", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("../view/login_form.fxml"));
        if(Locale.getDefault().getLanguage().equals("fr")){
            primaryStage.setTitle(RB.getString("Login"));
        }
        else {
            primaryStage.setTitle("Login");
        }
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
