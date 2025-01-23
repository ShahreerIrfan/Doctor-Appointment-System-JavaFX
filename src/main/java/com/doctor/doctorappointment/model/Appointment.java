package com.doctor.doctorappointment.model;

public class Appointment {
    private int appointmentId;
    private String patientName;
    private String doctorName;
    private String serviceName;
    private String appointmentDate;
    private String status;

    // Constructor with all attributes
    public Appointment(int appointmentId, String patientName, String doctorName, String serviceName, String appointmentDate, String status) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceName = serviceName;
        this.appointmentDate = appointmentDate;
        this.status = status;
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

    public String getServiceName() {
        return serviceName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getStatus() {
        return status;
    }
}
