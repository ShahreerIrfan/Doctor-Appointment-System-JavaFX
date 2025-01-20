package com.doctor.doctorappointment.model;

public class User {
    private int userId;  // Added userId field
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;

    // Constructor to initialize all fields
    public User(int userId, String username, String firstName, String lastName, String email, String password, String role) {
        this.userId = userId;  // Initialize userId
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getter for userId
    public int getUserId() {
        return userId;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
