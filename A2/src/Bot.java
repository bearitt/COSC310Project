import java.util.ArrayList;

//import java.io.Console;
/*
 * Main chatbot class. Zero argument constructor creates a new chatbot, calls methods from
 * the Question and Response classes.
 */
public class Bot {
	//Console console = System.console();
	//method for receiving input from the user through standard in
	String receiveQuery(String query) {
		String response;
		ArrayList<String> sentence = Bot.parse(query);
		if(sentence.size() == 0)
			response = "Please say something!";
		else if(query.equals("help"))
			response = Response.help();
		else if (NERConfidence.containsDate(query) || sentence.contains("daily") 
				|| sentence.contains("special") || sentence.contains("bargain")
				|| sentence.contains("deal"))
			response = NERConfidence.getSpecial(sentence);
		else if(!sentence.get(0).contentEquals("tell") && !sentence.get(0).contentEquals("say") 
				&& POS.getPOS(query).get(0).charAt(0) == 'V')
			response = Response.onlyARobot();
		else if(Question.isQuestion(query))
			response = Question.getQuestionType(sentence);
		else
			response = Response.respond(sentence, query);
		//delayResponse();
		return "Chatbot: " + response;
//		System.out.print("Chatbot: ");
//		System.out.print(response + "\n");
	}
	
	//usage: to create the revolving ellipsis simulating the chatbot "typing" input to the user 
	private void delayResponse() {
		//String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		String[] spinner = new String[] { "\b\b\b.  ", "\b\b\b.. ", "\b\b\b..." };
		System.out.printf("Chatbot: ");
		System.out.printf(".  ");
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(150);
				System.out.printf("%s", spinner[i % spinner.length]);
			}
			System.out.printf("\b\b\b");
		} catch (InterruptedException e) {
			System.out.printf("Something went wrong\n");
		}
	}
	
	static public ArrayList<String> parse(String query) {
		if(!query.contentEquals("") && Question.isQuestion(query))
			query = query.substring(0,query.length()-1);
		ArrayList<String> splitQuery = Lemma.lemmatize(query);
		ArrayList<String> output = new ArrayList<String>();
		for(int i=0; i<splitQuery.size(); ++i) {
			String temp = Spellcheck.wordSuggester(splitQuery.get(i));
			output.add(temp);
		}
		return output;
	}
}
