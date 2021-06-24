package com.randalladams.scheduler.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
    stage.setTitle("Login");
    stage.setScene(new Scene(root, 800, 500));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}