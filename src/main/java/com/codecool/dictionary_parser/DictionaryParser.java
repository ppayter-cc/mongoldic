package com.codecool.dictionary_parser;

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

    private Transliterator transliterator = new Transliterator();
    private static final String FILENAME = "src/main/resources/mongolian-dictionary.txt";
    private String currentlyImportedLine = "";

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

    private Entry createEntry(String currentlyReadLine) {
//        log.info("DictionaryParser.createEntry() method called");
        Entry entry = new Entry();
        String word = "";
        String description = "";

//        regex checking for cyrillic words at the beginning of a line, whitespace doesn't matter
        Pattern wordPattern = Pattern.compile("^\\s*[\\u0400-\\uFE2F\\s]+");
        Matcher wordMatcher = wordPattern.matcher(currentlyImportedLine);

        if (wordMatcher.find()) {
            word = wordMatcher.group().trim();
            description = currentlyImportedLine.replace(word, "").trim();
//            log.info("current entry: {} - {}", word, description);
        }

        /*
        *  sometimes the Classical Mongolian version of a word is in the next line,
        *  so we should look for it before saving a line to the db
        *  regex checking for anything surrounded with []
        */
        Pattern trailingWord = Pattern.compile("^\\[.*]$");
        Matcher trailingWordMatcher = trailingWord.matcher(currentlyReadLine);
        if (trailingWordMatcher.find()) {
            description = description + " " + trailingWordMatcher.group();
        }
        currentlyImportedLine = currentlyReadLine;

        entry.setWord(word);
        entry.setDescription(description);
        entry.setTransliterationScientific(transliterator.scientific(word));
        entry.setTransliterationHungarian(transliterator.hungarianPhonetic(word));
        entry.setTransliterationHungarianScientific(transliterator.hungarianScientific(word));

        return entry;
    }

    private void saveEntries(ArrayList<Entry> entries) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("saveEntries() method called");

        Connection connection = connect();
        String sql = "INSERT INTO mongolian_dictionary(word,description, transliteration_scientific, transliteration_hungarian, transliteration_hungarian_scientific) VALUES(?,?,?,?,?)";

        for (Entry entry : entries) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, entry.getWord());
                statement.setString(2, entry.getDescription());
                statement.setString(3, entry.getTransliterationScientific());
                statement.setString(4, entry.getTransliterationHungarian());
                statement.setString(5, entry.getTransliterationHungarianScientific());
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
