package com.randalladams.scheduler.login;
/*
 * LoginController is responsible for controlling our login operations
 * @author Randall Adams
 * @version 1.0.0
 * @since 06/01/2020
 */

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.randalladams.scheduler.util.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class LoginController implements Initializable {

  private static LoginService loginService;
  private static final String nextFxmlScene = "/fxml/customers.fxml";
  private static final SceneManager sm = new SceneManager();
  // TODO: reference i18n key instead of hard coded string
  private static final String nextSceneTitle = "Contacts";

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  private Button loginButton;

  @FXML
  private Label timezoneLabel;

  /**
   * Initializer for login scene
   * @param url - the url (required for all initializers)
   * @param resourceBundle - the resource bundle for the scene
   */
  public void initialize(URL url, ResourceBundle resourceBundle) {
    loginService = new LoginService();
    String timezoneId = loginService.getTimezoneId();
    timezoneLabel.setText(timezoneId);
  }

  /**
   * Login method that triggers a service to check for valid login
   * credentials and then load subsequent default scene (contacts
   * shows an alert if unsuccessful
   */
  @FXML
  public void login() {

    Window owner = loginButton.getScene().getWindow();

    // TODO: i18n
    if (username.getText().isEmpty()) {
      SceneManager.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter your username");
      return;
    }

    // TODO: i18n
    if (password.getText().isEmpty()) {
      SceneManager.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter your password");
      return;
    }

    String usernameText = username.getText();
    String passwordText = password.getText();

    try {
      boolean isValidLogin = loginService.isValidLogin(usernameText, passwordText);

      // valid login path
      if (isValidLogin) {
        sm.setScene(nextFxmlScene, nextSceneTitle, 800, 500);
        owner.hide();
      } else { // if login fails we show an error
        SceneManager.showAlert(Alert.AlertType.ERROR, owner, "Login Failed!",
          "Incorrect credentials for username:  " + usernameText);
      }
    } catch (NoSuchAlgorithmException | SQLException | IOException e) {
      System.out.println("Error trying to login: " + e.getMessage());
    }
  }
}