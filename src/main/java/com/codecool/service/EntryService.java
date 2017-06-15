package com.codecool.service;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

@Slf4j
public class EntryService {

    public ArrayList<Entry> getByWord(String word, String searchMethod) {
        String sql = "SELECT * FROM mongolian_dictionary WHERE " +
                "word LIKE ? COLLATE NOCASE OR " +
                "scientific LIKE ? COLLATE NOCASE OR " +
                "hungarian_phonetic LIKE ? COLLATE NOCASE OR " +
                "iso9 LIKE ? COLLATE NOCASE OR " +
                "standard_romanization LIKE ? COLLATE NOCASE OR " +
                "library_of_congress LIKE ? COLLATE NOCASE OR " +
                "ipa LIKE ? COLLATE NOCASE"
                ;
        String expression;
        ArrayList<Entry> entries = null;
        PreparedStatement preparedStatement;
        Connection connection;

        switch (searchMethod) {
            case "anywhere": expression = "%" + word + "%"; break;
            case "whole word": expression = word; break;
            case "starts with": expression = word + "%"; break;
            case "ends with": expression = "%" + word; break;
            default: return null;
        }

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expression);
            preparedStatement.setString(2, expression);
            preparedStatement.setString(3, expression);
            preparedStatement.setString(4, expression);
            preparedStatement.setString(5, expression);
            preparedStatement.setString(6, expression);
            preparedStatement.setString(7, expression);
            ResultSet resultSet = preparedStatement.executeQuery();

            entries = createEntryList(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public ArrayList<Entry> getByDescription(String description) {
        String sql = "SELECT * FROM mongolian_dictionary WHERE LOWER(description) LIKE LOWER(?)";
        String expression = "%" + description + "%";
        ArrayList<Entry> entries = null;
        PreparedStatement preparedStatement;
        Connection connection;

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expression);
            ResultSet resultSet = preparedStatement.executeQuery();

            entries = createEntryList(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entries;

    }

    public ArrayList<Entry> getRandomEntry() {
        ArrayList<Entry> entries = null;
        String sql = "SELECT * FROM mongolian_dictionary ORDER BY RANDOM() LIMIT 1";

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
                entry.setId(resultSet.getInt("id"));
                entry.setWord(resultSet.getString("word"));
                entry.setDescription(resultSet.getString("description"));
                entry.setIpa(resultSet.getString("ipa"));
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @SuppressWarnings("Duplicates")
    private Connection connect() {
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
