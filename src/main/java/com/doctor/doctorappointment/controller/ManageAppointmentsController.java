package com.doctor.doctorappointment.controller;

import com.doctor.doctorappointment.DatabaseConnection;
import com.doctor.doctorappointment.HelloApplication;
import com.doctor.doctorappointment.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageAppointmentsController {

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Appointment/addAppointment.fxml"));
            Scene addAppointmentScene = new Scene(fxmlLoader.load(), 400, 300);

            Stage addAppointmentStage = new Stage();
            addAppointmentStage.setTitle("Add Appointment");
            addAppointmentStage.setScene(addAppointmentScene);
            addAppointmentStage.show();

            AddAppointmentController addAppointmentController = fxmlLoader.getController();
            addAppointmentController.setManageAppointmentsController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointmentTableView.setItems(appointmentsList);
        loadAppointments();
    }

    public void loadAppointments() {
        String query = "SELECT a.appointment_id, CONCAT(p.patient_name) AS patient_name, " +
                "d.doctor_name, s.service_name, a.appointment_date, a.status " +
                "FROM appointmentsd a " +
                "JOIN Patientsd p ON a.patient_id = p.patient_id " +
                "JOIN Doctorsd d ON a.doctor_id = d.doctor_id " +
                "JOIN Servicesd s ON a.service_id = s.service_id";
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

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
