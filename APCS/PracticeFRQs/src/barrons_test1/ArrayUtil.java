package barrons_test1;

import java.util.Arrays;

// Problem 1 part A

public class ArrayUtil {
	
	private int[] arr;
	
	public ArrayUtil(int[] array) {
		arr = array;
	}
	
	public String toString() {
		return (Arrays.toString(arr));
	}
	
	/**
	 * Reverses elements of array arr.
	 * @pre arr.length > 0
	 * @post Elements of arr have been reversed
	 * @param arr
	 */
	public void reverseArray() {
		int j = 0;
		int k = arr.length-1;
		while (j < k) {
			int temp = arr[j];
			arr[j] = arr[k];
			arr[k] = temp;
			j++;
			k--;
		}
	}

}
