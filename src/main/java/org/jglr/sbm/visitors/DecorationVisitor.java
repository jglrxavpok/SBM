package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;

public interface DecorationVisitor {

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

    void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value);

    void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute);

    void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode);

    void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode);

    void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType);

    void visitMemberDecoration(long structureType, long member, Decoration decoration);

    void visitDecorationGroup(long resultID);

    void visitGroupDecoration(long decorationGroup, long[] targets);

    void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets);
}
