package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    @FXML
    private Button viewAppointmentsBtn;

    @FXML
    private Button createAppointmentBtn;

    @FXML
    private Button manageDoctorsBtn;

    @FXML
    private Button viewPaymentsBtn;

    @FXML
    private Button manageSpecializationsBtn;

    @FXML
    private Button viewPatientAppointmentsBtn;

    @FXML
    private Button viewDashboardAnalyticsBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button createAppointmentButton;
    private User currentUser;


    public void initialize() {
        currentUser = getCurrentUser();

        if ("Doctor".equals(currentUser.getRole())) {

            viewAppointmentsBtn.setVisible(true);
            createAppointmentBtn.setVisible(true);

        }

        else if ("Admin".equals(currentUser.getRole())) {

            manageDoctorsBtn.setVisible(true);
            viewPaymentsBtn.setVisible(true);
            manageSpecializationsBtn.setVisible(true);
            viewPatientAppointmentsBtn.setVisible(true);
            viewDashboardAnalyticsBtn.setVisible(true);
        }


    }


    private User getCurrentUser() {

        return new User(1, "johndoe", "John", "Doe", "johndoe@example.com", "password", "Doctor", "5551234567");
    }





    @FXML
    private void viewAppointments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/appointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("View Appointments");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the appointments page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void createAppointment() {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Appointment/createAppointment.fxml"));
            Parent root = fxmlLoader.load();


            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manage Appointments");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void manageDoctors(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Doctor/manageDoctors.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Manage Doctors");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the manage doctors page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void viewPayments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/viewPayments.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("View Payments");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the payments page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void manageSpecializations(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/manageSpecializations.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Manage Specializations");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the manage specializations page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void viewPatientAppointments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/viewPatientAppointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("View Patient Appointments");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the patient appointments page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void viewDashboardAnalytics(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/viewDashboardAnalytics.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("View Analytics");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the analytics page.");
            e.printStackTrace();
        }
    }


    @FXML
    private void logout(ActionEvent event) {
        showAlert(Alert.AlertType.CONFIRMATION, "Log Out", "Are you sure you want to logout?");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the login page.");
            e.printStackTrace();
        }
    }


    private boolean showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return false;
    }
}
