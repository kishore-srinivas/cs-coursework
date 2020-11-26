package ksrinivas.testers;


import java.text.DecimalFormat;

import ksrinivas.shapes.Circle;
import ksrinivas.shapes.Rectangle;
import ksrinivas.shapes.RegularPolygon;
import processing.core.PApplet;


public class DrawingSurface extends PApplet {

	
	public DrawingSurface() {
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);
		stroke(0);
		DecimalFormat numFormat = new DecimalFormat("#.00");
		
		Rectangle rect1 = new Rectangle(50, 150, 100, 50);
		noFill();
		strokeWeight(1.f);
		rect1.draw(this);
		textSize(14);
		fill(0);
		text("Area: " + numFormat.format(rect1.calcArea()), 50, 40);
		text("Perimeter: " + numFormat.format(rect1.calcPerimeter()), 50, 60);
		if (rect1.isPointInside(mouseX, mouseY)) {
			text("Mouse inside rect1!", 50, 80);
			strokeWeight((float) 3.5);
			rect1.mirrorPoint(mouseX, mouseY, this);
		}
		else {
			text("Mouse outside rect1!", 50, 80);
		}
		stroke((float)Math.random());
		strokeWeight(1.f);
		rect1.drawDiagonals(this);
		
		Circle cir1 = new Circle(270, 170, 20);
		noFill();
		stroke(0);
		strokeWeight(1.f);
		ellipseMode(RADIUS);
		cir1.draw(this);
		textSize(14);
		fill(0);
		text("Area: " + numFormat.format(cir1.calcArea()), 250, 40);
		text("Perimeter: " + numFormat.format(cir1.calcPerimeter()), 250, 60);
		if (cir1.isPointInside(mouseX, mouseY)) {
			text("Mouse inside cir1!", 250, 80);
			strokeWeight((float) 2.f);
			cir1.lineFromCenter(mouseX, mouseY, this);
			noFill();
			strokeWeight((float) 1.f);
			stroke(255, 0, 0);
			cir1.mirrorPoint(mouseX, mouseY, this);
		}
		else text("Mouse outside cir1!", 250, 80);
		
		RegularPolygon square = new RegularPolygon(4, 20, 200, 100);
		/*System.out.println(square.getNumSides());
		System.out.println(square.getSideLength());
		System.out.println(square.calcVertexAngle());
		System.out.println(square.getr());
		System.out.println(square.getR());
		System.out.println(square.calcPerimeter());
		System.out.println(square.calcArea());
		System.out.println("=============");*/
		RegularPolygon rectangle1 = new RegularPolygon(4, 50, 50, 50);
	}
	
	
	public void mousePressed() {
	}
	
	
	public void mouseDragged() {
		
	}
	
}










