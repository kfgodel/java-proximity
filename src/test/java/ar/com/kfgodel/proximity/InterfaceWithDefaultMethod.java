package ar.com.kfgodel.proximity;

/**
 * Date: 28/10/18 - 21:58
 */
public interface InterfaceWithDefaultMethod {

  default String aDefaultMethod() {
    return "the default value";
  }
}
