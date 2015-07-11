package ar.com.kfgodel.proximity.impl.cglib;

import ar.com.kfgodel.proximity.api.Proxier;
import ar.com.kfgodel.proximity.api.ProxyHandler;
import net.sf.cglib.proxy.Enhancer;

/**
 * This type represents the cglib proxier
 * Created by kfgodel on 11/07/15.
 */
public class CglibProxier implements Proxier {

    @Override
    public <T> T proxyWith(ProxyHandler handler, Class<T> expectedType, Class<?>... additionalTypes) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(expectedType);
        if(additionalTypes != null && additionalTypes.length > 0){
            enhancer.setInterfaces(additionalTypes);
        }
        enhancer.setCallback(CglibHandlerAdapter.create(handler));
        return (T) enhancer.create();

    }

    public static CglibProxier create() {
        CglibProxier proxier = new CglibProxier();
        return proxier;
    }

}
