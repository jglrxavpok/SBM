package org.jglr.sbm.visitors;

import java.io.IOException;

public interface SBMVisitor {

    SBMHeaderVisitor visitHeader() throws IOException;

    SBMCodeVisitor visitCode() throws IOException;
}
