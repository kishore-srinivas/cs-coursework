package ksrinivas.shapes;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class RegularPolygon {

	private double sideLength;
	private int numSides;
	private Circle outer, inner;
	private ArrayList<Line> sides = new ArrayList<Line>();
	private ArrayList<Line> radii = new ArrayList<Line>();

	/**
	 * Default constructor creates a triangle with sides of 100 centered at (200, 130)
	 */
	public RegularPolygon() {
		this(3, 100, 200, 130);
	}
	
	/**
	 * Constructor with side info but no coordinates
	 * 
	 * @param numSides The number of sides in the polygon
	 * @param sideLength The length of the sides
	 */
	public RegularPolygon(int numSides, double sideLength) {
		this(numSides, sideLength, 200, 130);
	}

	/**
	 * Constructor
	 * 
	 * @param numSides The number of sides in the polygon
	 * @param sideLength The length of the sides
	 * @param x The x coordinate of the center of the polygon
	 * @param y The y coordinate of the center of the polygon
	 */
	public RegularPolygon(int numSides, double sideLength, double x, double y) {
		this.numSides = numSides;
		this.sideLength = sideLength;
		
		for (int i = 0; i < numSides; i++) {
			radii.add(new Line((int)x, (int)y, (2*i*Math.PI/numSides) - Math.PI/2, this.calcR()));
		}		
		for (int i = 0; i < radii.size()-1; i++) {
			sides.add(new Line(radii.get(i).getPoint2()[0], radii.get(i).getPoint2()[1], radii.get(i+1).getPoint2()[0], radii.get(i+1).getPoint2()[1]));
		}
		sides.add(new Line(radii.get(radii.size()-1).getPoint2()[0], radii.get(radii.size()-1).getPoint2()[1], radii.get(0).getPoint2()[0], radii.get(0).getPoint2()[1]));
		
		inner = new Circle(x, y, this.calcr());
		outer = new Circle(x, y, this.calcR());
	}

	/**
	 * Calculates inner radius 
	 * 
	 * @return inner radius as double
	 */
	private double calcr() {
		return 0.5*sideLength/Math.tan(Math.PI/numSides);
	}

	/**
	 * Calculates outer radius
	 * 
	 * @return outer radius as double
	 */
	private double calcR() {
		return 0.5*sideLength/Math.sin(Math.PI/numSides);
	}

	/**
	 * Calculates vertex angle
	 * 
	 * @return vertex angle as double
	 */
	public double calcVertexAngle() {
		return (((double)numSides - 2)/(double)numSides)*180;
	}

	/**
	 * Calculates perimeter
	 * 
	 * @return perimeter as double
	 */
	public double calcPerimeter() {
		return sideLength * numSides;
	}

	/**
	 * Calculates area
	 * 
	 * @return area as double
	 */
	public double calcArea() {
		return 0.5*numSides*Math.pow(this.calcR(), 2) * Math.sin(2*Math.PI/numSides);
	}

	/**
	 * Getter for numSides
	 * 
	 * @return int numSides
	 */
	public int getNumSides() {
		return numSides;
	}
	
	public double getSideLength() {
		return sideLength;
	}

	/**
	 * Getter for outer radius
	 * 
	 * @return output of calcR()
	 */
	public double getR() {
		return this.calcR();
	}

	/**
	 * Getter for inner radius
	 * 
	 * @return output of calcr()
	 */
	public double getr() {
		return this.calcr();
	}

	/**
	 * Draws the polygon on the PApplet
	 * 
	 * @param marker The PApplet on which to draw
	 */
	public void draw(PApplet marker) {
		for (int i = 0; i < sides.size(); i++) {
			sides.get(i).draw(marker);
		}
	}

	/**
	 * Draws the inner and outer circles on the PApplet
	 * 
	 * @param marker The PApplet on which to draw
	 */
	public void drawBoundingCircles(PApplet marker) {
		outer.draw(marker);
		inner.draw(marker);
	}

}
