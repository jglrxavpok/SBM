package org.jglr.sbm.instructions;

import org.jglr.sbm.Capability;

public class CapabilityInstruction extends SpvInstruction {
    private final Capability cap;

    public CapabilityInstruction(Capability cap) {
        super(OpCapability, 2);
        this.cap = cap;
    }

    public Capability getCapability() {
        return cap;
    }

    @Override
    public String toString() {
        return "OpCapability: "+cap.name();
    }
}
