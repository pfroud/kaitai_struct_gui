package io.kaitai.struct.visualizer;

import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.visualizer.icons.LayeredSvgIcon;
import ru.mingun.kaitai.struct.tree.ChunkNode;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.tree.TreeNode;
import ru.mingun.kaitai.struct.Span;
import ru.mingun.kaitai.struct.tree.KaitaiStructTreeModel;
import tv.porst.jhexview.SelectionModel.Interval;

public class KaitaiStructVisualizerPanel extends JPanel {

    private final JTree JTREE;
    private final JSplitPane SPLIT_PANE;

    /**
     * This field is to prevent repeated selection events from happening. The JTree selection
     * listener changes the hex viewer selection, and the hex viewer selection listener changes the
     * JTree selection.
     */
    private boolean runEventListeners = true;

    public KaitaiStructVisualizerPanel(KaitaiStruct kaitaiStruct) throws ReflectiveOperationException {
        super();

        //////////////////////// Initialize the hex viewer ////////////////////
        final JHexView hexViewer = new JHexView();
        hexViewer.setSeparatorsVisible(false);
        hexViewer.setBytesPerColumn(1);
        hexViewer.setColumnSpacing(8);
        hexViewer.setHeaderFontStyle(Font.BOLD);
        hexViewer.setEditable(false);

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
            if (!runEventListeners) {
                return;
            }
            runEventListeners = false;
            hexViewer.getSelectionModel().clearSelection();
            if (JTREE.getSelectionPaths() == null) {
                return;
            }
            for (TreePath path : JTREE.getSelectionPaths()) {
                if (path.getLastPathComponent() instanceof ChunkNode) {
                    final ChunkNode node = (ChunkNode) path.getLastPathComponent();
                    final Span span = node.getSpan();
                    if (span != null && span.getStart() != span.getEnd()) {
                        // Selection in nibbles, so multiply by 2
                        hexViewer.getSelectionModel().addSelectionInterval(
                                2 * span.getStart(),
                                2 * span.getEnd() - 1
                        );
                    }
                }
            }
            runEventListeners = true;
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
        final KaitaiStructTreeModel treeModel = new KaitaiStructTreeModel(kaitaiStruct);
        JTREE.setModel(treeModel);

        ///////////////////////// Set hex viewer data ///////////////////////////////
        kaitaiStruct._io().seek(0);
        final byte[] allBytes = kaitaiStruct._io().readBytesFull();
        hexViewer.setData(new SimpleDataProvider(allBytes));
        hexViewer.setDefinitionStatus(JHexView.DefinitionStatus.DEFINED);

        /////////////////////// Populate map for the hex viewer selection listener////////////////
        /*
        This map is used to select tree nodes from a hex viewer selection.
        We never do a lookup on this map. It is just used as a Set of pairs.
         */
        final Map<TreePath, Span> mapTreePathToSpan = new HashMap<>();
        populateMapOfTreePathToSpan(mapTreePathToSpan, treeModel.getRoot(), new TreePath(treeModel.getRoot()));

        /////////////////////// Add hex viewer selection listener ///////////////
        hexViewer.getSelectionModel().addSelectionListener(selectionEvent -> {
            if (!runEventListeners) {
                return;
            }

            runEventListeners = false;

            JTREE.getSelectionModel().clearSelection();

            final Iterator<Interval> selectedIntervals = hexViewer.getSelectionModel().iterator();
            while (selectedIntervals.hasNext()) {
                final Interval selectedIntervalNibbles = selectedIntervals.next();

                /*
                The hex viewer selection interval is in nibbles but Kaitai Struct
                uses bytes.
                 */
                final int selectionStart = (int) Math.floor(selectedIntervalNibbles.getStart() / 2.0);
                final int selectionEnd = (int) Math.ceil(selectedIntervalNibbles.getEnd() / 2.0);

                for (Map.Entry<TreePath, Span> entry : mapTreePathToSpan.entrySet()) {
                    final TreePath treePath = entry.getKey();
                    final Span span = entry.getValue();
                    final long spanStart = span.getStart();
                    final long spanEnd = span.getEnd();

                    final boolean selectTheTreeNode;
                    /*
                     Check if the left edge of the selection is before the span.
                     */
                    if (selectionStart < spanStart) {
                        /*
                        The left edge of the selection is before the span.

                        Select the tree node only if the right edge of the selection is either
                        inside the span or goes past the right edge of the span.
                         */
                        if (selectionEnd > spanStart) {
                            /*
                            The right edge of the selection is inside the span or goes past the
                            right edge of the span.

                            Example:

                            selection   v-------v
                               bytes  0 1 2 3 4 5 6 7 8 9
                                span          ^---^

                            or

                            selection    v-------------v
                                bytes  0 1 2 3 4 5 6 7 8 9
                                 span          ^---^
                             */
                            selectTheTreeNode = true;
                        } else {
                            /*
                            The right edge of the span is to the left of the span.

                            Example:

                            selection    v---v
                                bytes  0 1 2 3 4 5 6 7 8 9
                                 span          ^---^
                             */
                            selectTheTreeNode = false;
                        }
                    } else {
                        /*
                        The left edge of the selection is either inside the span
                        or past the right edge of the span.

                        Select the tree node only if the left edge of the selection is inside
                        the span.
                         */
                        if (selectionStart < spanEnd) {
                            /*
                            The left edge of the selection is inside the span.

                            Example:

                            selection         v-----v
                               bytes  0 1 2 3 4 5 6 7 8 9
                                span      ^-----^
                             */
                            selectTheTreeNode = true;
                        } else {
                            /*
                            The left edge of the selection is to the right of the span.

                            Example:

                            selection                v---v
                                bytes  0 1 2 3 4 5 6 7 8 9
                                 span      ^-----^
                             */
                            selectTheTreeNode = false;
                        }
                    }

                    if (selectTheTreeNode) {
                        JTREE.getSelectionModel().addSelectionPath(treePath);
                    }
                }
            }
            runEventListeners = true;
        });
    }

    private void populateMapOfTreePathToSpan(Map<TreePath, Span> destination, ChunkNode root, TreePath thePathSoFar) {
        if (root.isLeaf()) {
            destination.put(thePathSoFar, root.getSpan());
        } else {
            final Enumeration<? extends TreeNode> children = root.children();
            while (children.hasMoreElements()) {
                final TreeNode child = children.nextElement();
                if (child instanceof ChunkNode) {
                    populateMapOfTreePathToSpan(destination, (ChunkNode) child, thePathSoFar.pathByAddingChild(child));
                }
            }
        }
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
