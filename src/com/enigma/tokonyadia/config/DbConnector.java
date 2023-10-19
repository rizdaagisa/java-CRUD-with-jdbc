package com.enigma.tokonyadia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection connect(){
        try {
            String driver = "jdbc:postgresql://localhost:5432/";
            String db_name = "tokonyadia_db";
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");
            return DriverManager.getConnection(driver+db_name, username, password);
        } catch (SQLException e){
            throw new RuntimeException("ERROR connecting " + e);
        }
    }
}
