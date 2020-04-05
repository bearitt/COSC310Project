//import java.io.Console;
import java.util.*;
public class Conversation {
/*
 * Class with main method, executes the conversation dialogue on an infinite loop
 * until the user says bye or goodbye. Uses scanner to receive dialogue from the user
 * through standard input and outputs messages to the console.
 */
	public static void main(String[] args) {
		//calling the SentimentAnalyzer on an empty string loads the Stanford library
		SentimentAnalyzer.getStanfordSentimentRate("");
		Bot chatbot = new Bot();
		//Console console = System.console();
		System.out.printf("Enter your question (type \"Help\" for more information): ");
		Scanner in = new Scanner(System.in);
		String question = in.nextLine().toLowerCase();
		while(!question.contentEquals("bye") && !question.contentEquals("goodbye")) {
			chatbot.receiveQuery(question);
			System.out.printf("You: ");
			question = in.nextLine().toLowerCase();
		}
		//Greeting.goodbye();
		System.out.println("Ok bye.");
		in.close();
	}

}
