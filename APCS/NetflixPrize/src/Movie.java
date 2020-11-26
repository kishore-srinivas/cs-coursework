
import java.util.ArrayList;

public class Movie implements Comparable<Movie>{

	private int movieId, year;
	private String title, imdbId, tmdbId;
	private ArrayList<String> tags;
	private String[] genres;
	private ArrayList<Rating> ratings;
	private double averageRating;
	
	public Movie(int id, String title, int year, String[] genres) {
		this.movieId = id;
		this.year = year;
		this.title = title;
		this.averageRating = 0;
		tags = new ArrayList<String>();
		ratings = new ArrayList<Rating>();
		this.genres = genres;
	}
	
	public Movie(int id) {
		this.movieId = id;
	}

	public void addIMDB(String imdb) {
		this.imdbId = imdb;
	}
	
	public void addTMDB(String tmdb) {
		this.tmdbId = tmdb;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public String toString() {
		return this.movieId + " (" + this.imdbId + ", " + this.tmdbId + ") : " + this.title + ">>> " + this.year;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getYear() {
		return this.year > 0 ? this.year+"" : "";
	}
	
	public int getId() {
		return this.movieId;
	}
	
	public String getIMDB() {
		return this.imdbId;
	}
	
	public String getTMDB() {
		return this.tmdbId;
	}
	
	public double getAverageRating() {
		return averageRating;
	}
	
	public int getNumRatings() {
		return ratings.size();
	}
	
	public String getGenres() {
		String s = "";
		for (String genre : this.genres) {
			s += genre;
		}
		return s;
	}
	
	public void addRating(Rating r) {
		double rating = r.getRating();
		this.ratings.add(r);
		if (ratings.size() <= 1) averageRating = rating;
		else {
			averageRating =  ((averageRating * (ratings.size()-1)) + rating) / ratings.size();
		}
	}

	@Override
	public int compareTo(Movie o) {
		return movieId - o.movieId;
	}
	
	public ArrayList<Movie> findSimilarMovies(ArrayList<Integer> movies) {
		ArrayList<Movie> similarMovies = new ArrayList<Movie>();
		for (int i : movies) {
			Movie m = null;
			if (i != this.getId())
				m = new Movie(i);
			if (m != null) {
				int overlaps = 0;
				for (String s : this.getGenres().replace(",", "").split("\\|")) {
					if (m != this && m.getGenres().contains(s))
						overlaps++;
				}
				if (overlaps > 2 && (Math.abs(this.getAverageRating() - m.getAverageRating()) < 0.5))
					similarMovies.add(m);
			}
		}
		return similarMovies;
	}
	
}
