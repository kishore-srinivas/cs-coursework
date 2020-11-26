package dominique.shape_demo;

import ksrinivas.shapes.Shape;
import ksrinivas.shapes.Shape2D;
import processing.core.PApplet;

public class PhysicsShape {
	// Fields
	private Shape2D boundingShape;
	private double vx, vy; // Velocity components of the PhysicsShape

	private double r; // The radius of the shape if it is a circle

	// Constructors
	public PhysicsShape(Shape2D boundingShape) {
		this.boundingShape = boundingShape;
		vx = 0;
		vy = 0;
	}

	// Methods
	public void draw(PApplet drawer) {
		boundingShape.draw(drawer);
	}

	public Shape getBoundingShape() {
		return boundingShape;
	}

	public double getXVel() {
		return vx;
	}

	public double getYVel() {
		return vy;
	}

	public void setXVel(double velX) {
		vx = velX;
	}

	public void setYVel(double velY) {
		vy = velY;
	}

	public void setVel(double velX, double velY) {
		vx = velX;
		vy = velY;
	}


	public void increaseVel(double incrAmt, int x, int y) {
		// The original magnitude of velocity of the shape
		double velMag = Math.sqrt(vx * vx + vy * vy);

		double newVel = velMag + incrAmt;
		// Set a max velocity - TODO This can be changed
		if (newVel > 5) {
			newVel = velMag;
		}

		// Find the components of the displacement vector of direction change
		double dy = y - boundingShape.getY();
		double dx = x - boundingShape.getX();
		double compMag = Math.sqrt(dy * dy + dx * dx);

		// Change the vector of direction change to its unit vector form
		double unitY = dy / compMag;
		double unitX = dx / compMag;

		vx = newVel * unitX;
		vy = newVel * unitY;
	}

	public double getX() {
		return boundingShape.getX();
	}

	public double getY() {
		return boundingShape.getY();
	}


	public void setRadius(double rad) {
		r = rad;
	}


	public void act(PhysicsShape bounds, double width, double height) {
	

		// Check for collisions with walls
		double x = boundingShape.getX();
		double y = boundingShape.getY();

		double wallX = bounds.getX();
		double wallY = bounds.getY();

		if (x - r < wallX || x + r > wallX + width) {
			// Collision with left or right wall
			vx = -vx;
		}

		if (y - r < wallY || y + r > wallY + height) {
			vy = -vy;
		}

		boundingShape.moveBy(vx, vy);
	}
		}
