package ar.com.kfgodel.proximity.impl.support;

import ar.com.kfgodel.proximity.api.MethodInvocation;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This type serves as base clase for different invocation types
 * Created by kfgodel on 11/07/15.
 */
public abstract class InvocationSupport implements MethodInvocation {

    private List<Object> argumentList;

    @Override
    public List<Object> getArgumentList() {
        if(argumentList == null){
            argumentList = createArgumentList();
        }
        return argumentList;
    }

    private List<Object> createArgumentList() {
        Object[] actualArguments = getArguments();
        if(actualArguments == null || actualArguments.length == 0){
            return Collections.emptyList();
        }
        return Lists.newArrayList(actualArguments);
    }

    protected abstract Object[] getArguments();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName());
        builder.append("{ methods: ");
        builder.append(this.getInvokedMethod());
        builder.append(", args: ");
        builder.append(Arrays.toString(this.getArguments()));
        builder.append("}");
        return builder.toString();
    }

}
