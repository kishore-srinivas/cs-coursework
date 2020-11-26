import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by:
	Modified on:

 */

public class Grid {

	private int[][] grid;
	private char direction;

	public Grid() {
		grid = new int[4][4];
		direction = ' ';
//		grid[0][0] = 2;
//		grid[1][1] = 2;
//		grid[2][2] = 2;
//		grid[3][3] = 2;
		getNewNumber();
		getNewNumber();
	}

	private void getNewNumber() {
		int[] starters = new int[] {2, 4};
		boolean done = false;
		int count = 0;
		while (!done && count < 10) {
			Random rand = new Random();
			int i = rand.nextInt(grid.length);
			int j = rand.nextInt(grid[0].length);
			if (grid[i][j] == 0) {
				grid[i][j] = starters[rand.nextInt(starters.length)];
				done = true;
			}
			count++;
		}
	}

	private String debug(int x, int y) {
		return("[" + x + "][" + y + "]");
	}

	private void combine(int x, int y) {
		if (this.direction == 'u') {
			if (y > 0) {
				grid[x][y-1] += grid[x][y];
				grid[x][y] = 0;
			}
		}
		if (this.direction == 'r') {
			if (x < grid.length-1) {
				grid[x+1][y] += grid[x][y];
				grid[x][y] = 0;
			}
		}
		if (this.direction == 'd') {
			if (y < grid[0].length-1) {
				grid[x][y+1] += grid[x][y];
				grid[x][y] = 0;
			}
		}
		if (this.direction == 'l') {
			if (x > 0) {
				grid[x-1][y] += grid[x][y];
				grid[x][y] = 0;
			}
		}
	}

	private void shift(int x, int y) {
		int num = numSpacesAvailable(x, y);
//		if (this.direction == 'u') {
//				grid[x][y-1] = grid[x][y];
//				grid[x][y] = 0;
//		}
		
		if (num > 0) {
			if (this.direction == 'u') {
				grid[x][y-num] = grid[x][y];
				grid[x][y] = 0;
			}
			if (this.direction == 'r') {
				grid[x+num-1][y] = grid[x][y];
				grid[x][y] = 0;
			}
			if (this.direction == 'd') {
				grid[x][y+num-1] = grid[x][y];
				grid[x][y] = 0;
			}
			if (this.direction == 'l') {
				grid[x-num][y] = grid[x][y];
				grid[x][y] = 0;
			}
		}
	}

	public void onClick() {
		this.onClick(' ');
	}

	public void onClick(char direction) {
		this.direction = direction;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
//				shift(x, y);
				while (canCombine(x, y)) {
					combine(x, y);
				}
				shift(x, y);
			}
		}

		/*if (this.direction == 'u') {
			for (int x = 0; x < grid.length; x++) {
				for (int y = 1; y < grid[0].length; y++) {
					if (canCombine(x, y)) {
						grid[x][y - numSpacesAvailable(x, y)] += grid[x][y];
						grid[x][y] = 0;
						System.out.println(debug(x, y) + " moved to " + debug(x, y-1));
						y--;
					}
				}
			}
		}
		if (this.direction == 'r') {
			for (int x = 0; x < grid.length - 1; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if (canCombine(x, y)) {
						grid[x + 1][y] += grid[x][y];
						grid[x][y] = 0;
						System.out.println(debug(x, y) + " moved to " + debug(x+1, y));
					}
				}
			}
		}
		if (this.direction == 'd') {
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if (canCombine(x, y)) {
						if (numSpacesAvailable(x, y) > 0) {
							grid[x][y + numSpacesAvailable(x, y)] += grid[x][y];
							grid[x][y] = 0;
						} else {
							System.out.println(debug(x, y) + ": " + numSpacesAvailable(x, y));
						}
					}
				}
			}
		}
		if (this.direction == 'l') {
			for (int x = 1; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if (canCombine(x, y)) {
						grid[x - numSpacesAvailable(x, y)][y] += grid[x][y];
						grid[x][y] = 0;
						System.out.println(debug(x, y) + " moved to " + debug(x-1, y));
					}
				}
			}
		}*/

//		getNewNumber();
	}

	public boolean canCombine(int x, int y) {
		if (grid[x][y] == 0) {
			return false;
		}
		if (direction == 'l') {
			if (x != 0) {
				return (grid[x - 1][y] == grid[x][y]) || (grid[x - 1][y] == 0);
			}
			return false;
		}
		if (direction == 'd') {
			if (y != grid.length - 1) {
				return (grid[x][y + 1] == grid[x][y]) || (grid[x][y + 1] == 0);
			}
			return false;
		}
		if (direction == 'r') {
			if (x != grid[0].length - 1) {
				return (grid[x + 1][y] == grid[x][y]) || (grid[x + 1][y] == 0);
			}
			return false;
		}
		if (direction == 'u') {
			if (y != 0) {
				return (grid[x][y - 1] == grid[x][y]) || (grid[x][y - 1] == 0);
			}
			return false;
		}
		return false;
	}

	public int numSpacesAvailable(int x, int y) {
		int numSpaces = 0;
		if (direction == 'l') {
			if (x == 0 || grid[x - 1][y] != 0) {
				numSpaces = 0;
			} else {
				for (int i = x; i > 0; i--) {
					if (grid[x - i][y] == 0) {
						numSpaces++;
					}
				}
			}
		}
		if (direction == 'd') {
			if (y == grid[0].length-1 || grid[x][y + 1] != 0) {
				numSpaces = 0;
			} else {
				for (int i = 0; i < grid[0].length - y; i++) {
					if (grid[x][y + i] == 0) {
						numSpaces++;
					}
				}
			}
		}
		if (direction == 'r') {
			if (x == grid.length-1 || grid[x + 1][y] != 0) {
				numSpaces = 0;
			} else {
				for (int i = x; i < grid.length - x; i++) {
					if (grid[x + i][y] == 0) {
						numSpaces++;
					}
				}
			}
		}
		if (direction == 'u') {
			if (y == 0 || grid[x][y - 1] != 0) {
				numSpaces = 0;
			} else {
				for (int i = y; i > 0; i--) {
					if (grid[x][y - i] == 0) {
						numSpaces++;
					}
				}
			}
		}
		return numSpaces;
	}

	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param marker
	 *            The PApplet used for drawing.
	 * @param x
	 *            The x pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param y
	 *            The y pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param width
	 *            The pixel width of the grid drawing.
	 * @param height
	 *            The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		marker.pushStyle();
		float cellWidth = width / grid.length;
		float cellHeight = height / grid[0].length;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				marker.noFill();
				marker.textAlign(marker.CENTER, marker.CENTER);
				marker.textSize(cellWidth / 2);
				marker.rect(j * cellWidth + x, i * cellHeight + y, cellWidth, cellHeight);
				if (grid[j][i] != 0) {
					marker.text(grid[j][i], j * cellWidth + x + cellWidth / 2, i * cellHeight + y + cellHeight / 2);
				}
			}
		}
		marker.popStyle();
	}
}