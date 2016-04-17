package org.jglr.sbm.instructions;

import org.jglr.sbm.AddressingModel;
import org.jglr.sbm.MemoryModel;

public class MemoryModelInstruction extends SpvInstruction {
    private final AddressingModel addressingModel;
    private final MemoryModel memoryModel;

    public MemoryModelInstruction(AddressingModel addressingModel, MemoryModel memoryModel) {
        super(MemoryModel, 3);
        this.addressingModel = addressingModel;
        this.memoryModel = memoryModel;
    }

    public AddressingModel getAddressingModel() {
        return addressingModel;
    }

    public MemoryModel getMemoryModel() {
        return memoryModel;
    }

    @Override
    public String toString() {
        return "MemoryModel "+addressingModel.name()+" "+memoryModel.name();
    }
}
