import org.jglr.sbm.generation.SPIRVOpcodesGenerator;
import org.junit.Test;

import java.io.File;

public class TestGeneration {

    @Test
    public void generateTest() {
        SPIRVOpcodesGenerator.INSTANCE.generate(new File("./src/main/gen/Opcodes.txt"));
    }
}
