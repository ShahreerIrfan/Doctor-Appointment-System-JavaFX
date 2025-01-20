package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {


    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    @FXML
    private void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Form Validation", "Please fill in both fields!");
            return;
        }

        // SQL query to check if the username and password are correct
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the username and password parameters
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Successful login
                showAlert(Alert.AlertType.INFORMATION, "Login Success", "You have logged in successfully!");

                // Get the user's role (for role-based navigation)
                String role = resultSet.getString("role");

                // Redirect to the main application screen (or dashboard)
                FXMLLoader fxmlLoader = null;

                // Load dashboard based on the user role
                switch (role) {
                    case "Admin":
                        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/adminDashboard.fxml"));
                        break;
                    case "Doctor":
                        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Doctor/doctorDashboard.fxml"));
                        break;
                    case "Patient":
                        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Patient/patientDashboard.fxml"));
                        break;
                    default:
                        showAlert(Alert.AlertType.ERROR, "Role Error", "Invalid user role.");
                        return;
                }

                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setTitle(role + " Dashboard");
                stage.setScene(scene);
                stage.show();
            } else {
                // Login failed
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect username or password!");
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while trying to log in.");
        }
    }

    @FXML
    private void goToRegistrationPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 700);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle("Registration Page");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
