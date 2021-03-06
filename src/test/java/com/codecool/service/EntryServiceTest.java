package com.codecool.service;

import com.codecool.model.Entry;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EntryServiceTest {

    private EntryService entryService = new EntryService();
    private ArrayList<Entry> entries;

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

    @Test
    public void getByWord_anywhere_noSuchWord() throws Exception {
        entries = entryService.getByWord("asdfasdf", "anywhere");
        assertEquals(0, entries.size());
    }

//    whole word
    @Test
    public void getByWord_wholeWord_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("аажий", "wholeWord");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_wholeWord_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("aažii", "wholeWord");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_wholeWord_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("aadzsí", "wholeWord");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_wholeWord_noSuchWord() throws Exception {
        entries = entryService.getByWord("asdfasdf", "wholeWord");
        assertEquals(0, entries.size());
    }

//    starts with
    @Test
    public void getByWord_startsWith_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("ааж", "startsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_startsWith_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("aaž", "startsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_startsWith_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("aadzs", "startsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_startsWith_noSuchWord() throws Exception {
        entries = entryService.getByWord("asdfasdf", "startsWith");
        assertEquals(0, entries.size());
    }

//    ends with
    @Test
    public void getByWord_endsWith_cyrillic_happyPath() throws Exception {
        entries = entryService.getByWord("жий", "endsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_endsWith_scientific_happyPath() throws Exception {
        entries = entryService.getByWord("žii", "endsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_endsWith_hungarian_happyPath() throws Exception {
        entries = entryService.getByWord("dzsí", "endsWith");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByWord_endsWith_noSuchWord() throws Exception {
        entries = entryService.getByWord("asdfasdf", "endsWith");
        assertEquals(0, entries.size());
    }

//    description
    @Test
    public void getByDescription_happyPath() throws Exception {
        entries = entryService.getByDescription("anyó");
        assertNotEquals(0, entries.size());
    }

    @Test
    public void getByDescription_noResult() throws Exception {
        entries = entryService.getByDescription("asdfasdf");
        assertEquals(0, entries.size());
    }

//    description whole word
    @Test
    public void getByDescriptionWholeWord_happyPath() throws Exception {
        entries = entryService.getByDescriptionWholeWord("különcködő");
        assertEquals("авзаатай", entries.get(0).getWord());
    }

    @Test
    public void getByDescriptionWholeWord_notAWholeWord() throws Exception {
        entries = entryService.getByDescriptionWholeWord("önck");
        assertEquals(0, entries.size());
    }

//    random
    @Test
    public void getRandomEntry() throws Exception {
        entries = entryService.getRandomEntry();
        assertEquals(1, entries.size());
    }

}