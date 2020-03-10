import java.io.Console;
import java.lang.*;
public class test{// implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] spinner = new String[] {"\u0008\u0008\u0008.  ","\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..."};
        Console console = System.console();
        console.printf("Chatbot: ");
        console.printf(".  ");
        try {
	        for (int i = 0; i < 30; i++) {
	            Thread.sleep(150);
	            console.printf("%s", spinner[i % spinner.length]);
	        }
	        console.printf("\u0008\u0008\u0008");
	        console.printf(" Here's a message");
        } catch(InterruptedException e) {
        	console.printf("Something went wrong\n");
        }
	}

}
