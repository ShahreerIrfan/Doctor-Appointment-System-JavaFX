package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.DatabaseConnection;
import com.doctor.doctorappointment.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageAppointmentsController {

    // ObservableList to store appointments
    private ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();

    @FXML
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML
    private TableColumn<Appointment, String> patientColumn;
    @FXML
    private TableColumn<Appointment, String> doctorColumn;
    @FXML
    private TableColumn<Appointment, String> serviceColumn;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> statusColumn;

    @FXML
    public void addAppointment() {
        System.out.println("Add Appointment button clicked!");
    }

    @FXML
    public void editAppointment() {
        System.out.println("Edit Appointment button clicked!");
    }

    @FXML
    public void deleteAppointment() {
        System.out.println("Delete Appointment button clicked!");
    }

    @FXML
    public void goBack() {
        System.out.println("Back button clicked!");
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Bind the ObservableList to the TableView
        appointmentTableView.setItems(appointmentsList);

        // Load data into the list
        loadAppointments();
    }

    public void loadAppointments() {
        String query = "SELECT * FROM appointments";
        Statement[] statementHolder = new Statement[1];
        ResultSet resultSet = DatabaseConnection.executeSelectQuery(query, statementHolder);

        if (resultSet == null) {
            System.out.println("No data found or error occurred!");
            return;
        }

        try {
            appointmentsList.clear();
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String serviceName = resultSet.getString("service_name");
                String appointmentDate = resultSet.getString("appointment_date");
                String status = resultSet.getString("status");

                appointmentsList.add(new Appointment(appointmentId, patientName, doctorName, serviceName, appointmentDate, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statementHolder[0] != null) statementHolder[0].close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
