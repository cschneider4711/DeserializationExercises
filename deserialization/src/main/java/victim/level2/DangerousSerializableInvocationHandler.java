package victim.level2;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// This dangerous class is not really used by the application,
// it's just sitting on the ClassPath somewhere (AppServer, etc.)
public class DangerousSerializableInvocationHandler 
       implements InvocationHandler, Serializable {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
         throws Exception {
    System.out.println("About to execute "+(String)args[0]);
    Runtime.getRuntime().exec((String)args[0]);
    return null;
  }

}
