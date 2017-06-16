package com.codecool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private DbConnection() {

    }

//    public Connection connect() {
//        try {
//            URI dbUri = new URI(System.getenv("DATABASE_URL"));
//            String username = dbUri.getUserInfo().split(":")[0];
//            String password = dbUri.getUserInfo().split(":")[1];
//            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
//            return DriverManager.getConnection(dbUrl, username, password);
//        } catch (URISyntaxException | SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @SuppressWarnings("Duplicates")
    public Connection connect() {
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
