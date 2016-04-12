package org.jglr.sbm.types;

import java.util.Arrays;

public class FunctionType extends Type {
    private final Type returnType;
    private final Type[] parameters;

    public FunctionType(Type returnType, Type[] parameters) {
        super(returnType+" f("+ Arrays.toString(parameters)+")");
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Type[] getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }
}
