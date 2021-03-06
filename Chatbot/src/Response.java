import java.util.ArrayList;

/*
 * This class returns responses to queries which are not questions. So far in the
 * implementation, only two methods make up this class: response, which responds
 * to a user greeting and a user compliment (maybe wishful thinking :) ), and help
 * which provides a short list of instructions to the user.
 * Future implementation will allow a user to place orders and check on the status of
 * their orders.
 */
public class Response {
	static String respond(ArrayList<String> sentence, String query) {
		String[] greetings = {"hello", "hi", "hey"};
		for(String e:greetings) {
			if(sentence.contains(e))
				return Greeting.hello();
		}
		
		String extraResponse = extraTopics(query);
		if(!extraResponse.contentEquals(""))
			return extraResponse;
		
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
	static String onlyARobot() {
		String robot = "I'm a robot, not a miracle maker! I can't do that :(";
		return robot;
	}
	
	private static String extraTopics(String query) {
		//feature: five responses to queries unrelated to the main topic
		
				if (query.toLowerCase().contains("music")) {
					return "Motorhead rules!";
				}
				else if (query.toLowerCase().contains("joke")) {
					return "Do you remember that joke I told you about my spine?"
							+ " It was about a weak back!";
				}
				else if (query.toLowerCase().contains("basketball") || query.toLowerCase().contains("nba")) {
					return "Go Raptors!";
				}
				else if (query.toLowerCase().contains("wine")) {
					return "I love a good pinot noir!";
				}
				else if (query.toLowerCase().contains("movie") || query.toLowerCase().contains("film")) {
					return "I cried in Twilight! Team Jacob for life.";
				}
				else
					return "";
	}
}
