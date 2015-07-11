package ar.com.kfgodel.proximity;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.proximity.api.MethodInvocation;
import ar.com.kfgodel.proximity.api.Proxier;

import java.util.function.Supplier;

/**
 * Created by kfgodel on 11/07/15.
 */
public interface ProximityTestContext extends TestContext {

    Proxier proxier();
    void proxier(Supplier<Proxier> definition);

    MethodInvocation invocation();
    void invocation(Supplier<MethodInvocation> definition);
}
