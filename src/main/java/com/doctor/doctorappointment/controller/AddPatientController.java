package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.DatabaseConnection;
import com.doctor.doctorappointment.model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientController {

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField contactInfoField;

    @FXML
    private Button savePatientBtn;

    @FXML
    private Button cancelBtn;

    // Method to handle saving patient
    @FXML
    public void savePatient() {
        String patientName = patientNameField.getText();
        String contactInfo = contactInfoField.getText();

        if (patientName.isEmpty() || contactInfo.isEmpty()) {
            // Handle empty fields
            System.out.println("Please fill in all fields.");
            return;
        }

        // Create a new Patient object
        Patient patient = new Patient(patientName, contactInfo);

        // Insert the new patient into the database
        try {
            String query = "INSERT INTO Patientsd (patient_name, contact_info) VALUES (?, ?)";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            statement.setString(1, patient.getPatientName());
            statement.setString(2, patient.getContactInfo());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient added successfully!");
                closeWindow();
            } else {
                System.out.println("Failed to add patient.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the add patient window
    @FXML
    public void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) savePatientBtn.getScene().getWindow();
        stage.close();
    }
}
