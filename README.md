# mongoldic
an attempt to create a mongolian-hungarian dictionary (web)app

---
##### Before first run, create the database:
 * location: `src/resources/mongolian-dictionary.sqlite`
 * query: `create table mongolian_dictionary (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, word TEXT NOT NULL, description TEXT UNIQUE);`
 
---
##### To use DictionaryParser you should have
 * a text file (`src/resources/mongolian-dictionary.txt`)
 * each line of the text file should start with a Mongolian word written in cyrillic
 * the rest of the line should be the description for that word
 ---
 importing dictionary took 3152.228 seconds