package io.kaitai.struct.visualizer.icons;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import io.kaitai.struct.KaitaiStruct;
import ru.mingun.kaitai.struct.tree.ChunkNode;
import ru.mingun.kaitai.struct.tree.ListNode;
import ru.mingun.kaitai.struct.tree.SimpleNode;
import ru.mingun.kaitai.struct.tree.StructNode;

import java.awt.Color;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public enum DataTypeEnum {
    BOOLEAN(1, "boolean", Color.decode("#e76ade"), Color.decode("#9c3495")), //magenta
    BYTE_ARRAY(2, "byteArray", Color.decode("#6a71e7"), Color.decode("#4e53b2")), //blue
    INTEGER(3, "integer", Color.decode("#52cfcd"), Color.decode("#279594")), //cyan
    ENUM(4, "enum", Color.decode("#5fcd43"), Color.decode("#39a71c")), //green
    FLOAT(5, "float", Color.decode("#ede72c"), Color.decode("#bbb620")), //yellow
    STRING(6, "string", Color.decode("#e38733"), Color.decode("#c06614")), //orange
    STRUCT(7, "struct", Color.decode("#d44747"), Color.decode("#c42222")), //red
    UNKNOWN(8, "unknown", Color.decode("#cccccc"), Color.decode("#999999")); // gray

    private final URL RESOURCE_URL;
    final int GRID_X;
    final String DISPLAY_NAME;
    final Color FILL;
    final Color STROKE;
    SVGDiagram svgDiagram;

    DataTypeEnum(int gridx, String svgFilenamePart, Color fill, Color stroke) {
        GRID_X = gridx;
        DISPLAY_NAME = svgFilenamePart;
        FILL = fill;
        STROKE = stroke;
        RESOURCE_URL = getClass().getResource("/layered-icons/dataType-" + svgFilenamePart + ".svg");
        reloadSvg();
    }

    void reloadSvg() {
        svgDiagram = SVGCache.getSVGUniverse().getDiagram(
                SVGCache.getSVGUniverse().loadSVG(RESOURCE_URL, true)
        );
    }

    static DataTypeEnum fromChunkNode(ChunkNode chunkNode) {
        if (chunkNode instanceof StructNode) {
            return STRUCT;

        } else if (chunkNode instanceof ListNode) {
            ListNode listNode = (ListNode) chunkNode;
            return fromClass(listNode.getElementClass());

        } else if (chunkNode instanceof SimpleNode) {
            final SimpleNode simpleNode = (SimpleNode) chunkNode;
            /*
            To choose an icon, we need to know what class is returned by
            simpleNode.getValue(). That method returns an Object. We can call
            object.getClass(), but if the value is null then getClass() will fail.

            There is also a method simpleNode.getValueClass(), which returns the
            compile-time class of the value. By 'compile-time class' I mean the
            class which is written in the .java source code file. The getValueClass()
            method lets us tell what type the value is even if the value is null.

            But, it is possible for the compile-time class to be java.lang.Object. It
            can happen if, in the KSY file, a type uses switch-on and there is no
            tighter bound than Object. Example KSY:
                meta:
                  id: object_test
                seq:
                  - id: switch_on_this
                    type: u1
                  - id: type_can_change
                    type:
                      switch-on: switch_on_this
                      cases:
                        1: b1
                        2: u1
            The compile-time class of 'type_can_change' will be Object. At runtime, the class will
            be 'my_type' or int (or null if the none of the switch cases matched).

            So, if the compile-time type is Object, we will try getting the runtime class.
            */
            final Class<?> classToGetIconFor;
            if (simpleNode.getValueClass() == Object.class) {
                if (simpleNode.getValue() == null) {
                    return UNKNOWN;
                } else {
                    // use runtime class
                    classToGetIconFor = simpleNode.getValue().getClass();
                }
            } else {
                // use compile-time class
                classToGetIconFor = simpleNode.getValueClass();
            }
            return fromClass(classToGetIconFor);

        } else {
            return UNKNOWN;
        }
    }

    private final static Map<Class<?>, DataTypeEnum> CLASS_TO_DATA_TYPE = new HashMap<>();

    static {
        addClassesToDataTypeMap(DataTypeEnum.BOOLEAN, boolean.class, Boolean.class);
        addClassesToDataTypeMap(DataTypeEnum.FLOAT, double.class, Double.class, float.class, Float.class);
        addClassesToDataTypeMap(DataTypeEnum.INTEGER, byte.class, Byte.class, short.class, Short.class, int.class, Integer.class, long.class, Long.class);
        addClassesToDataTypeMap(DataTypeEnum.STRING, String.class);
        addClassesToDataTypeMap(DataTypeEnum.BYTE_ARRAY, byte[].class);
    }

    private static void addClassesToDataTypeMap(DataTypeEnum icon, Class<?>... classes) {
        for (Class<?> aClass : classes) {
            CLASS_TO_DATA_TYPE.put(aClass, icon);
        }
    }

    private static DataTypeEnum fromClass(Class<?> clazz) {
        if (clazz.isEnum()) {
            return ENUM;
        } else if (KaitaiStruct.class.isAssignableFrom(clazz)) {
            // happens when getting icon for a ListNode of KaitaiStructs
            return STRUCT;
        } else {
            return CLASS_TO_DATA_TYPE.getOrDefault(clazz, UNKNOWN);
        }
    }


}
