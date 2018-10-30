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

  /**
   * Invokes the method definition from the super class of the proxied instance.<br>
   * This allows invoking default interface definitions, or inherited definitions.<br>
   * Note this may only be possible when using a cglib proxier
   *
   * @return The returned result of  the super invocation
   * @throws ProximityException when the invocation fails
   */
  Object invokeSuper() throws ProximityException;
}
