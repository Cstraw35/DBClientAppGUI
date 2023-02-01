package controller;

import DAO.AppointmentsDAOImp;
import DAO.ContactDAOImp;
import DAO.CustomerDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.AppointmentContact;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class mainform_controller implements Initializable{
        Stage stage;
        Scene scene;
        Parent root;
        public void getUser(String userName){
            mainFormUserlbl.setText(userName);
        }
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> appIdClm;

    @FXML
    private TableView<AppointmentContact> appointmentTbl;

    @FXML
    private ComboBox<String> contactCB;

    @FXML
    private TableColumn<?, ?> contactClm;

    @FXML
    private ComboBox<String> customerCB;

    @FXML
    private TableColumn<?, ?> customerIdClm;

    @FXML
    private TableColumn<?, ?> descriptionClm;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TableColumn<AppointmentContact, LocalDateTime> endDateClm;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> endtimehour;

    @FXML
    private ComboBox<String> endtimeminute;

    @FXML
    private ComboBox<String> starttimehour;

    @FXML
    private ComboBox<String> starttimeminute;

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
    private TableColumn<AppointmentContact, LocalDateTime> startDateClm;

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
    void addAppointment(ActionEvent event) {

    }

    @FXML
    void deleteAppointment(ActionEvent event) {

    }


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

    public void setupAppointmentTable(){
        ObservableList<AppointmentContact> appointments = FXCollections.observableArrayList();
        appIdClm.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionClm.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationClm.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactClm.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateClm.setCellValueFactory(new PropertyValueFactory<AppointmentContact, LocalDateTime>("localStart"));
        endDateClm.setCellValueFactory(new PropertyValueFactory<AppointmentContact, LocalDateTime>("localEnd"));
        customerIdClm.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdClm.setCellValueFactory(new PropertyValueFactory<>("userId"));
        try {
            appointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());

        } catch (Exception e) {
            e.printStackTrace();
        }
        appointmentTbl.setItems(appointments);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Appointment test = AppointmentsDAOImp.getAppointment(1);
            System.out.println(test.getStart());
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            allContacts = ContactDAOImp.getAllContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < allContacts.size(); ++i){
            contactCB.getItems().add(allContacts.get(i).getContactName());
        }
        try {
            allCustomers = CustomerDAOImp.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < allCustomers.size(); ++i){
            customerCB.getItems().add(allCustomers.get(i).getCustomerName());
        }

        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        starttimehour.setItems(hours);
        starttimeminute.setItems(minutes);
        endtimehour.setItems(hours);
        endtimeminute.setItems(minutes);
        setupAppointmentTable();


    }



}


