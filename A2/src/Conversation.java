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
		System.out.println("Enter your question (type \"Help\" for more information): ");
		System.out.println("(Warning: the response to your first question will take a few seconds"
				+ " to load. Please be patient!)");
		System.out.printf("You: ");
		Scanner in = new Scanner(System.in);
		String question = in.nextLine().toLowerCase();
		String response;
		while(!question.contentEquals("bye") && !question.contentEquals("goodbye")) {
			response = chatbot.receiveQuery(question);
			System.out.println(response);
			System.out.printf("You: ");
			question = in.nextLine().toLowerCase();
		}
		//Greeting.goodbye();
		System.out.println("Chatbot: Thanks for chatting with me, goodbye!");
		in.close();
	}

}
