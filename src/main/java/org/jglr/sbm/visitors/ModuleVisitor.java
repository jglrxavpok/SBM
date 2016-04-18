package org.jglr.sbm.visitors;

import java.io.IOException;

public interface ModuleVisitor {

    HeaderVisitor visitHeader() throws IOException;

    CodeVisitor visitCode() throws IOException;
}
