package io.kaitai.struct.visualizer.icons;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Peter Froud
 */
public class IconLayeringTestGui extends javax.swing.JFrame {

    private static class AllThree {
        DataTypeEnum dataType;
        SequentialOrInstanceEnum sequentialOrInstance;
        ScalarOrListEnum scalarOrList;

        @Override
        public String toString() {
            return (dataType + " " + sequentialOrInstance + " " + scalarOrList).toLowerCase();
        }
    }

    public IconLayeringTestGui() {
        initComponents();

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        try {
            for (DataTypeEnum dataType : DataTypeEnum.values()) {
                gbc.gridx = dataType.GRID_X;
                gbc.gridy = 1;
                getContentPane().add(new JLabel(dataType.DISPLAY_NAME), gbc);

                for (SequentialOrInstanceEnum sequentialOrInstance : SequentialOrInstanceEnum.values()) {
                    for (ScalarOrListEnum scalarOrList : ScalarOrListEnum.values()) {

                        getContentPane().add(
                                new JLabel(new LayeredSvgIcon(dataType, sequentialOrInstance, scalarOrList)),
                                setGbcPosition(gbc, dataType, sequentialOrInstance, scalarOrList)
                        );

                        final AllThree allThree = new AllThree();
                        allThree.dataType = dataType;
                        allThree.sequentialOrInstance = sequentialOrInstance;
                        allThree.scalarOrList = scalarOrList;
                        root.add(new DefaultMutableTreeNode(allThree));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        jTree1.setModel(new DefaultTreeModel(root));

        // https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html#display
        jTree1.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
                final Object userObject = treeNode.getUserObject();
                if (userObject instanceof AllThree) {
                    AllThree all3 = (AllThree) treeNode.getUserObject();
                    setIcon(new LayeredSvgIcon(all3.dataType, all3.sequentialOrInstance, all3.scalarOrList));
                }
                return this;
            }
        });

        pack();

    }


    private static GridBagConstraints setGbcPosition(GridBagConstraints gbc, DataTypeEnum dataType, SequentialOrInstanceEnum sequentialOrInstance, ScalarOrListEnum scalarOrList) {
        /*
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        |        | x=0               | x=1       | x=2    | x=3     | x=4  | x=5   | x=6    | x=7    |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=0    |                   |                        Data type                              |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=1    |                   | boolean   | byte[] | integer | enum | float | string | struct |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=2    | sequential scalar |           |        |         |      |       |        |        |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=3    | sequential array  |           |        |         |      |       |        |        |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=4    | instance scalar   |           |        |         |      |       |        |        |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
        | y=5    | instance array    |           |        |         |      |       |        |        |
        +--------+-------------------+-----------+--------+---------+------+-------+--------+--------+
         */

        gbc.gridx = dataType.GRID_X;

        switch (sequentialOrInstance) {
            case SEQUENTIAL:
                switch (scalarOrList) {
                    case SCALAR:
                        gbc.gridy = 2;
                        break;
                    case LIST:
                        gbc.gridy = 3;
                        break;
                }
                break;
            case INSTANCE:
                switch (scalarOrList) {
                    case SCALAR:
                        gbc.gridy = 4;
                        break;
                    case LIST:
                        gbc.gridy = 5;
                        break;
                }
                break;
        }
        return gbc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButtonReloadSvgs = new javax.swing.JButton();
        jTree1 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Layered icon test GUI");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("Sequential scalar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel8, gridBagConstraints);

        jLabel9.setText("Sequential list");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel11.setText("Instance scalar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel11, gridBagConstraints);

        jLabel12.setText("Instance list");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel12, gridBagConstraints);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Data type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jLabel14, gridBagConstraints);

        jButtonReloadSvgs.setText("Re-load SVGs");
        jButtonReloadSvgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReloadSvgsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jButtonReloadSvgs, gridBagConstraints);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 5);
        getContentPane().add(jTree1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReloadSvgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReloadSvgsActionPerformed
        try {
            for (DataTypeEnum dataType : DataTypeEnum.values()) {
                dataType.reloadSvg();
            }
            for (SequentialOrInstanceEnum sequentialOrInstance : SequentialOrInstanceEnum.values()) {
                sequentialOrInstance.reloadSvg();
            }
            ScalarOrListEnum.LIST.reloadSvg();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonReloadSvgsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IconLayeringTestGui().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReloadSvgs;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
