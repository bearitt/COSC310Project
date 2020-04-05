import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/*
 * As for the Levenshtein Distance class, big thanks to Baked Circuits for this class.
 * Modified from the code on
 * https://bakedcircuits.wordpress.com/2013/08/10/simple-spell-checker-in-java/
 */
public class Spellcheck {
	//dictionary file containing important words to the chatbot
    static String dictfile = "wordlist.txt";
    static ArrayList <String>wordlist = new ArrayList<String>();
    
    //load the dictionary
    public static void loadDictionary(String filename) throws IOException{
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while((line = br.readLine()) != null){
            if(line.length()>2 && line.length()<26)
                wordlist.add(line);
        }
        br.close();
    }
	public static String wordSuggester(String word){
        /*
         * Creates a list of words from the word list loaded in loadDictionary
         * and returns the top result 
         */
		HashMap <String, Integer>newlist = new HashMap<String, Integer>();
        int i;
        try {
            loadDictionary(dictfile);
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        for(String s : wordlist){
            i = LevenshteinDistance.computeDistance(s, word);
            if(i<3){
                // Adjust i. The lesser, the more precise number of options
                newlist.put(s, i);
            }
        }

        // Sorting to get the matches at the front of the list
        // Don't play with this part (as per Baked Circuits.
        // We did play with it though...)
        List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(newlist.entrySet());
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        
        Map<String, Integer> orderedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : entries) {
            orderedMap.put(entry.getKey(), entry.getValue());
        }

        /* Store the top result in result
        	(slightly janky implementation creating the whole list and
	        only taking the top value, future implementation could
	        clean this up significantly) */
        String result = "";
        int j = 0;
        for (Entry<String, Integer> e : orderedMap.entrySet()){
        	if(j++ == 0) {
        		result = e.getKey();
        		break;
        	}
        }
        return result;
    }
}
