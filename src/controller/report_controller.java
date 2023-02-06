package controller;

import DAO.AppointmentsDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AppointmentContact;
import utilities.TimeConv;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class report_controller implements Initializable {
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
    private TableColumn<?, ?> appointmentEndClm;

    @FXML
    private TableColumn<?, ?> appointmentStartClm;

    @FXML
    private TableColumn<?, ?> appointmentTitleClm;

    @FXML
    private TableColumn<?, ?> appointmentTypeClm;

    @FXML
    private TableView<?> contactScheduleTbl;

    @FXML
    private ComboBox<?> contactSelectCB;

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
    private TableColumn<?, ?> totalAppointmentsClm;

    @FXML
    private TableView<?> totalAppointmentsTableMonth;

    @FXML
    private TableView<AppointmentContact> totalAppointmentsTableType;

    @FXML
    private TableColumn<?, ?> totalAppointmentsTypeClm;

    @FXML
    private TableColumn<?, ?> totalMonthClm;

    @FXML
    private TableColumn<AppointmentContact, String> totalTypeClm;

    @FXML
    private TableColumn<?, ?> userClm;

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void customerSelectedCBAction(ActionEvent event) {

    }


    public Set<String> getTypes(ObservableList<AppointmentContact> allAppointments){
        List<String> types = new ArrayList<>();

        for(int i = 0; i < allAppointments.size(); ++i){
           types.add(allAppointments.get(i).getType());
        }
        Set<String> typesHash= new HashSet<String>(types);
        return typesHash;

    }


    public void initialize(URL url, ResourceBundle rb) {
        //Setup Tables
        ObservableList<AppointmentContact> typeAppointments = FXCollections.observableArrayList();
        ObservableList<AppointmentContact> allAppointments = FXCollections.observableArrayList();



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

    }
}









