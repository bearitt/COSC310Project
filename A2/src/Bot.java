import java.io.Console;

public class Bot {
	Console console = System.console();
	
	public void receiveQuery(String query) {
		String response;
		query = query.toLowerCase();
		if(query.equals("help"))
			response = help();
		else if(isQuestion(query))
			response = getQuestionType(query);
		else
			response = respond(query);
		delayResponse();
		console.printf(response + "\n");
	}
	
	private String getQuestionType(String question) {
		String[] questionSplit = question.split(" ");
		String response;
		String notUnderstood = "I'm sorry, I don't understand the question.";
		switch(questionSplit[0].toLowerCase()) {
		case "where":
			response=whereQuestion(question);
			break;
		case "what":
			response=whatQuestion(question);
			break;
		case "how":
			response=howQuestion(question);
			break;
		case "when":
			response=whenQuestion(question);
			break;
		case "who":
			response=whoQuestion(question);
			break;
		default:
			response=notUnderstood;
			break;
		}
		return response;
	}
	private void sendA() {}
	private void askQ() {}
	
	private boolean isQuestion(String query) {
		if(query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	
	private String whereQuestion(String question) {
		return "Where";
	}
	
	private String whatQuestion(String question) {
		return "What";
	}
	private String howQuestion(String question) {
		return "How";
	}
	private String whenQuestion(String question) {
		return "When";
	}
	private String whoQuestion(String question) {
		if(question.substring(question.length()-4).equals("you?"))
			return "I'm everyone's favourite chatbot!";
		return "Who";
	}
	
	private String respond(String query) {
		return "hi not a question";
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
