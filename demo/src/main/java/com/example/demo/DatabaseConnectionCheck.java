package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectionCheck implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseConnectionCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------------------------------------------");
        System.out.println("Checking Database Connection...");
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("SUCCESS: Connected to the database!");
            System.out.println("Database Product Name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Database URL: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            System.err.println("FAILURE: Could not connect to the database.");
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------------");
    }
}
