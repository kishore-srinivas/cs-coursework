import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private Grid board;
	private int runCount;	
	
	public DrawingSurface() {
		board = new Grid();
		runCount = -1;
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		//size(0,0,PApplet.P3D);
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(0);
		textAlign(LEFT);
		textSize(12);
		
		if (runCount == 0) {
			board.onClick();
		} else if (runCount > 0) {
			runCount--;
		}
		
		if (board != null) {
			board.draw(this, 0, 0, height, height);
		}
		
	}	
	
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_SPACE) {
			if (runCount >= 0)
				runCount = -1;
			else
				runCount = 0;
		} else if (keyCode == KeyEvent.VK_UP) {
			board.onClick('u');
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			board.onClick('r');
		} else if (keyCode == KeyEvent.VK_DOWN) {
			board.onClick('d');
		} else if (keyCode == KeyEvent.VK_LEFT) {
			board.onClick('l');
		}
	}

	
}










