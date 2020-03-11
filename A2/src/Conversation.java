import java.io.Console;
import java.util.Scanner;
public class Conversation {

	public static void main(String[] args) {
		Bot chatbot = new Bot();
		Console console = System.console();
		console.printf("Enter your question (type \"Help\" for more information): ");
		Scanner in = new Scanner(System.in);
		String question = in.nextLine().toLowerCase();
		try {
			while(!question.contentEquals("bye") && !question.contentEquals("goodbye")) {
				chatbot.receiveQuery(question);
				console.printf("You: ");
				question = in.nextLine().toLowerCase();
			}
			
			String[] spinner = new String[] { 
					"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00084 seconds",
					"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00083 seconds",
					"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00082 seconds",
					"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00081 second \n"
					};
			
			console.printf("Chatbot: Thanks for chatting with me, goodbye!\n"
					+ "Exiting program in 5 seconds");
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				console.printf("%s", spinner[i]);
			}			
		} catch(InterruptedException e) {
			console.printf("Sorry, something went wrong on our end! Closing program...");
		}
		in.close();
	}

}
