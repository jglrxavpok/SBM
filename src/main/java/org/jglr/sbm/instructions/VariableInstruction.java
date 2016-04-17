package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.decorations.DecorationValue;
import org.jglr.sbm.types.Type;

import java.util.Arrays;
import java.util.List;

public class VariableInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final StorageClass storageClass;
    private final long initializer;
    private Type resultType;
    private String name;
    private List<DecorationValue> decorations;

    public VariableInstruction(long resultTypeID, long resultID, StorageClass storageClass, long initializer) {
        super(VARIABLE, 4 + (initializer == -1 ? 0 : 1), resultID);
        this.resultTypeID = resultTypeID;
        this.storageClass = storageClass;
        this.initializer = initializer;
    }

    public long getInitializer() {
        return initializer;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public Type getResultType() {
        return resultType;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        resultType = constantPool.getType(resultTypeID);
        name = constantPool.getName(getResultID());
        decorations = constantPool.getDecorations(getResultID());
    }

    @Override
    public String toString() {
        return "VariableDeclaration "+nameOrID(getResultID(), name)+" "+nameOrID(resultTypeID, resultType) + " " + Arrays.toString(decorations.toArray());
    }
}
