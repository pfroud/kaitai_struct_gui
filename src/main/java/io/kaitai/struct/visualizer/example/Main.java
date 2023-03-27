package io.kaitai.struct.visualizer.example;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.visualizer.KaitaiStructVisualizerPanel;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                final KaitaiStructVisualizerPanel visualizerPanel = new KaitaiStructVisualizerPanel(
                        new Example(
                                new ByteBufferKaitaiStream(new byte[]{1, 2, 3})
                        )
                );
                visualizerPanel.recursiveExpandJTree();
                visualizerPanel.setSplitPaneDividerLocation(400);

                final JFrame jframe = new JFrame("Kaitai Struct visualizer");
                jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jframe.getContentPane().setLayout(new BorderLayout());
                jframe.getContentPane().add(visualizerPanel);
                jframe.setIconImages(Arrays.asList(
                        new ImageIcon(Main.class.getResource("/kaitai-struct-icon-48.png")).getImage(),
                        new ImageIcon(Main.class.getResource("/kaitai-struct-icon-32.png")).getImage(),
                        new ImageIcon(Main.class.getResource("/kaitai-struct-icon-16.png")).getImage()
                ));
                jframe.pack();
                jframe.setVisible(true);
            } catch (Exception ex) {
                if (ex instanceof InvocationTargetException) {
                    ex.getCause().printStackTrace();
                } else {
                    ex.printStackTrace();
                }
            }
        });
    }
}
