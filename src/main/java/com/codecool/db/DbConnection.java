package com.codecool.db;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
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
        // checking if running on Heroku or not
        try {
            return connectOnHeroku();
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            log.warn("Heroku DB not found, running on localhost");
        }

        return connectOnLocalhost();
    }

    private Connection connectOnHeroku() throws SQLException, URISyntaxException, NullPointerException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
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
