
public class Hanoi {
	
	private static int sum = 6;
	protected static int counts = 0;
	
	public static void solveHanoi(int size) {
		solveHanoi(size, 1, 3);
	}
	
	private static void solveHanoi(int size, int start, int goal) {
		counts++;
		if (size == 1) {
			System.out.println("Move top disk from peg " + start + " to peg " + goal);
		} else {
			solveHanoi(size-1, start, sum - start - goal);
			System.out.println("Move top disk from peg " + start + " to peg " + goal);
			solveHanoi(size-1, sum - start - goal, goal);
		}
	}

}
