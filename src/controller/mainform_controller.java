package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    private TableColumn<?, ?> appIdClm;

    @FXML
    private TableView<?> appoinmentTbl;

    @FXML
    private ComboBox<?> contactCB;

    @FXML
    private TableColumn<?, ?> contactClm;

    @FXML
    private ComboBox<?> customerCB;

    @FXML
    private TableColumn<?, ?> customerIdClm;

    @FXML
    private TableColumn<?, ?> descriptionClm;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TableColumn<?, ?> endDateClm;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableColumn<?, ?> locationClm;

    @FXML
    private TextField locationTxt;

    @FXML
    private Button mainFormAddCustBtn;

    @FXML
    private Button mainFormLogoutBtn;

    @FXML
    private RadioButton mainFormMonthRb;

    @FXML
    private Label mainFormUserlbl;

    @FXML
    private RadioButton mainFormWeekRb;

    @FXML
    private TableColumn<?, ?> startDateClm;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<?, ?> titleClm;

    @FXML
    private TextField titleTxt;

    @FXML
    private TableColumn<?, ?> typeClm;

    @FXML
    private TableColumn<?, ?> userIdClm;



    @FXML
    void contactComboBoxClicked(ActionEvent event) {

    }

    @FXML
    void customerComboBoxClicked(ActionEvent event) {

    }

    @FXML
    void endDatePickerClicked(ActionEvent event) {

    }


    @FXML
    void endTimeHourClicked(ActionEvent event) {

    }

    @FXML
    void endTimeMinuteClicked(ActionEvent event) {

    }


    @FXML
    void rowSelection(MouseEvent event) {

    }

    @FXML
    void startDatePickerClicked(ActionEvent event) {

    }

    @FXML
    void startTimeHourClicked(ActionEvent event) {

    }

    @FXML
    void startTimeMinuteClicked(ActionEvent event) {

    }


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
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }



}


