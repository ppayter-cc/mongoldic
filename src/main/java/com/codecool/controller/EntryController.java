package com.codecool.controller;

import com.codecool.model.Entry;
import com.codecool.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String wordSearch(@RequestParam String word, Model model) {
        log.info("wordSearch() method called. Searching: {}", word);

        ArrayList<Entry> entries = entryService.getByWord(word);
        model.addAttribute("entries", entries);

        return "index";
    }

    @RequestMapping(value = "/description-search", method = RequestMethod.GET)
    public String descriptionSearch(@RequestParam String description, Model model) {
        log.info("descriptionSearch() method called. Searching: {}", description);

        ArrayList<Entry> entries = entryService.getByDescription(description);
        model.addAttribute("entries", entries);

        return "index";
    }

    @GetMapping("/importDictionary")
    public ModelAndView importDictionary() {
        log.info("importDictionary button pressed, importing...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        DictionaryParser dictionaryParser = new DictionaryParser();
        dictionaryParser.readFile();

        stopWatch.stop();
        log.info("importing dictionary took {} milliseconds", stopWatch.getTotalTimeMillis());

        return new ModelAndView("redirect:/");
    }
}
