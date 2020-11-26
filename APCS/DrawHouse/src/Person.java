import processing.core.PApplet;

public class Person {

	private float dX;
	private float dY;
	private int r, g, b;
	public Line sword, torso;
	public int health;

	public Person(float x, float y, int r, int g, int b) {
		this.dX = x;
		this.dY = y;
		this.r = r;
		this.g = g;
		this.b = b;
		this.sword = new Line(50 + dX, 250 + dY, x, y);
		this.torso = null;
		this.health = 50;
	}

	public void setX(float x) {
		this.dX = x;
	}
	
	public float getX() {
		return dX;
	}

	public void setY(float y) {
		this.dY = y;
	}
	
	public float getY() {
		return dY;
	}

	public void drawPerson(PApplet drawer) {
		//drawer.stroke(r, g, b);
		drawer.stroke(0);
		drawer.strokeWeight((float) 2.5);
		drawer.noFill();
		drawer.pushMatrix();
		drawer.translate(dX, dY);

		// head
		drawer.ellipse(50, 200, 50, 50);

		// torso

		//drawer.line(50, 225, 50, 320);

		// arms
		drawer.line(50, 250, 30, 280);
		drawer.line(50, 250, 70, 280);

		// legs
		drawer.line(50, 320, 30, 365);
		drawer.line(50, 320, 70, 365);

		drawer.popMatrix();
		
		// torso
		torso = new Line(50+dX, 225+dY, 50+dX, 320+dY);
		torso.draw(drawer);
	}

	public void movePerson(int x, int y) {
		dX += x;
		dY += y;
	}

	public void reachSword(PApplet drawer, float x, float y) {
		drawer.stroke(r, g, b);
		if (x >= 70 + dX) {
			sword.setPoint1(70+dX, 280+dY);
			sword.setPoint2(x, y);
			sword.draw(drawer); // draw movable right arm sword
		}
		if (x < 70 + dX) {
			sword.setPoint1(30+dX, 280+dY);
			sword.setPoint2(x, y);
			sword.draw(drawer); // draw movable left arm sword
		}
	}
}
