import java.util.ArrayList;
import java.util.Arrays;

public class MovieLensCSVTranslatorBugs {

	
	private ArrayList<String> getLinePieces(String line) {
		ArrayList<String> pieces = new ArrayList<String>();  // Holds comma separated pieces of the line
		boolean quoted = false;  // Keeps track of whether the current character is inside of quotes or not
		int start = 0;
		for (int i = 0; i < line.length(); i++) {  // For each character...
			char thisChar = line.charAt(i);
			if (thisChar == '"') {  // If we see a quote symbol
				quoted = !quoted;   // Then we're inside of quotes
			} else if (thisChar == ',' && !quoted) {  // If we see a comma and we're not inside of quotes
				pieces.add(line.substring(start,i));  // Add this chunk of data to the pieces list
				start = i+1;
			}
		}
		pieces.add(line.substring(start));
		
		return pieces;
	}
	
	
	public Movie parseMovie(String line) {
		ArrayList<String> pieces = getLinePieces(line);  // Get all the sections of the line separated by commas (but not in quotes)
		
		int id = Integer.parseInt(pieces.get(0));  // ID is the first piece of data
		
		String title = pieces.get(1);  // title is the first piece of data
		
		int yearStart = title.lastIndexOf("(");
		int year = 0;
		if (yearStart > -1)	year = Integer.parseInt(title.substring(yearStart+1, yearStart+5));  // Extract the year from inside parenthesis
		
		String[] genrePieces = pieces.get(2).split("\\|"); // Split up the genres by looking for |
		
		Movie m = new Movie(id, year, title, genrePieces);
		return m;
	}


	// HERE WE NEED
	// METHODS FOR TRANSLATING
	// RATINGS
	// TAGS
	// AND LINKS
	
	
}
