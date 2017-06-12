package com.codecool.service;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
public class EntryService {
    public ArrayList<Entry> getByWord(String word) {
        PreparedStatement preparedStatement;
        Connection connection;
        ArrayList<Entry> entries = null;
        String sql = "SELECT * FROM mongolian_dictionary WHERE word LIKE ?";

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + word + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            entries = createEntryList(resultSet);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public ArrayList<Entry> getByDescription(String description) {
        PreparedStatement preparedStatement;
        Connection connection;
        ArrayList<Entry> entries = null;
        String sql = "SELECT * FROM mongolian_dictionary WHERE description LIKE ?";

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + description + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            entries = createEntryList(resultSet);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public ArrayList<Entry> getAll() {
        ArrayList<Entry> entries = null;
        String sql = "SELECT * FROM mongolian_dictionary";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            entries = createEntryList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    private ArrayList<Entry> createEntryList(ResultSet resultSet) {
        ArrayList<Entry> entries = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Entry entry = new Entry();
                entry.setWord(resultSet.getString("word"));
                entry.setDescription(resultSet.getString("description"));
                entry.setId(resultSet.getInt("id"));
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
