package illusionDrawings;

import ksrinivas.shapes.Line;
import ksrinivas.shapes.Circle;
import ksrinivas.shapes.Rectangle;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	Rectangle[] rect = new Rectangle[11];
	
	public DrawingSurface() {
		
	}
	
	public void draw() { 
		background(255);
		illusion1();
//		illusion2();
//		illusion3();
//		illusion4();
	}
	
	public void illusion1() {
		strokeWeight(2.f);
		new Line(width/2 - 75, height/2 - 70, -Math.PI/2, 140).draw(this);
		new Line(width/2 + 75, height/2 - 70, -Math.PI/2, 140).draw(this);
		strokeWeight(1.f);
		translate(0, height/2);
		for (int i = -5; i <= 5; i++) {
			new Line(-100, 0, i*Math.PI/30, 500).draw(this);
		}
	}
	
	public void illusion2() {
		Integer[] units = {10, -15, 20, 10, -10, -5, -15, 20, -10};
		for (int n = 0; n < 9; n++) {
			translate(units[n], (height-20)/9);
			for (int i = 0; i < rect.length; i++) {
				rect[i] = new Rectangle(i*(width-40)/11, 0, (width-40)/11, (height-20)/9);
				fill((i%2)*255.f);
				rect[i].draw(this);
			}
		}
	}
	
	public void illusion3() {
		translate(width/2, height/2);
		stroke(0);
		noFill();
		strokeWeight(1.f);
		for (int i = 0; i < 16; i++) {
			new Circle(0, 0, 30+(i*15)).draw(this);
		}
		strokeWeight(1.5f);
		new Rectangle(-75, -75, 150, 150).draw(this);
	}
	
	public void illusion4() {
		translate(width/2, height/2);
		strokeWeight((float) 1.75);
		stroke(0);
		for (int i = 0; i < 32; i++) {
			new Line(0, 0, i*Math.PI/16, 135).draw(this);
		}
		strokeWeight((float) 2.5);
		for (int i = 0; i < 6; i++) {
			new Line(-125.0+i*50, -150.0, -125.0+i*50, 150.0).draw(this);
		}
	}
}