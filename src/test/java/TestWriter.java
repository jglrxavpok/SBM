import org.jglr.sbm.SourceLanguage;
import org.jglr.sbm.visitors.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWriter {

    @Test
    public void testWrite() throws IOException {
        ModuleWriter writer = new ModuleWriter();

        // Set the visitors of the ModuleReader to the writer visitors in order to copy the contents from TestReading.fragShaderCode
        ModuleReader reader = new ModuleReader(TestReading.fragShaderCode) {
            @Override
            public HeaderVisitor newHeaderVisitor() throws IOException {
                return writer.visitHeader();
            }

            @Override
            public CodeVisitor newCodeVisitor() throws IOException {
                return writer.visitCode();
            }
        };
        reader.visitHeader();
        reader.visitCode();

        FileOutputStream out = new FileOutputStream(new File(".", "shaderWritten.frag.spv"));
        out.write(writer.toBytes());
        out.flush();
        out.close();

        reader = new ModuleReader(writer.toBytes());
        reader.visitHeader();
        CodeCollector codeCollector = (CodeCollector) reader.visitCode();
        codeCollector.getInstructions().forEach(System.out::println);
    }
}
