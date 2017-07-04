package com.codecool.controller;

import com.codecool.model.Entry;
import com.codecool.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Controller
public class ApiController {
    private EntryService entryService = new EntryService();

    @GetMapping("/api")
    public @ResponseBody ArrayList<Entry> randomWord() {
        return entryService.getRandomEntry();
    }

    @RequestMapping(value = "api/word", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Entry>  wordSearch(@RequestParam String expression, String method) {
        log.info("ApiController.wordSearch() method called. Searching: {}", expression);
        log.info("the selected search method is '{}'", method);

        return entryService.getByWord(expression, method);
    }

    @RequestMapping(value = "api/description", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Entry>  descriptionSearch(@RequestParam String expression, String method) {
        log.info("ApiController.descriptionSearch() method called. Searching: {}", expression);
        ArrayList<Entry> entries;

        if (method.equals("wholeWordDesc")) {
            entries = entryService.getByDescriptionWholeWord(expression);
        } else {
            entries = entryService.getByDescription(expression);
        }

        return entries;
    }
}
