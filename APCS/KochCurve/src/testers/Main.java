package testers;
import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {

	
	
	

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(400, 300);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
//		double[] point = new double[2];
//		point = KochCurve.rotate(100, 100, 200, 200, Math.PI/2);
//		System.out.println(KochCurve.rotate(100, 100, 200, 200, -Math.PI/2)[0] + ", " + KochCurve.rotate(100, 100, 200, 200, -Math.PI/2)[1]);
	}
	
	
	
	
	
	
	
	
}
