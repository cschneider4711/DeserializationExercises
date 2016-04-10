package victim;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

// Simulates a deserialization endpoint accepting data and deserializing from it
public class DeserializationEndpoint {

	public static void main(String[] args) throws Exception {
		System.out.println("Reading some bytes from somewhere and "
				+ "deserializing expected object from it:");
		
		// Read data from outside
		Scanner scanner = new Scanner(System.in);
		byte[] data = Base64.getDecoder().decode( scanner.nextLine() );
		
		// Deserialize and cast to expected object (completely unrelated to the Gadget)
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
			// could be any object, just cast it
			List result = (List) ois.readObject();
			// and use it as expected by the business usecase...
			// ... ... ...
			// ... ... ...
		}
	}
}
