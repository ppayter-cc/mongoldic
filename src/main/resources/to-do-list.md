#### Possible functions in the future (aka to-do list for me):

**User-suggested ideas:**
* [needs JS] autocomplete/suggestions (AJAX)
* [research] suggestions at the beginning of the result list in case the user mistyped something (needs further research & consultation)
* [research] similar words (compare searched expression to every other word? or only words with the same starting chars? might be slow with full database)
* synonyms (there are synonyms in the descriptions - maybe get & highlight '=someword' strings and possible synonyms from the descriptions, maybe create links for them)
* [waiting for possible new source without hyphens] removing hyphens from the descriptions where they shouldn't be (in the middle of Hungarian words usually, because the original material was formatted for printing)
* conjugated forms (not sure how to do that algorithmically, unless cutting off the last X characters)
* [done] search whole words in descriptions
* [needs an editor] translating the UI to Mongolian
---
**Developer ideas:**
* editing words (saving them to DB in a separate column, merging them after moderation)
* [unnecessary?] search/second limit (with captcha?)
* [done] proper error pages
* [done] nicer URLs
* android app (using API or locally stored data?)
* most searched words (extra column in DB)
* word of the day (not just random words, store date in DB with the word)
* [always in progress] more tests
* save the classical form of the words into separate column (parsing saved descriptions with regex)
* [done] separate html into fragments
* switch to Hibernate
* switch to REST & React