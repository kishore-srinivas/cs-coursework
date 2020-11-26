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

public class EraseObject {

	private char[][] grid;

	// Constructs an empty grid
	public EraseObject() {
		grid = new char[20][20];
	}

	// Constructs the grid defined in the file specified
	public EraseObject(String filename) {
		this();
		readData(filename, grid);
	}
	
	public void eraseObject(int x, int y) {
		if (grid[x][y] == '*') {
			grid[x][y] = ' ';
			if (x < grid.length-1 && y < grid.length) eraseObject(x+1, y);
			if (x < grid.length && y < grid.length-1) eraseObject(x, y+1);
			if (x > 0 && y < grid.length) eraseObject(x-1, y);
			if (x < grid.length && y > 0) eraseObject(x, y-1);	
		} else if (grid[x][y] == ' ') {
			
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
				if (grid[j][i] == '*') {
					marker.fill(0);
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