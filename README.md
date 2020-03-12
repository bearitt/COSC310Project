# COSC310Project
A chatbot coded in Java

## Instructions

To compile and run the code, add the appropriate script to the src folder
(follow directions in runme_scripts/readme.txt) and follow the directions
to run the program in the terminal.

## Classes
### Conversation
Contains main method. Prompts user for first input, then keeps the conversation
going until the user says "bye" or "goodbye".

### Bot
Receives the user input, then calls the appropriate class based on the input
type in the receiveQuery method. The delayResponse method creates an ellipsis
string for 1.5 seconds before the chatbot responds to simulate the chatbot
typing a response to the user.

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
and use a series of if statements to determine the appropriate response.

### Response
Contains methods for responding to user inputs that are not questions (as
determined by Question.isQuestion). respond contains if statements to
determine the appropriate response to a user input. help types out a short
manual for the user

### Products
Contains methods pertaining to the list of products in the store. getProducts
returns a HashMap with key value pair (product name,current stock). productsSold
returns a Hashmap with key value pair (product name, amount sold to date).
topSold returns a string array with the first, second, and third top selling
products to date. featuredProducts returns a string array with the three products
currently featured by the store (in current implementation, this corresponds
to the first three products in the list. Future implementations will allow
choosing products to feature).
