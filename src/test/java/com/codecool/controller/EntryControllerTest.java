package com.codecool.controller;

import com.codecool.MongoldicApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MongoldicApplication.class)
public class EntryControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private String expression;
    private String method;
    private String resultsFor = "results for";
    private String noResult = "no result";

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    //    main page
    @Test
    public void displayMainPage() throws Exception {
        String resultListHeader = "results for";
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(resultListHeader))))
                .andExpect(view().name("index"));
    }

    //    word search
    @Test
    public void wordSearch_anywhere_happyPath() throws Exception {
        expression = "агала";
        method = "anywhere";
        this.mockMvc.perform(get("http://localhost:8080/word?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void wordSearch_wholeWord_happyPath() throws Exception {
        expression = "аагалах";
        method = "wholeWord";
        this.mockMvc.perform(get("http://localhost:8080/word?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void wordSearch_startsWith_happyPath() throws Exception {
        expression = "аагал";
        method = "startsWith";
        this.mockMvc.perform(get("http://localhost:8080/word?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void wordSearch_endsWith_happyPath() throws Exception {
        expression = "галах";
        method = "endsWith";
        this.mockMvc.perform(get("http://localhost:8080/word?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void wordSearch_noResult() throws Exception {
        expression = "string not in the database";
        method = "endsWith";
        this.mockMvc.perform(get("http://localhost:8080/word?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(expression)))
                .andExpect(content().string(containsString(noResult)));
    }

    //    description search
    @Test
    public void descriptionSearch_anywhere_happyPath() throws Exception {
        expression = "hánt";
        method = "anywhereDesc";
        this.mockMvc.perform(get("http://localhost:8080/description?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void descriptionSearch_wholeWord_happyPath() throws Exception {
        expression = "hántol";
        method = "wholeWordDesc";
        this.mockMvc.perform(get("http://localhost:8080/description?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(resultsFor)))
                .andExpect(content().string(containsString(expression)));
    }

    @Test
    public void descriptionSearch_noResult() throws Exception {
        expression = "string not in the database";
        method = "anywhereDesc";
        this.mockMvc.perform(get("http://localhost:8080/description?expression=" + expression + "&method=" + method))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("expression", expression))
                .andExpect(model().attribute("method", method))
                .andExpect(content().string(containsString(expression)))
                .andExpect(content().string(containsString(noResult)));
    }

}