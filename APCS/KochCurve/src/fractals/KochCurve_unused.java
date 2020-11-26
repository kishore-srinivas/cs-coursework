package fractals;
import processing.core.PApplet;


/**
  @(#)KochCurve.java


  @author
  @version
*/
public class KochCurve_unused {

	int level, length;

    public KochCurve_unused(int level, int length) {
    	this.level = level;
    	this.length = length;
//    	System.out.println(level + ", " + length);
    }
    
    public void draw(PApplet marker) {
    	drawKochCurve(this.level, marker.width/2, marker.height/2, this.length, marker, 0);
    }

    private void drawKochCurve(int level, float x, float y, int length, PApplet marker, double angle) {
    	marker.pushMatrix();
//    	marker.rotate((float) angle);
//    	if (level < 0) level = 0;
    	level = Math.max(level, 0);
    	if (level <= 0) {
    		marker.line(x-3*length/2, y, x+3*length/2, y);
    	}
    	else if (level == 1) {
//    		marker.rotate((float) angle);
//    		marker.line(rotate(x-3*length/2, y, x, y, angle, marker)[0], 
//    				rotate(x-3*length/2, y, x, y, angle, marker)[1], 
//    				rotate(x-length/2, y, x, y, angle, marker)[0], 
//    				rotate(x-length/2, y, x, y, angle, marker)[1]);
//    		marker.line(rotate(x-length/2, y, x, y, angle, marker)[0], 
//    				rotate(x-length/2, y, x, y, angle, marker)[1], 
//    				rotate(x, (float) (y-Math.sqrt(3)*length/2), x, y, angle, marker)[0], 
//    				rotate(x, (float) (y-Math.sqrt(3)*length/2), x, y, angle, marker)[1]);
//    		marker.line(rotate(x, (float) (y-Math.sqrt(3)*length/2), x, y, angle, marker)[0], 
//    				rotate(x, (float) (y-Math.sqrt(3)*length/2), x, y, angle, marker)[1], 
//    				rotate(x+length/2, y, x, y, angle, marker)[0], 
//    				rotate(x+length/2, y, x, y, angle, marker)[1]);
//    		marker.line(rotate(x+length/2, y, x, y, angle, marker)[0], 
//    				rotate(x+length/2, y, x, y, angle, marker)[1], 
//    				rotate(x+3*length/2, y, x, y, angle, marker)[0], 
//    				rotate(x+3*length/2, y, x, y, angle, marker)[1]);
    		
//    		marker.strokeWeight(2.5f);
//    		marker.point(x, y);
//    		marker.strokeWeight(1);
//    		marker.stroke(255, 0, 0);
//    		marker.line(x-3*length/2, y, 
//    				rotate(x-length/2, y, x, y, angle, marker)[0], 
//    				rotate(x-length/2, y, x, y, angle, marker)[1]);
    		
    		/*marker.line((float)Math.cos(angle)*length+x-3*length/2, (float)-Math.sin(angle)*length+y, (float)Math.cos(angle)*length+x-length/2, (float)-Math.sin(angle)*length+y);
    		marker.line((float)Math.cos(angle)*length+x-length/2, (float)-Math.sin(angle)*length+y, (float)Math.cos(angle)*length+x, (float) (-Math.sin(angle)*length+y-Math.sqrt(3)*length/2));
    		marker.line((float)Math.cos(angle)*length+x, (float) (-Math.sin(angle)*length+y-Math.sqrt(3)*length/2), (float)Math.cos(angle)*length+x+length/2, (float)-Math.sin(angle)*length+y);
    		marker.line((float)Math.cos(angle)*length+x+length/2, (float)-Math.sin(angle)*length+y, (float)Math.cos(angle)*length+x+3*length/2, (float)-Math.sin(angle)*length+y);*/
    		
    		marker.line(x-3*length/2, y, x-length/2, y);
    		marker.line(x-length/2, y, x, (float) (y-Math.sqrt(3)*length/2));
    		marker.line(x, (float) (y-Math.sqrt(3)*length/2), x+length/2, y);
    		marker.line(x+length/2, y, x+3*length/2, y);
    		
//    		marker.rotate((float) -angle);
//    		marker.line(x-length/2, y+10, x+length/2, y+10);
    	} else {
//    		drawKochCurve(1, x, y, length, marker, 0);
    		drawKochCurve(level-1, x-length, y, length/3, marker, 0);
    		drawKochCurve(level-1, x-length/4, (float) (y-Math.sqrt(3)*length/4), length/3, marker, Math.PI/3);
    		drawKochCurve(level-1, x+length/4, (float) (y-Math.sqrt(3)*length/4), length/3, marker, -Math.PI/3);
    		drawKochCurve(level-1, x+length, y, length/3, marker, 0);
    	}
    	marker.popMatrix();
    }
    
    public static float[] rotate(float pointX, float pointY, float centerX, float centerY, double angle, PApplet marker) {
    	marker.pushMatrix();
    	marker.pushStyle();
    	marker.noFill();
    	marker.stroke(0);
    	marker.strokeWeight(1);
//    	marker.ellipse(centerX, centerY, distance(pointX, pointY, centerX, centerY)*2, distance(pointX, pointY, centerX, centerY)*2);
    	float[] newPoint = new float[2];
    	newPoint[0] = (float) (centerX - Math.cos(angle)*distance(pointX, pointY, centerX, centerY));
    	newPoint[1] = (float) (pointY - Math.sin(angle)*distance(pointX, pointY, centerX, centerY));
    	marker.popStyle();
    	marker.popMatrix();
    	return newPoint;
    }
    
    private static float distance(float x1, float y1, float x2, float y2) {
    	return (float) Math.sqrt((Math.pow(x1-x2, 2)) + (Math.pow(y1-y2, 2)));
    }

}
