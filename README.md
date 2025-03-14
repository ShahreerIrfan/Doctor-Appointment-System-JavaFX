# Doctor Appointment System - JavaFX

![Doctor Appointment System Banner](https://via.placeholder.com/800x200.png?text=Doctor+Appointment+System)

## Overview
The **Doctor Appointment System** is a JavaFX-based application designed to simplify appointment scheduling for doctors and patients. This user-friendly platform provides seamless functionality for creating accounts, logging in, and managing appointments efficiently.

## Features
- **User Registration and Login:**
  - Create a new account for patients or doctors.
  - Secure login with validation.

- **Appointment Scheduling:**
  - Book appointments with available doctors.
  - View and manage upcoming appointments.

- **Doctor Dashboard:**
  - Manage appointment schedules.
  - Update availability and view patient details.

- **Responsive Design:**
  - Intuitive and user-friendly interface.
  - Works smoothly across devices with JavaFX.

## Technologies Used
- **Programming Language:** Java
- **Framework:** JavaFX
- **Database:** MySQL
- **IDE:** IntelliJ IDEA

## Prerequisites
1. Java Development Kit (JDK 17 or above)
2. IntelliJ IDEA (or any preferred Java IDE)
3. MySQL Server

## Database Setup
1. Create a database named `healthcare_db`.
2. Execute the following SQL script to set up the required tables:

   ```sql
   CREATE DATABASE healthcare_db;
   USE healthcare_db;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL,
       password VARCHAR(100) NOT NULL,
       role ENUM('patient', 'doctor') NOT NULL
   );

   CREATE TABLE appointments (
       id INT AUTO_INCREMENT PRIMARY KEY,
       doctor_id INT NOT NULL,
       patient_id INT NOT NULL,
       appointment_date DATETIME NOT NULL,
       FOREIGN KEY (doctor_id) REFERENCES users(id),
       FOREIGN KEY (patient_id) REFERENCES users(id)
   );
   ```

3. Update the database connection settings in your project configuration.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Doctor-Appointment-System-JavaFX.git
   ```
2. Open the project in IntelliJ IDEA.
3. Configure the `healthcare_db` database connection in the `application.properties` file.
4. Run the `Main.java` file to start the application.

## Screenshots
### Login Page
![Login Page](https://via.placeholder.com/800x400.png?text=Login+Page)

### Appointment Dashboard
![Appointment Dashboard](https://via.placeholder.com/800x400.png?text=Appointment+Dashboard)

## Contribution
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For any inquiries or suggestions, feel free to reach out:
- **Email:** your.email@example.com
- **GitHub:** [YourGitHubProfile](https://github.com/yourusername)

---

*"Transforming healthcare scheduling, one appointment at a time!"*