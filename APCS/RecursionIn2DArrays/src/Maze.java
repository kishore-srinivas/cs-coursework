import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by:
	Modified on:

*/

public class Maze {

	private char[][] grid;

	// Constructs an empty grid
	public Maze() {
		grid = new char[20][20];
	}

	// Constructs the grid defined in the file specified
	public Maze(String filename) {
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
	
	private boolean solve(int x, int y) {
		if (grid[x][y] == '#') return false;
		if (grid[x][y] == 'X') return true;
		if (grid[x][y] == '!') return false;
		else {
			grid[x][y] = '!';
			if (y > 0 && solve(x, y-1) == true) {				
				return true;
			}
			else if (x < grid.length-1 && solve(x+1, y) == true) {
				return true;
			}
			else if (y < grid.length-1 && solve(x, y+1) == true) {
				return true;
			}
			else if (x > 0 && solve(x-1, y) == true) {
				return true;
			} else {
				grid[x][y] = '.';
				return false;
			}
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
				} else {
					marker.fill(255);
				}
				marker.rect(i * cellHeight + y, j * cellWidth + x, cellWidth, cellHeight);
			}
		}
		marker.popStyle();
	}

	/**
	 * Optionally, complete this method to determine which element of the grid
	 * matches with a particular pixel coordinate.
	 * 
	 * @param p
	 *            A Point object representing a graphical pixel coordinate.
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
	 * @return A Point object representing a coordinate within the game of life
	 *         grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		Point click;
		float cellWidth = width / grid.length;
		float cellHeight = height / grid[0].length;
		int spotX = (int) ((p.getX() - x) / cellWidth);
		int spotY = (int) ((p.getY() - y) / cellHeight);
		if (spotX >= 0 && spotX < grid.length && spotY >= 0 && spotY < grid[0].length) {
			click = new Point(spotX, spotY);
			return click;
		}
		return null;
	}

}