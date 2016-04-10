package victim.level2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SomeHarmlessSerializableThing implements Serializable {

	private Map<String,String> mapNameToSomething = new HashMap<>();
	private String nameLastAdded;
	
	private transient int someStatistics;
	

	public void addToNameMapping(String name, String something) {
		mapNameToSomething.put(name, something);
		updateStatistics();
	}

	public int getSomeStatistsics() {
		return someStatistics;
	}
	
	private void updateStatistics() {
		String lastAddedSomething = mapNameToSomething.get(nameLastAdded);
		// calculate some stats... whatever...
		System.out.println("Updating some statistics...");
		someStatistics = 42; // whatever...
		System.out.println("last added something: "+lastAddedSomething);
	}
	
	private void readObject(ObjectInputStream stream)
		        throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
		updateStatistics();
	}
}
