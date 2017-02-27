package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.modes.InvocationsExecutionMode;
import org.jglr.sbm.modes.OutputVerticesExecutionMode;
import org.jglr.sbm.modes.SizeExecutionMode;
import org.jglr.sbm.modes.VecTypeHintExecutionMode;
import org.jglr.sbm.types.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;

public class ModuleReader implements ModuleVisitor, Opcodes {

    public static final long SPIRV_MAGIC_NUMBER = 0x07230203;
    private final int[] input;
    private final ByteOrder endianness;
    int position;
    private ModuleReaderDispatcher readerDispatcher;

    public ModuleReader(byte[] input) throws IOException {
        this.input = toUnsignedBytes(input);
        endianness = findEndianness();
        readerDispatcher = new ModuleReaderDispatcher(this);
    }

    private int[] toUnsignedBytes(byte[] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = input[i] & 0xFF;
        }
        return result;
    }

    private ByteOrder findEndianness() throws IOException {
        long bigEndianWord = readWord(ByteOrder.BIG_ENDIAN);
        if(bigEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.BIG_ENDIAN;
        }
        position = 0;
        long littleEndianWord = readWord(ByteOrder.LITTLE_ENDIAN);
        if(littleEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IOException("Magic number (0x"+Long.toHexString(SPIRV_MAGIC_NUMBER)+") not found, found: 0x"+Long.toHexString(bigEndianWord)+" and 0x"+Long.toHexString(littleEndianWord));
    }

    long nextWord() throws IOException {
        return readWord(endianness);
    }

    private long readWord(ByteOrder endianness) throws IOException {
        long result = readWordFromArray(endianness) & 0x00000000FFFFFFFFL;
        position += 4;
        return result;
    }

    private long readWordFromArray(ByteOrder endianness) {
        if(endianness == ByteOrder.BIG_ENDIAN) {
            return ((input[3 + position] << 24) | (input[2 + position] << 16) | (input[1 + position] << 8) | (input[position]));
        } else {
            return ((input[position] << 24) | (input[1+position] << 16) | (input[2+position] << 8) | (input[3+position]));
        }
    }

    public ByteOrder getEndianness() {
        return endianness;
    }

    public HeaderVisitor visitHeader() throws IOException {
        position = 4; // skip the magic number (4 bytes)
        HeaderVisitor visitor = newHeaderVisitor();
        visitor.visitSpirVersion(nextWord());
        visitor.visitGeneratorMagicNumber(nextWord());
        visitor.visitBound(nextWord());
        visitor.visitInstructionSchema(nextWord());
        return visitor;
    }

    public HeaderVisitor newHeaderVisitor() throws IOException {
        return new HeaderCollector();
    }

    public CodeVisitor visitCode() throws IOException {
        position = 5*4; // skip the header (20 bytes long)
        CodeVisitor visitor = newCodeVisitor();
        while (position < input.length) {
            long opcodeFull = nextWord();
            int wordCount = (int) (opcodeFull >> 16);
            int opcodeID = (int) (opcodeFull & 0xFFFF);
            readerDispatcher.dispatch(opcodeID, wordCount, visitor);
        }
        visitor.visitEnd();
        return visitor;
    }

    long[] nextWords(int count) throws IOException {
        long[] result = new long[count];
        for (int i = 0; i < count; i++) {
            result[i] = nextWord();
        }
        return result;
    }

    ExecutionMode readMode(ExecutionMode.Type type) throws IOException {
        switch (type) {
            case Invocations:
                return new InvocationsExecutionMode(nextWord());

            case LocalSize:
            case LocalSizeHint:
                return new SizeExecutionMode(type, nextWord(), nextWord(), nextWord());

            case OutputVertices:
                return new OutputVerticesExecutionMode(nextWord());

            case VecTypeHint: {
                long componentCount = nextWord();
                VecTypeHintExecutionMode.DataType dataType = nextEnumValue(VecTypeHintExecutionMode.DataType.values());
                return new VecTypeHintExecutionMode(componentCount, dataType);
            }

        }
        return new ExecutionMode(type);
    }

    String nextString() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (;;) {
            long word = nextWord();

            // characters are encoded in little endian
            byte b3 = (byte) (word >> 24 & 0xFF);
            byte b2 = (byte) (word >> 16 & 0xFF);
            byte b1 = (byte) (word >> 8 & 0xFF);
            byte b0 = (byte) (word & 0xFF);

            if(b3 == 0) {
                break;
            }
            if(b2 == 0) {
                break;
            }
            if(b1 == 0) {
                break;
            }
            if(b0 == 0) {
                break;
            }
            baos.write(new byte[] {b0,b1,b2,b3});

        }
        baos.flush();
        baos.close();
        byte[] bytes = baos.toByteArray();
        // -1 to remove the null character
        return new String(bytes, "UTF-8").replace("\0", "");
    }

    void visitDecoration(CodeVisitor visitor, Decoration decoration, long target, int wordCount) throws IOException {
        switch (decoration) {
            case SpecId:
            case ArrayStride:
            case MatrixStride:
            case BuiltIn:
            case Stream:
            case Location:
            case Component:
            case Index:
            case Binding:
            case DescriptorSet:
            case Offset:
            case XfbBuffer:
            case XfbStride:
            case InputAttachmentIndex:
            case Alignment:
                visitor.visitIntDecoration(decoration, target, nextWord());
                break;

            case FuncParamAttr:
                visitor.visitFunctionParameterAttributeDecoration(target, nextEnumValue(FunctionParameterAttribute.values()));
                break;

            case FPRoundingMode:
                visitor.visitFPRoundingModeDecoration(target, nextEnumValue(FPRoundingMode.values()));
                break;

            case FPFastMathMode:
                visitor.visitFPFastMathModeDecoration(target, new FPFastMathMode(nextWord()));
                break;

            case LinkageAttributes:
                int strSize = wordCount-2;
                visitor.visitLinkageAttributesDecoration(target, nextString(strSize), nextEnumValue(LinkageType.values()));
                break;

            default:
                visitor.visitDecoration(target, decoration);
                break;
        }
    }

    void visitMemberDecoration(CodeVisitor visitor, Decoration decoration, long structureType, long member, int wordCount) throws IOException {
        switch (decoration) {
            case SpecId:
            case ArrayStride:
            case MatrixStride:
            case BuiltIn:
            case Stream:
            case Location:
            case Component:
            case Index:
            case Binding:
            case DescriptorSet:
            case Offset:
            case XfbBuffer:
            case XfbStride:
            case InputAttachmentIndex:
            case Alignment:
                visitor.visitIntMemberDecoration(decoration, structureType, member, nextWord());
                break;

            case FuncParamAttr:
                visitor.visitFunctionParameterAttributeMemberDecoration(structureType, member, nextEnumValue(FunctionParameterAttribute.values()));
                break;

            case FPRoundingMode:
                visitor.visitFPRoundingModeMemberDecoration(structureType, member, nextEnumValue(FPRoundingMode.values()));
                break;

            case FPFastMathMode:
                visitor.visitFPFastMathModeMemberDecoration(structureType, member, new FPFastMathMode(nextWord()));
                break;

            case LinkageAttributes:
                int strSize = wordCount-2;
                visitor.visitLinkageAttributesMemberDecoration(structureType, member, nextString(strSize), nextEnumValue(LinkageType.values()));
                break;

            default:
                visitor.visitMemberDecoration(structureType, member, decoration);
                break;
        }
    }

    String nextString(int wordCount) throws IOException {
        byte[] data = new byte[wordCount*4];
        for (int i = 0; i < wordCount; i++) {
            long word = nextWord();

            // characters are encoded in little endian
            byte b3 = (byte) ((word >> 24) & 0xFF);
            byte b2 = (byte) ((word >> 16) & 0xFF);
            byte b1 = (byte) ((word >> 8) & 0xFF);
            byte b0 = (byte) (word & 0xFF);

            data[i*4] = b0;
            data[i*4+1] = b1;
            data[i*4+2] = b2;
            data[i*4+3] = b3;
        }
        int len = data.length-1;
        for (; len >= 0; len--) {
            if(data[len] != 0)
                break;
        }
        // -1 to remove the null character
        return new String(data, 0, len+1, "UTF-8").replace("\0", "");
    }

    <T> T nextEnumValue(T[] values) throws IOException {
        long index = nextWord();
        return values[((int) index) & 0xFFFF];
    }

    @Deprecated
    private Type nextType(Map<Long, Type> types) throws IOException {
        long id = nextWord();
        if(types.containsKey(id))
            return types.get(id);
        return new Type("Waiting"+id);
    }

    public CodeVisitor newCodeVisitor() throws IOException {
        return new CodeCollector();
    }
}
