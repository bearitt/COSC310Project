import java.io.Console;

public class Bot {
	Console console = System.console();
	
	public void receiveQuery(String query) {
		String response;
		if(isQuestion(query))
			response = getQuestionType(query);
		else
			response = respond(query);
		delayResponse();
		console.printf(response + "\n");
	}
	
	public String getQuestionType(String question) {
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
		default:
			response=notUnderstood;
			break;
		}
		return response;
	}
	public void sendA() {}
	public void askQ() {}
	
	public boolean isQuestion(String query) {
		if(query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	
	public String whereQuestion(String question) {
		return "hi";
	}
	
	public String whatQuestion(String question) {
		return "hi";
	}
	public String howQuestion(String question) {
		return "hi";
	}
	public String whenQuestion(String question) {
		return "hi";
	}
	
	public String respond(String query) {
		return "hi not a question";
	}
	
	public void delayResponse() {
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
