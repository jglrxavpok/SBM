package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.sampler.ImageOperands;
import org.jglr.sbm.sampler.SamplerAddressingMode;
import org.jglr.sbm.sampler.SamplerFilterMode;

import java.util.Map;

/**
 * Documentation for the instructions copied from <a href="https://www.khronos.org/registry/spir-v/specs/1.1/SPIRV.html">the SPIR-V Specification </a>
 */
public interface CodeVisitor extends TypeVisitor, DecorationVisitor, ConstantVisitor, FunctionVisitor, MemoryVisitor {

    /**
     *
     Document what source language and text this module was translated from. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     Version is the version of the source language. This literal operand is limited to a single word.<br/>
     <br/>
     File is an OpString instruction and is the source-level file name.<br/>
     <br/>
     Source is the text of the source-level file.<br/>
     <br/>
     Each client API describes what form the Version operand takes, per source language.
     */
    void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode);

    /**
        Continue specifying the Source text from the previous instruction. This has no semantic impact and can safely be removed from a module.<br/>
        <br/>
        Continued Source is a continuation of the source text in the previous Source.<br/>
        <br/>
        The previous instruction must be an OpSource or an OpSourceContinued instruction. As is true for all literal strings, the previous instruction’s string was nul terminated. That terminating 0 word from the previous instruction is not part of the source text; the first character of Continued Source logically immediately follows the last character of Source before its nul.
     */
    void visitSourceContinued(String source);

    /**
     * Document an extension to the source language. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     Extension is a string describing a source-language extension. Its form is dependent on the how the source language describes extensions.
     */
    void visitSourceExtension(String source);

    /**
     * Add source-level location information. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     This location information applies to the instructions physically following this instruction, up to the first occurrence of any of the following: the next end of block, the next OpLine instruction, or the next OpNoLine instruction.<br/>
     <br/>
     File must be an OpString instruction and is the source-level file name.<br/>
     <br/>
     Line is the source-level line number. This literal operand is limited to a single word.<br/>
     <br/>
     Column is the source-level column number. This literal operand is limited to a single word.<br/>
     <br/>
     OpLine can generally immediately precede other instructions, with the following exceptions:<br/>
     <br/>
     - it may not be used until after the annotation instructions,<br/>
     (see the Logical Layout section)<br/>
     <br/>
     - cannot be the last instruction in a block, which is defined to end with a branch instruction<br/>
     <br/>
     - if a branch merge instruction is used, the last OpLine in the block must be before its merge instruction
     */
    void visitLine(long filenameID, long line, long column);

    /**
     * Assign a name string to another instruction’s Result id. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     Target is the Result id to assign a name to. It can be the Result id of any other instruction; a variable, function, type, intermediate result, etc.<br/>
     <br/>
     Name is the string to assign.
     */
    void visitName(long target, String name);

    /**
     * Assign a name string to a member of a structure type. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     Type is the id from an OpTypeStruct instruction.<br/>
     <br/>
     Member is the number of the member to assign in the structure. The first member is member 0, the next is member 1, … This literal operand is limited to a single word.<br/>
     <br/>
     Name is the string to assign to the member.
     */
    void visitMemberName(long structureType, long target, String name);

    /**
     * Assign a Result id to a string for use by other debug instructions (see OpLine and OpSource). This has no semantic impact and can safely be removed from a module. (Removal also requires removal of all instructions referencing Result id.)<br/>
     <br/>
     String is the literal string being assigned a Result id.
     */
    void visitString(long resultID, String value);

    /**
     * Declare a capability used by this module.<br/>
     <br/>
     Capability is the capability declared by this instruction. There are no restrictions on the order in which capabilities are declared.
     */
    void visitCapability(Capability cap);

    /**
     * Import an extended set of instructions. It can be later referenced by the Result id.<br/>
     <br/>
     Name is the extended instruction-set’s name string. There must be an external specification defining the semantics for this extended instruction set.<br/>
     <br/>
     See Extended Instruction Sets for more information.
     */
    void visitExtendedInstructionSetImport(long resultID, String name);

    /**
     * Execute an instruction in an imported set of extended instructions.<br/>
     <br/>
     Result Type is as defined, per Instruction, in the external specification for Set.<br/>
     <br/>
     Set is the result of an OpExtInstImport instruction.<br/>
     <br/>
     Instruction is the enumerant of the instruction to execute within Set. This literal operand is limited to a single word. The semantics of the instruction must be defined in the external specification for Set.<br/>
     <br/>
     Operand 1, … are the operands to the extended instruction.
     */
    void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands);

    /**
     * Declare an entry point and its execution model.<br/>
     <br/>
     Execution Model is the execution model for the entry point and its static call tree. See Execution Model.<br/>
     <br/>
     Entry Point must be the Result id of an OpFunction instruction.<br/>
     <br/>
     Name is a name string for the entry point. A module cannot have two OpEntryPoint instructions with the same Execution Model and the same Name string.<br/>
     <br/>
     Interface is a list of id of global OpVariable instructions with either Input or Output for its Storage Class operand. These declare the input/output interface of the entry point. They could be a subset of the input/output declarations of the module, and a superset of those referenced by the entry point’s static call tree. It is invalid for the entry point’s static call tree to reference such an id if it was not listed with this instruction.<br/>
     <br/>
     Interface id are forward references. They allow declaration of all variables forming an interface for an entry point, whether or not all the variables are actually used by the entry point.
     */
    void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces);

    /**
     * The block label instruction: Any reference to a block is through the Result id of its label.<br/>
     <br/>
     Must be the first instruction of any block, and appears only as the first instruction of a block.
     */
    void visitLabel(long resultID);

    /**
     * Declare use of an extension to SPIR-V. This allows validation of additional instructions, tokens, semantics, etc.<br/>
     <br/>
     Name is the extension’s name string.
     */
    void visitExtension(String extension);

    /**
     * Return with no value from a function with void return type.<br/>
     <br/>
     This instruction must be the last instruction in a block.
     */
    void visitReturn();

    /**
     * End of the visit, tells the visitor that it can free up resources, for instance
     */
    void visitEnd();

    void reset();

    /**
     * Make an intermediate object whose value is undefined.<br/>
     <br/>
     Result Type is the type of object to make.<br/>
     <br/>
     Each consumption of Result id yields an arbitrary, possibly different bit pattern.
     */
    void visitUndef(long resultType, long resultID);

    /**
     * Declare an execution mode for an entry point.<br/>
     <br/>
     Entry Point must be the Entry Point id operand of an OpEntryPoint instruction.<br/>
     <br/>
     Mode is the execution mode.
     */
    void visitExecutionMode(long entryPoint, ExecutionMode mode);

    /**
     * Sample an image with an implicit level of detail.<br/>
     <br/>
     Result Type must be a vector of four components of floating-point type or integer type. Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).<br/>
     <br/>
     Sampled Image must be an object whose type is OpTypeSampledImage.<br/>
     <br/>
     Coordinate must be a scalar or vector of floating-point type. It contains (u[, v] … [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.<br/>
     <br/>
     Image Operands encodes what operands follow, as per Image Operands.<br/>
     <br/>
     This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
     */
    void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands);

    /**
     * Fragment-shader discard.<br/>
     <br/>
     Ceases all further processing in any invocation that executes it: Only instructions these invocations executed before OpKill will have observable side effects. If this instruction is executed in non-uniform control flow, all subsequent control flow is non-uniform (for invocations that continue to execute).<br/>
     <br/>
     This instruction must be the last instruction in a block.<br/>
     <br/>
     This instruction is only valid in the Fragment Execution Model.
     */
    void visitKill();

    /**
     * Return a value from a function.<br/>
     <br/>
     Value is the value returned, by copy, and must match the Return Type operand of the OpTypeFunction type of the OpFunction body this return instruction is in.<br/>
     <br/>
     This instruction must be the last instruction in a block.
     */
    void visitReturnValue(long valueID);

    /**
     * Discontinue any source-level location information that might be active from a previous OpLine instruction. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     This instruction can only appear after the annotation instructions (see the Logical Layout section). It cannot be the last instruction in a block, or the second-to-last instruction if the block has a merge instruction. There is not a requirement that there is a preceding OpLine instruction.
     */
    void visitNoLine();

    /**
     * Document a process that was applied to a module. This has no semantic impact and can safely be removed from a module.<br/>
     <br/>
     Process is a string describing a process and/or tool (processor) that did the processing. Its form is dependent on the processor.
     */
    void visitModuleProcessed(String process);

    /**
     * Construct a new composite object from a set of constituent objects that will fully form it.<br/>
     <br/>
     Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the operands, with one exception. The exception is that for constructing a vector, the operands may also be vectors with the same component type as the Result Type component type. When constructing a vector, the total number of components in all the operands must equal the number of components in Result Type.<br/>
     <br/>
     Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result, with one exception. The exception is that for constructing a vector, a contiguous subset of the scalars consumed can be represented by a vector operand instead. The Constituents must appear in the order needed by the definition of the type of the result. When constructing a vector, there must be at least two Constituent operands.
     */
    void visitCompositeConstruct(long resultType, long resultID, long... constituents);

    /**
     * Signed-integer subtract of Operand from zero.<br/>
     <br/>
     Result Type must be a scalar or vector of integer type. <br/>
     <br/>
     Operand’s type must be a scalar or vector of integer type. It must have the same number of components as Result Type. The component width must equal the component width in Result Type. <br/>
     <br/>
     Results are computed per component
     */
    void visitSNegate(long resultTypeID, long resultID, long operandID);

    /**
     * Floating-point subtract of Operand from zero.<br/>
     <br/>
     Result Type must be a scalar or vector of floating-point type. <br/>
     <br/>
     The type of Operand must be the same as Result Type. <br/>
     <br/>
     Results are computed per component.
     */
    void visitFNegate(long resultTypeID, long resultID, long operandID);

    /**
     * Integer addition of Operand 1 and Operand 2.<br/>
     <br/>
     Result Type must be a scalar or vector of integer type. <br/>
     <br/>
     The type of Operand 1 and Operand 2 must be a scalar or vector of integer type. They must have the same number of components as Result Type. They must have the same component width as Result Type. <br/>
     <br/>
     Results are computed per component.
     */
    void visitIAdd(long resultTypeID, long resultID, long leftID, long rightID);

    /**
     * Floating-point addition of Operand 1 and Operand 2.<br/>
     <br/>
     Result Type must be a scalar or vector of floating-point type. <br/>
     <br/>
     The types of Operand 1 and Operand 2 both must be the same as Result Type. <br/>
     <br/>
     Results are computed per component.
     */
    void visitFAdd(long resultTypeID, long resultID, long leftID, long rightID);

    void visitISub(long resultType, long resultID, long leftID, long rightID);
    void visitFSub(long resultType, long resultID, long leftID, long rightID);
    void visitIMul(long resultType, long resultID, long leftID, long rightID);
    void visitFMul(long resultType, long resultID, long leftID, long rightID);
    void visitUDiv(long resultType, long resultID, long leftID, long rightID);
    void visitSDiv(long resultType, long resultID, long leftID, long rightID);
    void visitFDiv(long resultType, long resultID, long leftID, long rightID);
    void visitUMod(long resultType, long resultID, long leftID, long rightID);
    void visitSRem(long resultType, long resultID, long leftID, long rightID);
    void visitSMod(long resultType, long resultID, long leftID, long rightID);
    void visitFRem(long resultType, long resultID, long leftID, long rightID);
    void visitFMod(long resultType, long resultID, long leftID, long rightID);

    /**
     * Extract a part of a composite object. <br/>
     <br/>
     Result Type must be the type of object selected by the last provided index. The instruction result is the extracted object.<br/>
     <br/>
     Composite is the composite to extract from.<br/>
     <br/>
     Indexes walk the type hierarchy, potentially down to component granularity, to select the part to extract. All indexes must be in bounds. All composite constituents use zero-based numbering, as described by their OpType… instruction.
     */
    void visitCompositeExtract(long resultTypeID, long resultID, long compositeID, long[] indexes);
}
