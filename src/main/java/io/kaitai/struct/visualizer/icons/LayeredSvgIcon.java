package io.kaitai.struct.visualizer.icons;

import com.kitfox.svg.SVGException;
import ru.mingun.kaitai.struct.tree.ChunkNode;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class LayeredSvgIcon implements Icon {

    private static final int ICON_SIZE = 16;

    public final DataTypeEnum DATA_TYPE;
    public final SequentialOrInstanceEnum SEQUENTIAL_OR_INSTANCE;
    public final ScalarOrListEnum SCALAR_OR_LIST;

    public LayeredSvgIcon(ChunkNode chunkNode) {
        this(
                DataTypeEnum.fromChunkNode(chunkNode),
                SequentialOrInstanceEnum.fromChunkNode(chunkNode),
                ScalarOrListEnum.fromChunkNode(chunkNode)
        );
    }

    LayeredSvgIcon(DataTypeEnum dataType, SequentialOrInstanceEnum sequentialOrInstance, ScalarOrListEnum scalarOrList) {
        DATA_TYPE = dataType;
        SEQUENTIAL_OR_INSTANCE = sequentialOrInstance;
        SCALAR_OR_LIST = scalarOrList;
    }

    @Override
    public int getIconHeight() {
        return ICON_SIZE;
    }

    @Override
    public int getIconWidth() {
        return ICON_SIZE;
    }

    @Override
    public void paintIcon(Component c, Graphics g1, int x, int y) {
        final Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g.translate(x, y);

        try {
            SEQUENTIAL_OR_INSTANCE.setColors(DATA_TYPE);
            SEQUENTIAL_OR_INSTANCE.svgDiagram.render(g);
            DATA_TYPE.svgDiagram.render(g);
            if (SCALAR_OR_LIST.svgDiagram != null) {
                SCALAR_OR_LIST.svgDiagram.render(g);
            }
            g.translate(-x, -y);
        } catch (SVGException ex) {
            throw new RuntimeException(ex);
        }
    }

}
