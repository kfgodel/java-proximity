package ar.com.kfgodel.proximity.impl.primitive;

import ar.com.kfgodel.proximity.api.Proxier;
import ar.com.kfgodel.proximity.api.ProximityException;
import ar.com.kfgodel.proximity.api.ProxyHandler;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * This type implements proxier by using he native jdk proxies
 * Created by kfgodel on 11/07/15.
 */
public class NativeProxier implements Proxier {

    public static NativeProxier create() {
        NativeProxier proxier = new NativeProxier();
        return proxier;
    }

    @Override
    public <T> T proxyWith(ProxyHandler handler, Class<T> expectedType, Class<?>... additionalTypes) {
        Class<?>[] proxiedTypes = join(expectedType, additionalTypes);
        try {
            T proxyInstance = (T) Proxy.newProxyInstance(expectedType.getClassLoader(),
                    proxiedTypes,
                    NativeHandlerAdapter.create(handler));
            return proxyInstance;
        } catch (IllegalArgumentException e) {
            throw new ProximityException("Cannot create instance. Probably a type is not an interface: " + Arrays.toString(proxiedTypes),e);
        }
    }

    private <T> Class<?>[] join(Class<T> expectedType, Class<?>[] additionalTypes) {
        if(additionalTypes == null || additionalTypes.length == 0){
            // If no extra arg, don't waste any more time
            return new Class[]{expectedType};
        }
        int extraTypesCount = additionalTypes.length;
        Class<?>[] joinedTypes = new Class[extraTypesCount + 1];
        joinedTypes[0] = expectedType;
        System.arraycopy(additionalTypes,0,joinedTypes,1, extraTypesCount);
        return joinedTypes;
    }
}
