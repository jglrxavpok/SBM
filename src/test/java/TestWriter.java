import org.jglr.sbm.*;
import org.jglr.sbm.decorations.FastMathDecorationValue;
import org.jglr.sbm.instructions.ResultInstruction;
import org.jglr.sbm.types.*;
import org.jglr.sbm.utils.FunctionGenerator;
import org.jglr.sbm.utils.ModuleFunction;
import org.jglr.sbm.utils.ModuleGenerator;
import org.jglr.sbm.utils.ModuleVariable;
import org.jglr.sbm.visitors.*;
import org.junit.Test;

import java.io.File;
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

    @Test
    public void generator() throws IOException {
        ModuleGenerator generator = new ModuleGenerator()
                .setGeneratorMagicNumber(42)
                .setSourceInfos(SourceLanguage.GLSL, 400, "Test.txt", "c = a+b;")
                .addCapability(Capability.Shader).addCapability(Capability.Kernel)
                .addSetImport("GLSL.std.450")
                .addSourceExtension("GL_ARB_separate_shader_objects")
                .addSourceExtension("GL_ARB_shading_language_420pack")
                .setMemoryModel(AddressingModel.Logical, MemoryModel.GLSL450);

        ModuleFunction mainFunction = new ModuleFunction("main", new FunctionType(Type.VOID, new IntType(32, false)));
        generator.addEntryPoint(mainFunction, ExecutionModel.Fragment, new ModuleVariable[0]);
        generator.setExecutionMode(mainFunction, new ExecutionMode(ExecutionMode.Type.OriginLowerLeft) {
            @Override
            public int getOperandCount() {
                return 0;
            }
        });
        FunctionGenerator functionGenerator = generator.createFunction(mainFunction);
            Type objectType = new FloatType(32);
            ModuleVariable object = new ModuleVariable("object", objectType);
            object.addDecoration(new FastMathDecorationValue(new FPFastMathMode(FPFastMathMode.FLAG_NOT_INF | FPFastMathMode.FLAG_NOT_NAN)));
            ModuleVariable pointer = new ModuleVariable("pointer", new PointerType(StorageClass.Function, objectType));
            functionGenerator.store(object, pointer);
            ModuleVariable resultHolder = new ModuleVariable("result", objectType);
            functionGenerator.load(resultHolder, pointer);
        functionGenerator.end();

        FileOutputStream out = new FileOutputStream(new File(".", "shaderGenerated.frag.spv"));
        out.write(generator.toBytes());
        out.flush();
        out.close();

        ModuleReader reader = new ModuleReader(generator.toBytes());
        reader.visitHeader();
        CodeCollector codeCollector = (CodeCollector) reader.visitCode();
        codeCollector.getInstructions().forEach(i -> {
            if(i instanceof ResultInstruction)
                System.out.print("%"+((ResultInstruction)i).getResultID()+" = ");
            System.out.println(i.toString());
        });
    }
}
