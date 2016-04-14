package org.jglr.sbm.visitors;

import org.jglr.sbm.AccessQualifier;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.Type;

public interface SBMTypeVisitor {
    void visitVoidType(long resultID);
    void visitBoolType(long resultID);
    void visitFloatType(long resultID, long width);
    void visitIntType(long resultID, long width, boolean isSigned);
    void visitVectorType(long resultID, Type componentType, long componentCount);
    void visitMatrixType(long resultID, Type columnType, long columnCount);
    void visitImageType(long resultID, Type sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access);
    void visitSamplerType(long resultID);
    void visitSampledImageType(long resultID, Type imageType);
    void visitArrayType(long resultID, Type elementType, long length);
    void visitRuntimeArrayType(long resultID, Type elementType);
    void visitStructType(long resultID, Type[] memberTypes);
    void visitOpaqueType(long resultID, String name);
    void visitPointerType(long resultID, StorageClass storageClass, Type type);
    void visitFunctionType(long resultID, Type returnType, Type[] parameterTypes);
    void visitEventType(long resultID);
    void visitDeviceEventType(long resultID);
    void visitReserveIDType(long resultID);
    void visitQueueType(long resultID);
    void visitPipeType(long resultID, AccessQualifier accessQualifier);
    void visitForwardType(Type type, StorageClass storageClass);
}
