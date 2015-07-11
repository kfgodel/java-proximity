package ar.com.kfgodel.proximity.impl.primitive;

import ar.com.kfgodel.proximity.api.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This type is the adapter from native proxy handler to proximity handler.<br>
 *     Converts jdk proxy invocation into proximity invocations
 * Created by kfgodel on 26/07/14.
 */
public class NativeHandlerAdapter implements InvocationHandler {

    private ProxyHandler proxyHandler;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NativeMethodInvocation invocation = NativeMethodInvocation.create(proxy, method, args);
        return proxyHandler.handle(invocation);
    }

    public static NativeHandlerAdapter create(ProxyHandler proxyHandler) {
        NativeHandlerAdapter adapter = new NativeHandlerAdapter();
        adapter.proxyHandler = proxyHandler;
        return adapter;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + proxyHandler;
    }
}
