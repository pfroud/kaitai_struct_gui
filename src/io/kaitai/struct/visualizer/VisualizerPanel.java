package io.kaitai.struct.visualizer;

import at.HexLib.library.HexLib;
import io.kaitai.struct.ClassCompiler;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.StringLanguageOutputWriter;
import io.kaitai.struct.format.ClassSpec;
import io.kaitai.struct.languages.JavaCompiler;
import io.kaitai.struct.languages.JavaCompiler$;
import org.mdkt.compiler.InMemoryJavaCompiler;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.io.IOException;
import java.lang.reflect.Method;

public class VisualizerPanel extends JPanel {
    private JTree tree;
    private HexLib hexEditor;
    private JSplitPane splitPane;

    public VisualizerPanel() throws IOException {
        super();

        initialize();

//        KaitaiStruct ks = Wmf.fromFile("wmf_src/RedBags.wmf");
        try {
            KaitaiStruct ks = parseFileWithKSY("wmf.ksy");
            loadStruct(ks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        tree = new JTree();
        hexEditor = new HexLib(new byte[] {});

        JScrollPane treeScroll = new JScrollPane(tree);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, hexEditor);
    }

    public void loadStruct(KaitaiStruct struct) throws IOException {
        struct._io().seek(0);
        byte[] buf = struct._io().readBytesFull();
        hexEditor.setByteContent(buf);

        DataNode root = new DataNode(0, struct, null, "[root]");

        final DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setShowsRootHandles(true);
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                TreePath path = event.getPath();
                if (path.getLastPathComponent() instanceof DataNode) {
                    DataNode node = (DataNode) path.getLastPathComponent();
                    node.explore(model /* , progressListener */, null);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
            }
        });
        tree.setModel(model);
        root.explore(model /*, progressListener */, null);
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public static final String DEST_PACKAGE = "io.kaitai.struct.visualized";

    /**
     * Compiles a given .ksy file into Java class source.
     * @param ksyFileName
     * @return Java class source code as a string
     */
    private String compileKSY(String ksyFileName) {
        ClassSpec cs = ClassCompiler.localFileToSpec(ksyFileName);
        StringLanguageOutputWriter out = new StringLanguageOutputWriter(JavaCompiler$.MODULE$.indent());
        ClassCompiler cc = new ClassCompiler(cs, new JavaCompiler(true, out, DEST_PACKAGE));
        cc.compile();
        return out.result();
    }

    private Class<?> compileAndLoadJava(String javaSrc) throws Exception {
        return InMemoryJavaCompiler.compile(DEST_PACKAGE + "." + "Wmf", javaSrc);
    }

    private KaitaiStruct parseFileWithKSY(String ksyFileName) throws Exception {
        String javaSrc = compileKSY(ksyFileName);
        Class<?> ksyClass = compileAndLoadJava(javaSrc);

        // Find and run "fromFile" helper method to
        Method fromFileMethod = ksyClass.getMethod("fromFile", String.class);
        Object ks = fromFileMethod.invoke(null, "wmf_src/RedBags.wmf");

        return (KaitaiStruct) ks;
    }
}
