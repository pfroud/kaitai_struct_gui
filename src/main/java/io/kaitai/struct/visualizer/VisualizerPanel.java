package io.kaitai.struct.visualizer;

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
import java.lang.reflect.Method;

public class VisualizerPanel extends JPanel {

    private final JTree JTREE;
    private final JSplitPane SPLIT_PANE;

    public VisualizerPanel(KaitaiStruct kaitaiStruct) throws ReflectiveOperationException {
        super();

        //////////////////////// Initialize the hex viewer ////////////////////
        final JHexView hexViewer = new JHexView();
        hexViewer.setSeparatorsVisible(false);
        hexViewer.setBytesPerColumn(1);
        hexViewer.setColumnSpacing(8);
        hexViewer.setHeaderFontStyle(Font.BOLD);

        hexViewer.setFontColorHeader(new Color(0x0000C0));
        hexViewer.setFontColorOffsetView(new Color(0x0000C0));

        hexViewer.setFontColorHexView1(Color.BLACK);
        hexViewer.setFontColorHexView2(Color.BLACK);
        hexViewer.setFontColorAsciiView(Color.BLACK);

        hexViewer.setSelectionColor(new Color(0xC0C0C0));

        hexViewer.setBackgroundColorOffsetView(hexViewer.getBackground());
        hexViewer.setBackgroundColorHexView(hexViewer.getBackground());
        hexViewer.setBackgroundColorAsciiView(hexViewer.getBackground());

        ///////////////////////// Initialize the JTree /////////////////////////
        JTREE = new JTree(new DefaultMutableTreeNode("<root>"));
        JTREE.setShowsRootHandles(true);

        JTREE.addTreeSelectionListener(event -> {
            hexViewer.getSelectionModel().clearSelection();
            if (JTREE.getSelectionPaths() == null) {
                return;
            }
            for (TreePath path : JTREE.getSelectionPaths()) {
                if (path.getLastPathComponent() instanceof ChunkNode) {
                    final ChunkNode node = (ChunkNode) path.getLastPathComponent();
                    if (node.getSpan() != null) {
                        // TODO do not select anything if the span has length zero
                        // Selection in nibbles, so multiply by 2
                        hexViewer.getSelectionModel().addSelectionInterval(
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

        ////////////////////////////// Create the JPanel layout ///////////////////////
        setLayout(new BorderLayout());
        SPLIT_PANE = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(JTREE), hexViewer);
        SPLIT_PANE.setDividerLocation(200);
        add(SPLIT_PANE);

        //////////////////////////////// Set the tree model //////////////////////////
        kaitaiStruct._io().seek(0);
        final Method readMethod = kaitaiStruct.getClass().getDeclaredMethod("_read");
        readMethod.setAccessible(true);
        readMethod.invoke(kaitaiStruct);
        JTREE.setModel(new StructModel(kaitaiStruct));

        ///////////////////////// Set hex viewer data ///////////////////////////////
        kaitaiStruct._io().seek(0);
        final byte[] allBytes = kaitaiStruct._io().readBytesFull();
        hexViewer.setData(new SimpleDataProvider(allBytes));
        hexViewer.setDefinitionStatus(JHexView.DefinitionStatus.DEFINED);
    }

    public void setSplitPaneDividerLocation(int location) {
        SPLIT_PANE.setDividerLocation(location);
    }

    public void recursiveExpandJTree() {
        // https://stackoverflow.com/a/15211697
        for (int i = 0; i < JTREE.getRowCount(); i++) {
            JTREE.expandRow(i);
        }
    }

}
