package org.jglr.sbm.visitors;

import org.jglr.sbm.AccessQualifier;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.sampler.Dimensionality;
import org.jglr.sbm.sampler.ImageDepth;
import org.jglr.sbm.sampler.ImageFormat;
import org.jglr.sbm.sampler.Sampling;

public interface TypeVisitor {
    /**
     * Declare the void type.
     */
    void visitVoidType(long resultID);

    /**
     * Declare the Boolean type. Values of this type can only be either true or false. There is no physical size or bit pattern defined for these values. If they are stored (in conjunction with OpVariable), they can only be used with logical addressing operations, not physical, and only with non-externally visible shader Storage Classes: Workgroup, CrossWorkgroup, Private, and Function.
     */
    void visitBoolType(long resultID);

    /**
     * Declare a new floating-point type.<br/>
     <br/>
     Width specifies how many bits wide the type is. The bit pattern of a floating-point value is as described by the IEEE 754 standard.
     */
    void visitFloatType(long resultID, long width);

    /**
     * Declare a new integer type.<br/>
     <br/>
     Width specifies how many bits wide the type is. This literal operand is limited to a single word. The bit pattern of a signed integer value is two’s complement.<br/>
     <br/>
     Signedness specifies whether there are signed semantics to preserve or validate.<br/>
     0 indicates unsigned, or no signedness semantics<br/>
     1 indicates signed semantics.<br/>
     In all cases, the type of operation of an instruction comes from the instruction’s opcode, not the signedness of the operands.
     */
    void visitIntType(long resultID, long width, boolean isSigned);

    /**
     * Declare a new vector type.<br/>
     <br/>
     Component Type is the type of each component in the resulting type. It must be a scalar type.<br/>
     <br/>
     Component Count is the number of components in the resulting type. It must be at least 2.<br/>
     <br/>
     Components are numbered consecutively, starting with 0.
     */
    void visitVectorType(long resultID, long componentType, long componentCount);

    /**
     * Declare a new matrix type.<br/>
     <br/>
     Column Type is the type of each column in the matrix. It must be vector type.<br/>
     <br/>
     Column Count is the number of columns in the new matrix type. It must be at least 2.<br/>
     <br/>
     Matrix columns are numbered consecutively, starting with 0. This is true independently of any Decorations describing the memory layout of a matrix (e.g., RowMajor or MatrixStride).
     */
    void visitMatrixType(long resultID, long columnType, long columnCount);

