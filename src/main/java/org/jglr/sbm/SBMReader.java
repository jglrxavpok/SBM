package org.jglr.sbm;

import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.*;
import org.jglr.sbm.visitors.CodeCollector;
import org.jglr.sbm.visitors.HeaderCollector;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class SBMReader implements SBMVisitor, Opcodes {

    public static final int SPIRV_MAGIC_NUMBER = 0x07230203;
    private final byte[] input;
    private final ByteOrder endianness;
    private int position;

    public SBMReader(byte[] input) throws IOException {
        this.input = input;
        endianness = findEndianness();
    }

    private ByteOrder findEndianness() throws IOException {
        int bigEndianWord = readWord(ByteOrder.BIG_ENDIAN);
        if(bigEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.BIG_ENDIAN;
        }
        position = 0;
        int littleEndianWord = readWord(ByteOrder.LITTLE_ENDIAN);
        if(littleEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IOException("Magic number (0x"+Integer.toHexString(SPIRV_MAGIC_NUMBER)+") not found, found: 0x"+Integer.toHexString(bigEndianWord)+" and 0x"+Integer.toHexString(littleEndianWord));
    }

    public int nextWord() throws IOException {
        return readWord(endianness);
    }

    protected int readWord(ByteOrder endianness) throws IOException {
        int result = readIntFromArray(endianness);
        position += 4;
        return result;
    }

    private int readIntFromArray(ByteOrder endianness) {
        if(endianness == ByteOrder.BIG_ENDIAN) {
            return (input[3+position] << 24) | (input[2+position] << 16) | (input[1+position] << 8) | (input[position]);
        } else {
            return (input[position] << 24) | (input[1+position] << 16) | (input[2+position] << 8) | (input[3+position]);
        }
    }

    public ByteOrder getEndianness() {
        return endianness;
    }

    public SBMHeaderVisitor visitHeader() throws IOException {
        position = 4; // skip the magic number (4 bytes)
        SBMHeaderVisitor visitor = newHeaderVisitor();
        visitor.visitSpirVersion(nextWord());
        visitor.visitGeneratorMagicNumber(nextWord());
        visitor.visitBound(nextWord());
        visitor.visitInstructionSchema(nextWord());
        return visitor;
    }

    private SBMHeaderVisitor newHeaderVisitor() {
        return new HeaderCollector();
    }

    public SBMCodeVisitor visitCode() throws IOException {
        position = 5*4;
        SBMCodeVisitor visitor = newCodeVisitor();
        Map<Integer, Type> types = new HashMap<>();
        while (position < input.length) {
            int opcodeFull = nextWord();
            int wordCount = opcodeFull >> 16;
            int opcodeID = opcodeFull & 0xFFFF;
            switch (opcodeID) {
                case NOP:
                    break;

                case UNDEF: {
                    Type resultType = nextType(types);
                    int resultID = nextWord();
                    System.out.println("UNDEF! "+resultType);
                }
                break;

                case TYPE_VOID: {
                    int resultID = nextWord();
                    visitor.visitVoidType(resultID);
                    types.put(resultID, new Type("void"));
                }
                break;

                case TYPE_BOOL: {
                    int resultID = nextWord();
                    visitor.visitBoolType(resultID);
                    types.put(resultID, new Type("bool"));
                }
                break;

                case TYPE_INT: {
                    int resultID = nextWord();
                    int width = nextWord();
                    boolean isSigned = toBoolean(nextWord());
                    visitor.visitIntType(resultID, width, isSigned);
                    types.put(resultID, new IntType(width, isSigned));
                }
                break;

                case TYPE_FLOAT: {
                    int resultID = nextWord();
                    int width = nextWord();
                    visitor.visitFloatType(resultID, width);
                    types.put(resultID, new FloatType(width));
                }
                break;

                case TYPE_VEC: {
                    int resultID = nextWord();
                    Type componentType = nextType(types);
                    int componentCount = nextWord();
                    visitor.visitVectorType(resultID, componentType, componentCount);
                    types.put(resultID, new VectorType(componentType, componentCount));
                }
                break;

                case TYPE_MATRIX: {
                    int resultID = nextWord();
                    Type columnType = nextType(types);
                    int columnCount = nextWord();
                    visitor.visitMatrixType(resultID, columnType, columnCount);
                    types.put(resultID, new MatrixType(columnType, columnCount));
                }
                break;

                case TYPE_IMAGE: {
                    int resultID = nextWord();
                    Type sampledType = nextType(types);
                    Dimensionality dimensionality = nextEnumValue(Dimensionality.values());
                    ImageDepth depth = nextEnumValue(ImageDepth.values());
                    boolean arrayed = toBoolean(nextWord());
                    boolean multisampled = toBoolean(nextWord());
                    Sampling sampling = nextEnumValue(Sampling.values());
                    ImageFormat format = nextEnumValue(ImageFormat.values());
                    AccessQualifier qualifier = nextEnumValue(AccessQualifier.values());
                    visitor.visitImageType(resultID, sampledType, dimensionality, depth, arrayed, multisampled, sampling, format, qualifier);
                    types.put(resultID, new ImageType(sampledType, dimensionality, depth, arrayed, multisampled, sampling, format, qualifier));
                }
                break;

                case TYPE_SAMPLER: {
                    int resultID = nextWord();
                    visitor.visitSamplerType(resultID);
                    types.put(resultID, new Type("sampler"));
                }
                break;

                case TYPE_SAMPLED_IMAGE: {
                    int resultID = nextWord();
                    Type imageType = nextType(types);
                    visitor.visitSampledImageType(resultID, imageType);
                    types.put(resultID, new SampledImageType(imageType));
                }
                break;

                case TYPE_ARRAY: {
                    int resultID = nextWord();
                    Type elementType = nextType(types);
                    int length = nextWord();
                    visitor.visitArrayType(resultID, elementType, length);
                    types.put(resultID, new ArrayType(elementType, length));
                }
                break;

                case TYPE_RUNTIME_ARRAY: {
                    int resultID = nextWord();
                    Type elementType = nextType(types);
                    visitor.visitRuntimeArrayType(resultID, elementType);
                    types.put(resultID, new RuntimeArrayType(elementType));
                }
                break;

                case TYPE_STRUCT: {
                    int resultID = nextWord();
                    Type[] structMembers = new Type[wordCount - 2];
                    for (int i = 0; i < wordCount - 2; i++) {
                        structMembers[i] = nextType(types);
                    }
                    visitor.visitStructType(resultID, structMembers);
                    types.put(resultID, new StructType(structMembers));
                }
                break;

                case TYPE_OPAQUE: {
                    int resultID = nextWord();
                    String name = nextString(wordCount - 2);
                    visitor.visitOpaqueType(resultID, name);
                    types.put(resultID, new OpaqueType(name));
                }
                break;

                case TYPE_POINTER: {
                    int resultID = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    Type type = nextType(types);
                    visitor.visitPointerType(resultID, storageClass, type);
                    types.put(resultID, new PointerType(storageClass, type));
                }
                break;

                case SOURCE: {
                    SourceLanguage language = nextEnumValue(SourceLanguage.values());
                    int version = nextWord();
                    int filenameStringID = -1;
                    String sourceCode = null;
                    if(wordCount >= 3) {
                        filenameStringID = nextWord();
                        if(wordCount >= 4) {
                            sourceCode = nextString(wordCount-4);
                        }
                    }
                    visitor.visitSource(language, version, filenameStringID, sourceCode);
                }
                break;

                case NAME: {
                    int target = nextWord();
                    int stringSize = wordCount-2;
                    String name = nextString(stringSize);
                    visitor.visitName(target, name);
                }
                break;

                case MEMBER_NAME: {
                    Type type = nextType(types);
                    int target = nextWord();
                    int stringSize = wordCount-3;
                    String name = nextString(stringSize);
                    visitor.visitMemberName(type, target, name);
                }
                break;

                case STRING: {
                    int resultID = nextWord();
                    String value = nextString(wordCount-2);
                    visitor.visitString(resultID, value);
                    System.out.println(">>Str: "+value);
                }
                break;

                case LINE: {
                    int filenameID = nextWord();
                    int line = nextWord();
                    int column = nextWord();
                    visitor.visitLine(filenameID, line, column);
                }
                break;

                default:
                    System.out.println("Unhandled: " + Opcodes.getName(opcodeID)+" "+opcodeID);
                    break;
            }
        }
        System.out.println("=== TYPES ===");
        types.values().forEach(System.out::println);
        System.out.println("=============");
        // TODO: do the visit
        return visitor;
    }

    private String nextString(int wordCount) throws IOException {
        byte[] data = new byte[wordCount*4];
        for (int i = 0; i < wordCount; i++) {
            int word = nextWord();

            // characters are encoded in little endian
            byte b3 = (byte) (word >> 24 & 0xFF);
            byte b2 = (byte) (word >> 16 & 0xFF);
            byte b1 = (byte) (word >> 8 & 0xFF);
            byte b0 = (byte) (word >> 0 & 0xFF);

            data[i*4] = b0;
            data[i*4+1] = b1;
            data[i*4+2] = b2;
            data[i*4+3] = b3;
        }
        if(data[data.length-1] != '\0') {
            throw new IOException("Invalid string, not null terminated");
        }
        // -1 to remove the null character
        return new String(data, 0, data.length-1, "UTF-8");
    }

    private <T> T nextEnumValue(T[] values) throws IOException {
        int index = nextWord();
        return values[index];
    }

    private Type nextType(Map<Integer, Type> types) throws IOException {
        int id = nextWord();
        if(types.containsKey(id))
            return types.get(id);
        return new Type("Waiting"+id);
    }

    private boolean toBoolean(int value) {
        if(value != 0 && value != 1)
            throw new IllegalArgumentException("Must be 0 or 1 to convert to boolean (was "+value+")");
        return value != 0;
    }

    private SBMCodeVisitor newCodeVisitor() {
        return new CodeCollector();
    }
}
