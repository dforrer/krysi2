package rainbow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

		ArrayList<ArrayList<String>> finds = new ArrayList<ArrayList<String>>();
		String reducedGrail;

		for(int i = limit; i >= 0; i--) {
			String hashedGrail = HOLY_GRAIL;
			//	// calculate the next values
			reducedGrail = rf.reduce(hashedGrail, alphabet, 7, i);
			//	// add the finds to the array
			ArrayList<String> result = rt.countExistenceOf(reducedGrail);
			if(result.size() > 0) {
				finds.add(result);
				System.out.println("found:" + result);
			}
			if(i != limit) {
				for(int j = i+1; j <= limit; j++) {
					hashedGrail = hf.getHash(reducedGrail);
					reducedGrail = rf.reduce(hashedGrail, alphabet, 7, j);
					ArrayList<String> res = rt.countExistenceOf(reducedGrail);
					if(res.size() > 0) {
						finds.add(res);
						System.out.println("found:" + res);
					}
				}
			}

		}

		// Do something with the finds here (actually, find the previous value in the chain)

		for (ArrayList<String> list : finds) {
			for(int z = 0; z < list.size(); z++) {
				String foundValue = list.get(z);
				String tempValue = foundValue;
				String hashedGrail = HOLY_GRAIL;

				for(int i = 0; i <= limit; i++) {
					String oldValue = tempValue;
					tempValue = hf.getHash(tempValue);

					// if the hash was found in the chain
					if(tempValue.equals(hashedGrail)) {
						System.out.println("Passwort des Hashs: " + HOLY_GRAIL + " ist " + oldValue );
						break;
					}
					else {
						tempValue = rf.reduce(tempValue, alphabet, 7, i);
					}
				}
			}


		}


	}

}
