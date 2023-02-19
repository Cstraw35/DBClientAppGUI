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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import utilities.Alerts;
import utilities.FormatChecks;
import utilities.TimeConv;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class userform_controller implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button backBtn;
    @FXML
    private Button clearSelectionBtn;
    @FXML
    private TextField userFormUserID;
    @FXML
    private TableView<User> usersFormTable;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button userAddBtn;
    @FXML
    private Button userDeleteBtn;
    @FXML
    private Label userFormUserLbl;
    @FXML
    private TableColumn<?, ?> userID;
    @FXML
    private TableColumn<?, ?> userName;
    @FXML
    private TextField userNameTxt;
    @FXML
    private TableColumn<?, ?> userPassword;

    /**
     * Gets username from previous form.
     *
     * @param userName
     */
    public void getUser(String userName) {
        userFormUserLbl.setText(userName);
    }

    /**
     * Add users to system.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void addUpdateBtn(ActionEvent event) throws Exception {
        String userName = userNameTxt.getText();
        String password = passwordTxt.getText();
        Date lastUpdate = new Date();
        String lastUpdatedBy = userFormUserLbl.getText();
        ObservableList<User> allUsers = UserDAOImp.getAllUsers();
        Date createDate = new Date();
        String createdBy = userFormUserLbl.getText();
        //Check text fields not empty first. Only check Id is not blank if customer already exists.
        if (userName == "" || password == "") {
            Alerts.errorAlert("Missing information", "Please make sure to fill all fields.");
        } else {
            Boolean nameCheck = false;
            for (int i = 0; i < allUsers.size(); ++i) {
                if (allUsers.get(i).getUserName().equals(userName)) {
                    nameCheck = true;
                }
            }
            if (nameCheck) {
                Alerts.errorAlert("Username invalid", "That username is already in use.");
            } else {
                UserDAOImp.addUser(userName, password, TimeConv.DateToString(createDate), createdBy, TimeConv.DateToString(lastUpdate), lastUpdatedBy);
                Alerts.actionAlert("User Added!", "User successfully added to system.");
                setupUserTable();
            }
        }
    }

    /**
     * Clear mouse selection from table.
     *
     * @param event
     */
    @FXML
    void clearSelection(ActionEvent event) {
        userNameTxt.clear();
        passwordTxt.clear();
        usersFormTable.getSelectionModel().clearSelection();

    }

    /**
     * User selected in table. Fills fields.
     *
     * @param event
     */
    @FXML
    void usersFormTableItemSelected(MouseEvent event) {
        User selectedUser = usersFormTable.getSelectionModel().getSelectedItem();
        userNameTxt.setText(selectedUser.getUserName());
        passwordTxt.setText(selectedUser.getPassword());

    }

    /**
     * Delete selected user. Cannot delete admin account.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void deleteUser(ActionEvent event) throws Exception {
        String userName = userNameTxt.getText();
        if (userName.equals("")) {
            Alerts.errorAlert("No user selected", "Please select a user first");
        } else if (userName.equals("admin")) {
            Alerts.errorAlert("Error", "Cannot delete admin user.");
        } else {
            UserDAOImp.deleteUser(userName);
            Alerts.actionAlert("User deleted", "User successfully deleted.");
            setupUserTable();
            userNameTxt.clear();
            passwordTxt.clear();
            usersFormTable.getSelectionModel().clearSelection();

        }


    }

    @FXML
    void formMouseClicked(MouseEvent event) {

    }

    /**
     * Return to the main form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void returnPage(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_form.fxml"));
        root = loader.load();
        scene = new Scene(root);
        mainform_controller mainformController = loader.getController();
        mainformController.getUser(userFormUserLbl.getText());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Sets up columns with all user values.
     *
     * @throws Exception
     */
    public void setupUserTable() throws Exception {
        ObservableList<User> allUsers = UserDAOImp.getAllUsers();
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));

        usersFormTable.setItems(allUsers);

    }

    /**
     * Sets up the user table.
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setupUserTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

