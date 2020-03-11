import java.io.Console;

public class Greetings {
	
	public static String hello() {
		return "Hi! Thanks for chatting with me, what can I help you with?";
	}

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
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				console.printf("%s", spinner[i]);
			}
		} catch(InterruptedException e) {
			console.printf("Sorry, something went wrong on our end! Closing program...");
		}
	}
}
