package io.kaitai.struct.visualizer.icons;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import ru.mingun.kaitai.struct.tree.ChunkNode;
import ru.mingun.kaitai.struct.tree.ListNode;

import java.net.URL;

public enum ScalarOrListEnum {

    SCALAR(null), // no icon for scalar
    LIST("list");

    private final URL RESOURCE_URL;
    SVGDiagram svgDiagram;

    ScalarOrListEnum(String svgFilenamePart) {
        if (svgFilenamePart == null) {
            svgDiagram = null;
            RESOURCE_URL = null;
        } else {
            RESOURCE_URL = getClass().getResource("/layered-icons/decorator-" + svgFilenamePart + ".svg");
            reloadSvg();
        }
    }

    void reloadSvg() {
        if (RESOURCE_URL != null) {
            svgDiagram = SVGCache.getSVGUniverse().getDiagram(
                    SVGCache.getSVGUniverse().loadSVG(RESOURCE_URL, true)
            );
        }
    }

    static ScalarOrListEnum fromChunkNode(ChunkNode chunkNode) {
        return (chunkNode instanceof ListNode) ? LIST : SCALAR;
    }
}
