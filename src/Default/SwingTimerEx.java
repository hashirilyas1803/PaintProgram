package Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {
        Board board = new Board();
        add(board);

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                try {
//                    board.closeUp();
//                } catch (FileNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }
//                ((JFrame)(e.getComponent())).dispose();
//            }
//        });

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