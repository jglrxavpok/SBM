import org.jglr.sbm.*
import org.jglr.sbm.decorations.FastMathDecorationValue
import org.jglr.sbm.instructions.ResultInstruction
import org.jglr.sbm.types.FloatType
import org.jglr.sbm.types.FunctionType
import org.jglr.sbm.types.IntType
import org.jglr.sbm.types.PointerType
import org.jglr.sbm.utils.Label
import org.jglr.sbm.utils.ModuleFunction
import org.jglr.sbm.utils.ModuleGenerator
import org.jglr.sbm.utils.ModuleVariable
import org.jglr.sbm.visitors.*
import org.junit.Test
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteOrder

class TestUseGenCodeWriter {

    @Test
    fun testWriteAndRead(): Unit {
        val codeWriter0 = CodeWriter()
        val writer0 = object : ModuleWriter() {
            override fun visitCode(): CodeVisitor = codeWriter0
            override fun toBytes(): ByteArray {
                val resultBuffer = org.jglr.flows.io.ByteArray()
                resultBuffer.byteOrder = ByteOrder.BIG_ENDIAN
                resultBuffer.putUnsignedInt(0x07230203)
                resultBuffer.putArray(headerWriter.toBytes())
                resultBuffer.putArray(codeWriter0.toBytes())
                return resultBuffer.backingArray()
            }
        }
        val generator = ModuleGenerator(writer0)
        val objectType = FloatType(32)
        val mainFunction = ModuleFunction("main", FunctionType(objectType, IntType(32, false)))
        generator.addEntryPoint(mainFunction, ExecutionModel.Fragment, arrayOfNulls<ModuleVariable>(0))
        generator.setExecutionMode(mainFunction, ExecutionMode(ExecutionMode.Type.OriginLowerLeft))
        val startLabel = Label()
        val functionGenerator = generator.createFunction(mainFunction).label(startLabel)
        val `object` = ModuleVariable("object", objectType)
        `object`.addDecoration(FastMathDecorationValue(FPFastMathMode((FPFastMathMode.FLAG_NOT_INF or FPFastMathMode.FLAG_NOT_NAN).toLong())))
        val pointer = ModuleVariable("pointer", PointerType(StorageClass.Function, objectType))
        val resultHolder = ModuleVariable("result", objectType)

        functionGenerator.store(`object`, pointer)
        functionGenerator.load(resultHolder, pointer)
        functionGenerator.kill()
        functionGenerator.returnValue(`object`)
        functionGenerator.end()


        val reader = ModuleReader(generator.toBytes())
        reader.visitHeader()
        val codeCollector = reader.visitCode() as CodeCollector
        println("== START ==")
        codeCollector.instructions.forEach { i ->
            if (i is ResultInstruction)
                print("%" + i.resultID + " = ")
            println(i.toString())
        }
        println("== END ==")
    }
}