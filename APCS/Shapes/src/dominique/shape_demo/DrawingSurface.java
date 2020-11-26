package dominique.shape_demo;

import processing.core.PApplet;
import ksrinivas.shapes.Circle;
import ksrinivas.shapes.Rectangle;

public class DrawingSurface extends PApplet {

	private PhysicsShape shapeA, shapeB,shapeC;

	public DrawingSurface() {
		shapeA = new PhysicsShape(new Circle(350, 350, 50));
		shapeA.setRadius(50);
		shapeB = new PhysicsShape(new Rectangle(0, 0, 700, 700));
		shapeC = new PhysicsShape (new Rectangle (30,300,50,50));

	}

	public void setup() {

	}

	public void draw() {
		background(255);
		shapeA.draw(this);
		shapeC.draw(this);
		shapeB.draw(this);
		
		

		shapeA.act(shapeB, 725, 725);
		shapeC.act(shapeB, 650, 650);
	}


	public void mousePressed() {
		if (mouseButton == RIGHT) {

			
			shapeA.increaseVel(1, mouseX, mouseY);
			shapeC.increaseVel(1, mouseX, mouseY);

		} else if (mouseButton == LEFT) {
			shapeA.increaseVel(-1, mouseX, mouseY);
			shapeC.increaseVel(-1, mouseX, mouseY);


		}

	}

}
