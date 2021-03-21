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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM students"); // point 3
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("score"));
            }
            stmt.executeUpdate("DROP TABLE students"); // point 5
            stmt.executeUpdate("CREATE TABLE students (" +     // point 1
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT," +
                    " score INTEGER)");

            stmt.executeUpdate("DELETE FROM students"); // point 4

            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Alex', 10);"); // point 2
//            resultSet = stmt.executeQuery("SELECT * FROM students");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt(1)
//                        + " " + resultSet.getString("name")
//                        + " " + resultSet.getInt("score"));
//            }

            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bill', 20);"); // point 2
            System.out.println();
            resultSet = stmt.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " " + resultSet.getString("name")
                        + " " + resultSet.getInt("score"));
            }
            System.out.println();



            resultSet = stmt.executeQuery("SELECT * FROM students");
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
