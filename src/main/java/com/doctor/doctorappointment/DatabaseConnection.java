package com.doctor.doctorappointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost/healthcare_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";


    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static boolean executeInsertQuery(String query) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
     }


    public static ResultSet executeSelectQuery(String query, Statement[] statementHolder) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statementHolder[0] = statement; // Pass the statement back to the caller
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


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
