package com.codecool.controller;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DictionaryParser {
    private static String FILENAME = "src/main/resources/mongolian-dictionary.txt";

    public static void readFile() {
        log.info("readFile() method called");

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(FILENAME);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                saveEntry(currentLine);
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
    }

    private static void saveEntry(String line) {
        log.info("DictionaryParser.getWord() method called");
        Entry entry = new Entry();
        String word = "";
        String description = "";

        Pattern wordPattern = Pattern.compile("^\\s*[\\u0400-\\uFE2F]+");
        Matcher wordMatcher = wordPattern.matcher(line);

        if (wordMatcher.find()) {
            word = wordMatcher.group();
            description = line.replace(word, "");

            log.info("current entry: {} - {}", word, description);
        }

        entry.setWord(word);
        entry.setDescription(description);
        entry.save();

    }
}
