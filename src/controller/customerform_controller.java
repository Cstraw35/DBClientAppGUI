package controller;

import DAO.CountryDAOImp;
import DAO.CustomerDAOImp;
import DAO.FirstLevelDivisionDAOimp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class customerform_controller implements Initializable {
    public void getUser(String userName){
        customerFormUserLbl.setText(userName);
    }

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
    private TableColumn<?,?> tbName;

    @FXML
    private TableColumn<?, ?> tbPhone;

    @FXML
    private TableColumn<?, ?> tbPostalCode;

    /**
     * Sets up the country division combo box when selecting a country automatically and sets to the first index
     * instead of leaving the division blank.
     * @param event
     */
    @FXML
    void countryComboBoxSelect(ActionEvent event) {
        ObservableList<FirstLevelDivision> requiredDivision = FXCollections.observableArrayList();
        countryDivision.getItems().clear();
        try {
            Country currentCountry = CountryDAOImp.getCountry(countryComboBox.getValue());
            requiredDivision  = FirstLevelDivisionDAOimp.getCorrelatedFLD(currentCountry.getId());
            for(int i = 0; i < requiredDivision.size(); i++){
                countryDivision.getItems().add(requiredDivision.get(i).getDivision());
            }
            countryDivision.setValue(requiredDivision.get(0).getDivision());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void countryDivisionSelect(ActionEvent event) {


    }

    @FXML
    void addUpdateBtn(ActionEvent event) {
        int customerId;
        String customerName;
        String address;
        String postalCode;
        String phone;
        Date createDate;
        String createdBy;
        Date lastUpdate;
        String lastUpdatedBy;
        int divisionID;
        //Check text fields not empty first. Only check Id is not blank if customer already exists.


    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }

    /**
     * Sets up customer table for form and auto select country division Id values.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Setup lists for combo boxes and customer table.
        ObservableList<Customer> customers= FXCollections.observableArrayList();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision> requiredDivision = FXCollections.observableArrayList();
        try {
            allCountries = (CountryDAOImp.getAllCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < allCountries.size(); i++){
            countryComboBox.getItems().add(allCountries.get(i).getCountry());
        }
        countryComboBox.setValue(allCountries.get(1).getCountry());
        try {
            Country currentCountry = CountryDAOImp.getCountry(countryComboBox.getValue());
            requiredDivision  = FirstLevelDivisionDAOimp.getCorrelatedFLD(currentCountry.getId());
            for(int i = 0; i < requiredDivision.size(); i++){
                countryDivision.getItems().add(requiredDivision.get(i).getDivision());
            }
            countryDivision.setValue(requiredDivision.get(0).getDivision());
        } catch (Exception e) {
            e.printStackTrace();
        }



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
}
