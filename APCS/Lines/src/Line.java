import java.util.ArrayList;

import processing.core.PApplet;

/*
 * Evan Su's review
 * 2 compliments:
 * 	- Bounding box checks are quick and efficient.
 *  - Using the Double class to check if the intersection coordinates are finite is pretty cool. Better than what I used.
 * 
 * 2 suggestions for improvements:
 *  - If you want, you could edit the intersection-point-drawing-thingy so that it only draws the point iff the intersection point is valid.
 *  - Might want to add some documentation/comments. TBH I don't think this is a big problem given that your code is concise and often self explanatory,
 * 
 */

public class Line {
	
	private float x1, y1, x2, y2;
	
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = (float) x1;
		this.x2 = (float) x2;
		this.y1 = (float) y1;
		this.y2 = (float) y2;
	}
	
	public void draw(PApplet drawer) {
		drawer.line(x1, y1, x2, y2);
	}
	
	public void setPoint1(double x, double y) {
		this.x1 = (float) x;
		this.y1 = (float) y;
	}
	
	public void setPoint2(double x, double y) {
		this.x2 = (float) x;
		this.y2 = (float) y;
	}
	
	public float[] intersects(Line other) {
		float x3 = other.x1;
		float x4 = other.x2;
		float y3 = other.y1;
		float y4 = other.y2;
		float intersectionX = (((x1*y2 - y1*x2)*(x3 - x4)) - ((x1 - x2)*(x3*y4 - y3*x4))) / (((x1 - x2)*(y3 - y4)) - ((y1 - y2)*(x3 - x4)));
		float intersectionY = (((x1*y2 - y1*x2)*(y3 - y4)) - ((y1 - y2)*(x3*y4 - y3*x4))) / (((x1 - x2)*(y3 - y4)) - ((y1 - y2)*(x3 - x4)));
		float[] intersection = null;
		
		// if intersection point is valid on given line segments
		if (Double.isFinite(intersectionX) && Double.isFinite(intersectionY)) {
			if (((intersectionX <= x2 && intersectionX >= x1) || (intersectionX >= x2 && intersectionX <= x1)) &&
					((intersectionX <= x4 && intersectionX >= x3) || (intersectionX >= x4 && intersectionX <= x3))) {
				if (((intersectionY <= y2 && intersectionY >= y1) || (intersectionY >= y2 && intersectionY <= y1)) &&
						((intersectionY <= y4 && intersectionY >= y3) || (intersectionY >= y4 && intersectionY <= y3))) {
					intersection = new float[] {intersectionX, intersectionY};
					return intersection;
				}
			}
		}
		return intersection;
	}
}
