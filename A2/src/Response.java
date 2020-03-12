
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
