package io.kaitai.struct.visualizer;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SourceCodeViewerJFrame extends JFrame {

    public SourceCodeViewerJFrame(String javaSourceCode) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Java source code viewer");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // https://github.com/bobbylight/RSyntaxTextArea/wiki
        final RSyntaxTextArea textArea = new RSyntaxTextArea(javaSourceCode);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setEditable(false);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAnimateBracketMatching(false);

        getContentPane().add(new RTextScrollPane(textArea));
        setSize(800, 600);
    }

}
