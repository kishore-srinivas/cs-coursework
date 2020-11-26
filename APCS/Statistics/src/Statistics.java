import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

	private int[] data;
	private int numValues, numCompacted;
	private int UNWANTED = 0;

	public Statistics(int maxLength) {
		data = new int[maxLength];
	}

	public void readData(String filename) {
		ArrayReader reader = new ArrayReader(filename);
		numCompacted = numValues = reader.fillArray(data);
		reader.fillArray(data);
	}

	public void printData() {
		int[] copy = new int[numValues];
		for (int i = 0; i < numValues; i++) {
			copy[i] = data[i];
		}
		System.out.println(Arrays.toString(copy));
		System.out.println(numValues);
	}

	public double getAverage() {
		double sum = 0;
		for (int i = 0; i < numValues; i++ ) {
			sum += data[i];
		}
		return sum/numValues;
	}

	public String[] getMode() {
		int[] counts = new int[numValues];
		int maxCount = 0;
		for (int i = 0; i < numValues; i++) {
			int count = 0;
			for (int j = 0; j < numValues; j++) {
				if (data[i] == data[j]) {
					count++;
				}
			}
			counts[i] = count;

			for (int counter : counts) {
				if (counter > maxCount) {
					maxCount = counter;
				}
			}
		}

		int numModes = 0;
		String str = "";
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == maxCount) {
				if (!str.contains(data[i] + "")) {
					str += data[i] + " ";
					numModes++;
				}			
			}
		}

		String[] modes = new String[numModes];
		modes = str.split(" ");
		return modes;
	}

	public double getStandardDev() {
		double average = this.getAverage();
		double sumOfSquaredDiffs = 0;
		for (int i = 0; i < numValues; i++) {
			sumOfSquaredDiffs += (Math.pow((data[i] - average), 2));
		}
		return (Math.sqrt(sumOfSquaredDiffs / (numValues - 1)));
	}

	public void compact() {		
		/*int size = numValues;
		int numUnwanted = 0;
		for (int i = 0; i < size; i++) {
			if (data[i] == UNWANTED) {
				numUnwanted++;
				size--;
			}
			data[i] = data[i+numUnwanted];
		}
		numValues = size;*/
		
		int numUnwanted = 0;
		for (int i = 0; i < numValues; i++) {
			for (int j = 0; j < numValues-i; j++) {
				if (data[j] == UNWANTED) {
					data[j] = data[j+1];
					data[j+1] = UNWANTED;
				}
			}
		}
		for (int i = 0; i < numValues; i++) {
			if (data[i] == UNWANTED) {
				numUnwanted++;
			}
		}
		numValues -= numUnwanted;
	}
}
