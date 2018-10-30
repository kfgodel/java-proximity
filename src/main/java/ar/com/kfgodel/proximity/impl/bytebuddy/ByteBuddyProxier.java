package ar.com.kfgodel.proximity.impl.bytebuddy;

import ar.com.kfgodel.proximity.api.Proxier;
import ar.com.kfgodel.proximity.api.ProximityException;
import ar.com.kfgodel.proximity.api.ProxyHandler;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements proxier with Byte Buddy http://bytebuddy.net/#/
 * Date: 28/10/18 - 22:23
 */
public class ByteBuddyProxier implements Proxier {
  @Override
  public <T> T proxyWith(ProxyHandler handler, Class<T> expectedType, Class<?>... additionalTypes) {
    List<Type> interfaceTypes = Arrays.asList(additionalTypes);
    Class<? extends T> proxyClass = new ByteBuddy()
      .subclass(expectedType)
      .implement(interfaceTypes)
      .method(ElementMatchers.any())
      .intercept(InvocationHandlerAdapter.of(ByteBuddyInvocationHandler.create(handler)))
      .make()
      .load(expectedType.getClassLoader())
      .getLoaded();
    try {
      T proxy = proxyClass.getConstructor().newInstance();
      return proxy;
    } catch (Exception e) {
      throw new ProximityException("Failed to instante proxy: " + e.getMessage(), e);
    }
  }

  public static ByteBuddyProxier create() {
    ByteBuddyProxier proxier = new ByteBuddyProxier();
    return proxier;
  }

}
