package org.jglr.sbm.visitors;

import org.jglr.sbm.AccessQualifier;
import org.jglr.sbm.SBMCodeVisitor;
import org.jglr.sbm.SourceLanguage;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.types.Type;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;

public class CodeCollector implements SBMCodeVisitor {
    @Override
    public void visitVoidType(int resultID) {

    }

    @Override
    public void visitBoolType(int resultID) {

    }

    @Override
    public void visitFloatType(int resultID, int width) {

    }

    @Override
    public void visitIntType(int resultID, int width, boolean signed) {

    }

    @Override
    public void visitVectorType(int resultID, Type componentType, int componentCount) {

    }

    @Override
    public void visitMatrixType(int resultID, Type columnType, int columnCount) {

    }

    @Override
    public void visitImageType(int resultID, Type sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {

    }

    @Override
    public void visitSamplerType(int resultID) {

    }

    @Override
    public void visitSampledImageType(int resultID, Type imageType) {

    }

    @Override
    public void visitArrayType(int resultID, Type elementType, int length) {

    }

    @Override
    public void visitRuntimeArrayType(int resultID, Type elementType) {

    }

    @Override
    public void visitStructType(int resultID, Type[] memberTypes) {

    }

    @Override
    public void visitOpaqueType(int resultID, String name) {

    }

    @Override
    public void visitPointerType(int resultID, StorageClass storageClass, Type type) {

    }

    @Override
    public void visitFunctionType(int resultID, Type returnType, Type[] parameterTypes) {

    }

    @Override
    public void visitEventType(int resultID) {

    }

    @Override
    public void visitDeviceEventType(int resultID) {

    }

    @Override
    public void visitReserveIDType(int resultID) {

    }

    @Override
    public void visitQueueType(int resultID) {

    }

    @Override
    public void visitPipeType(int resultID, AccessQualifier accessQualifier) {

    }

    @Override
    public void visitForwardType(int resultID, StorageClass storageClass, Type type) {

    }

    @Override
    public void visitSource(SourceLanguage language, int version, int filenameStringID, String sourceCode) {

    }

    @Override
    public void visitLine(int filenameID, int line, int column) {

    }

    @Override
    public void visitName(int target, String name) {

    }

    @Override
    public void visitMemberName(Type structureType, int target, String name) {

    }

    @Override
    public void visitString(int resultID, String value) {

    }
}
