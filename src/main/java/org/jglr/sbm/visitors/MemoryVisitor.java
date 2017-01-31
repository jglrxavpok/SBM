package org.jglr.sbm.visitors;

import org.jglr.sbm.AddressingModel;
import org.jglr.sbm.MemoryAccess;
import org.jglr.sbm.MemoryModel;

public interface MemoryVisitor {

    void visitCopyMemory(long targetID, long sourceID, MemoryAccess access);

    void visitCopyMemorySized(long targetID, long sourceID, long size, MemoryAccess access);

    void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess);

    void visitStore(long pointer, long object, MemoryAccess memoryAccess);

    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);

}
