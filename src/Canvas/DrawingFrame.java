package Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

class DrawingFrame extends JFrame implements ActionListener
{
	public static void main(String[] args) {
	    JFrame frame = new JFrame( "Drawing Program" );
	    frame.setDefaultCloseOperation(1);

	    DrawingPanel panel = new DrawingPanel();

	    frame.add( panel );
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				try {
					panel.closeUp();
				} catch (FileNotFoundException ex) {
					throw new RuntimeException(ex);
				}
				((JFrame)(e.getComponent())).dispose();
			}
		});

	    frame.pack();
	    frame.setVisible( true );
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}