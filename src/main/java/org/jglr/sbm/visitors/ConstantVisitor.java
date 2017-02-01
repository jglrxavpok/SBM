package org.jglr.sbm.visitors;

import org.jglr.sbm.sampler.SamplerAddressingMode;
import org.jglr.sbm.sampler.SamplerFilterMode;

public interface ConstantVisitor {

    /**
     * Declare a true Boolean-type scalar constant.<br/>
     <br/>
     Result Type must be the scalar Boolean type.
     */
    void visitTrueConstant(long type, long resultID);

    /**
     * Declare a false Boolean-type scalar constant.<br/>
     <br/>
     Result Type must be the scalar Boolean type.
     */
    void visitFalseConstant(long type, long resultID);

    /**
     * Declare a new integer-type or floating-point-type scalar constant.<br/>
     <br/>
     Result Type must be a scalar integer type or floating-point type.<br/>
     <br/>
     Value is the bit pattern for the constant. Types 32 bits wide or smaller take one word. Larger types take multiple words, with low-order words appearing first.
     */
    void visitConstant(long type, long resultID, long[] bitPattern);

    /**
     * Declare a new composite constant.<br/>
     <br/>
     Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the Constituents. The ordering must be the same between the top-level types in Result Type and the Constituents.<br/>
     <br/>
     Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result. The Constituents must appear in the order needed by the definition of the Result Type. The Constituents must all be ids of other constant declarations or an OpUndef.
     */
    void visitConstantComposite(long resultType, long resultID, long[] constituents);

    /**
     * Declare a new sampler constant.<br/>
     <br/>
     Result Type must be OpTypeSampler.<br/>
     <br/>
     Sampler Addressing Mode is the addressing mode; a literal from Sampler Addressing Mode.<br/>
     <br/>
     Param is one of:<br/>
     0: Non Normalized<br/>
     1: Normalized<br/>
     <br/>
     Sampler Filter Mode is the filter mode; a literal from Sampler Filter Mode.
     */
    void visitConstantSampler(long resultType, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter);

    /**
     * Declare a new null constant value.<br/>
     <br/>
     The null value is type dependent, defined as follows:<br/>
     - Scalar Boolean: false<br/>
     - Scalar integer: 0<br/>
     - Scalar floating point: +0.0 (all bits 0)<br/>
     - All other scalars: Abstract<br/>
     - Composites: Members are set recursively to the null constant according to the null value of their constituent types.<br/>
     <br/>
     Result Type must be one of the following types:<br/>
     - Scalar or vector Boolean type<br/>
     - Scalar or vector integer type<br/>
     - Scalar or vector floating-point type<br/>
     - Pointer type<br/>
     - Event type<br/>
     - Device side event type<br/>
     - Reservation id type<br/>
     - Queue type<br/>
     - Composite type
     */
    void visitConstantNull(long resultType, long resultID);

    /**
     * Declare a Boolean-type scalar specialization constant with a default value of <code>defaultValue</code>.<br/>
     <br/>
     This instruction can be specialized to become either an OpConstantTrue or OpConstantFalse instruction.<br/>
     <br/>
     Result Type must be the scalar Boolean type.<br/>
     <br/>
     See Specialization.
     */
    void visitSpecConstantBool(long resultType, long resultID, boolean defaultValue);

    /**
     * Declare a new integer-type or floating-point-type scalar specialization constant.<br/>
     <br/>
     Result Type must be a scalar integer type or floating-point type.<br/>
     <br/>
     Value is the bit pattern for the default value of the constant. Types 32 bits wide or smaller take one word. Larger types take multiple words, with low-order words appearing first.<br/>
     <br/>
     This instruction can be specialized to become an OpConstant instruction.<br/>
     <br/>
     See Specialization.
     */
    void visitSpecConstant(long resultType, long resultID, long[] value);

    /**
     * Declare a new composite specialization constant.<br/>
     <br/>
     Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the Constituents. The ordering must be the same between the top-level types in Result Type and the Constituents.<br/>
     <br/>
     Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result. The Constituents must appear in the order needed by the definition of the type of the result. The Constituents must be the id of other specialization constant or constant declarations.<br/>
     <br/>
     This instruction will be specialized to an OpConstantComposite instruction.<br/>
     <br/>
     See Specialization.
     */
    void visitSpecConstantComposite(long resultType, long resultID, long[] constituents);

    /**
     * Declare a new specialization constant that results from doing an operation.<br/>
     <br/>
     Result Type must be the type required by the Result Type of Opcode.<br/>
     <br/>
     Opcode must be one of the following opcodes. This literal operand is limited to a single word.<br/>
     OpSConvert, OpFConvert<br/>
     OpSNegate, OpNot<br/>
     OpIAdd, OpISub<br/>
     OpIMul, OpUDiv, OpSDiv, OpUMod, OpSRem, OpSMod<br/>
     OpShiftRightLogical, OpShiftRightArithmetic, OpShiftLeftLogical<br/>
     OpBitwiseOr, OpBitwiseXor, OpBitwiseAnd<br/>
     OpVectorShuffle, OpCompositeExtract, OpCompositeInsert<br/>
     OpLogicalOr, OpLogicalAnd, OpLogicalNot,<br/>
     OpLogicalEqual, OpLogicalNotEqual<br/>
     OpSelect<br/>
     OpIEqual, OpINotEqual<br/>
     OpULessThan, OpSLessThan<br/>
     OpUGreaterThan, OpSGreaterThan<br/>
     OpULessThanEqual, OpSLessThanEqual<br/>
     OpUGreaterThanEqual, OpSGreaterThanEqual<br/>
     <br/>
     If the Shader capability was declared, the following opcode is also valid:<br/>
     OpQuantizeToF16<br/>
     <br/>
     If the Kernel capability was declared, the following opcodes are also valid:<br/>
     OpConvertFToS, OpConvertSToF<br/>
     OpConvertFToU, OpConvertUToF<br/>
     OpUConvert<br/>
     OpConvertPtrToU, OpConvertUToPtr<br/>
     OpGenericCastToPtr, OpPtrCastToGeneric<br/>
     OpBitcast<br/>
     OpFNegate<br/>
     OpFAdd, OpFSub<br/>
     OpFMul, OpFDiv<br/>
     OpFRem, OpFMod<br/>
     OpAccessChain, OpInBoundsAccessChain<br/>
     OpPtrAccessChain, OpInBoundsPtrAccessChain<br/>
     <br/>
     Operands are the operands required by opcode, and satisfy the semantics of opcode. In addition, all Operands must be the ids of other constant instructions, or for the AccessChain named opcodes, their Base is allowed to be a global (module scope) OpVariable instruction.<br/>
     <br/>
     See Specialization.
     */
    void visitSpecConstantOp(long resultType, long resultID, long opcode, long[] operands);
}
