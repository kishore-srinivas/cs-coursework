package barrons_test1;

import java.util.ArrayList;
import java.util.Arrays;

// Problem 2 parts A, B, and C

public class Sentence {
	
	private String sentence;
	
	public Sentence(String s) {
		sentence = s;
	}
	
	public Sentence() {
		this("");
	}
	
	public String toString() {
		return sentence;
	}
	
	public ArrayList<Integer> getBlankPositions() {
		ArrayList<Integer> blanks = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < sentence.length()-1; i++) {
			if (sentence.substring(i, i+1).equals(" ")) {
				blanks.add(i);
			}
		}
		return blanks;
	}
	
	public int countWords() {
		int numWords = 1;
		for (int i = 0; i < sentence.length()-1; i++) {
			if (sentence.substring(i, i+1).equals(" ")) {
				numWords++;
			}
		}
		return numWords;
	}
	
	public String[] getWords() {
		String[] words = new String[countWords()];
		int count = 0;
		int start = 0;
		for (int i = 0; i < sentence.length(); i++) {
			if (sentence.substring(i, i+1).equals(" ")) {
				words[count] = sentence.substring(start, i);
				start = i+1;
				count++;
			}
		}
		if (words[countWords() - 1] == null) {
			words[countWords() - 1] = sentence.substring(getBlankPositions().get(getBlankPositions().size()-1)+1, sentence.length());
		}
		return words;
	}

}
