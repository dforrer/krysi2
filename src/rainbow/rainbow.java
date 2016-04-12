package rainbow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rainbow {

	final static String HOLY_GRAIL = "1d56a37fb6b08aa709fe90e12ca59e12";  		// taken from Bonusaufgabe

	public static void main(String[] args) {

		int limit = 2000;
		RainbowTable rt = new RainbowTable(limit);

		HashMap<String,String> rainbowTable = rt.getRainbowTable();

		// Calculating the rainbow table
		for(Map.Entry<String, String> entry : rainbowTable.entrySet()) {
			entry.setValue("[value goes here]");																	// save the 2000-times reduced here
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}


		// Quest for encrypted string starts here

		String hashedGrail = HOLY_GRAIL;

		ArrayList<ArrayList<String>> finds = new ArrayList<ArrayList<String>>();
		for(int i = 0; i <= limit; i++) {

			//	// calculate the next values
			//	reducedGrail = reduceFunction(hashedGrail);
			//	hashedGrail = hashFunction(reducedGrail);

			//	// add the finds to the array
			//	ArrayList<String> result = rt.countExistenceOf(   reducedGrail   );
			//	if(result.size() > 0) {
			//		finds.add(result);
			//	}

		}


		// Do something with the finds here (actually, find the previous value in the chain)

	}

}
