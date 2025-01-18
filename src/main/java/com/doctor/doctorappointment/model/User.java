package com.doctor.doctorappointment.model;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;

    public User(String username, String firstName, String lastName, String email, String password, String role, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
