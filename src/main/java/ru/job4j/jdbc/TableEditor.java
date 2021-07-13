package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        try (FileInputStream fis = new FileInputStream("./app.properties")) {
            properties.load(fis);
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        }
    }

    private void executes(String command) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(command);
        }
    }

    public void createTable(String tableName) throws SQLException {
        executes(String.format("create table if not exists %s(%s)", tableName, "id serial primary key"));
    }

    public void dropTable(String tableName) throws SQLException {
        executes(String.format("drop table %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
       executes(String.format("alter table %s add %s %s null", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        executes(String.format("alter table %s drop column %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
       executes(String.format("alter table %s rename %s to %s", tableName, columnName, newColumnName));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor te = new TableEditor(new Properties());
        te.createTable("NewTable");
        System.out.println(getTableScheme(te.connection, "NewTable"));
        te.addColumn("NewTable", "name", "varchar(255)");
        te.addColumn("NewTable", "age", "int");
        System.out.println(getTableScheme(te.connection, "NewTable"));
        te.renameColumn("NewTable", "age", "aaage");
        System.out.println(getTableScheme(te.connection, "NewTable"));
        te.dropColumn("NewTable", "aaage");
        System.out.println(getTableScheme(te.connection, "NewTable"));
        te.dropTable("NewTable");
    }
}