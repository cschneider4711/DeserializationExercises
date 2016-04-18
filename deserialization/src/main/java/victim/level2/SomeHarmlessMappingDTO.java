package victim.level2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SomeHarmlessMappingDTO implements Serializable {

  private Map<String,String> nameMapping = new HashMap<>();
  private String nameLastAdded;
	
  private transient List<String> cachedLastItems;
	

  public void addToNameMapping(String name, String something) {
    nameMapping.put(name, something);
  }
	
  private void recreateCache() {
    String lastAddedSomething = nameMapping.get(nameLastAdded);
    // recreate the cache... whatever...
    if (cachedLastItems == null) {
      cachedLastItems = new ArrayList<>();
    }
    System.out.println("last added something: "+lastAddedSomething);
  }
	
  private void readObject(ObjectInputStream stream)
               throws IOException, ClassNotFoundException {
    stream.defaultReadObject();
    recreateCache();
  }
}
