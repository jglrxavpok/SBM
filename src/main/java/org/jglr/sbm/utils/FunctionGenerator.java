package org.jglr.sbm.utils;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.*;
import org.jglr.sbm.types.PointerType;
import org.jglr.sbm.types.Type;

public class FunctionGenerator {

    private final ModuleGenerator generator;
    private final ModuleFunction function;
    private long id;

    public FunctionGenerator(ModuleGenerator generator, ModuleFunction function) {
        this.generator = generator;
        this.function = function;
    }

    public void end() {
        generator.code.visitFunctionEnd();
    }

    void init(long id) {
        if(function.getName() != null) {
            generator.code.visitName(id, function.getName());
        }
        generator.code.visitFunction(generator.getTypeID(function.getReturnType()), id, function.getControl(), generator.getTypeID(function.getFunctionType()));
    }

    public FunctionGenerator load(ModuleVariable resultHolder, ModuleVariable pointerVariable) {
        return load(resultHolder, pointerVariable,  null);
    }

    public FunctionGenerator load(ModuleVariable resultHolder, ModuleVariable pointerVariable, MemoryAccess access) {
        checkType(pointerVariable, PointerType.class);
        checkCorrectPointerType(pointerVariable, resultHolder);
        registerType(resultHolder.getType());
        registerType(pointerVariable.getType());
        registerVariable(resultHolder);
        registerVariable(pointerVariable);
        generator.code.visitLoad(generator.getTypeID(resultHolder.getType()), generator.getComponentID(resultHolder), generator.getComponentID(pointerVariable), access);
        return this;
    }

    public FunctionGenerator store(ModuleVariable toStore, ModuleVariable pointerVariable) {
        return store(toStore, pointerVariable, null);
    }

    public FunctionGenerator store(ModuleVariable toStore, ModuleVariable pointerVariable, MemoryAccess access) {
        checkType(pointerVariable, PointerType.class);
        checkCorrectPointerType(pointerVariable, toStore);
        registerType(toStore.getType());
        registerType(pointerVariable.getType());
        registerVariable(toStore);
        registerVariable(pointerVariable);
        generator.code.visitStore(generator.getComponentID(pointerVariable), generator.getComponentID(toStore), access);
        return this;
    }

    private void registerVariable(ModuleVariable var) {
        if(!generator.hasComponentID(var)) {
            registerType(var.getType());
            if(var.getName() != null)
                generator.code.visitName(generator.getComponentID(var), var.getName());
            for (DecorationValue decorationValue : var.getDecorations()) {
                visitDecoration(generator.getComponentID(var), decorationValue);
            }
            generator.code.visitVariable(generator.getTypeID(var.getType()), generator.getComponentID(var), var.getStorageClass(), -1);
        }
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
                generator.code.visitIntDecoration(decoration.getType(), id, ((IntDecorationValue) decoration).getValue());
                break;

            case FuncParamAttr:
                generator.code.visitFunctionParameterAttributeDecoration(id, ((FunctionParameterAttributeDecorationValue) decoration).getAttribute());
                break;

            case FPRoundingMode:
                generator.code.visitFPRoundingModeDecoration(id, ((RoundingModeDecorationValue) decoration).getRoundingMode());
                break;

            case FPFastMathMode:
                generator.code.visitFPFastMathModeDecoration(id, ((FastMathDecorationValue) decoration).getFastMathMode());
                break;

            case LinkageAttributes:
                LinkageDecorationValue linkageDecoration = (LinkageDecorationValue) decoration;
                generator.code.visitLinkageAttributesDecoration(id, linkageDecoration.getName(), linkageDecoration.getLinkageType());
                break;

            default:
                generator.code.visitDecoration(id, decoration.getType());
                break;
        }
    }

    private void registerType(Type type) {
        generator.getTypeID(type); // forces generator to generate an ID if none exist and register it
    }

    private void checkCorrectPointerType(ModuleVariable pointerVariable, ModuleVariable object) {
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
}
