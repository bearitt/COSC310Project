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
	static String respond(ArrayList<String> sentence) {
		if(sentence.size() == 0)
			return "Please say something!";
		String[] greetings = {"hello", "hi", "hey"};
		for(String e:greetings) {
			if(sentence.contains(e))
				return Greeting.hello();
			//following lines deprecated with Jake SA implementation
//			if(e.contentEquals("awesome") ||
//					e.contentEquals("best") || e.contentEquals("great") ||
//					e.contentEquals("super"))
//				return "Thanks, you're pretty great too!";
		}
		
		return "I'm not sure what you mean. Type \"help\" if you need some guidance.";
	}	
	static String help() {
		String help = "\nType directly into the terminal anything you want to say to the me.\n"
				+"Questions must start with \"Where\", \"What\", \"How\" or \"When\".\n"
				+ "To exit the program, say \"Bye\" to me."
				;
		return help;
	}	
}
