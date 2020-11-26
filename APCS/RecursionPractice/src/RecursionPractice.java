import java.awt.Dimension;

import javax.swing.JFrame;

//import DrawingSurface;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class RecursionPractice {

	private static int iterations;

	public static int triangleNumber(int n) {
		if (n == 1)
			return 1;
		else
			return n + triangleNumber(n-1);
	}


	public static int squareNumber(int n) {
		if (n == 1) {
			return 1;
		} else {
			return squareNumber(n-1) + 2*n - 1;
		}
	}


	public static int logBase2(int n) {
		if (n == 1) {
			return 0;
		} else {
			return 1 + logBase2(n/2);
		}
	}


	public static int pow(int n) {
		if (n == 0) {
			return 1;
		} else {
			return 2 * pow(n-1);
		}
	}


	public static int pentagonalNumber(int n) {
		if (n == 1) {
			return 1;
		} else {
			return (n-1)*3 + 1 + pentagonalNumber(n-1);
		}
	}

	public static int fibonacciRecursive(int n) {
		iterations++;
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
		}
	}

	public static int fibonacciLoops(int n) {
		int fib = 1;
		int oneBefore = 1;
		int twoBefore = 0;
		for (int i = 2; i < n; i++) {
			twoBefore = oneBefore;
			oneBefore = fib;
			fib = oneBefore + twoBefore;
		}
		return fib;
	}

	public static void main(String[] args) {
		for (int n = 1; n < 10; n++) {
			System.out.println(n + ": triangle number is " + triangleNumber(n));
			System.out.println(n + ": square number is " + squareNumber(n));
			System.out.println(n + ": log base 2 number is " + logBase2(n));
			System.out.println(n + ": power is " + pow(n));
			System.out.println(n + ": pentagonal number is " + pentagonalNumber(n));
			System.out.println(n + ": fibonacci number is " + fibonacciRecursive(n));
			System.out.println(n + ": fibonacci number is " + fibonacciLoops(n));
			System.out.println("# of calls: " + iterations);
			System.out.println("---------\n");
		}
		
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + " disks...");
			Hanoi.solveHanoi(i);
			System.out.println(Hanoi.counts + " calls\n------\n");
			Hanoi.counts = 0;
		}
		
		initDrawing();
	}
	
	private static void initDrawing() {
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
	}


}