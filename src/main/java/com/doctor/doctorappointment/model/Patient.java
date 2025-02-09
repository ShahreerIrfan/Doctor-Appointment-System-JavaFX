package com.doctor.doctorappointment.model;

public class Patient {

    private int patientId;
    private String patientName;
    private String contactInfo;

    // Constructor
    public Patient(String patientName, String contactInfo) {
        this.patientName = patientName;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
