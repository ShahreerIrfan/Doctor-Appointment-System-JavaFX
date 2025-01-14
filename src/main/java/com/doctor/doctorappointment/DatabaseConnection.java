package com.doctor.doctorappointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost/healthcare_db"; // Replace with your database name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "password"; // Replace with your MySQL password

    // Method to establish and return a database connection
    public static Connection getConnection() {
        try {
            // Establish a connection to the database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Method to execute an insert query for any table
    public static boolean executeInsertQuery(String query) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected > 0; // Return true if insertion was successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to execute a select query (e.g., to fetch data)
    public static ResultSet executeSelectQuery(String query) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            return statement.executeQuery(query); // Return result set for select query
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Method to close resources
    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
