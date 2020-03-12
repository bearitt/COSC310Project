import java.util.HashMap;
/*
 * Method for handling questions from the user. Determines whether the input is a question,
 * and if so, what type of question it is. Called by Bot, calls Product class.

TODO: Fix jankiness of for loops. Maybe java map and filter?
https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html*/
public class Question {
	//HashMap storing products in the inventory
	private static HashMap<String,Integer> products = Product.getProducts();
	//string to output if the question does not match any criteria in the methods
	private static String notUnderstood = "I'm sorry, I don't understand the question.";
	static boolean isQuestion(String query) {
		if(query.substring(query.length()-1).contentEquals("?"))
			return true;
		return false;
	}
	//uses first word of question to determine type of question
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
		case "which":
			response=whichQuestion(question);
			break;
		default:
			response=otherQuestion(question);
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
		boolean open, holiday;
		open=holiday=false;		
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("phone"))
				return "Our customer service number is 123-456-7890";
			if(questionSplit[i].contentEquals("time") || questionSplit[i].contentEquals("open"))
				open=true;	
			if(questionSplit[i].contentEquals("holiday") || questionSplit[i].contentEquals("Christmas")
					|| questionSplit[i].contentEquals("holidays"))
				holiday=true;
			if(questionSplit[i].contentEquals("top") 
					|| questionSplit[i].contentEquals("most")) {
				String[] topSold = Product.topSold(products);
				String availProd = "The top selling products are: \n";
				int k = 1;
				for(String j : topSold) {
					availProd+= k++ + ")" + j + "\n";
				}
				return availProd;
			}	
			if(questionSplit[i].contentEquals("products") 
					|| questionSplit[i].contentEquals("sale") || questionSplit[i].contentEquals("sell")
					|| questionSplit[i].contentEquals("buy")) {
				String availProd = "We have the following for sale: \n";
				for(String j : products.keySet()) {
					availProd+=j + "\t\tIn stock: " + products.get(j) + "\n";
				}
				return availProd;
			}	
			if(questionSplit[i].contentEquals("member") || 
					questionSplit[i].contentEquals("membership") ||
					questionSplit[i].contentEquals("members")) {
				return "Members receive preferred pricing on certain items, advance notice for"
						+ " sales and weekly coupons.";
			}	
			if(questionSplit[i].contentEquals("return") || 
					questionSplit[i].contentEquals("exchange")) {
				return "We will accept returns and exchanges for up to 7 days for a full refund, or "
						+"for store credit up to 14 days after purchase.";
			}	
		}
		if(open&&!holiday)
			return "We are open from 8am to 10pm, Monday to Sunday.";
		if(open&&holiday)
			return "The only day the store is closed is during Christmas, otherwise we are open"
					+ " every day with normal operating hours.";
		return notUnderstood;
	}
	private static String howQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		if(questionSplit[1].contentEquals("are") && questionSplit[2].contentEquals("you"))
			return "I'm good!";
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("service") || questionSplit[i].contentEquals("contact"))
				return "Our customer service number is 123-456-7890";
			if(questionSplit[i].contentEquals("membership") ||
					questionSplit[i].contentEquals("account"))
				return "Membership enquiries can be handled in store. For further details, feel"
						+ " free to check out our website in the \"Membership\" section.";
			if(questionSplit[i].contentEquals("track") ||
					questionSplit[i].contentEquals("order"))
				return "You can track your delivery on the website in the Orders section.";
			if(questionSplit[i].contentEquals("cancel"))
				return "To cancel an order, please call our customer service line at 123-456-7890.";
		}
		return notUnderstood;
	}
	private static String whenQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		boolean open, holiday;
		open=holiday=false;
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("time") || questionSplit[i].contentEquals("open"))
				open=true;
			if(questionSplit[i].contentEquals("holiday") || questionSplit[i].contentEquals("Christmas"))
				holiday=true;
			if(questionSplit[i].contentEquals("promotion") ||
					questionSplit[i].contentEquals("promoted") ||
					questionSplit[i].contentEquals("featured") ||
					questionSplit[i].contentEquals("features"))
				return "Promotions usually run for one week, ending Sunday night."
						+ "Check the website for further details.";
		}		
		if(open&&!holiday)
			return "We are open from 8am to 10pm, Monday to Sunday.";
		if(open&&holiday)
			return "The only day the store is closed is during Christmas, otherwise we are open"
					+ " every day with normal operating hours.";		
		return notUnderstood;
	}	
	private static String whoQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		if(questionSplit[questionSplit.length-1].contentEquals("you"))//question.substring(question.length()-4).equals("you?"))
			return "I'm everyone's favourite chatbot!";
		return notUnderstood;
	}	
	private static String whichQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		boolean pay, online;
		pay=online=false;
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("promotion") ||
					questionSplit[i].contentEquals("promoted") ||
					questionSplit[i].contentEquals("featured") ||
					questionSplit[i].contentEquals("features")) {
				String[] promotions = Product.featuredProducts(products);
				String answer = "The following items are currently featured: \n";
				for(String j:promotions)
					answer+=j+"\n";
				return answer;
			}
			if(questionSplit[i].contentEquals("payment"))
				pay=true; 
			if(questionSplit[i].contentEquals("online"))
				online=true;
		}
		if(pay&&!online)
			return "In store, we will accept Mastercard, Visa, American Express, debit and cash.";
		if(pay&&online)
			return "For online orders, we accept Interac-Online, Visa, Mastercard, and American Express.";
		return notUnderstood;
	}
	private static String otherQuestion(String question) {
		String[] questionSplit = question.split("[ ,.?;:]+");
		boolean membership = false;
		boolean renew = false;
		boolean pay = false;
		boolean delivery,schedule,account,expedited,fresh;
		delivery=schedule=account=expedited=fresh=false;
		for(int i=0;i<questionSplit.length;++i) {
			if(questionSplit[i].contentEquals("open"))
				return "We are open from 8am to 10pm, Monday to Sunday.";
			if(questionSplit[i].contentEquals("products") 
					|| questionSplit[i].contentEquals("sale") || questionSplit[i].contentEquals("sell")
					|| questionSplit[i].contentEquals("buy")) {
				String availProd = "We have the following for sale: \n";
				for(String j : products.keySet()) {
					availProd+=j + " In stock: " + products.get(j) + "\n";
				}
				return availProd;
			}
			if(questionSplit[i].contentEquals("membership"))
				membership = true;
			if(questionSplit[i].contentEquals("expired") ||
					questionSplit[i].contentEquals("renew") ||
					questionSplit[i].contentEquals("reclaim"))
				renew = true;
			if(questionSplit[i].contentEquals("pay") ||
					questionSplit[i].contentEquals("cost"))
				pay = true;
			if(questionSplit[i].contentEquals("checkout") ||
					questionSplit[i].contentEquals("self-checkout"))
				return "Yes, our store offers a self-checkout system.";	
			if(questionSplit[i].contentEquals("online") ||
					questionSplit[i].contentEquals("delivery"))
				delivery=true;
			if(questionSplit[i].contentEquals("time") ||
					questionSplit[i].contentEquals("schedule"))
				schedule=true;
			if(questionSplit[i].contentEquals("account") ||
					questionSplit[i].contentEquals("sign") ||
					questionSplit[i].contentEquals("signup"))
				account=true;
			if(questionSplit[i].contentEquals("expedited"))
				expedited=true;
			if(questionSplit[i].contentEquals("fresh") ||
					questionSplit[i].contentEquals("vegetables") ||
					questionSplit[i].contentEquals("produce") ||
					questionSplit[i].contentEquals("meat"))
				fresh=true;
		}
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
		return notUnderstood;
	}
}
