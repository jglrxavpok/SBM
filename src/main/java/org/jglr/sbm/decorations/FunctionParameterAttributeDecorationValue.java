package org.jglr.sbm.decorations;

import org.jglr.sbm.FunctionParameterAttribute;

public class FunctionParameterAttributeDecorationValue extends DecorationValue {
    private final FunctionParameterAttribute attribute;

    public FunctionParameterAttributeDecorationValue(FunctionParameterAttribute attribute) {
        super(Decoration.FuncParamAttr);
        this.attribute = attribute;
    }

    public FunctionParameterAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return super.toString()+" "+attribute.name();
    }
}
