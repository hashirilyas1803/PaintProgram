package Canvas;

import java.awt.Color;
import java.awt.Graphics;
/**
 * 
 * @author uakhan
 * This class creates a circle
 */
class Circle extends Shape
{
    private int	size;

    /**
     * This is the Circle constructor
     * @param iSize	defines the size
     * @param center defines the location
     * @param C	defines the color
     */
    Circle(int iSize, Point center, Color C)
    {
        super(center, C);
        setSize(iSize);
        type = "Circle";
    }

    public void setSize(int iSize) {
        if (iSize > 1) {
            size = iSize;
        } else {
            size = 1;
        }
    }

    public void setColor(Color Ccolor) {
        super.setColor(Ccolor);
    }

    @Override
    public void setCenter(Point center) {
        super.setCenter(center);
    }

    /**
     * 
     * @return returns the size of the circle
     */
    public int getSize()
    {
        return size;
    }

    @Override
    public Point getCenter() {
        return super.getCenter();
    }

    public Color getColor()
    {
        return super.getColor();
    }


    public void draw(Graphics g)
    {
        g.setColor(getColor());
        g.fillOval(getCenter().x - getSize()/2 ,getCenter().y - getSize()/2,getSize(),getSize());
    }
}