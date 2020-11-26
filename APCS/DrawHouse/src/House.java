import processing.core.PApplet;

public class House {
	
	private float dX;
	private float dY;
	private float scale;
	private int redShift, greenShift, blueShift;
	
	static final int bodyX = 100;
	static final int bodyY = 150;
	
	public House() {
		dX = dY = 0;
		scale = 1;
		redShift = greenShift = blueShift = 0;
	}
	
	public void drawHouse(PApplet drawer) {
		drawer.strokeWeight((float) 1);
		drawer.scale(drawer.width/400.f, drawer.height/400.f);
		
		// front lawn - stationary throughout program
		drawer.stroke(100, 84, 0);
		drawer.fill(0, 100, 0);
		drawer.rect(0, 300, 400, 100);
		
		drawer.pushMatrix();
		drawer.translate(dX, dY);
		drawer.scale(scale);
		
		// house body
		drawer.stroke(99, 16, 132);
		drawer.fill(90 + redShift, 235 + greenShift, 235 + blueShift);
		drawer.rect(bodyX, bodyY, 200, 175);
		
		// window left
		drawer.stroke(153, 31, 129);
		drawer.fill(153 + redShift, 31 + greenShift, 129 + blueShift);
		drawer.rect(bodyX + 35, bodyY + 35, 30, 30);
		
		// window right
		drawer.stroke(79, 37, 188);
		drawer.fill(79 + redShift, 37 + greenShift, 188 + blueShift);
		drawer.rect(bodyX + 135, bodyY + 35, 30, 30);
		
		// roof
		drawer.stroke(162, 40, 74);
		drawer.fill(162 + redShift, 40 + greenShift, 74 + blueShift);
		drawer.triangle(bodyX - 35, bodyY, bodyX + 235, bodyY, bodyX + 100, bodyY - 70);
		
		// door
		drawer.stroke(190, 12, 115);
		drawer.fill(190 + redShift, 12 + greenShift, 115 + blueShift);
		drawer.rect(bodyX + 80, bodyY + 125, 40, 50);
		
		drawer.popMatrix();
	}
	
	public void moveHouse(float x, float y) {
		dX = (float) (x - bodyX*scale - 200*scale/2);
		dY = (float) (y - bodyY*scale - 175*scale/2);
	}
	
	public void scaleHouse(float s) {
		 scale *= s;
	}
	
	public void changeColor(int r, int g, int b) {
		if (r == 0 && g == 0 && b == 0) {
			redShift = greenShift = blueShift = 0;
		} else {
			redShift += r;
			greenShift += g;
			blueShift += b;
		}
	}
}
