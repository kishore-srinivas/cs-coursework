package ksrinivas.illusions.second;


import ksrinivas.shapes.Line;

import java.awt.Color;

import ksrinivas.shapes.Circle;
import ksrinivas.shapes.Rectangle;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	private Color transparentWhite = new Color(255, 255, 255, 0);
	
	public DrawingSurface() {
		
	}
	
	public void draw() { 
//		illusion1();
//		illusion2();
//		illusion3();
//		illusion4();
		tester();
	}
	
	public void tester() {
		Line l = new Line(100.0, 100.0, 200, 200);
		l.draw(this);
		if (l.contains(150, 150)) {
			new Circle(150, 150, 5, Color.GREEN, Color.GREEN, 1.f).draw(this);
		} else {
			new Circle(150, 150, 5, Color.RED, Color.RED, 1.f).draw(this);
		}
		if (l.contains(160, 150)) {
			new Circle(160, 150, 5, Color.GREEN, Color.GREEN, 1.f).draw(this);
		} else {
			new Circle(160, 150, 5, Color.RED, Color.RED, 1.f).draw(this);
		}
		if (l.contains(151, 151)) {
			new Circle(151, 151, 5, Color.GREEN, Color.GREEN, 1.f).draw(this);
		} else {
			new Circle(151, 151, 5, Color.RED, Color.RED, 1.f).draw(this);
		}
	}
	
	public void illusion1() {
		background(255);
		new Line(230, 65, -Math.PI/2, 140, Color.BLUE, transparentWhite, (float) 3.25, "").draw(this);
		new Line(170, 65, -Math.PI/2, 140, Color.BLUE, transparentWhite, (float) 3.25, "").draw(this);
		for (int i = -5; i < 5; i++) {
			new Line(-100, 125, i*Math.PI/40, 500.0).draw(this);
		}
	}
	
	public void illusion2() {
		Integer[] units = {10, -15, 20, 10, -10, -5, -15, 20, -10};
		Rectangle[] rects = new Rectangle[11];
		rects[0] = new Rectangle(2, 10, 35, 35, Color.GRAY, new Color(0, 150, 0), 1.f);
		for (int n = 1; n < 11; n+=2) {
			rects[n] = new Rectangle(n*35, 10, 35, 35, Color.GRAY, Color.WHITE, 1.f);
			rects[n+1] = new Rectangle((n+1)*35, 10, 35, 35, Color.GRAY, new Color(0, 150, 0), 1.f);
		}
		for (int n = 0; n < 11; n++) {
			rects[n].draw(this);
		}
		for (int i = 1; i < 9; i++) {
			for (int n = 0; n < 11; n++) {
				rects[n].moveBy(units[i], 37);
				rects[n].draw(this);
			}
		}
	}
	
	public void illusion3() {	
		background(255);
		for (int i = 0; i < 16; i++) {
			new Circle(190, 140, 40+15*i, Color.BLACK, transparentWhite, 4.f).draw(this);
		}
		new Rectangle(90, 40, 200, 200, new Color(155, 0, 255), transparentWhite, 4.f).draw(this);
	}
	
	public void illusion4() {
		background(255);
		Line l = new Line(190, 140, 0, 130, Color.BLACK, transparentWhite, (float) 3.5, "");
		for (double i = 0; i < 2*Math.PI; i += Math.PI/16) {
			l.rotateLine(i);
			l.draw(this);
		}
		l = new Line(75, 10, 75, 270, Color.RED, transparentWhite, (float) 3.5);
		l.draw(this);
		for (int i = 0; i < 5; i++) {
			l.moveBy(46, 0);
			l.draw(this);
		}
	}
	
	/*
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
	}*/
}