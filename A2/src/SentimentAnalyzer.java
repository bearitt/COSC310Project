import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {
	//This method uses Stanford NLP to analyze the sentiment of a string
	static public int getStanfordSentimentRate(String sentimentText) {
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    //StanfordCoreNLP
	    int totalRate = 0;
	    String[] linesArr = sentimentText.split("\\.");
	    for (int i = 0; i < linesArr.length; i++) {
	        if (linesArr[i] != null) {
	            edu.stanford.nlp.pipeline.Annotation annotation = pipeline.process(linesArr[i]);
	            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
	                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	                int score = RNNCoreAnnotations.getPredictedClass(tree);
	                totalRate = totalRate + (score - 2);
	            }
	        }
	    }
	    return totalRate;
	}
}
