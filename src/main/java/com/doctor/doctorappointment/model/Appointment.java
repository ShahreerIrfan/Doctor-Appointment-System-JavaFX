package com.doctor.doctorappointment.model;

public class Appointment {
    private int appointmentId;
    private String patientName;
    private String doctorName;
    private String appointmentDate;

    // Constructor
    public Appointment(int appointmentId, String patientName, String doctorName, String appointmentDate) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(int appointmentId, String patientName, String doctorName, String serviceName, String appointmentDate, String status) {
    }

    // Getters
    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}
