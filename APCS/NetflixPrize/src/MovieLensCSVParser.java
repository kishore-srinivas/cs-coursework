

public class MovieLensCSVParser {

	// generates Movie object from a line of movies.csv
	public Movie toMovie(String line) {
		String title = "";
		int year = 0;

		int id = Integer.parseInt(line.substring(0, line.indexOf(",")));

		if (line.indexOf("\"") < 0) {
			title = line.substring(line.indexOf(",") + 1, line.indexOf(",", line.indexOf(",") + 1));
		} else {
			int startQuote = line.indexOf("\"");
			int endQuote = line.indexOf("\"", startQuote + 1);
			while (line.charAt(endQuote + 1) == '\"') {
				endQuote = line.indexOf("\"", endQuote + 1);
			}
			title = line.substring(startQuote + 1, endQuote);
		}

		for (int i = line.indexOf(","); i < line.length() - 3; i++) {
			if (isNumber(line.charAt(i)) && isNumber(line.charAt(i + 1)) && isNumber(line.charAt(i + 2))
					&& isNumber(line.charAt(i + 3))) {
				year = Integer.parseInt(line.substring(i, i + 4));
			}
		}
		
		String genres = line.substring(line.lastIndexOf(","));
		String[] genreList = null;
		if (genres.length() > 0 && genres.indexOf("|") > 0) genreList = genres.split("|");
		else genreList = new String[] {genres};

		title = title.replace(year + "", "");
		title = title.replace("()", "");

		return new Movie(id, title, year, genreList);
	}

	// parses the links.csv file for links
	@SuppressWarnings("finally")
	public int[] getLinks(String line) {
		int a = 0, b = 0, c = 0;
		try {
			a = Integer.parseInt(line.substring(0, line.indexOf(",")));
			b = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.lastIndexOf(",")));
			c = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.length()));
		} catch (NumberFormatException e) {

		} finally {
			return new int[] {a, b, c};
		}
	}

	private boolean isNumber(char c) {
		return ((int) c >= 48 && (int) c <= 57);
	}

	// generates User object from line of tags.csv
	public User toUser(String line) {
		return new User(Integer.parseInt(line.split(",")[0]));
	}
	
	// adds tags to User from line of tags.csv
	public String[] getTags(String line) {
		String tag = "";
//		int id = Integer.parseInt(line.substring(0, line.indexOf(",")));
		int movie = Integer.parseInt(line.split(",")[1]);
		long timestamp = Long.parseLong(line.substring(line.lastIndexOf(",")+1, line.length()));

		if (line.indexOf("\"") < 0) {
			tag = line.substring(line.indexOf(",", line.indexOf(",")+1) + 1, line.lastIndexOf(","));
		} else {
			int startQuote = line.indexOf("\"");
			int endQuote = line.indexOf("\"", startQuote + 1);
			while (line.charAt(endQuote + 1) == '\"') {
				endQuote = line.indexOf("\"", endQuote + 1);
			}
			tag = line.substring(startQuote + 1, endQuote);
		}
		return new String[] {movie+"", tag, timestamp+""};
	}
	
	// generates Rating object from line of ratings.csv
	@SuppressWarnings("finally")
	public Rating toRating(String line) {
		int a = -1, b = -1;
		double c = -1;
		long d = -1;
		try {
			a = Integer.parseInt(line.substring(0, line.indexOf(",")));
			b = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(",", line.indexOf(",")+1)));
			c = Double.parseDouble(line.substring(line.indexOf(",", line.indexOf(",", line.indexOf(b)) + 1)+1, line.lastIndexOf(",")));
			d = Long.parseLong(line.substring(line.lastIndexOf(",")+1, line.length()));
		} catch (NumberFormatException e) {
		} finally {
			return new Rating(a, b, c, d);
		}
	}
}
