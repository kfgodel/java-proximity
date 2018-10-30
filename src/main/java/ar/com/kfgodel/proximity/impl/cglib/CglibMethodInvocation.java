package ar.com.kfgodel.proximity.impl.cglib;

import ar.com.kfgodel.proximity.api.ProximityException;
import ar.com.kfgodel.proximity.impl.support.InvocationSupport;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * This type represents a method invocation created by cglib
 * Created by kfgodel on 11/07/15.
 */
public class CglibMethodInvocation extends InvocationSupport {

  private Object proxyInstance;
  private Method method;
  private Object[] arguments;
  private MethodProxy methodProxy;

  public static CglibMethodInvocation create(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
    CglibMethodInvocation invocation = new CglibMethodInvocation();
    invocation.proxyInstance = o;
    invocation.arguments = objects;
    invocation.method = method;
    invocation.methodProxy = methodProxy;
    return invocation;
  }

  @Override
  public Method getInvokedMethod() {
    return method;
  }

  @Override
  public <T> T getProxyInstance() {
    return (T) proxyInstance;
  }

  @Override
  public Object invokeSuper() {
    try {
      return methodProxy.invokeSuper(proxyInstance, arguments);
    } catch (Throwable e) {
      throw new ProximityException("Failed to call super " + method, e);
    }
  }

  @Override
  protected Object[] getArguments() {
    return arguments;
  }
}
