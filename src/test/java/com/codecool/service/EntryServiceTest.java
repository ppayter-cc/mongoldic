package com.codecool.service;

import com.codecool.model.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EntryServiceTest {

    private EntryService entryService = new EntryService();
    private ArrayList<Entry> entries;

    @Before
    public void setUp() throws Exception {
        entries = null;
    }

    @After
    public void tearDown() throws Exception {

    }

//    anywhere
    @Test
    public void getByWord_anywhere_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("ажи", "anywhere");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_anywhere_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("aži", "anywhere");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_anywhere_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("adzs", "anywhere");
        assertNotEquals(0, entries.size());
    }

//    @Test
//    public void getByWord_anywhere_noSuchWord() throws Exception {
//        entries = entryService.getByWord("asdfasdf", "anywhere");
//        assertEquals(0, entries.size());
//    }

//    whole word
    @Test
    public void getByWord_wholeWord_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("аажий", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_wholeWord_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("aažii", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_wholeWord_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("aadzsí", "whole word");
        assertNotEquals(0, entries.size());
    }

//    @Test
//    public void getByWord_wholeWord_noSuchWord() throws Exception {
//        entries = entryService.getByWord("asdfasdf", "whole word");
//        assertEquals(0, entries.size());
//    }

//    starts with
    @Test
    public void getByWord_startsWith_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("ааж", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_startsWith_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("aaž", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_startsWith_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("aadzs", "whole word");
        assertNotEquals(0, entries.size());
    }

//    @Test
//    public void getByWord_startsWith_noSuchWord() throws Exception {
//        entries = entryService.getByWord("asdfasdf", "whole word");
//        assertEquals(0, entries.size());
//    }

//    ends with
    @Test
    public void getByWord_endsWith_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("жий", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_endsWith_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("žii", "whole word");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_endsWith_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("dzsí", "whole word");
        assertNotEquals(0, entries.size());
    }

//    @Test
//    public void getByWord_endsWith_noSuchWord() throws Exception {
//        entries = entryService.getByWord("asdfasdf", "whole word");
//        assertEquals(0, entries.size());
//    }

//    description
//    @Test
//    public void getByDescription_happyPath() throws Exception {
//        entries = entryService.getByDescription("anyó");
//        assertNotEquals(0, entries.size());
//    }
//
//    @Test
//    public void getByDescription_noResult() throws Exception {
//        entries = entryService.getByDescription("asdfasdf");
//        assertEquals(0, entries.size());
//    }

//    random
//    @Test
//    public void getRandomEntry() throws Exception {
//        entries = entryService.getRandomEntry();
//        assertEquals(1, entries.size());
//    }

}