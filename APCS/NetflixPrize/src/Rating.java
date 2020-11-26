

public class Rating implements Comparable<Rating> {

	private int movieId, userId;
	private long timestamp;
	private double rating;
	
	public Rating(int user, int movie) {
		this.userId = user;
		this.movieId = movie;
	}
	
	public Rating(int user, int movie, double rating, long timestamp) {
		this.movieId = movie;
		this.userId = user;
		this.rating = rating;
		this.timestamp = timestamp;
	}
	
	public int getMovie() {
		return movieId;
	}
	
	public int getUser() {
		return this.userId;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public long getTime() {
		return this.timestamp;
	}
	
	public String toString() {
		return this.getUser() + ": " + this.getRating() + " for movie " + this.getMovie() + " at " + this.getTime();
	}

	@Override
	public int compareTo(Rating r) {
		if (userId > r.userId) {
			return 1;
		} else if (userId < r.userId) {
			return -1;
		} else {
			if (movieId > r.movieId) {
				return 1;
			} else if (movieId < r.movieId) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
}
