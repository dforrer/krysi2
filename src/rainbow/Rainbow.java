package rainbow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.security.*;

public class Rainbow {

	final static String HOLY_GRAIL = "1d56a37fb6b08aa709fe90e12ca59e12";  		// taken from Bonusaufgabe
	final static ArrayList<String> alphabet = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));

	public static void main(String[] args) {

		ReduceFunction rf = new ReduceFunction();
		rf.testReduce();

		MD5Hash hf = new MD5Hash();
		int limit = 2000;
		RainbowTable rt = new RainbowTable(limit);

		HashMap<String,String> rainbowTable = rt.getRainbowTable();

		// Calculating the rainbow table
		for(Map.Entry<String, String> entry : rainbowTable.entrySet()) {
			String tempValue = entry.getKey();
			for(int i = 0; i <= limit; i++) {
				tempValue = hf.getHash(tempValue);
				tempValue = rf.reduce(tempValue, alphabet, 7, i);
			}
			// save the 2000-times reduced here
			entry.setValue(tempValue);
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}


		// Quest for encrypted string starts here

		String hashedGrail = HOLY_GRAIL;

		ArrayList<ArrayList<String>> finds = new ArrayList<ArrayList<String>>();
		String reducedGrail;

		System.out.println("--------------------");
		System.out.println("REDUCEDGRAILS");
		System.out.println("--------------------");

		for(int i = 0; i <= limit; i++) {
		//for(int i = limit; i >= 0; i--) {

			//	// calculate the next values
			reducedGrail = rf.reduce(hashedGrail, alphabet, 7, i);
			System.out.println(reducedGrail);
			//	// add the finds to the array
			ArrayList<String> result = rt.countExistenceOf(reducedGrail);
			if(result.size() > 0) {
				finds.add(result);
				System.out.println("found:" + result);
			}
			hashedGrail = hf.getHash(reducedGrail);
		}


		// Do something with the finds here (actually, find the previous value in the chain)

	}

}
