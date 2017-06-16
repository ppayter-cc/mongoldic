package com.codecool.db;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private DbConnection() {

    }

    public Connection connect() {
        // check if running on localhost:8080
        try {
            URL myURL = new URL("http://localhost:8080");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            return connectOnLocalhost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectOnHeroku();
    }

    private Connection connectOnHeroku() {
        Connection connection = null;
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Connection connectOnLocalhost() {
        Connection connection = null;

        String url = "jdbc:postgresql://localhost:5432/mongolian_dictionary";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        props.setProperty("ssl","true");

        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
