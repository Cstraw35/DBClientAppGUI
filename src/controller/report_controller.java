package controller;

import DAO.AppointmentsDAOImp;
import DAO.ContactDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.AppointmentContact;
import model.Contact;
import model.Customer;
import utilities.LocaleInterface;
import utilities.TimeConv;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;

public class report_controller implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    /**
     * Get user from main form.
     * @param userName
     */
    public void getUser(String userName) {
        reportFormUserLbl.setText(userName);
    }

    @FXML
    private TableColumn<?, ?> appoinmentIDClm;

    @FXML
    private TableColumn<?, ?> appointmentDescriptionClm;

    @FXML
    private TableColumn<AppointmentContact, LocalDateTime> appointmentEndClm;

    @FXML
    private TableColumn<AppointmentContact, LocalDateTime> appointmentStartClm;

    @FXML
    private TableColumn<?, ?> appointmentTitleClm;

    @FXML
    private TableColumn<?, ?> appointmentTypeClm;

    @FXML
    private TableView<AppointmentContact> contactScheduleTbl;

    @FXML
    private ComboBox<String> contactSelectCB;

    @FXML
    private TableColumn<?, ?> customerIDClm;

    @FXML
    private TableColumn<?, ?> failedLoginAttemptsClm;

    @FXML
    private TableView<?> loginAttemptsTbl;

    @FXML
    private Label reportFormUserLbl;

    @FXML
    private TableColumn<?, ?> successfulLoginAttemptsClm;

    @FXML
    private TableColumn<AppointmentContact, ?> totalAppointmentsClm;

    @FXML
    private TableView<AppointmentContact> totalAppointmentsTableMonth;

    @FXML
    private TableView<AppointmentContact> totalAppointmentsTableType;

    @FXML
    private TableColumn<?, ?> totalAppointmentsTypeClm;

    @FXML
    private TableColumn<AppointmentContact, Month> totalMonthClm;

    @FXML
    private TableColumn<AppointmentContact, String> totalTypeClm;

    @FXML
    private TableColumn<?, ?> userClm;

    /**
     * Goes back to main form.
     * @param event
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_form.fxml"));
        root = loader.load();
        scene = new Scene(root);
        mainform_controller mainformController = loader.getController();
        mainformController.getUser(reportFormUserLbl.getText());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Uses lambda expression to filter by contact.
     * @param event
     * @throws Exception
     */
    @FXML
    void contactSelectedCBAction(ActionEvent event) throws Exception {
        System.out.println("Selected");
        ObservableList<AppointmentContact> allAppointments = FXCollections.observableArrayList();
        ObservableList<AppointmentContact> contactAppointments = FXCollections.observableArrayList();
        FilteredList<AppointmentContact> filteredAppointments = new FilteredList<>(allAppointments);
        allAppointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());

        filteredAppointments.setPredicate(appointment -> {
                String contactName = appointment.getContactName();
                return contactName.equals(contactSelectCB.getValue());
        });

        appoinmentIDClm.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionClm.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentTypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartClm.setCellValueFactory(new PropertyValueFactory<AppointmentContact, LocalDateTime>("localStart"));
        appointmentEndClm.setCellValueFactory(new PropertyValueFactory<AppointmentContact, LocalDateTime>("localEnd"));
        customerIDClm.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        contactScheduleTbl.setItems(filteredAppointments);


    }

    /**
     * Gets unique months to avoid duplicates in month report.
     * @param allAppointments
     * @return
     */
    public Set<Month> getUniqueMonths(ObservableList<AppointmentContact> allAppointments) {
        List<Month> months = new ArrayList<>();
        for(int i = 0; i < allAppointments.size(); ++i){
            months.add(allAppointments.get(i).getMonth());
        }
        Set<Month> monthsHash = new HashSet<Month>(months);
        return monthsHash;

    }


    /**
     * Initialize report tables.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        //Setup Tables
        ObservableList<AppointmentContact> typeAppointments = FXCollections.observableArrayList();
        ObservableList<AppointmentContact> allAppointments = FXCollections.observableArrayList();
        ObservableList<AppointmentContact> monthAppointments = FXCollections.observableArrayList();


        //Type Table
        totalTypeClm.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalAppointmentsTypeClm.setCellValueFactory(new PropertyValueFactory<>("typeCount"));
            try {
                allAppointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                typeAppointments = AppointmentsDAOImp.getAppointmentTypes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalAppointmentsTableType.setItems(typeAppointments);

        //Month Table
        Set<Month> months = new HashSet<>(getUniqueMonths(allAppointments));
        List<Month> monthsList = months.stream().toList();
        totalMonthClm.setCellValueFactory(new PropertyValueFactory<>("month"));
        totalAppointmentsClm.setCellValueFactory(new PropertyValueFactory<>("monthCount"));
        try {
            Boolean added = false;
            for(int j = 0; j < monthsList.size(); j++){
                added = false;
                System.out.println(j);
                for(int i = 0; i < allAppointments.size(); ++i){
                    if(monthsList.get(j) == allAppointments.get(i).getMonth() && added == false){
                        monthAppointments.add(allAppointments.get(i));
                        added = true;

                    }

                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        //Setup contact CB
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        try {
            allContacts = ContactDAOImp.getAllContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allContacts.size(); ++i) {
            contactSelectCB.getItems().add(allContacts.get(i).getContactName());
        }
        totalAppointmentsTableMonth.setItems(monthAppointments);
        totalAppointmentsTableMonth.sort();
    }
}









