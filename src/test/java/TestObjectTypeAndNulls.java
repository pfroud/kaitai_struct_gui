import compiled_from_ksy.ObjectAndNullTests;
import io.kaitai.struct.visualizer.icons.DataTypeEnum;
import io.kaitai.struct.visualizer.icons.ScalarOrListEnum;
import io.kaitai.struct.visualizer.icons.SequentialOrInstanceEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

@DisplayName("Test behavior when types are java.lang.Object and/or values are null")
public class TestObjectTypeAndNulls extends AbstractTest {


    @BeforeAll
    static void initAll() throws IOException, ReflectiveOperationException, URISyntaxException {
        init(ObjectAndNullTests.class, "/bin/ascii.txt");
    }


    @Test
    void sequentialField_CompileTimeTypeIsIntAndRuntimeValueIsNull_useCompileTimeType() {
        assertEnumsForNode(
                "sequentialFieldCompileTimeTypeIsIntAndRuntimeValueIsNull",
                SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger_useRuntimeType() {
        assertEnumsForNode(
                "sequentialFieldCompileTimeTypeIsObjectAndRuntimeTypeIsInteger",
                SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull_shouldBeUnknown() {
        assertEnumsForNode(
                "sequentialFieldCompileTimeTypeIsObjectAndRuntimeValueIsNull",
                SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.SCALAR, DataTypeEnum.UNKNOWN);
    }

    @Test
    void sequentialFieldListOfObjects() {
        assertEnumsForNode("sequentialFieldListOfObjects",
                SequentialOrInstanceEnum.SEQUENTIAL, ScalarOrListEnum.LIST, DataTypeEnum.UNKNOWN);
    }

    @Test
    void instanceCompileTimeTypeIsIntAndRuntimeValueIsNull_useCompileTimeType() {
        assertEnumsForNode(
                "instanceCompileTimeTypeIsIntAndRuntimeValueIsNull",
                SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat_useRuntimeType() {
        assertEnumsForNode(
                "instanceCompileTimeTypeIsObjectAndRuntimeTypeIsFloat",
                SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.INTEGER);
    }

    @Test
    void instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull_shouldBeUnknown() {
        assertEnumsForNode(
                "instanceCompileTimeTypeIsObjectAndRuntimeValueIsNull",
                SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.SCALAR, DataTypeEnum.UNKNOWN);
    }

    @Test
    void instanceListOfObjects() {
        assertEnumsForNode("instanceListOfObjects",
                SequentialOrInstanceEnum.INSTANCE, ScalarOrListEnum.LIST, DataTypeEnum.UNKNOWN);
    }


}
