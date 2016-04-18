package org.jglr.sbm.visitors;

import jdk.internal.org.objectweb.asm.ByteVector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ModuleWriter implements ModuleVisitor {

    public ModuleWriter() {
        //codeBuffer = null;
        //headerWriter = new HeaderWriter();
    }

    @Override
    public HeaderVisitor visitHeader() throws IOException {

        return null;
    }

    @Override
    public CodeVisitor visitCode() throws IOException {
        return null;
    }
}
