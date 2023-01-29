package controller;

import DAO.CustomerDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class customerform_controller implements Initializable {
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




    @FXML
    void addUpdateBtn(ActionEvent event) {

    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }
    ObservableList<Customer> customers= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
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
