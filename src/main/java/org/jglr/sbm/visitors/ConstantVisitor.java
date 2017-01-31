package org.jglr.sbm.visitors;

import org.jglr.sbm.sampler.SamplerAddressingMode;
import org.jglr.sbm.sampler.SamplerFilterMode;

public interface ConstantVisitor {

    void visitTrueConstant(long type, long resultID);

    void visitFalseConstant(long type, long resultID);

    void visitConstant(long type, long resultID, long[] bitPattern);

    void visitConstantComposite(long resultType, long resultID, long[] constituents);

    void visitConstantSampler(long resultType, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter);

    void visitConstantNull(long resultType, long resultID);

    void visitSpecConstantBool(long resultType, long resultID, boolean defaultValue);

    void visitSpecConstant(long resultType, long resultID, long[] value);

    void visitSpecConstantComposite(long resultType, long resultID, long[] constituents);

    void visitSpecConstantOp(long resultType, long resultID, long opcode, long[] operands);
}
