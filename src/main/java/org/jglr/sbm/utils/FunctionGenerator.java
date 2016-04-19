package org.jglr.sbm.utils;

import org.jglr.sbm.ModuleFunction;

public class FunctionGenerator {

    private final ModuleGenerator generator;
    private final ModuleFunction function;
    private long id;

    public FunctionGenerator(ModuleGenerator generator, ModuleFunction function) {
        this.generator = generator;
        this.function = function;
    }

    public void end() {
        generator.code.visitFunctionEnd();
    }

    void init(long id) {
        if(function.getName() != null) {
            generator.code.visitName(id, function.getName());
        }
        generator.code.visitFunction(generator.getTypeID(function.getReturnType()), id, function.getControl(), generator.getTypeID(function.getFunctionType()));
    }
}
