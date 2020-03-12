import java.io.Console;

/*
 * Greeting class, handles hellos and goodbyes
 */
public class Greeting {
	//outputs a string greeting the user
	public static String hello() {
		return "Hi! Thanks for chatting with me, what can I help you with?";
	}
	//outputs a string saying goodbye to the user with a timer counting down to program
	//completion, similar implementation to Bot.delayResponse
	static void goodbye() {
		Console console = System.console();
		String[] spinner = new String[] { 
				"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00084 seconds",
				"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00083 seconds",
				"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00082 seconds",
				"\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u0008\u00081 second \n"
				};
		try {
			console.printf("Chatbot: Thanks for chatting with me, goodbye!\n"
					+ "Exiting program in 5 seconds");
			Thread.sleep(1000);
			for (int i = 0; i < 4; i++) {
				console.printf("%s", spinner[i]);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			console.printf("Sorry, something went wrong on our end! Closing program...");
		}
	}
}
