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
import utilities.LoginFile;

import java.net.URL;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Login form class.
 */
public class loginform_controller implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;

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


    /**
     * Login button action script to check login and load main form if successful.
     * @param event
     * @throws Exception
     */
    @FXML
    void loginBtnAction(ActionEvent event) throws Exception {
        ResourceBundle RB = ResourceBundle.getBundle("Languages/ResourceBundle_RB", Locale.getDefault());
        String loginUser;
        String username = loginUserTxt.getText();
        String password = loginPasswordTxt.getText();
        User user = UserDAOImp.getUser(username);
        if (username == "" || password == "") {
            LoginFile.userLoginAttempt("No userName", ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "Fail");
            if(Locale.getDefault().getLanguage().equals("fr")){
                Alerts.errorAlert(RB.getString("Empty"),RB.getString("FillFields"));
            }
            else {
                Alerts.errorAlert("Fields Empty", "Please make sure to fill user and password fields");
            }
        }
        else {
            try {
                loginUser = user.getUserName();
                if (user.getPassword().equals(password)) {
                    LoginFile.userLoginAttempt(loginUser, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "Pass");
                    stage = (Stage)((Button) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_form.fxml"));
                    root = loader.load();
                    scene = new Scene(root);
                    mainform_controller mainFormController = loader.getController();
                    mainFormController.getUser(loginUser);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    LoginFile.userLoginAttempt(loginUser, ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "fail");
                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alerts.errorAlert(RB.getString("InvalidPassword"),RB.getString("WrongPassword"));

                    }
                    else {
                        Alerts.errorAlert("Wrong password", "Wrong password for user " + user.getUserName());
                    }
                }
            } catch (Exception e) {
                LoginFile.userLoginAttempt(loginUserTxt.getText(), ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC), "fail");
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alerts.errorAlert(RB.getString("InvalidUser"), RB.getString("UserNameExist"));
                } else {

                    Alerts.errorAlert("Invalid User", "username does not exist");
                }
            }
        }
    }


    /**
     * Initialize labels based on locale.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle RB = ResourceBundle.getBundle("Languages/ResourceBundle_RB", Locale.getDefault());
        loginZoneLbl.setText(String.valueOf(ZoneId.systemDefault()));
        if (Locale.getDefault().getLanguage().equals("fr")) {
            loginTitleLbl.setText(RB.getString("Login"));
            passwordLbl.setText(RB.getString("Password"));
            usernameLbl.setText(RB.getString("Username"));
            loginBtn.setText(RB.getString("Login"));
        }
    }


}











