
public class Response {
	static String respond(String query) {
		String[] greetings = {"hello", "hi", "hey"};
		for(String e:greetings) {
			if(e.contentEquals(query))
				return Greetings.hello();
		}
		return "hi not a question";
	}
	
}
