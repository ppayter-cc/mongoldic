package com.codecool.controller;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DictionaryParser {

    private static final String FILENAME = "src/main/resources/mongolian-dictionary.txt";

    public void readFile() {
        log.info("readFile() method called");

        ArrayList<Entry> entries = new ArrayList<>();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(FILENAME);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                Entry entry = createEntry(currentLine);
                entries.add(entry);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (fileReader != null) {
                    fileReader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("there are {} entries", entries.size());
        saveEntries(entries);
    }

    private Entry createEntry(String line) {
//        log.info("DictionaryParser.createEntry() method called");
        Entry entry = new Entry();
        String word = "";
        String description = "";

        Pattern wordPattern = Pattern.compile("^\\s*[\\u0400-\\uFE2F\\s]+");
        Matcher wordMatcher = wordPattern.matcher(line);

        if (wordMatcher.find()) {
            word = wordMatcher.group().trim();
            description = line.replace(word, "").trim();
//            log.info("current entry: {} - {}", word, description);
        }

        entry.setWord(word);
        entry.setDescription(description);

        return entry;
    }

    private void saveEntries(ArrayList<Entry> entries) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("saveEntries() method called");

        Connection connection = connect();
        String sql = "INSERT INTO mongolian_dictionary(word,description) VALUES(?,?)";

        for (Entry entry : entries) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, entry.getWord());
                statement.setString(2, entry.getDescription());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        stopWatch.stop();
        log.info("saving entries to the database took {} milliseconds", stopWatch.getTotalTimeMillis());
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
