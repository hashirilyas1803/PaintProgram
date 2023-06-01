package Default;

import Buttons.ColorButton;
import Buttons.GradientButton;
import Canvas.Point;
import Canvas.Queue;
import Canvas.Rectangle;
import Canvas.Shape;
import Canvas.*;
import Interfaces.DrawButtons;
import Windows.Window;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Board extends JPanel
        implements ActionListener , MouseInputListener, DrawButtons, ComponentListener, Serializable {

    // Attributes
    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 800;
    private final int DELAY = 0;
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
    public LayersToolBar layers;
    public Window panel;
    Serialization serialization;


    // Drawing variables
    private int x1, y1, x2, y2, x3, y3;
    private long occur;
    public int stroke = 2;
    private Circle circle;
    private Rectangle rectangle;
    private Triangle triangle;
    private Pentagram pentagram;
    private Hexagon hexagon;
    private FreeDrawing drawing;
    public String shape = "Free-Drawing";
    private Random random = new Random();
    //public Queue<Shape> redo = new Queue<>();

    public LinkedList<Stack<Shape>> layer = new LinkedList<>();
    public LinkedList<Queue<Shape>> redo = new LinkedList<>();
//    TextBox textBox = new TextBox(200, 500, 100);

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

            // Bit buggy but the textbox works overall. Saad add a Save Button(ActiveButton) and use that to submit the text
        	keyPressed = true;
            key = e.getKeyCode();

            if (key == KeyEvent.VK_BACK_SPACE) {
                if (Header.save_) {
                    header.delete();
                }
            }
            else if (key == KeyEvent.VK_SPACE) {
                int temp = layer.size() - 1;
                if (!redo.get(temp).isEmpty())
                    layer.get(temp).push(redo.get(temp).dequeue());
            }
            else if (key == KeyEvent.VK_ENTER) {
//                header.save.textBox.path();
                System.out.println(header.save.textBox.getFilepath() + 555);
                Header.save_ = false;
            }
            else {
                if (Header.save_) {
                    header.write(String.valueOf(e.getKeyChar()));
                }

            }


        }
    }

    public Board() {

        initBoard();
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void InitializeAssets() {

        // Initialize the header
        header = new Header(0, 0, this.B_WIDTH, HEIGHT, Color.WHITE, Color.LIGHT_GRAY, 0, this);

        // Initialize the Menubar window
        menuBar = new Window(0, HEIGHT + 2, width, (HEIGHT * 9) / 2, Color.WHITE, Color.LIGHT_GRAY, 2);

        // Add the shapes toolbar
        shapes = new ToolBar(width/60, menuBar.centre.y + 16, (HEIGHT * 3) + 4, (HEIGHT * 2) + 4, Color.WHITE,Color.LIGHT_GRAY, 2, this);
        menuBar.addToolBar(shapes);

        // Adding buttons to the shapes toolbar
        int xtemp = shapes.centre.x + 2;
        int ytemp = shapes.centre.y + shapes.stroke;
        shapes.addShapes(xtemp, ytemp, 32);

        // Add the color toolbar
        color = new ToolBar(width / 3, menuBar.centre.y + 8, (HEIGHT * 16) + 20, (HEIGHT * 2) + 66, Color.WHITE,  Color.LIGHT_GRAY, 2, this);
        menuBar.addToolBar(color);

        // Add buttons to the color toolbar
        xtemp = color.centre.x + 2;
        ytemp = color.centre.y + shapes.stroke;
        color.buttons.add(new ColorButton(xtemp, ytemp, 64, 126, "Stroke Color"));
        color.buttons.add(new ColorButton(xtemp + 64, ytemp, 64, 126, "Fill Color"));
        color.addPaletteButtons(xtemp + (64 * 2), ytemp, 42, 15);

        // Add a layers toolbar
        layers = new LayersToolBar((width * 41) / 50, height / 4, (width * 17)/ 100, (height * 2) / 3, Color.GRAY, Color.LIGHT_GRAY, 2, this);

        // Add a Window for drawing
        panel = new Window(1, menuBar.centre.y + menuBar.height + 4, (width * 4) / 5, (height * 3) / 4, Color.WHITE, Color.LIGHT_GRAY, 1);

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

//        if (obj.isEmpty() && redo.isEmpty()) {
//            try {
//                startUp();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }


        for (int i = 0; i < layer.size(); i++) {
            Stack<Shape> temp = new Stack<>();
            while(!layer.get(i).isEmpty()) {
                Shape test = layer.get(i).pop();
                temp.push(test);
            }
            while(!temp.isEmpty()) {
                Shape temp2 = temp.pop();
                temp2.draw(g);
                layer.get(i).push(temp2);
            }
        }
        menuBar.paint(g);
        header.paint(g);
        layers.paint(g);
        g.setColor(Color.BLACK);
        g.drawString(shape, panel.centre.x + panel.width - 100, panel.centre.y + panel.height - 50);


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
        if (header.IsClicked(x, y))
            return;
        if (shapes.IsClicked(x, y))
            shapes.click(x, y);
        else if (color.IsClicked(x, y)) {
            color.click(x, y);
            stroke = color.strokeSize.getSize();
        }
        layers.click(x, y);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();
//        textBox.click(x, y);
        if (panel.inBounds(x, y) && !Header.save_ && !Header.open_ && !GradientButton.window) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                x1 = x;
                y1 = y;
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
        color.gradient.Clicked(x, y);
        if (panel.inBounds(x, y)) {
            if(SwingUtilities.isLeftMouseButton(e))
            {
                x1 = e.getX();
                y1 = e.getY();
                if (shape.equals("Free-Drawing"))
                    drawing = new FreeDrawing(new Point(x1, y1), color.getStrokeColor(), stroke);
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
            if(SwingUtilities.isLeftMouseButton(e)) {
                occur = 0;
                if (shape.equals("Free-Drawing")) {
                    drawing.stop();
                }
            }
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
        if(SwingUtilities.isLeftMouseButton(e))
        {
            int x = e.getX(), y = e.getY();
            color.gradient.Dragged(x, y);
            if (panel.inBounds(x, y)) {
                // Repeated draw and erase the shapes while dragging for the user to judge how big they should be

                // Erase the intermediate drawing of the shapes
                if (occur != 0 && !shape.equals("Free-Drawing"))
                    layer.get(layer.size() - 1).pop();
                // Purge the redo queue whenever the user draws a new shape
                //purge();
                x2 = x;
                y2 = y;
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
                int radius = (int)Math.sqrt((width * width) + (height * height));
                if (shape.equals("Rectangle")) {
                    rectangle = new Rectangle(start, color.getFillColor(), width, height, color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(rectangle);
                }
                else if (shape.equals("Circle")) {
                    Point center = new Point(x1, y1);
                    circle = new Circle(2 * radius, center, color.getFillColor(), color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(circle);
                }
                else if (shape.equals("Right-Angled-Triangle")) {
                    Point center = new Point(x1, y1);
                    Point corner1 = new Point(x1, y2);
                    Point corner2 = new Point(x2, y2);
                    if (x2 < x1) {
                        center.x = x2;
                        corner1.x = x2;
                        corner2.x = x1;
                    }
                    if (y2 < y1) {
                        center.y = y2;
                        corner1.y = y1;
                        corner2.y = y1;
                    }
                    triangle = new Triangle(center, corner1, corner2, color.getFillColor(), color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(triangle);
                }
                else if (shape.equals("Equilateral-Triangle")) {
                    int distance = (int) ((Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)))) / 2);
                    x3 = x1 + distance;
                    Point center = new Point(x3, y1);
                    Point corner1 = new Point(x2, y2);
                    Point corner2 = new Point(x1, y2);
                    if (x2 < x1) {
                        center.x = x1 - distance;
                        corner1.x = x2;
                        corner2.x = x1;
                    }
                    if (y2 < y1) {
                        center.y = y2;
                        corner1.y = y1;
                        corner2.y = y1;
                    }
                    triangle = new Triangle(center, corner1, corner2, color.getFillColor(), color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(triangle);
                }
                else if (shape.equals("Pentagram")) {
                    pentagram = new Pentagram(radius, new Point(x1, y1), color.getFillColor(), color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(pentagram);
                }
                else if (shape.equals("Hexagon")) {
                    hexagon = new Hexagon(radius, new Point(x1, y1), color.getFillColor(), color.getStrokeColor(), stroke);
                    layer.get(layer.size() - 1).push(hexagon);
                }
                else if (shape.equals("Free-Drawing")) {
                    if (occur == 0)
                        layer.get(layer.size() - 1).push(drawing);
                    drawing.freeDrawing(x, y);
                }
                occur++;
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
            redo.get(redo.size()-1).dequeue();
        }
    }

    // Remove a shape from the stack
    public void undo(){
        int temp = layer.size()-1;
        if (layer.get(temp).size() > 0)
            redo.get(temp).enqueue(layer.get(temp).pop());
    }

//    // Read the layers.selectedStack into a file before the program exits
//    public void closeUp() throws FileNotFoundException {
//        // Open file and declare resources
//        File file = new File("log.txt");
//        PrintWriter out = new PrintWriter(file);
//
//        // Write the obj from the stack and queue to the file
//        out.println(obj.size());
//
//        // Temporary Stack to ensure the obj are written in the order they were drawn
//        Stack<Shape> temp = new Stack<>();
//        while(!obj.isEmpty()) {
//            Shape test = obj.pop();
//            temp.push(test);
//        }
//        while (!temp.isEmpty()) {
//            Shape temp2 = temp.pop();
//            out.print(temp2.type + " " + temp2.getCenter().x + " " + temp2.getCenter().y + " " + temp2.getColor().getRed() + " " + temp2.getColor().getGreen() + " " + temp2.getColor().getBlue() + " ");
//            if (temp2 instanceof Circle)
//                out.println(((Circle) temp2).getSize());
//            else if (temp2 instanceof Triangle)
//                out.println(((Triangle) temp2).getCorner1().x + " " + ((Triangle) temp2).getCorner1().y + " " + ((Triangle) temp2).getCorner2().x + " " + ((Triangle) temp2).getCorner2().y);
//            else
//                out.println(((Rectangle) temp2).getWidth() + " " + ((Rectangle) temp2).getHeight());
//        }
//
//        // Write the obj from the queue into the file
//        while (!redo.isEmpty()) {
//            Shape temp2 = redo.dequeue();
//            out.print(temp2.type + " " + temp2.getCenter().x + " " + temp2.getCenter().y + " " + temp2.getColor().getRed() + " " + temp2.getColor().getGreen() + " " + temp2.getColor().getBlue() + " ");
//            if (temp2 instanceof Circle)
//                out.println(((Circle) temp2).getSize());
//            else if (temp2 instanceof Triangle)
//                out.println(((Triangle) temp2).getCorner1().x + " " + ((Triangle) temp2).getCorner1().y + " " + ((Triangle) temp2).getCorner2().x + " " + ((Triangle) temp2).getCorner2().y);
//            else
//                out.println(((Rectangle) temp2).getWidth() + " " + ((Rectangle) temp2).getHeight());
//        }
//
//        // Close the file
//        out.close();
//    }
//
//    // Read the obj into memory when the program starts
//    public void startUp() throws FileNotFoundException {
//        // Open file and declare resources
//        File file = new File("log.txt");
//        Scanner in = new Scanner(file);
//
//        // Declare a counter to keep track of the obj which should go in the stack and the ones which should go in the queue
//        int stack = 0;
//        if (in.hasNext())
//            stack = Integer.parseInt(in.next());
//        while (in.hasNext()) {
//            // Read the type and general properties of the obj
//            String type = in.next();
//            Point center = new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next()));
//            Color color = new Color(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));
//
//            // Read the specific property of each shape
//            if (type.equals("Circle")) {
//                circle = new Circle(Integer.parseInt(in.next()), center, color);
//                if (stack > 0)
//                    obj.push(circle);
//                else
//                    redo.enqueue(circle);
//            }
//            else if (type.equals("Rectangle")) {
//                rectangle = new Rectangle(center, color, Integer.parseInt(in.next()), Integer.parseInt(in.next()), );
//                if (stack > 0)
//                    obj.push(rectangle);
//                else
//                    redo.enqueue(rectangle);
//            }
//            else {
//                triangle = new Triangle(center, new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), color);
//                if (stack > 0)
//                    obj.push(triangle);
//                else
//                    redo.enqueue(triangle);
//            }
//            stack--;
//        }
//    }
}