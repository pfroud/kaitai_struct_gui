import compiled_from_ksy.Basic;
import io.kaitai.struct.visualizer.icons.DataTypeEnum;
import io.kaitai.struct.visualizer.icons.ScalarOrListEnum;
import io.kaitai.struct.visualizer.icons.SequentialOrInstanceEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class BasicTest extends AbstractTest {

    @BeforeAll
    static void initAll() throws IOException, ReflectiveOperationException, URISyntaxException {
        init(Basic.class, "/bin/ascii.txt");
    }

    //<editor-fold desc="Sequential fields">
    @Test
    void sequentialBytesScalar() {
        assertEnumsForNode("sequentialBytesScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void sequentialBytesList() {
        assertEnumsForNode("sequentialBytesList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void sequentialIntegerScalar() {
        assertEnumsForNode("sequentialIntegerScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void sequentialIntegerList() {
        assertEnumsForNode("sequentialIntegerList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.INTEGER);
    }

    @Test
    void sequentialFloatScalar() {
        assertEnumsForNode("sequentialFloatScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.FLOAT);
    }

    @Test
    void sequentialFloatList() {
        assertEnumsForNode("sequentialFloatList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.FLOAT);
    }

    @Test
    void sequentialEnumScalar() {
        assertEnumsForNode("sequentialEnumScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.ENUM);
    }

    @Test
    void sequentialEnumList() {
        assertEnumsForNode("sequentialEnumList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.ENUM);
    }

    @Test
    void sequentialBooleanScalar() {
        assertEnumsForNode("sequentialBooleanScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.BOOLEAN);
    }

    @Test
    void sequentialBooleanList() {
        assertEnumsForNode("sequentialBooleanList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.BOOLEAN);
    }

    @Test
    void sequentialStringScalar() {
        assertEnumsForNode("sequentialStringScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.STRING);
    }

    @Test
    void sequentialStringList() {
        assertEnumsForNode("sequentialStringList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.STRING);
    }

    @Test
    void sequentialStructScalar() {
        assertEnumsForNode("sequentialStructScalar", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.STRUCT);
    }

    @Test
    void sequentialStructList() {
        assertEnumsForNode("sequentialStructList", SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.STRUCT);
    }
    //</editor-fold>

    //<editor-fold desc="Chunk instances">
    @Test
    void chunkInstanceBytesScalar() {
        assertEnumsForNode("chunkInstanceBytesScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void chunkInstanceBytesList() {
        assertEnumsForNode("chunkInstanceBytesList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void chunkInstanceIntegerScalar() {
        assertEnumsForNode("chunkInstanceIntegerScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void chunkInstanceIntegerList() {
        assertEnumsForNode("chunkInstanceIntegerList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.INTEGER);
    }

    @Test
    void chunkInstanceFloatScalar() {
        assertEnumsForNode("chunkInstanceFloatScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.FLOAT);
    }

    @Test
    void chunkInstanceFloatList() {
        assertEnumsForNode("chunkInstanceFloatList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.FLOAT);
    }

    @Test
    void chunkInstanceEnumScalar() {
        assertEnumsForNode("chunkInstanceEnumScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.ENUM);
    }

    @Test
    void chunkInstanceEnumList() {
        assertEnumsForNode("chunkInstanceEnumList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.ENUM);
    }

    @Test
    void chunkInstanceBooleanScalar() {
        assertEnumsForNode("chunkInstanceBooleanScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.BOOLEAN);
    }

    @Test
    void chunkInstanceBooleanList() {
        assertEnumsForNode("chunkInstanceBooleanList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.BOOLEAN);
    }

    @Test
    void chunkInstanceStringScalar() {
        assertEnumsForNode("chunkInstanceStringScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.STRING);
    }

    @Test
    void chunkInstanceStringList() {
        assertEnumsForNode("chunkInstanceStringList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.STRING);
    }

    @Test
    void chunkInstanceStructScalar() {
        assertEnumsForNode("chunkInstanceStructScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.STRUCT);
    }

    @Test
    void chunkInstanceStructList() {
        assertEnumsForNode("chunkInstanceStructList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.STRUCT);
    }
    //</editor-fold>

    //<editor-fold desc="Value instances">
    @Test
    void valueInstanceBytesScalar() {
        assertEnumsForNode("valueInstanceBytesScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void valueInstanceBytesList() {
        assertEnumsForNode("valueInstanceBytesList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.BYTE_ARRAY);
    }

    @Test
    void valueInstanceIntegerScalar() {
        assertEnumsForNode("valueInstanceIntegerScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void valueInstanceIntegerList() {
        assertEnumsForNode("valueInstanceIntegerList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.INTEGER);
    }

    @Test
    void valueInstanceFloatScalar() {
        assertEnumsForNode("valueInstanceFloatScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.FLOAT);
    }

    @Test
    void valueInstanceFloatList() {
        assertEnumsForNode("valueInstanceFloatList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.FLOAT);
    }

    @Test
    void valueInstanceEnumScalar() {
        assertEnumsForNode("valueInstanceEnumScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.ENUM);
    }

    @Test
    void valueInstanceEnumList() {
        assertEnumsForNode("valueInstanceEnumList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.ENUM);
    }

    @Test
    void valueInstanceBooleanScalar() {
        assertEnumsForNode("valueInstanceBooleanScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.BOOLEAN);
    }

    @Test
    void valueInstanceBooleanList() {
        assertEnumsForNode("valueInstanceBooleanList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.BOOLEAN);
    }

    @Test
    void valueInstanceStringScalar() {
        assertEnumsForNode("valueInstanceStringScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.STRING);
    }

    @Test
    void valueInstanceStringList() {
        assertEnumsForNode("valueInstanceStringList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.STRING);
    }

    @Test
    void valueInstanceStructScalar() {
        assertEnumsForNode("valueInstanceStructScalar", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.STRUCT);
    }

    @Test
    void valueInstanceStructList() {
        assertEnumsForNode("valueInstanceStructList", SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.STRUCT);
    }

    //</editor-fold>


}
