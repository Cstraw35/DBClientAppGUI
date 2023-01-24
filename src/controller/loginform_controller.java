package controller;

import DAO.UserDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import utilities.Alerts;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Login form class.
 */
public class loginform_controller implements Initializable {
    Stage stage;
    Parent scene;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField loginPasswordTxt;

    @FXML
    private Text loginTitleLbl;

    @FXML
    private TextField loginUserTxt;

    @FXML
    private Label loginZoneLbl;

    @FXML
    private Text passwordLbl;

    @FXML
    private Text usernameLbl;

    @FXML
    void loginBtnAction(ActionEvent event) throws Exception {
        stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_form.fxml"));
        String loginUser;
        String username = loginUserTxt.getText();
        String password = loginPasswordTxt.getText();
        User user = UserDAOImp.getUser(username);


        if (username == "" || password == "") {
            Alerts.errorAlert("Fields Empty", "Please make sure to fill user and password fields");
        }
        else {
            try {
                loginUser = user.getUserName();
                if (user.getPassword().equals(password)) {
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            } catch (Exception e) {
                Alerts.errorAlert("Invalid User", "username does not exist");
            }
        }
        }
}






