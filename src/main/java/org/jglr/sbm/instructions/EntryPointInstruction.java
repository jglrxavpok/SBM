package org.jglr.sbm.instructions;

import org.jglr.sbm.ExecutionMode;
import org.jglr.sbm.InfoPool;
import org.jglr.sbm.ExecutionModel;

import java.util.Arrays;

public class EntryPointInstruction extends SpvInstruction implements ResolvableInstruction {
    private final ExecutionModel model;
    private final long entryPoint;
    private final String name;
    private final long[] interfaceIDs;
    private String[] interfaces;
    private ExecutionMode executionMode;

    public EntryPointInstruction(ExecutionModel model, long entryPoint, String name, long[] interfaceIDs) {
        super(EntryPoint, 3 + getWordCount(name) + interfaceIDs.length);
        this.model = model;
        this.entryPoint = entryPoint;
        this.name = name;
        this.interfaceIDs = interfaceIDs;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        interfaces = infoPool.getNames(interfaceIDs);
        executionMode = infoPool.getExecutionMode(entryPoint);
    }

    @Override
    public String toString() {
        return "EntryPoint "+model.name()+" "+entryPoint+" "+name+" "+(interfaces == null ? Arrays.toString(interfaceIDs) : Arrays.toString(interfaces))
                +(executionMode == null ? "" : " "+executionMode.toString());
    }
}
