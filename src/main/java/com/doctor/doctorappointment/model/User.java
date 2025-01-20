package com.doctor.doctorappointment.model;

import javafx.beans.property.*;

public class User {
    private final IntegerProperty userId;
    private final StringProperty username;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty role;
    private final StringProperty phoneNumber;

    // Constructor to initialize all fields using JavaFX properties
    public User(int userId, String username, String firstName, String lastName, String email, String password, String role, String phoneNumber) {
        this.userId = new SimpleIntegerProperty(userId);
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    // Constructor with fewer parameters (useful for scenarios like viewing or editing doctors)
    public User(int userId, StringProperty username, String firstName, String lastName, String email, StringProperty password, String role, String phoneNumber) {
        this.userId = new SimpleIntegerProperty(userId);
        this.username = username;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.password = password;
        this.role = new SimpleStringProperty(role);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    // Getter for userId
    public IntegerProperty userIdProperty() {
        return userId;
    }

    public int getUserId() {
        return userId.get();
    }

    // Getter for username
    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    // Getter for first name
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    // Getter for last name
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.get();
    }

    // Getter for email
    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    // Getter for password
    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    // Getter for role
    public StringProperty roleProperty() {
        return role;
    }

    public String getRole() {
        return role.get();
    }

    // Getter for phone number
    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    // Setter for userId (if needed for later updates)
    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    // Setter for username
    public void setUsername(String username) {
        this.username.set(username);
    }

    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    // Setter for last name
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    // Setter for email
    public void setEmail(String email) {
        this.email.set(email);
    }

    // Setter for password
    public void setPassword(String password) {
        this.password.set(password);
    }

    // Setter for role
    public void setRole(String role) {
        this.role.set(role);
    }

    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
