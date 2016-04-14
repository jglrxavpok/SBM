package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.types.Type;

public interface SBMDecorationVisitor {

    /**
     * <b>Note</B>: Compressed because the related decorations have the same physical layout and differentiation must be done by the end-user
    */
    void visitIntDecoration(Decoration decoration, long target, long value);

    void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute);

    void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode);
    void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode);
    void visitLinkageAttributesDecoration(long target, String name, LinkageType type);

    /**
     * Called when the decoration did not fit inside any of the previous methods, ie when it is just a Decoration with a target and nothing more
     * @param target
     * @param decoration
     */
    void visitDecoration(long target, Decoration decoration);

    void visitIntMemberDecoration(Decoration decoration, Type structureType, long member, long value);

    void visitFunctionParameterAttributeMemberDecoration(Type structureType, long member, FunctionParameterAttribute attribute);

    void visitFPRoundingModeMemberDecoration(Type structureType, long member, FPRoundingMode roundingMode);

    void visitFPFastMathModeMemberDecoration(Type structureType, long member, FPFastMathMode mathMode);

    void visitLinkageAttributesMemberDecoration(Type structureType, long member, String name, LinkageType linkageType);

    void visitMemberDecoration(Type structureType, long member, Decoration decoration);
}
