
import java.util.ArrayList;

public class Tester {
	
	public static void main (String[] args) {
		ArrayList<String> movieStrings = FileIO.readFile("ml-latest-small" + FileIO.DELIMITER + "movies.csv");
		movieStrings.remove(0);
		ArrayList<String> linkStrings = FileIO.readFile("ml-latest-small" + FileIO.DELIMITER + "links.csv");
		linkStrings.remove(0);
		ArrayList<String> ratingStrings = FileIO.readFile("ml-latest-small" + FileIO.DELIMITER + "ratings.csv");
		ratingStrings.remove(0);
		ArrayList<String> tagStrings = FileIO.readFile("ml-latest-small" + FileIO.DELIMITER + "tags.csv");
		tagStrings.remove(0);

		
		ArrayList<Movie> movieData = new ArrayList<Movie>();
		MovieLensCSVParser parser = new MovieLensCSVParser();
		for (String line : movieStrings) {
			movieData.add(parser.toMovie(line));
		}
		for (String line : linkStrings) {
			Movie toChange = null;
			int[] links = parser.getLinks(line);
			for (Movie m : movieData) {
				if (m.getId() == links[0]) toChange = m;
			}
			toChange.addIMDB(links[1]+"");
			toChange.addTMDB(links[2]+"");
		}		
//		for (Movie m : movieData) {
//			System.out.println(m.toString());
//		}
		
		ArrayList<Rating> ratingData = new ArrayList<Rating>();
		for (String line : ratingStrings) {
			ratingData.add(parser.toRating(line));
		}
		for (Rating r : ratingData) {
			try {
				Movie toChange = null;
				for (Movie m : movieData) {
					if (m.getId() == r.getMovie()) toChange = m;
				}
				toChange.addRating(r);
			} catch (NullPointerException e) {
				System.out.println("rating not added because movie not found");
			}
		}
//		for (Rating r : ratingData) {
//			System.out.println(r.toString());
//		}
		
		ArrayList<User> userData = new ArrayList<User>();
		for (String line : tagStrings) {
			User u = parser.toUser(line);
			if (userData.size() > 0 && userData.get(userData.size()-1).getId() == u.getId()) {
				u = userData.get(userData.size()-1);
			} else userData.add(u);
			u.addTags(parser.getTags(line));
		}
//		for (User u : userData) {
//			System.out.println(u.toString());
//		}
	}
}
