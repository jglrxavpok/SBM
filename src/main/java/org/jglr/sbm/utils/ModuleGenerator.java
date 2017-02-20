package org.jglr.sbm.utils;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.*;
import org.jglr.sbm.sampler.Dimensionality;
import org.jglr.sbm.sampler.ImageDepth;
import org.jglr.sbm.sampler.Sampling;
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
    private final Map<String, Long> componentIDs;
    private final Map<Label, Long> labelIDs;
    private HeaderVisitor header;
    private CodeVisitor code;
    private long currentID;
    private boolean checks;
    private int tmpID;

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
        getCode().visitSource(language, sourceVersion, getStringID(filename), source);
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
            getCode().visitString(currentID, string); // register the string
            stringIDs.put(string, currentID++);
        }
        return stringIDs.get(string);
    }

    public byte[] toBytes() {
        return writer.toBytes();
    }

    public ModuleGenerator addCapability(Capability capability) {
        getCode().visitCapability(capability);
        return this;
    }

    public ModuleGenerator addSourceExtension(String name) {
        getCode().visitSourceExtension(name);
        return this;
    }

    public ModuleGenerator addSetImport(String name) {
        getCode().visitExtendedInstructionSetImport(getSetID(name), name);
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
        if(!componentIDs.containsKey(component.getID())) {
            componentIDs.put(component.getID(), currentID++);
        }
        return componentIDs.get(component.getID());
    }

    public ModuleGenerator setMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        getCode().visitMemoryModel(addressingModel, memoryModel);
        return this;
    }

    public FunctionGenerator createFunction(ModuleFunction function) {
        FunctionGenerator generator = new FunctionGenerator(this, function);
        generator.init(getComponentID(function));
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
            getCode().visitArrayType(currentID, getTypeID(arrayType.getElementType()), arrayType.getLength());
        } else if(type instanceof FloatType) {
            FloatType floatType = (FloatType) type;
            getCode().visitFloatType(currentID, floatType.getWidth());
        } else if(type instanceof FunctionType) {
            FunctionType functionType = (FunctionType) type;
            getCode().visitFunctionType(currentID, getTypeID(functionType.getReturnType()), getTypeIDs(functionType.getParameters()));
        } else if(type instanceof ImageType) {
            ImageType imageType = (ImageType) type;
            long sampledType = getTypeID(imageType.getSampledType());
            Dimensionality dim = imageType.getDimensionality();
            Sampling sampling = imageType.getSampling();
            ImageDepth depth = imageType.getDepth();
            getCode().visitImageType(currentID, sampledType, dim, depth, imageType.isArrayed(), imageType.isMultisampled(), sampling, imageType.getFormat(), imageType.getQualifier());
        } else if(type instanceof IntType) {
            IntType intType = (IntType) type;
            getCode().visitIntType(currentID, intType.getWidth(), intType.isSigned());
        } else if(type instanceof MatrixType) {
            MatrixType matrixType = (MatrixType) type;
            // FIXME: Declare Matrix capability
            getCode().visitMatrixType(currentID, getTypeID(matrixType.getColumnType()), matrixType.getColumnCount());
        } else if(type instanceof VectorType) {
            VectorType vectorType = (VectorType) type;
            getCode().visitVectorType(currentID, getTypeID(vectorType.getComponentType()), vectorType.getComponentCount());
        } else if(type instanceof OpaqueType) {
            OpaqueType opaqueType = (OpaqueType) type;
            getCode().visitOpaqueType(currentID, opaqueType.getName());
        } else if(type instanceof PipeType) {
            PipeType pipeType = (PipeType) type;
            getCode().visitPipeType(currentID, pipeType.getQualifier());
        } else if(type instanceof PointerType) {
            PointerType pointerType = (PointerType) type;
            getCode().visitPointerType(currentID, pointerType.getStorageClass(), getTypeID(pointerType.getType()));
        } else if(type instanceof RuntimeArrayType) {
            RuntimeArrayType runtimeArrayType = (RuntimeArrayType) type;
            getCode().visitRuntimeArrayType(currentID, getTypeID(runtimeArrayType.getElementType()));
        } else if(type instanceof SampledImageType) {
            SampledImageType sampledImageType = (SampledImageType) type;
            getCode().visitSampledImageType(currentID, getTypeID(sampledImageType.getImageType()));
        } else if(type instanceof StructType) {
            StructType structType = (StructType) type;
            getCode().visitStructType(currentID, getTypeIDs(structType.getStructMembers()));
        } else {
            switch (type.getName()) {
                case "bool":
                    getCode().visitBoolType(currentID);
                    break;

                case "void":
                    getCode().visitVoidType(currentID);
                    break;

                case "sampler":
                    getCode().visitSamplerType(currentID);
                    break;

                case "reserveID":
                    getCode().visitReserveIdType(currentID);
                    break;

                case "queue":
                    getCode().visitQueueType(currentID);
                    break;

                case "event":
                    getCode().visitEventType(currentID);
                    break;

                case "deviceEvent":
                    getCode().visitDeviceEventType(currentID);
                    break;

                default:
                    throw new UnsupportedOperationException("Invalid type: "+type.getName());
            }
        }
    }

    public ModuleGenerator addNamedEntryPoint(String name, ModuleFunction function, ExecutionModel model, ModuleVariable[] interfaces) {
        getCode().visitEntryPoint(model, getComponentID(function), name, getComponentIDs(interfaces));
        return this;
    }

    public ModuleGenerator addEntryPoint(ModuleFunction function, ExecutionModel model, ModuleVariable[] interfaces) {
        return addNamedEntryPoint(function.getName(), function, model, interfaces);
    }

    public ModuleGenerator setExecutionMode(ModuleFunction entryPoint, ExecutionMode mode) {
        getCode().visitExecutionMode(getComponentID(entryPoint), mode);
        return this;
    }

    public boolean performsChecks() {
        return checks;
    }

    public ModuleGenerator performChecks(boolean checks) {
        this.checks = checks;
        return this;
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

    public ModuleConstant constantFloat(String name, FloatType type, float value) {
        return constant(name, type, new long[]{Float.floatToRawIntBits(value)});
    }

    public ModuleConstant constant(String name, Type type, long[] bitPattern) {
        ModuleConstant constant = new ModuleConstant(name, type, bitPattern);
        getCode().visitConstant(getTypeID(type), getComponentID(constant), bitPattern);
        return constant;
    }

    public ModuleConstant constantComposite(String name, Type type, ModuleConstant... constituentValues) {
        long[] constituents = new long[constituentValues.length];
        for (int i = 0; i < constituents.length; i++) {
            constituents[i] = getComponentID(constituentValues[i]);
        }
        ModuleConstant constant = new ModuleConstant(name, type, new long[0]);
        getCode().visitConstantComposite(getTypeID(type), getComponentID(constant), constituents);
        return constant;
    }

    public ModuleVariable declareVariable(String name, Type type, StorageClass storageClass) {
        ModuleVariable var = new ModuleVariable(name, type);
        var.setStorageClass(storageClass);
        getCode().visitVariable(getTypeID(var.getType()), getComponentID(var), var.getStorageClass(), -1);
        return var;
    }

    public void end() {
        header.visitBound(currentID);
    }

    /**
     * Reserves an id for the given name. Use it for forward references
     */
    public ModuleVariable reserveName(String name) {
        if( ! componentIDs.containsKey(name))
            componentIDs.put(name, currentID++);
        return new ModuleVariable(name, Type.VOID);
    }

    public CodeVisitor getCode() {
        return code;
    }

    /**
     * Registers a name that will be shown in the compiled code
     * @param name
     * @return
     */
    public ModuleGenerator name(String name) {
        code.visitName(getComponentID(reserveName(name)), name);
        return this;
    }

    public ModuleGenerator decorate(ModuleComponent toDecorate, DecorationValue decoration) {
        switch(decoration.getType()) {
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
                code.visitIntDecoration(decoration.getType(), getComponentID(toDecorate), ((IntDecorationValue)decoration).getValue());
                break;

            case FuncParamAttr:
                code.visitFunctionParameterAttributeDecoration(getComponentID(toDecorate), ((FunctionParameterAttributeDecorationValue) decoration).getAttribute());
                break;

            case FPRoundingMode:
                code.visitFPRoundingModeDecoration(getComponentID(toDecorate), ((RoundingModeDecorationValue)decoration).getRoundingMode());
                break;

            case FPFastMathMode:
                code.visitFPFastMathModeDecoration(getComponentID(toDecorate), ((FastMathDecorationValue)decoration).getFastMathMode());
                break;

            case LinkageAttributes:
                LinkageDecorationValue linkDecoration = ((LinkageDecorationValue) decoration);
                code.visitLinkageAttributesDecoration(getComponentID(toDecorate), linkDecoration.getName(), linkDecoration.getLinkageType());
                break;

            default:
                code.visitDecoration(getComponentID(toDecorate), decoration.getType());
                break;
        }
        return this;
    }

    public void lineNumber(String filename, int line, int column) {
        code.visitLine(getStringID(filename), line, column);
    }

    public ModuleConstant constantBool(String name, boolean value) {
        ModuleConstant constant = new ModuleConstant(name, Type.BOOL, new long[]{value ? 1 : 0});
        if(value) {
            code.visitTrueConstant(getTypeID(Type.BOOL), getComponentID(constant));
        } else {
            code.visitFalseConstant(getTypeID(Type.BOOL), getComponentID(constant));
        }
        return constant;
    }

    public int nextTmpID() {
        return tmpID++;
    }
}
