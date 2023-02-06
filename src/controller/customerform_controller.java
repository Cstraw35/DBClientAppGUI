package controller;

import DAO.AppointmentsDAOImp;
import DAO.CountryDAOImp;
import DAO.CustomerDAOImp;
import DAO.FirstLevelDivisionDAOimp;
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
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import utilities.Alerts;
import utilities.FormatChecks;
import utilities.TimeConv;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller for customer form.
 */
public class customerform_controller implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private TextField customerFormCustomerID;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private ComboBox<String> countryDivision;
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
    private TableView<Customer> customersFormTable;
    @FXML
    private TableColumn<?, ?> tbAddress;
    @FXML
    private TableColumn<Customer, Date> tbCreateDate;
    @FXML
    private TableColumn<?, ?> tbCreatedBy;
    @FXML
    private TableColumn<?, ?> tbDivisionId;
    @FXML
    private TableColumn<?, ?> tbID;
    @FXML
    private TableColumn<Customer, Date> tbLastUpdate;
    @FXML
    private TableColumn<?, ?> tbLastUpdatedBy;
    @FXML
    private TableColumn<?, ?> tbName;
    @FXML
    private TableColumn<?, ?> tbPhone;
    @FXML
    private TableColumn<?, ?> tbPostalCode;
    @FXML
    private TextField customerFormPhoneTxt;

    public void getUser(String userName) {
        customerFormUserLbl.setText(userName);
    }

    /**
     * Sets up the country division combo box when selecting a country automatically and sets to the first index
     * instead of leaving the division blank.
     *
     * @param event
     */
    @FXML
    void countryComboBoxSelect(ActionEvent event) {
        ObservableList<FirstLevelDivision> requiredDivision = FXCollections.observableArrayList();
        countryDivision.getItems().clear();
        try {
            Country currentCountry = CountryDAOImp.getCountry(countryComboBox.getValue());
            requiredDivision = FirstLevelDivisionDAOimp.getCorrelatedFLD(currentCountry.getId());
            for (int i = 0; i < requiredDivision.size(); i++) {
                countryDivision.getItems().add(requiredDivision.get(i).getDivision());
            }
            countryDivision.setValue(requiredDivision.get(0).getDivision());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Clear selection if clicking in empty area on the form.
     *
     * @param event
     */
    @FXML
    void formMouseClicked(MouseEvent event) {
        customersFormTable.getSelectionModel().clearSelection();

    }

    /**
     * Used to clear selection when table value was initially selected.
     *
     * @param event
     */
    @FXML
    void clearSelection(ActionEvent event) {
        customerFormNameTxt.clear();
        customerFormAddressTxt.clear();
        customerFormPostalTxt.clear();
        customerFormPhoneTxt.clear();
        customerFormCustomerID.clear();
        setupCustomerTable();
        customersFormTable.getSelectionModel().clearSelection();

    }

    /**
     * Fill out customer fields based on row selected.
     *
     * @param event
     */
    @FXML
    void customerFormTableItemSelected(MouseEvent event) throws Exception {
        Customer selectedCustomer = customersFormTable.getSelectionModel().getSelectedItem();
        int divisionID = selectedCustomer.getDivisionID();
        System.out.println(divisionID);
        FirstLevelDivision selectedDivision = FirstLevelDivisionDAOimp.getDivision(divisionID);
        Country selectedCountry = CountryDAOImp.getCountry(selectedDivision.getCountryId());
        customerFormCustomerID.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerFormNameTxt.setText(selectedCustomer.getCustomerName());
        customerFormAddressTxt.setText(selectedCustomer.getAddress());
        customerFormPostalTxt.setText(selectedCustomer.getPostalCode());
        customerFormPhoneTxt.setText(selectedCustomer.getPhone());
        countryComboBox.setValue(selectedCountry.getCountry());
        countryDivision.setValue(selectedDivision.getDivision());


    }

    @FXML
    void countryDivisionSelect(ActionEvent event) {


    }

    /**
     * Adds customer or updates if ID already exists.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void addUpdateBtn(ActionEvent event) throws Exception {
        String customerId = customerFormCustomerID.getText();
        String customerName = customerFormNameTxt.getText();
        String address = customerFormAddressTxt.getText();
        String postalCode = customerFormPostalTxt.getText();
        String phone = customerFormPhoneTxt.getText();
        Date lastUpdate = new Date();
        String lastUpdatedBy = customerFormUserLbl.getText();
        FirstLevelDivision selectedDivision = FirstLevelDivisionDAOimp.getDivision(countryDivision.getValue());
        int divisionID = selectedDivision.getDivisionId();
        if (customerId.equals("")) {
            Date createDate = new Date();
            String createdBy = customerFormUserLbl.getText();
            //Check text fields not empty first. Only check Id is not blank if customer already exists.
            if (customerName == "" || address == "" || postalCode == "" || phone == "") {
                Alerts.errorAlert("Missing information", "Please make sure to fill all fields.");
            } else if (postalCode.length() != 5) {
                Alerts.errorAlert("Incorrect postal code format", "Make sure postal code is only 5 characters.");
            } else if ((FormatChecks.checkPhone(
                    phone, countryComboBox.getValue()) == true)) {
                CustomerDAOImp.addCustomer(customerName, address, postalCode, phone, TimeConv.DateToString(createDate), createdBy,
                        TimeConv.DateToString(lastUpdate), lastUpdatedBy, divisionID);
                Alerts.actionAlert("Customer Added!", "Customer successfully added to system.");
                customerFormNameTxt.clear();
                customerFormAddressTxt.clear();
                customerFormPostalTxt.clear();
                customerFormPhoneTxt.clear();
                setupCustomerTable();
            }
        } else {
            Customer selectedCustomer = CustomerDAOImp.getCustomer(Integer.parseInt(customerId));
            Date createDate = selectedCustomer.getCreateDate();
            String createdBy = selectedCustomer.getCreatedBy();

            if (customerName == "" || address == "" || postalCode == "" || phone == "") {
                Alerts.errorAlert("Missing information", "Please make sure to fill all fields.");
            } else if (postalCode.length() != 5) {
                Alerts.errorAlert("Incorrect postal code format", "Make sure postal code is only 5 characters.");
            } else if ((FormatChecks.checkPhone(
                    phone, countryComboBox.getValue()) == true)) {
                CustomerDAOImp.updateCustomer(Integer.parseInt(customerId), customerName, address, postalCode, phone, TimeConv.DateToString(createDate), createdBy,
                        TimeConv.DateToString(lastUpdate), lastUpdatedBy, divisionID);
                Alerts.actionAlert("Customer Added!", "Customer successfully updated.");
                customerFormNameTxt.clear();
                customerFormAddressTxt.clear();
                customerFormPostalTxt.clear();
                customerFormPhoneTxt.clear();
                customerFormCustomerID.clear();
                setupCustomerTable();
                customersFormTable.getSelectionModel().clearSelection();
            }


        }


    }

    /**
     * Delete customer from table after checking appointments.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void deleteCustomer(ActionEvent event) throws Exception {
        String customerId = customerFormCustomerID.getText();
        if (customerId.equals("")) {
            Alerts.errorAlert("No customer selected", "Please select a customer first");
        } else {
            Customer selectedCustomer = CustomerDAOImp.getCustomer(Integer.parseInt(customerId));
            ObservableList<Appointment> checkAppointments = AppointmentsDAOImp.getCustomerAppointments(selectedCustomer.getCustomerId());
            System.out.println(checkAppointments.size());
            if (checkAppointments.size() == 0) {
                CustomerDAOImp.deleteCustomer(selectedCustomer.getCustomerId());
                Alerts.actionAlert("Customer deleted", "Customer successfully deleted.");
                customerFormNameTxt.clear();
                customerFormAddressTxt.clear();
                customerFormPostalTxt.clear();
                customerFormPhoneTxt.clear();
                customerFormCustomerID.clear();
                setupCustomerTable();
                customersFormTable.getSelectionModel().clearSelection();
            } else {
                Alerts.errorAlert("Can't delete customer", "If you want to delete the customer, please delete customer appointments first.");
            }
        }


    }

    /**
     * Sets up the customer table for customer form.
     */
    public void setupCustomerTable() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        tbID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        tbPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tbCreateDate.setCellValueFactory(new PropertyValueFactory<Customer, Date>("createDate"));
        tbCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        tbLastUpdate.setCellValueFactory(new PropertyValueFactory<Customer, Date>("lastUpdate"));
        tbLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        tbDivisionId.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        try {
            customers.addAll(CustomerDAOImp.getAllCustomers());

        } catch (Exception e) {
            e.printStackTrace();
        }
        customersFormTable.setItems(customers);


    }

    /**
     * Go back to previous page.
     *
     * @param event
     */
    @FXML
    void returnPage(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_form.fxml"));
        root = loader.load();
        scene = new Scene(root);
        mainform_controller mainformController = loader.getController();
        mainformController.getUser(customerFormUserLbl.getText());
        stage.setScene(scene);
        stage.show();

    }


    /**
     * Sets up customer table for form and auto select country division Id values.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setup lists for combo boxes and customer table.
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision> requiredDivision = FXCollections.observableArrayList();
        try {
            allCountries = (CountryDAOImp.getAllCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allCountries.size(); i++) {
            countryComboBox.getItems().add(allCountries.get(i).getCountry());
        }
        countryComboBox.setValue(allCountries.get(1).getCountry());
        try {
            Country currentCountry = CountryDAOImp.getCountry(countryComboBox.getValue());
            requiredDivision = FirstLevelDivisionDAOimp.getCorrelatedFLD(currentCountry.getId());
            for (int i = 0; i < requiredDivision.size(); i++) {
                countryDivision.getItems().add(requiredDivision.get(i).getDivision());
            }
            countryDivision.setValue(requiredDivision.get(0).getDivision());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupCustomerTable();
    }
}
