package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.model.User;
import com.doctor.doctorappointment.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCreationController {

    @FXML private TextField usernameField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private TextField phoneNumberField;

    @FXML
    private void goToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void saveUser(ActionEvent event) {
        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
        String phoneNumber = phoneNumberField.getText();

        // Validate inputs
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || role == null || phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Form Validation", "Please fill in all the fields!");
            return;
        }

        // Check if email already exists
        String selectQuery = "SELECT COUNT(*) FROM Users WHERE email = '" + email + "'";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                showAlert(Alert.AlertType.WARNING, "Email Already Exists", "This email is already associated with an existing account.");
                return;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to check email uniqueness.");
            return;
        }

        // Proceed to save the new user
        User user = new User(username, firstName, lastName, email, password, role, phoneNumber);
        String insertQuery = "INSERT INTO Users (username, first_name, last_name, email, password, role, phone_number) VALUES ('" +
                user.getUsername() + "', '" +
                user.getFirstName() + "', '" +
                user.getLastName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "', '" +
                user.getRole() + "', '" +
                user.getPhoneNumber() + "')";

        boolean success = DatabaseConnection.executeInsertQuery(insertQuery);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "User Created", "User created successfully!");
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
        } else {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to create user.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
