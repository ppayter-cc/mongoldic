package com.codecool.service;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
public class EntryService {
    public ArrayList<Entry> getByWord(String word) {
        String sql = "SELECT * FROM mongolian_dictionary WHERE word LIKE ?";
        return createEntryList(sql, word);
    }

    public ArrayList<Entry> getByDescription(String description) {
        String sql = "SELECT * FROM mongolian_dictionary WHERE description LIKE ?";
        return createEntryList(sql, description);
    }

    private ArrayList<Entry> createEntryList(String sql, String searchedString) {
        ArrayList<Entry> entries = new ArrayList<>();
        PreparedStatement preparedStatement;
        Connection connection;

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchedString + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Entry entry = new Entry();
                entry.setWord(resultSet.getString("word"));
                entry.setDescription(resultSet.getString("description"));
                entry.setId(resultSet.getInt("id"));
                entries.add(entry);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    private Connection connect() {
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
