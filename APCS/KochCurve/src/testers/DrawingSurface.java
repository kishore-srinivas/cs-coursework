package testers;
import java.awt.event.KeyEvent;

import fractals.BoxFractal;
import fractals.GosperCurve;
import fractals.KochCurve;
import fractals.PythagorasTree;
import fractals.SierpinskiTriangle;
import processing.core.PApplet;
import processing.event.MouseEvent;


public class DrawingSurface extends PApplet {

	private KochCurve curve;
	private BoxFractal box;
	private SierpinskiTriangle triangle;
	private GosperCurve gosper;
	private PythagorasTree tree;
	private int level, length;
	private float angle, point;
	
	public DrawingSurface() {
		level = 3;
		length = 100;
		curve = new KochCurve(level, length);
		box = new BoxFractal(level, length);
		triangle = new SierpinskiTriangle(level, length);
		gosper = new GosperCurve(level, length);
		tree = new PythagorasTree(level, length);
		angle = 0;
		point = 100;
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
		background(255);   // Clear the screen with a white background
		
		textSize(12);
		fill(0);
		text("Use the mouse wheel to change length, use UP/DOWN keys to change level.", 0, 15);
		
		stroke(0);
//		curve.draw(this);
//		box.draw(this);
//		triangle.draw(this);
//		gosper.draw(this);
		tree.draw(this);
		
	}
	
		public void mouseWheel(MouseEvent event) {
		  int num = event.getCount();
		  length -= num*30;
		  curve = new KochCurve(level,length);
		  box = new BoxFractal(level, length);
		  triangle = new SierpinskiTriangle(level, length);
		  gosper = new GosperCurve(level, length);
	}
	
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_UP) {
			level++;
			curve = new KochCurve(level,length);
			box = new BoxFractal(level, length);
			triangle = new SierpinskiTriangle(level, length);
			gosper = new GosperCurve(level, length);
			tree = new PythagorasTree(level, length);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			level--;
			curve = new KochCurve(level,length);
			box = new BoxFractal(level, length);
			triangle = new SierpinskiTriangle(level, length);
			gosper = new GosperCurve(level, length);
			tree = new PythagorasTree(level, length);
		}
	}
	
	
}










