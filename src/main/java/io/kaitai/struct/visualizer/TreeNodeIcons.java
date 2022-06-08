package io.kaitai.struct.visualizer;

import com.kitfox.svg.app.beans.SVGIcon;
import ru.mingun.kaitai.struct.tree.ChunkNode;
import ru.mingun.kaitai.struct.tree.ListNode;
import ru.mingun.kaitai.struct.tree.SimpleNode;
import ru.mingun.kaitai.struct.tree.StructNode;

import javax.swing.Icon;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TreeNodeIcons {

    /*
    I think having an SVG file for every possibility is not sustainable.

    Each tree node has these variables:
        - either sequential or instance
        - data type: struct, boolean, bytes, enum, float, integer, string
        - either list or scalar

    Not sure whether to have SVG files which get overlaid on top of each other,
    or draw the entire icon using java.awt.Graphics at runtime.
     */

    private static final int DEFAULT_SIZE = 16;

    private static final Icon STRUCT_NODE_SEQUENTIAL = getIconFromSvgResourcePath("/tree-node-icons/StructNode-sequential.svg");
    private static final Icon STRUCT_NODE_INSTANCE = getIconFromSvgResourcePath("/tree-node-icons/StructNode-instance.svg");
    private static final Icon LIST_NODE_SEQUENTIAL = getIconFromSvgResourcePath("/tree-node-icons/ListNode-sequential.svg");
    private static final Icon LIST_NODE_INSTANCE = getIconFromSvgResourcePath("/tree-node-icons/ListNode-instance.svg");

    private static final Icon SIMPLE_NODE_SEQUENTIAL_BOOLEAN = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-boolean.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_BYTE_ARRAY = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-bytes.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_ENUM = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-enum.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_FLOAT = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-float.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_GENERIC = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-generic.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_INTEGER = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-integer.svg");
    private static final Icon SIMPLE_NODE_SEQUENTIAL_STRING = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-sequential-string.svg");

    private static final Icon SIMPLE_NODE_INSTANCE_BOOLEAN = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-boolean.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_BYTE_ARRAY = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-bytes.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_ENUM = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-enum.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_FLOAT = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-float.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_GENERIC = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-generic.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_INTEGER = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-integer.svg");
    private static final Icon SIMPLE_NODE_INSTANCE_STRING = getIconFromSvgResourcePath("/tree-node-icons/SimpleNode-instance-string.svg");

    private final static Map<Class<?>, Icon> SIMPLE_NODE_SEQUENTIAL_MAP = new HashMap<>();
    private final static Map<Class<?>, Icon> SIMPLE_NODE_INSTANCE_MAP = new HashMap<>();

    static {
        addClassesToIconMap(SIMPLE_NODE_SEQUENTIAL_MAP, SIMPLE_NODE_SEQUENTIAL_BOOLEAN, boolean.class, Boolean.class);
        addClassesToIconMap(SIMPLE_NODE_SEQUENTIAL_MAP, SIMPLE_NODE_SEQUENTIAL_FLOAT, double.class, Double.class, float.class, Float.class);
        addClassesToIconMap(SIMPLE_NODE_SEQUENTIAL_MAP, SIMPLE_NODE_SEQUENTIAL_INTEGER, int.class, Integer.class, long.class, Long.class);
        addClassesToIconMap(SIMPLE_NODE_SEQUENTIAL_MAP, SIMPLE_NODE_SEQUENTIAL_STRING, String.class);
        addClassesToIconMap(SIMPLE_NODE_SEQUENTIAL_MAP, SIMPLE_NODE_SEQUENTIAL_BYTE_ARRAY, byte[].class);

        addClassesToIconMap(SIMPLE_NODE_INSTANCE_MAP, SIMPLE_NODE_INSTANCE_BOOLEAN, boolean.class, Boolean.class);
        addClassesToIconMap(SIMPLE_NODE_INSTANCE_MAP, SIMPLE_NODE_INSTANCE_FLOAT, double.class, Double.class, float.class, Float.class);
        addClassesToIconMap(SIMPLE_NODE_INSTANCE_MAP, SIMPLE_NODE_INSTANCE_INTEGER, int.class, Integer.class, long.class, Long.class);
        addClassesToIconMap(SIMPLE_NODE_INSTANCE_MAP, SIMPLE_NODE_INSTANCE_STRING, String.class);
        addClassesToIconMap(SIMPLE_NODE_INSTANCE_MAP, SIMPLE_NODE_INSTANCE_BYTE_ARRAY, byte[].class);
    }

    private static Icon getIconFromSvgResourcePath(String resourcePath) {
        SVGIcon icon = new SVGIcon();
        try {
            icon.setSvgURI(TreeNodeIcons.class.getResource(resourcePath).toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        icon.setAntiAlias(true);
        icon.setInterpolation(SVGIcon.INTERP_BICUBIC);
        icon.setAutosize(SVGIcon.AUTOSIZE_BESTFIT);
        icon.setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
        return icon;
    }

    public static Optional<Icon> getTreeNodeIcon(Object treeNode) {
        if (treeNode instanceof StructNode) {
            final boolean sequentialNotInstance = ((ChunkNode) treeNode).isSequential();
            return Optional.of(
                    sequentialNotInstance ? STRUCT_NODE_SEQUENTIAL : STRUCT_NODE_INSTANCE
            );

        } else if (treeNode instanceof ListNode) {
            // TODO access the type of the list elements. It is private Class<?> elementClass
            final boolean sequentialNotInstance = ((ChunkNode) treeNode).isSequential();
            return Optional.of(
                    sequentialNotInstance ? LIST_NODE_SEQUENTIAL : LIST_NODE_INSTANCE
            );

        } else if (treeNode instanceof SimpleNode) {
            final SimpleNode simpleNode = (SimpleNode) treeNode;
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
                        1: my_type
                        2: u1
                types:
                  my_type:
                    seq: []
            The compile-time class of 'type_can_change' will be Object. At runtime, the class will
            be 'my_type' or int (or null if the none of the switch cases matched).

            So, if the compile-time type is Object, we will try getting the runtime class.
            */
            final Class<?> classToGetIconFor;
            if (simpleNode.getValueClass() == Object.class) {
                if (simpleNode.getValue() == null) {
                    return Optional.of(
                            simpleNode.isSequential() ? SIMPLE_NODE_SEQUENTIAL_GENERIC : SIMPLE_NODE_INSTANCE_GENERIC
                    );
                } else {
                    // use runtime class
                    classToGetIconFor = simpleNode.getValue().getClass();
                }
            } else {
                // use compile-time class
                classToGetIconFor = simpleNode.getValueClass();
            }
            return Optional.of(getIconForSimpleNode(classToGetIconFor, simpleNode.isSequential()));
        } else {
            return Optional.empty();
        }
    }

    private static Icon getIconForSimpleNode(Class<?> valueClass, boolean sequentialNotInstance) {
        if (sequentialNotInstance) {
            if (valueClass == null) {
                return SIMPLE_NODE_SEQUENTIAL_GENERIC;
            } else if (valueClass.isEnum()) {
                return SIMPLE_NODE_SEQUENTIAL_ENUM;
            } else {
                return SIMPLE_NODE_SEQUENTIAL_MAP.getOrDefault(valueClass, SIMPLE_NODE_SEQUENTIAL_GENERIC);
            }
        } else {
            if (valueClass == null) {
                return SIMPLE_NODE_INSTANCE_GENERIC;
            } else if (valueClass.isEnum()) {
                return SIMPLE_NODE_INSTANCE_ENUM;
            } else {
                return SIMPLE_NODE_INSTANCE_MAP.getOrDefault(valueClass, SIMPLE_NODE_INSTANCE_GENERIC);
            }
        }
    }

    private static void addClassesToIconMap(Map<Class<?>, Icon> map, Icon icon, Class<?>... classes) {
        for (Class<?> aClass : classes) {
            map.put(aClass, icon);
        }
    }


}