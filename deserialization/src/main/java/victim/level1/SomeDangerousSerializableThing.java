package victim.level1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SomeDangerousSerializableThing implements Serializable {
	
  private String command;

  private void readObject(ObjectInputStream stream)
               throws IOException, ClassNotFoundException {
    stream.defaultReadObject();
    System.out.println("About to execute "+this.command);
    Runtime.getRuntime().exec(this.command);
  }
  
}
