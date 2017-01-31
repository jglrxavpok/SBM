package org.jglr.sbm.visitors;

import org.jglr.sbm.FunctionControl;

public interface FunctionVisitor {

    void visitFunction(long resultType, long resultID, FunctionControl control, long funcType);

    void visitFunctionEnd();

    void visitFunctionCall(long resultType, long resultID, long functionID, long[] arguments);

    void visitFunctionParameter(long resultType, long resultID);

}
