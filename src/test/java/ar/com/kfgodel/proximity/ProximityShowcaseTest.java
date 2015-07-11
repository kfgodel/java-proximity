package ar.com.kfgodel.proximity;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.com.kfgodel.proximity.api.MethodInvocation;
import ar.com.kfgodel.proximity.api.ProxyHandler;
import ar.com.kfgodel.proximity.impl.cglib.CglibProxier;
import ar.com.kfgodel.proximity.impl.primitive.NativeProxier;
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
public class ProximityShowcaseTest extends JavaSpec<ProximityTestContext> {
    @Override
    public void define() {

        describe("a proxier", () -> {

            it("can be created using native jdk proxies",()->{
                NativeProxier aProxier = NativeProxier.create();

                assertThat(aProxier).isNotNull();
            });

            context().proxier(NativeProxier::create);

            it("allows the creation of proxy instances for interface types", () -> {

                List aProxy = context().proxier()
                        .proxyWith((invocation) -> null, List.class);

                assertThat(aProxy).isNotNull();
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

                context().invocation(()->{
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

                it("knows the actual arguments",()->{
                    List<Object> argumentList = context().invocation().getArgumentList();

                    assertThat(argumentList).isEqualTo(Lists.newArrayList("passed argument"));
                });

                it("knows the invoked proxy",()->{
                    Function proxyInstance = context().invocation().getProxyInstance();

                    assertThat(proxyInstance).isNotNull();
                });

            });


        });

        describe("a cglib proxier", ()-> {

            it("can be used to proxy non interfaces",()->{
                CglibProxier proxier = CglibProxier.create();

                Object aProxy = proxier.proxyWith(invocation -> "A", Object.class);

                assertThat(aProxy.toString()).isEqualTo("A");
            });

        });


    }
}