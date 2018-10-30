package ar.com.kfgodel.proximity.impl.primitive;

import ar.com.kfgodel.proximity.api.ProximityException;
import ar.com.kfgodel.proximity.impl.support.InvocationSupport;

import java.lang.reflect.Method;

/**
 * This type represents a method invocation generated from a java proxy
 * Created by kfgodel on 26/07/14.
 */
public class NativeMethodInvocation extends InvocationSupport {

  private Object proxyInstance;
  private Method method;
  private Object[] args;

  public static NativeMethodInvocation create(Object proxyInstance, Method method, Object[] args) {
    NativeMethodInvocation invocation = new NativeMethodInvocation();
    invocation.proxyInstance = proxyInstance;
    invocation.args = args;
    invocation.method = method;
    return invocation;
  }

  @Override
  protected Object[] getArguments() {
    return args;
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
    throw new ProximityException("Native proxier doesn't allow super invocations (yet). Due to differences on JDKs: https://blog.jooq.org/2018/03/28/correct-reflective-access-to-interface-default-methods-in-java-8-9-10/");
  }
}
