package org.jglr.sbm.visitors;

import org.jglr.flows.io.ByteArray;

import java.nio.ByteOrder;

public class HeaderWriter implements HeaderVisitor {

    private final ByteArray buffer;

    public HeaderWriter() {
        buffer = new ByteArray(4*4);
        buffer.setByteOrder(ByteOrder.BIG_ENDIAN);
        visitInstructionSchema(0);
        visitSpirVersion(0x10000);
    }

    @Override
    public void visitSpirVersion(long value) {
        buffer.putUnsignedInt(value, 0);
    }

    @Override
    public void visitGeneratorMagicNumber(long value) {
        buffer.putUnsignedInt(value, 4);
    }

    @Override
    public void visitBound(long value) {
        buffer.putUnsignedInt(value, 8);
    }

    @Override
    public void visitInstructionSchema(long value) {
        buffer.putUnsignedInt(value, 12);
    }

    public byte[] toBytes() {
        return buffer.backingArray();
    }
}
