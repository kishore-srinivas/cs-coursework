package ksrinivas.shapedemo;

import ecksdee.shapes.Line;
import ecksdee.shapes.Shape;
import ecksdee.shapes.Circle;
import processing.core.PApplet;

public class PhysicsShape {
	
	// FIELDS
	private Shape primaryShape;
	private double vx, vy;
	
	// CONSTRUCTORS
	public PhysicsShape(Shape mainShape) {
		vx = vy = 0;
		this.primaryShape = mainShape;
	}
	
	// METHODS
	public void draw(PApplet drawer) {
		primaryShape.draw(drawer);
	}
	
	public double getX() {
		return primaryShape.getX();
	}
	
	public double getY() {
		return primaryShape.getY();
	}
	
	public double getVX() {
		return this.vx;
	}
	
	public double getVY() {
		return this.vy;
	}
	
	public Shape getShape() { // could return the Shape and then use its draw() method
		return primaryShape;
	}

	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void act() {
		primaryShape.move(vx, vy);
	}
	
	public void bounce(Shape boundingShape, double width, double height) {
		if ((this.getX() >= boundingShape.getX() + width) || (this.getX() <= boundingShape.getX())) {
			this.setVelocity(vx*-1, vy);
		}
		else if ((this.getY() >= boundingShape.getY() + height) || (this.getY() <= boundingShape.getY())) {
			this.setVelocity(vx, vy*-1);
		}
		else {
			this.setVelocity(vx*-1, vy*-1);
		}
	}
	
	public void accelerate(double incrementX, double incrementY) {
		if (!((Math.pow(this.vx, 2) + Math.pow(this.vy, 2))>10)) {
			this.vx += incrementX;//* (this.vx/this.vy);
			this.vy += incrementY;// * (this.vy/this.vx);
		}
	}
	
	public boolean isTouching(PhysicsShape other) {
		Circle c = (Circle) other.getShape();
		double distance = Math.sqrt(Math.pow((other.getX() - this.getX()), 2) + Math.pow((other.getY() - this.getY()), 2));
		return distance < Math.abs(((c.getPerimeter() / (4*Math.PI)) +
				(((Circle)this.getShape()).getPerimeter() / (4*Math.PI))));
	}
	
	public boolean isInside(Shape boundingShape) {
		return boundingShape.isPointInside(primaryShape.getX(), primaryShape.getY());
	}

}
