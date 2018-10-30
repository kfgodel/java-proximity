package ar.com.kfgodel.proximity.impl.bytebuddy;

import ar.com.kfgodel.proximity.api.ProxyHandler;
import ar.com.kfgodel.proximity.impl.primitive.NativeMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date: 28/10/18 - 22:50
 */
public class ByteBuddyInvocationHandler implements InvocationHandler {

  private ProxyHandler proxyHandler;

  public static ByteBuddyInvocationHandler create(ProxyHandler proxyHandler) {
    ByteBuddyInvocationHandler handler = new ByteBuddyInvocationHandler();
    handler.proxyHandler = proxyHandler;
    return handler;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    NativeMethodInvocation invocation = NativeMethodInvocation.create(proxy, method, args);
    return proxyHandler.handle(invocation);
  }

}
