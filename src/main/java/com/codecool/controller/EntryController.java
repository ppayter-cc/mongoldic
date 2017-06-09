package com.codecool.controller;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
@Controller
public class EntryController {

    @GetMapping("/")
    public String displayEntries(Model model) {
        ArrayList entries = getAll();
        model.addAttribute("entries", entries);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchWord(@RequestParam String word, Model model) {
        log.info("searchWord() method called. Searching: {}", word);
        ArrayList<Entry> entries = getByWord(word);
        model.addAttribute("entries", entries);
        return "index";
    }

    @GetMapping("/importDictionary")
    public ModelAndView importDictionary() {
        log.info("importDictionary button pressed");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        DictionaryParser dictionaryParser = new DictionaryParser();
        dictionaryParser.readFile();

        stopWatch.stop();
        log.info("importing dictionary took {} milliseconds", stopWatch.getTotalTimeMillis());

        return new ModelAndView("redirect:/");
    }

    private ArrayList<Entry> getByWord(String word) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ArrayList<Entry> entries = new ArrayList<>();
        String sql = "SELECT * FROM mongolian_dictionary WHERE word = ?";

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, word);
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

    private ArrayList<Entry> getAll() {
        ArrayList<Entry> entries = new ArrayList<>();
        String sql = "SELECT * FROM mongolian_dictionary";

        try (Connection connection = connect();
             Statement statement  = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            // loop through the result set
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
