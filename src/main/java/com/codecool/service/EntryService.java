package com.codecool.service;

import com.codecool.db.DbConnection;
import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EntryService {
    private Connection connection;
    private ArrayList<Entry> entries = null;
    private PreparedStatement preparedStatement;
    private String expression;
    private String sql;

    public ArrayList<Entry> getByWord(String word, String searchMethod) {
        sql = "SELECT * FROM mongolian_dictionary WHERE " +
                    "LOWER (word) LIKE LOWER (?) OR " +
                    "LOWER (scientific) LIKE LOWER (?) OR " +
                    "LOWER (hungarian_phonetic) LIKE LOWER (?) OR " +
                    "LOWER (iso9) LIKE LOWER (?) OR " +
                    "LOWER (standard_romanization) LIKE LOWER (?) OR " +
                    "LOWER (library_of_congress) LIKE LOWER (?)  OR " +
                    "LOWER (ipa) LIKE LOWER (?)"
                    ;

        switch (searchMethod) {
            case "anywhere": expression = "%" + word.trim() + "%"; break;
            case "wholeWord": expression = word.trim(); break;
            case "startsWith": expression = word.trim() + "%"; break;
            case "endsWith": expression = "%" + word.trim(); break;
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

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public ArrayList<Entry> getByDescription(String description) {
        sql = "SELECT * FROM mongolian_dictionary WHERE LOWER(description) LIKE LOWER(?)";
        expression = "%" + description + "%";

        try {
            connection = DbConnection.getInstance().connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expression);
            ResultSet resultSet = preparedStatement.executeQuery();

            entries = createEntryList(resultSet);

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entries;
    }

    public ArrayList<Entry> getByDescriptionWholeWord(String description) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> wildcardEntries = getByDescription(description);
        Pattern matchWholeWord = Pattern.compile("\\b" + description + "\\b");

        for (Entry entry : wildcardEntries) {
            Matcher wordMatcher = matchWholeWord.matcher(entry.getDescription());
            if (wordMatcher.find()) {
                entries.add(entry);
            }
        }
        return entries;
    }

    public ArrayList<Entry> getRandomEntry() {
        sql = "SELECT * FROM mongolian_dictionary ORDER BY RANDOM() LIMIT 1";

        try {
            connection = DbConnection.getInstance().connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            entries = createEntryList(resultSet);

            connection.close();

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
