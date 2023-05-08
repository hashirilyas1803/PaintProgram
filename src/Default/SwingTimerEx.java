package Default;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import javax.swing.*;

import Canvas.DrawingPanel;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {
        add(new Board());

//        DrawingPanel panel = new DrawingPanel();
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                try {
//                    panel.closeUp();
//                } catch (FileNotFoundException ex) {
//                    System.out.println("No File Found");
//                }
//                ((JFrame)(e.getComponent())).dispose();
//            }
//        });
//        panel.setPreferredSize(new Dimension(400, 00));
//        add(board);
//        board.add(panel, BoxLayout.X_AXIS);

        setResizable(true);
        pack();

        setTitle("Java Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }
}