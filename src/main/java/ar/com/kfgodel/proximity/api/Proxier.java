package ar.com.kfgodel.proximity.api;

/**
 * This type represents a proxy creator instance.<br>
 *     Using instances of this type you can create proxies for a type, or types.<br>
 * Depending on the selected proxier the features that tha proxy offers
 * Created by kfgodel on 11/07/15.
 */
public interface Proxier {

    /**
     * Creates a new proxy instances for the given types
     * @param handler The invocation handler for each method invocation
     * @param expectedType The first proxy type that defines the expected instance type
     * @param additionalTypes Additional types that the proxy must meet
     * @param <T> The expected instance type
     * @return The created proxy instance
     */
    <T> T proxyWith(ProxyHandler handler, Class<T> expectedType, Class<?>... additionalTypes);
}
