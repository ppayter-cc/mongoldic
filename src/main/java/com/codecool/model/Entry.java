package com.codecool.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Entry {
    private int id;
    private String word;
    private String scientific;
    private String hungarianPhonetic;
    private String iso9;
    private String standardRomanization;
    private String libraryOfCongress;
    private String ipa;
    private String description;
}
