import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.visualizer.icons.DataTypeEnum;
import io.kaitai.struct.visualizer.icons.LayeredSvgIcon;
import io.kaitai.struct.visualizer.icons.ScalarOrListEnum;
import io.kaitai.struct.visualizer.icons.SequentialOrInstanceEnum;
import ru.mingun.kaitai.struct.tree.ChunkNode;
import ru.mingun.kaitai.struct.tree.StructNode;

import javax.swing.tree.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ru.mingun.kaitai.struct.tree.KaitaiStructTreeModel;

public abstract class AbstractTest {

    static StructNode rootNode;
    static Map<String, ChunkNode> nodeByName;


    @SuppressWarnings("SameParameterValue")
    static void init(Class<?> kaitaiStructClass, String resourceNameOfBinaryFileToParse) throws ReflectiveOperationException, IOException, URISyntaxException {

        final URL resourceUrl = kaitaiStructClass.getResource(resourceNameOfBinaryFileToParse);
        if (resourceUrl == null) {
            throw new FileNotFoundException(resourceNameOfBinaryFileToParse);
        }

        // required insanity to pass a String into the fromFile() method
        final String passToKaitaiStructConstructor = Paths.get(resourceUrl.toURI()).toString();

        final Method fromFileMethod = kaitaiStructClass.getMethod("fromFile", String.class);
        final Object invoked = fromFileMethod.invoke(null, passToKaitaiStructConstructor);
        final KaitaiStruct kaitaiStructInstance = (KaitaiStruct) invoked;
        rootNode = new KaitaiStructTreeModel(kaitaiStructInstance).getRoot();

        nodeByName = new HashMap<>();
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            final TreeNode treeNode = rootNode.getChildAt(i);
            final ChunkNode chunkNode = (ChunkNode) treeNode;
            nodeByName.put(chunkNode.getName(), chunkNode);
        }

    }

    void assertEnumsForNode(String nodeName, SequentialOrInstanceEnum sequentialOrInstance,
                            ScalarOrListEnum scalarOrList, DataTypeEnum dataType) {
        assertTrue(nodeByName.containsKey(nodeName), "The map of node names does not contain \"" + nodeName + "\"");
        final ChunkNode chunkNode = nodeByName.get(nodeName);

        assertEquals(nodeName, chunkNode.getName());

        final String message = "The node type " + chunkNode.getClass().getSimpleName() + ", the value is type " +
                (chunkNode.getValue() == null ? "null" : chunkNode.getValue().getClass().getSimpleName());

        final LayeredSvgIcon icon = new LayeredSvgIcon(chunkNode);
        assertEquals(sequentialOrInstance, icon.SEQUENTIAL_OR_INSTANCE, message);
        assertEquals(scalarOrList, icon.SCALAR_OR_LIST, message);
        assertEquals(dataType, icon.DATA_TYPE, message);
    }

}
