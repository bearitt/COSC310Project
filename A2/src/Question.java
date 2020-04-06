import java.util.ArrayList;
import java.util.HashMap;
/*
 * Method for handling questions from the user. Determines whether the input is a question,
 * and if so, what type of question it is. Called by Bot, calls Product class.

TODO: Fix jankiness of for loops. Maybe java map and filter?
https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html*/
public class Question {
	//HashMap storing products in the inventory
	private static final HashMap<String,Integer> products = Product.getProducts();
	//string to output if the question does not match any criteria in the methods
	private static final String notUnderstood = "I'm sorry, I don't understand the question.";
	static boolean isQuestion(String query) {
		if(query.length() > 0 && query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	//uses first word of question to determine type of question
	static String getQuestionType(ArrayList<String> sentence) {		
		String response;
		switch(sentence.get(0)) {
		case "where":
			response=whereQuestion(sentence);
			break;
		case "what":
			response=whatQuestion(sentence);
			break;
		case "how":
			response=howQuestion(sentence);
			break;
		case "when":
			response=whenQuestion(sentence);
			break;
		case "who":
			response=whoQuestion(sentence);
			break;
		case "which":
			response=whichQuestion(sentence);
			break;
		default:
			response=otherQuestion(sentence);
			break;
		}
		return response;
	}
/*
 * The following 7 methods return answers to the questions based on string matching.
 * The question is split based on one of the values in the regex expression [ ,.?;:]+
 * then a loop compares each word in the question to a list of keywords which return
 * an appropriate response. If no match is found, the string notUnderstood is returned.
 * In some cases, a certain keyword corresponds to multiple responses. In that case,
 * boolean flags indicate the presence of one or more keywords, and the response is
 * determined based on the presence of multiple flags.
 */
	private static String whereQuestion(ArrayList<String> sentence) {
		if(sentence.contains("store") || sentence.contains("locate") || sentence.contains("you") 
				|| sentence.contains("location"))
			return "We are located at 92 Baker Street.";
		
		return notUnderstood;
	}
	private static String whatQuestion(ArrayList<String> sentence) {
		boolean open, holiday;
		open=holiday=false;		
	
		if(sentence.contains("phone"))
			return "Our customer service number is 123-456-7890";
		if(sentence.contains("time") || sentence.contains("open"))
			open=true;	
		if(sentence.contains("holiday") || sentence.contains("christmas"))
			holiday=true;
	
		if(sentence.contains("member") || sentence.contains("membership"))
			return "Members receive preferred pricing on certain items, advance notice for"
					+ " sales and weekly coupons.";	
		if(sentence.contains("return") || 
				sentence.contains("exchange")) {
			return "We will accept returns and exchanges for up to 7 days for a full refund, or "
					+"for store credit up to 14 days after purchase.";
		}	
		
		if(open&&!holiday)
			return "We are open from 8am to 10pm, Monday to Sunday.";
		if(open&&holiday)
			return "The only day the store is closed is during Christmas, otherwise we are open"
					+ " every day with normal operating hours.";
		if(sentence.contains("top") 
				|| sentence.contains("most")) {
			String[] topSold = Product.topSold(products);
			String availProd = "The top selling products are: \n";
			int k = 1;
			for(String j : topSold) {
				availProd+= k++ + ")" + j + "\n";
			}
			return availProd;
		}	
		if(sentence.contains("product") 
				|| sentence.contains("sale") || sentence.contains("sell")
				|| sentence.contains("buy")) {
			String availProd = "We have the following for sale: \n";
			for(String j : products.keySet()) {
				availProd+=j + "\t\tIn stock: " + products.get(j) + "\n";
			}
			return availProd;
		}
		return notUnderstood;
	}
	private static String howQuestion(ArrayList<String> sentence) {
		if(sentence.get(2).contentEquals("you"))
			return "I'm good!";
		if(sentence.contains("service") || sentence.contains("contact"))
			return "Our customer service number is 123-456-7890";
		if(sentence.contains("membership") ||
				sentence.contains("account"))
			return "Membership enquiries can be handled in store. For further details, feel"
					+ " free to check out our website in the \"Membership\" section.";
		if(sentence.contains("cancel"))
			return "To cancel an order, please call our customer service line at 123-456-7890.";
		if(sentence.contains("track") ||
				sentence.contains("order"))
			return "You can track your delivery on the website in the Orders section.";
		
		
		return notUnderstood;
	}
	private static String whenQuestion(ArrayList<String> sentence) {
		boolean open, holiday;
		open=holiday=false;
		
		if(sentence.contains("time") || sentence.contains("open"))
			open=true;
		if(sentence.contains("holiday") || sentence.contains("christmas"))
			holiday=true;
		if(sentence.contains("promotion") || sentence.contains("promote") ||
				sentence.contains("feature"))
			return "Promotions usually run for one week, ending Sunday night."
					+ "Check the website for further details.";
				
		if(open&&!holiday)
			return "We are open from 8am to 10pm, Monday to Sunday.";
		if(open&&holiday)
			return "The only day the store is closed is during Christmas, otherwise we are open"
					+ " every day with normal operating hours.";		
		return notUnderstood;
	}	
	private static String whoQuestion(ArrayList<String> sentence) {
		
		if(sentence.contains("you"))//question.substring(question.length()-4).equals("you?"))
			return "I'm everyone's favourite chatbot!";
		return notUnderstood;
	}	
	private static String whichQuestion(ArrayList<String> sentence) {
		boolean pay, online;
		pay=online=false;
		
		if(sentence.contains("promotion") || sentence.contains("promote") ||
				sentence.contains("feature")) {
			String[] promotions = Product.featuredProducts(products);
			String answer = "The following items are currently featured: \n";
			for(String j:promotions)
				answer+=j+"\n";
			return answer;
		}
		if(sentence.contains("payment"))
			pay=true; 
		if(sentence.contains("online"))
			online=true;
		
		if(pay&&!online)
			return "In store, we will accept Mastercard, Visa, American Express, debit and cash.";
		if(pay&&online)
			return "For online orders, we accept Interac-Online, Visa, Mastercard, and American Express.";
		return notUnderstood;
	}
	private static String otherQuestion(ArrayList<String> sentence) {
		boolean membership = false;
		boolean renew = false;
		boolean pay = false;
		boolean delivery,schedule,account,expedited,fresh;
		delivery=schedule=account=expedited=fresh=false;
		if(sentence.contains("open"))
			return "We are open from 8am to 10pm, Monday to Sunday.";
		
		if(sentence.contains("membership"))
			membership = true;
		if(sentence.contains("expire") ||
				sentence.contains("renew") ||
				sentence.contains("reclaim"))
			renew = true;
		if(sentence.contains("pay") ||
				sentence.contains("cost"))
			pay = true;
		if(sentence.contains("checkout") ||
				sentence.contains("self-checkout"))
			return "Yes, our store offers a self-checkout system.";	
		if(sentence.contains("online") ||
				sentence.contains("deliver"))
			delivery=true;
		if(sentence.contains("time") ||
				sentence.contains("schedule"))
			schedule=true;
		if(sentence.contains("account") ||
				sentence.contains("sign") ||
				sentence.contains("signup"))
			account=true;
		if(sentence.contains("expedite"))
			expedited=true;
		if(sentence.contains("fresh") ||
				sentence.contains("vegetable") ||
				sentence.contains("produce") ||
				sentence.contains("meat"))
			fresh=true;
		
		//membership queries
		if(membership&&!renew&&!pay)
			return "We are currently accepting new members for our preferred customers promotion! "
			+ "Check the website for details or apply in store";
		if(membership&&renew&&!pay)
			return "Your card can be renewed either online or in store.";
		if(membership&&pay)
			return "Our preferred members program is completely free! You can join the club"
					+" or renew for free either online or in store";
		//delivery queries
		if(delivery&&!schedule&&!account&&!expedited&&!fresh)
			return "We offer online delivery for members. If you need a rush order, ask me "
					+ "about expedited delivery.";
		if(delivery&&schedule&&!account&&!expedited&&!fresh)
			return "The delivery schedule is set by the store. If you need a rush order, ask me "
					+ "about expedited delivery.";
		if(delivery&&!schedule&&account&&!expedited&&!fresh)
			return "You must be a member of our preferred customers program for delivery services."
					+ " Please inquire in the store or take a look at our website for details.";
		if(delivery&&!schedule&&!account&&expedited&&!fresh)
			return "We can guarantee one hour delivery for an extra $15 expedited delivery charge."
					+ " Note expedited delivery is only available within a 10 km radius of the store.";
		if(delivery&&!schedule&&!account&&!expedited&&fresh)
			return "Our team members choose the finest meats and produce for every delivery order, "
					+ "guaranteed fresh. If you are unsatisfied with an item, we will offer a full"
					+ " refund.";
		if(sentence.contains("product") 
				|| sentence.contains("sale") || sentence.contains("sell")
				|| sentence.contains("buy")) {
			String availProd = "We have the following for sale: \n";
			for(String j : products.keySet()) {
				availProd+=j + " In stock: " + products.get(j) + "\n";
			}
			return availProd;
		}
		return notUnderstood;
	}
}
