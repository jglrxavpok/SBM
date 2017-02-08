package org.jglr.sbm.utils;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.*;
import org.jglr.sbm.sampler.ImageOperands;
import org.jglr.sbm.types.PointerType;
import org.jglr.sbm.types.Type;

import java.util.HashMap;
import java.util.Map;

public class FunctionGenerator {

    private final ModuleGenerator generator;
    private final ModuleFunction function;
    private long id;

    public FunctionGenerator(ModuleGenerator generator, ModuleFunction function) {
        this.generator = generator;
        this.function = function;
    }

    public void end() {
        generator.getCode().visitFunctionEnd();
    }

    void init(long id, Label startLabel) {
        generator.getCode().visitFunction(generator.getTypeID(function.getReturnType()), id, function.getControl(), generator.getTypeID(function.getFunctionType()));
        label(startLabel);
    }

    public FunctionGenerator load(ModuleVariable resultHolder, ModuleVariable pointerVariable) {
        return load(resultHolder, pointerVariable,  null);
    }

    public FunctionGenerator load(ModuleVariable resultHolder, ModuleVariable pointerVariable, MemoryAccess access) {
        checkType(pointerVariable, PointerType.class);
        checkCorrectPointerType(pointerVariable, resultHolder);
        generator.getCode().visitLoad(generator.getTypeID(resultHolder.getType()), generator.getComponentID(resultHolder), generator.getComponentID(pointerVariable), access);
        return this;
    }

    public FunctionGenerator store(ModuleComponent toStore, ModuleVariable pointerVariable) {
        return store(toStore, pointerVariable, null);
    }

    public FunctionGenerator store(ModuleComponent toStore, ModuleVariable pointerVariable, MemoryAccess access) {
        checkType(pointerVariable, PointerType.class);
        checkCorrectPointerType(pointerVariable, toStore);
        generator.getCode().visitStore(generator.getComponentID(pointerVariable), generator.getComponentID(toStore), access);
        return this;
    }

    private void visitDecoration(long id, DecorationValue decoration) {
        switch (decoration.getType()) {
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
                generator.getCode().visitIntDecoration(decoration.getType(), id, ((IntDecorationValue) decoration).getValue());
                break;

            case FuncParamAttr:
                generator.getCode().visitFunctionParameterAttributeDecoration(id, ((FunctionParameterAttributeDecorationValue) decoration).getAttribute());
                break;

            case FPRoundingMode:
                generator.getCode().visitFPRoundingModeDecoration(id, ((RoundingModeDecorationValue) decoration).getRoundingMode());
                break;

            case FPFastMathMode:
                generator.getCode().visitFPFastMathModeDecoration(id, ((FastMathDecorationValue) decoration).getFastMathMode());
                break;

            case LinkageAttributes:
                LinkageDecorationValue linkageDecoration = (LinkageDecorationValue) decoration;
                generator.getCode().visitLinkageAttributesDecoration(id, linkageDecoration.getName(), linkageDecoration.getLinkageType());
                break;

            default:
                generator.getCode().visitDecoration(id, decoration.getType());
                break;
        }
    }

    private void registerType(Type type) {
        generator.getTypeID(type); // forces generator to generate an ID if none exist and register it
    }

    private void checkCorrectPointerType(ModuleVariable pointerVariable, ModuleComponent object) {
        if(!generator.performsChecks())
            return;
        checkType(((PointerType)pointerVariable.getType()).getType(), object.getType().getClass());
    }

    private void checkType(ModuleComponent component, Class<? extends Type> typeClass) {
        checkType(component.getType(), typeClass);
    }

    private void checkType(Type type, Class<? extends Type> typeClass) {
        if(!generator.performsChecks())
            return;
        if(!typeClass.isAssignableFrom(type.getClass())) {
            throw new IllegalArgumentException("Types do not match, requested: "+typeClass.getSimpleName()+", got: "+type+" ("+type.getClass().getSimpleName()+")");
        }
    }

    public FunctionGenerator label(Label label) {
        generator.getCode().visitLabel(generator.getLabelID(label));
        return this;
    }

    public FunctionGenerator returnVoid() {
        generator.getCode().visitReturn();
        return this;
    }

    public FunctionGenerator kill() {
        generator.getCode().visitKill();
        return this;
    }

    public FunctionGenerator returnValue(ModuleVariable value) {
        generator.getCode().visitReturnValue(generator.getComponentID(value));
        return this;
    }

    public FunctionGenerator sampleImageImplicitLOD(ModuleVariable holder, ModuleComponent image, ModuleComponent coordinates) {
        return sampleImageImplicitLOD(holder, image, coordinates, new ImageOperands(0x0), new HashMap<>());
    }

    public FunctionGenerator sampleImageImplicitLOD(ModuleVariable holder, ModuleComponent image, ModuleComponent coordinates, ImageOperands operands, Map<Integer, long[]> operandMap) {
        generator.getCode().visitImageSampleImplicitLod(generator.getTypeID(holder.getType()),
                generator.getComponentID(holder),
                generator.getComponentID(image),
                generator.getComponentID(coordinates),
                operands,
                operandMap);
        return this;
    }

}
