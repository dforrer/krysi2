package rainbow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RainbowTable {

  HashMap<String, String> rainbowTable;

  // Same in Ruby: (0..2000).map { |i| i.to_s(36).rjust(7, '0') }
  public RainbowTable() {
    this(2000);
  }

  public RainbowTable(int limit){
    rainbowTable = new HashMap<String, String>();
    for(int i = 0; i <= limit; i++) {
      rainbowTable.put(get7digitsNumber(i), "");
    }
  }

  public HashMap<String, String> getRainbowTable() {
    return rainbowTable;
  }

  private String get7digitsNumber(int original) {
    return String.format("%7s", Integer.toString(original, 36)).replace(" ", "0");
  }

  public ArrayList countExistenceOf(String value) {
    ArrayList<String> foundKeys = new ArrayList<String>();
    for(Map.Entry<String, String> entry : rainbowTable.entrySet()) {
      if(entry.getValue().equals(value)){
        foundKeys.add(entry.getKey());
      }
    }
    return foundKeys;
  }

}
