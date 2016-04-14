package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;

/**
 * Represents an instruction that needs to be updated at the end of a code visit, eg for selecting type, strings, names...
 */
public interface ResolvableInstruction {

    void onVisitEnd(ConstantPool constantPool);
}
