package fractals;

import processing.core.PApplet;

public class PythagorasTree {
	
	private int level, length;
	
	public PythagorasTree(int level, int length) {
		this.level = level;
		this.length = length;
	}
	
	public void draw(PApplet marker) {
		drawPythagorasTree(length, marker.width/2, 3*marker.height/4, level, 0, marker);
	}
	
	private void drawPythagorasTree(double length, float x, float y, int level, double angle, PApplet marker) {
		if (level < 1) level = 1;
		if (level == 1) {
			float x4 = (float) (x + length*Math.cos(-Math.PI/2+angle));
			float y4 = (float) (y - length*Math.sin(-Math.PI/2+angle));
			float x3 = (float) (x4 + length*Math.cos(angle));
			float y3 = (float) (y4 - length*Math.sin(angle));
			marker.quad(x, y, (float) (x+length*Math.cos(angle)), (float) (y-length*Math.sin(angle)), x3, y3, x4, y4);
		} else {
			length /= Math.sqrt(2);
			drawPythagorasTree(length, x, y, level-1, angle+Math.PI/4, marker);
			drawPythagorasTree(length, (float) (x+length*Math.cos(angle)), (float) (y-length*Math.sin(angle)), level-1, angle-Math.PI/4+Math.PI, marker);
		}
	}

}
