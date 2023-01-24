package utilities;

import javafx.scene.control.Alert;

/**
 * Class for setting up alerts through the application.
 */
public class Alerts {
    public static void errorAlert(String title, String info){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setContentText(info);
        errorAlert.showAndWait();

    }
}
