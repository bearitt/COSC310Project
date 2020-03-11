
public class Question {
	static boolean isQuestion(String query) {
		if(query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	static String getQuestionType(String question) {
		String[] questionSplit = question.split(" ");
		String response;
		String notUnderstood = "I'm sorry, I don't understand the question.";
		switch(questionSplit[0].toLowerCase()) {
		case "where":
			response=whereQuestion(question);
			break;
		case "what":
			response=whatQuestion(question);
			break;
		case "how":
			response=howQuestion(question);
			break;
		case "when":
			response=whenQuestion(question);
			break;
		case "who":
			response=whoQuestion(question);
			break;
		default:
			response=notUnderstood;
			break;
		}
		return response;
	}
	static void sendA() {}
	static void askQ() {}
	
	private static String whereQuestion(String question) {
		return "Where";
	}
	
	private static String whatQuestion(String question) {
		return "What";
	}
	private static String howQuestion(String question) {
		return "How";
	}
	private static String whenQuestion(String question) {
		return "When";
	}
	private static String whoQuestion(String question) {
		if(question.substring(question.length()-4).equals("you?"))
			return "I'm everyone's favourite chatbot!";
		return "Who";
	}

}
