package com.codecool.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Entry {
    private String word;
    private String description;
    private int id;

}
