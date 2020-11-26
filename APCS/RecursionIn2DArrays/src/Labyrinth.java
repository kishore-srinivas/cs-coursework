import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public class Labyrinth {
	private char[][] grid;
	private boolean invisible;

	// Constructs an empty grid
	public Labyrinth() {
		grid = new char[20][20];
		invisible = false;
	}

	// Constructs the grid defined in the file specified
	public Labyrinth(String filename) {
		this();
		readData(filename, grid);
	}
	
	public void solve() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if (grid[x][y] == 'C') {
					System.out.println(solve(x, y));
				}
			}
		}
	}
	
	private int solve(int x, int y) {
		if (grid[x][y] == '@') invisible = true;
		if (grid[x][y] == '#') return -1;
		if (grid[x][y] == 'X') return 0;
		if (grid[x][y] == '!' && invisible) return -1;
		if (grid[x][y] == 'A' && !invisible) return -1;
		else {
			grid[x][y] = '!';
			char temp = grid[x][y];
			int up = 0, down = 0, right = 0, left = 0;
			if (grid[x][y-1] != '!') up = solve(x, y-1);
			if (grid[x][y+1] != '!') down = solve(x, y+1);
			if (grid[x+1][y] != '!') right = solve(x+1, y);
			if (grid[x-1][y] != '!') left = solve(x-1, y);
			return findMin(new int[] {up, down, right, left});
		}
	}
	
	private int findMin(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length-1; j++) {
				if (numbers[j] > numbers[j+1]) {
					this.swap(j, j+1, numbers);
				}
			}
		}
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + ", ");
		}
		System.out.println();
		return numbers[0];
	}
	
	private void swap(int index1, int index2, int[] numbers) {
		if (index1 < numbers.length && index2 < numbers.length) {
			int temp = numbers[index1];
			numbers[index1] = numbers[index2];
			numbers[index2] = temp;
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}
	

	// Formats this Life grid as a String to be printed (one call to this method
	// returns the whole multi-line grid)
	public String toString() {
		String str = "";
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				str += grid[i][j];
			}
			str += "\n";
		}
		return str;
	}

	// Reads in array data from a simple text file containing asterisks (*)
	public void readData(String filename, char[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for (int i = 0; i < line.length(); i++)
						if (i < gameData.length && count < gameData[i].length)
							gameData[count][i] = line.charAt(i);

					count++;
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
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
				if (grid[j][i] == '.') {
					marker.fill(0);
				} else if (grid[j][i] == '#') {
					marker.fill(255);
				} else if (grid[j][i] == 'C') {
					marker.fill(0, 255, 0);
				} else if (grid[j][i] == 'X') {
					marker.fill(255, 0, 0);
				} else if (grid[j][i] == '!') {
					marker.fill(0, 0, 255);
				} else if (grid[j][i] == 'A') {
					marker.fill(255, 0, 255);
				} else if (grid[j][i] == '@') {
					marker.fill(255, 255, 0);
				} else {
					marker.fill(255);
				}
				marker.rect(i * cellHeight + y, j * cellWidth + x, cellWidth, cellHeight);
			}
		}
		marker.popStyle();
	}
}
