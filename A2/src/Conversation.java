import java.io.Console;
import java.util.Scanner;
public class Conversation {

	public static void main(String[] args) {
		Bot chatbot = new Bot();
		Console console = System.console();
		console.printf("Enter your question: ");

		Scanner in = new Scanner(System.in);
		String question = in.nextLine();
		chatbot.receiveQ(question);
		
		in.close();
	}

}
