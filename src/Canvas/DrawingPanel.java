package Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Random;
import java.util.Scanner;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	// PROPERTIES
	private final int DEFAULT_WIDTH  = 800;
	private final int DEFAULT_HEIGHT = 800;

	private int x1, y1, x2, y2, x3, y3;
	private byte occur, tri;

	private Graphics g;

	private Circle circle;
	private Rectangle rectangle;
	private Triangle triangle;
	String shape = "";
	private Random random = new Random();
	private Stack<Shape> shapes = new Stack<>();
	private Queue<Shape> redo = new Queue<>();
	// CONSTRUCTOR
	public DrawingPanel()
	{
		setBackground( Color.BLUE );
		setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );
		setLocation(0, 200);

		this.addMouseListener( this );
		this.addMouseMotionListener(this  );
		this.addKeyListener(this);
		setFocusable(true);
	}

	// METHOD
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (shapes.isEmpty() && redo.isEmpty()) {
			try {
				startUp();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		Stack<Shape> temp = new Stack<>();
		while(!shapes.isEmpty()) {
			Shape test = shapes.pop();
			temp.push(test);
		}
		while(!temp.isEmpty()) {
			Shape temp2 = temp.pop();
			temp2.draw(g);
			shapes.push(temp2);
		}
		g.setColor(Color.BLACK);
		g.drawString(shape, DEFAULT_WIDTH - 100, DEFAULT_HEIGHT - 50);
	}

	private void setUpDrawingGraphics()
	{
//		g = getGraphics();
//		if (shapes.isEmpty() && redo.isEmpty()) {
//			try {
//				startUp();
//			} catch (FileNotFoundException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		Stack<Shape> temp = new Stack<>();
//		while(!shapes.isEmpty()) {
//			Shape test = shapes.pop();
//			temp.push(test);
//		}
//		while(!temp.isEmpty()) {
//			Shape temp2 = temp.pop();
//			temp2.draw(g);
//			shapes.push(temp2);
//		}
//		g.setColor(Color.BLACK);
//		g.drawString(shape, DEFAULT_WIDTH - 100, DEFAULT_HEIGHT - 50);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
					shapes.push(triangle);
					setUpDrawingGraphics();
					tri = 0;
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton(e))
		{
			// Get the co-ordinates of the first mouse press for the shapes except if it is a triangle
			if (!shape.equals("Triangle")) {
				x1 = e.getX();
				y1 = e.getY();
			}
		}
		// Call the Undo function when the right button is pressed
		else if(SwingUtilities.isRightMouseButton(e))
			undo();
		// Push the shape into the stack from the redo queue
		else if(SwingUtilities.isMiddleMouseButton(e))
			if (!redo.isEmpty())
				shapes.push(redo.dequeue());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// Reset the counter when the user finalises a shape
		if(SwingUtilities.isLeftMouseButton(e))
			occur = 0;

		// Redraw the canvas
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Repeated draw and erase the shapes while dragging for the user to judge how big they should be

		// Purge the redo queue whenever the user draws a new shape
		purge();
//		if (occur != 0) {
//			shapes.pop();
			repaint();
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
				shapes.push(rectangle);
				occur++;
			}
			else if (shape.equals("Circle")) {
				int radius = (int)Math.sqrt((width * width) + (height * height));
				Point center = new Point(x1, y1);
				if (occur == 0)
					circle = new Circle(2 * radius, center, new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
				circle.setSize(2 * radius);
				shapes.push(circle);
				occur++;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Draw a line from the previously clicked point to the position of your mouse
		if (shape.equals("Triangle")) {
			if (tri == 1) {
				repaint();
				x2 = e.getX();
				y2 = e.getY();
				g.drawLine(x1, y1, x2, y2);
			}
			else if (tri == 2) {
				repaint();
				g.drawLine(x1, y1, x2, y2);
				x3 = e.getX();
				y3 = e.getY();
				g.drawLine(x2, y2, x3, y3);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// Set up the menu option for the selection of the type of shape to be drawn
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		g = getGraphics();
		g.setColor(Color.BLACK);
		repaint();
		if (e.getKeyChar() == '1')
			shape = "Rectangle";
		else if (e.getKeyChar() == '2')
			shape = "Circle";
		else if (e.getKeyChar() == '3')
			shape = "Triangle";
		g.drawString(shape, DEFAULT_WIDTH - 100, DEFAULT_HEIGHT - 50);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// Purge the redo queue
	public void purge() {
		while (!redo.isEmpty()) {
			redo.dequeue();
		}
	}

	// Remove a shape from the stack
	public void undo(){
		if (shapes.size() > 0){
			redo.enqueue(shapes.pop());
			this.repaint();
		}
	}

	// Read the shapes into a file before the program exits
	public void closeUp() throws FileNotFoundException {
		// Open file and declare resources
		File file = new File("log.txt");
		PrintWriter out = new PrintWriter(file);

		// Write the shapes from the stack and queue to the file
		out.println(shapes.size());

		// Temporary Stack to ensure the shapes are written in the order they were drawn
		Stack<Shape> temp = new Stack<>();
		while(!shapes.isEmpty()) {
			Shape test = shapes.pop();
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
		// Write the shapes from the queue into the file
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

	// Read the shapes into memory when the program starts
	public void startUp() throws FileNotFoundException {
		// Open file and declare resources
		File file = new File("log.txt");
		Scanner in = new Scanner(file);

		// Declare a counter to keep track of the shapes which should go in the stack and the ones which should go in the queue
		int stack = 0;
		if (in.hasNext())
			stack = Integer.parseInt(in.next());
		while (in.hasNext()) {
			// Read the type and general properties of the shapes
			String type = in.next();
			Point center = new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next()));
			Color color = new Color(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));

			// Read the specific property of each shape
			if (type.equals("Circle")) {
				circle = new Circle(Integer.parseInt(in.next()), center, color);
				if (stack > 0)
					shapes.push(circle);
				else
					redo.enqueue(circle);
			}
			else if (type.equals("Rectangle")) {
				rectangle = new Rectangle(center, color, Integer.parseInt(in.next()), Integer.parseInt(in.next()));
				if (stack > 0)
					shapes.push(rectangle);
				else
					redo.enqueue(rectangle);
			}
			else {
				triangle = new Triangle(center, new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), new Point(Integer.parseInt(in.next()), Integer.parseInt(in.next())), color);
				if (stack > 0)
					shapes.push(triangle);
				else
					redo.enqueue(triangle);
			}
			stack--;
		}
	}
}