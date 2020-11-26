package barrons_test1;

import java.util.Arrays;

// Problem 1 parts B and C

public class Matrix {
	
	private int[][] matrix;
	
	public Matrix(int [][] m) {
		matrix = m;		
	}
	
	public int getLength() {
		return matrix.length;
	}
	
	public void populate() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = i * (matrix.length + 2) + j;
			}
		}
	}
	
	public String toString() {
		String str = "[";
		for (int i = 0; i < matrix.length-1; i++) {
			str += Arrays.toString(matrix[i]) + ", ";
		}
		str += Arrays.toString(matrix[matrix.length-1]) + "]";
		return str;
	}
	
	/**
	 * Reverses the elements of each row of the matrix
	 * @post Elements of each row have been reversed
	 */
	public void reverseAllRows() {
		for (int i = 0; i < matrix.length; i++) {
			reverseArray(matrix[i]);
		}
	}
	
	/**
	 * Reverses elements of array arr.
	 * @pre arr.length > 0
	 * @post Elements of arr have been reversed
	 * @param arr
	 */
	public void reverseArray(int[] arr) {
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
	
	/**
	 * Reverses the elements of the matrix
	 * @post
	 * 	- final elements of the matrix, when read in row-major order, are the same as the original elements of the matrix when read from the bottom corner, right to left, going upward
	 * 	- mat[0][0] contains what was originally the last element
	 * 	- mat[mat.length-1][mat.length-1] contains what was originally the first element
	 */
	public void reverseMatrix() {
		reverseAllRows();
		int j = 0;
		int k = matrix.length - 1;
		while (j < k) {
			int[] temp = matrix[j];
			matrix[j] = matrix[k];
			matrix[k] = temp;
			j++;
			k--;
		}
	}

}
