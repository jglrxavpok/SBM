package org.jglr.sbm.utils;

import org.jglr.flows.io.ByteArray;
import org.jglr.sbm.visitors.CodeVisitor;
import org.jglr.sbm.visitors.ModuleWriter;

import java.io.IOException;
import java.nio.ByteOrder;

/**
 * Module writer using {@link StructuredCodeWriter} as its code writer
 */
public class StructuredModuleWriter extends ModuleWriter {

    public StructuredModuleWriter() {
        super();
        codeWriter = new StructuredCodeWriter();
    }
}
