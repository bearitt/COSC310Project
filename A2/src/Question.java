import java.util.ArrayList;

//TODO: Fix jankiness of for loops. Maybe java map and filter?
//https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

public class Question {
	private static ArrayList<String> products = Products.getProducts();
	
	private static String notUnderstood = "I'm sorry, I don't understand the question.";
	static boolean isQuestion(String query) {
		if(query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	static String getQuestionType(String question) {
		String[] questionSplit = question.split(" ");
		String response;
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
			response=otherQuestion(question);
			break;
		}
		return response;
	}
	
	private static String whereQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("store") || questionSplit[i].contentEquals("located")
					|| questionSplit[i].contentEquals("location") || questionSplit[i].contentEquals("you"))
				return "We are located at 92 Baker Street.";
		}
		
		return notUnderstood;
	}
	
	private static String whatQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("phone"))
				return "Our customer service number is 123-456-7890";
		}
		
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("time") || questionSplit[i].contentEquals("open"))
				return "We are open from 8am to 10pm, Monday to Sunday.";
		}
		
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("products") 
					|| questionSplit[i].contentEquals("sale") || questionSplit[i].contentEquals("sell")
					|| questionSplit[i].contentEquals("buy")) {
				String availProd = "We have the following for sale: \n";
				for(int j=0;j<products.size();++j) {
					availProd+=products.get(j) + "\n";
				}
				return availProd;
			}	
		}
		
		return notUnderstood;
	}
	private static String howQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		if(questionSplit[1].contentEquals("are") && questionSplit[2].contentEquals("you"))
			return "I'm good!";
		else {
			for(int i=0;i<questionSplit.length;++i) {
				if(questionSplit[i].contentEquals("service") || questionSplit[i].contentEquals("contact"))
					return "Our customer service number is 123-456-7890";
			}
		}
		return notUnderstood;
	}
	private static String whenQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("open"))
				return "We are open from 8am to 10pm, Monday to Sunday.";
		}
		
		return notUnderstood;
	}
	private static String whoQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		if(questionSplit[questionSplit.length-1].contentEquals("you"))//question.substring(question.length()-4).equals("you?"))
			return "I'm everyone's favourite chatbot!";
		return notUnderstood;
	}

	private static String otherQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("open"))
				return "We are open from 8am to 10pm, Monday to Sunday.";
		}
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("products") 
					|| questionSplit[i].contentEquals("sale") || questionSplit[i].contentEquals("sell")
					|| questionSplit[i].contentEquals("buy")) {
				String availProd = "We have the following for sale: \n";
				for(int j=0;j<products.size();++j) {
					availProd+=products.get(j) + "\n";
				}
				return availProd;
			}	
		}
		return notUnderstood;
	}
}
