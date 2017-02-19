import org.jglr.sbm.generation.SPIRVKt;
import org.jglr.sbm.generation.SPIRVOpcodes;
import org.junit.Test;

import java.io.File;

public class TestGeneration {

    @Test
    public void generateTest() {
        SPIRVOpcodes.INSTANCE.generate(new File("./src/main/gen/Opcodes.txt"));
    }
}
