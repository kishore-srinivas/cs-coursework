import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class NetFlixPredictor {

	private ArrayList<String> movieStrings;
	private ArrayList<String> linkStrings;
	private ArrayList<String> ratingStrings;
	private ArrayList<String> tagStrings;
	
	private ArrayList<Movie> movieData;
	private ArrayList<Rating> ratingData;
	private ArrayList<User> userData;
	
	private double avgMovie, avgUser;

	/**
	 * 
	 * Use the file names to read all data into some local structures. 
	 * 
	 * @param movieFilePath The full path to the movies database.
	 * @param ratingFilePath The full path to the ratings database.
	 * @param tagFilePath The full path to the tags database.
	 * @param linkFilePath The full path to the links database.
	 */
	public NetFlixPredictor(String movieFilePath, String ratingFilePath, String tagFilePath, String linkFilePath) {
		movieStrings = FileIO.readFile(movieFilePath);
		movieStrings.remove(0);
		linkStrings = FileIO.readFile(linkFilePath);
		linkStrings.remove(0);
		ratingStrings = FileIO.readFile(ratingFilePath);
		ratingStrings.remove(0);
		tagStrings = FileIO.readFile(tagFilePath);
		tagStrings.remove(0);
		
		movieData = new ArrayList<Movie>();
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
		
		ratingData = new ArrayList<Rating>();
		userData = new ArrayList<User>();
		for (String line : ratingStrings) {
			ratingData.add(parser.toRating(line));
		}
		System.out.println("adding ratings...");
		for (Rating r : ratingData) {
			try {
				Movie toChange = null;
				for (Movie m : movieData) {
					if (m.getId() == r.getMovie()) {
						toChange = m;
						break;
					}
				}
				toChange.addRating(r);				
			} catch (NullPointerException e) {
				System.out.println("rating not added because movie not found");
			}
		}
		System.out.println("done");
		
		for (String line : tagStrings) {
			User u = parser.toUser(line);
			int indexOfUser = findUserIndex(u.getId(), userData);
			if (userData.size() > 0 && indexOfUser > 0) {
				u = userData.get(indexOfUser);
			} else userData.add(u);
			u.addTags(parser.getTags(line));
		}
		for (String line : ratingStrings) {
			User u = parser.toUser(line);
			int indexOfUser = findUserIndex(u.getId(), userData);
			if (userData.size() > 0 && indexOfUser > 0) {				
				u = userData.get(indexOfUser);
			} else userData.add(u);
			Rating r = parser.toRating(line);
			u.addRating(r);
			try {
				if (r.getRating() > 3.5) {
					u.addLikedGenres(movieData.get(findMovieIndex(r.getMovie(), movieData)).getGenres());
					u.addLikedYear(Integer.parseInt((movieData.get(findMovieIndex(r.getMovie(), movieData)).getYear())));
				}
			} catch (NumberFormatException e) {}
			
		}
		for (Rating r : ratingData) 
			for (User u : userData) 
				if (r.getUser() == u.getId()) {
					u.addRating(r);
					break;
				}
		
		Collections.sort(userData);
		Collections.sort(movieData);
		Collections.sort(ratingData);
		
		double movieSum2 = 0, userSum2 = 0;
		for (Movie m : movieData) {
			movieSum2 += m.getAverageRating();
		}
		for (User u : userData) {
			userSum2 += u.getAverageRating();
		}
		avgMovie = movieSum2/movieData.size();
		avgUser = userSum2/userData.size();
	}
	
	private int findUserIndex(int id, ArrayList<User> users) {
		return Collections.binarySearch(users, new User(id));
	}
	
	protected int findMovieIndex(int id, ArrayList<Movie> movies) {
		return Collections.binarySearch(movies, new Movie(id));
	}
	
	private Movie findMovieByIndex(int id) {
		for (Movie m : movieData) {
			if (m.getId() == id) return m;
		}
		return null;
	}
	
	private ArrayList<Integer> getMovieIds() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Movie m : movieData) {
			ids.add(m.getId());
		}
		return ids;
	}
		
	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, return -1.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or -1 if the user does not exist in the database, the movie does not exist, or the movie has not been rated by this user.
	 */
	public double getRating(int userID, int movieID) {		
		int i = Collections.binarySearch(ratingData, new Rating(userID, movieID));
		if (i >= 0) {
			return ratingData.get(i).getRating();
		}
		return -1;
	}
	
	/**
	 * If userNumber has rated movieNumber, return the rating. Otherwise, use other available data to guess what this user would rate the movie.
	 * 
	 * @param userNumber The ID of the user.
	 * @param movieNumber The ID of the movie.
	 * @return The rating that userNumber gave movieNumber, or the best guess if the movie has not been rated by this user.
	 * @pre A user with id userID and a movie with id movieID exist in the database.
	 */
	public double guessRating(int userID, int movieID) {
		// if user has already rated the movie return that rating
		double existingRating = getRating(userID, movieID);
		if (existingRating != -1) {
			return existingRating;
		}
		
		User user = null;
		Movie movie = null;
		int userIndex = findUserIndex(userID, userData);
		if (userIndex > 0) user = userData.get(userIndex);
		int movieIndex = findMovieIndex(movieID, movieData);
		if (movieIndex > 0) movie = movieData.get(movieIndex);
		
		if (user == null || movie == null) return 3.71;

		// if user has not rated any movies or movie has no ratings return median rating
		try {
			if (user.getNumRatings() < 1 || movie.getNumRatings() < 1)
				return 2.5;
		} catch (NullPointerException e) {}
		
		double userWeight = user.getAverageRating() - avgUser;
		double movieWeight = movie.getAverageRating() - avgMovie;
				
		if (Math.abs(movie.getAverageRating() - user.getAverageRating()) < 1) {
			return (movie.getAverageRating() + user.getAverageRating()) / 2.0;
		}
		
		double genreWeight = 0;
		double yearWeight = -1;
		
		for (String genre : movie.getGenres().replace(",", "").split("\\|")) {
			if ((user.getLikedGenres().contains(genre))) genreWeight += 0.1;
		}
		try {
			yearWeight = user.rankYear(Integer.parseInt(movie.getYear()));
//			System.out.println(Integer.parseInt(movie.getYear()) + ": [" + avgSimilarUser + " + " + avgSimilarMovie + " + " + genreWeight + " + " + yearWeight + " + " + userWeight + " + " + movieWeight + "] ");
			return 2.25 + genreWeight*1.75 + yearWeight + userWeight*0.6 + movieWeight*0.55;
//			return 2.25 + userWeight + movieWeight + genreWeight;
//			return (avgSimilarUser*1.7+avgSimilarMovie*0.3)/2.0 + userWeight + movieWeight;// + genreWeight;
//			return avgSimilarUser;
		} catch (NumberFormatException e) {}
		
		
		return 3.8;
	}
	
	/**
	 * Recommend a movie that you think this user would enjoy (but they have not currently rated it). 
	 * 
	 * @param userNumber The ID of the user.
	 * @return The ID of a movie that data suggests this user would rate highly (but they haven't rated it currently).
	 * @pre A user with id userID exists in the database.
	 */
	public int recommendMovie(int userID) {
		int best = 0;
		double maxRating = 0;
		for (Movie m : movieData) {
			double guessed = guessRating(userID, m.getId());
			double existingRating = getRating(userID, m.getId());
			if (existingRating != -1 && guessed > maxRating) {
				maxRating = guessed;
				best = m.getId();
				break;
			}
		}
		return best;
	}
	
	public ArrayList<Movie> getTopMovies(int userID) {
		ArrayList<Integer> topMovies = new ArrayList<Integer>();
		topMovies.add(movieData.get(0).getId());
		for (Movie m : movieData) {
			double guessed = guessRating(userID, m.getId());
			double existingRating = getRating(userID, m.getId());
			if (existingRating != -1 && guessed > guessRating(userID, topMovies.get(0))) {
				topMovies.add(0, m.getId());
				break;
			}
		}
		ArrayList<Movie> top = new ArrayList<Movie>();
		if (topMovies.size() > 6) {
			top.add(findMovieByIndex(topMovies.get(0)));
			top.add(findMovieByIndex(topMovies.get(1)));
			top.add(findMovieByIndex(topMovies.get(2)));
			top.add(findMovieByIndex(topMovies.get(3)));
			top.add(findMovieByIndex(topMovies.get(4)));
			top.add(findMovieByIndex(topMovies.get(5)));
		} else {
			for (int i : topMovies) {
				top.add(new Movie(i));
			}
		}
		return top;
	}
	
	public ArrayList<Movie> getMovies() {
		return movieData;
	}
	
}
