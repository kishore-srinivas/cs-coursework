import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by:
	Modified on:

*/

public class Life {
	
	private boolean[][] grid;
	private boolean[][] changes;

	// Constructs an empty grid
	public Life() {
		grid = new boolean[20][20];
		changes = new boolean[grid.length][grid[0].length];
	}

	// Constructs the grid defined in the file specified
	public Life(String filename) {
		this();
		readData(filename, grid);
	}

	// Runs a single turn of the Game Of Life
	public void step() {
		this.step(1);
	}

	// Runs n turns of the Game Of Life
	public void step(int n) {
		int counter = 0;
		while (counter < n) {
			for (int i = 0; i < grid[0].length; i++) {
				for (int j = 0; j < grid.length; j++) {
					int neighbors = numLivingNeighbors(j, i);
					if ((neighbors == 3 && !grid[j][i]) || ((neighbors <= 1 || neighbors >= 4) && grid[j][i])) {
						changes[j][i] = true;
					}
				}
			}
			changeStatus();
			counter++;
		}
	}
	
	public int numLivingNeighbors(int j, int i) {
		int neighbors = 0;
		if (!(j == 0 || j == grid.length - 1 || i == 0 || i == grid[0].length - 1)) {
			if (grid[j+1][i] == true) { // east
				neighbors++;
			}
			if (grid[j+1][i-1] == true) { // northeast
				neighbors++;
			}
			if (grid[j][i-1] == true) { // north
				neighbors++;
			}
			if (grid[j-1][i-1] == true) { // northwest
				neighbors++;
			}
			if (grid[j-1][i] == true) { // west
				neighbors++;
			}
			if (grid[j-1][i+1] == true) { // southwest
				neighbors++;
			}
			if (grid[j][i+1] == true) { // south
				neighbors++;
			}
			if (grid[j+1][i+1] == true) { // southeast
				neighbors++;
			}
		}
		if (j == 0) {
			if (i == 0) {
				if (grid[j+1][i] == true) { // east
					neighbors++;
				}
				if (grid[j][i+1] == true) { // south
					neighbors++;
				}
				if (grid[j+1][i+1] == true) { // southeast
					neighbors++;
				}
			} else if (i == grid[0].length - 1) {
				if (grid[j+1][i] == true) { // east
					neighbors++;
				}
				if (grid[j+1][i-1] == true) { // northeast
					neighbors++;
				}
				if (grid[j][i-1] == true) { // north
					neighbors++;
				}				
			} else {
				if (grid[j+1][i] == true) { // east
					neighbors++;
				}
				if (grid[j+1][i-1] == true) { // northeast
					neighbors++;
				}
				if (grid[j][i-1] == true) { // north
					neighbors++;
				}
				if (grid[j][i+1] == true) { // south
					neighbors++;
				}
				if (grid[j+1][i+1] == true) { // southeast
					neighbors++;
				}
			}
		}
		if (j == grid.length - 1) {
			if (i == 0) {
				if (grid[j-1][i] == true) { // west
					neighbors++;
				}
				if (grid[j-1][i+1] == true) { // southwest
					neighbors++;
				}
				if (grid[j][i+1] == true) { // south
					neighbors++;
				}
			} else if (i == grid[0].length - 1) {
				if (grid[j][i-1] == true) { // north
					neighbors++;
				}
				if (grid[j-1][i-1] == true) { // northwest
					neighbors++;
				}
				if (grid[j-1][i] == true) { // west
					neighbors++;
				}
			} else {
				if (grid[j][i-1] == true) { // north
					neighbors++;
				}
				if (grid[j-1][i-1] == true) { // northwest
					neighbors++;
				}
				if (grid[j-1][i] == true) { // west
					neighbors++;
				}
				if (grid[j-1][i+1] == true) { // southwest
					neighbors++;
				}
				if (grid[j][i+1] == true) { // south
					neighbors++;
				}
			}
		}
		return neighbors;		
	}
	
	public void changeStatus() {
		for (int i = 0; i < changes[0].length; i++) {
			for (int j = 0; j < changes.length; j++) {
				if (changes[j][i]) {
					grid[j][i] = !grid[j][i];
				}
				changes[j][i] = false;
			}
		}
	}
	
	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String str = "";
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i] == true) {
					str += "+";
				} else {
					str += "-";
				}
				str += " ";
			}
			str += "\n";
		}
		return str;
	}

	// Reads in array data from a simple text file containing asterisks (*)
	public void readData (String filename, boolean[][] gameData) {
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
						for(int i = 0; i < line.length(); i++)
							if (i < gameData.length && count < gameData[i].length && line.charAt(i)=='*')
								gameData[i][count] = true;

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
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		marker.pushStyle();
		float cellWidth = width / grid.length;
		float cellHeight = height / grid[0].length;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i] == true) {
					marker.fill(0);
				} else {
					marker.fill(255);
				}
				marker.rect(j*cellWidth+x, i*cellHeight+y, cellWidth, cellHeight);
			}
		}
		marker.popStyle();
	}
	
	/**
	 * Optionally, complete this method to determine which element of the grid matches with a
	 * particular pixel coordinate.
	 * 
	 * @param p A Point object representing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
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
	
	/**
	 * Optionally, complete this method to toggle a cell in the game of life grid
	 * between alive and dead.
	 * 
	 * @param i The x coordinate of the cell in the grid.
	 * @param j The y coordinate of the cell in the grid.
	 */
	public void toggleCell(int i, int j) {
		grid[i][j] = !grid[i][j];
	}

	
	
}