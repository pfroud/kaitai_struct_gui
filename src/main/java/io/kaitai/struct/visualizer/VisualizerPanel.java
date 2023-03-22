package io.kaitai.struct.visualizer;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.visualizer.icons.LayeredSvgIcon;
import ru.mingun.kaitai.struct.tree.ChunkNode;
import ru.mingun.kaitai.struct.tree.StructModel;
import tv.porst.jhexview.JHexView;
import tv.porst.jhexview.SimpleDataProvider;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class VisualizerPanel extends JPanel {

    /**
     * Color of hex editor section headers.
     */
    private static final Color HEADER = new Color(0x0000c0);

    /**
     * Color of hex data in HEX and ASCII sections.
     */
    private static final Color UNMODIFIED = Color.BLACK;

    /**
     * Background color of selected hex data in HEX and ASCII sections.
     */
    private static final Color SELECTION = new Color(0xc0c0c0);

    private final JTree JTREE = new JTree(new DefaultMutableTreeNode("<root>"));
    private final JHexView HEX_EDITOR = new JHexView();

    private ByteBufferKaitaiStream binaryStreamToParse;

    /**
     * This field stores a reference to the Java class compiled in memory from a KSY file.
     */
    private Class<? extends KaitaiStruct> kaitaiStructClass;

    /**
     * This object does the actual parsing. It is an instance of {@link #kaitaiStructClass}.
     * <p>
     * This instance can only be created when both of these are true:
     * <ul>
     *     <li>a KSY file has been compiled to a Java class, and</li>
     *     <li>the binary stream to parse is known</li>
     * </ul>
     */
    private KaitaiStruct kaitaiStructInstance;

    private Object[] userParams;

    public VisualizerPanel() {
        super();

        HEX_EDITOR.setSeparatorsVisible(false);
        HEX_EDITOR.setBytesPerColumn(1);
        HEX_EDITOR.setColumnSpacing(8);
        HEX_EDITOR.setHeaderFontStyle(Font.BOLD);

        HEX_EDITOR.setFontColorHeader(HEADER);
        HEX_EDITOR.setFontColorOffsetView(HEADER);

        HEX_EDITOR.setFontColorHexView1(UNMODIFIED);
        HEX_EDITOR.setFontColorHexView2(UNMODIFIED);
        HEX_EDITOR.setFontColorAsciiView(UNMODIFIED);

        HEX_EDITOR.setSelectionColor(SELECTION);

        HEX_EDITOR.setBackgroundColorOffsetView(HEX_EDITOR.getBackground());
        HEX_EDITOR.setBackgroundColorHexView(HEX_EDITOR.getBackground());
        HEX_EDITOR.setBackgroundColorAsciiView(HEX_EDITOR.getBackground());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(JTREE), HEX_EDITOR);
        splitPane.setDividerLocation(200);
        setLayout(new BorderLayout());
        add(splitPane);

        JTREE.setShowsRootHandles(true);
        JTREE.addTreeSelectionListener(event -> {
            HEX_EDITOR.getSelectionModel().clearSelection();
            if (JTREE.getSelectionPaths() == null) {
                return;
            }
            for (TreePath path : JTREE.getSelectionPaths()) {
                if (path.getLastPathComponent() instanceof ChunkNode) {
                    final ChunkNode node = (ChunkNode) path.getLastPathComponent();
                    if (node.getSpan() != null) {
                        // Selection in nibbles, so multiply by 2
                        HEX_EDITOR.getSelectionModel().addSelectionInterval(
                                2 * node.getSpan().getStart(),
                                2 * node.getSpan().getEnd() - 1
                        );
                    }
                }
            }
        });

        // https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html#display
        JTREE.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object treeNode, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, treeNode, sel, expanded, leaf, row, hasFocus);
                if (treeNode instanceof ChunkNode) {
                    ChunkNode chunkNode = (ChunkNode) treeNode;
                    setIcon(new LayeredSvgIcon(chunkNode));
                }
                return this;
            }
        });
    }

    public void setBinaryStreamToParse(ByteBufferKaitaiStream stream) {
        binaryStreamToParse = stream;
    }

    /**
     * Set values which will get passed as parameters into the Kaitai Struct object.
     * <ul>
     * <li>In a KSY file, these are in {@code params}.</li>
     * <li>In a Java file, these are constructor arguments which come after
     * {@code KaitaiStream _io, KaitaiStruct _parent, Rdm _root}.</li>
     * </ul>
     *
     * @see <a href="https://doc.kaitai.io/user_guide.html#param-types">Kaitai Struct user guide
     * section 7.10 "Parametric types"</a>
     */
    public void setUserParameters(Object... params) {
        userParams = params;
    }


    /**
     * If the parser and binary stream have both been set, then this method parses the stream
     * and shows the result in the GUI.
     *
     * @throws ReflectiveOperationException if creating an instance of the Kaitai Struct class failed,
     *                                      or if parsing the binary stream failed
     */
    public void parseFileAndUpdateGui() throws ReflectiveOperationException {
        if (isParserReady() && binaryStreamToParse != null) {
            // the stream must already be open when you call the kaitai struct constructor
            kaitaiStructInstance = createKaitaiStructInstance(
                    kaitaiStructClass, binaryStreamToParse, userParams);

            // the read method parses the whole file.
            kaitaiStructInstance._io().seek(0);
            final Method readMethod = kaitaiStructClass.getDeclaredMethod("_read");
            readMethod.setAccessible(true);
            readMethod.invoke(kaitaiStructInstance);

            JTREE.setModel(new StructModel(kaitaiStructInstance));

            updateHexEditorData();
        }
    }

    /**
     * Returns whether a KSY file has been compiled into a Java class.
     *
     * @return true if the application has a reference to a compiled Kaitai Struct parser; false otherwise.
     */
    public boolean isParserReady() {
        return kaitaiStructClass != null;
    }

    private void updateHexEditorData() {
        kaitaiStructInstance._io().seek(0);
        final byte[] allBytes = kaitaiStructInstance._io().readBytesFull(); //read all remaining bytes
        HEX_EDITOR.setData(new SimpleDataProvider(allBytes));
        HEX_EDITOR.setDefinitionStatus(JHexView.DefinitionStatus.DEFINED);
    }

    /**
     * Instantiates a Kaitai Struct class so that the instance will read from the specified binary stream, and returns
     * the instance.
     *
     * @param ksClass the Kaitai Struct Java class to instantiate
     * @param streamToParse the binary stream that the Kaitai Struct parser will parse
     * @return an instance of {@code ksClass} which will parse {@code streamToParse}
     * @throws ReflectiveOperationException if the instance could not be created
     */
    private static KaitaiStruct createKaitaiStructInstance(
            Class<? extends KaitaiStruct> ksClass,
            ByteBufferKaitaiStream streamToParse,
            Object[] userParams)
            throws ReflectiveOperationException {
        final Constructor<? extends KaitaiStruct> ctor = findConstructor(ksClass);
        final Class<?>[] paramTypes = ctor.getParameterTypes();
        final Object[] argsToPassIntoConstructor = new Object[paramTypes.length];
        argsToPassIntoConstructor[0] = streamToParse;
        // index 1 is _parent
        // index 2 is _root
        for (int i = 3; i < argsToPassIntoConstructor.length; ++i) {
            final int indexInUserParamsArray = i - 3;
            if (userParams != null && indexInUserParamsArray < userParams.length) {
                argsToPassIntoConstructor[i] = userParams[indexInUserParamsArray];
            } else {
                argsToPassIntoConstructor[i] = getDefaultValue(paramTypes[i]);
            }
        }

        return ctor.newInstance(argsToPassIntoConstructor);
    }

    /**
     * Returns a constructor from the given Kaitai Struct class.
     * <p>
     * TODO: search for a constructor which accepts user parameters.
     *
     * @param ksClass a Kaitai Struct Java class from which to find a constructor
     * @return a {@code Constructor} object
     */
    @SuppressWarnings("unchecked")
    private static Constructor<? extends KaitaiStruct> findConstructor(Class<? extends KaitaiStruct> ksClass) {
        /*
         Difference between getConstructors and getDeclaredConstructors:
         The getConstructors() method only returns public constructors.
         The getDeclaredConstructors() method returns all constructors (private, etc).
         See https://stackoverflow.com/a/8249415/7376577.
         The TOP_CLASS_NAME_AND_PARAMETERS regex searches for a public constructor,
         so we can use getConstructors() here.

         Right now we have to get all the constructors and search for the right one.
         Once we've added support for user parameters, we can use the getConstructor() method
         and specify the user param types.
         Java does not support arrays of bounded wildcards. As a result:
           * ksClass.getConstructors() returns Constructor<?>[]
           * ksClass.getConstructor()  returns Constructor<? extends KaitaiStruct>
         We're using getConstructors() so we have to manually cast the result.
         */
        for (final Constructor<?> ctor : ksClass.getConstructors()) {
            final Class<?>[] types = ctor.getParameterTypes();
            if (types.length >= 3
                    && types[0] == KaitaiStream.class
                    && types[1] == KaitaiStruct.class
                    && types[2] == ksClass
            ) {
                return (Constructor<? extends KaitaiStruct>) ctor;
            }
        }
        throw new IllegalArgumentException(ksClass + " has no KaitaiStruct-generated constructor");
    }

    /**
     * If you already compiled a KSY file into Java source code, you can add the
     * Java file to your project's sources and then use it directly.
     *
     * @param compiledKaitaiStructClass an instance of {@code java.lang.Class}
     */
    public void setKaitaiStructClass(Class<? extends KaitaiStruct> compiledKaitaiStructClass) {
        this.kaitaiStructClass = compiledKaitaiStructClass;
    }

    private static Object getDefaultValue(Class<?> clazz) {
        if (clazz == boolean.class) return false;
        if (clazz == char.class) return (char) 0;
        if (clazz == byte.class) return (byte) 0;
        if (clazz == short.class) return (short) 0;
        if (clazz == int.class) return 0;
        if (clazz == long.class) return 0L;
        if (clazz == float.class) return 0.0f;
        if (clazz == double.class) return 0.0;
        return null;
    }

    public JTree getTree() {
        return JTREE;
    }

    public JHexView getHexEditor() {
        return HEX_EDITOR;
    }

}
