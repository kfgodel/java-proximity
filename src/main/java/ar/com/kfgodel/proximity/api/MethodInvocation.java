package ar.com.kfgodel.proximity.api;

import java.lang.reflect.Method;
import java.util.List;

/**
 * This type represents the invocation of method over an instance with arguments
 * Created by kfgodel on 11/07/15.
 */
public interface MethodInvocation {
    /**
     * @return The method called by this invocation
     */
    Method getInvokedMethod();

    /**
     * @return The list of arguments used, an empty list if none
     */
    List<Object> getArgumentList();

    /**
     * The proxied instance over which this invocation was done
     * @param <T> The type of expected proxy
     * @return The proxy receiver
     */
    <T> T getProxyInstance();
}
