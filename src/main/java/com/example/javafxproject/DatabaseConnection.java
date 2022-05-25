package com.example.javafxproject;

import java.sql.*;

public class DatabaseConnection {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JavaFXproject_database";
    public static ResultSet resultSet;
    private Connection connection;
    private String SQL_SELECT_TABLES;

    public void createDatabase() {
    }

    public ResultSet setConnectionDB() throws SQLException{
        System.out.println("Сейчас будем коннектиться..");
        this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = this.connection.createStatement();
        SQL_SELECT_TABLES = "select * from createtables";
        resultSet = statement.executeQuery(SQL_SELECT_TABLES); // отправляем запрос и помещяем его в результСет

        return resultSet;
    }


    public void setCreateTable(String sqlStatement) throws SQLException {
        System.out.println("Сейчас будем коннектиться..");
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        SQL_SELECT_TABLES = sqlStatement;
        statement.execute(SQL_SELECT_TABLES);
    }
}

