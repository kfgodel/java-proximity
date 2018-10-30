package ar.com.kfgodel.proximity;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.variable.Variable;
import ar.com.kfgodel.proximity.api.MethodInvocation;
import ar.com.kfgodel.proximity.api.ProxyHandler;
import ar.com.kfgodel.proximity.impl.bytebuddy.ByteBuddyProxier;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class tests and shows the main fgeatures of proximity
 * Created by kfgodel on 11/07/15.
 */
@RunWith(JavaSpecRunner.class)
public class BytBuddyProximityShowcaseTest extends JavaSpec<ProximityTestContext> {
  @Override
  public void define() {
    describe("a cglib proxier", () -> {
      context().proxier(ByteBuddyProxier::create);

      it("allows the creation of proxy instances for interface types", () -> {
        List aProxy = context().proxier().proxyWith((invocation) -> null, List.class);

        assertThat(aProxy).isNotNull();
      });

      it("can be used to proxy concrete classes", () -> {
        Object aProxy = context().proxier().proxyWith(invocation -> "A", Object.class);

        assertThat(aProxy.toString()).isEqualTo("A");
      });

      it("can delegate an interface method call to its default definition", () -> {
        ProxyHandler proxyHandler = invocation -> invocation.invokeSuper();
        InterfaceWithDefaultMethod aProxy = context().proxier().proxyWith(proxyHandler, InterfaceWithDefaultMethod.class);
        assertThat(aProxy.aDefaultMethod()).isEqualTo("the default value");
      });

      describe("a proxy instance", () -> {
        it("uses an proxy handler to handle the method invocation", () -> {
          ProxyHandler invocationHandler = (invocation) -> 2;

          List aProxy = context().proxier()
            .proxyWith(invocationHandler, List.class);

          assertThat(aProxy.size()).isEqualTo(2);
        });
      });

      describe("a method invocation", () -> {

        context().invocation(() -> {
          Variable<MethodInvocation> capturedInvocation = Variable.create();
          Function functionProxy = context().proxier()
            .proxyWith(capturedInvocation::set, Function.class);
          functionProxy.apply("passed argument");
          return capturedInvocation.get();
        });

        it("knows the invoked method ", () -> {
          Method invokedMethod = context().invocation().getInvokedMethod();

          assertThat(invokedMethod.getName()).isEqualTo("apply");
        });

        it("knows the actual arguments", () -> {
          List<Object> argumentList = context().invocation().getArgumentList();

          assertThat(argumentList).isEqualTo(Lists.newArrayList("passed argument"));
        });

        it("knows the invoked proxy", () -> {
          Function proxyInstance = context().invocation().getProxyInstance();

          assertThat(proxyInstance).isNotNull();
        });

      });


    });


  }
}