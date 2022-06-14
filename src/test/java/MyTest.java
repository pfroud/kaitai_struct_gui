import io.kaitai.struct.visualizer.icons.DataTypeEnum;
import io.kaitai.struct.visualizer.icons.LayeredSvgIcon;
import io.kaitai.struct.visualizer.icons.ScalarOrListEnum;
import io.kaitai.struct.visualizer.icons.SequentialOrInstanceEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mingun.kaitai.struct.tree.StructModel;
import ru.mingun.kaitai.struct.tree.StructNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class MyTest {

    private static StructModel structModel;

    @BeforeAll
    static void initAll() throws IOException, ReflectiveOperationException, URISyntaxException {
        final String resourceName = "/pnggrad8rgb.png";
        final URL resourceUrl = MyTest.class.getResource(resourceName);
        if (resourceUrl == null) {
            throw new FileNotFoundException(resourceName);
        }

        // required!
        final String passToKaitaiStructConstructor = Paths.get(resourceUrl.toURI()).toString();

        Png png = Png.fromFile(passToKaitaiStructConstructor);
        png._read();
        structModel = new StructModel(png);


    }

    @Test
    void hello() {
        StructNode rootNode = structModel.getRoot();
        LayeredSvgIcon icon = new LayeredSvgIcon(rootNode);

        org.junit.jupiter.api.Assertions.assertEquals(icon.DATA_TYPE, DataTypeEnum.STRUCT);
        org.junit.jupiter.api.Assertions.assertEquals(icon.SCALAR_OR_LIST, ScalarOrListEnum.SCALAR);
        org.junit.jupiter.api.Assertions.assertEquals(icon.SEQUENTIAL_OR_INSTANCE, SequentialOrInstanceEnum.SEQUENTIAL);


    }

}
