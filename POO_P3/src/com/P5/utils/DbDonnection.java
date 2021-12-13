package com.P5.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDonnection {

    public static Connection connectDatabase() throws SQLException {
        String baseUrl = "localhost";
        String port = "3306";
        String database = "POOandSQLP5";

        String url = String.format("jdbc:mysql://%s:%s/%s", baseUrl, port, database);
        String user = "user";
        String password = "user";

        return DriverManager.getConnection(url, user, password);
    }

}
