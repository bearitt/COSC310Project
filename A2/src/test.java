import java.util.Scanner;

public class test {

	public static void main(String[] args) {
//		String question = "What time is it?";
//		String mark = question.substring(question.length()-1);
//		System.out.println(mark);
		
//		Scanner in = new Scanner(System.in);
//		String query1 = in.nextLine();
//		String query = query1.toLowerCase();
//		String[] greetings = {"Hello", "Hi", "Hey"};
//		for(String e:greetings) {
//			if(e.contentEquals(query))
//				System.out.println(Greeting.hello());
//			else
//				System.out.println("Nope");
//		}
//		in.close();
		
//		String hey = "Hey man";
//		String firstWord = hey.split(" ")[0];
//		String thing = firstWord.toLowerCase();
//		System.out.println(thing.contentEquals("hey") ? "yup" : "nope");
//		
		String[] greetings = {"hello", "hi", "hey"};
//		System.out.println(greetings[2].contentEquals(thing));
//		for(String e:greetings) {
//			if(e.contentEquals(thing))
//				System.out.println(Greeting.hello());
//			else
//				System.out.println("Nope");
//		}
		
		String test = "hey, man, what's up?";
		String[] splitTest = test.split("[ ,']+");
		String first = test.split("[ ,']+")[0];
		for(String e:splitTest)
			System.out.println(e);
		for(String e:greetings)
			if(e.contentEquals(first))
				System.out.println("yup");
	}

}
