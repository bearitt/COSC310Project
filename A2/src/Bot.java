import java.io.Console;

public class Bot {
	Console console = System.console();
	
	public void receiveQuery(String query) {
		String response;
		query = query.toLowerCase();
		if(query.equals("help"))
			response = help();
		else if(Questions.isQuestion(query))
			response = Questions.getQuestionType(query);
		else
			response = Response.respond(query);
		delayResponse();
		console.printf(response + "\n");
	}
	
	private String help() {
		String help = "Type directly into the terminal anything you want to say to the me.\n"
				+"Questions must start with \"Where\", \"What\", \"How\" or \"When\".\n"
				+ "To exit the program, say \"Bye\" to me."
				;
		return help;
	}
	
	private void delayResponse() {
		String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		console.printf("Chatbot: ");
		console.printf(".  ");
		try {
			for (int i = 0; i < 20; i++) {
				Thread.sleep(150);
				console.printf("%s", spinner[i % spinner.length]);
			}
			console.printf("\u0008\u0008\u0008");
		} catch (InterruptedException e) {
			console.printf("Something went wrong\n");
		}
	}
}
