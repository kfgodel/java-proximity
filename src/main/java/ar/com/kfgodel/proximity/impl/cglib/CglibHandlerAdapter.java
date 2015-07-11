package ar.com.kfgodel.proximity.impl.cglib;

import ar.com.kfgodel.proximity.api.MethodInvocation;
import ar.com.kfgodel.proximity.api.ProxyHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * This type represents the adapter from cglib handler to proximity handler
 * Created by kfgodel on 11/07/15.
 */
public class CglibHandlerAdapter implements MethodInterceptor {

    private ProxyHandler proxyHandler;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInvocation methodInvocation = CglibMethodInvocation.create(o, method, objects, methodProxy);
        return proxyHandler.handle(methodInvocation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + proxyHandler;
    }

    public static CglibHandlerAdapter create(ProxyHandler handler) {
        CglibHandlerAdapter adapter = new CglibHandlerAdapter();
        adapter.proxyHandler = handler;
        return adapter;
    }

}
