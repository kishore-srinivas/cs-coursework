package ksrinivas.shapedemo;
import java.awt.Color;

import ecksdee.shapes.Circle;
import ecksdee.shapes.Line;
import ecksdee.shapes.Rectangle;
import ecksdee.shapes.Shape;
import ecksdee.shapes.Shape;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	private Color TRANSPARENT_WHITE = new Color(255, 255, 255, 0);
	private Shape boundaryShape;
	private PhysicsShape shape1, shapeA, shape2, shapeB, l1;
	private Line line1;
	private Circle cA, cB;
	private double boundingShapeHeight, boundingShapeWidth, startX, startY;
	
	public DrawingSurface() {
		boundingShapeHeight = 270;
		boundingShapeWidth = 390;
		boundaryShape = new Rectangle(0, 0, boundingShapeWidth, boundingShapeHeight);
		shape1 = new PhysicsShape(new Rectangle(100, 150, 30, 30));
		shapeA = new PhysicsShape(new Circle(115, 165, (60+60*Math.sqrt(2))/2, Color.RED, TRANSPARENT_WHITE, 1));
		shape2 = new PhysicsShape(new Circle(200, 100, 30));
		shapeB = new PhysicsShape(new Circle(199, 100, 32, Color.RED, TRANSPARENT_WHITE, 1));
		l1 = new PhysicsShape(new Line(0, 0, 0, 0));
		line1 = (Line) l1.getShape();
	}
	
	public void draw() { 
		background(255);
		boundaryShape.draw(this);
		shape1.draw(this);
		shape1.act();
		shapeA.act();
		shape2.draw(this);
		shape2.act();
		shapeB.act();
		l1.draw(this);
		if (!shapeA.isInside(boundaryShape)) {
			shapeA.bounce(boundaryShape, boundingShapeWidth, boundingShapeHeight);
			shape1.setVelocity(shapeA.getVX(), shapeA.getVY());
		}
		if (!shapeB.isInside(boundaryShape)) {
			shapeB.bounce(boundaryShape, boundingShapeWidth, boundingShapeHeight);
			shape2.setVelocity(shapeB.getVX(), shapeB.getVY());
		}
		
		if (shapeA.isTouching(shapeB)) {
			shapeA.setVelocity(shapeA.getVX()*-1, shapeA.getVY()*-1);
			shape1.setVelocity(shapeA.getVX(), shapeA.getVY());
			shapeB.setVelocity(shapeB.getVX()*-1, shapeB.getVY()*-1);
			shape2.setVelocity(shapeB.getVX(), shapeB.getVY());
		}
		
		/*if (shapeA.isTouching(l1)) {
			shapeA.setVelocity(shapeA.getVX() *-1, shapeA.getVY()*-1);
		}*/
	}
	
	public void mousePressed() {
		startX = mouseX;
		startY = mouseY;
		
//		shape1.accelerate(Math.cos(mouseX - shape1.getX())*3, Math.sin(mouseY - shape1.getY())*3);
//		shape2.accelerate(Math.cos(mouseX - shape2.getX())*3, Math.sin(mouseY - shape2.getY())*3);
		
		if (mouseX > shape1.getX() && mouseY > shape1.getY()) {
			shape1.setVelocity(3, 2);
		}
		if (mouseX < shape1.getX() && mouseY > shape1.getY()) {
			shape1.setVelocity(-3, 2);
		}
		if (mouseX > shape1.getX() && mouseY < shape1.getY()) {
			shape1.setVelocity(3, -2);
		}
		if (mouseX < shape1.getX() && mouseY < shape1.getY()) {
			shape1.setVelocity(-3, -2);
		}
		
		if (mouseX > shape2.getX() && mouseY > shape2.getY()) {
			shape2.setVelocity(2, 2.5);
		}
		if (mouseX < shape2.getX() && mouseY > shape2.getY()) {
			shape2.setVelocity(-2, 2.5);
		}
		if (mouseX > shape2.getX() && mouseY < shape2.getY()) {
			shape2.setVelocity(2, -2.5);
		}
		if (mouseX < shape2.getX() && mouseY < shape2.getY()) {
			shape2.setVelocity(-2, -2.5);
		}
		
		shapeA.setVelocity(shape1.getVX(), shape1.getVY());
		shapeB.setVelocity(shape2.getVX(), shape2.getVY());
	}
	
	public void mouseDragged() {
		l1 = new PhysicsShape(new Line(startX, startY, mouseX, mouseY));
	}
	
}