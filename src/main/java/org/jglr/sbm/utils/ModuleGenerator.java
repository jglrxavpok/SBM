package org.jglr.sbm.utils;

import org.jglr.sbm.*;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.*;
import org.jglr.sbm.visitors.CodeVisitor;
import org.jglr.sbm.visitors.HeaderVisitor;
import org.jglr.sbm.visitors.ModuleWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ModuleGenerator {

    private final ModuleWriter writer;
    private final Map<String, Long> stringIDs;
    private final Map<String, Long> setIDs;
    private final Map<Type, Long> typeIDs;
    private final Map<ModuleComponent, Long> componentIDs;
    private final Map<Label, Long> labelIDs;
    HeaderVisitor header;
    CodeVisitor code;
    private long currentID;
    private boolean checks;

    public ModuleGenerator() {
        this(new ModuleWriter());
    }

    public ModuleGenerator(ModuleWriter writer) {
        checks = true;
        currentID = 1;
        this.writer = writer;
        try {
            header = writer.visitHeader();
            code = writer.visitCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIDs = new HashMap<>();
        stringIDs = new HashMap<>();
        typeIDs = new HashMap<>();
        componentIDs = new HashMap<>();
        labelIDs = new HashMap<>();
    }

    public ModuleGenerator setSpirVersion(long version) {
        header.visitSpirVersion(version);
        return this;
    }

    public ModuleGenerator setGeneratorMagicNumber(long version) {
        header.visitGeneratorMagicNumber(version);
        return this;
    }

    public ModuleGenerator setSourceInfos(SourceLanguage language, long sourceVersion, String filename, String source) {
        code.visitSource(language, sourceVersion, getStringID(filename), source);
        return this;
    }

    /**
     * Finds the &lt;id&gt; of the given string. Assigns one if none found
     * @param string
     *      The string to assign an &lt;id&gt; to
     * @return
     *      The assigned &lt;id&gt;
     */
    public long getStringID(String string) {
        if(string == null)
            return -1;
        if(!stringIDs.containsKey(string)) {
            code.visitString(currentID, string); // register the string
            stringIDs.put(string, currentID++);
        }
        return stringIDs.get(string);
    }

    public byte[] toBytes() {
        return writer.toBytes();
    }

    public ModuleGenerator addCapability(Capability capability) {
        code.visitCapability(capability);
        return this;
    }

    public ModuleGenerator addSourceExtension(String name) {
        code.visitSourceExtension(name);
        return this;
    }

    public ModuleGenerator addSetImport(String name) {
        code.visitExtendedInstructionSetImport(getSetID(name), name);
        return this;
    }

    public long getSetID(String name) {
        if(name == null)
            return -1;
        if(!setIDs.containsKey(name)) {
            setIDs.put(name, currentID++);
        }
        return setIDs.get(name);
    }

    public long[] getComponentIDs(ModuleComponent[] components) {
        long[] result = new long[components.length];
        for (int i = 0; i < components.length; i++) {
            result[i] = getComponentID(components[i]);
        }
        return result;
    }

    public long getComponentID(ModuleComponent component) {
        if(component == null)
            return -1;
        if(!componentIDs.containsKey(component)) {
            componentIDs.put(component, currentID++);
        }
        return componentIDs.get(component);
    }

    public ModuleGenerator setMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        code.visitMemoryModel(addressingModel, memoryModel);
        return this;
    }

    public FunctionGenerator createFunction(ModuleFunction function, Label startLabel) {
        FunctionGenerator generator = new FunctionGenerator(this, function);
        generator.init(getComponentID(function), startLabel);
        return generator;
    }

    public long[] getTypeIDs(Type[] types) {
        long[] result = new long[types.length];
        for (int i = 0; i < types.length; i++) {
            result[i] = getTypeID(types[i]);
        }
        return result;
    }

    public long getTypeID(Type type) {
        if(type == null)
            return -1;
        if(!typeIDs.containsKey(type)) {
            long id = currentID++;
            typeIDs.put(type, id);
            visitType(type, id); // register the type
        }
        return typeIDs.get(type);
    }

    private void visitType(Type type, long currentID) {
        if(type instanceof ArrayType) {
            ArrayType arrayType = (ArrayType) type;
            code.visitArrayType(currentID, getTypeID(arrayType.getElementType()), arrayType.getLength());
        } else if(type instanceof FloatType) {
            FloatType floatType = (FloatType) type;
            code.visitFloatType(currentID, floatType.getWidth());
        } else if(type instanceof FunctionType) {
            FunctionType functionType = (FunctionType) type;
            code.visitFunctionType(currentID, getTypeID(functionType.getReturnType()), getTypeIDs(functionType.getParameters()));
        } else if(type instanceof ImageType) {
            ImageType imageType = (ImageType) type;
            long sampledType = getTypeID(imageType.getSampledType());
            Dimensionality dim = imageType.getDimensionality();
            Sampling sampling = imageType.getSampling();
            ImageDepth depth = imageType.getDepth();
            code.visitImageType(currentID, sampledType, dim, depth, imageType.isArrayed(), imageType.isMultisampled(), sampling, imageType.getFormat(), imageType.getQualifier());
        } else if(type instanceof IntType) {
            IntType intType = (IntType) type;
            code.visitIntType(currentID, intType.getWidth(), intType.isSigned());
        } else if(type instanceof MatrixType) {
            MatrixType matrixType = (MatrixType) type;
            code.visitMatrixType(currentID, getTypeID(matrixType.getColumnType()), matrixType.getColumnCount());
        } else if(type instanceof VectorType) {
            VectorType vectorType = (VectorType) type;
            code.visitVectorType(currentID, getTypeID(vectorType.getComponentType()), vectorType.getComponentCount());
        } else if(type instanceof OpaqueType) {
            OpaqueType opaqueType = (OpaqueType) type;
            code.visitOpaqueType(currentID, opaqueType.getName());
        } else if(type instanceof PipeType) {
            PipeType pipeType = (PipeType) type;
            code.visitPipeType(currentID, pipeType.getQualifier());
        } else if(type instanceof PointerType) {
            PointerType pointerType = (PointerType) type;
            code.visitPointerType(currentID, pointerType.getStorageClass(), getTypeID(pointerType.getType()));
        } else if(type instanceof RuntimeArrayType) {
            RuntimeArrayType runtimeArrayType = (RuntimeArrayType) type;
            code.visitRuntimeArrayType(currentID, getTypeID(runtimeArrayType.getElementType()));
        } else if(type instanceof SampledImageType) {
            SampledImageType sampledImageType = (SampledImageType) type;
            code.visitSampledImageType(currentID, getTypeID(sampledImageType.getImageType()));
        } else if(type instanceof StructType) {
            StructType structType = (StructType) type;
            code.visitStructType(currentID, getTypeIDs(structType.getStructMembers()));
        } else {
            switch (type.getName()) {
                case "bool":
                    code.visitBoolType(currentID);
                    break;

                case "void":
                    code.visitVoidType(currentID);
                    break;

                case "sampler":
                    code.visitSamplerType(currentID);
                    break;

                case "reserveID":
                    code.visitReserveIDType(currentID);
                    break;

                case "queue":
                    code.visitQueueType(currentID);
                    break;

                case "event":
                    code.visitEventType(currentID);
                    break;

                case "deviceEvent":
                    code.visitDeviceEventType(currentID);
                    break;

                default:
                    throw new UnsupportedOperationException("Invalid type: "+type.getName());
            }
        }
    }

    public void addEntryPoint(ModuleFunction function, ExecutionModel model, ModuleVariable[] interfaces) {
        code.visitEntryPoint(model, getComponentID(function), function.getName(), getComponentIDs(interfaces));
    }

    public void setExecutionMode(ModuleFunction entryPoint, ExecutionMode mode) {
        code.visitExecutionMode(getComponentID(entryPoint), mode);
    }

    public boolean performsChecks() {
        return checks;
    }

    public ModuleGenerator performChecks(boolean checks) {
        this.checks = checks;
        return this;
    }

    public boolean hasComponentID(ModuleComponent component) {
        return componentIDs.containsKey(component);
    }

    public long getLabelID(Label label) {
        if(label == null)
            return -1;
        if(!labelIDs.containsKey(label)) {
            long id = currentID++;
            labelIDs.put(label, id);
        }
        return labelIDs.get(label);
    }
}
