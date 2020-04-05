import java.util.*;

import edu.stanford.nlp.pipeline.*; //StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreLabel;

public class Lemma {
	
	public static ArrayList<String> lemmatize(String input) {
		Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    CoreDocument coreDocument = new CoreDocument(input);
	    pipeline.annotate(coreDocument);
	    List<CoreLabel> coreLabelList = coreDocument.tokens();
	    ArrayList<String> stems = new ArrayList<String>();
	    for(CoreLabel coreLabel : coreLabelList) {
	    	String lemma = coreLabel.lemma();
	    	stems.add(lemma);
	    }
	    return stems;
	}
}
