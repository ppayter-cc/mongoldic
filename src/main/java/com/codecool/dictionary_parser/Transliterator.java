package com.codecool.dictionary_parser;

public class Transliterator {

    public String scientific(String word) {
        return word.toLowerCase()
                    .replaceAll("а", "a")
                    .replaceAll("б", "b")
                    .replaceAll("в", "v")
                    .replaceAll("г", "g")
                    .replaceAll("д", "d")
                    .replaceAll("е", "je")
                    .replaceAll("ё", "jo")
                    .replaceAll("ж", "ž")
                    .replaceAll("з", "z")
                    .replaceAll("и", "i")
                    .replaceAll("й", "j")
                    .replaceAll("к", "k")
                    .replaceAll("л", "l")
                    .replaceAll("м", "m")
                    .replaceAll("н", "n")
                    .replaceAll("о", "o")
                    .replaceAll("ө", "ö")
                    .replaceAll("п", "p")
                    .replaceAll("р", "r")
                    .replaceAll("с", "s")
                    .replaceAll("т", "t")
                    .replaceAll("у", "u")
                    .replaceAll("ү", "ü")
                    .replaceAll("ф", "f")
                    .replaceAll("х", "h")
                    .replaceAll("ц", "c")
                    .replaceAll("ч", "č")
                    .replaceAll("ш", "š")
                    .replaceAll("ъ", "\"")
                    .replaceAll("ы", "y")
                    .replaceAll("ь", "'")
                    .replaceAll("э", "e")
                    .replaceAll("ю", "ju")
                    .replaceAll("я", "ja")
        ;
    }

    String hungarian(String word) {

        return word;
    }
}
