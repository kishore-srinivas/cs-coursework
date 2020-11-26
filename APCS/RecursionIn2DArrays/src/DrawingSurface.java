import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private EraseObject erase;
	private Maze maze;
	private Labyrinth labyrinth;
	private int runCount;
	private int speed;
	private Point prevToggle;
	
	private final int MAX_SPEED = 480, MIN_SPEED = 15;
	
	
	public DrawingSurface() {
		erase = new EraseObject("eraseobject\\digital.txt");
		erase = null;
		maze = new Maze("maze\\test6.txt");
		maze = null;
		labyrinth = new Labyrinth("labyrinth\\test2.txt");
//		labyrinth = null;
		runCount = -1;
		speed = 120;
		prevToggle = null;
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
		
//		text("Enter: Run 1 step\nSpace: Start/Stop\nUp arrow: Increase speed\nDown arrow: Decrease speed\n\nSpeed: " + (60.0/speed) + " per sec", height+20, 30);
		
//		erase.draw(this, 0, 0, height, height);
//		maze.draw(this, 0, 0, height, height);
		
		if (runCount == 0) {
			runCount = speed;
		} else if (runCount > 0) {
			runCount--;
		}
		
		if (erase != null) {
			erase.draw(this, 0, 0, height, height);
		}
		if (maze != null) {
			maze.draw(this, 0, 0, height, height);
		}
		if (labyrinth != null) {
			labyrinth.draw(this, 0, 0, height, height);
		}
		
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			if (erase != null) {
				Point cellCoord = erase.clickToIndex(click,0,0,dimension,dimension);
				erase.eraseObject(cellCoord.y, cellCoord.x);
			}
			if (maze != null) {
				maze.solve();
			}
			if (labyrinth != null) {
				labyrinth.solve();
			}
		} 
	}
	
	
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			if (erase != null) {
				Point cellCoord = erase.clickToIndex(click,0,0,dimension,dimension);
				if (cellCoord != null && !cellCoord.equals(prevToggle)) {
					prevToggle = cellCoord;
				}
			}
			
		} 
	}
	
	
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_SPACE) {
			if (runCount >= 0)
				runCount = -1;
			else
				runCount = 0;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			speed = Math.min(MAX_SPEED, speed*2);
		} else if (keyCode == KeyEvent.VK_UP) {
			speed = Math.max(MIN_SPEED, speed/2);
			runCount = Math.min(runCount, speed);
		} else if (keyCode == KeyEvent.VK_ENTER) {
		}
	}

	
}










