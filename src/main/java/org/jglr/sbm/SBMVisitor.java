package org.jglr.sbm;

import java.io.IOException;

public interface SBMVisitor {

    SBMHeaderVisitor visitHeader() throws IOException;

    SBMCodeVisitor visitCode() throws IOException;
}
