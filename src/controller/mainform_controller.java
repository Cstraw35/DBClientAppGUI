package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainform_controller implements Initializable{

        Stage stage;
        Scene scene;
        Parent root;
        public void getUser(String userName){
            mainFormUserlbl.setText(userName);
        }

        @FXML
        private Button mainFormAddAppointBtn;

        @FXML
        private Button mainFormAddCustBtn;

        @FXML
        private RadioButton mainFormMonthRb;

        @FXML
        private Label mainFormUserlbl;

        @FXML
        private RadioButton mainFormWeekRb;

        @FXML
        private Button mainFormLogoutBtn;

        @FXML
        void addCustomer(ActionEvent event) throws IOException {
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/customer_form.fxml"));
            root = loader.load();
            scene = new Scene(root);
            customerform_controller customerFormController = loader.getController();
            customerFormController.getUser(mainFormUserlbl.getText());
            stage.setScene(scene);
            stage.show();

       }

        @FXML
        void logOut(ActionEvent event) throws IOException {
            stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login_form.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

        public void initialize(URL url, ResourceBundle rb) {

        }
        @FXML
        void addApointment(ActionEvent event) {

        }


}


