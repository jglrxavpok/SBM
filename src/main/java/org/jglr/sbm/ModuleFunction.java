package org.jglr.sbm;

import org.jglr.sbm.types.FunctionType;
import org.jglr.sbm.types.Type;
import org.jglr.sbm.utils.ModuleComponent;

public class ModuleFunction implements ModuleComponent {

    private final String name;
    private final Type returnType;
    private final Type functionType;
    private final FunctionControl control;

    public ModuleFunction(String name, FunctionType functionType) {
        this.name = name;
        this.returnType = functionType.getReturnType();
        this.functionType = functionType;
        this.control = new FunctionControl(0);
    }

    public FunctionControl getControl() {
        return control;
    }

    public Type getFunctionType() {
        return functionType;
    }

    public String getName() {
        return name;
    }

    public Type getReturnType() {
        return returnType;
    }
}
