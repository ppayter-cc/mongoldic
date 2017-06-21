package com.codecool.controller;

import com.codecool.model.Entry;
import com.codecool.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Slf4j
@Controller
public class EntryController {

    private EntryService entryService = new EntryService();

    @GetMapping("/")
    public String displayMainPage(Model model) {
        ArrayList<Entry> entries = entryService.getRandomEntry();
        model.addAttribute("entries", entries);
        return "index";
    }

    @RequestMapping(value = "/word-search", method = RequestMethod.GET)
    public String wordSearch(@RequestParam String expression, String searchMethod, Model model) {
        log.info("wordSearch() method called. Searching: {}", expression);
        log.info("the selected search method is '{}'", searchMethod);

        ArrayList<Entry> entries = entryService.getByWord(expression, searchMethod);

        model.addAttribute("expression", expression);
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("entries", entries);

        return "index";
    }
    
    @RequestMapping(value = "/description-search", method = RequestMethod.GET)
    public String descriptionSearch(@RequestParam String expression, String searchMethod, Model model) {
        log.info("descriptionSearch() method called. Searching: {}", expression);
        ArrayList<Entry> entries;

        if (searchMethod.equals("wholeWordDesc")) {
            entries = entryService.getByDescriptionWholeWord(expression);
        } else {
            entries = entryService.getByDescription(expression);
        }

        model.addAttribute("expression", expression);
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("entries", entries);

        return "index";
    }
}
