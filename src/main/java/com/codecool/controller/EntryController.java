package com.codecool.controller;

import com.codecool.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class EntryController {

    @GetMapping("/")
    public String displayMessage(Model model){
        ArrayList entries = Entry.getAll();
        model.addAttribute("entries", entries);
        return "index";
    }
}
