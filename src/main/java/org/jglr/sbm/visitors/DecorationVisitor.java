package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;

public interface DecorationVisitor {

    /**
     * <b>Note</B>: Compressed because the related decorations have the same physical layout and differentiation must be done by the end-user<br/>
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitIntDecoration(Decoration decoration, long target, long value);

    /**
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute);

    /**
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode);

    /**
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode);

    /**
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitLinkageAttributesDecoration(long target, String name, LinkageType type);

    /**
     * Add a Decoration to another id.<br/>
     <br/>
     Target is the id to decorate. It can potentially be any id that is a forward reference. A set of decorations can be grouped together by having multiple OpDecorate instructions target the same OpDecorationGroup instruction.
     */
    void visitDecoration(long target, Decoration decoration);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType);

    /**
     * Add a Decoration to a member of a structure type.<br/>
     <br/>
     Structure type is the id of a type from OpTypeStruct.<br/>
     <br/>
     Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …<br/>
     <br/>
     Note: See OpDecorate for creating groups of decorations for consumption by OpGroupMemberDecorate
     */
    void visitMemberDecoration(long structureType, long member, Decoration decoration);

    /**
     * A collector for Decorations from OpDecorate instructions. All such OpDecorate instructions targeting this OpDecorationGroup instruction must precede it. Subsequent OpGroupDecorate and OpGroupMemberDecorate instructions that consume this instruction’s Result id will apply these decorations to their targets.
     */
    void visitDecorationGroup(long resultID);

    /**
     * Add a group of Decorations to another id.<br/>
     <br/>
     Decoration Group is the id of an OpDecorationGroup instruction.<br/>
     <br/>
     Targets is a list of ids to decorate with the groups of decorations.
     */
    void visitGroupDecoration(long decorationGroup, long[] targets);

    /**
     * Add a group of Decorations to members of structure types.<br/>
     <br/>
     Decoration Group is the id of an OpDecorationGroup instruction.<br/>
     <br/>
     Targets is a list of (id, Member) pairs to decorate with the groups of decorations. Each id in the pair must be a target structure type, and the associated Member is the number of the member to decorate in the type. The first member is member 0, the next is member 1, …
     */
    void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets);
}
