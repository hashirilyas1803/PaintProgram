package Default;

import Buttons.ColorButton;
import Buttons.GradientButton;
import Interfaces.DrawButtons;
import Windows.Window;
import Canvas.DrawingPanel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class Board extends JPanel
        implements ActionListener , MouseInputListener, DrawButtons, ComponentListener {

    // Attributes
    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 800;
    private final int DELAY = 25;
    private final int HEIGHT = 32;
    private int width = B_WIDTH;
    private int height = B_HEIGHT;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;
    
    private boolean start_drawing = false;
    
    private int x_init;
    private int y_init;
    private int x_final;
    private int y_final;

    private ColorButton button;
    Window menuBar;
    private Header header;
    private ToolBar shapes;
    private ToolBar color;
    private LayersToolBar layers;
    DrawingPanel panel;

    @Override
    public void componentResized(ComponentEvent e) {
        width = getWidth();
        height = getHeight();
        InitializeAssets();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    // Methods
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            
            int key = e.getKeyCode();
            keyPressed = false;

            if (key == KeyEvent.VK_SPACE) {

            }

        }

        @Override
        public void keyPressed(KeyEvent e) {

        	keyPressed = true;
            key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

            }

        }
    }

    public Board() {

        initBoard();
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    private void InitializeAssets() {

        // Initialize the header
        header = new Header(0, 0, this.B_WIDTH, HEIGHT, Color.WHITE, Color.LIGHT_GRAY, 0, this);

        // Initialize the Menubar window
        menuBar = new Window(0, HEIGHT + 2, width, HEIGHT * 3, Color.WHITE, Color.LIGHT_GRAY, 2);

        // Add the shapes toolbar
        shapes = new ToolBar(width/60, menuBar.centre.y + 16, (HEIGHT * 3) + 4, (HEIGHT * 2) + 4, Color.WHITE,Color.LIGHT_GRAY, 2, this);
        menuBar.addToolBar(shapes);

        // Adding buttons to the shapes toolbar
        int xtemp = shapes.centre.x + 2;
        int ytemp = shapes.centre.y + shapes.stroke;
        shapes.addShapes(xtemp, ytemp, 32);

        // Add the color toolbar
        color = new ToolBar(width / 5, menuBar.centre.y + 16, (HEIGHT * 8) + 20, (HEIGHT * 2) + 4, Color.WHITE,  Color.LIGHT_GRAY, 2, this);
        menuBar.addToolBar(color);

        // Add buttons to the color toolbar
        xtemp = color.centre.x + 2;
        ytemp = color.centre.y + shapes.stroke;
        color.buttons.add(new ColorButton(xtemp, ytemp, 42, 64, "Stroke Color"));
        color.buttons.add(new ColorButton(xtemp + 42, ytemp, 42, 64, "Fill Color"));
        color.addPaletteButtons(xtemp + (42 * 2), ytemp, 32, 10);
        xtemp = color.centre.x + color.width - 42;
        color.buttons.add(new GradientButton(xtemp, ytemp, 42, 64, "Gradient"));

        // Add a layers toolbar
        layers = new LayersToolBar((width * 4) / 5, height / 4, width / 5, (height * 2) / 3, Color.GRAY, Color.LIGHT_GRAY, 2, this);
    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        addComponentListener(this);

        panel = new DrawingPanel();

        add(panel);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        menuBar.paint(g);
        header.paint(g);
        layers.paint(g);

        
        if(keyPressed)
        {
//        	drawNotification("key ", g);
        }
        
        if(mousePressed)
        {
//        	drawNotification("mouse ", g);
        }
        
//        if(start_drawing)
//        {
//        	g.setColor(Color.RED);
//        	g.drawRect(x_init, y_init, x_final, y_final);
//        }
    }
    
    private void drawNotification(String text, Graphics g)
    {
    	g.setColor(Color.RED);
    	g.drawString(text + key + " pressed", 20, 20);
    }

    private void drawButton(Graphics g) {

        g.drawImage(button.GetImage(), button.x, button.y, this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }
    
    public void IsClicked(int x, int y)
    {
        header.click(x, y);
//        if (header.editMenu.getButtons().get(0).IsClicked(x, y))
//            panel.undo();
        if (header.IsClicked(x, y))
            return;
        if (shapes.IsClicked(x, y))
            shapes.click(x, y);
        else if (color.IsClicked(x, y))
            color.click(x, y);
//        color.gradient.click(x, y);
        layers.click(x, y);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		IsClicked(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		x_init = e.getX();
//		y_init = e.getY();
//		mousePressed = true;
//		start_drawing = true;
        color.press(e.getX(), e.getY());
        header.press(e.getX(), e.getY());
        layers.press(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		mousePressed = false;
//		start_drawing = false;
        color.release(e.getX(), e.getY());
        header.release(e.getX(), e.getY());
        layers.release(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x_final = e.getX() - x_init;
		y_final = e.getY() - y_init;
	}

    public int getwidth() {
        return width;
    }

    public int getheight() {
        return height;
    }
}