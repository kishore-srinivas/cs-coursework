package barrons_test1;

import java.util.Arrays;

public class Tester1 {
	
	public static void main(String args[]) {
		
		// 1a
		ArrayUtil arrayUtil = new ArrayUtil(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
		System.out.println(arrayUtil.toString());
		arrayUtil.reverseArray();
		System.out.println(arrayUtil.toString());
		System.out.println("==========================\n");
		
		// 1b and 1c
		Matrix mat = new Matrix(new int[3][5]);
		System.out.println(mat.getLength());
		mat.populate();
		System.out.println(mat.toString());
		mat.reverseAllRows();
		System.out.println(mat.toString());
		mat.reverseAllRows();
		mat.reverseMatrix();
		System.out.println(mat.toString());
		System.out.println("==========================\n");
		
		// 2a and 2b and 2c
		Sentence sent = new Sentence("abcd efgh ijkl mnop qrst uvwx yz");
		System.out.println(sent.toString());
		System.out.println(sent.getBlankPositions());
		System.out.println(sent.countWords());
		System.out.println(Arrays.toString(sent.getWords()));
		System.out.println("==========================\n");
		
		// 3a and 3b
		Tournament tourney = new Tournament();
		System.out.println("" + tourney.requestSlot("abcd"));
		System.out.println("" + tourney.cancelAndReassignSlot(new Player("13bea", 78)).getPlayerName());
		
	}

}
