import java.io.Console;
import java.util.*;
public class Conversation {
/*
 * Class with main method, executes the conversation dialogue on an infinite loop
 * until the user says bye or goodbye. Uses scanner to receive dialogue from the user
 * through standard input and outputs messages to the console.
 */
	public static void main(String[] args) {
		Bot chatbot = new Bot();
		Console console = System.console();
		console.printf("Enter your question (type \"Help\" for more information): ");
		Scanner in = new Scanner(System.in);
		String question = in.nextLine().toLowerCase();
		while(!question.contentEquals("bye") && !question.contentEquals("goodbye")) {
			chatbot.receiveQuery(question);
			console.printf("You: ");
			question = in.nextLine().toLowerCase();
			//Non-functional NLP code: raises exceptions when running in terminal
//			console.printf("Sentiment rating: %d\n", SentimentAnalyzer.getStanfordSentimentRate(question));
		}
		Greeting.goodbye();	
		in.close();
	}

}
