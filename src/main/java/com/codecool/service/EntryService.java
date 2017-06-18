package com.codecool.service;

import com.codecool.db.DbConnection;
import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
public class EntryService {

    public ArrayList<Entry> getByWord(String word, String searchMethod) {
        String sql = "SELECT * FROM mongolian_dictionary WHERE " +
                    "LOWER (word) LIKE LOWER (?) OR " +
                    "LOWER (scientific) LIKE LOWER (?) OR " +
                    "LOWER (hungarian_phonetic) LIKE LOWER (?) OR " +
                    "LOWER (iso9) LIKE LOWER (?) OR " +
                    "LOWER (standard_romanization) LIKE LOWER (?) OR " +
                    "LOWER (library_of_congress) LIKE LOWER (?)  OR " +
                    "LOWER (ipa) LIKE LOWER (?)"
                    ;
        String expression;
        ArrayList<Entry> entries = null;
        PreparedStatement preparedStatement;
        Connection connection;

        switch (searchMethod) {
            case "anywhere": expression = "%" + word + "%"; break;
            case "wholeWord": expression = word; break;
            case "startsWith": expression = word + "%"; break;
            case "endsWith": expression = "%" + word; break;
            default: return null;
        }

        try {
            connection = DbConnection.getInstance().connect();
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
            connection = DbConnection.getInstance().connect();
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

        try (Connection connection = DbConnection.getInstance().connect();
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
}
