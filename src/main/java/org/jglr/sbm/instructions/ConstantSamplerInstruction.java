package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.sampler.SamplerAddressingMode;
import org.jglr.sbm.sampler.SamplerFilterMode;
import org.jglr.sbm.types.Type;

public class ConstantSamplerInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final SamplerAddressingMode mode;
    private final boolean normalized;
    private final SamplerFilterMode filter;
    private String name;
    private Type resultType;

    public ConstantSamplerInstruction(long resultTypeID, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter) {
        super(OpConstantSampler, 6, resultID);
        this.resultTypeID = resultTypeID;
        this.mode = mode;
        this.normalized = normalized;
        this.filter = filter;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "OpConstantSampler "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType)+" ("+mode.name()+filter.name()+", n:"+normalized+")";
    }
}
