package fractals;
import processing.core.PApplet;

public class BoxFractal {
	
	private int level, length;
	
	public BoxFractal(int level, int length) {
		this.level = level;
		this.length = length;
	}
	
	public void draw(PApplet marker) {
		drawBoxFractal(level, marker.width/2-length/2, marker.height/2-length/2, length, marker);
	}
	
	private void drawBoxFractal(int level, float x, float y, int length, PApplet marker) {
		if (level < 1) level = 1;
		if (level == 1) {
			marker.rect(x, y, length, length);
		} else {
			length /= 3;
			drawBoxFractal(level-1, x, y, length, marker);
			drawBoxFractal(level-1, x+2*length, y, length, marker);
			drawBoxFractal(level-1, x+length, y+length, length, marker);
			drawBoxFractal(level-1, x, y+2*length, length, marker);
			drawBoxFractal(level-1, x+2*length, y+2*length, length, marker);
		}
	}

}
