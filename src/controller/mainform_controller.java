package controller;

import DAO.*;
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
import model.*;
import utilities.Alerts;
import utilities.FormatChecks;
import utilities.TimeConv;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
    private TextField appointmentIdTxt;

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
    private TextField typeTxt;

    @FXML
    private TableColumn<?, ?> typeClm;

    @FXML
    private TableColumn<?, ?> userIdClm;


    @FXML
    void addAppointment(ActionEvent event) throws Exception {
        String appointmentId = appointmentIdTxt.getText();
        if(appointmentId.equals("")){
            ZonedDateTime createDate = ZonedDateTime.now();
            String createdBy = mainFormUserlbl.getText();
            ZonedDateTime lastUpdate = ZonedDateTime.now();
            String last_updated_by = mainFormUserlbl.getText();

            if (titleTxt.equals("") || descriptionTxt.equals("") || locationTxt.equals("") || typeTxt.equals("")
            || contactCB.getValue() == null || startDatePicker.getValue() == null || starttimehour.getValue() == null ||
            starttimeminute.getValue() == null || endDatePicker.getValue() == null || endtimehour.getValue() == null ||
            endtimeminute.getValue() == null || customerCB.getValue() == null){
                Alerts.errorAlert("Not all fields filled", "Please make sure to fill all fields and dropdowns.");
        }
            else{
                ObservableList<Appointment> allAppointments = AppointmentsDAOImp.getAllAppointments();
                //Get Customer ID and User ID
                Customer customer = CustomerDAOImp.getCustomer(customerCB.getValue());
                User user = UserDAOImp.getUser(mainFormUserlbl.getText());
                Contact contact = ContactDAOImp.getContact(contactCB.getValue());
                //Get start times and also convert so it's ready for database
                LocalDate startDate = startDatePicker.getValue();
                String startHour = starttimehour.getValue();
                String startMinute = starttimeminute.getValue();
                LocalDateTime startLdt = LocalDateTime.of(startDate.getYear(), startDate.getMonth(),
                        startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
                ZonedDateTime startLocZdt = ZonedDateTime.of(startLdt, ZoneId.systemDefault());
                ZonedDateTime startEstZdt = startLocZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime startUtcZdt = startLocZdt.withZoneSameInstant(ZoneOffset.UTC);

                //Get end times and also convert so it's ready for database
                LocalDate endDate = endDatePicker.getValue();
                String endHour = endtimehour.getValue();
                String endMinute = endtimeminute.getValue();
                LocalDateTime endLdt = LocalDateTime.of(endDate.getYear(), endDate.getMonth(),
                        endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
                ZonedDateTime endLocZdt = ZonedDateTime.of(endLdt, ZoneId.systemDefault());
                ZonedDateTime endEstZdt = endLocZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime endUtcZdt = endLocZdt.withZoneSameInstant(ZoneOffset.UTC);

                //Check if appoinment is same time or between hours of another appoinment.
                Boolean appointmentFlag = false;
                for(int i = 0; i < allAppointments.size(); i++){
                    if((!startUtcZdt.isBefore(allAppointments.get(i).getStart())) && (!startUtcZdt.isAfter(allAppointments.get(i).getEnd()))){
                        appointmentFlag = true;
                    }
                    else if((!endUtcZdt.isBefore(allAppointments.get(i).getStart()))&& (!endUtcZdt.isAfter(allAppointments.get(i).getEnd()))){
                        appointmentFlag = true;
                    }

                }

                if(endEstZdt.isBefore(startEstZdt)){
                    Alerts.errorAlert("End time wrong", "Please make sure to set end time to a time after start time.");
                }
                else if(startEstZdt.getHour() < 8 || startEstZdt.getHour() > 22 || endEstZdt.getHour() > 22 ||
                        endEstZdt.getDayOfMonth() > startEstZdt.getDayOfMonth() || startDate.getDayOfWeek() ==
                        DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                    Alerts.errorAlert("Outside working hours", "Appointment set outside of working hours" +
                            ", please only schedule between 8am-10pm EST");
                }
                else if(appointmentFlag){
                    Alerts.errorAlert("Conflicting appointment", "Time conflicts with other appointments. Please choose a different time.");
                }


                else {

                    AppointmentsDAOImp.addAppointment(titleTxt.getText(), descriptionTxt.getText(), locationTxt.getText(),
                            typeTxt.getText(), TimeConv.zdtToString(startUtcZdt), TimeConv.zdtToString(endUtcZdt),
                            TimeConv.zdtToString(createDate), mainFormUserlbl.getText(), TimeConv.zdtToString(lastUpdate),
                            mainFormUserlbl.getText(), customer.getCustomerId(), user.getUserID(), contact.getContactId());
                            setupAppointmentTable();
                            Alerts.actionAlert("Appointment successfully added", "Appoinment successfully scheduled.");
                }


            }



    }


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
    void monthButtonClicked(ActionEvent event) {
        mainFormWeekRb.disarm();
        setupAppointmentTable();
        System.out.println("month" + mainFormMonthRb.isArmed());
        System.out.println("week" + mainFormWeekRb.isArmed());

    }

    @FXML
    void weekButtonClicked(ActionEvent event) {
        mainFormMonthRb.disarm();
        setupAppointmentTable();
        System.out.println("month" + mainFormMonthRb.isArmed());
        System.out.println("week" + mainFormWeekRb.isArmed());


    }


    /**
     * Button to go to the add customer form.
     * @param event
     * @throws IOException
     */
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

    /**
     * Button to logout and return to the login screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void logOut(ActionEvent event) throws IOException {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login_form.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Sets up the appointment table with filters and display in local date time.
     */
    public void setupAppointmentTable(){
        ObservableList<AppointmentContact> appointments = FXCollections.observableArrayList();
        ObservableList<AppointmentContact> appointmentsFiltered = FXCollections.observableArrayList();

        AppointmentContact appointment = new AppointmentContact();
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
            if(mainFormWeekRb.isArmed()){
                appointmentsFiltered.clear();
                System.out.println("Running");
                for(int i = 0; i < appointments.size(); i++){
                    Calendar checkCalendar = TimeConv.ldtToCalendar(appointments.get(i).getLocalStart());
                    Calendar currentCalendar = Calendar.getInstance();
                    currentCalendar.setTime(new Date());
                    if(checkCalendar.get(Calendar.WEEK_OF_YEAR) == currentCalendar.get(Calendar.WEEK_OF_YEAR)){
                        appointmentsFiltered.add(appointments.get(i));
                    }
                }
            }
            if(mainFormMonthRb.isArmed()) {
                appointmentsFiltered.clear();
                for (int i = 0; i < appointments.size(); i++) {
                    Calendar checkCalendar = TimeConv.ldtToCalendar(appointments.get(i).getLocalStart());
                    Calendar currentCalendar = Calendar.getInstance();
                    currentCalendar.setTime(new Date());
                    if (checkCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                        appointmentsFiltered.add(appointments.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        appointmentTbl.setItems(appointmentsFiltered);
    }

    /**
     * Sets up the combo boxes and calls the setup table function.
     * @param url
     * @param rb
     */
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
        mainFormMonthRb.arm();
        setupAppointmentTable();


    }



}


