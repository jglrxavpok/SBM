package org.jglr.sbm.visitors;

import jdk.internal.org.objectweb.asm.ByteVector;
import org.jglr.flows.io.ByteArray;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ByteChannel;

public class ModuleWriter implements ModuleVisitor {

    private final HeaderWriter headerWriter;
    private final CodeWriter codeWriter;

    public ModuleWriter() {
        headerWriter = new HeaderWriter();
        codeWriter = new CodeWriter();
    }

    @Override
    public HeaderVisitor visitHeader() throws IOException {
        return headerWriter;
    }

    @Override
    public CodeVisitor visitCode() throws IOException {
        return codeWriter;
    }

    public byte[] toBytes() {
        ByteArray resultBuffer = new ByteArray();
        resultBuffer.setByteOrder(ByteOrder.BIG_ENDIAN);
        resultBuffer.putUnsignedInt(0x07230203);
        resultBuffer.putArray(headerWriter.toBytes());
        resultBuffer.putArray(codeWriter.toBytes());
        return resultBuffer.backingArray();
    }
}
