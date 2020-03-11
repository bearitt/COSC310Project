
public class Response {
	static String respond(String query) {
		String[] greetings = {"hello", "hi", "hey"};
		for(String e:greetings) {
			if(e.contentEquals(query))
				return Greeting.hello();
		}
		return "hi not a question";
	}
	
	static String help() {
		String help = "\nType directly into the terminal anything you want to say to the me.\n"
				+"Questions must start with \"Where\", \"What\", \"How\" or \"When\".\n"
				+ "To exit the program, say \"Bye\" to me."
				;
		return help;
	}
	
}
