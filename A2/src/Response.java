/*
 * This class returns responses to queries which are not questions. So far in the
 * implementation, only two methods make up this class: response, which responds
 * to a user greeting and a user compliment (maybe wishful thinking :) ), and help
 * which provides a short list of instructions to the user.
 * Future implementation will allow a user to place orders and check on the status of
 * their orders.
 */
public class Response {
	static String respond(String query) {
		String[] greetings = {"hello", "hi", "hey"};
		String firstWord = query.split("[ ,';:.]+",0)[0];
		for(String e:greetings) {
			if(e.contentEquals(firstWord))
				return Greeting.hello();
			if(e.contentEquals("awesome") ||
					e.contentEquals("best") || e.contentEquals("great") ||
					e.contentEquals("super"))
				return "Thanks, you're pretty great too!";
		}
		//This calls the Sentiment Analyzer to determine if the statement 
		//is positive or negative and responds appropriately
		if (SentimentAnalyzer.getStanfordSentimentRate(query) < 0) {
			return "That's not very nice!";
		}else if (SentimentAnalyzer.getStanfordSentimentRate(query) > 0) {
			return "That's awesome!";
		}
		return "I'm not sure what you mean. Type \"help\" if you need some guidance.";
	}
	
	static String help() {
		String help = "\nType directly into the terminal anything you want to say to the me.\n"
				+"Questions must start with \"Where\", \"What\", \"How\" or \"When\".\n"
				+ "To exit the program, say \"Bye\" to me."
				+ "Hint: Ask me about my daily specials!"
				;
		return help;
	}	
}