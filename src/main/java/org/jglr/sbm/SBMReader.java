package org.jglr.sbm;

import org.jglr.sbm.visitors.CodeReader;
import org.jglr.sbm.visitors.HeaderReader;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class SBMReader implements SBMVisitor {

    public static final int SPIRV_MAGIC_NUMBER = 0x07230203;
    private final InputStream input;
    private final byte[] wordBuffer;
    private final ByteOrder endianness;

    public SBMReader(InputStream input) throws IOException {
        this.input = input;
        wordBuffer = new byte[4];
        endianness = findEndianness();
    }

    private ByteOrder findEndianness() throws IOException {
        fillWordBuffer();
        int bigEndianWord = readIntFromArray(wordBuffer, ByteOrder.BIG_ENDIAN);
        if(bigEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.BIG_ENDIAN;
        }
        int littleEndianWord = readIntFromArray(wordBuffer, ByteOrder.LITTLE_ENDIAN);
        if(littleEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IOException("Magic number (0x"+Integer.toHexString(SPIRV_MAGIC_NUMBER)+") not found, found: 0x"+Integer.toHexString(bigEndianWord)+" and 0x"+Integer.toHexString(littleEndianWord));
    }

    public int nextWord() throws IOException {
        return readWord(endianness);
    }

    protected int readWord(ByteOrder endianness) throws IOException {
        fillWordBuffer();
        return readIntFromArray(wordBuffer, endianness);
    }

    private void fillWordBuffer() throws IOException {
        for (int i = 0; i < 4;) {
            int readCount = input.read(wordBuffer, i, wordBuffer.length-i);
            if(readCount < 0)
                throw new EOFException();
            i += readCount;
        }
    }

    private int readIntFromArray(byte[] arr, ByteOrder endianness) {
        if(endianness == ByteOrder.BIG_ENDIAN) {
            return (arr[3] << 24) | (arr[2] << 16) | (arr[1] << 8) | (arr[0]);
        } else {
            return (arr[0] << 24) | (arr[1] << 16) | (arr[2] << 8) | (arr[3]);
        }
    }

    public ByteOrder getEndianness() {
        return endianness;
    }

    public SBMHeaderVisitor visitHeader() throws IOException {
        SBMHeaderVisitor visitor = newHeaderVisitor();
        visitor.visitSpirVersion(nextWord());
        visitor.visitGeneratorMagicNumber(nextWord());
        visitor.visitBound(nextWord());
        visitor.visitInstructionSchema(nextWord());
        return visitor;
    }

    private SBMHeaderVisitor newHeaderVisitor() {
        return new HeaderReader();
    }

    public SBMCodeVisitor visitCode() throws IOException {
        SBMCodeVisitor visitor = newCodeVisitor();
        // TODO: do the visit
        return visitor;
    }

    private SBMCodeVisitor newCodeVisitor() {
        return new CodeReader();
    }
}
