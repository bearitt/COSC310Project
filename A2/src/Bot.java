import java.io.Console;
/*
 * Main chatbot class. Zero argument constructor creates a new chatbot, calls methods from
 * the Question and Response classes.
 */
public class Bot {
	Console console = System.console();
	//method for receiving input from the user through standard in
	void receiveQuery(String query) {
		String response;
		if(query.equals("help"))
			response = Response.help();
		else if (NERConfidence.containsDate(query) || query.toLowerCase().contains("daily") || query.toLowerCase().contains("special"))
			response = NERConfidence.getSpecial(query);
		else if(Question.isQuestion(query))
			response = Question.getQuestionType(query);
		else
			response = Response.respond(query);
		delayResponse();
		console.printf(response + "\n");
	}
	
	//usage: to create the revolving ellipsis simulating the chatbot "typing" input to the user 
	private void delayResponse() {
		String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		console.printf("Chatbot: ");
		console.printf(".  ");
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(150);
				console.printf("%s", spinner[i % spinner.length]);
			}
			console.printf("\u0008\u0008\u0008");
		} catch (InterruptedException e) {
			console.printf("Something went wrong\n");
		}
	}
}
