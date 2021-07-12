package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream("./app.properties")) {
            property.load(fis);
            String url = property.getProperty("url");
            String login = property.getProperty("login");
            String password = property.getProperty("password");
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        }

    }
}

