
import java.awt.Color;
import processing.core.PApplet;

/**
 * 
 * @author Luke Vandevoorde
 * @version 9/29/2017
 */
@Deprecated
public abstract class Shape {
	
	/**
	 * X-coordinate of this shape
	 */
	protected double x;
	
	/**
	 * Y-coordinate of this shape
	 */
	protected double y;
	
	private Color strokeColor, fillColor;
	private int strokeWidth;
	
	/**
	 * Initializes x and y, and sets the drawing stroke to black, fill to white and
	 * stroke weight to 1.
	 * 
	 * @param x
	 *            X-coordinate of this shape
	 * @param y
	 *            Y-coordinate of this shape
	 */
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
		strokeColor = Color.BLACK;
		fillColor = Color.WHITE;
		strokeWidth = 1;
	}
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * @param drawer
	 *            The PApplet this shape is being drawn on
	 */
	public void draw(PApplet drawer) {
		drawer.stroke(strokeColor.getRGB());
		drawer.fill(fillColor.getRGB());
		drawer.strokeWeight(strokeWidth);
	}
	
	/**
	 * Determines if a point is inside this shape.
	 * 
	 * @param x
	 *            X-coordinate of the point
	 * @param y
	 *            Y-coordinate of the point
	 * @return Boolean true if the point (x, y) is inside this shape
	 */
	public abstract boolean contains(double x, double y);
	
	/**
	 * @return The area of the shape
	 */
	public abstract double calcArea();
	
	/**
	 * @return The perimeter of the shape
	 */
	public abstract double calcPerimeter();

	
	/**
	 * Sets the x and y coordinate of the shape
	 * 
	 * @param x
	 *            New X-coordinate
	 * @param y
	 *            New Y-coordinate
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Moves this shape by x and y
	 * 
	 * @param x
	 *            Amount to shift this shape in the x-direction
	 * @param y
	 *            Amount to shift this shape in the y-direction
	 */
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * @return The X-coordinate (double) of this shape
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return The Y-coordinate (double) of this shape
	 */
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
	    this.y = y;
	}
	
	public void setX(double x) {
	    this.x = x;
	}
	
	/**
	 * @param color
	 *            The new color to fill this shape with when drawn.
	 */
	public void setFillColor(Color color) {
		fillColor = color;
	}
	
	/**
	 * @param color
	 *            The new color to outline this shape with when drawn.
	 */
	/**
	 * @param width
	 *            The width (pixels) of the outline. Must be >= 0
	 */
	public void setStrokeWidth(int width) {
		strokeWidth = width;
	}
}