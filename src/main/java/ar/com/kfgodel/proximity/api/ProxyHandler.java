package ar.com.kfgodel.proximity.api;

/**
 * This type represents the invocation handler for proxied instances
 * Created by kfgodel on 11/07/15.
 */
@FunctionalInterface
public interface ProxyHandler {

    /**
     * Handles the method invocation according to this instance definition, returning the result
     * @param invocation The invocation to handle
     * @return The objet to return as result of the invocation
     */
    Object handle(MethodInvocation invocation);
}
