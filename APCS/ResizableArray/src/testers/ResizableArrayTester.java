package testers;

import java.util.ArrayList;

import resizableArray.ResizableArray;

/**
 * ResizableArrayTester.java
 *
 *
 *
 */

public class ResizableArrayTester {

	private ArrayList<Integer> tester;
	private long startMem = 0;
	private long startTime = 0;


    public static void main(String[] args) {
        ResizableArrayTester worker = new ResizableArrayTester();

        worker.setUp();

    	worker.tester = new ArrayList<Integer>();
    	
    	// Switch between these method calls to run different tests.
    	// Only run 1 test at a time (so that memory and timing data is as accurate as possible).
    	
//    	worker.runFirstWaveFunctionalTest();
//    	worker.runFunctionalTest();
    	worker.runEfficiencyTest(100000);

    	worker.runDown();

    }

    /*
    
    // STEP 1: This tests basic ResizableArray methods: add, remove, size, and toString.
    // Correct output is in the comments under each println()

	public void runFirstWaveFunctionalTest() {
		tester.add(1);
		tester.add(2);
		tester.add(3);
		System.out.println(tester + " size = " + tester.size());
		// [1, 2, 3] size = 3
		tester.add(4);
		tester.add(5);
		tester.add(6);
		System.out.println(tester + " size = " + tester.size());
		// [1, 2, 3, 4, 5, 6] size = 6
		tester.remove(1);
		tester.remove(4);
		tester.add(7);
		System.out.println(tester + " size = " + tester.size());
		// [1, 3, 4, 5, 7] size = 5
	}



	// STEP 2: UNCOMMENT THE FOLLOWING TO DO A FULL FUNCTIONALITY TEST
	// This tests most required ResizableArray methods. Additional tests are highly recommended.
	

	public void runFunctionalTest () {

		// ADD, INSERT, REMOVE, GET, SET TEST
		tester.add(1);
		tester.add(3);
		tester.add(5);
		tester.add(7);
		tester.add(9);
		tester.add(11);
		tester.add(13);
		tester.insert(0,0);
		tester.insert(2,2);
		tester.insert(4,4);
		tester.insert(6,6);
		tester.insert(8,8);
		tester.insert(10,10);
		tester.insert(12,12);
		int val = tester.get(1);
		tester.set(0,val);
		tester.remove(4);
		tester.remove(5);
		tester.remove(5);
		tester.remove(6);
		tester.remove(6);
		tester.remove(6);
		tester.remove(6);

		System.out.println("tester: " + tester.toString());
		// [1, 1, 2, 3, 5, 8, 13]

		// EQUALS TEST
		ResizableArray other = new ResizableArray();
		other.add(1);
		other.add(1);
		other.add(2);
		other.add(3);
		other.add(5);
		other.add(8);
		other.add(13);

		System.out.println(tester.equals(other));
		// true
		other.remove(other.size()-1);
		other.add(14);
		System.out.println(tester.equals(other));
		// false


		// BAD METHOD CALLS TEST
		try {
			other.insert(10,5);
			System.out.println("IF YOU SEE THIS, THERE IS A PROBLEM.");
		} catch(Exception e) {
			System.out.println("Caught exception from a bad insert (this is a good thing). Message: " + e.getMessage());
		}
		try {
			other.remove(10);
			System.out.println("IF YOU SEE THIS, THERE IS A PROBLEM.");
		} catch(Exception e) {
			System.out.println("Caught exception from a bad remove (this is also a good thing). Message: " + e.getMessage());
		}


		// SORT TEST
		ResizableArray otherSort = new ResizableArray();

		otherSort.add(13);
		otherSort.add(8);
		otherSort.add(5);
		otherSort.add(3);
		otherSort.add(2);
		otherSort.add(1);
		otherSort.add(1);
		otherSort.sort();
		System.out.println(otherSort);
		// [1, 1, 2, 3, 5, 8, 13]
		System.out.println(tester.equals(otherSort));
		// true

	}*/
	

	// STEP 3: UNCOMMENT THE FOLLOWING TO DO EFFICIENCY TESTING
	// This code tests the efficiency of the ResizableArray by performing a large number of adds, inserts, and removes.
	
	
	public void runEfficiencyTest (int num) {
		runAddTest(num, false);
		runInsertTest(num, false);
		runRemoveTest(num, false);
	}

	public void runAddTest (int num, boolean random) {

		if (random) {
			for (int i = 0; i < num; i++)
				tester.add((int)(Math.random() * 1000));
		} else {
			for (int i = 0; i < num; i++)
				tester.add(i);
		}

	}


	public void runInsertTest (int num, boolean random) {
		int size = tester.size();

		if (random) {
			for (int i = 0; i < num; i++)
				tester.add((int)(Math.random() * (size+i)),(int)(Math.random() * 1000));
		} else {
			for (int i = num-1; i >= 0; i--)
				tester.add(tester.size(),i);
		}

	}


	public void runRemoveTest (int num, boolean random) {
		int size = tester.size();

		if (random) {
			for (int i = 0; i < num; i++)
				tester.remove((int)(Math.random() * (size-i)));
		} else {
			for (int i = 0; i < num; i++)
				tester.remove(tester.size()-1);
		}

	}
	

	public void setUp() {
		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();
		startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		startTime = System.currentTimeMillis();
	}

	public void runDown () {
		System.out.println("The test took " + (System.currentTimeMillis() - startTime) + " milliseconds.");

		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();
		System.gc(); System.gc(); System.gc(); System.gc();

		long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - startMem;

		System.out.println("And " + usage + " bytes of memory.");

		tester = null;
		System.gc();
	}






}
