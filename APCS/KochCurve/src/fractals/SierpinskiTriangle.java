package fractals;

import processing.core.PApplet;

public class SierpinskiTriangle {

	private int level, length;
	
	public SierpinskiTriangle(int level, int length) {
		this.level = level;
		this.length = length;
	}
	
	public void draw(PApplet marker) {
		drawSierpinskiTriangle(level, marker.width/2, (float) (marker.height/2-length*Math.sqrt(3)/4), length, marker);
	}
	
	private void drawSierpinskiTriangle(int level, float x, float y, double length, PApplet marker) {
		float[] triangle;
		if (level < 1) level = 1;
		if (level == 1) {
			triangle = getTriangle(x, y, length);
			marker.triangle(x, y, triangle[0], triangle[1], triangle[2], triangle[3]);
		} else {
			length /= 2;
			drawSierpinskiTriangle(level-1, x, y, length, marker);
			drawSierpinskiTriangle(level-1, (float) (x-length/2), (float) (y+length*Math.sqrt(3)/2), length, marker);
			drawSierpinskiTriangle(level-1, (float) (x+length/2), (float) (y+length*Math.sqrt(3)/2), length, marker);
		}
	}
	
	private float[] getTriangle(float x, float y, double length) {
		float[] points = new float[4];
		points[0] = (float) (x + Math.cos(-Math.PI/3)*length);
		points[1] = (float) (y - Math.sin(-Math.PI/3)*length);
		points[2] = (float) (x + Math.cos(-2*Math.PI/3)*length);
		points[3] = (float) (y - Math.sin(-2*Math.PI/3)*length);
		return points;
	}
}
