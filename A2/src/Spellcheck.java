import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Spellcheck {
	//dictionary file containing important words to the chatbot
    static String dictfile = "wordlist.txt";
    static ArrayList <String>wordlist = new ArrayList<String>();
    static HashMap <String, Integer>newlist = new HashMap<String, Integer>();
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
		//TODO: edit method to return the top result in the list
        /**
         * Prints a list of cities that can replace the city name in a 
         * sorted list, along with their edit distances (difference in name)
         */
        int i;
        try {
            loadDictionary(dictfile);
        } catch (IOException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        for(String s : wordlist){
            i = LevenshteinDistance.computeDistance(s, word);
            if(i<3){
                // Adjust i. The lesser, the more precise number of options
                newlist.put(s, i);
            }
        }

        // Sorting to get the best cities in the top
        // Don't play with this part
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

        // Display the list of cities
        String result = "";
        int j = 0;
        for (Entry<String, Integer> e : orderedMap.entrySet()){
        	if(j++ == 0) {
        		result = e.getKey();
        		break;
        	}
//            System.out.println(e.getKey() + " " + e.getValue());
        }
        return result;
    }
}
