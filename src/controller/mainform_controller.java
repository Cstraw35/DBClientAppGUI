package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class mainform_controller implements Initializable{

        Stage stage;
        Parent scene;
        public void getUser(String userName){
            mainFormUserlbl.setText("User: " + userName);
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
        void addCustomer(ActionEvent event) {

       }

        @FXML
        void logOut(ActionEvent event) {

        }

        public void initialize(URL url, ResourceBundle rb) {

        }
        @FXML
        void addApointment(ActionEvent event) {

        }


}