    /**
     * Declare a new image type. Consumed, for example, by OpTypeSampledImage. This type is opaque: values of this type have no defined physical size or bit pattern.<br/>
     <br/>
     Sampled Type is the type of the components that result from sampling or reading from this image type. Must be a scalar numerical type or OpTypeVoid.<br/>
     <br/>
     Dim is the image dimensionality (Dim).<br/>
     <br/>
     Depth is whether or not this image is a depth image. (Note that whether or not depth comparisons are actually done is a property of the sampling opcode, not of this type declaration.)<br/>
     0 indicates not a depth image<br/>
     1 indicates a depth image<br/>
     2 means no indication as to whether this is a depth or non-depth image<br/>
     <br/>
     Arrayed must be one of the following indicated values:<br/>
     0 indicates non-arrayed content<br/>
     1 indicates arrayed content<br/>
     <br/>
     MS must be one of the following indicated values:<br/>
     0 indicates single-sampled content<br/>
     1 indicates multisampled content<br/>
     <br/>
     Sampled indicates whether or not this image will be accessed in combination with a sampler, and must be one of the following values:<br/>
     0 indicates this is only known at run time, not at compile time<br/>
     1 indicates will be used with sampler<br/>
     2 indicates will be used without a sampler (a storage image)<br/>
     <br/>
     Image Format is the Image Format, which can be Unknown, depending on the client API.<br/>
     <br/>
     If Dim is SubpassData, Sampled must be 2, Image Format must be Unknown, and the Execution Model must be Fragment.<br/>
     <br/>
     Access Qualifier is an image Access Qualifier.
     */
    void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access);

    /**
     * Declare the sampler type. Consumed by OpSampledImage. This type is opaque: values of this type have no defined physical size or bit pattern.
     */
    void visitSamplerType(long resultID);

    /**
     * Declare a sampled image type, the Result Type of OpSampledImage, or an externally combined sampler and image. This type is opaque: values of this type have no defined physical size or bit pattern.<br/>
     <br/>
     Image Type must be an OpTypeImage. It is the type of the image in the combined sampler and image type.
     */
    void visitSampledImageType(long resultID, long imageType);

    /**
     * Declare a new array type: a dynamically-indexable ordered aggregate of elements all having the same type.<br/>
     <br/>
     Element Type is the type of each element in the array.<br/>
     <br/>
     Length is the number of elements in the array. It must be at least 1. Length must come from a constant instruction of an integer-type scalar whose value is at least 1.<br/>
     <br/>
     Array elements are number consecutively, starting with 0.
     */
    void visitArrayType(long resultID, long elementType, long length);

    /**
     * Declare a new run-time array type. Its length is not known at compile time.<br/>
     <br/>
     Element Type is the type of each element in the array. It must be a concrete type.<br/>
     <br/>
     See OpArrayLength for getting the Length of an array of this type.<br/>
     <br/>
     Objects of this type can only be created with OpVariable using the Uniform Storage Class.
     */
    void visitRuntimeArrayType(long resultID, long elementType);

    /**
     * Declare a new structure type: an aggregate of potentially heterogeneous members.<br/>
     <br/>
     Member N type is the type of member N of the structure. The first member is member 0, the next is member 1, …<br/>
     <br/>
     If an operand is not yet defined, it must be defined by an OpTypePointer, where the type pointed to is an OpTypeStruct.
     */
    void visitStructType(long resultID, long[] memberTypes);

    /**
     * Declare a structure type with no body specified.
     */
    void visitOpaqueType(long resultID, String name);

    /**
     * Declare a new pointer type.<br/>
     <br/>
     Storage Class is the Storage Class of the memory holding the object pointed to. If there was a forward reference to this type from an OpTypeForwardPointer, the Storage Class of that instruction must equal the Storage Class of this instruction.<br/>
     <br/>
     Type is the type of the object pointed to.
     */
    void visitPointerType(long resultID, StorageClass storageClass, long type);

    /**
     * Declare a new function type.<br/>
     <br/>
     OpFunction will use this to declare the return type and parameter types of a function. OpFunction is the only valid use of OpTypeFunction.<br/>
     <br/>
     Return Type is the type of the return value of functions of this type. It must be a concrete or abstract type, or a pointer to such a type. If the function has no return value, Return Type must be OpTypeVoid.<br/>
     <br/>
     Parameter N Type is the type <id> of the type of parameter N.
     */
    void visitFunctionType(long resultID, long returnType, long[] parameterTypes);

    /**
     * Declare an OpenCL event type.
     */
    void visitEventType(long resultID);

    /**
     * Declare an OpenCL device-side event type.
     */
    void visitDeviceEventType(long resultID);

    /**
     * Declare an OpenCL reservation id type.
     */
    void visitReserveIDType(long resultID);

    /**
     * Declare an OpenCL queue type.
     */
    void visitQueueType(long resultID);

    /**
     * Declare an OpenCL pipe type.<br/>
        <br/>
     Qualifier is the pipe access qualifier.
     */
    void visitPipeType(long resultID, AccessQualifier accessQualifier);

    /**
     * Declare the Storage Class for a forward reference to a pointer.<br/>
     <br/>
     Pointer Type is a forward reference to the result of an OpTypePointer. The type of object the pointer points to is declared by the OpTypePointer instruction, not this instruction. Subsequent OpTypeStruct instructions can use Pointer Type as an operand.<br/>
     <br/>
     Storage Class is the Storage Class of the memory holding the object pointed to.
     */
    void visitForwardType(long type, StorageClass storageClass);

    /**
     * Declare the named-barrier type.
     */
    void visitNamedBarrierType(long resultID);

    /**
     * Declare the OpenCL pipe-storage type.
     */
    void visitPipeStorageType(long resultID);
}
