// Auto-generated from org.jglr.sbm.generation.SPIRVCodeWriterGenerator
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

public class CodeWriter implements CodeVisitor, Opcodes {
    
    protected ByteArray buffer;
    
    
    public  CodeWriter() {
        buffer = new ByteArray();
        buffer.setByteOrder(ByteOrder.BIG_ENDIAN);
    }
    
    public ByteArray getBuffer() {
        return buffer;
    }
    
    private void newOpcode(int opcode, int argCount) {
        buffer.putUnsignedInt(((long)(argCount+1) << 16L | (opcode & 0xFFFF)));
    }
    
    public byte[] toBytes() {
        return buffer.backingArray();
    }
    
    @Override
    public void visitEnd() {
        
    }
    
    @Override
    public void reset() {
        buffer.reset();
    }
    
    private void writeChars(String chars) {
        ByteOrder order = buffer.getByteOrder();
        buffer.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        buffer.putChars(chars);
        buffer.setByteOrder(order);
    }
    
    private int sizeOf(String string) {
        if(string == null)
            return 0;
        int size = (int) Math.ceil((string.length()) / 4f);
        try {
            byte[] bytes = string.getBytes("UTF-8");
            if(bytes.length % 4 == 0)
                size++;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return size;
        
    }
    
    /**
    OpNop
    <br/>
    <br/> This has no semantic impact and can safely be removed from a module.
    */
    @Override
    public void visitNop() {
        newOpcode(OpNop, 0);
    }
    
    /**
    OpUndef
    <br/>
    <br/>Make an intermediate object whose value is undefined.
    <br/>
    <br/>Result Type is the type of object to make.
    <br/>
    <br/>Each consumption of Result &lt;id&gt; yields an arbitrary, possibly different bit pattern.
    */
    @Override
    public void visitUndef(long resultType, long result) {
        newOpcode(OpUndef, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpSizeOf
    <br/>
    <br/>Computes the run-time size of the type pointed to by Pointer
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>Pointer must point to a concrete type.
    */
    @Override
    public void visitSizeOf(long resultType, long result, long pointer) {
        newOpcode(OpSizeOf, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        
    }
    
    /**
    OpSourceContinued
    <br/>
    <br/>Continue specifying the Source text from the previous instruction. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Continued Source is a continuation of the source text in the previous Source.
    <br/>
    <br/>The previous instruction must be an OpSource or an OpSourceContinued instruction. As is true for all literal strings, the previous instruction&#8217;s string was nul terminated. That terminating 0 word from the previous instruction is not part of the source text; the first character of Continued Source logically immediately follows the last character of Source before its nul.
    */
    @Override
    public void visitSourceContinued(String source) {
        newOpcode(OpSourceContinued, sizeOf(source));
        writeChars(source);
        
    }
    
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
    @Override
    public void visitSource(SourceLanguage sourceLanguage, long version, long optionalLong, String optionalString) {
        newOpcode(OpSource, 1 + 1 + (optionalLong == -1 ? 0 : 1) + (optionalString != null ? sizeOf(optionalString) : 0));
        buffer.putUnsignedInt(sourceLanguage.ordinal());
        buffer.putUnsignedInt(version);
        if(optionalLong != -1) {
        buffer.putUnsignedInt(optionalLong);
            
        }
        if(optionalString != null) {
        writeChars(optionalString);
            
        }
        
    }
    
    /**
    OpSourceExtension
    <br/>
    <br/>Document an extension to the source language. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Extension is a string describing a source-language extension. Its form is dependent on the how the source language describes extensions.
    */
    @Override
    public void visitSourceExtension(String extension) {
        newOpcode(OpSourceExtension, sizeOf(extension));
        writeChars(extension);
        
    }
    
    /**
    OpName
    <br/>
    <br/>Assign a name string to another instruction&#8217;s Result &lt;id&gt;. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Target is the Result &lt;id&gt; to assign a name to. It can be the Result &lt;id&gt; of any other instruction; a variable, function, type, intermediate result, etc.
    <br/>
    <br/>Name is the string to assign.
    */
    @Override
    public void visitName(long target, String name) {
        newOpcode(OpName, 1 + sizeOf(name));
        buffer.putUnsignedInt(target);
        writeChars(name);
        
    }
    
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
    @Override
    public void visitMemberName(long type, long member, String name) {
        newOpcode(OpMemberName, 1 + 1 + sizeOf(name));
        buffer.putUnsignedInt(type);
        buffer.putUnsignedInt(member);
        writeChars(name);
        
    }
    
    /**
    OpString
    <br/>
    <br/>Assign a Result &lt;id&gt; to a string for use by other debug instructions (see OpLine and OpSource). This has no semantic impact and can safely be removed from a module. (Removal also requires removal of all instructions referencing Result &lt;id&gt;.)
    <br/>
    <br/>String is the literal string being assigned a Result &lt;id&gt;.
    */
    @Override
    public void visitString(long result, String string) {
        newOpcode(OpString, 1 + sizeOf(string));
        buffer.putUnsignedInt(result);
        writeChars(string);
        
    }
    
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
    @Override
    public void visitLine(long file, long line, long column) {
        newOpcode(OpLine, 1 + 1 + 1);
        buffer.putUnsignedInt(file);
        buffer.putUnsignedInt(line);
        buffer.putUnsignedInt(column);
        
    }
    
    /**
    OpNoLine
    <br/>
    <br/>Discontinue any source-level location information that might be active from a previous OpLine instruction. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>This instruction can only appear after the annotation instructions (see the Logical Layout section). It cannot be the last instruction in a block, or the second-to-last instruction if the block has a merge instruction. There is not a requirement that there is a preceding OpLine instruction.
    */
    @Override
    public void visitNoLine() {
        newOpcode(OpNoLine, 0);
    }
    
    /**
    OpModuleProcessed
    <br/>
    <br/>Document a process that was applied to a module. This has no semantic impact and can safely be removed from a module.
    <br/>
    <br/>Process is a string describing a process and/or tool (processor) that did the processing. Its form is dependent on the processor.
    */
    @Override
    public void visitModuleProcessed(String process) {
        newOpcode(OpModuleProcessed, sizeOf(process));
        writeChars(process);
        
    }
    
    /**
    OpExtension
    <br/>
    <br/>Declare use of an extension to SPIR-V. This allows validation of additional instructions, tokens, semantics, etc.
    <br/>
    <br/>Name is the extension&#8217;s name string.
    */
    @Override
    public void visitExtension(String name) {
        newOpcode(OpExtension, sizeOf(name));
        writeChars(name);
        
    }
    
    /**
    OpExtInstImport
    <br/>
    <br/>Import an extended set of instructions. It can be later referenced by the Result &lt;id&gt;.
    <br/>
    <br/>Name is the extended instruction-set&#8217;s name string. There must be an external specification defining the semantics for this extended instruction set.
    <br/>
    <br/>See Extended Instruction Sets for more information.
    */
    @Override
    public void visitExtendedInstructionSetImport(long result, String name) {
        newOpcode(OpExtInstImport, 1 + sizeOf(name));
        buffer.putUnsignedInt(result);
        writeChars(name);
        
    }
    
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
    @Override
    public void visitExecExtendedInstruction(long resultType, long result, long set, long instruction, long[] operands) {
        newOpcode(OpExtInst, 1 + 1 + 1 + 1 + operands.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(set);
        buffer.putUnsignedInt(instruction);
        buffer.putUnsignedInts(operands);
        
    }
    
    /**
    OpMemoryModel
    <br/>
    <br/>Set addressing model and memory model for the entire module.
    <br/>
    <br/>Addressing Model selects the module&#8217;s Addressing Model.
    <br/>
    <br/>Memory Model selects the module&#8217;s memory model, see Memory Model.
    */
    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        newOpcode(OpMemoryModel, 1 + 1);
        buffer.putUnsignedInt(addressingModel.ordinal());
        buffer.putUnsignedInt(memoryModel.ordinal());
        
    }
    
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
    @Override
    public void visitEntryPoint(ExecutionModel executionModel, long entryPoint, String name, long[] interfaces) {
        newOpcode(OpEntryPoint, 1 + 1 + sizeOf(name) + interfaces.length);
        buffer.putUnsignedInt(executionModel.ordinal());
        buffer.putUnsignedInt(entryPoint);
        writeChars(name);
        buffer.putUnsignedInts(interfaces);
        
    }
    
    /**
    OpExecutionMode
    <br/>
    <br/>Declare an execution mode for an entry point.
    <br/>
    <br/>Entry Point must be the Entry Point &lt;id&gt; operand of an OpEntryPoint instruction.
    <br/>
    <br/>Mode is the execution mode. See Execution Mode.
    */
    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {
        newOpcode(OpExecutionMode, 1 + 1);
        buffer.putUnsignedInt(entryPoint);
        buffer.putUnsignedInt(mode.getType().ordinal());
        buffer.putUnsignedInts(mode.getOperands());
        
    }
    
    /**
    OpCapability
    <br/>
    <br/>Declare a capability used by this module.
    <br/>
    <br/>Capability is the capability declared by this instruction.  There are no restrictions on the order in which capabilities are declared.
    <br/>
    <br/>See the capabilities section for more detail.
    */
    @Override
    public void visitCapability(Capability capability) {
        newOpcode(OpCapability, 1);
        buffer.putUnsignedInt(capability.ordinal());
        
    }
    
    /**
    OpTypeVoid
    <br/>
    <br/>Declare the void type.
    */
    @Override
    public void visitVoidType(long result) {
        newOpcode(OpTypeVoid, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeBool
    <br/>
    <br/>Declare the Boolean type.  Values of this type can only be either true or false. There is no physical size or bit pattern defined for these values.  If they are stored (in conjunction with OpVariable), they can only be used with logical addressing operations, not physical, and only with non-externally visible shader Storage Classes: Workgroup, CrossWorkgroup, Private, and Function.
    */
    @Override
    public void visitBoolType(long result) {
        newOpcode(OpTypeBool, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeInt
    <br/>
    <br/>Declare a new integer type.
    <br/>
    <br/>Width specifies how many bits wide the type is. This literal operand is limited to a single word. The bit pattern of a signed integer value is two&#8217;s complement.
    <br/>
    <br/>Signedness specifies whether there are signed semantics to preserve or validate.
    <br/>0 indicates unsigned, or no signedness semantics
    <br/>1 indicates signed semantics.
    <br/>In all cases, the type of operation of an instruction comes from the instruction&#8217;s opcode, not the signedness of the operands.
    */
    @Override
    public void visitIntType(long result, long width, boolean signedness) {
        newOpcode(OpTypeInt, 1 + 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(width);
        buffer.putUnsignedInt(signedness ? 1 : 0);
        
    }
    
    /**
    OpTypeFloat
    <br/>
    <br/>Declare a new floating-point type.
    <br/>
    <br/>Width specifies how many bits wide the type is. The bit pattern of a floating-point value is as described by the IEEE 754 standard.
    */
    @Override
    public void visitFloatType(long result, long width) {
        newOpcode(OpTypeFloat, 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(width);
        
    }
    
    /**
    OpTypeVector
    <br/>
    <br/>Declare a new vector type.
    <br/>
    <br/>Component Type is the type of each component in the resulting type.  It must be a scalar type.
    <br/>
    <br/>Component Count is the number of components in the resulting type.  It must be at least 2.
    <br/>
    <br/>Components are numbered consecutively, starting with 0.
    */
    @Override
    public void visitVectorType(long result, long componentType, long count) {
        newOpcode(OpTypeVector, 1 + 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(componentType);
        buffer.putUnsignedInt(count);
        
    }
    
    /**
    OpTypeMatrix
    <br/>
    <br/>Declare a new matrix type.
    <br/>
    <br/>Column Type is the type of each column in the matrix.  It must be vector type.
    <br/>
    <br/>Column Count is the number of columns in the new matrix type. It must be at least 2.
    <br/>
    <br/>Matrix columns are numbered consecutively, starting with 0. This is true independently of any Decorations describing the memory layout of a matrix (e.g., RowMajor or MatrixStride).
    */
    @Override
    public void visitMatrixType(long result, long columnType, long count) {
        newOpcode(OpTypeMatrix, 1 + 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(columnType);
        buffer.putUnsignedInt(count);
        
    }
    
    /**
    OpTypeImage
    <br/>
    <br/>Declare a new image type. Consumed, for example, by OpTypeSampledImage. This type is opaque: values of this type have no defined physical size or bit pattern.
    <br/>
    <br/>Sampled Type is the type of the components that result from sampling or reading from this image type. Must be a scalar numerical type or OpTypeVoid.
    <br/>
    <br/>Dim is the image dimensionality (Dim).
    <br/>
    <br/>Depth is whether or not this image is a depth image. (Note that whether or not depth comparisons are actually done is a property of the sampling opcode, not of this type declaration.)
    <br/>0 indicates not a depth image
    <br/>1 indicates a depth image
    <br/>2 means no indication as to whether this is a depth or non-depth image
    <br/>
    <br/>Arrayed must be one of the following indicated values:
    <br/>0 indicates non-arrayed content
    <br/>1 indicates arrayed content
    <br/>
    <br/>MS must be one of the following indicated values:
    <br/>0 indicates single-sampled content
    <br/>1 indicates multisampled content
    <br/>
    <br/>Sampled indicates whether or not this image will be accessed in combination with a sampler, and must be one of the following values:
    <br/>0 indicates this is only known at run time, not at compile time
    <br/>1 indicates will be used with sampler
    <br/>2 indicates will be used without a sampler (a storage image)
    <br/>
    <br/>Image Format is the Image Format, which can be Unknown, depending on the client API.
    <br/>
    <br/>If Dim is SubpassData, Sampled must be 2, Image Format must be Unknown, and the Execution Model must be Fragment.
    <br/>
    <br/>Access Qualifier is an image Access Qualifier.
    */
    @Override
    public void visitImageType(long result, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean mS, Sampling sampled, ImageFormat imageFormat, AccessQualifier optionalAccessQualifier) {
        newOpcode(OpTypeImage, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + (optionalAccessQualifier != null ? 1 : 0));
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledType);
        buffer.putUnsignedInt(dim.ordinal());
        buffer.putUnsignedInt(depth.ordinal());
        buffer.putUnsignedInt(arrayed ? 1 : 0);
        buffer.putUnsignedInt(mS ? 1 : 0);
        buffer.putUnsignedInt(sampled.ordinal());
        buffer.putUnsignedInt(imageFormat.ordinal());
        if(optionalAccessQualifier != null) {
        buffer.putUnsignedInt(optionalAccessQualifier.ordinal());
            
        }
        
    }
    
    /**
    OpTypeSampler
    <br/>
    <br/>Declare the sampler type. Consumed by OpSampledImage. This type is opaque: values of this type have no defined physical size or bit pattern.
    */
    @Override
    public void visitSamplerType(long result) {
        newOpcode(OpTypeSampler, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeSampledImage
    <br/>
    <br/>Declare a sampled image type, the Result Type of OpSampledImage, or an externally combined sampler and image. This type is opaque: values of this type have no defined physical size or bit pattern.
    <br/>
    <br/>Image Type must be an OpTypeImage.  It is the type of the image in the combined sampler and image type.
    */
    @Override
    public void visitSampledImageType(long result, long imageType) {
        newOpcode(OpTypeSampledImage, 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(imageType);
        
    }
    
    /**
    OpTypeArray
    <br/>
    <br/>Declare a new array type: a dynamically-indexable ordered aggregate of elements all having the same type.
    <br/>
    <br/>Element Type is the type of each element in the array.
    <br/>
    <br/>Length is the number of elements in the array.  It must be at least 1. Length must come from a constant instruction of an integer-type scalar whose value is at least 1.
    <br/>
    <br/>Array elements are number consecutively, starting with 0.
    */
    @Override
    public void visitArrayType(long result, long elementType, long length) {
        newOpcode(OpTypeArray, 1 + 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(elementType);
        buffer.putUnsignedInt(length);
        
    }
    
    /**
    OpTypeRuntimeArray
    <br/>
    <br/>Declare a new run-time array type.  Its length is not known at compile time.
    <br/>
    <br/>Element Type is the type of each element in the array. It must be a concrete type.
    <br/>
    <br/> See OpArrayLength for getting the Length of an array of this type.
    <br/>
    <br/>Objects of this type can only be created with OpVariable using the Uniform Storage Class.
    */
    @Override
    public void visitRuntimeArrayType(long result, long elementType) {
        newOpcode(OpTypeRuntimeArray, 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(elementType);
        
    }
    
    /**
    OpTypeStruct
    <br/>
    <br/>Declare a new structure type: an aggregate of potentially heterogeneous members.
    <br/>
    <br/>Member N type is the type of member N of the structure. The first member is member 0, the next is member 1, &#8230;
    <br/>
    <br/>If an operand is not yet defined, it must be defined by an OpTypePointer, where the type pointed to is an OpTypeStruct.
    */
    @Override
    public void visitStructType(long result, long[] members) {
        newOpcode(OpTypeStruct, 1 + members.length);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(members);
        
    }
    
    /**
    OpTypeOpaque
    <br/>
    <br/>Declare a structure type with no body specified.
    */
    @Override
    public void visitOpaqueType(long result, String type) {
        newOpcode(OpTypeOpaque, 1 + sizeOf(type));
        buffer.putUnsignedInt(result);
        writeChars(type);
        
    }
    
    /**
    OpTypePointer
    <br/>
    <br/>Declare a new pointer type.
    <br/>
    <br/>Storage Class is the Storage Class of the memory holding the object pointed to. If there was a forward reference to this type from an OpTypeForwardPointer, the Storage Class of that instruction must equal the Storage Class of this instruction.
    <br/>
    <br/>Type is the type of the object pointed to.
    */
    @Override
    public void visitPointerType(long result, StorageClass storageClass, long type) {
        newOpcode(OpTypePointer, 1 + 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(storageClass.ordinal());
        buffer.putUnsignedInt(type);
        
    }
    
    /**
    OpTypeFunction
    <br/>
    <br/>Declare a new function type.
    <br/>
    <br/>OpFunction will use this to declare the return type and parameter types of a function. OpFunction is the only valid use of OpTypeFunction.
    <br/>
    <br/>Return Type is the type of the return value of functions of this type. It must be a concrete or abstract type, or a pointer to such a type. If the function has no return value, Return Type must be OpTypeVoid.
    <br/>
    <br/>Parameter N Type is the type &lt;id&gt; of the type of parameter N.
    */
    @Override
    public void visitFunctionType(long result, long returnType, long[] parameters) {
        newOpcode(OpTypeFunction, 1 + 1 + parameters.length);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(returnType);
        buffer.putUnsignedInts(parameters);
        
    }
    
    /**
    OpTypeEvent
    <br/>
    <br/>Declare an OpenCL event type.
    */
    @Override
    public void visitEventType(long result) {
        newOpcode(OpTypeEvent, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeDeviceEvent
    <br/>
    <br/>Declare an OpenCL device-side event type.
    */
    @Override
    public void visitDeviceEventType(long result) {
        newOpcode(OpTypeDeviceEvent, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeReserveId
    <br/>
    <br/>Declare an OpenCL reservation id type.
    */
    @Override
    public void visitReserveIdType(long result) {
        newOpcode(OpTypeReserveId, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeQueue
    <br/>
    <br/>Declare an OpenCL queue type.
    */
    @Override
    public void visitQueueType(long result) {
        newOpcode(OpTypeQueue, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypePipe
    <br/>
    <br/>Declare an OpenCL pipe type.
    <br/>
    <br/>Qualifier is the pipe access qualifier.
    */
    @Override
    public void visitPipeType(long result, AccessQualifier qualifier) {
        newOpcode(OpTypePipe, 1 + 1);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(qualifier.ordinal());
        
    }
    
    /**
    OpTypeForwardPointer
    <br/>
    <br/>Declare the Storage Class for a forward reference to a pointer.
    <br/>
    <br/>Pointer Type is a forward reference to the result of an OpTypePointer. The type of object the pointer points to is declared by the OpTypePointer instruction, not this instruction. Subsequent OpTypeStruct instructions can use Pointer Type as an operand.
    <br/>
    <br/>Storage Class is the Storage Class of the memory holding the object pointed to.
    */
    @Override
    public void visitForwardPointerType(long pointerType, StorageClass storageClass) {
        newOpcode(OpTypeForwardPointer, 1 + 1);
        buffer.putUnsignedInt(pointerType);
        buffer.putUnsignedInt(storageClass.ordinal());
        
    }
    
    /**
    OpTypePipeStorage
    <br/>
    <br/>Declare the OpenCL pipe-storage type.
    */
    @Override
    public void visitPipeStorageType(long result) {
        newOpcode(OpTypePipeStorage, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpTypeNamedBarrier
    <br/>
    <br/>Declare the named-barrier type.
    */
    @Override
    public void visitNamedBarrierType(long result) {
        newOpcode(OpTypeNamedBarrier, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpConstantTrue
    <br/>
    <br/>Declare a true Boolean-type scalar constant.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    */
    @Override
    public void visitConstantTrue(long resultType, long result) {
        newOpcode(OpConstantTrue, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpConstantFalse
    <br/>
    <br/>Declare a false Boolean-type scalar constant.
    <br/>
    <br/>Result Type must be the scalar Boolean type.
    */
    @Override
    public void visitConstantFalse(long resultType, long result) {
        newOpcode(OpConstantFalse, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpConstant
    <br/>
    <br/>Declare a new integer-type or floating-point-type scalar constant.
    <br/>
    <br/>Result Type must be a scalar integer type or floating-point type.
    <br/>
    <br/>Value is the bit pattern for the constant. Types 32 bits wide or smaller take one word. Larger types take multiple words, with low-order words appearing first.
    */
    @Override
    public void visitConstant(long resultType, long result, long[] value) {
        newOpcode(OpConstant, 1 + 1 + value.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(value);
        
    }
    
    /**
    OpConstantComposite
    <br/>
    <br/>Declare a new composite constant.
    <br/>
    <br/>Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the Constituents. The ordering must be the same between the top-level types in Result Type and the Constituents.
    <br/>
    <br/>Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result. The Constituents must appear in the order needed by the definition of the Result Type. The Constituents must all be &lt;id&gt;s of other constant declarations or an OpUndef.
    */
    @Override
    public void visitConstantComposite(long resultType, long result, long[] constituents) {
        newOpcode(OpConstantComposite, 1 + 1 + constituents.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(constituents);
        
    }
    
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
    @Override
    public void visitConstantSampler(long resultType, long result, SamplerAddressingMode samplerAddressingMode, boolean param, SamplerFilterMode samplerFilterMode) {
        newOpcode(OpConstantSampler, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(samplerAddressingMode.ordinal());
        buffer.putUnsignedInt(param ? 1 : 0);
        buffer.putUnsignedInt(samplerFilterMode.ordinal());
        
    }
    
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
    @Override
    public void visitConstantNull(long resultType, long result) {
        newOpcode(OpConstantNull, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
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
    @Override
    public void visitSpecConstantTrue(long resultType, long result) {
        newOpcode(OpSpecConstantTrue, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
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
    @Override
    public void visitSpecConstantFalse(long resultType, long result) {
        newOpcode(OpSpecConstantFalse, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
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
    @Override
    public void visitSpecConstant(long resultType, long result, long[] value) {
        newOpcode(OpSpecConstant, 1 + 1 + value.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(value);
        
    }
    
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
    @Override
    public void visitSpecConstantComposite(long resultType, long result, long[] constituents) {
        newOpcode(OpSpecConstantComposite, 1 + 1 + constituents.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(constituents);
        
    }
    
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
    @Override
    public void visitSpecConstantOp(long resultType, long result, long opcode, long[] operands) {
        newOpcode(OpSpecConstantOp, 1 + 1 + 1 + operands.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(opcode);
        buffer.putUnsignedInts(operands);
        
    }
    
    /**
    OpVariable
    <br/>
    <br/>Allocate an object in memory, resulting in a pointer to it, which can be used with OpLoad and OpStore.
    <br/>
    <br/> Result Type must be an OpTypePointer. Its Type operand is the type of object in memory.
    <br/>
    <br/>Storage Class is the Storage Class of the memory holding the object. It cannot be Generic.
    <br/>
    <br/>Initializer is optional.  If Initializer is present, it will be the initial value of the variable&#8217;s memory content. Initializer must be an &lt;id&gt; from a constant instruction or a global (module scope) OpVariable instruction. Initializer must have the same type as the type pointed to by Result Type.
    */
    @Override
    public void visitVariable(long resultType, long result, StorageClass storageClass, long optionalLong) {
        newOpcode(OpVariable, 1 + 1 + 1 + (optionalLong == -1 ? 0 : 1));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(storageClass.ordinal());
        if(optionalLong != -1) {
        buffer.putUnsignedInt(optionalLong);
            
        }
        
    }
    
    /**
    OpImageTexelPointer
    <br/>
    <br/>Form a pointer to a texel of an image. Use of such a pointer is limited to atomic operations.
    <br/>
    <br/>Result Type must be an OpTypePointer whose Storage Class operand is Image. Its Type operand must be a scalar numerical type or OpTypeVoid.
    <br/>
    <br/>Image must have a type of OpTypePointer with Type OpTypeImage. The Sampled Type of the type of Image must be the same as the Type pointed to by Result Type. The Dim operand of Type cannot be SubpassData.
    <br/>
    <br/>Coordinate and Sample specify which texel and sample within the image to form a pointer to.
    <br/>
    <br/>Coordinate must be a scalar or vector of integer type.  It must have the number of components specified below, given the following Arrayed and Dim operands of the type of the OpTypeImage.
    <br/>
    <br/>If Arrayed is 0:
    <br/>1D: scalar
    <br/>2D: 2 components
    <br/>3D: 3 components
    <br/>Cube: 3 components
    <br/>Rect: 2 components
    <br/>Buffer: scalar
    <br/>
    <br/>If Arrayed is 1:
    <br/>1D: 2 components
    <br/>2D: 3 components
    <br/>Cube: 4 components
    <br/>
    <br/>Sample must be an integer type scalar. It specifies which sample to select at the given coordinate.  It must be a valid &lt;id&gt; for the value 0 if the OpTypeImage has MS of 0.
    */
    @Override
    public void visitImageTexelPointer(long resultType, long result, long image, long coordinate, long sample) {
        newOpcode(OpImageTexelPointer, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(sample);
        
    }
    
    /**
    OpLoad
    <br/>
    <br/>Load through a pointer.
    <br/>
    <br/>Result Type is the type of the loaded object.
    <br/>
    <br/>Pointer is the pointer to load through.  Its type must be an OpTypePointer whose Type operand is the same as Result Type.
    <br/>
    <br/>Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
    */
    @Override
    public void visitLoad(long resultType, long result, long pointer, MemoryAccess optionalMemoryAccess) {
        newOpcode(OpLoad, 1 + 1 + 1 + (optionalMemoryAccess != null ? 1 : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        if(optionalMemoryAccess != null) {
        buffer.putUnsignedInt(optionalMemoryAccess.getMask());
            
        }
        
    }
    
    /**
    OpStore
    <br/>
    <br/>Store through a pointer.
    <br/>
    <br/>Pointer is the pointer to store through.  Its type must be an OpTypePointer whose Type operand is the same as the type of Object.
    <br/>
    <br/>Object is the object to store.
    <br/>
    <br/>Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
    */
    @Override
    public void visitStore(long pointer, long object, MemoryAccess optionalMemoryAccess) {
        newOpcode(OpStore, 1 + 1 + (optionalMemoryAccess != null ? 1 : 0));
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(object);
        if(optionalMemoryAccess != null) {
        buffer.putUnsignedInt(optionalMemoryAccess.getMask());
            
        }
        
    }
    
    /**
    OpCopyMemory
    <br/>
    <br/>Copy from the memory pointed to by Source to the memory pointed to by Target. Both operands must be non-void pointers of the same type.  Matching Storage Class is not required. The amount of memory copied is the size of the type pointed to.
    <br/>
    <br/>Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
    */
    @Override
    public void visitCopyMemory(long target, long source, MemoryAccess optionalMemoryAccess) {
        newOpcode(OpCopyMemory, 1 + 1 + (optionalMemoryAccess != null ? 1 : 0));
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(source);
        if(optionalMemoryAccess != null) {
        buffer.putUnsignedInt(optionalMemoryAccess.getMask());
            
        }
        
    }
    
    /**
    OpCopyMemorySized
    <br/>
    <br/>Copy from the memory pointed to by Source to the memory pointed to by Target. 
    <br/>
    <br/>Size is the number of bytes to copy. It must have a scalar integer type. If it is a constant instruction, the constant value cannot be 0. It is invalid for both the constant&#8217;s type to have Signedness of 1 and to have the sign bit set. Otherwise, as a run-time value, Size is treated as unsigned, and if its value is 0, no memory access will be made.
    <br/>
    <br/>Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
    */
    @Override
    public void visitCopyMemorySized(long target, long source, long size, MemoryAccess optionalMemoryAccess) {
        newOpcode(OpCopyMemorySized, 1 + 1 + 1 + (optionalMemoryAccess != null ? 1 : 0));
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(source);
        buffer.putUnsignedInt(size);
        if(optionalMemoryAccess != null) {
        buffer.putUnsignedInt(optionalMemoryAccess.getMask());
            
        }
        
    }
    
    /**
    OpAccessChain
    <br/>
    <br/>Create a pointer into a composite object that can be used with OpLoad and OpStore. 
    <br/>
    <br/> Result Type must be an OpTypePointer. Its Type operand must be the type reached by walking the Base&#8217;s type hierarchy down to the last provided index in Indexes, and its Storage Class operand must be the same as the Storage Class of Base.
    <br/>
    <br/>Base must be a pointer, pointing to the base of a composite object.
    <br/>
    <br/>Indexes walk the type hierarchy to the desired depth, potentially down to scalar granularity. The first index in Indexes will select the top-level member/element/component/element of the base composite. All composite constituents use zero-based numbering, as described by their OpType&#8230; instruction. The second index will apply similarly to that result, and so on. Once any non-composite type is reached, there must be no remaining (unused) indexes. Each of the Indexes must:
    <br/>- be a scalar integer type,
    <br/>- be an OpConstant when indexing into a structure.
    */
    @Override
    public void visitAccessChain(long resultType, long result, long base, long[] indexes) {
        newOpcode(OpAccessChain, 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInts(indexes);
        
    }
    
    /**
    OpInBoundsAccessChain
    <br/>
    <br/>Has the same semantics as OpAccessChain, with the addition that the resulting pointer is known to point within the base object.
    */
    @Override
    public void visitInBoundsAccessChain(long resultType, long result, long base, long[] indexes) {
        newOpcode(OpInBoundsAccessChain, 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInts(indexes);
        
    }
    
    /**
    OpPtrAccessChain
    <br/>
    <br/>Has the same semantics as OpAccessChain, with the addition of the Element operand.
    <br/>
    <br/>Element is used to do the initial dereference of Base: Base is treated as the address of the first element of an array, and the Element element&#8217;s address is computed to be the base for the Indexes, as per OpAccessChain. The type of Base after being dereferenced with Element is still the same as the original type of Base.
    <br/>
    <br/>Note: If Base is originally typed to be a pointer an array, and the desired operation is to select an element of that array, OpAccessChain should be directly used, as its first Index will select the array element.
    */
    @Override
    public void visitPtrAccessChain(long resultType, long result, long base, long element, long[] indexes) {
        newOpcode(OpPtrAccessChain, 1 + 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(element);
        buffer.putUnsignedInts(indexes);
        
    }
    
    /**
    OpArrayLength
    <br/>
    <br/>Length of a run-time array.
    <br/>
    <br/>Result Type must be an OpTypeInt with 32-bit Width and 0 Signedness.
    <br/>
    <br/>Structure must have a type of OpTypeStruct whose last member is a run-time array.
    <br/>
    <br/>Array member is the last member number of Structure and must have a type from OpTypeRuntimeArray.
    */
    @Override
    public void visitArrayLength(long resultType, long result, long structure, long member) {
        newOpcode(OpArrayLength, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(structure);
        buffer.putUnsignedInt(member);
        
    }
    
    /**
    OpGenericPtrMemSemantics
    <br/>
    <br/>Result is a valid Memory Semantics which includes mask bits set for the Storage Class for the specific (non-Generic) Storage Class of Pointer. 
    <br/>
    <br/>Pointer must point to Generic Storage Class.
    <br/>
    <br/>Result Type must be an OpTypeInt with 32-bit Width and 0 Signedness.
    */
    @Override
    public void visitGenericPtrMemSemantics(long resultType, long result, long pointer) {
        newOpcode(OpGenericPtrMemSemantics, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        
    }
    
    /**
    OpInBoundsPtrAccessChain
    <br/>
    <br/>Has the same semantics as OpPtrAccessChain, with the addition that the resulting pointer is known to point within the base object.
    */
    @Override
    public void visitInBoundsPtrAccessChain(long resultType, long result, long base, long element, long[] indexes) {
        newOpcode(OpInBoundsPtrAccessChain, 1 + 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(element);
        buffer.putUnsignedInts(indexes);
        
    }
    
    /**
    OpFunction
    <br/>
    <br/>Add a function.  This instruction must be immediately followed by one OpFunctionParameter instruction per each formal parameter of this function. This function&#8217;s body or declaration will terminate with the next OpFunctionEnd instruction.
    <br/>
    <br/>The Result &lt;id&gt; cannot be used generally by other instructions. It can only be used by OpFunctionCall,  OpEntryPoint, and decoration instructions.
    <br/>
    <br/>Result Type must be the same as the Return Type declared in Function Type.
    <br/>
    <br/>Function Type is the result of an OpTypeFunction, which declares the types of the return value and parameters of the function.
    */
    @Override
    public void visitFunction(long resultType, long result, FunctionControl functionControl, long functionType) {
        newOpcode(OpFunction, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(functionControl.getMask());
        buffer.putUnsignedInt(functionType);
        
    }
    
    /**
    OpFunctionParameter
    <br/>
    <br/>Declare a formal parameter of the current function.
    <br/>
    <br/>Result Type is the type of the parameter.
    <br/>
    <br/>This instruction must immediately follow an OpFunction or OpFunctionParameter instruction. The order of contiguous OpFunctionParameter instructions is the same order arguments will be listed in an OpFunctionCall instruction to this function. It is also the same order in which Parameter Type operands are listed in the OpTypeFunction of the Function Type operand for this function&#8217;s OpFunction instruction.
    */
    @Override
    public void visitFunctionParameter(long resultType, long result) {
        newOpcode(OpFunctionParameter, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpFunctionEnd
    <br/>
    <br/>Last instruction of a function.
    */
    @Override
    public void visitFunctionEnd() {
        newOpcode(OpFunctionEnd, 0);
    }
    
    /**
    OpFunctionCall
    <br/>
    <br/>Call a function.
    <br/>
    <br/>Result Type is the type of the return value of the function. It must be the same as the Return Type operand of the Function Type operand of the Function operand.
    <br/>
    <br/>Function is an OpFunction instruction.  This could be a forward reference.
    <br/>
    <br/>Argument N is the object to copy to parameter N of Function.
    <br/>
    <br/>Note: A forward call is possible because there is no missing type information: Result Type must match the Return Type of the function, and the calling argument types must match the formal parameter types.
    */
    @Override
    public void visitFunctionCall(long resultType, long result, long function, long[] arguments) {
        newOpcode(OpFunctionCall, 1 + 1 + 1 + arguments.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(function);
        buffer.putUnsignedInts(arguments);
        
    }
    
    /**
    OpSampledImage
    <br/>
    <br/>Create a sampled image, containing both a sampler and an image.
    <br/>
    <br/>Result Type must be the OpTypeSampledImage type.
    <br/>
    <br/>Image is an object whose type is an OpTypeImage, whose Sampled operand is 0 or 1, and whose Dim operand is not SubpassData.
    <br/>
    <br/>Sampler must be an object whose type is OpTypeSampler.
    */
    @Override
    public void visitSampledImage(long resultType, long result, long image, long sampler) {
        newOpcode(OpSampledImage, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(sampler);
        
    }
    
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
    @Override
    public void visitImageSampleImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSampleImplicitLod, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSampleExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSampleDrefImplicitLod, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSampleDrefExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSampleProjImplicitLod, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSampleProjExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSampleProjDrefImplicitLod, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSampleProjDrefExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
    /**
    OpImageFetch
    <br/>
    <br/>Fetch a single texel from a sampled image.
    <br/>
    <br/> Result Type must be a vector of four components of floating-point type or integer type.  Its components must be the same as Sampled Type of the underlying OpTypeImage (unless that underlying Sampled Type is OpTypeVoid).
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand cannot be Cube, and its Sampled operand must be 1.
    <br/>
    <br/>Coordinate is an integer scalar or vector containing (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    @Override
    public void visitImageFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageFetch, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageGather, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(component);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImageDrefGather
    <br/>
    <br/>Gathers the requested depth-comparison from four texels.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage. It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    @Override
    public void visitImageDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageDrefGather, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageRead, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageWrite(long image, long coordinate, long texel, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageWrite, 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(texel);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImage
    <br/>
    <br/>Extract the image from a sampled image.
    <br/>
    <br/>Result Type must be OpTypeImage.
    <br/>
    <br/>Sampled Image must have type OpTypeSampledImage whose Image Type is the same as Result Type.
    */
    @Override
    public void visitImage(long resultType, long result, long sampledImage) {
        newOpcode(OpImage, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        
    }
    
    /**
    OpImageQueryFormat
    <br/>
    <br/>Query the image format of an image created with an Unknown Image Format.
    <br/>
    <br/>Result Type must be a scalar integer type. The resulting value is an enumerant from Image Channel Data Type.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage.
    */
    @Override
    public void visitImageQueryFormat(long resultType, long result, long image) {
        newOpcode(OpImageQueryFormat, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        
    }
    
    /**
    OpImageQueryOrder
    <br/>
    <br/>Query the channel order of an image created with an Unknown Image Format.
    <br/>
    <br/>Result Type must be a scalar integer type. The resulting value is an enumerant from Image Channel Order.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage.
    */
    @Override
    public void visitImageQueryOrder(long resultType, long result, long image) {
        newOpcode(OpImageQueryOrder, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        
    }
    
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
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 1D, 2D, 3D, or Cube, and its MS must be 0. See OpImageQuerySize for querying image types without level of detail.
    <br/>
    <br/>Level of Detail is used to compute which mipmap level to query, as described in the API specification.
    */
    @Override
    public void visitImageQuerySizeLod(long resultType, long result, long image, long levelOfDetail) {
        newOpcode(OpImageQuerySizeLod, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(levelOfDetail);
        
    }
    
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
    @Override
    public void visitImageQuerySize(long resultType, long result, long image) {
        newOpcode(OpImageQuerySize, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        
    }
    
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
    @Override
    public void visitImageQueryLod(long resultType, long result, long sampledImage, long coordinate) {
        newOpcode(OpImageQueryLod, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        
    }
    
    /**
    OpImageQueryLevels
    <br/>
    <br/>Query the number of mipmap levels accessible through Image.
    <br/>
    <br/>Result Type must be a scalar integer type. The result is the number of mipmap levels, as defined by the API specification.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 1D, 2D, 3D, or Cube.
    */
    @Override
    public void visitImageQueryLevels(long resultType, long result, long image) {
        newOpcode(OpImageQueryLevels, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        
    }
    
    /**
    OpImageQuerySamples
    <br/>
    <br/>Query the number of samples available per texel fetch in a multisample image.
    <br/>
    <br/>Result Type must be a scalar integer type. The result is the number of samples.
    <br/>
    <br/>Image must be an object whose type is OpTypeImage. Its Dim operand must be one of 2D and MS of 1.
    */
    @Override
    public void visitImageQuerySamples(long resultType, long result, long image) {
        newOpcode(OpImageQuerySamples, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        
    }
    
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
    @Override
    public void visitImageSparseSampleImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseSampleImplicitLod, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSparseSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSparseSampleExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
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
    @Override
    public void visitImageSparseSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseSampleDrefImplicitLod, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSparseSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSparseSampleDrefExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
    /**
    OpImageSparseSampleProjImplicitLod
    <br/>
    <br/>Instruction reserved for future use.  Use of this instruction is invalid.
    <br/>
    <br/>Sample a sparse image with a projective coordinate and an implicit level of detail.
    */
    @Override
    public void visitImageSparseSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseSampleProjImplicitLod, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImageSparseSampleProjExplicitLod
    <br/>
    <br/>Instruction reserved for future use.  Use of this instruction is invalid.
    <br/>
    <br/>Sample a sparse image with a projective coordinate using an explicit level of detail.
    */
    @Override
    public void visitImageSparseSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSparseSampleProjExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
    /**
    OpImageSparseSampleProjDrefImplicitLod
    <br/>
    <br/>Instruction reserved for future use.  Use of this instruction is invalid.
    <br/>
    <br/>Sample a sparse image with a projective coordinate, doing depth-comparison, with an implicit level of detail.
    */
    @Override
    public void visitImageSparseSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseSampleProjDrefImplicitLod, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImageSparseSampleProjDrefExplicitLod
    <br/>
    <br/>Instruction reserved for future use.  Use of this instruction is invalid.
    <br/>
    <br/>Sample a sparse image with a projective coordinate, doing depth-comparison, using an explicit level of detail.
    */
    @Override
    public void visitImageSparseSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {
        newOpcode(OpImageSparseSampleProjDrefExplicitLod, 1 + 1 + 1 + 1 + 1 + 1 + 1 + (optionalLongArray != null ? optionalLongArray.length : 0));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        
        buffer.putUnsignedInt(imageOperands.getMask());
        long[] imageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
        for (long o : imageOperands_operandValues) buffer.putUnsignedInt(o);
        if(optionalLongArray != null) {
            for(long e_optionalLongArray : optionalLongArray) {
                buffer.putUnsignedInt(e_optionalLongArray);
                
            }
            
        }
        
    }
    
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
    @Override
    public void visitImageSparseFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseFetch, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitImageSparseGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseGather, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(component);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImageSparseDrefGather
    <br/>
    <br/>Gathers the requested depth-comparison from four texels of a sparse image.
    <br/>
    <br/>Result Type must be an OpTypeStruct with two members. The first member&#8217;s type must be an integer type scalar.  It will hold a Residency Code that can be passed to OpImageSparseTexelsResident. The second member  must be a scalar of integer type or floating-point type.  It must be the same as Sampled Type of the underlying OpTypeImage. It has one component per gathered texel.
    <br/>
    <br/>Sampled Image must be an object whose type is OpTypeSampledImage. Its OpTypeImage must have a Dim of 2D, Cube, or Rect.
    <br/>
    <br/>Coordinate  must be a scalar or vector of floating-point type.  It contains (u[, v] &#8230; [, array layer]) as needed by the definition of Sampled Image.
    <br/>
    <br/>Dref is the depth-comparison reference value.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    @Override
    public void visitImageSparseDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseDrefGather, 1 + 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        buffer.putUnsignedInt(dref);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
    /**
    OpImageSparseTexelsResident
    <br/>
    <br/>Translates a Resident Code into a Boolean. Result is false if any of the texels were in uncommitted texture memory, and true otherwise.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Resident Code is a value from an OpImageSparse&#8230; instruction that returns a resident code.
    */
    @Override
    public void visitImageSparseTexelsResident(long resultType, long result, long residentCode) {
        newOpcode(OpImageSparseTexelsResident, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(residentCode);
        
    }
    
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
    <br/> The Image Format must not be Unknown, unless the StorageImageReadWithoutFormat Capability was declared.
    <br/>
    <br/>Image Operands encodes what operands follow, as per Image Operands.
    */
    @Override
    public void visitImageSparseRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSparseRead, 1 + 1 + 1 + 1 + (optionalImageOperands != null ? 1 : 0) + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(image);
        buffer.putUnsignedInt(coordinate);
        if(optionalImageOperands != null) {
        
            buffer.putUnsignedInt(optionalImageOperands.getMask());
            long[] optionalImageOperands_operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : optionalImageOperands_operandValues) buffer.putUnsignedInt(o);
            
        }
        
    }
    
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
    @Override
    public void visitConvertFToU(long resultType, long result, long floatValue) {
        newOpcode(OpConvertFToU, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(floatValue);
        
    }
    
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
    @Override
    public void visitConvertFToS(long resultType, long result, long floatValue) {
        newOpcode(OpConvertFToS, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(floatValue);
        
    }
    
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
    @Override
    public void visitConvertSToF(long resultType, long result, long signedValue) {
        newOpcode(OpConvertSToF, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(signedValue);
        
    }
    
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
    @Override
    public void visitConvertUToF(long resultType, long result, long unsignedValue) {
        newOpcode(OpConvertUToF, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(unsignedValue);
        
    }
    
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
    @Override
    public void visitUConvert(long resultType, long result, long unsignedValue) {
        newOpcode(OpUConvert, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(unsignedValue);
        
    }
    
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
    @Override
    public void visitSConvert(long resultType, long result, long signedValue) {
        newOpcode(OpSConvert, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(signedValue);
        
    }
    
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
    @Override
    public void visitFConvert(long resultType, long result, long floatValue) {
        newOpcode(OpFConvert, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(floatValue);
        
    }
    
    /**
    OpQuantizeToF16
    <br/>
    <br/>Quantize a floating-point value to what is expressible by a 16-bit floating-point value.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. The component width must be 32 bits.
    <br/>
    <br/>Value is the value to quantize. The type of Value must be the same as Result Type. 
    <br/>
    <br/> If Value is an infinity, the result is the same infinity. If Value is a NaN, the result is a NaN, but not necessarily the same NaN. If Value is positive with a magnitude too large to represent as a 16-bit floating-point value, the result is positive infinity. If Value is negative with a magnitude too large to represent as a 16-bit floating-point value, the result is negative infinity. If the magnitude of Value is too small to represent as a normalized 16-bit floating-point value, the result is 0.
    <br/>
    <br/>The RelaxedPrecision Decoration has no effect on this instruction.
    <br/>
    <br/> Results are computed per component.
    */
    @Override
    public void visitQuantizeToF16(long resultType, long result, long value) {
        newOpcode(OpQuantizeToF16, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(value);
        
    }
    
    /**
    OpConvertPtrToU
    <br/>
    <br/>Convert a pointer to an unsigned integer type. A Result Type width larger than the width of Pointer will zero extend. A Result Type smaller than the width of Pointer will truncate. For same-width source and result, this is the same as OpBitcast. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type, whose Signedness operand is 0.
    */
    @Override
    public void visitConvertPtrToU(long resultType, long result, long pointer) {
        newOpcode(OpConvertPtrToU, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        
    }
    
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
    @Override
    public void visitSatConvertSToU(long resultType, long result, long signedValue) {
        newOpcode(OpSatConvertSToU, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(signedValue);
        
    }
    
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
    @Override
    public void visitSatConvertUToS(long resultType, long result, long unsignedValue) {
        newOpcode(OpSatConvertUToS, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(unsignedValue);
        
    }
    
    /**
    OpConvertUToPtr
    <br/>
    <br/>Convert an integer to pointer. A Result Type width smaller than the width of Integer Value pointer will truncate. A Result Type width larger than the width of Integer Value pointer will zero extend. 
    <br/>
    <br/> Result Type must be an OpTypePointer. For same-width source and result, this is the same as OpBitcast.
    */
    @Override
    public void visitConvertUToPtr(long resultType, long result, long integerValue) {
        newOpcode(OpConvertUToPtr, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(integerValue);
        
    }
    
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
    @Override
    public void visitPtrCastToGeneric(long resultType, long result, long pointer) {
        newOpcode(OpPtrCastToGeneric, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        
    }
    
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
    @Override
    public void visitGenericCastToPtr(long resultType, long result, long pointer) {
        newOpcode(OpGenericCastToPtr, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        
    }
    
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
    @Override
    public void visitGenericCastToPtrExplicit(long resultType, long result, long pointer, StorageClass storage) {
        newOpcode(OpGenericCastToPtrExplicit, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(storage.ordinal());
        
    }
    
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
    @Override
    public void visitBitcast(long resultType, long result, long operand) {
        newOpcode(OpBitcast, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
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
    @Override
    public void visitVectorExtractDynamic(long resultType, long result, long vector, long index) {
        newOpcode(OpVectorExtractDynamic, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        buffer.putUnsignedInt(index);
        
    }
    
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
    @Override
    public void visitVectorInsertDynamic(long resultType, long result, long vector, long component, long index) {
        newOpcode(OpVectorInsertDynamic, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        buffer.putUnsignedInt(component);
        buffer.putUnsignedInt(index);
        
    }
    
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
    @Override
    public void visitVectorShuffle(long resultType, long result, long vector1, long vector2, long[] components) {
        newOpcode(OpVectorShuffle, 1 + 1 + 1 + 1 + components.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector1);
        buffer.putUnsignedInt(vector2);
        buffer.putUnsignedInts(components);
        
    }
    
    /**
    OpCompositeConstruct
    <br/>
    <br/>Construct a new composite object from a set of constituent objects that will fully form it.
    <br/>
    <br/>Result Type must be a composite type, whose top-level members/elements/components/columns have the same type as the types of the operands, with one exception. The exception is that for constructing a vector, the operands may also be vectors with the same component type as the Result Type component type. When constructing a vector, the total number of components in all the operands must equal the number of components in Result Type.
    <br/>
    <br/>Constituents will become members of a structure, or elements of an array, or components of a vector, or columns of a matrix. There must be exactly one Constituent for each top-level member/element/component/column of the result, with one exception. The exception is that for constructing a vector, a contiguous subset of the scalars consumed can be represented by a vector operand instead. The Constituents must appear in the order needed by the definition of the type of the result. When constructing a vector, there must be at least two Constituent operands.
    */
    @Override
    public void visitCompositeConstruct(long resultType, long result, long[] constituents) {
        newOpcode(OpCompositeConstruct, 1 + 1 + constituents.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(constituents);
        
    }
    
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
    @Override
    public void visitCompositeExtract(long resultType, long result, long composite, long[] indexes) {
        newOpcode(OpCompositeExtract, 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(composite);
        buffer.putUnsignedInts(indexes);
        
    }
    
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
    @Override
    public void visitCompositeInsert(long resultType, long result, long object, long composite, long[] indexes) {
        newOpcode(OpCompositeInsert, 1 + 1 + 1 + 1 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(object);
        buffer.putUnsignedInt(composite);
        buffer.putUnsignedInts(indexes);
        
    }
    
    /**
    OpCopyObject
    <br/>
    <br/>Make a copy of Operand. There are no dereferences involved.
    <br/>
    <br/>Result Type must match Operand type.  There are no other restrictions on the types.
    */
    @Override
    public void visitCopyObject(long resultType, long result, long operand) {
        newOpcode(OpCopyObject, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
    /**
    OpTranspose
    <br/>
    <br/>Transpose a matrix.
    <br/>
    <br/>Result Type must be an OpTypeMatrix, where the number of columns and the column size is the reverse of those of the type of Matrix.
    <br/>
    <br/>Matrix must have of type of OpTypeMatrix.
    */
    @Override
    public void visitTranspose(long resultType, long result, long matrix) {
        newOpcode(OpTranspose, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(matrix);
        
    }
    
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
    @Override
    public void visitSNegate(long resultType, long result, long operand) {
        newOpcode(OpSNegate, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
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
    @Override
    public void visitFNegate(long resultType, long result, long operand) {
        newOpcode(OpFNegate, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
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
    @Override
    public void visitIAdd(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpIAdd, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFAdd(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFAdd, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitISub(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpISub, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFSub(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFSub, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitIMul(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpIMul, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFMul(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFMul, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitUDiv(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpUDiv, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSDiv(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSDiv, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFDiv(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFDiv, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitUMod(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpUMod, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSRem(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSRem, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSMod(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSMod, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFRem(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFRem, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFMod(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFMod, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitVectorTimesScalar(long resultType, long result, long vector, long scalar) {
        newOpcode(OpVectorTimesScalar, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        buffer.putUnsignedInt(scalar);
        
    }
    
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
    @Override
    public void visitMatrixTimesScalar(long resultType, long result, long matrix, long scalar) {
        newOpcode(OpMatrixTimesScalar, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(matrix);
        buffer.putUnsignedInt(scalar);
        
    }
    
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
    @Override
    public void visitVectorTimesMatrix(long resultType, long result, long vector, long matrix) {
        newOpcode(OpVectorTimesMatrix, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        buffer.putUnsignedInt(matrix);
        
    }
    
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
    @Override
    public void visitMatrixTimesVector(long resultType, long result, long matrix, long vector) {
        newOpcode(OpMatrixTimesVector, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(matrix);
        buffer.putUnsignedInt(vector);
        
    }
    
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
    @Override
    public void visitMatrixTimesMatrix(long resultType, long result, long leftMatrix, long rightMatrix) {
        newOpcode(OpMatrixTimesMatrix, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(leftMatrix);
        buffer.putUnsignedInt(rightMatrix);
        
    }
    
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
    @Override
    public void visitOuterProduct(long resultType, long result, long vector1, long vector2) {
        newOpcode(OpOuterProduct, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector1);
        buffer.putUnsignedInt(vector2);
        
    }
    
    /**
    OpDot
    <br/>
    <br/>Dot product of Vector 1 and Vector 2.
    <br/>
    <br/> Result Type must be a floating-point type scalar. 
    <br/>
    <br/>Vector 1 and Vector 2 must have the same type, and their component type must be Result Type.
    */
    @Override
    public void visitDot(long resultType, long result, long vector1, long vector2) {
        newOpcode(OpDot, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector1);
        buffer.putUnsignedInt(vector2);
        
    }
    
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
    @Override
    public void visitIAddCarry(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpIAddCarry, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitISubBorrow(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpISubBorrow, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitUMulExtended(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpUMulExtended, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSMulExtended(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSMulExtended, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitShiftRightLogical(long resultType, long result, long base, long shift) {
        newOpcode(OpShiftRightLogical, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(shift);
        
    }
    
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
    @Override
    public void visitShiftRightArithmetic(long resultType, long result, long base, long shift) {
        newOpcode(OpShiftRightArithmetic, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(shift);
        
    }
    
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
    @Override
    public void visitShiftLeftLogical(long resultType, long result, long base, long shift) {
        newOpcode(OpShiftLeftLogical, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(shift);
        
    }
    
    /**
    OpBitwiseOr
    <br/>
    <br/>Result is 1 if either Operand 1 or Operand 2 is 1. Result is 0 if both Operand 1 and Operand 2 are 0.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    @Override
    public void visitBitwiseOr(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpBitwiseOr, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
    /**
    OpBitwiseXor
    <br/>
    <br/>Result is 1 if exactly one of Operand 1 or Operand 2 is 1. Result is 0 if Operand 1 and Operand 2 have the same value.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    @Override
    public void visitBitwiseXor(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpBitwiseXor, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
    /**
    OpBitwiseAnd
    <br/>
    <br/>Result is 1 if both Operand 1 and Operand 2 are 1. Result is 0 if either Operand 1 or Operand 2 are 0.
    <br/>
    <br/> Results are computed per component, and within each component, per bit. 
    <br/>
    <br/> Result Type must be a scalar or vector of integer type.  The type of Operand 1 and Operand 2  must be a scalar or vector of integer type.  They must have the same number of components as Result Type. They must have the same component width as Result Type.
    */
    @Override
    public void visitBitwiseAnd(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpBitwiseAnd, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitNot(long resultType, long result, long operand) {
        newOpcode(OpNot, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
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
    @Override
    public void visitBitFieldInsert(long resultType, long result, long base, long insert, long offset, long count) {
        newOpcode(OpBitFieldInsert, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(insert);
        buffer.putUnsignedInt(offset);
        buffer.putUnsignedInt(count);
        
    }
    
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
    @Override
    public void visitBitFieldSExtract(long resultType, long result, long base, long offset, long count) {
        newOpcode(OpBitFieldSExtract, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(offset);
        buffer.putUnsignedInt(count);
        
    }
    
    /**
    OpBitFieldUExtract
    <br/>
    <br/>Extract a bit field from an object, without sign extension.
    <br/>
    <br/>The semantics are the same as with OpBitFieldSExtract with the exception that there is no sign extension. The remaining bits of the result will all be 0.
    */
    @Override
    public void visitBitFieldUExtract(long resultType, long result, long base, long offset, long count) {
        newOpcode(OpBitFieldUExtract, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        buffer.putUnsignedInt(offset);
        buffer.putUnsignedInt(count);
        
    }
    
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
    @Override
    public void visitBitReverse(long resultType, long result, long base) {
        newOpcode(OpBitReverse, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        
    }
    
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
    @Override
    public void visitBitCount(long resultType, long result, long base) {
        newOpcode(OpBitCount, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(base);
        
    }
    
    /**
    OpAny
    <br/>
    <br/>Result is true if any component of Vector is true, otherwise result is false.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Vector must be a vector of Boolean type.
    */
    @Override
    public void visitAny(long resultType, long result, long vector) {
        newOpcode(OpAny, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        
    }
    
    /**
    OpAll
    <br/>
    <br/>Result is true if all components of Vector are true, otherwise result is false.
    <br/>
    <br/> Result Type must be a Boolean type scalar. 
    <br/>
    <br/>Vector must be a vector of Boolean type.
    */
    @Override
    public void visitAll(long resultType, long result, long vector) {
        newOpcode(OpAll, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(vector);
        
    }
    
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
    @Override
    public void visitIsNan(long resultType, long result, long x) {
        newOpcode(OpIsNan, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        
    }
    
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
    @Override
    public void visitIsInf(long resultType, long result, long x) {
        newOpcode(OpIsInf, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        
    }
    
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
    @Override
    public void visitIsFinite(long resultType, long result, long x) {
        newOpcode(OpIsFinite, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        
    }
    
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
    @Override
    public void visitIsNormal(long resultType, long result, long x) {
        newOpcode(OpIsNormal, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        
    }
    
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
    @Override
    public void visitSignBitSet(long resultType, long result, long x) {
        newOpcode(OpSignBitSet, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        
    }
    
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
    @Override
    public void visitLessOrGreater(long resultType, long result, long x, long y) {
        newOpcode(OpLessOrGreater, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        buffer.putUnsignedInt(y);
        
    }
    
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
    @Override
    public void visitOrdered(long resultType, long result, long x, long y) {
        newOpcode(OpOrdered, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        buffer.putUnsignedInt(y);
        
    }
    
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
    @Override
    public void visitUnordered(long resultType, long result, long x, long y) {
        newOpcode(OpUnordered, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(x);
        buffer.putUnsignedInt(y);
        
    }
    
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
    @Override
    public void visitLogicalEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpLogicalEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitLogicalNotEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpLogicalNotEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitLogicalOr(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpLogicalOr, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitLogicalAnd(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpLogicalAnd, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitLogicalNot(long resultType, long result, long operand) {
        newOpcode(OpLogicalNot, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand);
        
    }
    
    /**
    OpSelect
    <br/>
    <br/>Select between two objects.
    <br/>
    <br/>Result Type must be a scalar or vector.
    <br/>
    <br/> The type of Object 1 must be the same as Result Type. Object 1 is selected as the result if Condition is true.
    <br/>
    <br/> The type of Object 2 must be the same as Result Type. Object 2 is selected as the result if Condition is false.
    <br/>
    <br/>Condition must be a scalar or vector of Boolean type.  It must have the same number of components as Result Type.
    <br/>
    <br/> Results are computed per component.
    */
    @Override
    public void visitSelect(long resultType, long result, long condition, long object1, long object2) {
        newOpcode(OpSelect, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(condition);
        buffer.putUnsignedInt(object1);
        buffer.putUnsignedInt(object2);
        
    }
    
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
    @Override
    public void visitIEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpIEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitINotEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpINotEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitUGreaterThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpUGreaterThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSGreaterThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSGreaterThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitUGreaterThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpUGreaterThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSGreaterThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSGreaterThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitULessThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpULessThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSLessThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSLessThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitULessThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpULessThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitSLessThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpSLessThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdNotEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdNotEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordNotEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordNotEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdLessThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdLessThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordLessThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordLessThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdGreaterThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdGreaterThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordGreaterThan(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordGreaterThan, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdLessThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdLessThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordLessThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordLessThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFOrdGreaterThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFOrdGreaterThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
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
    @Override
    public void visitFUnordGreaterThanEqual(long resultType, long result, long operand1, long operand2) {
        newOpcode(OpFUnordGreaterThanEqual, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(operand1);
        buffer.putUnsignedInt(operand2);
        
    }
    
    /**
    OpDPdx
    <br/>
    <br/>Same result as either OpDPdxFine or OpDPdxCoarse on P. Selection of which one is based on external factors.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdx(long resultType, long result, long p) {
        newOpcode(OpDPdx, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpDPdy
    <br/>
    <br/>Same result as either OpDPdyFine or OpDPdyCoarse on P. Selection of which one is based on external factors.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdy(long resultType, long result, long p) {
        newOpcode(OpDPdy, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpFwidth
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdx and OpDPdy on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitFwidth(long resultType, long result, long p) {
        newOpcode(OpFwidth, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpDPdxFine
    <br/>
    <br/>Result is the partial derivative of P with respect to the window x coordinate.Will use local differencing based on the value of P for the current fragment and its immediate neighbor(s).
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdxFine(long resultType, long result, long p) {
        newOpcode(OpDPdxFine, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpDPdyFine
    <br/>
    <br/>Result is the partial derivative of P with respect to the window y coordinate.Will use local differencing based on the value of P for the current fragment and its immediate neighbor(s).
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdyFine(long resultType, long result, long p) {
        newOpcode(OpDPdyFine, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpFwidthFine
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdxFine and OpDPdyFine on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitFwidthFine(long resultType, long result, long p) {
        newOpcode(OpFwidthFine, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpDPdxCoarse
    <br/>
    <br/>Result is the partial derivative of P with respect to the window x coordinate. Will use local differencing based on the value of P for the current fragment&#8217;s neighbors, and will possibly, but not necessarily, include the value of P for the current fragment. That is, over a given area, the implementation can compute x derivatives in fewer unique locations than would be allowed for OpDPdxFine.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdxCoarse(long resultType, long result, long p) {
        newOpcode(OpDPdxCoarse, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpDPdyCoarse
    <br/>
    <br/>Result is the partial derivative of P with respect to the window y coordinate. Will use local differencing based on the value of P for the current fragment&#8217;s neighbors, and will possibly, but not necessarily, include the value of P for the current fragment. That is, over a given area, the implementation can compute y derivatives in fewer unique locations than would be allowed for OpDPdyFine.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitDPdyCoarse(long resultType, long result, long p) {
        newOpcode(OpDPdyCoarse, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpFwidthCoarse
    <br/>
    <br/>Result is the same as computing the sum of the absolute values of OpDPdxCoarse and OpDPdyCoarse on P.
    <br/>
    <br/> Result Type must be a scalar or vector of floating-point type. 
    <br/>
    <br/> The type of P must be the same as Result Type. P is the value to take the derivative of.
    <br/>
    <br/>This instruction is only valid in the Fragment Execution Model.
    */
    @Override
    public void visitFwidthCoarse(long resultType, long result, long p) {
        newOpcode(OpFwidthCoarse, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(p);
        
    }
    
    /**
    OpPhi
    <br/>
    <br/>The SSA phi function.
    <br/>
    <br/>The result is selected based on control flow: If control reached the current block from Parent i, Result Id gets the value that Variable i had at the end of Parent i.
    <br/>
    <br/>Result Type can be any type.
    <br/>
    <br/>Operands are a sequence of pairs: (Variable 1, Parent 1 block), (Variable 2, Parent 2 block), &#8230; Each Parent i block is the label of an immediate predecessor in the CFG of the current block. A Parent i block must not appear more than once in the operand sequence. All Variables must have a type matching Result Type.
    <br/>
    <br/>Within a block, this instruction must appear before all non-OpPhi instructions (except for OpLine, which can be mixed with OpPhi).
    */
    @Override
    public void visitPhi(long resultType, long result, long[] variables) {
        newOpcode(OpPhi, 1 + 1 + variables.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInts(variables);
        
    }
    
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
    @Override
    public void visitLoopMerge(long mergeBlock, long continueTarget, long loopControl, long[] parameters) {
        newOpcode(OpLoopMerge, 1 + 1 + 1 + parameters.length);
        buffer.putUnsignedInt(mergeBlock);
        buffer.putUnsignedInt(continueTarget);
        buffer.putUnsignedInt(loopControl);
        buffer.putUnsignedInts(parameters);
        
    }
    
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
    @Override
    public void visitSelectionMerge(long mergeBlock, long selectionControl) {
        newOpcode(OpSelectionMerge, 1 + 1);
        buffer.putUnsignedInt(mergeBlock);
        buffer.putUnsignedInt(selectionControl);
        
    }
    
    /**
    OpLabel
    <br/>
    <br/>The block label instruction: Any reference to a block is through the Result &lt;id&gt; of its label.
    <br/>
    <br/>Must be the first instruction of any block, and appears only as the first instruction of a block.
    */
    @Override
    public void visitLabel(long result) {
        newOpcode(OpLabel, 1);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpBranch
    <br/>
    <br/>Unconditional branch to Target Label.
    <br/>
    <br/>Target Label must be the Result &lt;id&gt; of an OpLabel instruction in the current function.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    @Override
    public void visitBranch(long targetLabel) {
        newOpcode(OpBranch, 1);
        buffer.putUnsignedInt(targetLabel);
        
    }
    
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
    <br/>Branch weights are unsigned 32-bit integer literals. There must be either no Branch Weights or exactly two branch weights. If present, the first is the weight for branching to True Label, and the second is the weight for branching to False Label. The implied probability that a branch is taken is its weight divided by the sum of the two Branch weights.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    @Override
    public void visitBranchConditional(long condition, long trueLabel, long falseLabel, long[] weights) {
        newOpcode(OpBranchConditional, 1 + 1 + 1 + weights.length);
        buffer.putUnsignedInt(condition);
        buffer.putUnsignedInt(trueLabel);
        buffer.putUnsignedInt(falseLabel);
        buffer.putUnsignedInts(weights);
        
    }
    
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
    @Override
    public void visitSwitch(long selector, long defaultValue, long[] target) {
        newOpcode(OpSwitch, 1 + 1 + target.length);
        buffer.putUnsignedInt(selector);
        buffer.putUnsignedInt(defaultValue);
        buffer.putUnsignedInts(target);
        
    }
    
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
    @Override
    public void visitKill() {
        newOpcode(OpKill, 0);
    }
    
    /**
    OpReturn
    <br/>
    <br/>Return with no value from a function with void return type.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    @Override
    public void visitReturn() {
        newOpcode(OpReturn, 0);
    }
    
    /**
    OpReturnValue
    <br/>
    <br/>Return a value from a function.
    <br/>
    <br/>Value is the value returned, by copy, and must match the Return Type operand of the OpTypeFunction type of the OpFunction body this return instruction is in.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    @Override
    public void visitReturnValue(long value) {
        newOpcode(OpReturnValue, 1);
        buffer.putUnsignedInt(value);
        
    }
    
    /**
    OpUnreachable
    <br/>
    <br/>Declares that this block is not reachable in the CFG.
    <br/>
    <br/>This instruction must be the last instruction in a block.
    */
    @Override
    public void visitUnreachable() {
        newOpcode(OpUnreachable, 0);
    }
    
    /**
    OpLifetimeStart
    <br/>
    <br/>Declare that an object was not defined before this instruction.
    <br/>
    <br/>Pointer is a pointer to the object whose lifetime is starting. Its type must be an OpTypePointer with Storage Class Function.
    <br/>
    <br/>Size must be 0 if Pointer is a pointer to a non-void type or the Addresses capability is not being used. If Size is non-zero, it is the number of bytes of memory whose lifetime is starting.  Its type  must be an integer type scalar.  It is treated as unsigned; if its type has Signedness of 1, its sign bit cannot be set.
    */
    @Override
    public void visitLifetimeStart(long pointer, long size) {
        newOpcode(OpLifetimeStart, 1 + 1);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(size);
        
    }
    
    /**
    OpLifetimeStop
    <br/>
    <br/>Declare that an object is dead after this instruction.
    <br/>
    <br/>Pointer is a pointer to the object whose lifetime is ending. Its type must be an OpTypePointer with Storage Class Function.
    <br/>
    <br/>Size must be 0 if Pointer is a pointer to a non-void type or the Addresses capability is not being used. If Size is non-zero, it is the number of bytes of memory whose lifetime is ending.  Its type  must be an integer type scalar.  It is treated as unsigned; if its type has Signedness of 1, its sign bit cannot be set.
    */
    @Override
    public void visitLifetimeStop(long pointer, long size) {
        newOpcode(OpLifetimeStop, 1 + 1);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(size);
        
    }
    
    /**
    OpAtomicLoad
    <br/>
    <br/>Atomically load through Pointer using the given Semantics. All subparts of the value that is loaded will be read atomically with respect to all other atomic accesses to it within Scope.
    <br/>
    <br/> Result Type must be a scalar of integer type or floating-point type. 
    <br/>
    <br/>Pointer is the pointer to the memory to read. The type of the value pointed to by Pointer must be the same as Result Type.
    */
    @Override
    public void visitAtomicLoad(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {
        newOpcode(OpAtomicLoad, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
    /**
    OpAtomicStore
    <br/>
    <br/>Atomically store through Pointer using the given Semantics. All subparts of Value will be written atomically with respect to all other atomic accesses to it within Scope.
    <br/>
    <br/>Pointer is the pointer to the memory to write. The type it points to  must be a scalar of integer type or floating-point type. 
    <br/>
    <br/>Value is the value to write. The type of Value and the type pointed to by Pointer must be the same type.
    */
    @Override
    public void visitAtomicStore(long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicStore, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicExchange, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
    /**
    OpAtomicCompareExchange
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by selecting Value if Original Value equals Comparator or selecting Original Value otherwise, and
    <br/>3) store the New Value back through Pointer.
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
    @Override
    public void visitAtomicCompareExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator) {
        newOpcode(OpAtomicCompareExchange, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsEqual);
        buffer.putUnsignedInt(memorySemanticsUnequal);
        buffer.putUnsignedInt(value);
        buffer.putUnsignedInt(comparator);
        
    }
    
    /**
    OpAtomicCompareExchangeWeak
    <br/>
    <br/>Attempts to do the following:
    <br/>
    <br/>Perform the following steps atomically with respect to any other atomic accesses within Scope to the same location: 
    <br/>1) load through Pointer to get an Original Value,
    <br/>2) get a New Value by selecting Value if Original Value equals Comparator or selecting Original Value otherwise, and
    <br/>3) store the New Value back through Pointer.
    <br/>
    <br/>The instruction&#8217;s result is the Original Value.
    <br/>
    <br/>The weak compare-and-exchange operations may fail spuriously. That is, even when Original Value equals Comparator the comparison can fail and store back the Original Value through Pointer.
    <br/>
    <br/> Result Type must be an integer type scalar. 
    <br/>
    <br/>Use Equal for the memory semantics of this instruction when Value and Original Value compare equal.
    <br/>
    <br/>Use Unequal for the memory semantics of this instruction when Value and Original Value compare unequal. Unequal cannot be set to Release or Acquire and Release. In addition, Unequal cannot be set to a stronger memory-order then Equal.
    <br/>
    <br/> The type of Value must be the same as Result Type.  The type of the value pointed to by Pointer must be the same as Result Type.  This type must also match the type of Comparator.
    */
    @Override
    public void visitAtomicCompareExchangeWeak(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator) {
        newOpcode(OpAtomicCompareExchangeWeak, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsEqual);
        buffer.putUnsignedInt(memorySemanticsUnequal);
        buffer.putUnsignedInt(value);
        buffer.putUnsignedInt(comparator);
        
    }
    
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
    @Override
    public void visitAtomicIIncrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {
        newOpcode(OpAtomicIIncrement, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
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
    @Override
    public void visitAtomicIDecrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {
        newOpcode(OpAtomicIDecrement, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
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
    @Override
    public void visitAtomicIAdd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicIAdd, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicISub(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicISub, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicSMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicSMin, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicUMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicUMin, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicSMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicSMax, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicUMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicUMax, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicAnd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicAnd, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicOr(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicOr, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicXor(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {
        newOpcode(OpAtomicXor, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        buffer.putUnsignedInt(value);
        
    }
    
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
    @Override
    public void visitAtomicFlagTestAndSet(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {
        newOpcode(OpAtomicFlagTestAndSet, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
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
    @Override
    public void visitAtomicFlagClear(long pointer, long scopeScope, long memorySemanticsSemantics) {
        newOpcode(OpAtomicFlagClear, 1 + 1 + 1);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(scopeScope);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
    /**
    OpEmitVertex
    <br/>
    <br/>Emits the current values of all output variables to the current output primitive. After execution, the values of all output variables are undefined.
    <br/>
    <br/>This instruction can only be used when only one stream is present.
    */
    @Override
    public void visitEmitVertex() {
        newOpcode(OpEmitVertex, 0);
    }
    
    /**
    OpEndPrimitive
    <br/>
    <br/>Finish the current primitive and start a new one.  No vertex is emitted.
    <br/>
    <br/>This instruction can only be used when only one stream is present.
    */
    @Override
    public void visitEndPrimitive() {
        newOpcode(OpEndPrimitive, 0);
    }
    
    /**
    OpEmitStreamVertex
    <br/>
    <br/>Emits the current values of all output variables to the current output primitive. After execution, the values of all output variables are undefined.
    <br/>
    <br/>Stream must be an &lt;id&gt; of a constant instruction with a scalar integer type. That constant is the output-primitive stream number.
    <br/>
    <br/>This instruction can only be used when multiple streams are present.
    */
    @Override
    public void visitEmitStreamVertex(long stream) {
        newOpcode(OpEmitStreamVertex, 1);
        buffer.putUnsignedInt(stream);
        
    }
    
    /**
    OpEndStreamPrimitive
    <br/>
    <br/>Finish the current primitive and start a new one.  No vertex is emitted.
    <br/>
    <br/>Stream must be an &lt;id&gt; of a constant instruction with a scalar integer type. That constant is the output-primitive stream number.
    <br/>
    <br/>This instruction can only be used when multiple streams are present.
    */
    @Override
    public void visitEndStreamPrimitive(long stream) {
        newOpcode(OpEndStreamPrimitive, 1);
        buffer.putUnsignedInt(stream);
        
    }
    
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
    <br/>It is only valid to use this instruction with TessellationControl, GLCompute, or Kernel execution models.
    <br/>
    <br/>When used with the TessellationControl execution model, it also implicitly synchronizes the Output Storage Class:  Writes to Output variables performed by any invocation executed prior to a OpControlBarrier will be visible to any other invocation after return from that OpControlBarrier.
    */
    @Override
    public void visitControlBarrier(long scopeExecution, long scopeMemory, long memorySemanticsSemantics) {
        newOpcode(OpControlBarrier, 1 + 1 + 1);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(scopeMemory);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
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
    @Override
    public void visitMemoryBarrier(long scopeMemory, long memorySemanticsSemantics) {
        newOpcode(OpMemoryBarrier, 1 + 1);
        buffer.putUnsignedInt(scopeMemory);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
    /**
    OpNamedBarrierInitialize
    <br/>
    <br/>Declare a new named-barrier object.
    <br/>
    <br/>Result Type must be the type OpTypeNamedBarrier.
    <br/>
    <br/>Subgroup Count must be a 32-bit integer type scalar representing the number of subgroups that must reach the current point of execution.
    */
    @Override
    public void visitNamedBarrierInitialize(long resultType, long result, long subgroupCount) {
        newOpcode(OpNamedBarrierInitialize, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(subgroupCount);
        
    }
    
    /**
    OpMemoryNamedBarrier
    <br/>
    <br/>Wait for other invocations of this module to reach the current point of execution.
    <br/>
    <br/>Named Barrier must be the type OpTypeNamedBarrier.
    <br/>
    <br/>If Semantics is not None, this instruction also serves as an OpMemoryBarrier instruction, and must also perform and adhere to the description and semantics of an OpMemoryBarrier instruction with the same Memory and Semantics operands.  This allows atomically specifying both a control barrier and a memory barrier (that is, without needing two instructions). If Semantics None, Memory is ignored.
    */
    @Override
    public void visitMemoryNamedBarrier(long namedBarrier, long scopeMemory, long memorySemanticsSemantics) {
        newOpcode(OpMemoryNamedBarrier, 1 + 1 + 1);
        buffer.putUnsignedInt(namedBarrier);
        buffer.putUnsignedInt(scopeMemory);
        buffer.putUnsignedInt(memorySemanticsSemantics);
        
    }
    
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
    @Override
    public void visitGroupAsyncCopy(long resultType, long result, long scopeExecution, long destination, long source, long numElements, long stride, long event) {
        newOpcode(OpGroupAsyncCopy, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(destination);
        buffer.putUnsignedInt(source);
        buffer.putUnsignedInt(numElements);
        buffer.putUnsignedInt(stride);
        buffer.putUnsignedInt(event);
        
    }
    
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
    @Override
    public void visitGroupWaitEvents(long scopeExecution, long numEvents, long eventsList) {
        newOpcode(OpGroupWaitEvents, 1 + 1 + 1);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(numEvents);
        buffer.putUnsignedInt(eventsList);
        
    }
    
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
    @Override
    public void visitGroupAll(long resultType, long result, long scopeExecution, long predicate) {
        newOpcode(OpGroupAll, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(predicate);
        
    }
    
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
    @Override
    public void visitGroupAny(long resultType, long result, long scopeExecution, long predicate) {
        newOpcode(OpGroupAny, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(predicate);
        
    }
    
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
    @Override
    public void visitGroupBroadcast(long resultType, long result, long scopeExecution, long value, long localId) {
        newOpcode(OpGroupBroadcast, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(value);
        buffer.putUnsignedInt(localId);
        
    }
    
    /**
    OpGroupIAdd
    <br/>
    <br/>An integer add group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is 0.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupIAdd(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupIAdd, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupFAdd
    <br/>
    <br/>A floating-point add group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is 0.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupFAdd(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupFAdd, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupFMin
    <br/>
    <br/>A floating-point minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is +INF.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupFMin(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupFMin, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupUMin
    <br/>
    <br/>An unsigned integer minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is UINT_MAX when X is 32 bits wide and ULONG_MAX when X is 64 bits wide.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupUMin(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupUMin, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupSMin
    <br/>
    <br/>A signed integer minimum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is INT_MAX when X is 32 bits wide and LONG_MAX when X is 64 bits wide.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupSMin(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupSMin, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupFMax
    <br/>
    <br/>A floating-point maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is -INF.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 16-bit, 32-bit, or 64-bit floating-point type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupFMax(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupFMax, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupUMax
    <br/>
    <br/>An unsigned integer maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is 0.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>Result Type must be a 32-bit or 64-bit integer type scalar.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupUMax(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupUMax, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpGroupSMax
    <br/>
    <br/>A signed integer maximum group operation specified for all values of X specified by invocations in the group.
    <br/>
    <br/>The identity I is INT_MIN when X is 32 bits wide and LONG_MIN when X is 64 bits wide.
    <br/>
    <br/>All invocations of this module within Execution must reach this point of execution.
    <br/>
    <br/>This instruction is only guaranteed to work correctly if placed strictly within uniform control flow within Execution. This ensures that if any invocation executes it, all invocations will execute it. If placed elsewhere, an invocation may stall indefinitely.
    <br/>
    <br/>X and Result Type must be a 32-bit or 64-bit OpTypeInt data type.
    <br/>
    <br/>Execution must be Workgroup or Subgroup Scope.
    <br/>
    <br/> The type of X must be the same as Result Type.
    */
    @Override
    public void visitGroupSMax(long resultType, long result, long scopeExecution, long operation, long x) {
        newOpcode(OpGroupSMax, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(operation);
        buffer.putUnsignedInt(x);
        
    }
    
    /**
    OpSubgroupBallotKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    @Override
    public void visitSubgroupBallotKHR(long resultType, long result, long predicate) {
        newOpcode(OpSubgroupBallotKHR, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(predicate);
        
    }
    
    /**
    OpSubgroupFirstInvocationKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    @Override
    public void visitSubgroupFirstInvocationKHR(long resultType, long result, long value) {
        newOpcode(OpSubgroupFirstInvocationKHR, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(value);
        
    }
    
    /**
    OpSubgroupReadInvocationKHR
    <br/>
    <br/>See extension SPV_KHR_shader_ballot
    */
    @Override
    public void visitSubgroupReadInvocationKHR(long resultType, long result, long value, long index) {
        newOpcode(OpSubgroupReadInvocationKHR, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(value);
        buffer.putUnsignedInt(index);
        
    }
    
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
    @Override
    public void visitEnqueueMarker(long resultType, long result, long queue, long numEvents, long waitEvents, long retEvent) {
        newOpcode(OpEnqueueMarker, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(queue);
        buffer.putUnsignedInt(numEvents);
        buffer.putUnsignedInt(waitEvents);
        buffer.putUnsignedInt(retEvent);
        
    }
    
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
    <br/>ND Range must have a type of OpTypeStruct created by OpBuildNDRange.
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
    @Override
    public void visitEnqueueKernel(long resultType, long result, long queue, long flags, long nDRange, long numEvents, long waitEvents, long retEvent, long invoke, long param, long paramSize, long paramAlign, long[] locals) {
        newOpcode(OpEnqueueKernel, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + locals.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(queue);
        buffer.putUnsignedInt(flags);
        buffer.putUnsignedInt(nDRange);
        buffer.putUnsignedInt(numEvents);
        buffer.putUnsignedInt(waitEvents);
        buffer.putUnsignedInt(retEvent);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        buffer.putUnsignedInts(locals);
        
    }
    
    /**
    OpGetKernelNDrangeSubGroupCount
    <br/>
    <br/>Returns the number of subgroups in each workgroup of the dispatch (except for the last in cases where the global size does not divide cleanly into work-groups) given the combination of the passed NDRange descriptor specified by ND Range and the function specified by Invoke.
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>ND Range must have a type of OpTypeStruct created by OpBuildNDRange.
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
    @Override
    public void visitGetKernelNDrangeSubGroupCount(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelNDrangeSubGroupCount, 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(nDRange);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
    /**
    OpGetKernelNDrangeMaxSubGroupSize
    <br/>
    <br/>Returns the maximum sub-group size for the function specified by Invoke and the NDRange specified by ND Range. 
    <br/>
    <br/>Result Type must be a 32-bit integer type scalar.
    <br/>
    <br/>ND Range must have a type of OpTypeStruct created by OpBuildNDRange.
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
    @Override
    public void visitGetKernelNDrangeMaxSubGroupSize(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelNDrangeMaxSubGroupSize, 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(nDRange);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
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
    @Override
    public void visitGetKernelWorkGroupSize(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelWorkGroupSize, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
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
    @Override
    public void visitGetKernelPreferredWorkGroupSizeMultiple(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelPreferredWorkGroupSizeMultiple, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
    /**
    OpRetainEvent
    <br/>
    <br/>Increments the reference count of the event object specified by Event.
    <br/>
    <br/>Event must be an event that was produced by OpEnqueueKernel, OpEnqueueMarker or OpCreateUserEvent.
    */
    @Override
    public void visitRetainEvent(long event) {
        newOpcode(OpRetainEvent, 1);
        buffer.putUnsignedInt(event);
        
    }
    
    /**
    OpReleaseEvent
    <br/>
    <br/>Decrements the reference count of the event object specified by Event. The event object is deleted once the event reference count is zero, the specific command identified by this event has completed (or terminated) and there are no commands in any device command queue that require a wait for this event to complete.
    <br/>
    <br/>Event must be an event that was produced by OpEnqueueKernel, OpEnqueueMarker or OpCreateUserEvent.
    */
    @Override
    public void visitReleaseEvent(long event) {
        newOpcode(OpReleaseEvent, 1);
        buffer.putUnsignedInt(event);
        
    }
    
    /**
    OpCreateUserEvent
    <br/>
    <br/>Create a user event. The execution status of the created event is set to a value of 2 (CL_SUBMITTED).
    <br/>
    <br/>Result Type must be OpTypeDeviceEvent.
    */
    @Override
    public void visitCreateUserEvent(long resultType, long result) {
        newOpcode(OpCreateUserEvent, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
    /**
    OpIsValidEvent
    <br/>
    <br/>Returns true if the event specified by Event is a valid event, otherwise result is false.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Event must have a type of OpTypeDeviceEvent
    */
    @Override
    public void visitIsValidEvent(long resultType, long result, long event) {
        newOpcode(OpIsValidEvent, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(event);
        
    }
    
    /**
    OpSetUserEventStatus
    <br/>
    <br/>Sets the execution status of a user event specified by Event.Status can be either 0 (CL_COMPLETE) to indicate that this kernel and all its child kernels finished execution successfully, or a negative integer value indicating an error.
    <br/>
    <br/>Event must have a type of OpTypeDeviceEvent that was produced by OpCreateUserEvent.
    <br/>
    <br/>Status must have a type of 32-bit OpTypeInt treated as a signed integer.
    */
    @Override
    public void visitSetUserEventStatus(long event, long status) {
        newOpcode(OpSetUserEventStatus, 1 + 1);
        buffer.putUnsignedInt(event);
        buffer.putUnsignedInt(status);
        
    }
    
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
    @Override
    public void visitCaptureEventProfilingInfo(long event, long profilingInfo, long value) {
        newOpcode(OpCaptureEventProfilingInfo, 1 + 1 + 1);
        buffer.putUnsignedInt(event);
        buffer.putUnsignedInt(profilingInfo);
        buffer.putUnsignedInt(value);
        
    }
    
    /**
    OpGetDefaultQueue
    <br/>
    <br/>Returns the default device queue. If a default device queue has not been created, a null queue object is returned.
    <br/>
    <br/>Result Type must be an OpTypeQueue.
    */
    @Override
    public void visitGetDefaultQueue(long resultType, long result) {
        newOpcode(OpGetDefaultQueue, 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        
    }
    
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
    @Override
    public void visitBuildNDRange(long resultType, long result, long globalWorkSize, long localWorkSize, long globalWorkOffset) {
        newOpcode(OpBuildNDRange, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(globalWorkSize);
        buffer.putUnsignedInt(localWorkSize);
        buffer.putUnsignedInt(globalWorkOffset);
        
    }
    
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
    @Override
    public void visitGetKernelLocalSizeForSubgroupCount(long resultType, long result, long subgroupCount, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelLocalSizeForSubgroupCount, 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(subgroupCount);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
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
    @Override
    public void visitGetKernelMaxNumSubgroups(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {
        newOpcode(OpGetKernelMaxNumSubgroups, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(invoke);
        buffer.putUnsignedInt(param);
        buffer.putUnsignedInt(paramSize);
        buffer.putUnsignedInt(paramAlign);
        
    }
    
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
    @Override
    public void visitReadPipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment) {
        newOpcode(OpReadPipe, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitWritePipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment) {
        newOpcode(OpWritePipe, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitReservedReadPipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment) {
        newOpcode(OpReservedReadPipe, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(index);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitReservedWritePipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment) {
        newOpcode(OpReservedWritePipe, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(index);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitReserveReadPipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment) {
        newOpcode(OpReserveReadPipePackets, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(numPackets);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitReserveWritePipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment) {
        newOpcode(OpReserveWritePipePackets, 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(numPackets);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitCommitReadPipe(long pipe, long reserveId, long packetSize, long packetAlignment) {
        newOpcode(OpCommitReadPipe, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitCommitWritePipe(long pipe, long reserveId, long packetSize, long packetAlignment) {
        newOpcode(OpCommitWritePipe, 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
    /**
    OpIsValidReserveId
    <br/>
    <br/>Return true if Reserve Id is a valid reservation id and false otherwise.
    <br/>
    <br/>Result Type must be a Boolean type.
    <br/>
    <br/>Reserve Id must have a type of OpTypeReserveId.
    */
    @Override
    public void visitIsValidReserveId(long resultType, long result, long reserveId) {
        newOpcode(OpIsValidReserveId, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(reserveId);
        
    }
    
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
    @Override
    public void visitGetNumPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment) {
        newOpcode(OpGetNumPipePackets, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitGetMaxPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment) {
        newOpcode(OpGetMaxPipePackets, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitGroupReserveReadPipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment) {
        newOpcode(OpGroupReserveReadPipePackets, 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(numPackets);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitGroupReserveWritePipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment) {
        newOpcode(OpGroupReserveWritePipePackets, 1 + 1 + 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(numPackets);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitGroupCommitReadPipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment) {
        newOpcode(OpGroupCommitReadPipe, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitGroupCommitWritePipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment) {
        newOpcode(OpGroupCommitWritePipe, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(scopeExecution);
        buffer.putUnsignedInt(pipe);
        buffer.putUnsignedInt(reserveId);
        buffer.putUnsignedInt(packetSize);
        buffer.putUnsignedInt(packetAlignment);
        
    }
    
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
    @Override
    public void visitConstantPipeStorage(long resultType, long result, long size, long alignment, long capacity) {
        newOpcode(OpConstantPipeStorage, 1 + 1 + 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(size);
        buffer.putUnsignedInt(alignment);
        buffer.putUnsignedInt(capacity);
        
    }
    
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
    @Override
    public void visitCreatePipeFromPipeStorage(long resultType, long result, long pipeStorage) {
        newOpcode(OpCreatePipeFromPipeStorage, 1 + 1 + 1);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(result);
        buffer.putUnsignedInt(pipeStorage);
        
    }
    @Override
    public void visitIntDecoration(Decoration decoration, long target, long value) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(decoration.ordinal());
        buffer.putUnsignedInt(value);
    }
    
    @Override
    public void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FuncParamAttr.ordinal());
        buffer.putUnsignedInt(attribute.ordinal());
    }
    
    @Override
    public void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FPRoundingMode.ordinal());
        buffer.putUnsignedInt(roundingMode.ordinal());
    }
    
    @Override
    public void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FPFastMathMode.ordinal());
        buffer.putUnsignedInt(fastMathMode.getMask());
    }
    
    @Override
    public void visitLinkageAttributesDecoration(long target, String name, LinkageType type) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.LinkageAttributes.ordinal());
        buffer.putUnsignedInt(type.ordinal());
    }
    
    @Override
    public void visitDecoration(long target, Decoration decoration) {
        newOpcode(OpDecorate, 2);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(decoration.ordinal());
    }
    
    @Override
    public void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(decoration.ordinal());
        buffer.putUnsignedInt(value);
    }
    
    @Override
    public void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FuncParamAttr.ordinal());
        buffer.putUnsignedInt(attribute.ordinal());
    }
    
    @Override
    public void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FPRoundingMode.ordinal());
        buffer.putUnsignedInt(roundingMode.ordinal());
    }
    
    @Override
    public void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FPFastMathMode.ordinal());
        buffer.putUnsignedInt(mathMode.getMask());
    }
    
    @Override
    public void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType) {
        newOpcode(OpMemberDecorate, 4 + sizeOf(name));
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.LinkageAttributes.ordinal());
        writeChars(name);
        buffer.putUnsignedInt(linkageType.ordinal());
    }
    
    @Override
    public void visitMemberDecoration(long structureType, long member, Decoration decoration) {
        newOpcode(OpMemberDecorate, 3);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(decoration.ordinal());
    }
    
    @Override
    public void visitDecorationGroup(long resultID) {
        newOpcode(OpDecorationGroup, 1);
        buffer.putUnsignedInt(resultID);
    }
    
    @Override
    public void visitGroupDecoration(long decorationGroup, long[] targets) {
        newOpcode(OpGroupDecorate, 1 + targets.length);
        buffer.putUnsignedInt(decorationGroup);
        buffer.putUnsignedInts(targets);
    }
    
    @Override
    public void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets) {
        newOpcode(OpDecorationGroup, 1 + memberTargets.length);
        buffer.putUnsignedInt(decorationGroup);
        buffer.putUnsignedInts(memberTargets);
    }
}
