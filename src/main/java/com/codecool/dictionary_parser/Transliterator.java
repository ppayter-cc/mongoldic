package com.codecool.dictionary_parser;

class Transliterator {

    String scientific(String word) {
        return word.toLowerCase()
                    .replaceAll("ий", "ii")

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
                    .replaceAll("ъ", "")
                    .replaceAll("ы", "y")
                    .replaceAll("ь", "'")
                    .replaceAll("э", "e")
                    .replaceAll("ю", "ju")
                    .replaceAll("я", "ja")
        ;
    }

    String hungarianPhonetic(String word) {
        return word.toLowerCase()
                .replaceAll("ий", "í")

                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "v")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "je")
                .replaceAll("ё", "jo")
                .replaceAll("ж", "dzs")
                .replaceAll("з", "dz")
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
                .replaceAll("с", "sz")
                .replaceAll("т", "t")
                .replaceAll("у", "u")
                .replaceAll("ү", "ü")
                .replaceAll("ф", "f")
                .replaceAll("х", "h")
                .replaceAll("ц", "c")
                .replaceAll("ч", "cs")
                .replaceAll("ш", "s")
                .replaceAll("ъ", "")
                .replaceAll("ы", "í")
                .replaceAll("ь", "i")
                .replaceAll("э", "e")
                .replaceAll("ю", "ju")
                .replaceAll("я", "ja")
        ;
    }

    String hungarianScientific(String word) {
        return word.toLowerCase()
                .replaceAll("юү", "yüü")
                .replaceAll("ий", "í")

                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "w")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "ye")
                .replaceAll("ё", "yo")
                .replaceAll("ж", "ǰ")
                .replaceAll("з", "j")
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
                .replaceAll("ъ", "")
                .replaceAll("ы", "í")
                .replaceAll("ь", "i")
                .replaceAll("э", "e")
                .replaceAll("ю", "yu")
                .replaceAll("я", "ya")
                ;
    }

    String iso9(String word) {
        return word.toLowerCase()
                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "w")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "e")
                .replaceAll("ё", "ë")
                .replaceAll("ж", "ž")
                .replaceAll("з", "z")
                .replaceAll("и", "i")
                .replaceAll("й", "j")
                .replaceAll("к", "k")
                .replaceAll("л", "l")
                .replaceAll("м", "m")
                .replaceAll("н", "n")
                .replaceAll("о", "o")
                .replaceAll("ө", "ô")
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
                .replaceAll("щ", "ŝ")
                .replaceAll("ъ", "ʺ")
                .replaceAll("ы", "y")
                .replaceAll("ь", "ʹ")
                .replaceAll("э", "è")
                .replaceAll("ю", "û")
                .replaceAll("я", "â")
                ;
    }

    String standardRomanization(String word) {
        return word.toLowerCase()
                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "w")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "ye")
                .replaceAll("ё", "yo")
                .replaceAll("ж", "j")
                .replaceAll("з", "z")
                .replaceAll("и", "i")
                .replaceAll("й", "i")
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
                .replaceAll("х", "kh")
                .replaceAll("ц", "ts")
                .replaceAll("ч", "ch")
                .replaceAll("ш", "sh")
                .replaceAll("щ", "sh")
                .replaceAll("ъ", "i")
                .replaceAll("ы", "y")
                .replaceAll("ь", "i")
                .replaceAll("э", "e")
                .replaceAll("ю", "yu")
                .replaceAll("я", "ya")
                ;
    }

    String libraryOfCongress(String word) {
        return word.toLowerCase()
                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "w")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "e")
                .replaceAll("ё", "ë")
                .replaceAll("ж", "zh")
                .replaceAll("з", "z")
                .replaceAll("и", "i")
                .replaceAll("й", "ĭ")
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
                .replaceAll("х", "kh")
                .replaceAll("ц", "ts")
                .replaceAll("ч", "ch")
                .replaceAll("ш", "sh")
                .replaceAll("щ", "shch")
                .replaceAll("ъ", "ı")
                .replaceAll("ы", "y")
                .replaceAll("ь", "i")
                .replaceAll("э", "ê")
                .replaceAll("ю", "iu")
                .replaceAll("я", "ia")
                ;
    }

    String ipa(String word) {
        return word.toLowerCase()
                .replaceAll("а", "a")
                .replaceAll("б", "p")
                .replaceAll("в", "w̜")
                .replaceAll("г", "ɡ")
                .replaceAll("д", "t")
                .replaceAll("е", "ji")
                .replaceAll("ё", "jɔ")
                .replaceAll("ж", "tʃ")
                .replaceAll("з", "ts")
                .replaceAll("и", "i")
                .replaceAll("й", "i")
                .replaceAll("к", "kʰ")
                .replaceAll("л", "ɮ")
                .replaceAll("м", "m")
                .replaceAll("н", "n")
                .replaceAll("о", "ɔ")
                .replaceAll("ө", "ɵ")
                .replaceAll("п", "pʰ")
                .replaceAll("р", "r")
                .replaceAll("с", "s")
                .replaceAll("т", "tʰ")
                .replaceAll("у", "ʊ")
                .replaceAll("ү", "u")
                .replaceAll("ф", "f")
                .replaceAll("х", "x")
                .replaceAll("ц", "tsʰ")
                .replaceAll("ч", "tʃʰ")
                .replaceAll("ш", "ʃ")
                .replaceAll("щ", "ʃt͡ʃ")
                .replaceAll("ъ", "")
                .replaceAll("ы", "i")
                .replaceAll("ь", "ʲ")
                .replaceAll("э", "e")
                .replaceAll("ю", "jʊ")
                .replaceAll("я", "ja")
                ;
    }
}
