package rainbow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class ReduceFunction {

	public String reduce (String hash, ArrayList<String> alphabet, int pwLength, long step) {

		BigInteger h = new BigInteger(hash, 16);  // hash as BigInteger
		h = h.add(BigInteger.valueOf(step));      // + step
		BigInteger r = new BigInteger("0");
		BigInteger alphabetSize = BigInteger.valueOf(alphabet.size());
		
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i<=pwLength; i++){
			r = h.mod(alphabetSize);
			h = h.divide(alphabetSize); 
			sb.append(alphabet.get(r.intValue()));
		}
		 
		return sb.reverse().toString();	  // invert order with reverse()
	}
	
	public void testReduce(){
		ArrayList <String> alphabet = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
		System.out.println("expected: 87inwgn, " + reduce("29c3eea3f305d6b823f562ac4be35217", alphabet, 7, 0));
		System.out.println("expected: frrkiis, " + reduce("12e2feb5a0feccf82a8d4172a3bd51c3", alphabet, 7, 1));
		System.out.println("expected: dues6fg, " + reduce("437988e45a53c01e54d21e5dc4ae658a", alphabet, 7, 2));
	}
	
}
