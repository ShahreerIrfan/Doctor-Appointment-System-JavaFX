package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private User currentUser;

    // Initialize the dashboard based on user role
    public void initialize() {
        currentUser = getCurrentUser();  // Fetch the current user details (can be stored globally)

        // Set visibility based on the role
        if ("Doctor".equals(currentUser.getRole())) {
            // Show only the doctor-related buttons
            viewAppointmentsBtn.setVisible(true);
            createAppointmentBtn.setVisible(true);
            hideAdminButtons();
        } else if ("Admin".equals(currentUser.getRole())) {
            // Show all admin-related buttons
            manageDoctorsBtn.setVisible(true);
            viewPaymentsBtn.setVisible(true);
            manageSpecializationsBtn.setVisible(true);
            viewPatientAppointmentsBtn.setVisible(true);
            viewDashboardAnalyticsBtn.setVisible(true);
        }
    }

    // Method to retrieve current user (example, you can modify this to fetch the logged-in user)
    private User getCurrentUser() {
        // Here you would retrieve the current logged-in user from your session or database
        // This is just a mock-up user for the example
        return new User(1, "johndoe", "John", "Doe", "johndoe@example.com", "password", "Doctor", "5551234567");
    }


    private void hideAdminButtons() {
        manageDoctorsBtn.setVisible(false);
        viewPaymentsBtn.setVisible(false);
        manageSpecializationsBtn.setVisible(false);
        viewPatientAppointmentsBtn.setVisible(false);
        viewDashboardAnalyticsBtn.setVisible(false);
    }

    // Handle the action for viewing appointments
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

    // Handle the action for creating appointments
    @FXML
    private void createAppointment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Doctor/createAppointment.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Create Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the create appointment page.");
            e.printStackTrace();
        }
    }

    // Handle the action for managing doctors (Admin-specific)
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


    // Handle the action for viewing payments (Admin-specific)
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

    // Handle the action for managing specializations (Admin-specific)
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

    // Handle the action for viewing patient appointments (Admin-specific)
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

    // Handle the action for viewing dashboard analytics (Admin-specific)
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

    // Log out action
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

    // Show alerts for errors or successful actions
    private boolean showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return false;
    }
}
