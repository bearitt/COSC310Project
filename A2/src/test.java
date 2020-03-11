import java.util.Scanner;

public class test {

	public static void main(String[] args) {
//		String question = "What time is it?";
//		String mark = question.substring(question.length()-1);
//		System.out.println(mark);
		Scanner in = new Scanner(System.in);
		String query1 = in.nextLine();
		String query = query1.toLowerCase();
		String[] greetings = {"Hello", "Hi", "Hey"};
		for(String e:greetings) {
			if(e.contentEquals(query))
				System.out.println(Greetings.hello());
			else
				System.out.println("Nope");
		}
		in.close();
	}

}
