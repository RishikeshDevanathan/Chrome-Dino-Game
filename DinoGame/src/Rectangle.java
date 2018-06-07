
import java.io.Serializable;

import processing.core.PApplet;

/**
 * This class represents a Rectangle modeled for processing.js
 * 
 * @author Rishikesh
 * @version 9.20.2017
 * 
 */
public class Rectangle extends Shape
{
    private double width, height;

    /**
     * Creates a default instance of a Rectangle object with all dimensions 
     * (x, y, width, height) set to zero.
     * 
     */
    public Rectangle()
    {
        this(0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Creates a new instance of a Rectangle object with the left and right
     * edges of the rectangle at x and x + width. The top and bottom edges
     * are at y and y + height.
     * 
     * @param x the x-coordinate of the upper left corner 
     * @param y the y-coordinate of the upper left corner 
     * @param width the pixel width of the Rectangle
     * @param height the pixel height of the Rectangle
     */
    public Rectangle(double x, double y, double width, double height)
    {
        super(x, y);
        this.width = width;
        this.height = height;

    }
    
    @Override
    public double calcPerimeter()
    {
        return 2 * (width + height);
    }

    @Override
    public double calcArea()
    {
        return (width * height);
    }

    
    @Override
    public boolean contains(double x, double y)
    {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height)
            return true;
        
        return false;
        
    }
 
    /**
     * Determines if the rectangle overlaps with the given rectangle
     * 
     * @param other the Rectangle to check the overlap on
     * @return boolean value representing if the other rectangle overlaps on the implicit rectangle
     */
    public boolean overlaps(Rectangle other)
    {
        double x2 = other.x;
        double y2 = other.y;
        double width2 = other.width;
        double height2 = other.height;

        if (this.contains(x2, y2) 
                || this.contains(x2 + width2, y2 + height2)
                || this.contains(x2 + width2, y2) 
                || this.contains(x2, y2 + height2))
            return true;

        return false;
    }

    /**
     * Determines if the rectangle is in the confines of the DrawingSurface
     * 
     * @param width the width of the DrawingSurface
     * @param height the height of the DrawingSurface
     * @return boolean value representing if all of the implicit rectangle is in the confines of the window
     */
    public boolean inWindow(int width, int height)
    {
        Rectangle window = new Rectangle(0, 0, width, height);

        return window.overlaps(this);

    }

    /**
     * Draws a new instance of a Rectangle object with the left and right
     * edges of the rectangle at x and x + width. The top and bottom edges
     * are at y and y + height.
     * 
     * @param drawer the canvas to draw the objects on
     * @pre drawer must not be null, and appropriate settings should have been selected (color, fill, etc)
     * @post the drawer will have its rectangle mode modified to PApplet.CORNER 
     */
    @Override
    public void draw(PApplet drawer)
    {
        super.draw(drawer);

        drawer.rect((float) x, (float) y, (float) (width), (float) (height));
    }

    /**
     * @return the width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height)
    {
        this.height = height;
    }
    
}
