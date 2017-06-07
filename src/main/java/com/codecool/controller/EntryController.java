package com.codecool.controller;

import com.codecool.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Slf4j
@Controller
public class EntryController {

    @GetMapping("/")
    public String displayEntries(Model model) {
        ArrayList entries = Entry.getAll();
        model.addAttribute("entries", entries);
        return "index";
    }

    @GetMapping("/importDictionary")
    public ModelAndView importDictionary(Model model) {
        log.info("importDictionary button pressed");
        DictionaryParser.readFile();
        return new ModelAndView("redirect:/");
    }
}
