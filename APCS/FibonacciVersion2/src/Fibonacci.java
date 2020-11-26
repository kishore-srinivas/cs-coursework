
/**
 * @(#)Fibonacci.java
 *
 *
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibonacci {

	// 20th Fibonacci number is 6765
	public static long computeFibonacci(int x) {
		if (x < 0) {
			throw new IllegalArgumentException("not a positive number");
		}
		if (x <= 1) {
			return x;
		} else {
			long answer = computeFibonacci(x - 2) + computeFibonacci(x - 1);
			return answer;
		}

	}

	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.print("Which fibonacci number would you like to find? --> ");
		int x = 0;
		try {
			x = kboard.nextInt();
			long answer = computeFibonacci(x);
			System.out.println("The " + x + " fibonacci number is " + answer + ".");
			kboard.close();
		} catch (IllegalArgumentException input) {
			System.out.println("Please try again...");
			main(args);
		} catch (InputMismatchException input) {
			System.out.println("Please try again...");
			main(args);
		} catch (StackOverflowError e) {
			System.out.println("overflowed");
		}
	}
}