# COSC310Project
A chatbot coded in Java

## Instructions

To compile and run the code, add the appropriate script to the src folder
(follow directions in runme_scripts/readme.txt) and follow the directions
to run the program in the terminal.

**_Note:_ to run the runme scripts, your system must have the [JDK installed](https://www.oracle.com/java/technologies/javase-downloads.html)
 and your [path variables](https://www.java.com/en/download/help/path.xml) in your system must be set properly to point to
 your Java install. Without these steps, the scripts will throw an error. This program _will not_ run in Eclipse because
 of the use of the Console class.**

## Classes
### Conversation
Contains main method. Prompts user for first input, then keeps the conversation
going until the user says "bye" or "goodbye".

### Bot
Receives the user input, then calls the appropriate class based on the input
type in the receiveQuery method. The delayResponse method creates an ellipsis
string for 1.5 seconds before the chatbot responds to simulate the chatbot
typing a response to the user. The parse method calls Lemma.lemmatize to split
the input sentence into word stems, then performs a spellcheck on every word in
the sentence. receiveQuery calls Bot.parse, then uses a series of if statements
to determine if the query is asking for help, if the query contains a date value
(call NERConfidence.getSpecial), and whether or not the query is a question.

### Greeting
Contains two methods to hail the user. The hello method returns a greeting
triggered when the user says "hello", "hi", or "hey", while the goodbye method
uses a similar technique as Bot.delayResponse to say goodbye to the user
and provide a countdown before exiting the program.

### Question
Contains methods pertaining to user questions. isQuestion returns a boolean, true
if the last character in the user query is a question mark, false otherwise.
getQuestionType takes the first word in the user's query and calls the appropriate
method according to whether the question is a "where", "what", "how", "when", "which"
or "who" question. The method defaults to otherQuestion if the question does not
fall into one of those categories. whereQuestion, whatQuestion, howQuestion,
whenQuestion, whichQuestion and whoQuestion contain the responses to the user query
and use a series of if statements to determine the appropriate response. Note that
in the latest version of the program, implementation of the question methods take
an ArrayList (parsed in Bot.receiveQuery) as input. Some conditionals had to be
moved around with this new implementation to ensure accurate responses.

### Response
Contains methods for responding to user inputs that are not questions (as
determined by Question.isQuestion). respond contains if statements to
determine the appropriate response to a user input, including a check to determine
the sentiment of the statement using SentimentAnalyzer. help types out a short
manual for the user.

### Product
Contains methods pertaining to the list of products in the store. getProducts
returns a HashMap with key value pair (product name,current stock). productsSold
returns a Hashmap with key value pair (product name, amount sold to date).
topSold returns a string array with the first, second, and third top selling
products to date. featuredProducts returns a string array with the three products
currently featured by the store (in current implementation, this corresponds
to the first three products in the list. Future implementations will allow
choosing products to feature).

### Lemma
Contains a single method lemmatize which takes a string input, uses the Stanford
NLP library to split the string into the words that make up the sentence, then
uses a stemming algorithm to return an ArrayList containing the word stems
in the sentence. The algorithm seems to work similarly to the Porter Stemmer
algorithm, but does not perform as effectively (e.g. the output for "translation"
is "translation" instead of "translate").

### Levenshtein Distance
A helper class for spellcheck. Contains a single method computeDistance which
determines the Levenshtein distance between two words (i.e. the number of additions,
subtractions and/or substitutions needed to convert the first word into the second
word).

### Spellcheck
Performs spellcheck analysis for user inputs. Two static variables are used, a
string dictfile which indicates a filepath containing a dictionary of words to
use in checking spelling, and an ArrayList wordlist which contains the words in
the file indicated by dictfile. The loadDictionary method loads the dictionary file
into memory and stores it in wordlist. The wordSuggestor method takes a word,
calculates the Levenshtein distance against all the words in the dictionary, then
adds the matches to an ArrayList newlist if the distance is less than 3 (this
could be any value; because of the relatively small size of our dictionary, we
wanted to minimize the chance the algorithm would convert a correctly spelled
word that is not in the dictionary into a word in the dictionary, which would
fundamentally change the meaning of the sentence). newlist is then sorted with
the closest matches at the top, then the top match is returned. If newlist is
empty, the original word is returned.

### NERConfidence
Employs the Named Entity Recognition algorithm from the Stanford NLP library. The
getNamedEntityRecognition method <DOES SOME STUFF>. containsDate returns true if
the string contains a date value, and containsPerson returns true if the string
contains a person's name (the latter is unimplemented at this phase). getSpecial
returns a string telling the user about daily specials based on their date query.

### SentimentAnalyzer
Contains a single method getStanfordSentimentRate which takes a string as an
input then uses the Stanford NLP library to calculate a sentiment rating. Returns
an integer value representing the rating with the following key:
* rate < 0 : negative statement (e.g. "This app is a piece of garbage")
* rate = 0 : neutral statement (e.g. "The capital of Alberta is Edmonton")
* rate > 0 : positive statement (e.g. "This is the best app ever!")

### Test
Contains mostly garbage code not necessary for implementation. A playground for us
to experiment with methods in other classes.

## Features
* __Extra topic:__ _Daily Specials_
A list of daily specials which can be queried by the user.
e.g. ![Specials Chat Example](/img/specials_example.png)
