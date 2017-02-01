package org.jglr.sbm.visitors;

import org.jglr.sbm.FunctionControl;

public interface FunctionVisitor {

    /**
     *
     Add a function. This instruction must be immediately followed by one OpFunctionParameter instruction per each formal parameter of this function. This function’s body or declaration will terminate with the next OpFunctionEnd instruction.

     The Result id cannot be used generally by other instructions. It can only be used by OpFunctionCall, OpEntryPoint, and decoration instructions.

     Result Type must be the same as the Return Type declared in Function Type.

     Function Type is the result of an OpTypeFunction, which declares the types of the return value and parameters of the function.
     */
    void visitFunction(long resultType, long resultID, FunctionControl control, long funcType);

    /**
     * Last instruction of a function.
     */
    void visitFunctionEnd();

    /**
     * Call a function.<br/>
     <br/>
     Result Type is the type of the return value of the function. It must be the same as the Return Type operand of the Function Type operand of the Function operand.<br/>
     <br/>
     Function is an OpFunction instruction. This could be a forward reference.<br/>
     <br/>
     Argument N is the object to copy to parameter N of Function.<br/>
     <br/>
     Note: A forward call is possible because there is no missing type information: Result Type must match the Return Type of the function, and the calling argument types must match the formal parameter types.
     */
    void visitFunctionCall(long resultType, long resultID, long functionID, long[] arguments);

    /**
     * Declare a formal parameter of the current function.<br/>
     <br/>
     Result Type is the type of the parameter.<br/>
     <br/>
     This instruction must immediately follow an OpFunction or OpFunctionParameter instruction. The order of contiguous OpFunctionParameter instructions is the same order arguments will be listed in an OpFunctionCall instruction to this function. It is also the same order in which Parameter Type operands are listed in the OpTypeFunction of the Function Type operand for this function’s OpFunction instruction.
     */
    void visitFunctionParameter(long resultType, long resultID);

}
