import java.util.Scanner;
import java.util.*;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			// String d ="wont";
			// System.out.println(hash);
			// String[] tokens = toReadFile("Files" + d);
			// System.out.println(tokens[0]);
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				// String[] tokens
				// System.out.println(key);
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				t9.getAllWords(prefix);
				// for (String each : t9.getAllWords(prefix)) {
				// 	// System.out.println(each);
				// }
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
		// System.out.println("first");
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				// System.out.println("second");
				String line = scan.nextLine();
				bag.add(line);
			}
			// System.out.println("third");
			t9.getSuggestions(bag, k);
			// for (String each : t9.getSuggestions(bag, k)) {
			// 	System.out.println(each);
			// }

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] tokens = toReadFile(file);
		// System.out.println(tokens[0]);
			// System.out.println(tokens.length + "------------length");

		for (int i = 0; i < tokens.length; i++) 
		{
			// System.out.print(tokens[i] + "  ");
			String s = tokens[i].toLowerCase();
			if (st.contains(s)) {
				int val = st.get(s);
				st.put(s , val+1);
				// System.out.println(st.get(tokens[i]));
			}else {
				st.put(s , 1);

			}
		}
		// for (String s : st.keys())
  //           System.out.println(s + " " + st.get(s));

		return st;
	}

}

class T9 {
		BinarySearchST<String, Integer> st;
		TST<Integer> tst ;
		Bag<String> bag;
	public T9(BinarySearchST<String, Integer> s) {
		// your code goes here
		this.st = s;
		 tst = new TST<Integer>();
        int val = 0;
        // String[] tokens = st.keys(0, st.size());
        for(String k : st.keys()) {
        	// System.out.println(k);
            tst.put(k, st.get(k));
            // for (int j = 0; j < k.length(); j++) {
            //     tst.put(k.substring(j, k.length()), val++);
            // }

        }
        // for (int i = 0; i < s.size(); i++) {
        //     tst.put(s.key(), s.get());
        //     for (int j = 0; j < words[i].length(); j++) {
        //         tst.put(words[i].substring(j, words[i].length()), val++);
            // }
        // }
	}

	// get all the prefixes that match with given prefix.
	// public Iterable<String> getAllWords(String prefix) {
	public void getAllWords(String prefix) {
	
		// your code goes here
		String substring = prefix;
        for (String s : tst.keysWithPrefix(substring)) {
            System.out.println(s);
        }

		// return null;
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		bag = new Bag<>();
		// System.out.println("input");
		// System.out.println(words);
		Map<String, Integer> map = new HashMap<>(); 
		
		for (String s : words) {
			int i = 0;
			for(String p : tst.keysWithPrefix(s)) {
				i++;
			}
			// System.out.println(i);
			map.put(s,i);

		}
  
	    // Function to sort map by Key 
	    // public static void sortbykey() 
	    // { 
	        // TreeMap to store values of HashMap 
	        TreeMap<String, Integer> sorted = new TreeMap<>(); 
	  
	        // Copy all data from hashMap into TreeMap 
	        sorted.putAll(map); 
	  
	        // Display the TreeMap which is naturally sorted 
	        	int j = 0;
	        
	        for (Map.Entry<String, Integer> entry : sorted.entrySet())  {
	        	if (j<k) {
	        		// System.out.println(j);
	            	System.out.println(entry.getKey()) ;   
	            	j++;
	        		}
	            }      
	    // } 

		return null;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
