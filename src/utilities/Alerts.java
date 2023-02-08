package utilities;

import javafx.scene.control.Alert;

/**
 * Class for setting up alerts through the application.
 */
public class Alerts {
    /**
     * Sets up an error alert so there is no repeating the sets and show.
     * @param title
     * @param info
     */
    public static void errorAlert(String title, String info) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setContentText(info);
        errorAlert.showAndWait();

    }

    /**
     * Sets the action/information alert so no need to repeat the sets and show.
     * @param title
     * @param info
     */
    public static void actionAlert(String title, String info) {
        Alert actionAlert = new Alert(Alert.AlertType.INFORMATION);
        actionAlert.setTitle(title);
        actionAlert.setContentText(info);
        actionAlert.showAndWait();
    }
}
