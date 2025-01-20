package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageDoctorsController {

    @FXML
    private TableView<User> doctorTableView;

    @FXML
    private TableColumn<User, Integer> doctorIdColumn;

    @FXML
    private TableColumn<User, String> doctorNameColumn;

    @FXML
    private TableColumn<User, String> doctorEmailColumn;

    @FXML
    private TableColumn<User, String> doctorPhoneColumn;

    @FXML
    private TableColumn<User, String> doctorRoleColumn;

    @FXML
    private Button addDoctorBtn;

    @FXML
    private Button editDoctorBtn;

    @FXML
    private Button deleteDoctorBtn;

    @FXML
    private Button backBtn;

    private Connection connection;

    public void initialize() {
        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare_db", "root", "password");  // Adjust with your DB credentials
            loadDoctorData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load doctors from the database and populate the table
    private void loadDoctorData() {
        List<User> doctorList = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE role = 'Doctor'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");

                User doctor = new User(id, firstName, lastName, email, null, "Doctor", phoneNumber);
                doctorList.add(doctor);
            }

            doctorTableView.getItems().setAll(doctorList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add doctor
    @FXML
    private void addDoctor(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/addDoctor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Add Doctor");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Edit doctor
    @FXML
    private void editDoctor(ActionEvent event) {
        User selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            // Open the edit doctor page
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/editDoctor.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setTitle("Edit Doctor");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Doctor Selected", "Please select a doctor to edit.");
        }
    }

    // Delete doctor
    @FXML
    private void deleteDoctor(ActionEvent event) {
        User selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            try {
                String query = "DELETE FROM Users WHERE user_id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, selectedDoctor.getUserId());
                    stmt.executeUpdate();
                    loadDoctorData();  // Reload the table after deletion
                    showAlert(Alert.AlertType.INFORMATION, "Doctor Deleted", "The selected doctor has been deleted.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Doctor Selected", "Please select a doctor to delete.");
        }
    }

    // Go back to the admin dashboard
    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/adminDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
