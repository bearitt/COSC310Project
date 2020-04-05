//import java.io.Console;
/*
 * Main chatbot class. Zero argument constructor creates a new chatbot, calls methods from
 * the Question and Response classes.
 */
public class Bot {
	//Console console = System.console();
	//method for receiving input from the user through standard in
	void receiveQuery(String query) {
		String response;
		if(query.equals("help"))
			response = Response.help();
		else if(Question.isQuestion(query))
			response = Question.getQuestionType(query);
		else
			response = Response.respond(query);
		//delayResponse();
		System.out.print("Chatbot: ");
		System.out.printf(response + "\n");
	}
	
	//usage: to create the revolving ellipsis simulating the chatbot "typing" input to the user 
	private void delayResponse() {
		//String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		String[] spinner = new String[] { "\b\b\b.  ", "\b\b\b.. ", "\b\b\b..." };
		System.out.printf("Chatbot: ");
		System.out.printf(".  ");
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(150);
				System.out.printf("%s", spinner[i % spinner.length]);
			}
			System.out.printf("\b\b\b");
		} catch (InterruptedException e) {
			System.out.printf("Something went wrong\n");
		}
	}
}
