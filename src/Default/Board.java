package Default;

import Buttons.ColorButton;
import Buttons.GradientButton;
import Interfaces.DrawButtons;
import Windows.Window;
import Canvas.*;
import Canvas.Shape;
import Canvas.Point;
import Canvas.Rectangle;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

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
    Window panel;


    // Drawing variables
    private int x1, y1, x2, y2, x3, y3;
    private byte occur, tri;
    private Circle circle;
    private Rectangle rectangle;
    private Triangle triangle;
    public String shape = "";
    private Random random = new Random();
    private Stack<Shape> obj = new Stack<>();
    private Queue<Shape> redo = new Queue<>();

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

        // Add a Window for drawing
        panel = new Window(1, 150, (width * 3) / 4, (height * 3) / 4, Color.WHITE, Color.LIGHT_GRAY, 1);
    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        addComponentListener(this);

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
        panel.paint(g);

        if (obj.isEmpty() && redo.isEmpty()) {
            try {
                startUp();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        Stack<Shape> temp = new Stack<>();
        while(!obj.isEmpty()) {
            Shape test = obj.pop();
            temp.push(test);
        }
        while(!temp.isEmpty()) {
            Shape temp2 = temp.pop();
            temp2.draw(g);
            obj.push(temp2);
        }
        g.setColor(Color.BLACK);
        g.drawString(shape, panel.centre.x + panel.width - 100, panel.centre.y + panel.height - 50);
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
        if (header.editMenu.getButtons().get(0).IsClicked(x, y))
            undo();
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
        int x = e.getX(), y = e.getY();
        if (panel.inBounds(x, y)) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (shape.equals("Triangle")) {
                    if (tri == 0) {
                        x1 = e.getX();
                        y1 = e.getY();
                        tri++;
                    }
                    else if (tri == 1) {
                        x2 = e.getX();
                        y2 = e.getY();
                        tri++;
                    }
                    else if (tri == 2) {
                        x3 = e.getX();
                        y3 = e.getY();
                        triangle = new Triangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));

                        // Purge the redo queue when drawing a new shape
                        purge();
                        obj.push(triangle);
                        tri = 0;
                    }
                }
            }
        }
        else {
		    IsClicked(x, y);
        }

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();
        if (panel.inBounds(x, y)) {
            if(SwingUtilities.isLeftMouseButton(e))
            {
                // Get the co-ordinates of the first mouse press for the shapes except if it is a triangle
                if (!shape.equals("Triangle")) {
                    x1 = e.getX();
                    y1 = e.getY();
                }
            }
        }
        else{
            color.press(x, y);
            header.press(x, y);
            layers.press(x, y);
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();
        // Reset the counter when the user finalises a shape
        if (panel.inBounds(x, y)) {
            if(SwingUtilities.isLeftMouseButton(e))
                occur = 0;
        }
        else {
            color.release(x, y);
            header.release(x, y);
            layers.release(x, y);
        }
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
        int x = e.getX(), y = e.getY();
        if (panel.inBounds(x, y)) {
            // Repeated draw and erase the shapes while dragging for the user to judge how big they should be

            // Purge the redo queue whenever the user draws a new shape
            purge();
//		if (occur != 0) {
//			shapes.pop();
//            repaint();
//		}
            x2 = e.getX();
            y2 = e.getY();
            Point start = new Point(x1, y1);
            int width, height;
            width = x2 - x1;
            height = y2 - y1;
            if (width < 0) {
                start.x = x2;
                width *= -1;
            }
            if (height < 0) {
                start.y = y2;
                height *= -1;
            }
            if(SwingUtilities.isLeftMouseButton(e))
            {
                if (shape.equals("Rectangle")) {
                    if (occur == 0)
                        rectangle = new Rectangle(start,new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)), width, height);
                    rectangle.setCenter(start);
                    rectangle.setWidth(width);
                    rectangle.setHeight(height);
                    obj.push(rectangle);
                    occur++;
                }
                else if (shape.equals("Circle")) {
                    int radius = (int)Math.sqrt((width * width) + (height * height));
                    Point center = new Point(x1, y1);
                    if (occur == 0)
                        circle = new Circle(2 * radius, center, new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
                    circle.setSize(2 * radius);
                    obj.push(circle);
                    occur++;
                }
            }
        }
		
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


    // Purge the redo queue
    public void purge() {
        while (!redo.isEmpty()) {
            redo.dequeue();
        }
    }

    // Remove a shape from the stack
    public void undo(){
        if (obj.size() > 0){
            redo.enqueue(obj.pop());
            this.repaint();
        }
    }

    // Read the obj into a file before the program exits
    public void closeUp() throws FileNotFoundException {
        // Open file and declare resources
        File file = new File("log.txt");
        PrintWriter out = new PrintWriter(file);

        // Write the obj from the stack and queue to the file
        out.println(obj.size());

        // Temporary Stack to ensure the obj are written in the order they were drawn
        Stack<Shape> temp = new Stack<>();
        while(!obj.isEmpty()) {
            Shape test = obj.pop();
            temp.push(test);
        }
        while (!temp.isEmpty()) {
            Shape temp2 = temp.pop();
            out.print(temp2.type + " " + temp2.getCenter().x + " " + temp2.getCenter().y + " " + temp2.getColor().getRed() + " " + temp2.getColor().getGreen() + " " + temp2.getColor().getBlue() + " ");
            if (temp2 instanceof Circle)
                out.println(((Circle) temp2).getSize());
            else if (temp2 instanceof Triangle)
                out.println(((Triangle) temp2).getCorner1().x + " " + ((Triangle) temp2).getCorner1().y + " " + ((Triangle) temp2).getCorner2().x + " " + ((Triangle) temp2).getCorner2().y);
            else
                out.println(((Rectangle) temp2).getWidth() + " " + ((Rectangle) temp2).getHeight());
        }

        // Write the obj from the queue into the file
        while (!redo.isEmpty()) {
            Shape temp2 = redo.dequeue();
            out.print(temp2.type + " " + temp2.getCenter().x + " " + temp2.getCenter().y + " " + temp2.getColor().getRed() + " " + temp2.getColor().getGreen() + " " + temp2.getColor().getBlue() + " ");
            if (temp2 instanceof Circle)
                out.println(((Circle) temp2).getSize());
            else if (temp2 instanceof Triangle)
                out.println(((Triangle) temp2).getCorner1().x + " " + ((Triangle) temp2).getCorner1().y + " " + ((Triangle) temp2).getCorner2().x + " " + ((Triangle) temp2).getCorner2().y);
            else
                out.println(((Rectangle) temp2).getWidth() + " " + ((Rectangle) temp2).getHeight());
        }

        // Close the file
        out.close();
    }

    // Read the obj into memory when the program starts
    public void startUp() throws FileNotFoundException {
        // Open file and declare resources
        File file = new File("log.txt");
        Scanner in = new Scanner(file);

        // Declare a counter to keep track of the obj which should go in the stack and the ones which should go in the queue
        int stack = 0;
        if (in.hasNext())
            stack = Integer.parseInt(in.next());
        while (in.hasNext()) {
            // Read the type and general properties of the obj
            String type = in.next();
            Point center = new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next()));
            Color color = new Color(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));

            // Read the specific property of each shape
            if (type.equals("Circle")) {
                circle = new Circle(Integer.parseInt(in.next()), center, color);
                if (stack > 0)
                    obj.push(circle);
                else
                    redo.enqueue(circle);
            }
            else if (type.equals("Rectangle")) {
                rectangle = new Rectangle(center, color, Integer.parseInt(in.next()), Integer.parseInt(in.next()));
                if (stack > 0)
                    obj.push(rectangle);
                else
                    redo.enqueue(rectangle);
            }
            else {
                triangle = new Triangle(center, new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), color);
                if (stack > 0)
                    obj.push(triangle);
                else
                    redo.enqueue(triangle);
            }
            stack--;
        }
    }
}