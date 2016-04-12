package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.types.Type;

public interface SBMDecorationVisitor {

    /**
     * <b>Note</B>: Compressed because the related decorations have the same physical layout and differentiation must be done by the end-user
    */
    void visitIntDecoration(Decoration decoration, int target, int value);

    void visitFunctionParameterAttributeDecoration(int target, FunctionParameterAttribute attribute);

    void visitFPRoundingModeDecoration(int target, FPRoundingMode roundingMode);
    void visitFPFastMathModeDecoration(int target, FPFastMathMode fastMathMode);
    void visitLinkageAttributesDecoration(int target, String name, LinkageType type);

    /**
     * Called when the decoration did not fit inside any of the previous methods, ie when it is just a Decoration with a target and nothing more
     * @param target
     * @param decoration
     */
    void visitDecoration(int target, Decoration decoration);

    void visitIntMemberDecoration(Decoration decoration, Type structureType, int member, int value);

    void visitFunctionParameterAttributeMemberDecoration(Type structureType, int member, FunctionParameterAttribute attribute);

    void visitFPRoundingModeMemberDecoration(Type structureType, int member, FPRoundingMode roundingMode);

    void visitFPFastMathModeMemberDecoration(Type structureType, int member, FPFastMathMode mathMode);

    void visitLinkageAttributesMemberDecoration(Type structureType, int member, String name, LinkageType linkageType);

    void visitMemberDecoration(Type structureType, int member, Decoration decoration);
}
