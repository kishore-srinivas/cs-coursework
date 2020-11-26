
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class User implements Comparable<User>{

	private int userId;
	private double averageRating;
	private ArrayList<Double> ratings;
	private ArrayList<String[]> tags;
	private ArrayList<String> likedGenres;
	private HashMap<Integer, Integer> favoriteYears;
	private ArrayList<Integer> likedYears;
	
	public User(int id) {
		this.userId = id;
		this.tags = new ArrayList<String[]>();
		this.ratings = new ArrayList<Double>();
		this.averageRating = 0;
		this.likedGenres = new ArrayList<String>();
		this.favoriteYears = new HashMap<Integer, Integer>();
		this.likedYears = new ArrayList<Integer>();
	}
	
	public void addTags(String[] tag) {
		tags.add(tag);
	}
	
	public void addTags(int movie, String rating, long timestamp) {
		addTags(new String[] {movie+"", rating, timestamp+""});
	}
	
	public int getId() {
		return this.userId;
	}
	
	public ArrayList<String[]> getTags() {
		return this.tags;
	}
	
	public String toString() {
		return this.getId() + " said " + this.ratings.size();
	}
	
	public void addRating(Rating r) {
		double rating = r.getRating();
		ratings.add(rating);
		if (ratings.size() <= 1) averageRating = rating;
		else {
			averageRating =  ((averageRating * (ratings.size()-1)) + rating) / ratings.size();
		}
	}
	
	public void addLikedGenres(String genres) {
		for (String s : genres.split("\\|")) {
			if (!likedGenres.contains(s)) likedGenres.add(s);
		}
	}
	
	public void addLikedYear(int year) {
		likedYears.add(year);
	}
	
	private ArrayList<Integer> removeDuplicates(ArrayList<Integer> input) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int i : input) {
			if (!output.contains(i)) output.add(i);
		}
		return output;
	}
	
	private static Integer getKeyFromValue(HashMap map, Integer value) {
	    for (Object o : map.keySet()) {
	      if (map.get(o).equals(value)) {
	        return (Integer) o;
	      }
	    }
	    return null;
	  }
	
	public double rankYear(int year) {
		HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
		for (int i : likedYears) {
			if (occurrences.containsKey(i)) {
				occurrences.replace(i, occurrences.get(i)+1);
			} else {
				occurrences.put(i, 1);
			}
		}
		
		Collection<Integer> values = occurrences.values();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		ArrayList<Integer> favYears = new ArrayList<Integer>();
		for (int i : values) {
			counts.add(i);
		}
		Collections.sort(counts, Collections.reverseOrder());
		for (int i : counts) {
			int toAdd = getKeyFromValue(occurrences, i);
			favYears.add(toAdd);
			occurrences.remove(toAdd);
		}
		favYears = removeDuplicates(favYears);
		int index = favYears.indexOf(year);
		for (int y : favYears) {
			if (Math.abs(year - y) < 5) {
				index = favYears.indexOf(y);
				break;
			}
		}
		try {
			double decimal = Double.parseDouble(new DecimalFormat("#.###").format((index+0.0)/favYears.size()));
			if (decimal > 0) return 1.0 - decimal;
			else return 0.85;
		} catch (ArithmeticException e) {
			return 0.85;
		}
	}
	
	public double getAverageRating() {
		return averageRating;
	}
	
	public int getNumRatings() {
		return ratings.size();
	}
	
	public ArrayList<String> getLikedGenres() {
		return likedGenres;
	}

	@Override
	public int compareTo(User o) {
		return userId - o.userId;
	}
	
	public ArrayList<User> findSimilarUsers(ArrayList<User> users) {
		ArrayList<User> similarUsers = new ArrayList<User>();
		for (User u : users) {
			int overlaps = 0;
			for (String genre : this.getLikedGenres()) {
				if (u != this && u.getLikedGenres().contains(genre)) overlaps++;
			}
			if (overlaps > 2 || (Math.abs(this.getAverageRating() - u.getAverageRating()) < 0.5)) similarUsers.add(u);
		}	
		return similarUsers;
	}
}
