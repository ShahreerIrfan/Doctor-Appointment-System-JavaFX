package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.model.User;
import com.doctor.doctorappointment.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserCreationController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private TextField phoneNumberField; // Ensure this is declared

    // Method to save user
    @FXML
    public void saveUser(ActionEvent event) {
        // Retrieve input data
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
        String phoneNumber = phoneNumberField.getText(); // Now properly initialized

        // Check if required fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || role == null || phoneNumber.isEmpty()) {
            showAlert(AlertType.WARNING, "Form Validation", "Please fill in all the fields!");
            return;
        }

        // Create User object and save it to database
        User user = new User(firstName, lastName, email, password, role, phoneNumber);
        String insertQuery = "INSERT INTO Users (first_name, last_name, email, password, role, phone_number) VALUES ('" +
                user.getFirstName() + "', '" +
                user.getLastName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "', '" +
                user.getRole() + "', '" +
                user.getPhoneNumber() + "')";

        // Execute insert query
        boolean success = DatabaseConnection.executeInsertQuery(insertQuery);

        if (success) {
            showAlert(AlertType.INFORMATION, "User Created", "User created successfully!");
        } else {
            showAlert(AlertType.ERROR, "Database Error", "Failed to create user.");
        }
    }

    // Method to show alert dialog
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
