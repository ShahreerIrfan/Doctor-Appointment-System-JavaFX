package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class AddAppointmentController {

    // Input fields
    @FXML private ComboBox<String> patientComboBox;
    @FXML private ComboBox<String> doctorComboBox;
    @FXML private ComboBox<String> serviceComboBox;
    @FXML private DatePicker appointmentDatePicker;
    @FXML private Button saveAppointmentBtn;
    @FXML private Button cancelBtn;

    // Reference to the parent controller
    private ManageAppointmentsController manageAppointmentsController;

    // Method to set the reference to the parent controller
    public void setManageAppointmentsController(ManageAppointmentsController controller) {
        this.manageAppointmentsController = controller;
    }

    @FXML
    public void initialize() {
        loadComboBoxData();

        saveAppointmentBtn.setOnAction(event -> handleSubmitAppointment());
        cancelBtn.setOnAction(event -> handleCancel());
    }

    private void loadComboBoxData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();

            // Fetch patients
            ResultSet patients = stmt.executeQuery("SELECT patient_name FROM Patientsd");
            while (patients.next()) {
                patientComboBox.getItems().add(patients.getString("patient_name"));
            }

            // Fetch doctors
            ResultSet doctors = stmt.executeQuery("SELECT doctor_name FROM Doctorsd");
            while (doctors.next()) {
                doctorComboBox.getItems().add(doctors.getString("doctor_name"));
            }

            // Fetch services
            ResultSet services = stmt.executeQuery("SELECT service_name FROM Servicesd");
            while (services.next()) {
                serviceComboBox.getItems().add(services.getString("service_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubmitAppointment() {
        String patientName = patientComboBox.getValue();
        String doctorName = doctorComboBox.getValue();
        String serviceName = serviceComboBox.getValue();
        String appointmentDate = appointmentDatePicker.getValue().toString();

        if (patientName != null && doctorName != null && serviceName != null && appointmentDate != null) {
            int patientId = getPatientIdByName(patientName);
            int doctorId = getDoctorIdByName(doctorName);
            int serviceId = getServiceIdByName(serviceName);

            if (patientId != -1 && doctorId != -1 && serviceId != -1) {
                String query = "INSERT INTO Appointmentsd (patient_id, doctor_id, service_id, appointment_date, status) " +
                        "VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query)) {
                    pst.setInt(1, patientId);
                    pst.setInt(2, doctorId);
                    pst.setInt(3, serviceId);
                    pst.setString(4, appointmentDate);
                    pst.setString(5, "Scheduled");

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Appointment added successfully!");
                        Stage stage = (Stage) saveAppointmentBtn.getScene().getWindow();
                        stage.close();
                        manageAppointmentsController.loadAppointments();
                    } else {
                        System.out.println("Error adding appointment.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid IDs for patient, doctor, or service.");
            }
        } else {
            System.out.println("Please fill in all fields.");
        }
    }

    private int getPatientIdByName(String patientName) {
        return getIdByName(patientName, "Patientsd", "patient_name", "patient_id");
    }

    private int getDoctorIdByName(String doctorName) {
        return getIdByName(doctorName, "Doctorsd", "doctor_name", "doctor_id");
    }

    private int getServiceIdByName(String serviceName) {
        return getIdByName(serviceName, "Servicesd", "service_name", "service_id");
    }

    private int getIdByName(String name, String tableName, String nameColumn, String idColumn) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT " + idColumn + " FROM " + tableName + " WHERE " + nameColumn + " = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, name);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(idColumn);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // ID not found
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
