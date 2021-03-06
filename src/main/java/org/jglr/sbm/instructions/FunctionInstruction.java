package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
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
        super(OpFunction, 5, resultID);
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
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
        funcType = infoPool.getType(funcTypeID);
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "FunctionDeclaration "+nameOrID(funcTypeID, funcType)+" (0x"+Long.toHexString(control.getMask())+"): "+nameOrID(getResultID(), name);
    }
}
