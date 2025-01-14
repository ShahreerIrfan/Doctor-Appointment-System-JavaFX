CREATE DATABASE healthcare_db;
use healthcare_db;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Doctor', 'Patient') NOT NULL,
    phone_number VARCHAR(15),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 
CREATE TABLE Services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    duration INT NOT NULL -- Duration in minutes
);

-- Appointment

CREATE TABLE Appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    service_id INT NOT NULL,
    appointment_date DATETIME NOT NULL,
    status ENUM('Scheduled', 'Completed', 'Canceled') DEFAULT 'Scheduled',
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES Users(user_id),
    FOREIGN KEY (doctor_id) REFERENCES Users(user_id),
    FOREIGN KEY (service_id) REFERENCES Services(service_id)
);
-- Payments 

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method ENUM('Credit Card', 'Debit Card', 'Cash', 'Insurance') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_status ENUM('Paid', 'Pending', 'Failed') DEFAULT 'Pending',
    transaction_id VARCHAR(255) UNIQUE,
    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
);


CREATE TABLE Patient_Medical_History (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    diagnosis TEXT,
    treatment TEXT,
    doctor_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES Users(user_id),
    FOREIGN KEY (doctor_id) REFERENCES Users(user_id)
);


CREATE TABLE Emergency_Contacts (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    relationship VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES Users(user_id)
);


CREATE TABLE Specializations (
    specialization_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE Doctor_Availability (
    availability_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES Users(user_id)
);


CREATE TABLE Appointment_Reminders (
    reminder_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    reminder_time TIMESTAMP NOT NULL,
    reminder_type ENUM('Email', 'SMS', 'Push Notification') NOT NULL,
    status ENUM('Sent', 'Pending') DEFAULT 'Pending',
    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
);



-- Inserting an Admin User
INSERT INTO Users (first_name, last_name, email, password, role, phone_number, address) 
VALUES ('Admin', 'Smith', 'admin@healthcare.com', 'adminpassword', 'Admin', '1234567890', 'Admin Office, City Center');

-- Inserting a Doctor User
INSERT INTO Users (first_name, last_name, email, password, role, phone_number, address) 
VALUES ('Dr. John', 'Doe', 'john.doe@healthcare.com', 'doctorpassword', 'Doctor', '2345678901', 'Medical Clinic, Main St.');

-- Inserting another Doctor User
INSERT INTO Users (first_name, last_name, email, password, role, phone_number, address) 
VALUES ('Dr. Jane', 'Doe', 'jane.doe@healthcare.com', 'doctorpassword', 'Doctor', '3456789012', 'Health Center, Oak Ave.');

-- Inserting a Patient User
INSERT INTO Users (first_name, last_name, email, password, role, phone_number, address) 
VALUES ('Michael', 'Johnson', 'michael.johnson@patient.com', 'patientpassword', 'Patient', '4567890123', '123 Elm St, Citytown');

-- Inserting another Patient User
INSERT INTO Users (first_name, last_name, email, password, role, phone_number, address) 
VALUES ('Sarah', 'Williams', 'sarah.williams@patient.com', 'patientpassword', 'Patient', '5678901234', '456 Pine Rd, Uptown');

select * from Users;
 