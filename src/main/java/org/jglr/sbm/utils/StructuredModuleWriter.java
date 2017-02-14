package org.jglr.sbm.utils;

import org.jglr.sbm.visitors.ModuleWriter;

/**
 * Module writer using {@link StructuredCodeWriter} as its code writer
 */
public class StructuredModuleWriter extends ModuleWriter {

    public StructuredModuleWriter() {
        super();
        codeWriter = new StructuredCodeWriter();
    }
}
