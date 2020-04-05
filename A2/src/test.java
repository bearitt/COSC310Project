import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.CoreMap;

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
		
//		String test = "hey, man, what's up?";
//		String[] splitTest = test.split("[ ,']+");
//		String first = test.split("[ ,']+")[0];
//		for(String e:splitTest)
//			System.out.println(e);
//		for(String e:greetings)
//			if(e.contentEquals(first))
//				System.out.println("yup");
//		HashMap<String,Integer> products = new HashMap<String,Integer>();
//		//usage: product name, amount in stock
//		products.put("Apple\t",57);
//		products.put("Can of soup",12);
//		products.put("Steak\t",15);
//		products.put("Oranges\t",98);
//		products.put("Tofu\t",32);
//		HashMap<String, Integer> soldProd = new HashMap<String, Integer>();
//		Set<String> keys = products.keySet();
//		String[] prodKeys = new String[keys.size()];
//		keys.toArray(prodKeys);
//		//usage: product name, amount sold
//		soldProd.put(prodKeys[0], 147);
//		soldProd.put(prodKeys[1], 38);
//		soldProd.put(prodKeys[2], 42);
//		soldProd.put(prodKeys[3], 198);
//		soldProd.put(prodKeys[4], 43);
//		String first = "", second = "", third = "";
//		int max, mid, min;
//		max=mid=min=-1;
//		for(String i:soldProd.keySet()) {
//			if(soldProd.get(i) > max) {
//				min = mid;
//				third = second;
//				mid = max;
//				second = first;
//				max = soldProd.get(i);
//				first = i;
//			} else if(soldProd.get(i) > mid) {
//				min = mid;
//				third = second;
//				mid = soldProd.get(i);
//				second = i;
//			} else if(soldProd.get(i) > min) {
//				min = soldProd.get(i);
//				third = i;
//			}
//		}
//		String[] topThree = {first,second,third};
////		for(String i:topThree)
////			System.out.println(i);
//		System.out.println("Done");
		
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Hi! Say something");
		//String response = sc.nextLine();
		//System.out.println(NERConfidence.getNamedEntityRecognition(response));
		
//		String a = "I like watching movies";
//		MaxentTagger tagger =  new MaxentTagger("lib/tagger/taggers/english-left3words-distsim.tagger");
//		String tagged = tagger.tagString(a);
//		System.out.println(tagged);
		
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String text = "John is going to the mall tomorrow night and it is sunny outside";
		Annotation document = new Annotation(text);
		pipeline.annotate(document);
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences) {
			for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				System.out.printf("Print: word: [%s] pos: [%s] ne: [%s]\n", word, pos, ne);
			}
		}
	}

}
