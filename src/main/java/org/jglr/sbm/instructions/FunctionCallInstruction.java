package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class FunctionCallInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long[] argumentIDs;
    private final long functionID;
    private final long returnTypeID;
    private String[] argumentTypes;
    private Type returnType;
    private String functionName;

    public FunctionCallInstruction(long resultType, long resultID, long functionID, long[] arguments) {
        super(OpFunctionCall, 4 + arguments.length, resultID);
        this.functionID = functionID;
        this.returnTypeID = resultType;
        this.argumentIDs = arguments;
    }

    public long[] getArgumentIDs() {
        return argumentIDs;
    }

    public long getFunctionID() {
        return functionID;
    }

    public long getReturnTypeID() {
        return returnTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        argumentTypes = infoPool.getNames(argumentIDs);
        returnType = infoPool.getType(returnTypeID);
        functionName = infoPool.getName(functionID);
    }

    @Override
    public String toString() {
        String params = "";
        String funcName = nameOrID(functionID, functionName);
        for (int i = 0; i < argumentIDs.length; i++) {
            if(i != 0)
                params += ", ";
            params += nameOrID(argumentIDs[i], argumentTypes[i]);
        }
        return "OpFunctionCall "+funcName+"("+params+")"+nameOrID(returnTypeID, returnType);
    }
}
