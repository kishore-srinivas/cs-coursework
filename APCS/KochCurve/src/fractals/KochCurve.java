package fractals;
import processing.core.PApplet;

public class KochCurve {

	private int level, length;

    public KochCurve(int level, int length) {
    	this.level = level;
    	this.length = length;
//    	System.out.println(level + ", " + length);
    }
    
    public void draw(PApplet marker) {
    	drawKochCurve(this.level, 10, marker.height/2, this.length, marker, 0);
    }
    
    private void drawKochCurve(int level, float x, float y, int length, PApplet marker, double angle) {
    	if (level == 1) {
    		float newX = (float) (x + Math.cos(angle)*length);
    		float newY = (float) (y + Math.sin(angle)*length);
    		marker.line(x, y, newX, newY);
    	} else {
    		length /= 3;
    		drawKochCurve(level-1, x, y, length, marker, angle);
    		float newX = (float) (x + Math.cos(angle)*length);
    		float newY = (float) (y + Math.sin(angle)*length);
    		angle -= Math.PI/3;
    		drawKochCurve(level-1, newX, newY, length, marker, angle);
    		newX = (float) (newX + Math.cos(angle)*length);
    		newY = (float) (newY + Math.sin(angle)*length);
    		angle += 2*Math.PI/3;
    		drawKochCurve(level-1, newX, newY, length, marker, angle);
    		newX = (float) (newX + Math.cos(angle)*length);
    		newY = (float) (newY + Math.sin(angle)*length);
    		angle -= Math.PI/3;
    		drawKochCurve(level-1, newX, newY, length, marker, angle);
    	}
    }
}
