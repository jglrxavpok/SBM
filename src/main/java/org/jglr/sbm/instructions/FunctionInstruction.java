package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.FunctionControl;
import org.jglr.sbm.types.Type;

public class FunctionInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final FunctionControl control;
    private final long funcTypeID;
    private Type resultType;
    private Type funcType;
    private String name;

    public FunctionInstruction(long resultTypeID, long resultID, FunctionControl control, long funcTypeID) {
        super(FUNCTION, 5, resultID);
        this.resultTypeID = resultTypeID;
        this.control = control;
        this.funcTypeID = funcTypeID;
    }

    public Type getFuncType() {
        return funcType;
    }

    public String getName() {
        return name;
    }

    public Type getResultType() {
        return resultType;
    }

    public FunctionControl getControl() {
        return control;
    }

    public long getFuncTypeID() {
        return funcTypeID;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        name = constantPool.getName(getResultID());
        funcType = constantPool.getType(funcTypeID);
        resultType = constantPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "FunctionDeclaration "+nameOrID(funcTypeID, funcType)+" (0x"+Long.toHexString(control.getMask())+"): "+nameOrID(getResultID(), name);
    }
}
