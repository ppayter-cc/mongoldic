package com.codecool.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
@Data
public class Entry {
    private String word;
    private String description;
    private int id;

    public void save() {
        String sql = "INSERT INTO mongolian_dictionary(word,description) VALUES(?,?)";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, word);
            statement.setString(2, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Entry> getAll() {
        ArrayList<Entry> messages = new ArrayList<>();
        String sql = "SELECT * FROM mongolian_dictionary";

        try (Connection connection = connect();
             Statement statement  = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            // loop through the result set
            while (resultSet.next()) {
                Entry message = new Entry();
                message.setWord(resultSet.getString("word"));
                message.setDescription(resultSet.getString("description"));
                message.setId(resultSet.getInt("id"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    private static Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/mongolian-dictionary.sqlite";
        Connection connection = null;
        try {
            log.info("trying to connect to the database: {}", url);
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            log.error("something happened while trying to connect to the database: {}", e.getMessage());
        }
        return connection;
    }

}
