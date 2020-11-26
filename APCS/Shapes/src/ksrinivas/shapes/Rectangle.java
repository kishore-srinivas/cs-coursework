package ksrinivas.shapes;

import java.awt.Color;

import processing.core.PApplet;

/**
 * 
 * This class models a Processing-drawable Rectangle.
 * 
 * @author ksrinivas788
 *
 */
public class Rectangle extends Shape2D {
	private double width, height;
	/**
	 * Creates a default instance of a Rectangle object with all dimensions
	 * set to zero.
	 */
	public Rectangle() { 
		super(0, 0);
		width = 0;
		height = 0;
		//this(0, 0, 0, 0);
	}

	/**
	 * Creates a new instance of a Rectangle object with the left and right
	 * edges of the rectangle at x and x + width. The top and bottom edges
	 * are at y and y + height.
	 * 
	 * @param x The x coordinate of the upper left corner of the rectangle
	 * @param y The y coordinate of the upper left corner of the rectangle
	 * @param width The pixel width of the rectangle
	 * @param height The pixel height of the rectangle
	 */
	public Rectangle(double x, double y, double width, double height) {
		this(x, y, width, height, Color.BLACK, new Color(255, 255, 255, 0), 1.f);
	}
	
	/**
	 * Creates a new instance of a Rectangle object with (x, y) as its top left corner
	 * 
	 * @param x The x coordinate of the upper left corner of the rectangle
	 * @param y The y coordinate of the upper left corner of the rectangle
	 * @param width The pixel width of the rectangle
	 * @param height The pixel height of the rectangle
	 * @param stroke The Color object representing the stroke color
	 * @param fill The Color object representing the fill color
	 * @param strokeWeight The width of the stroke
	 */
	public Rectangle(double x, double y, double width, double height, Color stroke, Color fill, float strokeWeight) {
		super(x, y, stroke, fill, strokeWeight);
		this.width = width;
		this.height = height;		
	}

	/**
	 * Calculates and returns the perimeter of the rectangle
	 * 
	 * @return double perimeter of rectangle in pixels
	 */
	public double calcPerimeter() {
		return width*2 + height*2;
	}

	/**
	 * Calculates and returns the area of the rectangle
	 * 
	 * @return double area of rectangle in square pixels
	 */
	public double calcArea() {
		return width * height;
	}

	/**
	 * Determines whether the point x,y is contained inside this rectangle
	 * 
	 * @param x The x coordinate of given point
	 * @param y The y coordinate of given point
	 * @return boolean whether point (x,y) is inside the rectangle
	 */
	public boolean isPointInside(double x, double y) {
		return ((x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height));
	}

	/**
	 * Mirrors the point (x,y) across each side of the rectangle
	 * 
	 * @param x The x coordinate of given point
	 * @param y The y coordinate of given point
	 * @param marker The PApplet used to draw mirrored points
	 * @post marker.stroke is set to (127, 127, 127)
	 */
	public void mirrorPoint(double x, double y, PApplet marker) {
		marker.stroke(255, 0, 0);
		marker.point((float)(this.x - (x - this.x)), (float)y);
		marker.stroke(0, 255, 0);
		marker.point((float)x, (float)(this.y - (y - this.y)));
		marker.stroke(0, 0, 255);
		marker.point((float)(this.x + this.width + ((this.x + this.width) - x)), (float)y);
		marker.stroke(127, 127, 127);
		marker.point((float)x, (float)(this.y + this.height + (this.y + this.height) - y));
	}

	/**
	 * Draws the diagonals of the rectangle
	 * 
	 * @param marker The PApplet used to draw diagonals
	 */
	public void drawDiagonals(PApplet marker) {
		marker.line((float)this.x, (float)this.y, (float)this.x + (float)this.width, (float)this.y + (float)this.height);
		marker.line((float)this.x + (float)this.width, (float)this.y, (float)this.x, (float)this.y + (float)this.height);

	}

	/**
	 * Draws a new instance of a Rectangle object with the left and right
	 * edges of the rectangle at x and x + width. The top and bottom edges
	 * are at y and y + height.
	 * 
	 * @param marker The PApplet used to draw the rectangle
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.rect((float)x, (float)y, (float)width, (float)height);
	}


}
