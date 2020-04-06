import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;

public class POS {
	public static ArrayList<String> getPOS(String input) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		
		Annotation document = new Annotation(input);
		
		pipeline.annotate(document);
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		ArrayList<String> posValues = new ArrayList<String>();
		for(CoreMap sentence: sentences) {
			for(CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				//following line returns the original word. Not needed in current implementation
//				String word = token.get(CoreAnnotations.TextAnnotation.class);
				String pos = token.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
				posValues.add(pos);
			}
		}
		return posValues;
	}
	
	
}
