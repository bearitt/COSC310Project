import java.io.Console;

public class Bot {
	Console console = System.console();
	
	public void receiveQuery(String query) {
		String response;
		query = query.toLowerCase();
		if(query.equals("help"))
			response = Response.help();
		else if(Question.isQuestion(query))
			response = Question.getQuestionType(query);
		else
			response = Response.respond(query);
		delayResponse();
		console.printf(response + "\n");
	}
	
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
