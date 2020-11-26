

import java.util.ArrayList;

public class DatabaseSearcher {
	
	protected ArrayList<Movie> movieData;
	protected ArrayList<Rating> ratingData;
	protected ArrayList<User> userData;
	
	public DatabaseSearcher(ArrayList<Movie> movies, ArrayList<User> users, ArrayList<Rating> ratings) {
		this.movieData = movies;
		this.ratingData = ratings;
		this.userData = users;
	}
	
	public Movie findMovie(int id) {
		for (Movie m : movieData) 
			if (m.getId() == id)
				return m;
		return null;
	}
	
	public User findUser(int id) {
		for (User u : userData) 
			if (u.getId() == id)
				return u;		
		return null;
	}

}
