package org.jglr.sbm.visitors;

import org.jglr.sbm.AddressingModel;
import org.jglr.sbm.MemoryAccess;
import org.jglr.sbm.MemoryModel;
import org.jglr.sbm.StorageClass;

public interface MemoryVisitor {

    /**
     * Copy from the memory pointed to by Source to the memory pointed to by Target. Both operands must be non-void pointers of the same type. Matching Storage Class is not required. The amount of memory copied is the size of the type pointed to.<br/>
     <br/>
     Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
     */
    void visitCopyMemory(long targetID, long sourceID, MemoryAccess access);

    /**
     * Copy from the memory pointed to by Source to the memory pointed to by Target. <br/>
     <br/>
     Size is the number of bytes to copy. It must have a scalar integer type. If it is a constant instruction, the constant value cannot be 0. It is invalid for both the constant’s type to have Signedness of 1 and to have the sign bit set. Otherwise, as a run-time value, Size is treated as unsigned, and if its value is 0, no memory access will be made.<br/>
     <br/>
     Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
     */
    void visitCopyMemorySized(long targetID, long sourceID, long size, MemoryAccess access);

    /**
     * Load through a pointer.<br/>
     <br/>
     Result Type is the type of the loaded object.<br/>
     <br/>
     Pointer is the pointer to load through. Its type must be an OpTypePointer whose Type operand is the same as Result Type.<br/>
     <br/>
     Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
     */
    void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess);

    /**
     * Store through a pointer.<br/>
     <br/>
     Pointer is the pointer to store through. Its type must be an OpTypePointer whose Type operand is the same as the type of Object.<br/>
     <br/>
     Object is the object to store.<br/>
     <br/>
     Memory Access must be a Memory Access literal. If not present, it is the same as specifying None.
     */
    void visitStore(long pointer, long object, MemoryAccess memoryAccess);

    /**
     * Set addressing model and memory model for the entire module.<br/>
     <br/>
     Addressing Model selects the module’s Addressing Model.<br/>
     <br/>
     Memory Model selects the module’s memory model, see Memory Model.
     */
    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);

    /**
     * Create a pointer into a composite object that can be used with OpLoad and OpStore. <br/>
     <br/>
     Result Type must be an OpTypePointer. Its Type operand must be the type reached by walking the Base’s type hierarchy down to the last provided index in Indexes, and its Storage Class operand must be the same as the Storage Class of Base.<br/>
     <br/>
     Base must be a pointer, pointing to the base of a composite object.<br/>
     <br/>
     Indexes walk the type hierarchy to the desired depth, potentially down to scalar granularity. The first index in Indexes will select the top-level member/element/component/element of the base composite. All composite constituents use zero-based numbering, as described by their OpType… instruction. The second index will apply similarly to that result, and so on. Once any non-composite type is reached, there must be no remaining (unused) indexes. Each of the Indexes must:<br/>
     - be a scalar integer type,<br/>
     - be an OpConstant when indexing into a structure.
     */
    void visitAccessChain(long resultType, long resultID, long base, long[] indexes);

    /**
     * Allocate an object in memory, resulting in a pointer to it, which can be used with OpLoad and OpStore.<br/>
     <br/>
     Result Type must be an OpTypePointer. Its Type operand is the type of object in memory.<br/>
     <br/>
     Storage Class is the Storage Class of the memory holding the object. It cannot be Generic.<br/>
     <br/>
     Initializer is optional. If Initializer is present, it will be the initial value of the variable’s memory content. Initializer must be an <id> from a constant instruction or a global (module scope) OpVariable instruction. Initializer must have the same type as the type pointed to by Result Type.
     */
    void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer);

}
