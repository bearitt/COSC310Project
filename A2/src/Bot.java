import java.io.Console;

public class Bot {
	Console console = System.console();
	
	public void receiveQ(String question) {
		String[] questionSplit = question.split(" ");
		int type = getQuestionType(question);
		console.printf("Your question is type %d",type);
	}
	
	public int getQuestionType(String question) {
		String[] questionSplit = question.split(" ");
		int type;
		switch(questionSplit[0].toLowerCase()) {
		case "where":
			type=0;
			break;
		case "what":
			type=1;
			break;
		case "how":
			type=2;
			break;
		case "when":
			type=3;
			break;
		default:
			type=-1;
			break;
		}
		return type;
	}
	public void sendA() {}
	public void askQ() {}
	public void receiveA() {}
	
	public void delayResponse() {
		String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		console.printf("Chatbot: ");
		console.printf(".  ");
		try {
			for (int i = 0; i < 30; i++) {
				Thread.sleep(150);
				console.printf("%s", spinner[i % spinner.length]);
			}
			console.printf("\u0008\u0008\u0008");
		} catch (InterruptedException e) {
			console.printf("Something went wrong\n");
		}
	}
}
