package org.jglr.sbm;

import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.Type;

public interface SBMCodeVisitor {

    // Types
    void visitVoidType(int resultID);
    void visitBoolType(int resultID);
    void visitFloatType(int resultID, int width);
    void visitIntType(int resultID, int width, boolean isSigned);
    void visitVectorType(int resultID, Type componentType, int componentCount);
    void visitMatrixType(int resultID, Type columnType, int columnCount);
    void visitImageType(int resultID, Type sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access);
    void visitSamplerType(int resultID);
    void visitSampledImageType(int resultID, Type imageType);
    void visitArrayType(int resultID, Type elementType, int length);
    void visitRuntimeArrayType(int resultID, Type elementType);
    void visitStructType(int resultID, Type[] memberTypes);
    void visitOpaqueType(int resultID, String name);
    void visitPointerType(int resultID, StorageClass storageClass, Type type);
    void visitFunctionType(int resultID, Type returnType, Type[] parameterTypes);
    void visitEventType(int resultID);
    void visitDeviceEventType(int resultID);
    void visitReserveIDType(int resultID);
    void visitQueueType(int resultID);
    void visitPipeType(int resultID, AccessQualifier accessQualifier);
    void visitForwardType(int resultID, StorageClass storageClass, Type type);

    // source info
    void visitSource(SourceLanguage language, int version, int filenameStringID, String sourceCode);
    void visitLine(int filenameID, int line, int column);

    void visitName(int target, String name);
    void visitMemberName(Type structureType, int target, String name);
    void visitString(int resultID, String value);
}
