package ru.geekbrains.HomeWork_3_2;

import java.sql.*;

public class Main {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void main(String[] args) {
        connect();
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("score"));



            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        disconnect();

    }
}
