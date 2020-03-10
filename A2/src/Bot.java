import java.io.Console;

public class Bot {
	
	public void delayResponse() {
		String[] spinner = new String[] {"\u0008\u0008\u0008.  ","\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..."};
        Console console = System.console();
        console.printf("Chatbot: ");
        console.printf(".  ");
        try {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(150);
            console.printf("%s", spinner[i % spinner.length]);
        }
        } catch(InterruptedException e) {
        	console.printf("Something went wrong\n");
        }
	}
}
