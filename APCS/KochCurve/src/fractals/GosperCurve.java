package fractals;

import java.awt.geom.Point2D;

import processing.core.PApplet;

public class GosperCurve {
	
	private int level, length;
	private float[] line1, line2, line3, line4, line5, line6;
	
	public GosperCurve(int level, int length) {
		this.level = level;
		this.length = length;
	}
	
	public void draw(PApplet marker) {
		drawGosperCurve(level, new Point2D.Float(marker.width/2 - 40*marker.width/400, marker.height/3 + 100*marker.width/400), new Point2D.Float(marker.width/2, marker.height/3), 0, marker);
		
	}
	
	private void drawGosperCurve(int level, Point2D.Float start, Point2D.Float end, double angle, PApplet marker) {
		if (level < 1) level = 1;
		if (level == 1) {
			marker.ellipse(start.x, start.y, 5, 5);
			marker.stroke(255, 0, 0);
			marker.ellipse(end.x, end.y, 5, 5);
			marker.stroke(0);
			
			float x = start.x;
			float y = start.y;
			float dX = Math.abs(start.x - end.x);
			float dY = Math.abs(start.y - end.y);
			float newX = x;
			float newY = y-dY/2.5f;
			
			
			marker.line(x, y, newX, newY);
			x = newX;
			y = newY;
			newX = (float) (x+Math.cos(Math.PI/5)*(dY/2.5f));
			newY = (float) (y-Math.sin(Math.PI/5)*(dY/2.5f));
			marker.line(x, y, newX, newY);
			x = newX;
			y = newY;
			newX = newX;
			newY = (float) (start.y-Math.sin(Math.PI/5)*(dY/2.5f));
			marker.line(x, y, newX, newY);
			x = newX;
			y = newY;
			newX = (float) (x+Math.cos(-Math.PI/5)*dY/2.5f);
			newY = (float) (y-Math.sin(-Math.PI/5)*dY/2.5f);
			marker.line(x, y, newX, newY);
			x = newX;
			y = newY;
			newY = (float) (y-dY*5/6f);
			marker.line(x, y, newX, newY);
			x = newX;
			y = newY;
			marker.line(x, y, end.x, end.y);
		} else {
			drawGosperCurve(level-1, start, new Point2D.Float(end.x-5, end.y+10), angle, marker);
		}
	}

}
