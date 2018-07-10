// Auto-generated from org.jglr.sbm.generation.SPIRVCodeVisitorGenerator on Tue Jul 10 17:21:22 CEST 2018
package org.jglr.sbm.visitors;

import org.jglr.flows.io.ByteArray;
import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.Map;

/**
Generated from spirv-opcodesonly.json
*/

public interface CodeVisitor extends TypeVisitor, DecorationVisitor, ConstantVisitor, FunctionVisitor, MemoryVisitor {
    
    
    
    void reset();
    
    
    void visitEnd();
    
    
    /**
    OpNop
    <br/>
    <br/> This has no semantic impact and can safely be removed from a module.
    */
    void visitNop();
    
    
    /**
    OpUndef
    <br/>
    <br/>Make an intermediate object whose value is undefined.
    <br/>
    <br/>Result Type is the type of object to make.
    <br/>
    <br/>Each consumption of Result &lt;id&gt; yields an arbitrary, possibly different bit pattern or abstract value resulting in possibly different concrete, abstract, or opaque values.
    */
    void visitUndef(long resultType, long result);
    
    
    /**
    OpSizeOf
    <br/>
    <br/>Computes the run-time size of the type pointed to by Pointer
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pointer must point to a concrete type.
    */
    void visitSizeOf(long resultType, long result, long pointer);
    
    
    /**
    OpFragmentMaskFetchAMD
    <br/>
    <br/>TBD
    */
    void visitFragmentMaskFetchAMD(long resultType, long result, long image, long coordinate);
    
    
    /**
    OpFragmentFetchAMD
    <br/>
    <br/>TBD
    */
    void visitFragmentFetchAMD(long resultType, long result, long image, long coordinate, long fragmentIndex);
    
    
    /**
    OpDecorateStringGOOGLE
    <br/>
    <br/>TBD
    */
    void visitDecorateStringGOOGLE(long target, long decoration);
    
    
    /**
    OpMemberDecorateStringGOOGLE
    <br/>
    <br/>TBD
    */
    void visitMemberDecorateStringGOOGLE(long structType, long member, long decoration);
    
    
    /**
    OpSourceContinued
    <br/>
    <br/>Continue specifying the Source text from the previous instruction. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Continued Source is a continuation of the source text in the previous Source.
    <br/>
    <br/>The previous instruction must be an OpSource or an OpSourceContinued instruction. As is true for all literal strings, the previous instruction&#8217;s string was nul terminated. That terminating 0 word from the previous instruction is not part of the source text; the first character of Continued Source logically immediately follows the last character of Source before its nul.
    */
    void visitSourceContinued(String source);
    
    
    /**
    OpSource
    <br/>
    <br/>Document what source language and text this module was translated from. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Version is the version of the source language. This literal operand is limited to a single word.
    <br/>
    <br/>File is an OpString instruction and is the source-level file name.
    <br/>
    <br/>Source is the text of the source-level file.
    <br/>
    <br/>Each client API describes what form the Version operand takes, per source language.
    */
    void visitSource(SourceLanguage sourceLanguage, long version, long optionalLong, String optionalString);
    
    
    /**
    OpSourceExtension
    <br/>
    <br/>Document an extension to the source language. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Extension is a string describing a source-language extension. Its form is dependent on the how the source language describes extensions.
    */
    void visitSourceExtension(String extension);
    
    
    /**
    OpName
    <br/>
    <br/>Assign a name string to another instruction&#8217;s Result &lt;id&gt;. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Target is the Result &lt;id&gt; to assign a name to. It can be the Result &lt;id&gt; of any other instruction; a variable, function, type, intermediate result, etc.
    <br/>
    <br/>Name is the string to assign.
    */
    void visitName(long target, String name);
    
    
    /**
    OpMemberName
    <br/>
    <br/>Assign a name string to a member of a structure type. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Type is the &lt;id&gt; from an OpTypeStruct instruction.
    <br/>
    <br/>Member is the number of the member to assign in the structure. The first member is member 0, the next is member 1, &#8230; This literal operand is limited to a single word.
    <br/>
    <br/>Name is the string to assign to the member.
    */
    void visitMemberName(long type, long member, String name);
    
    
    /**
    OpString
    <br/>
    <br/>Assign a Result &lt;id&gt; to a string for use by other debug instructions (see OpLine and OpSource). This has no semantic impact and can safely be removed from a module. (Removal also requires removal of all instructions referencing Result &lt;id&gt;.)
    <br/>
    <br/>String is the literal string being assigned a Result &lt;id&gt;.
    */
    void visitString(long result, String string);
    
    
    /**
    OpLine
    <br/>
    <br/>Add source-level location information. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/> This location information applies to the instructions physically following this instruction, up to the first occurrence of any of the following: the next end of block, the next OpLine instruction, or the next OpNoLine instruction.
    <br/>
    <br/>File must be an OpString instruction and is the source-level file name.
    <br/>
    <br/>Line is the source-level line number. This literal operand is limited to a single word.
    <br/>
    <br/>Column is the source-level column number. This literal operand is limited to a single word.
    <br/>
    <br/>OpLine can generally immediately precede other instructions, with the following exceptions:
    <br/>
    <br/> - it may not be used until after the annotation instructions,
    <br/>   (see the Logical Layout section)
    <br/>
    <br/> - cannot be the last instruction in a block, which is defined to end with a termination instruction
    <br/>
    <br/> - if a branch merge instruction is used, the last OpLine in the block must be before its merge instruction
    */
    void visitLine(long file, long line, long column);
    
    
    /**
    OpNoLine
    <br/>
    <br/>Discontinue any source-level location information that might be active from a previous OpLine instruction. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>This instruction can only appear after the annotation instructions (see the Logical Layout section). It cannot be the last instruction in a block, or the second-to-last instruction if the block has a merge instruction. There is not a requirement that there is a preceding OpLine instruction.
    */
    void visitNoLine();
    
    
    /**
    OpModuleProcessed
    <br/>
    <br/>Document a process that was applied to a module. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Process is a string describing a process and/or tool (processor) that did the processing. Its form is dependent on the processor.
    */
    void visitModuleProcessed(String process);
    
    
    /**
    OpExtension
    <br/>
    <br/>Declare use of an extension to SPIR-V. This allows validation of additional instructions, tokens, semantics, etc.
    <br/>
    <br/>Name is the extension&#8217;s name string.
    */
    void visitExtension(String name);
    
    
    /**
    OpExtInstImport
    <br/>
    <br/>Import an extended set of instructions. It can be later referenced by the Result &lt;id&gt;.
    <br/>
    <br/>Name is the extended instruction-set&#8217;s name string. There must be an external specification defining the semantics for this extended instruction set.
    <br/>
    <br/>See Extended Instruction Sets for more information.
    */
    void visitExtendedInstructionSetImport(long result, String name);
    
    
    /**
    OpExtInst
    <br/>
    <br/>Execute an instruction in an imported set of extended instructions.
    <br/>
    <br/>Result Type is as defined, per Instruction, in the external specification for Set.
    <br/>
    <br/>Set is the result of an OpExtInstImport instruction.
    <br/>
    <br/>Instruction is the enumerant of the instruction to execute within Set. This literal operand is limited to a single word. The semantics of the instruction must be defined in the external specification for Set.
    <br/>
    <br/>Operand 1, &#8230; are the operands to the extended instruction.
    */
    void visitExecExtendedInstruction(long resultType, long result, long set, long instruction, long[] operands);
    
    
    /**
    OpMemoryModel
    <br/>
    <br/>Set addressing model and memory model for the entire module.
    <br/>
    <br/>Addressing Model selects the module&#8217;s Addressing Model.
    <br/>
    <br/>Memory Model selects the module&#8217;s memory model, see Memory Model.
    */
    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);
    
    
    /**
    OpEntryPoint
    <br/>
    <br/>Declare an entry point and its execution model.
    <br/>
    <br/>Execution Model is the execution model for the entry point and its static call tree.  See Execution Model.
    <br/>
    <br/>Entry Point must be the Result &lt;id&gt; of an OpFunction instruction.
    <br/>
    <br/>Name is a name string for the entry point. A module cannot have two OpEntryPoint instructions with the same Execution Model and the same Name string.
    <br/>
    <br/>Interface is a list of &lt;id&gt; of global OpVariable instructions with either Input or Output for its Storage Class operand. These declare the input/output interface of the entry point.  They could be a subset of the input/output declarations of the module, and a superset of those referenced by the entry point&#8217;s static call tree. It is invalid for the entry point&#8217;s static call tree to reference such an &lt;id&gt; if it was not listed with this instruction.
    <br/>
    <br/>Interface &lt;id&gt; are forward references.  They allow declaration of all variables forming an interface for an entry point, whether or not all the variables are actually used by the entry point.
    */
    void visitEntryPoint(ExecutionModel executionModel, long entryPoint, String name, long[] interfaces);
    
    
    /**
    OpExecutionMode
    <br/>
    <br/>Declare an execution mode for an entry point.
    <br/>
    <br/>Entry Point must be the Entry Point &lt;id&gt; operand of an OpEntryPoint instruction.
    <br/>
    <br/>Mode is the execution mode. See Execution Mode.
    <br/>
    <br/>This instruction is only valid when the Mode operand is an execution mode that takes no Extra Operands, or takes Extra Operands that are not &lt;id&gt; operands.
    */
    void visitExecutionMode(long entryPoint, ExecutionMode mode);
    
    
    /**
    OpCapability
    <br/>
    <br/>Declare a capability used by this module.
    <br/>
    <br/>Capability is the capability declared by this instruction.  There are no restrictions on the order in which capabilities are declared.
    <br/>
    <br/>See the capabilities section for more detail.
    */
    void visitCapability(Capability capability);
    
    
    /**
    OpExecutionModeId
    <br/>
    <br/>Declare an execution mode for an entry point, using &lt;id&gt;s as Extra Operands.
    <br/>
    <br/>Entry Point must be the Entry Point &lt;id&gt; operand of an OpEntryPoint instruction.
    <br/>
    <br/>Mode is the execution mode. See Execution Mode.
    <br/>
    <br/>This instruction is only valid when the Mode operand is an execution mode that takes Extra Operands that are &lt;id&gt; operands. All such &lt;id&gt; Extra Operands must be constant instructions.
    */
    void visitExecutionModeId(long entryPoint, ExecutionMode mode, long[] sees);
    
    
    /**
    OpConstantTrue
    <br/>
    <br/>Declare a true Boolean-type scalar constant.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    */
    void visitConstantTrue(long resultType, long result);
    
    
    /**
    OpConstantFalse
    <br/>
    <br/>Declare a false Boolean-type scalar constant.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    */
    void visitConstantFalse(long resultType, long result);
    
    
    /**
    OpConstant
    <br/>
    <br/>Declare a new integer-type or floating-point-type scalar constant.
    <br/>
    <br/>Result Type must be a scalar integer type or floating-point type.
    <br/>
    <br/>Value is the bit pattern for the constant. Types 32 bits wide or smaller take one word. Larger types take multiple words, with low-order words appearing first.
    */
    void visitConstant(long resultType, long result, long[] value);
    
    
    /**
    OpConstantComposite
    <br/>
    <br/>Declare a new composite constant.
    <br/>
    <br/>Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the Constituents. The ordering must be the same between the top-level types in Result Type and the Constituents.
    <br/>
    <br/>Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result. The Constituents must appear in the order needed by the definition of the Result Type. The Constituents must all be &lt;id&gt;s of other constant declarations or an OpUndef.
    */
    void visitConstantComposite(long resultType, long result, long[] constituents);
    
    
    /**
    OpConstantSampler
    <br/>
    <br/>Declare a new sampler constant.
    <br/>
    <br/>Result Type must be OpTypeSampler.
    <br/>
    <br/>Sampler Addressing Mode is the addressing mode; a literal from Sampler Addressing Mode.
    <br/>
    <br/>Param is one of:
    <br/>0: Non Normalized
    <br/>1: Normalized
    <br/>
    <br/>Sampler Filter Mode is the filter mode; a literal from Sampler Filter Mode.
    */
    void visitConstantSampler(long resultType, long result, SamplerAddressingMode samplerAddressingMode, boolean param, SamplerFilterMode samplerFilterMode);
    
    
    /**
    OpConstantNull
    <br/>
    <br/>Declare a new null constant value.
    <br/>
    <br/>The null value is type dependent, defined as follows:
    <br/> -  Scalar Boolean: false
    <br/> -  Scalar integer: 0
    <br/> -  Scalar floating point: +0.0 (all bits 0)
    <br/> -  All other scalars: Abstract
    <br/> -  Composites: Members are set recursively to the null constant according to the null value of their constituent types.
    <br/>
    <br/>Result Type must be one of the following types:
    <br/> -  Scalar or vector Boolean type
    <br/> -  Scalar or vector integer type
    <br/> -  Scalar or vector floating-point type
    <br/> -  Pointer type
    <br/> -  Event type
    <br/> -  Device side event type
    <br/> -  Reservation id type
    <br/> -  Queue type
    <br/> -  Composite type
    */
    void visitConstantNull(long resultType, long result);
    
    
    /**
    OpSpecConstantTrue
    <br/>
    <br/>Declare a Boolean-type scalar specialization constant with a default value of true.
    <br/>
    <br/>This instruction can be specialized to become either an OpConstantTrue or OpConstantFalse instruction.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    <br/>
    <br/>See Specialization.
    */
    void visitSpecConstantTrue(long resultType, long result);
    
    
    /**
    OpSpecConstantFalse
    <br/>
    <br/>Declare a Boolean-type scalar specialization constant with a default value of false.
    <br/>
    <br/>This instruction can be specialized to become either an OpConstantTrue or OpConstantFalse instruction.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    <br/>
    <br/>See Specialization.
    */
    void visitSpecConstantFalse(long resultType, long result);
    
    
    /**
    OpSpecConstant
    <br/>
    <br/>Declare a new integer-type or floating-point-type scalar specialization constant.
    <br/>
    <br/>Result Type must be a scalar integer type or floating-point type.
    <br/>
    <br/>Value is the bit pattern for the default value of the constant. Types 32 bits wide or smaller take one word. Larger types take multiple words, with low-order words appearing first.
    <br/>
    <br/>This instruction can be specialized to become an OpConstant instruction.
    <br/>
    <br/>See Specialization.
    */
    void visitSpecConstant(long resultType, long result, long[] value);
    
    
    /**
    OpSpecConstantComposite
    <br/>
    <br/>Declare a new composite specialization constant.
    <br/>
    <br/>Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the Constituents. The ordering must be the same between the top-level types in Result Type and the Constituents.
    <br/>
    <br/>Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result. The Constituents must appear in the order needed by the definition of the type of the result. The Constituents must be the &lt;id&gt; of other specialization constant or constant declarations.
    <br/>
    <br/>This instruction will be specialized to an OpConstantComposite instruction.
    <br/>
    <br/>See Specialization.
    */
    void visitSpecConstantComposite(long resultType, long result, long[] constituents);
    
    
    /**
    OpSpecConstantOp
    <br/>
    <br/>Declare a new specialization constant that results from doing an operation.
    <br/>
    <br/>Result Type must be the type required by the Result Type of Opcode.
    <br/>
    <br/>Opcode must be one of the following opcodes. This literal operand is limited to a single word.
    <br/>OpSConvert, OpFConvert
    <br/>OpSNegate, OpNot
    <br/>OpIAdd, OpISub
    <br/>OpIMul, OpUDiv, OpSDiv, OpUMod, OpSRem, OpSMod
    <br/>OpShiftRightLogical, OpShiftRightArithmetic, OpShiftLeftLogical
    <br/>OpBitwiseOr, OpBitwiseXor, OpBitwiseAnd
    <br/>OpVectorShuffle, OpCompositeExtract, OpCompositeInsert
    <br/>OpLogicalOr, OpLogicalAnd, OpLogicalNot,
    <br/>OpLogicalEqual, OpLogicalNotEqual
    <br/>OpSelect
    <br/>OpIEqual, OpINotEqual
    <br/>OpULessThan, OpSLessThan
    <br/>OpUGreaterThan, OpSGreaterThan
    <br/>OpULessThanEqual, OpSLessThanEqual
    <br/>OpUGreaterThanEqual, OpSGreaterThanEqual
    <br/>
    <br/>If the Shader capability was declared, the following opcode is also valid:
    <br/>OpQuantizeToF16
    <br/>
    <br/>If the Kernel capability was declared, the following opcodes are also valid:
    <br/>OpConvertFToS, OpConvertSToF
    <br/>OpConvertFToU, OpConvertUToF
    <br/>OpUConvert
    <br/>OpConvertPtrToU, OpConvertUToPtr
    <br/>OpGenericCastToPtr, OpPtrCastToGeneric
    <br/>OpBitcast
    <br/>OpFNegate
    <br/>OpFAdd, OpFSub
    <br/>OpFMul, OpFDiv
    <br/>OpFRem, OpFMod
    <br/>OpAccessChain, OpInBoundsAccessChain
    <br/>OpPtrAccessChain, OpInBoundsPtrAccessChain
    <br/>
    <br/>Operands are the operands required by opcode, and satisfy the semantics of opcode. In addition, all Operands must be either:
    <br/> - the &lt;id&gt;s of other constant instructions, or
    <br/> - OpUndef, when allowed by opcode, or
    <br/> - for the AccessChain named opcodes, their Base is allowed to be a global (module scope) OpVariable instruction.
    <br/>
    <br/>See Specialization.
    */
    void visitSpecConstantOp(long resultType, long result, long opcode, long[] operands);
    
    
    /**
    OpSampledImage
    <br/>
    <br/>Create a sampled image, containing both a sampler and an image.
    <br/>
    <br/>Result Type must be the OpTypeSampledImage type whose Image Type operand is the type of Image.
    <br/>
    <br/>Image is an object whose type is an OpTypeImage, whose Sampled operand is 0 or 1, and whose Dim operand is not SubpassData.
    <br/>
    <br/>Sampler must be an object whose type is OpTypeSampler.
    */
    void visitSampledImage(long resultType, long result, long image, long sampler);
    
    
    /**
    OpImageSampleImplicitLod
    <br/>
    <br/>Sample an image with an implicit level of detail.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSampleImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSampleExplicitLod
    <br/>
    <br/>Sample an image using an explicit level of detail.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type or integer type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. Unless the Kernel capability is being used, it must be floating point. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSampleDrefImplicitLod
    <br/>
    <br/>Sample an image doing depth-comparison with an implicit level of detail.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSampleDrefExplicitLod
    <br/>
    <br/>Sample an image doing depth-comparison using an explicit level of detail.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSampleProjImplicitLod
    <br/>
    <br/>Sample an image with with a project coordinate and an implicit level of detail.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. The Dim operand of the underlying OpTypeImage must be 1D, 2D, 3D, or Rect,  and the Arrayed and MS operands must be 0.
    <br/>
    <br/>Coordinate is a floating-point vector containing (u [, v] [, w], q), as needed by the definition of Sampled Image, with the q component consumed for the projective division. That is, the actual sample coordinate will be (u/q [, v/q] [, w/q]), as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSampleProjExplicitLod
    <br/>
    <br/>Sample an image with a project coordinate using an explicit level of detail.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. The Dim operand of the underlying OpTypeImage must be 1D, 2D, 3D, or Rect,  and the Arrayed and MS operands must be 0.
    <br/>
    <br/>Coordinate is a floating-point vector containing (u [, v] [, w], q), as needed by the definition of Sampled Image, with the q component consumed for the projective division. That is, the actual sample coordinate will be (u/q [, v/q] [, w/q]), as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSampleProjDrefImplicitLod
    <br/>
    <br/>Sample an image with a project coordinate, doing depth-comparison, with an implicit level of detail.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. The Dim operand of the underlying OpTypeImage must be 1D, 2D, 3D, or Rect,  and the Arrayed and MS operands must be 0.
    <br/>
    <br/>Coordinate is a floating-point vector containing (u [, v] [, w], q), as needed by the definition of Sampled Image, with the q component consumed for the projective division. That is, the actual sample coordinate will be (u/q [, v/q] [, w/q]), as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref /q is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSampleProjDrefExplicitLod
    <br/>
    <br/>Sample an image with a project coordinate, doing depth-comparison, using an explicit level of detail.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. The Dim operand of the underlying OpTypeImage must be 1D, 2D, 3D, or Rect,  and the Arrayed and MS operands must be 0.
    <br/>
    <br/>Coordinate is a floating-point vector containing (u [, v] [, w], q), as needed by the definition of Sampled Image, with the q component consumed for the projective division. That is, the actual sample coordinate will be (u/q [, v/q] [, w/q]), as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref /q is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageFetch
    <br/>
    <br/>Fetch a single texel from an image whose Sampled operand is 1.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand cannot be Cube, and its Sampled operand must be 1.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageGather
    <br/>
    <br/>Gathers the requested component from four texels.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid). It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Component is the component number that will be gathered from all four texels. It must be 0, 1, 2 or 3.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageDrefGather
    <br/>
    <br/>Gathers the requested depth-comparison from four texels.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid). It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageRead
    <br/>
    <br/>Read a texel from an image without a sampler.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type or integer type.  Its component type must be the same as Sampled Type of the OpTypeImage (unless that Sampled Type is OpTypeVoid).
    <br/>
    <br/>Image must be an object whose type is OpTypeImage with a Sampled operand of 0 or 2. If the Sampled operand is 2, then some dimensions require a capability; e.g., one of Image1D, ImageRect, ImageBuffer, ImageCubeArray, or ImageMSArray.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing non-normalized texel coordinates (u[, v] &#8230; [, array layer]) as needed by the definition of Image. If the coordinates are outside the image, the memory location that is accessed is undefined.
    <br/>
    <br/> When the Image Dim operand is SubpassData, Coordinate is relative to the current fragment location. That is, the integer value (rounded down) of the current fragment&#8217;s window-relative (x, y) coordinate is added to (u, v).
    <br/>
    <br/> When the Image Dim operand is not SubpassData, the Image Format must not be Unknown, unless the StorageImageReadWithoutFormat Capability was declared.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageWrite
    <br/>
    <br/>Write a texel to an image without a sampler.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage with a Sampled operand of 0 or 2. If the Sampled operand is 2, then some dimensions require a capability; e.g., one of Image1D, ImageRect, ImageBuffer, ImageCubeArray, or ImageMSArray. Its Dim operand cannot be SubpassData.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing non-normalized texel coordinates (u[, v] &#8230; [, array layer]) as needed by the definition of Image. If the coordinates are outside the image, the memory location that is accessed is undefined.
    <br/>
    <br/>Texel is the data to write. Its component type must be the same as Sampled Type of the OpTypeImage (unless that Sampled Type is OpTypeVoid).
    <br/>
    <br/> The Image Format must not be Unknown, unless the StorageImageWriteWithoutFormat Capability was declared.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageWrite(long image, long coordinate, long texel, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImage
    <br/>
    <br/>Extract the image from a sampled image.
    <br/>
    <br/>Result Type must be OpTypeImage.
    <br/>
    <br/>Sampled Image must have type OpTypeSampledImage whose Image Type is the same as Result Type.
    */
    void visitImage(long resultType, long result, long sampledImage);
    
    
    /**
    OpImageQueryFormat
    <br/>
    <br/>Query the image format of an image created with an Unknown Image Format.
    <br/>
    <br/>Result Type must be a scalar integer type. The resulting value is an enumerant from Image Channel Data Type.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage.
    */
    void visitImageQueryFormat(long resultType, long result, long image);
    
    
    /**
    OpImageQueryOrder
    <br/>
    <br/>Query the channel order of an image created with an Unknown Image Format.
    <br/>
    <br/>Result Type must be a scalar integer type. The resulting value is an enumerant from Image Channel Order.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage.
    */
    void visitImageQueryOrder(long resultType, long result, long image);
    
    
    /**
    OpImageQuerySizeLod
    <br/>
    <br/>Query the dimensions of Image for mipmap level for Level of Detail.
    <br/>
    <br/>Result Type must be an integer type scalar or vector.  The number of components must be
    <br/>1 for 1D Dim,
    <br/>2 for 2D, and Cube Dimensionalities,
    <br/>3 for 3D Dim,
    <br/>plus 1 more if the image type is arrayed. This vector is filled in with (width [, height] [, depth] [, elements]) where elements is the number of layers in an image array, or the number of cubes in a cube-map array.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 1D, 2D, 3D, or Cube, and its MS must be 0. See OpImageQuerySize for querying image types without level of detail. See the client API for additional image type restrictions.
    <br/>
    <br/>Level of Detail is used to compute which mipmap level to query, as described in the API specification.
    */
    void visitImageQuerySizeLod(long resultType, long result, long image, long levelOfDetail);
    
    
    /**
    OpImageQuerySize
    <br/>
    <br/>Query the dimensions of Image, with no level of detail.
    <br/>
    <br/>Result Type must be an integer type scalar or vector.  The number of components must be
    <br/>1 for Buffer Dim,
    <br/>2 for 2D and Rect Dimensionalities,
    <br/>3 for 3D Dim,
    <br/>plus 1 more if the image type is arrayed. This vector is filled in with (width [, height] [, elements]) where elements is the number of layers in an image array.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of Rect or Buffer, or if its MS is 1, it can be 2D, or, if its Sampled Type is 0 or 2, it can be 2D or 3D. It cannot be an image with level of detail; there is no implicit level-of-detail consumed by this instruction. See OpImageQuerySizeLod for querying images having level of detail.
    */
    void visitImageQuerySize(long resultType, long result, long image);
    
    
    /**
    OpImageQueryLod
    <br/>
    <br/>Query the mipmap level and the level of detail for a hypothetical sampling of Image at Coordinate using an implicit level of detail.
    <br/>
    <br/>Result Type must be a two-component floating-point type vector.
    <br/>The first component of the result will contain the mipmap array layer.
    <br/>The second component of the result will contain the implicit level of detail relative to the base level.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its Dim operand must be one of 1D, 2D, 3D, or Cube.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type or integer type.  It contains (u[, v] &#8230; ) as needed by the definition of Sampled Image, not including any array layer index. Unless the Kernel capability is being used, it must be floating point.
    <br/>
    <br/>If called on an incomplete image, the results are undefined.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageQueryLod(long resultType, long result, long sampledImage, long coordinate);
    
    
    /**
    OpImageQueryLevels
    <br/>
    <br/>Query the number of mipmap levels accessible through Image.
    <br/>
    <br/>Result Type must be a scalar integer type. The result is the number of mipmap levels,as defined by the API specification.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 1D, 2D, 3D, or Cube. See the client API for additional image type restrictions.
    */
    void visitImageQueryLevels(long resultType, long result, long image);
    
    
    /**
    OpImageQuerySamples
    <br/>
    <br/>Query the number of samples available per texel fetch in a multisample image.
    <br/>
    <br/>Result Type must be a scalar integer type. The result is the number of samples.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 2D and MS of 1.
    */
    void visitImageQuerySamples(long resultType, long result, long image);
    
    
    /**
    OpImageSparseSampleImplicitLod
    <br/>
    <br/>Sample a sparse image with an implicit level of detail.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSparseSampleImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseSampleExplicitLod
    <br/>
    <br/>Sample a sparse image using an explicit level of detail.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type or integer type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. Unless the Kernel capability is being used, it must be floating point. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSparseSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSparseSampleDrefImplicitLod
    <br/>
    <br/>Sample a sparse image doing depth-comparison with an implicit level of detail.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model. In addition, it consumes an implicit derivative that can be affected by code motion.
    */
    void visitImageSparseSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseSampleDrefExplicitLod
    <br/>
    <br/>Sample a sparse image doing depth-comparison using an explicit level of detail.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image. It may be a vector larger than needed, but all unused components will appear after all used components.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands. At least one operand setting the level of detail must be present.
    */
    void visitImageSparseSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSparseSampleProjImplicitLod
    <br/>
    <br/>Sample a sparse image with a projective coordinate and an implicit level of detail.
    */
    void visitImageSparseSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseSampleProjExplicitLod
    <br/>
    <br/>Sample a sparse image with a projective coordinate using an explicit level of detail.
    */
    void visitImageSparseSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSparseSampleProjDrefImplicitLod
    <br/>
    <br/>Sample a sparse image with a projective coordinate, doing depth-comparison, with an implicit level of detail.
    */
    void visitImageSparseSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseSampleProjDrefExplicitLod
    <br/>
    <br/>Sample a sparse image with a projective coordinate, doing depth-comparison, using an explicit level of detail.
    */
    void visitImageSparseSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray);
    
    
    /**
    OpImageSparseFetch
    <br/>
    <br/>Fetch a single texel from a sampled sparse image.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand cannot be Cube.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageSparseFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseGather
    <br/>
    <br/>Gathers the requested component from four texels of a sparse image.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid). It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Component is the component number that will be gathered from all four texels. It must be 0, 1, 2 or 3.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageSparseGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseDrefGather
    <br/>
    <br/>Gathers the requested depth-comparison from four texels of a sparse image.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid). It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageSparseDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpImageSparseTexelsResident
    <br/>
    <br/>Translates a Resident Code into a Boolean. Result is false if any of the texels were in uncommitted texture memory, and true otherwise.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Resident Code is a value from an OpImageSparse&#8230; instruction that returns a resident code.
    */
    void visitImageSparseTexelsResident(long resultType, long result, long residentCode);
    
    
    /**
    OpImageSparseRead
    <br/>
    <br/>Read a texel from a sparse image without a sampler.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a scalar or vector of floating-point type or integer type.  Its component type must be the same as Sampled Type of the OpTypeImage (unless that Sampled Type is OpTypeVoid).
    <br/>
    <br/>Image must be an object whose type is OpTypeImage with a Sampled operand of 2.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing non-normalized texel coordinates (u[, v] &#8230; [, array layer]) as needed by the definition of Image. If the coordinates are outside the image, the memory location that is accessed is undefined.
    <br/>
    <br/>The Image Dim operand must not be SubpassData. The Image Format must not be Unknown unless the StorageImageReadWithoutFormat Capability was declared.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    void visitImageSparseRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands);
    
    
    /**
    OpConvertFToU
    <br/>
    <br/>Convert (value preserving) from floating point to unsigned integer, with round toward 0.0.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Float Value must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitConvertFToU(long resultType, long result, long floatValue);
    
    
    /**
    OpConvertFToS
    <br/>
    <br/>Convert (value preserving) from floating point to signed integer, with round toward 0.0.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/>Float Value must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitConvertFToS(long resultType, long result, long floatValue);
    
    
    /**
    OpConvertSToF
    <br/>
    <br/>Convert (value preserving) from signed integer to floating point.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Signed Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitConvertSToF(long resultType, long result, long signedValue);
    
    
    /**
    OpConvertUToF
    <br/>
    <br/>Convert (value preserving) from unsigned integer to floating point.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Unsigned Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitConvertUToF(long resultType, long result, long unsignedValue);
    
    
    /**
    OpUConvert
    <br/>
    <br/>Convert (value preserving) unsigned width. This is either a truncate or a zero extend.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Unsigned Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type.  The component width cannot equal the component width in Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitUConvert(long resultType, long result, long unsignedValue);
    
    
    /**
    OpSConvert
    <br/>
    <br/>Convert (value preserving) signed width.  This is either a truncate or a sign extend.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/>Signed Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type.  The component width cannot equal the component width in Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSConvert(long resultType, long result, long signedValue);
    
    
    /**
    OpFConvert
    <br/>
    <br/>Convert (value preserving) floating-point width.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Float Value must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.  The component width cannot equal the component width in Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFConvert(long resultType, long result, long floatValue);
    
    
    /**
    OpQuantizeToF16
    <br/>
    <br/>Quantize a floating-point value to what is expressible by a 16-bit floating-point value.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. The component width must be 32 bits.
    <br/>
    <br/>Value is the value to quantize. The type of Value must be the same as Result Type. 
    <br/>
    <br/> If Value is an infinity, the result is the same infinity. If Value is a NaN, the result is a NaN, but not necessarily the same NaN. If Value is positive with a magnitude too large to represent as a 16-bit floating-point value, the result is positive infinity. If Value is negative with a magnitude too large to represent as a 16-bit floating-point value, the result is negative infinity. If the magnitude of Value is too small to represent as a normalized 16-bit floating-point value, the result may be either +0 or -0.
    <br/>
    <br/>The RelaxedPrecision Decoration has no effect on this instruction.
    <br/>
    <br/> Results are computed per component.
    */
    void visitQuantizeToF16(long resultType, long result, long value);
    
    
    /**
    OpConvertPtrToU
    <br/>
    <br/>Convert a pointer to an unsigned integer type. A Result Type width larger than the width of Pointer will zero extend. A Result Type smaller than the width of Pointer will truncate. For same-width source and result, this is the same as OpBitcast. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0.
    */
    void visitConvertPtrToU(long resultType, long result, long pointer);
    
    
    /**
    OpSatConvertSToU
    <br/>
    <br/>Convert a signed integer to unsigned integer. Converted values outside the representable range of Result Type are clamped to the nearest representable value of Result Type.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/>Signed Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSatConvertSToU(long resultType, long result, long signedValue);
    
    
    /**
    OpSatConvertUToS
    <br/>
    <br/>Convert an unsigned integer to signed integer.  Converted values outside the representable range of Result Type are clamped to the nearest representable value of Result Type.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/>Unsigned Value must be a scalar or vector of integer type.  It must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSatConvertUToS(long resultType, long result, long unsignedValue);
    
    
    /**
    OpConvertUToPtr
    <br/>
    <br/>Convert an integer to pointer. A Result Type width smaller than the width of Integer Value pointer will truncate. A Result Type width larger than the width of Integer Value pointer will zero extend. 
    <br/>
    <br/> Result Type must be an OpTypePointer. For same-width source and result, this is the same as OpBitcast.
    */
    void visitConvertUToPtr(long resultType, long result, long integerValue);
    
    
    /**
    OpPtrCastToGeneric
    <br/>
    <br/>Convert a pointer&#8217;s Storage Class to Generic.
    <br/>
    <br/> Result Type must be an OpTypePointer. Its Storage Class must be Generic.
    <br/>
    <br/>Pointer must point to the Workgroup, CrossWorkgroup, or Function Storage Class.
    <br/>
    <br/>Result Type and Pointer must point to the same type.
    */
    void visitPtrCastToGeneric(long resultType, long result, long pointer);
    
    
    /**
    OpGenericCastToPtr
    <br/>
    <br/>Convert a pointer&#8217;s Storage Class to a non-Generic class.
    <br/>
    <br/> Result Type must be an OpTypePointer. Its Storage Class must be Workgroup, CrossWorkgroup, or Function.
    <br/>
    <br/>Pointer must point to the Generic Storage Class.
    <br/>
    <br/>Result Type and Pointer must point to the same type.
    */
    void visitGenericCastToPtr(long resultType, long result, long pointer);
    
    
    /**
    OpGenericCastToPtrExplicit
    <br/>
    <br/>Attempts to explicitly convert Pointer to Storage storage-class pointer value. 
    <br/>
    <br/> Result Type must be an OpTypePointer. Its Storage Class must be Storage.
    <br/>
    <br/>Pointer must have a type of OpTypePointer whose Type is the same as the Type of Result Type.Pointer must point to the Generic Storage Class. If the cast fails, the instruction result is an OpConstantNull pointer in the Storage Storage Class.
    <br/>
    <br/>Storage must be one of the following literal values from Storage Class: Workgroup, CrossWorkgroup, or Function.
    */
    void visitGenericCastToPtrExplicit(long resultType, long result, long pointer, StorageClass storage);
    
    
    /**
    OpBitcast
    <br/>
    <br/>Bit pattern-preserving type conversion.
    <br/>
    <br/>Result Type must be an OpTypePointer, or a scalar or vector of numerical-type.
    <br/>
    <br/>Operand must have a type of OpTypePointer, or a scalar or vector of numerical-type. It must be a different type than Result Type.
    <br/>
    <br/> If Result Type is a pointer, Operand must be a pointer or integer scalar. If Operand is a pointer, Result Type must be a pointer or integer scalar.
    <br/>
    <br/>If Result Type has the same number of components as Operand, they must also have the same component width, and results are computed per component.
    <br/>
    <br/>If Result Type has a different number of components than Operand, the total number of bits in Result Type must equal the total number of bits in Operand. Let L be the type, either Result Type or Operand&#8217;s type, that has the larger number of components. Let S be the other type, with the smaller number of components. The number of components in L must be an integer multiple of the number of components in S. The first component (that is, the only or lowest-numbered component) of S maps to the first components of L, and so on,  up to the last component of S mapping to the last components of L. Within this mapping, any single component of S (mapping to multiple components of L) maps its lower-ordered bits to the lower-numbered components of L.
    */
    void visitBitcast(long resultType, long result, long operand);
    
    
    /**
    OpVectorExtractDynamic
    <br/>
    <br/>Extract a single, dynamically selected, component of a vector.
    <br/>
    <br/>Result Type must be a scalar type.
    <br/>
    <br/>Vector must have a type OpTypeVector whose Component Type is Result Type.
    <br/>
    <br/>Index must be a scalar integer 0-based index of which component of Vector to extract.
    <br/>
    <br/>The value read is undefined if Index&#8217;s value is less than zero or greater than or equal to the number of components in Vector.
    */
    void visitVectorExtractDynamic(long resultType, long result, long vector, long index);
    
    
    /**
    OpVectorInsertDynamic
    <br/>
    <br/>Make a copy of a vector, with a single, variably selected, component modified.
    <br/>
    <br/>Result Type must be an OpTypeVector.
    <br/>
    <br/>Vector must have the same type as Result Type and is the vector that the non-written components will be copied from.
    <br/>
    <br/>Component is the value that will be supplied for the component selected by Index. It must have the same type as the type of components in Result Type.
    <br/>
    <br/>Index must be a scalar integer 0-based index of which component to modify.
    <br/>
    <br/>What is written is undefined if Index&#8217;s value is less than zero or greater than or equal to the number of components in Vector.
    */
    void visitVectorInsertDynamic(long resultType, long result, long vector, long component, long index);
    
    
    /**
    OpVectorShuffle
    <br/>
    <br/>Select arbitrary components from two vectors to make a new vector.
    <br/>
    <br/>Result Type must be an OpTypeVector. The number of components in Result Type must be the same as the number of Component operands.
    <br/>
    <br/>Vector 1 and Vector 2 must both have vector types, with the same Component Type as Result Type. They do not have to have the same number of components as Result Type or with each other. They are logically concatenated, forming a single vector with Vector 1&#8217;s components appearing before Vector 2&#8217;s. The components of this logical vector are logically numbered with a single consecutive set of numbers from 0 to N - 1, where N is the total number of components.
    <br/>
    <br/>Components are these logical numbers (see above), selecting which of the logically numbered components form the result. They can select the components in any order and can repeat components. The first component of the result is selected by the first Component operand,  the second component of the result is selected by the second Component operand, etc. A Component literal may also be FFFFFFFF, which means the corresponding result component has no source and is undefined. All Component literals must either be FFFFFFFF or in [0, N - 1] (inclusive).
    <br/>
    <br/>Note: A vector &#8220;swizzle&#8221; can be done by using the vector for both Vector operands, or using an OpUndef for one of the Vector operands.
    */
    void visitVectorShuffle(long resultType, long result, long vector1, long vector2, long[] components);
    
    
    /**
    OpCompositeConstruct
    <br/>
    <br/>Construct a new composite object from a set of constituent objects that will fully form it.
    <br/>
    <br/>Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the operands, with one exception. The exception is that for constructing a vector, the operands may also be vectors with the same component type as the Result Type component type. When constructing a vector, the total number of components in all the operands must equal the number of components in Result Type.
    <br/>
    <br/>Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result, with one exception. The exception is that for constructing a vector, a contiguous subset of the scalars consumed can be represented by a vector operand instead. The Constituents must appear in the order needed by the definition of the type of the result. When constructing a vector, there must be at least two Constituent operands.
    */
    void visitCompositeConstruct(long resultType, long result, long[] constituents);
    
    
    /**
    OpCompositeExtract
    <br/>
    <br/>Extract a part of a composite object. 
    <br/>
    <br/>Result Type must be the type of object selected by the last provided index.  The instruction result is the extracted object.
    <br/>
    <br/>Composite is the composite to extract from.
    <br/>
    <br/>Indexes walk the type hierarchy, potentially down to component granularity, to select the part to extract. All indexes must be in bounds.  All composite constituents use zero-based numbering, as described by their OpType&#8230; instruction.
    */
    void visitCompositeExtract(long resultType, long result, long composite, long[] indexes);
    
    
    /**
    OpCompositeInsert
    <br/>
    <br/>Make a copy of a composite object, while modifying one part of it.
    <br/>
    <br/>Result Type must be the same type as Composite.
    <br/>
    <br/>Object is the object to use as the modified part.
    <br/>
    <br/>Composite is the composite to copy all but the modified part from.
    <br/>
    <br/>Indexes walk the type hierarchy of Composite to the desired depth, potentially down to component granularity, to select the part to modify. All indexes must be in bounds. All composite constituents use zero-based numbering, as described by their OpType&#8230; instruction. The type of the part selected to modify must match the type of Object.
    */
    void visitCompositeInsert(long resultType, long result, long object, long composite, long[] indexes);
    
    
    /**
    OpCopyObject
    <br/>
    <br/>Make a copy of Operand. There are no dereferences involved.
    <br/>
    <br/>Result Type must match Operand type.  There are no other restrictions on the types.
    */
    void visitCopyObject(long resultType, long result, long operand);
    
    
    /**
    OpTranspose
    <br/>
    <br/>Transpose a matrix.
    <br/>
    <br/>Result Type must be an OpTypeMatrix.
    <br/>
    <br/>Matrix must be an object of type OpTypeMatrix. The number of columns and the column size of Matrix must be the reverse of those in Result Type. The types of the scalar components in Matrix and Result Type must be the same.
    <br/>
    <br/>Matrix must have of type of OpTypeMatrix.
    */
    void visitTranspose(long resultType, long result, long matrix);
    
    
    /**
    OpSNegate
    <br/>
    <br/>Signed-integer subtract of Operand from zero.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> Operand&#8217;s type  must be a scalar or vector of integer type.  It must have the same number of components as Result Type.  The component width must equal the component width in Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSNegate(long resultType, long result, long operand);
    
    
    /**
    OpFNegate
    <br/>
    <br/>Floating-point subtract of Operand from zero.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of Operand must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFNegate(long resultType, long result, long operand);
    
    
    /**
    OpIAdd
    <br/>
    <br/>Integer addition of Operand 1 and Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitIAdd(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFAdd
    <br/>
    <br/>Floating-point addition of Operand 1 and Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFAdd(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpISub
    <br/>
    <br/>Integer subtraction of Operand 2 from Operand 1.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitISub(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFSub
    <br/>
    <br/>Floating-point subtraction of Operand 2 from Operand 1.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFSub(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpIMul
    <br/>
    <br/>Integer multiplication of Operand 1 and Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitIMul(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFMul
    <br/>
    <br/>Floating-point multiplication of Operand 1 and Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFMul(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpUDiv
    <br/>
    <br/>Unsigned-integer division of Operand 1 divided by Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitUDiv(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSDiv
    <br/>
    <br/>Signed-integer division of Operand 1 divided by Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitSDiv(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFDiv
    <br/>
    <br/>Floating-point division of Operand 1 divided by Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitFDiv(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpUMod
    <br/>
    <br/>Unsigned modulo operation of Operand 1 modulo Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitUMod(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSRem
    <br/>
    <br/>Signed remainder operation of Operand 1 divided by Operand 2.  The sign of a non-0 result comes from Operand 1.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitSRem(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSMod
    <br/>
    <br/>Signed modulo operation of Operand 1 modulo Operand 2.  The sign of a non-0 result comes from Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitSMod(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFRem
    <br/>
    <br/>Floating-point remainder operation of Operand 1 divided by Operand 2.  The sign of a non-0 result comes from Operand 1.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitFRem(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFMod
    <br/>
    <br/>Floating-point remainder operation of Operand 1 divided by Operand 2.  The sign of a non-0 result comes from Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The types of Operand 1 and Operand 2 both must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.  The resulting value is undefined if Operand 2 is 0.
    */
    void visitFMod(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpVectorTimesScalar
    <br/>
    <br/>Scale a floating-point vector.
    <br/>
    <br/> Result Type must be a vector of floating-point type. 
    <br/>
    <br/> The type of Vector must be the same as Result Type. Each component of Vector is multiplied by Scalar.
    <br/>
    <br/>Scalar must have the same type as the Component Type in Result Type.
    */
    void visitVectorTimesScalar(long resultType, long result, long vector, long scalar);
    
    
    /**
    OpMatrixTimesScalar
    <br/>
    <br/>Scale a floating-point matrix.
    <br/>
    <br/> Result Type must be an OpTypeMatrix whose Column Type is a vector of floating-point type. 
    <br/>
    <br/> The type of Matrix must be the same as Result Type. Each component in each column in Matrix is multiplied by Scalar.
    <br/>
    <br/>Scalar must have the same type as the Component Type in Result Type.
    */
    void visitMatrixTimesScalar(long resultType, long result, long matrix, long scalar);
    
    
    /**
    OpVectorTimesMatrix
    <br/>
    <br/>Linear-algebraic Vector X Matrix.
    <br/>
    <br/> Result Type must be a vector of floating-point type. 
    <br/>
    <br/>Vector must be a vector with the same Component Type as the Component Type in Result Type. Its number of components must equal the number of components in each column in Matrix.
    <br/>
    <br/>Matrix must be a matrix with the same Component Type as the Component Type in Result Type. Its number of columns must equal the number of components in Result Type.
    */
    void visitVectorTimesMatrix(long resultType, long result, long vector, long matrix);
    
    
    /**
    OpMatrixTimesVector
    <br/>
    <br/>Linear-algebraic Vector X Matrix.
    <br/>
    <br/> Result Type must be a vector of floating-point type. 
    <br/>
    <br/>Matrix must be an OpTypeMatrix whose Column Type is Result Type.
    <br/>
    <br/>Vector must be a vector with the same Component Type as the Component Type in Result Type. Its number of components must equal the number of columns in Matrix.
    */
    void visitMatrixTimesVector(long resultType, long result, long matrix, long vector);
    
    
    /**
    OpMatrixTimesMatrix
    <br/>
    <br/>Linear-algebraic multiply of LeftMatrix X RightMatrix.
    <br/>
    <br/> Result Type must be an OpTypeMatrix whose Column Type is a vector of floating-point type. 
    <br/>
    <br/>LeftMatrix must be a matrix whose Column Type is the same as the Column Type in Result Type.
    <br/>
    <br/>RightMatrix must be a matrix with the same Component Type as the Component Type in Result Type. Its number of columns must equal the number of columns in Result Type. Its columns must have the same number of components as the number of columns in LeftMatrix.
    */
    void visitMatrixTimesMatrix(long resultType, long result, long leftMatrix, long rightMatrix);
    
    
    /**
    OpOuterProduct
    <br/>
    <br/>Linear-algebraic outer product of Vector 1 and Vector 2.
    <br/>
    <br/> Result Type must be an OpTypeMatrix whose Column Type is a vector of floating-point type. 
    <br/>
    <br/>Vector 1 must have the same type as the Column Type in Result Type.
    <br/>
    <br/>Vector 2 must be a vector with the same Component Type as the Component Type in Result Type. Its number of components must equal the number of columns in Result Type.
    */
    void visitOuterProduct(long resultType, long result, long vector1, long vector2);
    
    
    /**
    OpDot
    <br/>
    <br/>Dot product of Vector 1 and Vector 2.
    <br/>
    <br/> Result Type must be a floating-point type scalar. 
    <br/>
    <br/>Vector 1 and Vector 2 must be vectors of the same type, and their component type must be Result Type.
    */
    void visitDot(long resultType, long result, long vector1, long vector2);
    
    
    /**
    OpIAddCarry
    <br/>
    <br/>Result is the unsigned integer addition of Operand 1 and Operand 2, including its carry.
    <br/>
    <br/>Result Type must be from OpTypeStruct.  The struct must have two members, and the two members must be the same type.  The member type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Operand 1 and Operand 2 must have the same type as the members of Result Type. These are consumed as unsigned integers.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/>Member 0 of the result gets the low-order bits (full component width) of the addition.
    <br/>
    <br/>Member 1 of the result gets the high-order (carry) bit of the result of the addition. That is, it gets the value 1 if the addition overflowed the component width, and 0 otherwise.
    */
    void visitIAddCarry(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpISubBorrow
    <br/>
    <br/>Result is the unsigned integer subtraction of Operand 2 from Operand 1, and what it needed to borrow.
    <br/>
    <br/>Result Type must be from OpTypeStruct.  The struct must have two members, and the two members must be the same type.  The member type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Operand 1 and Operand 2 must have the same type as the members of Result Type. These are consumed as unsigned integers.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/>Member 0 of the result gets the low-order bits (full component width) of the subtraction. That is, if Operand 1 is larger than Operand 2, member 0 gets the full value of the subtraction;  if Operand 2 is larger than Operand 1, member 0 gets 2w + Operand 1 - Operand 2, where w is the component width.
    <br/>
    <br/>Member 1 of the result gets 0 if Operand 1 &ge; Operand 2, and gets 1 otherwise.
    */
    void visitISubBorrow(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpUMulExtended
    <br/>
    <br/>Result is the full value of the unsigned integer multiplication of Operand 1 and Operand 2.
    <br/>
    <br/>Result Type must be from OpTypeStruct.  The struct must have two members, and the two members must be the same type.  The member type must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Operand 1 and Operand 2 must have the same type as the members of Result Type. These are consumed as unsigned integers.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/>Member 0 of the result gets the low-order bits of the multiplication.
    <br/>
    <br/>Member 1 of the result gets the high-order bits of the multiplication.
    */
    void visitUMulExtended(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSMulExtended
    <br/>
    <br/>Result is the full value of the signed integer multiplication of Operand 1 and Operand 2.
    <br/>
    <br/>Result Type must be from OpTypeStruct.  The struct must have two members, and the two members must be the same type.  The member type must be a scalar or vector of integer type. 
    <br/>
    <br/>Operand 1 and Operand 2 must have the same type as the members of Result Type. These are consumed as signed integers.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/>Member 0 of the result gets the low-order bits of the multiplication.
    <br/>
    <br/>Member 1 of the result gets the high-order bits of the multiplication.
    */
    void visitSMulExtended(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpShiftRightLogical
    <br/>
    <br/>Shift the bits in Base right by the number of bits specified in Shift.  The most-significant bits will be zero filled. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of each Base and Shift must be a scalar or vector of integer type. Base and Shift must have the same number of components.  The number of components and bit width of the type of Base must be the same as in Result Type. 
    <br/>
    <br/>Shift is consumed as an unsigned integer. The result is undefined if Shift is greater than the bit width of the components of Base.
    <br/>
    <br/> Results are computed per component.
    */
    void visitShiftRightLogical(long resultType, long result, long base, long shift);
    
    
    /**
    OpShiftRightArithmetic
    <br/>
    <br/>Shift the bits in Base right by the number of bits specified in Shift. The most-significant bits will be filled with the sign bit from Base. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of each Base and Shift must be a scalar or vector of integer type. Base and Shift must have the same number of components.  The number of components and bit width of the type of Base must be the same as in Result Type. 
    <br/>
    <br/>Shift is treated as unsigned.  The result is undefined if Shift is greater than the bit width of the components of Base. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitShiftRightArithmetic(long resultType, long result, long base, long shift);
    
    
    /**
    OpShiftLeftLogical
    <br/>
    <br/>Shift the bits in Base left by the number of bits specified in Shift. The least-significant bits will be zero filled. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of each Base and Shift must be a scalar or vector of integer type. Base and Shift must have the same number of components.  The number of components and bit width of the type of Base must be the same as in Result Type. 
    <br/>
    <br/>Shift is treated as unsigned.  The result is undefined if Shift is greater than the bit width of the components of Base. 
    <br/>
    <br/>The number of components and bit width of Result Type must match those Base type. All types must be integer types.
    <br/>
    <br/> Results are computed per component.
    */
    void visitShiftLeftLogical(long resultType, long result, long base, long shift);
    
    
    /**
    OpBitwiseOr
    <br/>
    <br/>Result is 1 if either Operand 1 or Operand 2 is 1. Result is 0 if both Operand 1 and Operand 2 are 0.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    void visitBitwiseOr(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpBitwiseXor
    <br/>
    <br/>Result is 1 if exactly one of Operand 1 or Operand 2 is 1. Result is 0 if Operand 1 and Operand 2 have the same value.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    void visitBitwiseXor(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpBitwiseAnd
    <br/>
    <br/>Result is 1 if both Operand 1 and Operand 2 are 1. Result is 0 if either Operand 1 or Operand 2 are 0.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    void visitBitwiseAnd(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpNot
    <br/>
    <br/>Complement the bits of Operand.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> Operand&#8217;s type  must be a scalar or vector of integer type.  It must have the same number of components as Result Type.  The component width must equal the component width in Result Type.
    */
    void visitNot(long resultType, long result, long operand);
    
    
    /**
    OpBitFieldInsert
    <br/>
    <br/>Make a copy of an object, with a modified bit field that comes from another object.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Base and Insert must be the same as Result Type. 
    <br/>
    <br/>Any result bits numbered outside [Offset, Offset + Count -  1] (inclusive) will come from the corresponding bits in Base.
    <br/>
    <br/>Any result bits numbered in [Offset, Offset + Count -  1] come, in order, from the bits numbered [0, Count - 1] of Insert.
    <br/>
    <br/>Count  must be an integer type scalar. Count is the number of bits taken from Insert. It will be consumed as an unsigned value. Count can be 0, in which case the result will be Base.
    <br/>
    <br/>Offset  must be an integer type scalar. Offset is the lowest-order bit of the bit field.  It will be consumed as an unsigned value.
    <br/>
    <br/>The resulting value is undefined if Count or Offset or their sum is greater than the number of bits in the result.
    */
    void visitBitFieldInsert(long resultType, long result, long base, long insert, long offset, long count);
    
    
    /**
    OpBitFieldSExtract
    <br/>
    <br/>Extract a bit field from an object, with sign extension.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Base must be the same as Result Type. 
    <br/>
    <br/>If Count is greater than 0: The bits of Base numbered in [Offset, Offset + Count -  1] (inclusive) become the bits numbered [0, Count - 1] of the result. The remaining bits of the result will all be the same as bit Offset + Count -  1 of Base.
    <br/>
    <br/>Count  must be an integer type scalar. Count is the number of bits extracted from Base. It will be consumed as an unsigned value. Count can be 0, in which case the result will be 0.
    <br/>
    <br/>Offset  must be an integer type scalar. Offset is the lowest-order bit of the bit field to extract from Base.  It will be consumed as an unsigned value.
    <br/>
    <br/>The resulting value is undefined if Count or Offset or their sum is greater than the number of bits in the result.
    */
    void visitBitFieldSExtract(long resultType, long result, long base, long offset, long count);
    
    
    /**
    OpBitFieldUExtract
    <br/>
    <br/>Extract a bit field from an object, without sign extension.
    <br/>
    <br/>The semantics are the same as with OpBitFieldSExtract with the exception that there is no sign extension. The remaining bits of the result will all be 0.
    */
    void visitBitFieldUExtract(long resultType, long result, long base, long offset, long count);
    
    
    /**
    OpBitReverse
    <br/>
    <br/>Reverse the bits in an object.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type. 
    <br/>
    <br/> The type of Base must be the same as Result Type. 
    <br/>
    <br/>The bit-number n of the result will be taken from bit-number Width - 1 - n of Base, where Width is the OpTypeInt operand of the Result Type.
    */
    void visitBitReverse(long resultType, long result, long base);
    
    
    /**
    OpBitCount
    <br/>
    <br/>Count the number of set bits in an object.
    <br/>
    <br/> Results are computed per component. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The components must be wide enough to hold the unsigned Width of Base as an unsigned value. That is, no sign bit is needed or counted when checking for a wide enough result width.
    <br/>
    <br/>Base must be a scalar or vector of integer type.  It must have the same number of components as Result Type.
    <br/>
    <br/>The result is the unsigned value that is the number of bits in Base that are 1.
    */
    void visitBitCount(long resultType, long result, long base);
    
    
    /**
    OpAny
    <br/>
    <br/>Result is true if any component of Vector is true, otherwise result is false.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Vector must be a vector of Boolean type.
    */
    void visitAny(long resultType, long result, long vector);
    
    
    /**
    OpAll
    <br/>
    <br/>Result is true if all components of Vector are true, otherwise result is false.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Vector must be a vector of Boolean type.
    */
    void visitAll(long resultType, long result, long vector);
    
    
    /**
    OpIsNan
    <br/>
    <br/>Result is true if x is an IEEE NaN, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitIsNan(long resultType, long result, long x);
    
    
    /**
    OpIsInf
    <br/>
    <br/>Result is true if x is an IEEE Inf, otherwise result is false
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitIsInf(long resultType, long result, long x);
    
    
    /**
    OpIsFinite
    <br/>
    <br/>Result is true if x is an IEEE finite number, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitIsFinite(long resultType, long result, long x);
    
    
    /**
    OpIsNormal
    <br/>
    <br/>Result is true if x is an IEEE normal number, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitIsNormal(long resultType, long result, long x);
    
    
    /**
    OpSignBitSet
    <br/>
    <br/>Result is true if x has its sign bit set, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitSignBitSet(long resultType, long result, long x);
    
    
    /**
    OpLessOrGreater
    <br/>
    <br/>Result is true if x &lt; y or x &gt; y, where IEEE comparisons are used, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/>y must have the same type as x.
    <br/>
    <br/> Results are computed per component.
    */
    void visitLessOrGreater(long resultType, long result, long x, long y);
    
    
    /**
    OpOrdered
    <br/>
    <br/>Result is true if both x == x and y == y are true, where IEEE comparison is used, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/>y must have the same type as x.
    <br/>
    <br/> Results are computed per component.
    */
    void visitOrdered(long resultType, long result, long x, long y);
    
    
    /**
    OpUnordered
    <br/>
    <br/>Result is true if either x or y is an IEEE NaN, otherwise result is false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/>x must be a scalar or vector of floating-point type.  It must have the same number of components as Result Type.
    <br/>
    <br/>y must have the same type as x.
    <br/>
    <br/> Results are computed per component.
    */
    void visitUnordered(long resultType, long result, long x, long y);
    
    
    /**
    OpLogicalEqual
    <br/>
    <br/>Result is true if Operand 1 and Operand 2 have the same value. Result is false if Operand 1 and Operand 2 have different values.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 must be the same as Result Type. 
    <br/>
    <br/> The type of Operand 2 must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitLogicalEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpLogicalNotEqual
    <br/>
    <br/>Result is true if Operand 1 and Operand 2 have different values. Result is false if Operand 1 and Operand 2 have the same value.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 must be the same as Result Type. 
    <br/>
    <br/> The type of Operand 2 must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitLogicalNotEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpLogicalOr
    <br/>
    <br/>Result is true if either Operand 1 or Operand 2 is true. Result is false if both Operand 1 and Operand 2 are false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 must be the same as Result Type. 
    <br/>
    <br/> The type of Operand 2 must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitLogicalOr(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpLogicalAnd
    <br/>
    <br/>Result is true if both Operand 1 and Operand 2 are true. Result is false if either Operand 1 or Operand 2 are false.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 must be the same as Result Type. 
    <br/>
    <br/> The type of Operand 2 must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitLogicalAnd(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpLogicalNot
    <br/>
    <br/>Result is true if Operand is false.  Result is false if Operand is true.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand must be the same as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitLogicalNot(long resultType, long result, long operand);
    
    
    /**
    OpSelect
    <br/>
    <br/>Select components from two objects.
    <br/>
    <br/>Result Type must be a pointer, scalar, or vector.
    <br/>
    <br/> The type of Object 1 must be the same as Result Type. Object 1 is selected as the result if Condition is true.
    <br/>
    <br/> The type of Object 2 must be the same as Result Type. Object 2 is selected as the result if Condition is false.
    <br/>
    <br/>Condition must be a scalar or vector of Boolean type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    void visitSelect(long resultType, long result, long condition, long object1, long object2);
    
    
    /**
    OpIEqual
    <br/>
    <br/>Integer comparison for equality.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitIEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpINotEqual
    <br/>
    <br/>Integer comparison for inequality.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitINotEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpUGreaterThan
    <br/>
    <br/>Unsigned-integer comparison if Operand 1 is greater than  Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitUGreaterThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSGreaterThan
    <br/>
    <br/>Signed-integer comparison if Operand 1 is greater than  Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSGreaterThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpUGreaterThanEqual
    <br/>
    <br/>Unsigned-integer comparison if Operand 1 is greater than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitUGreaterThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSGreaterThanEqual
    <br/>
    <br/>Signed-integer comparison if Operand 1 is greater than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSGreaterThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpULessThan
    <br/>
    <br/>Unsigned-integer comparison if Operand 1 is less than Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitULessThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSLessThan
    <br/>
    <br/>Signed-integer comparison if Operand 1 is less than Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSLessThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpULessThanEqual
    <br/>
    <br/>Unsigned-integer comparison if Operand 1 is less than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitULessThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpSLessThanEqual
    <br/>
    <br/>Signed-integer comparison if Operand 1 is less than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same component width, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitSLessThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdEqual
    <br/>
    <br/>Floating-point comparison for being ordered and equal.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordEqual
    <br/>
    <br/>Floating-point comparison for being unordered or equal.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdNotEqual
    <br/>
    <br/>Floating-point comparison for being ordered and not equal.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdNotEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordNotEqual
    <br/>
    <br/>Floating-point comparison for being unordered or not equal.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordNotEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdLessThan
    <br/>
    <br/>Floating-point comparison if operands are ordered and Operand 1 is less than Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdLessThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordLessThan
    <br/>
    <br/>Floating-point comparison if operands are unordered or Operand 1 is less than Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordLessThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdGreaterThan
    <br/>
    <br/>Floating-point comparison if operands are ordered and Operand 1 is greater than  Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdGreaterThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordGreaterThan
    <br/>
    <br/>Floating-point comparison if operands are unordered or Operand 1 is greater than  Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordGreaterThan(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdLessThanEqual
    <br/>
    <br/>Floating-point comparison if operands are ordered and Operand 1 is less than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdLessThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordLessThanEqual
    <br/>
    <br/>Floating-point comparison if operands are unordered or Operand 1 is less than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordLessThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFOrdGreaterThanEqual
    <br/>
    <br/>Floating-point comparison if operands are ordered and Operand 1 is greater than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFOrdGreaterThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpFUnordGreaterThanEqual
    <br/>
    <br/>Floating-point comparison if operands are unordered or Operand 1 is greater than or equal to Operand 2.
    <br/>
    <br/> Result Type must be a scalar or vector of Boolean type. 
    <br/>
    <br/> The type of Operand 1 and Operand 2  must be a scalar or vector of floating-point type.  They must have the same type, and they must have the same number of components as Result Type. 
    <br/>
    <br/> Results are computed per component.
    */
    void visitFUnordGreaterThanEqual(long resultType, long result, long operand1, long operand2);
    
    
    /**
    OpDPdx
    <br/>
    <br/>Same result as either OpDPdxFine or OpDPdxCoarse on P. Selection of which one is based on external factors.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdx(long resultType, long result, long p);
    
    
    /**
    OpDPdy
    <br/>
    <br/>Same result as either OpDPdyFine or OpDPdyCoarse on P. Selection of which one is based on external factors.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdy(long resultType, long result, long p);
    
    
    /**
    OpFwidth
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdx and OpDPdy on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitFwidth(long resultType, long result, long p);
    
    
    /**
    OpDPdxFine
    <br/>
    <br/>Result is the partial derivative of P with respect to the window x coordinate.Will use local differencing based on the value of P for the current fragment and its immediate neighbor(s).
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdxFine(long resultType, long result, long p);
    
    
    /**
    OpDPdyFine
    <br/>
    <br/>Result is the partial derivative of P with respect to the window y coordinate.Will use local differencing based on the value of P for the current fragment and its immediate neighbor(s).
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdyFine(long resultType, long result, long p);
    
    
    /**
    OpFwidthFine
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdxFine and OpDPdyFine on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitFwidthFine(long resultType, long result, long p);
    
    
    /**
    OpDPdxCoarse
    <br/>
    <br/>Result is the partial derivative of P with respect to the window x coordinate. Will use local differencing based on the value of P for the current fragment&#8217;s neighbors, and will possibly, but not necessarily, include the value of P for the current fragment. That is, over a given area, the implementation can compute x derivatives in fewer unique locations than would be allowed for OpDPdxFine.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdxCoarse(long resultType, long result, long p);
    
    
    /**
    OpDPdyCoarse
    <br/>
    <br/>Result is the partial derivative of P with respect to the window y coordinate. Will use local differencing based on the value of P for the current fragment&#8217;s neighbors, and will possibly, but not necessarily, include the value of P for the current fragment. That is, over a given area, the implementation can compute y derivatives in fewer unique locations than would be allowed for OpDPdyFine.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitDPdyCoarse(long resultType, long result, long p);
    
    
    /**
    OpFwidthCoarse
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdxCoarse and OpDPdyCoarse on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type.  The component width must be 32 bits.
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitFwidthCoarse(long resultType, long result, long p);
    
    
    /**
    OpPhi
    <br/>
    <br/>The SSA phi function.
    <br/>
    <br/>The result is selected based on control flow: If control reached the current block from Parent i, Result Id gets the value that Variable i had at the end of Parent i.
    <br/>
    <br/>Result Type can be any type.
    <br/>
    <br/>Operands are a sequence of pairs: (Variable 1, Parent 1 block), (Variable 2, Parent 2 block), &#8230; Each Parent i block is the label of an immediate predecessor in the CFG of the current block. There must be exactly one Parent i for each parent block of the current block in the CFG. All Variables must have a type matching Result Type.
    <br/>
    <br/>Within a block, this instruction must appear before all non-OpPhi instructions (except for OpLine, which can be mixed with OpPhi).
    */
    void visitPhi(long resultType, long result, long[] variables);
    
    
    /**
    OpLoopMerge
    <br/>
    <br/>Declare a structured loop.
    <br/>
    <br/>This instruction must immediately precede either an OpBranch or OpBranchConditional instruction. That is, it must be the second-to-last instruction in its block.
    <br/>
    <br/>Merge Block is the label of the merge block for this structured loop.
    <br/>
    <br/>Continue Target is the label of a block targeted for processing a loop "continue".
    <br/>
    <br/>Loop Control Parameters appear in Loop Control-table order for any Loop Control setting that requires such a parameter.
    <br/>
    <br/>See Structured Control Flow for more detail.
    */
    void visitLoopMerge(long mergeBlock, long continueTarget, long loopControl, long[] parameters);
    
    
    /**
    OpSelectionMerge
    <br/>
    <br/>Declare a structured selection.
    <br/>
    <br/>This instruction must immediately precede either an OpBranchConditional or OpSwitch instruction. That is, it must be the second-to-last instruction in its block.
    <br/>
    <br/>Merge Block is the label of the merge block for this structured selection.
    <br/>
    <br/>See Structured Control Flow for more detail.
    */
    void visitSelectionMerge(long mergeBlock, long selectionControl);
    
    
    /**
    OpLabel
    <br/>
    <br/>The block label instruction: Any reference to a block is through the Result &lt;id&gt; of its label.
    <br/>
    <br/>Must be the first instruction of any block, and appears only as the first instruction of a block.
    */
    void visitLabel(long result);
    
    
    /**
    OpBranch
    <br/>
    <br/>Unconditional branch to Target Label.
    <br/>
    <br/>Target Label must be the Result &lt;id&gt; of an OpLabel instruction in the current function.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitBranch(long targetLabel);
    
    
    /**
    OpBranchConditional
    <br/>
    <br/>If Condition is true, branch to True Label, otherwise branch to False Label.
    <br/>
    <br/>Condition must be a Boolean type scalar.
    <br/>
    <br/>True Label must be an OpLabel in the current function.
    <br/>
    <br/>False Label must be an OpLabel in the current function.
    <br/>
    <br/>Branch weights are unsigned 32-bit integer literals. There must be either no Branch Weights or exactly two branch weights. If present, the first is the weight for branching to True Label, and the second is the weight for branching to False Label. The implied probability that a branch is taken is its weight divided by the sum of the two Branch weights. At least one weight must be non-zero. A weight of zero does not imply a branch is dead or permit its removal; branch weights are only hints. The two weights must not overflow a 32-bit unsigned integer when added together.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitBranchConditional(long condition, long trueLabel, long falseLabel, long[] weights);
    
    
    /**
    OpSwitch
    <br/>
    <br/>Multi-way branch to one of the operand label &lt;id&gt;.
    <br/>
    <br/>Selector must have a type of OpTypeInt. Selector will be compared for equality to the Target literals.
    <br/>
    <br/>Default must be the &lt;id&gt; of a label.  If Selector does not equal any of the Target literals, control flow will branch to the Default label &lt;id&gt;.
    <br/>
    <br/>Target must be alternating scalar integer literals and the &lt;id&gt; of a label.  If Selector equals a literal, control flow will branch to the following label &lt;id&gt;. It is invalid for any two literal to be equal to each other. If Selector does not equal any literal, control flow will branch to the Default label &lt;id&gt;. Each literal is interpreted with the type of Selector: The bit width of Selector&#8217;s type will be the width of each literal&#8217;s type. If this width is not a multiple of 32-bits, the literals must be sign extended when the OpTypeInt Signedness is set to 1. (See Literal Number.)
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitSwitch(long selector, long defaultValue, long[] target);
    
    
    /**
    OpKill
    <br/>
    <br/>Fragment-shader discard.
    <br/>
    <br/>Ceases all further processing in any invocation that executes it: Only instructions these invocations executed before OpKill will have observable side effects. If this instruction is executed in non-uniform control flow, all subsequent control flow is non-uniform (for invocations that continue to execute).
    <br/>
    <br/>This instruction must be the last instruction in a block.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    void visitKill();
    
    
    /**
    OpReturn
    <br/>
    <br/>Return with no value from a function with void return type.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitReturn();
    
    
    /**
    OpReturnValue
    <br/>
    <br/>Return a value from a function.
    <br/>
    <br/>Value is the value returned, by copy, and must match the Return Type operand of the OpTypeFunction type of the OpFunction body this return instruction is in.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitReturnValue(long value);
    
    
    /**
    OpUnreachable
    <br/>
    <br/>Declares that this block is not reachable in the CFG.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    void visitUnreachable();
    
    
    /**
    OpLifetimeStart
    <br/>
    <br/>Declare that an object was not defined before this instruction.
    <br/>
    <br/>Pointer is a pointer to the object whose lifetime is starting. Its type must be an OpTypePointer with Storage Class Function.
    <br/>
    <br/>Size must be 0 if Pointer is a pointer to a non-void type or the Addresses capability is not being used. If Size is non-zero, it is the number of bytes of memory whose lifetime is starting.  Its type  must be an integer type scalar.  It is treated as unsigned; if its type has Signedness of 1, its sign bit cannot be set.
    */
    void visitLifetimeStart(long pointer, long size);
    
    
    /**
    OpLifetimeStop
    <br/>
    <br/>Declare that an object is dead after this instruction.
    <br/>
    <br/>Pointer is a pointer to the object whose lifetime is ending. Its type must be an OpTypePointer with Storage Class Function.
    <br/>
    <br/>Size must be 0 if Pointer is a pointer to a non-void type or the Addresses capability is not being used. If Size is non-zero, it is the number of bytes of memory whose lifetime is ending.  Its type  must be an integer type scalar.  It is treated as unsigned; if its type has Signedness of 1, its sign bit cannot be set.
    */
    void visitLifetimeStop(long pointer, long size);
    
    
    /**
    OpAtomicLoad
    <br/>
    <br/>Atomically load through Pointer using the given Semantics. All subparts of the value that is loaded will be read atomically with respect to all other atomic accesses to it within Scope.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type. 
    <br/>
    <br/>Pointer is the pointer to the memory to read. The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicLoad(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics);
    
    
    /**
    OpAtomicStore
    <br/>
    <br/>Atomically store through Pointer using the given Semantics. All subparts of Value will be written atomically with respect to all other atomic accesses to it within Scope.
    <br/>
    <br/>Pointer is the pointer to the memory to write. The type it points to  must be a scalar of integer type or floating-point type. 
    <br/>
    <br/>Value is the value to write. The type of Value and the type pointed to by Pointer must be the same type.
    */
    void visitAtomicStore(long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicExchange
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value from copying Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicCompareExchange
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value from Value only if Original Value equals Comparator, and
    <br/>3) store the New Value back through Pointer&#8217;only if 'Original Value equaled Comparator.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/>Use Equal for the memory semantics of this instruction when Value and Original Value compare equal.
    <br/>
    <br/>Use Unequal for the memory semantics of this instruction when Value and Original Value compare unequal. Unequal cannot be set to Release or Acquire and Release. In addition, Unequal cannot be set to a stronger memory-order then Equal.
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.  This type must also match the type of Comparator.
    */
    void visitAtomicCompareExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator);
    
    
    /**
    OpAtomicCompareExchangeWeak
    <br/>
    <br/>Deprecated (use OpAtomicCompareExchange).
    <br/>
    <br/>Has the same semantics as OpAtomicCompareExchange.
    */
    void visitAtomicCompareExchangeWeak(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator);
    
    
    /**
    OpAtomicIIncrement
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value through integer addition of 1 to Original Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicIIncrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics);
    
    
    /**
    OpAtomicIDecrement
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value through integer subtraction of 1 from Original Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicIDecrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics);
    
    
    /**
    OpAtomicIAdd
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by integer addition of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicIAdd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicISub
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by integer subtraction of Value from Original Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicISub(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicSMin
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by finding the smallest signed integer of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicSMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicUMin
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by finding the smallest unsigned integer of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicUMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicSMax
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by finding the largest signed integer of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicSMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicUMax
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by finding the largest unsigned integer of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicUMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicAnd
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by the bitwise AND of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicAnd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicOr
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by the bitwise OR of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicOr(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicXor
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by the bitwise exclusive OR of Original Value and Value, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.
    */
    void visitAtomicXor(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value);
    
    
    /**
    OpAtomicFlagTestAndSet
    <br/>
    <br/>Atomically sets the flag value pointed to by Pointer to the set state.
    <br/>
    <br/>Pointer must be a pointer to a 32-bit integer type representing an atomic flag.
    <br/>
    <br/>The instruction&#8217;s result is true if the flag was in the set state or false if the flag was in the clear state immediately before the operation.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Results are undefined if an atomic flag is modified  by an instruction other than OpAtomicFlagTestAndSet or OpAtomicFlagClear
    */
    void visitAtomicFlagTestAndSet(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics);
    
    
    /**
    OpAtomicFlagClear
    <br/>
    <br/>Atomically sets the flag value pointed to by Pointer to the clear state.
    <br/>
    <br/>Pointer must be a pointer to a 32-bit integer type representing an atomic flag.
    <br/>
    <br/>Memory Semantics cannot be Acquire or AcquireRelease
    <br/>
    <br/>Results are undefined if an atomic flag is modified  by an instruction other than OpAtomicFlagTestAndSet or OpAtomicFlagClear
    */
    void visitAtomicFlagClear(long pointer, long scopeScope, long memorySemanticsSemantics);
    
    
    /**
    OpEmitVertex
    <br/>
    <br/>Emits the current values of all output variables to the current output primitive. After execution, the values of all output variables are undefined.
    <br/>
    <br/>This instruction can only be used when only one stream is present.
    */
    void visitEmitVertex();
    
    
    /**
    OpEndPrimitive
    <br/>
    <br/>Finish the current primitive and start a new one.  No vertex is emitted.
    <br/>
    <br/>This instruction can only be used when only one stream is present.
    */
    void visitEndPrimitive();
    
    
    /**
    OpEmitStreamVertex
    <br/>
    <br/>Emits the current values of all output variables to the current output primitive. After execution, the values of all output variables are undefined.
    <br/>
    <br/>Stream must be an &lt;id&gt; of a constant instruction with a scalar integer type. That constant is the output-primitive stream number.
    <br/>
    <br/>This instruction can only be used when multiple streams are present.
    */
    void visitEmitStreamVertex(long stream);
    
    
    /**
    OpEndStreamPrimitive
    <br/>
    <br/>Finish the current primitive and start a new one.  No vertex is emitted.
    <br/>
    <br/>Stream must be an &lt;id&gt; of a constant instruction with a scalar integer type. That constant is the output-primitive stream number.
    <br/>
    <br/>This instruction can only be used when multiple streams are present.
    */
    void visitEndStreamPrimitive(long stream);
    
    
    /**
    OpControlBarrier
    <br/>
    <br/>Wait for other invocations of this module to reach the current point of execution.
    <br/>
    <br/>All invocations of this module within Execution scope must reach this point of execution before any invocation will proceed beyond it.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>If Semantics is not None, this instruction also serves as an OpMemoryBarrier instruction, and must also perform and adhere to the description and semantics of an OpMemoryBarrier instruction with the same Memory and Semantics operands.  This allows atomically specifying both a control barrier and a memory barrier (that is, without needing two instructions). If Semantics is None, Memory is ignored.
    <br/>
    <br/>Before version 1.3, it is only valid to use this instruction with TessellationControl, GLCompute, or Kernel execution models. There is no such restriction starting with version 1.3.
    <br/>
    <br/>When used with the TessellationControl execution model, it also implicitly synchronizes the Output Storage Class:  Writes to Output variables performed by any invocation executed prior to a OpControlBarrier will be visible to any other invocation after return from that OpControlBarrier.
    */
    void visitControlBarrier(long scopeExecution, long scopeMemory, long memorySemanticsSemantics);
    
    
    /**
    OpMemoryBarrier
    <br/>
    <br/>Control the order that memory accesses are observed.
    <br/>
    <br/>Ensures that memory accesses issued before this instruction will be observed before memory accesses issued after this instruction. This control is ensured only for memory accesses issued by this invocation and observed by another invocation executing within Memory scope.
    <br/>
    <br/>Semantics declares what kind of memory is being controlled and what kind of control to apply.
    <br/>
    <br/>To execute both a memory barrier and a control barrier, see OpControlBarrier.
    */
    void visitMemoryBarrier(long scopeMemory, long memorySemanticsSemantics);
    
    
    /**
    OpNamedBarrierInitialize
    <br/>
    <br/>Declare a new named-barrier object.
    <br/>
    <br/>Result Type must be the type OpTypeNamedBarrier.
    <br/>
    <br/>Subgroup Count must be a 32-bit integer type scalar representing the number of subgroups that must reach the current point of execution.
    */
    void visitNamedBarrierInitialize(long resultType, long result, long subgroupCount);
    
    
    /**
    OpMemoryNamedBarrier
    <br/>
    <br/>Wait for other invocations of this module to reach the current point of execution.
    <br/>
    <br/>Named Barrier must be the type OpTypeNamedBarrier.
    <br/>
    <br/>If Semantics is not None, this instruction also serves as an OpMemoryBarrier instruction, and must also perform and adhere to the description and semantics of an OpMemoryBarrier instruction with the same Memory and Semantics operands.  This allows atomically specifying both a control barrier and a memory barrier (that is, without needing two instructions). If Semantics None, Memory is ignored.
    */
    void visitMemoryNamedBarrier(long namedBarrier, long scopeMemory, long memorySemanticsSemantics);
    
    
    /**
    OpGroupAsyncCopy
    <br/>
    <br/>Perform an asynchronous group copy of Num Elements elements from Source to Destination. The asynchronous copy is performed by all work-items in a group.
    <br/>
    <br/>This instruction returns an event object that can be used by OpGroupWaitEvents to wait for the async copy to finish.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be an OpTypeEvent object.
    <br/>
    <br/>Destination must be a pointer to a scalar or vector of floating-point type or integer type. 
    <br/>
    <br/>Destination pointer Storage Class must be Workgroup or CrossWorkgroup.
    <br/>
    <br/> The type of Source must be the same as Destination. 
    <br/>
    <br/>When Destination pointer Storage Class is Workgroup, the Source pointer Storage Class must be CrossWorkgroup. In this case Stride defines the stride in elements when reading from Source pointer.
    <br/>
    <br/>When Destination pointer Storage Class is CrossWorkgroup, the Source pointer Storage Class must be Workgroup. In this case Stride defines the stride in elements when writing each element to Destination pointer.
    <br/>
    <br/>Stride and NumElements must be a 32-bit integer type scalar when the addressing model is Physical32 and 64 bit integer type scalar when the Addressing Model is Physical64.
    <br/>
    <br/>Event must have a type of OpTypeEvent.
    <br/>
    <br/>Event can be used to associate the copy with a previous copy allowing an event to be shared by multiple copies. Otherwise Event should be an OpConstantNull.
    <br/>
    <br/>If Event argument is not OpConstantNull, the event object supplied in event argument will be returned.
    */
    void visitGroupAsyncCopy(long resultType, long result, long scopeExecution, long destination, long source, long numElements, long stride, long event);
    
    
    /**
    OpGroupWaitEvents
    <br/>
    <br/>Wait for events generated by OpGroupAsyncCopy operations to complete. Events List points to Num Events event objects, which will be released after the wait is performed.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Num Events must be a 32-bit integer type scalar.
    <br/>
    <br/>Events List must be a pointer to OpTypeEvent.
    */
    void visitGroupWaitEvents(long scopeExecution, long numEvents, long eventsList);
    
    
    /**
    OpGroupAll
    <br/>
    <br/>Evaluates a predicate for all invocations in the group,resulting in true if predicate evaluates to true for all invocations in the group, otherwise the result is false.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Predicate must be a Boolean type.
    */
    void visitGroupAll(long resultType, long result, long scopeExecution, long predicate);
    
    
    /**
    OpGroupAny
    <br/>
    <br/>Evaluates a predicate for all invocations in the group,resulting in true if predicate evaluates to true for any invocation in the group, otherwise the result is false.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Predicate must be a Boolean type.
    */
    void visitGroupAny(long resultType, long result, long scopeExecution, long predicate);
    
    
    /**
    OpGroupBroadcast
    <br/>
    <br/>Return the Value of the invocation identified by the local id LocalId to all invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type or a 16, 32 or 64 float type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>LocalId must be an integer datatype. It can be a scalar, or a vector with 2 components or a vector with 3 components. LocalId must be the same for all invocations in the group.
    */
    void visitGroupBroadcast(long resultType, long result, long scopeExecution, long value, long localId);
    
    
    /**
    OpGroupIAdd
    <br/>
    <br/>An integer add group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupIAdd(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFAdd
    <br/>
    <br/>A floating-point add group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupFAdd(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFMin
    <br/>
    <br/>A floating-point minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is +INF. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupFMin(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupUMin
    <br/>
    <br/>An unsigned integer minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is UINT_MAX when X is 32 bits wide and ULONG_MAX when X is 64 bits wide. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupUMin(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupSMin
    <br/>
    <br/>A signed integer minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is INT_MAX when X is 32 bits wide and LONG_MAX when X is 64 bits wide. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupSMin(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFMax
    <br/>
    <br/>A floating-point maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is -INF. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupFMax(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupUMax
    <br/>
    <br/>An unsigned integer maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupUMax(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupSMax
    <br/>
    <br/>A signed integer maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>X and Result Type must be a 32-bit or 64-bit OpTypeInt data type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is INT_MIN when X is 32 bits wide and LONG_MIN when X is 64 bits wide. 
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    void visitGroupSMax(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpSubgroupBallotKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    void visitSubgroupBallotKHR(long resultType, long result, long predicate);
    
    
    /**
    OpSubgroupFirstInvocationKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    void visitSubgroupFirstInvocationKHR(long resultType, long result, long value);
    
    
    /**
    OpSubgroupAllKHR
    <br/>
    <br/>TBD
    */
    void visitSubgroupAllKHR(long resultType, long result, long predicate);
    
    
    /**
    OpSubgroupAnyKHR
    <br/>
    <br/>TBD
    */
    void visitSubgroupAnyKHR(long resultType, long result, long predicate);
    
    
    /**
    OpSubgroupAllEqualKHR
    <br/>
    <br/>TBD
    */
    void visitSubgroupAllEqualKHR(long resultType, long result, long predicate);
    
    
    /**
    OpSubgroupReadInvocationKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    void visitSubgroupReadInvocationKHR(long resultType, long result, long value, long index);
    
    
    /**
    OpGroupIAddNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupIAddNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFAddNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupFAddNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFMinNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupFMinNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupUMinNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupUMinNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupSMinNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupSMinNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupFMaxNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupFMaxNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupUMaxNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupUMaxNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpGroupSMaxNonUniformAMD
    <br/>
    <br/>TBD
    */
    void visitGroupSMaxNonUniformAMD(long resultType, long result, long scopeExecution, long operation, long x);
    
    
    /**
    OpSubgroupShuffleINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupShuffleINTEL(long resultType, long result, long data, long invocationId);
    
    
    /**
    OpSubgroupShuffleDownINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupShuffleDownINTEL(long resultType, long result, long current, long next, long delta);
    
    
    /**
    OpSubgroupShuffleUpINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupShuffleUpINTEL(long resultType, long result, long previous, long current, long delta);
    
    
    /**
    OpSubgroupShuffleXorINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupShuffleXorINTEL(long resultType, long result, long data, long value);
    
    
    /**
    OpSubgroupBlockReadINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupBlockReadINTEL(long resultType, long result, long ptr);
    
    
    /**
    OpSubgroupBlockWriteINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupBlockWriteINTEL(long ptr, long data);
    
    
    /**
    OpSubgroupImageBlockReadINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupImageBlockReadINTEL(long resultType, long result, long image, long coordinate);
    
    
    /**
    OpSubgroupImageBlockWriteINTEL
    <br/>
    <br/>TBD
    */
    void visitSubgroupImageBlockWriteINTEL(long image, long coordinate, long data);
    
    
    /**
    OpEnqueueMarker
    <br/>
    <br/>Enqueue a marker command to the queue object specified by Queue. The marker command waits for a list of events to complete, or if the list is empty it waits for all previously enqueued commands in Queue to complete before the marker completes.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar. A successful enqueue results in the value 0. A failed enqueue results in a non-0 value.
    <br/>
    <br/>Queue must be of the type OpTypeQueue.
    <br/>
    <br/>Num Events specifies the number of event objects in the wait list pointed to by Wait Events and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Wait Events specifies the list of wait event objects and must be a pointer to OpTypeDeviceEvent.
    <br/>
    <br/>Ret Event is a pointer to a device event which gets implicitly retained by this instruction.  It must have a type of OpTypePointer to OpTypeDeviceEvent. If Ret Event is set to null this instruction becomes a no-op.
    */
    void visitEnqueueMarker(long resultType, long result, long queue, long numEvents, long waitEvents, long retEvent);
    
    
    /**
    OpEnqueueKernel
    <br/>
    <br/>Enqueue the function specified by Invoke and the NDRange specified by ND Range for execution to the queue object specified by Queue. 
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar. A successful enqueue results in the value 0. A failed enqueue results in a non-0 value.
    <br/>
    <br/>Queue must be of the type OpTypeQueue.
    <br/>
    <br/>Flags must be an integer type scalar.  The content of Flags is interpreted as Kernel Enqueue Flags mask.
    <br/>
    <br/>The type of ND Range must be an OpTypeStruct whose members are as described by the Result Type of OpBuildNDRange.
    <br/>
    <br/>Num Events specifies the number of event objects in the wait list pointed to by Wait Events and must be 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Wait Events specifies the list of wait event objects and must be a pointer to OpTypeDeviceEvent.
    <br/>
    <br/>Ret Event must be a pointer to OpTypeDeviceEvent which gets implicitly retained by this instruction. 
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Each Local Size operand corresponds (in order) to one OpTypePointer to Workgroup Storage Class parameter to the Invoke function, and specifies the number of bytes of Workgroup storage used to back the pointer during the execution of the Invoke function.
    */
    void visitEnqueueKernel(long resultType, long result, long queue, long flags, long nDRange, long numEvents, long waitEvents, long retEvent, long invoke, long param, long paramSize, long paramAlign, long[] locals);
    
    
    /**
    OpGetKernelNDrangeSubGroupCount
    <br/>
    <br/>Returns the number of subgroups in each workgroup of the dispatch (except for the last in cases where the global size does not divide cleanly into work-groups) given the combination of the passed NDRange descriptor specified by ND Range and the function specified by Invoke.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>The type of ND Range must be an OpTypeStruct whose members are as described by the Result Type of OpBuildNDRange.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelNDrangeSubGroupCount(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpGetKernelNDrangeMaxSubGroupSize
    <br/>
    <br/>Returns the maximum sub-group size for the function specified by Invoke and the NDRange specified by ND Range. 
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>The type of ND Range must be an OpTypeStruct whose members are as described by the Result Type of OpBuildNDRange.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelNDrangeMaxSubGroupSize(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpGetKernelWorkGroupSize
    <br/>
    <br/>Returns the maximum work-group size that can be used to execute the function specified by Invoke on the device.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelWorkGroupSize(long resultType, long result, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpGetKernelPreferredWorkGroupSizeMultiple
    <br/>
    <br/>Returns the preferred multiple of work-group size for the function specified by Invoke. This is a performance hint. Specifying a work-group size that is not a multiple of the value returned by this query as the value of the local work size will not fail to enqueue Invoke for execution unless the work-group size specified is larger than the device maximum.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelPreferredWorkGroupSizeMultiple(long resultType, long result, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpRetainEvent
    <br/>
    <br/>Increments the reference count of the event object specified by Event.
    <br/>
    <br/>Event must be an event that was produced by OpEnqueueKernel, OpEnqueueMarker or OpCreateUserEvent.
    */
    void visitRetainEvent(long event);
    
    
    /**
    OpReleaseEvent
    <br/>
    <br/>Decrements the reference count of the event object specified by Event. The event object is deleted once the event reference count is zero, the specific command identified by this event has completed (or terminated) and there are no commands in any device command queue that require a wait for this event to complete.
    <br/>
    <br/>Event must be an event that was produced by OpEnqueueKernel, OpEnqueueMarker or OpCreateUserEvent.
    */
    void visitReleaseEvent(long event);
    
    
    /**
    OpCreateUserEvent
    <br/>
    <br/>Create a user event. The execution status of the created event is set to a value of 2 (CL_SUBMITTED).
    <br/>
    <br/>Result Type must be OpTypeDeviceEvent.
    */
    void visitCreateUserEvent(long resultType, long result);
    
    
    /**
    OpIsValidEvent
    <br/>
    <br/>Returns true if the event specified by Event is a valid event, otherwise result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Event must have a type of OpTypeDeviceEvent
    */
    void visitIsValidEvent(long resultType, long result, long event);
    
    
    /**
    OpSetUserEventStatus
    <br/>
    <br/>Sets the execution status of a user event specified by Event.Status can be either 0 (CL_COMPLETE) to indicate that this kernel and all its child kernels finished execution successfully, or a negative integer value indicating an error.
    <br/>
    <br/>Event must have a type of OpTypeDeviceEvent that was produced by OpCreateUserEvent.
    <br/>
    <br/>Status must have a type of 32-bit OpTypeInt treated as a signed integer.
    */
    void visitSetUserEventStatus(long event, long status);
    
    
    /**
    OpCaptureEventProfilingInfo
    <br/>
    <br/>Captures the profiling information specified by Profiling Info for the command associated with the event specified by Event in the memory pointed to by Value.The profiling information will be available in the memory pointed to by Value once the command identified by Event has completed.
    <br/>
    <br/>Event must have a type of OpTypeDeviceEvent that was produced by OpEnqueueKernel or OpEnqueueMarker. 
    <br/>
    <br/>Profiling Info must be an integer type scalar.  The content of Profiling Info is interpreted as Kernel Profiling Info mask.
    <br/>
    <br/>Value must be a pointer to a scalar 8-bit integer type in the CrossWorkgroup Storage Class.
    <br/>
    <br/>When Profiling Info is CmdExecTime, Value must point to 128-bit memory range.
    <br/> The first 64 bits contain the elapsed time CL_PROFILING_COMMAND_END - CL_PROFILING_COMMAND_START for the command identified by Event in nanoseconds.
    <br/> The second 64 bits contain the elapsed time CL_PROFILING_COMMAND_COMPLETE - CL_PROFILING_COMMAND_START for the command identified by Event in nanoseconds.
    <br/>
    <br/>Note: The behavior of this instruction is undefined when called multiple times for the same event.
    */
    void visitCaptureEventProfilingInfo(long event, long profilingInfo, long value);
    
    
    /**
    OpGetDefaultQueue
    <br/>
    <br/>Returns the default device queue. If a default device queue has not been created, a null queue object is returned.
    <br/>
    <br/>Result Type must be an OpTypeQueue.
    */
    void visitGetDefaultQueue(long resultType, long result);
    
    
    /**
    OpBuildNDRange
    <br/>
    <br/>Given the global work size specified by GlobalWorkSize, local work size specified by LocalWorkSize and global work offset specified by GlobalWorkOffset, builds a 1D, 2D or 3D ND-range descriptor structure and returns it.
    <br/>
    <br/>Result Type must be an OpTypeStruct with the following ordered list of members, starting from the first to last:
    <br/>
    <br/>   1) 32-bit integer type scalar, that specifies the number of dimensions used to specify the global work-items and work-items in the work-group. 
    <br/>
    <br/>   2) OpTypeArray with 3 elements, where each element is 32-bit integer type scalar when the addressing model is Physical32 and 64-bit integer type scalar when the addressing model is Physical64. This member is an array of per-dimension unsigned values that describe the offset used to calculate the global ID of a work-item.
    <br/>
    <br/>   3) OpTypeArray with 3 elements, where each element is 32-bit integer type scalar when the addressing model is Physical32 and 64-bit integer type scalar when the addressing model is Physical64. This member is an array of per-dimension unsigned values that describe the number of global work-items in the dimensions that will execute the kernel function.
    <br/>
    <br/>   4) OpTypeArray with 3 elements, where each element is 32-bit integer type scalar when the addressing model is Physical32 and 64-bit integer type scalar when the addressing model is Physical64. This member is an array of per-dimension unsigned values that describe the number of work-items that make up a work-group.
    <br/>
    <br/>GlobalWorkSize must be a scalar or an array with 2 or 3 components. Where the type of each element in the array is 32-bit integer type scalar when the addressing model is Physical32 or 64-bit integer type scalar when the addressing model is Physical64.
    <br/>
    <br/>The type of LocalWorkSize must be the same as GlobalWorkSize.
    <br/>
    <br/>The type of GlobalWorkOffset must be the same as GlobalWorkSize.
    */
    void visitBuildNDRange(long resultType, long result, long globalWorkSize, long localWorkSize, long globalWorkOffset);
    
    
    /**
    OpGetKernelLocalSizeForSubgroupCount
    <br/>
    <br/>Returns the 1D local size to enqueue Invoke with Subgroup Count subgroups per workgroup.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Subgroup Count must be a 32-bit integer type scalar.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelLocalSizeForSubgroupCount(long resultType, long result, long subgroupCount, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpGetKernelMaxNumSubgroups
    <br/>
    <br/>Returns the maximum number of subgroups that can be used to execute Invoke on the devce.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Invoke must be an OpFunction whose OpTypeFunction operand has:
    <br/>- Result Type must be OpTypeVoid.
    <br/>- The first parameter must have a type of OpTypePointer to an 8-bit OpTypeInt.
    <br/>- An optional list of parameters, each of which must have a type of OpTypePointer to the Workgroup Storage Class.
    <br/>
    <br/>Param is the first parameter of the function specified by Invoke and must be a pointer to an 8-bit integer type scalar.
    <br/>
    <br/>Param Size is the size in bytes of the memory pointed to by Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    <br/>
    <br/>Param Align is the alignment of Param and must be a 32-bit integer type scalar, which is treated as an unsigned integer.
    */
    void visitGetKernelMaxNumSubgroups(long resultType, long result, long invoke, long param, long paramSize, long paramAlign);
    
    
    /**
    OpReadPipe
    <br/>
    <br/>Read a packet from the pipe object specified by Pipe into Pointer. Result is 0 if the operation is successful and a negative value if the pipe is empty.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Pointer must have a type of OpTypePointer with the same data type as Pipe and a Generic Storage Class.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitReadPipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment);
    
    
    /**
    OpWritePipe
    <br/>
    <br/>Write a packet from Pointer to the pipe object specified by Pipe. Result is 0 if the operation is successful and a negative value if the pipe is full.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Pointer must have a type of OpTypePointer with the same data type as Pipe and a Generic Storage Class.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitWritePipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment);
    
    
    /**
    OpReservedReadPipe
    <br/>
    <br/>Read a packet from the reserved area specified by Reserve Id and Index of the pipe object specified by Pipe into Pointer. The reserved pipe entries are referred to by indices that go from 0 &#8230; Num Packets - 1. Result is 0 if the operation is successful and a negative value otherwise.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Index must be a 32-bit integer type scalar, which is treated as an unsigned value.
    <br/>
    <br/>Pointer must have a type of OpTypePointer with the same data type as Pipe and a Generic Storage Class.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitReservedReadPipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment);
    
    
    /**
    OpReservedWritePipe
    <br/>
    <br/>Write a packet from Pointer into the reserved area specified by Reserve Id and Index of the pipe object specified by Pipe. The reserved pipe entries are referred to by indices that go from 0 &#8230; Num Packets - 1. Result is 0 if the operation is successful and a negative value otherwise.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Index must be a 32-bit integer type scalar, which is treated as an unsigned value.
    <br/>
    <br/>Pointer must have a type of OpTypePointer with the same data type as Pipe and a Generic Storage Class.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitReservedWritePipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment);
    
    
    /**
    OpReserveReadPipePackets
    <br/>
    <br/>Reserve Num Packets entries for reading from the pipe object specified by Pipe. Result is a valid reservation ID if the reservation is successful.
    <br/>
    <br/>Result Type must be an OpTypeReserveId.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Num Packets must be a 32-bit integer type scalar, which is treated as an unsigned value.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitReserveReadPipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment);
    
    
    /**
    OpReserveWritePipePackets
    <br/>
    <br/>Reserve num_packets entries for writing to the pipe object specified by Pipe. Result is a valid reservation ID if the reservation is successful.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Num Packets must be a 32-bit OpTypeInt which is treated as an unsigned value.
    <br/>
    <br/>Result Type must be an OpTypeReserveId.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitReserveWritePipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment);
    
    
    /**
    OpCommitReadPipe
    <br/>
    <br/>Indicates that all reads to Num Packets associated with the reservation specified by Reserve Id and the pipe object specified by Pipe are completed.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitCommitReadPipe(long pipe, long reserveId, long packetSize, long packetAlignment);
    
    
    /**
    OpCommitWritePipe
    <br/>
    <br/>Indicates that all writes to Num Packets associated with the reservation specified by Reserve Id and the pipe object specified by Pipe are completed.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitCommitWritePipe(long pipe, long reserveId, long packetSize, long packetAlignment);
    
    
    /**
    OpIsValidReserveId
    <br/>
    <br/>Return true if Reserve Id is a valid reservation id and false otherwise.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    */
    void visitIsValidReserveId(long resultType, long result, long reserveId);
    
    
    /**
    OpGetNumPipePackets
    <br/>
    <br/>Result is the number of available entries in the pipe object specified by Pipe. The number of available entries in a pipe is a dynamic value.  The value returned should be considered immediately stale.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar, which should be treated as an unsigned value.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly or WriteOnly access qualifier.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGetNumPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment);
    
    
    /**
    OpGetMaxPipePackets
    <br/>
    <br/>Result is the maximum number of packets specified when the pipe object specified by Pipe was created.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar, which should be treated as an unsigned value.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly or WriteOnly access qualifier.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGetMaxPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment);
    
    
    /**
    OpGroupReserveReadPipePackets
    <br/>
    <br/>Reserve Num Packets entries for reading from the pipe object specified by Pipe at group level. Result is a valid reservation id if the reservation is successful.
    <br/>
    <br/>The reserved pipe entries are referred to by indices that go from 0 &#8230; Num Packets - 1.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be an OpTypeReserveId.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Num Packets must be a 32-bit integer type scalar, which is treated as an unsigned value.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGroupReserveReadPipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment);
    
    
    /**
    OpGroupReserveWritePipePackets
    <br/>
    <br/>Reserve Num Packets entries for writing to the pipe object specified by Pipe at group level. Result is a valid reservation ID if the reservation is successful.
    <br/>
    <br/>The reserved pipe entries are referred to by indices that go from 0 &#8230; Num Packets - 1.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be an OpTypeReserveId.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Num Packets must be a 32-bit integer type scalar, which is treated as an unsigned value.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGroupReserveWritePipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment);
    
    
    /**
    OpGroupCommitReadPipe
    <br/>
    <br/>A group level indication that all reads to Num Packets associated with the reservation specified by Reserve Id to the pipe object specified by Pipe are completed.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with ReadOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGroupCommitReadPipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment);
    
    
    /**
    OpGroupCommitWritePipe
    <br/>
    <br/>A group level indication that all writes to Num Packets associated with the reservation specified by Reserve Id to the pipe object specified by Pipe are completed.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Pipe must have a type of OpTypePipe with WriteOnly access qualifier.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    */
    void visitGroupCommitWritePipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment);
    
    
    /**
    OpConstantPipeStorage
    <br/>
    <br/>Creates a pipe-storage object.
    <br/>
    <br/>Result Type must be OpTypePipeStorage.
    <br/>
    <br/>Packet Size must be a 32-bit integer type scalar that represents the size in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Alignment must be a 32-bit integer type scalar that presents the alignment in bytes of each packet in the pipe.
    <br/>
    <br/>Packet Size and Packet Alignment must satisfy the following: 
    <br/> - 1 &lt;= Packet Alignment &lt;= Packet Size. 
    <br/> - Packet Alignment must evenly divide Packet Size
    <br/>
    <br/>For concrete types, Packet Alignment should equal Packet Size.  For aggregate types, Packet Alignment should be the size of the largest primitive type in the hierarchy of types.
    <br/>
    <br/>Capacity is the minimum number of Packet Size blocks the resulting OpTypePipeStorage can hold.
    */
    void visitConstantPipeStorage(long resultType, long result, long size, long alignment, long capacity);
    
    
    /**
    OpCreatePipeFromPipeStorage
    <br/>
    <br/>Creates a pipe object from a pipe-storage object.
    <br/>
    <br/>Result Type must be OpTypePipe.
    <br/>
    <br/>Pipe Storage must be a pipe-storage object created from OpConstantPipeStorage.
    <br/>
    <br/>Qualifier is the pipe access qualifier.
    */
    void visitCreatePipeFromPipeStorage(long resultType, long result, long pipeStorage);
    
    
    /**
    OpGroupNonUniformElect
    <br/>
    <br/>Result is true only in the active invocation with the lowest id in the group, otherwise result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    */
    void visitGroupNonUniformElect(long resultType, long result, long scopeExecution);
    
    
    /**
    OpGroupNonUniformAll
    <br/>
    <br/>Evaluates a predicate for all active invocations in the group, resulting in true if predicate evaluates to true for all active invocations in the group, otherwise the result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Predicate must be a Boolean type.
    */
    void visitGroupNonUniformAll(long resultType, long result, long scopeExecution, long predicate);
    
    
    /**
    OpGroupNonUniformAny
    <br/>
    <br/>Evaluates a predicate for all active invocations in the group, resulting in true if predicate evaluates to true for any active invocation in the group, otherwise the result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Predicate must be a Boolean type.
    */
    void visitGroupNonUniformAny(long resultType, long result, long scopeExecution, long predicate);
    
    
    /**
    OpGroupNonUniformAllEqual
    <br/>
    <br/>Evaluates a value for all active invocations in the group, resulting in true if value is equal for all active invocations in the group, otherwise the result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Value  must be a scalar or vector of floating-point type, integer type, or Boolean type.
    */
    void visitGroupNonUniformAllEqual(long resultType, long result, long scopeExecution, long value);
    
    
    /**
    OpGroupNonUniformBroadcast
    <br/>
    <br/>Return the Value of the invocation identified by the id Id to all active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Id  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/> Id must come from a constant instruction. 
    <br/>
    <br/>The resulting value is undefined if Id is an inactive invocation, or is greater than or equal to the size of the group.
    */
    void visitGroupNonUniformBroadcast(long resultType, long result, long scopeExecution, long value, long id);
    
    
    /**
    OpGroupNonUniformBroadcastFirst
    <br/>
    <br/>Return the Value of the invocation from the active invocation with the lowest id in the group to all active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type.
    */
    void visitGroupNonUniformBroadcastFirst(long resultType, long result, long scopeExecution, long value);
    
    
    /**
    OpGroupNonUniformBallot
    <br/>
    <br/>Returns a bitfield value combining the Predicate value from all invocations in the group that execute the same dynamic instance of this instruction. The bit is set to one if the corresponding invocation is active and the Predicate for that invocation evaluated to true; otherwise, it is set to zero.
    <br/>
    <br/>Result Type  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Result is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Predicate must be a Boolean type.
    */
    void visitGroupNonUniformBallot(long resultType, long result, long scopeExecution, long predicate);
    
    
    /**
    OpGroupNonUniformInverseBallot
    <br/>
    <br/>Evaluates a value for all active invocations in the group, resulting in true if the bit in Value for the corresponding invocation is set to one, otherwise the result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Value  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Value must be the same for all invocations that execute the same dynamic instance of this instruction.
    <br/>
    <br/>Value is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations.
    */
    void visitGroupNonUniformInverseBallot(long resultType, long result, long scopeExecution, long value);
    
    
    /**
    OpGroupNonUniformBallotBitExtract
    <br/>
    <br/>Evaluates a value for all active invocations in the group, resulting in true if the bit in Value that corresponds to Index is set to one, otherwise the result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Value  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Value is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations. 
    <br/>
    <br/>Index  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>The resulting value is undefined if Index is an inactive invocation, or is greater than or equal to the size of the group.
    */
    void visitGroupNonUniformBallotBitExtract(long resultType, long result, long scopeExecution, long value, long index);
    
    
    /**
    OpGroupNonUniformBallotBitCount
    <br/>
    <br/>A group operation that returns the number of bits that are set to 1 in Value, only considering the bits in Value required to represent all bits of the group&#8217;s invocations.
    <br/>
    <br/>Result Type  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. 
    <br/>
    <br/>Value  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Value is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations.
    */
    void visitGroupNonUniformBallotBitCount(long resultType, long result, long scopeExecution, long operation, long value);
    
    
    /**
    OpGroupNonUniformBallotFindLSB
    <br/>
    <br/>Find the least significant bit set to 1 in Value, considering only the bits in Value required to represent all bits of the group&#8217;s invocations. If none of the considered bits is set to 1, the result is undefined.
    <br/>
    <br/>Result Type  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Value  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Value is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations.
    */
    void visitGroupNonUniformBallotFindLSB(long resultType, long result, long scopeExecution, long value);
    
    
    /**
    OpGroupNonUniformBallotFindMSB
    <br/>
    <br/>Find the most significant bit set to 1 in Value, considering only the bits in Value required to represent all bits of the group&#8217;s invocations.  If none of the considered bits is set to 1, the result is undefined.
    <br/>
    <br/>Result Type  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>Value  must be a vector of four components of integer type scalar, whose Signedness operand is 0. 
    <br/>
    <br/>Value is a set of bitfields where the first invocation is represented in the lowest bit of the first vector component and the last (up to the size of the group) is the higher bit number of the last bitmask needed to represent all bits of the group invocations.
    */
    void visitGroupNonUniformBallotFindMSB(long resultType, long result, long scopeExecution, long value);
    
    
    /**
    OpGroupNonUniformShuffle
    <br/>
    <br/>Return the Value of the invocation identified by the id Id.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Id  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>The resulting value is undefined if Id is an inactive invocation, or is greater than or equal to the size of the group.
    */
    void visitGroupNonUniformShuffle(long resultType, long result, long scopeExecution, long value, long id);
    
    
    /**
    OpGroupNonUniformShuffleXor
    <br/>
    <br/>Return the Value of the invocation identified by the current invocation&#8217;s id within the group xor&#8217;ed with Mask.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Mask  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>The resulting value is undefined if current invocation&#8217;s id within the group xor&#8217;ed with Mask is an inactive invocation, or is greater than or equal to the size of the group. 
    <br/>
    <br/>Mask must evaluate to a power of 2.
    */
    void visitGroupNonUniformShuffleXor(long resultType, long result, long scopeExecution, long value, long mask);
    
    
    /**
    OpGroupNonUniformShuffleUp
    <br/>
    <br/>Return the Value of the invocation identified by the current invocation&#8217;s id within the group - Delta.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Delta  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>The resulting value is undefined if current invocation&#8217;s id within the group - Delta is an inactive invocation, or is greater than or equal to the size of the group.
    */
    void visitGroupNonUniformShuffleUp(long resultType, long result, long scopeExecution, long value, long delta);
    
    
    /**
    OpGroupNonUniformShuffleDown
    <br/>
    <br/>Return the Value of the invocation identified by the current invocation&#8217;s id within the group + Delta.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Delta  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>The resulting value is undefined if current invocation&#8217;s id within the group + Delta is an inactive invocation, or is greater than or equal to the size of the group.
    */
    void visitGroupNonUniformShuffleDown(long resultType, long result, long scopeExecution, long value, long delta);
    
    
    /**
    OpGroupNonUniformIAdd
    <br/>
    <br/>An integer add group operation of all Value operands contributed active by invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformIAdd(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformFAdd
    <br/>
    <br/>A floating point add group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformFAdd(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformIMul
    <br/>
    <br/>An integer multiply group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 1. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformIMul(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformFMul
    <br/>
    <br/>A floating point multiply group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 1. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformFMul(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformSMin
    <br/>
    <br/>A signed integer minimum group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is INT_MAX. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformSMin(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformUMin
    <br/>
    <br/>An unsigned integer minimum group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is UINT_MAX. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformUMin(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformFMin
    <br/>
    <br/>A floating point minimum group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is +INF. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformFMin(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformSMax
    <br/>
    <br/>A signed integer maximum group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is INT_MIN. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformSMax(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformUMax
    <br/>
    <br/>An unsigned integer maximum group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type, whose Signedness operand is 0. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformUMax(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformFMax
    <br/>
    <br/>A floating point maximum group operation of all Value operands contributed by active invocations in by group.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is -INF. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformFMax(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformBitwiseAnd
    <br/>
    <br/>A bitwise and group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is ~0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformBitwiseAnd(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformBitwiseOr
    <br/>
    <br/>A bitwise or group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformBitwiseOr(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformBitwiseXor
    <br/>
    <br/>A bitwise xor group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of integer type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformBitwiseXor(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformLogicalAnd
    <br/>
    <br/>A logical and group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is ~0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformLogicalAnd(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformLogicalOr
    <br/>
    <br/>A logical or group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformLogicalOr(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformLogicalXor
    <br/>
    <br/>A logical xor group operation of all Value operands contributed by active invocations in the group.
    <br/>
    <br/>Result Type  must be a scalar or vector of Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/>The identity I for Operation is 0. If Operation is ClusteredReduce, ClusterSize must be specified. 
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>ClusterSize is the size of cluster to use. ClusterSize must be a scalar of integer type, whose Signedness operand is 0. ClusterSize must come from a constant instruction. ClusterSize must be at least 1, and must be a power of 2. If ClusterSize is greater than the declared SubGroupSize, executing this instruction results in undefined behavior.
    */
    void visitGroupNonUniformLogicalXor(long resultType, long result, long scopeExecution, long operation, long value, long optionalLong);
    
    
    /**
    OpGroupNonUniformQuadBroadcast
    <br/>
    <br/>Return the Value of the invocation within the quad whose SubgroupLocalInvocationId % 4 is equal to Index.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Index  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/> Index must come from a constant instruction. 
    <br/>
    <br/>If the value of Index is greater or equal to 4, an undefined result is returned.
    */
    void visitGroupNonUniformQuadBroadcast(long resultType, long result, long scopeExecution, long value, long index);
    
    
    /**
    OpGroupNonUniformQuadSwap
    <br/>
    <br/>Swap the Value of the invocation within the quad with another invocation in the quad using Direction.
    <br/>
    <br/>Result Type  must be a scalar or vector of floating-point type, integer type, or Boolean type. 
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of Value must be the same as Result Type. 
    <br/>
    <br/>Direction is the kind of swap to perform.
    <br/>
    <br/>Direction  must be a scalar of integer type, whose Signedness operand is 0. 
    <br/>
    <br/> Direction must come from a constant instruction. 
    <br/>
    <br/>The value of Direction is evaluated such that:
    <br/>0 indicates a horizontal swap within the quad.
    <br/>1 indicates a vertical swap within the quad.
    <br/>2 indicates a diagonal swap within the quad.
    */
    void visitGroupNonUniformQuadSwap(long resultType, long result, long scopeExecution, long value, long direction);
    
    
    /**
    OpGroupNonUniformPartitionNV
    <br/>
    <br/>TBD
    */
    void visitGroupNonUniformPartitionNV(long resultType, long result, long value);
    
}
