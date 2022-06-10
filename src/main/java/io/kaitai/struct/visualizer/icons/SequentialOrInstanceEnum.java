package io.kaitai.struct.visualizer.icons;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.animation.AnimationElement;
import ru.mingun.kaitai.struct.tree.ChunkNode;

import java.awt.Color;
import java.net.URL;

enum SequentialOrInstanceEnum {
    SEQUENTIAL("sequential"),
    INSTANCE("instance");

    private final URL RESOURCE_URL;
    SVGDiagram svgDiagram;
    SVGElement baseShape;

    SequentialOrInstanceEnum(String svgFilenamePart) {
        RESOURCE_URL = getClass().getResource("/layered-icons/base-" + svgFilenamePart + ".svg");
        reloadSvg();
    }

    void setColors(DataTypeEnum dataType) throws SVGElementException {
        baseShape.setAttribute("fill", AnimationElement.AT_CSS, colorToHex(dataType.FILL));
        baseShape.setAttribute("stroke", AnimationElement.AT_CSS, colorToHex(dataType.STROKE));
    }

    private static String colorToHex(Color color) {
        // https://stackoverflow.com/a/66919552/7376577
        return String.format("#%06x", color.getRGB() & 0xFF_FF_FF); //remove alpha channel using bit mask
    }

    void reloadSvg() {
        svgDiagram = SVGCache.getSVGUniverse().getDiagram(
                SVGCache.getSVGUniverse().loadSVG(RESOURCE_URL, true)
        );
        baseShape = svgDiagram.getElement("base-shape");
    }

    static SequentialOrInstanceEnum fromChunkNode(ChunkNode chunkNode) {
        return chunkNode.isSequential() ? SEQUENTIAL : INSTANCE;
    }
}
