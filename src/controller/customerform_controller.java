package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class customerform_controller {
    public void getUser(String userName){
        customerFormUserLbl.setText(userName);
    }
    @FXML
    private Button customerFormAddBtn;

    @FXML
    private TextField customerFormAddressTxt;

    @FXML
    private Button customerFormDeleteBtn;

    @FXML
    private TextField customerFormNameTxt;

    @FXML
    private TextField customerFormPostalTxt;

    @FXML
    private Label customerFormUserLbl;

    @FXML
    void addUpdateBtn(ActionEvent event) {

    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }
}
