package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    private void goToRegistrationPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        // Get the stage from the event source
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle("Registration Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Get the stage from the event source
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }
}
