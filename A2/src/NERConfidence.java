
import edu.stanford.nlp.pipeline.*;
import java.util.*;

public class NERConfidence {

	//method that returns the named entity recognitions in a string with confidences
    public static String getNamedEntityRecognition(String NERtext) {
        
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(NERtext);
        pipeline.annotate(document);
        String NERString = "";
        // get confidences for entities
        for (CoreEntityMention em : document.entityMentions()) {
            NERString+=(em.text() + "\t" + em.entityTypeConfidences() + "\n");
        }
        return NERString;
        // get confidences for tokens
        //for (CoreLabel token : document.tokens()) {
        //    System.out.println(token.word() + "\t" + token.get(CoreAnnotations.NamedEntityTagProbsAnnotation.class));
        //}
    }
    
    //See if string contains a date
    public static boolean containsDate(String checkForDate) {
    	return getNamedEntityRecognition(checkForDate).contains("DATE");
    }
    //See if string contains person
    public static boolean containsPerson(String checkForPerson) {
    	return getNamedEntityRecognition(checkForPerson).contains("PERSON");
    }
    //Uses NER to return the special for every day of the week
    public static String getSpecial(String findDate) {
    	if (!findDate.toLowerCase().contains("special") && 
    			!findDate.toLowerCase().contains("sale") &&
    			!findDate.toLowerCase().contains("bargain") &&
    			!findDate.toLowerCase().contains("deal"))
    		return "We have a different deal every day of the week. ";
    	if (findDate.toLowerCase().contains("monday"))
    		return "On Monday we have 30% off tofu.";
    	if (findDate.toLowerCase().contains("tuesday"))
			return "On Tuesday we have buy two get one free for soup cans.";
    	if (findDate.toLowerCase().contains("wednesday"))
			return "On Wednesday we have store wide 5% off.";
    	if (findDate.toLowerCase().contains("thursday"))
			return "On Thursday we have no specials.";
    	if (findDate.toLowerCase().contains("friday"))
			return "On Friday we have 10% off steak.";
    	if (findDate.toLowerCase().contains("saturday"))
			return "On Saturday apples are 1$ each.";
    	if (findDate.toLowerCase().contains("sunday"))
			return "On Sunday, oranges are 50% off.";
    	else 
    		return "We have a different special every day of the week.";				
    }
}