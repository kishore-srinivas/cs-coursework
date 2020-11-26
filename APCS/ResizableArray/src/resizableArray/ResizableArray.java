package resizableArray;

import java.util.Arrays;

public class ResizableArray {
	
	private int[] data;
	private int size; // size is amount of data, length is number of variables
	private final int INITIAL_LENGTH = 20;
	
	public ResizableArray() {
		data = new int[INITIAL_LENGTH];
		size = 0;
	}
	
	public ResizableArray(int length) {
		this();
		size = length;
		resize();
	}
	
	public ResizableArray(int[] values) {
		this(values.length);
		for (int i = 0; i < values.length; i++) {
			this.set(i, values[i]);
		}
	}
	
	// ================================ FIRST WAVE ================================
	
	public void add(int value) {
		if (this.size() >= data.length) {
			resize();
		}		
		data[size] = value;
		size++;
	}
	
	public int remove(int index) {
		int x = data[index];
		if (index < size) {
			for (int i = index; i < size-1; i++) {
				data[i] = data[i+1];
			}
			size--;
		} else {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		return x;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		int[] copy = new int[size];
		for (int i = 0; i < size; i++) {
			copy[i] = data[i];
		}
		return Arrays.toString(copy);
	}
	
	// ================================ SECOND WAVE ================================
	
	public void insert(int index, int value) {
		if (this.size() >= data.length) {
			resize();
		}
		for (int i = size; i > index; i--) {
			data[i] = data[i-1];
		}
		if (index >= 0 && index < size) {
			data[index] = value;
		} else {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		size++;
	}
	
	public int get(int index) {
		return data[index];
	}
	
	public void set(int index, int value) {
		if (index >= 0 && index < size) {
			data[index] = value;
		} else {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
	}
	
	public void sort() {		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size-1; j++) {
				if (data[j] > data[j+1]) {
					this.swap(j, j+1);
				}
			}
		}
	}
	
	public int indexOf(int value) {
		int index = -1;
		int i = 0;
		while (index != -1) {
			if (data[i] == value) {
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public ResizableArray findAll(int value) {
		ResizableArray indeces = new ResizableArray();
		for (int i = 0; i < size; i++) {
			if (data[i] == value) {
				indeces.add(i);
			}
		}
		return indeces;
	}
	
	public boolean equals(Object object) {
		ResizableArray other = (ResizableArray) object;
		if (other.size() != this.size() ) {
			return false;
		} else {
			for (int i = 0; i < size; i++) {
				if (this.get(i) != other.get(i)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[] toArray() { // good to not have a "getter" because it would be giving away access to the memory location
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = this.get(i);
		}
		return array;
	}
	
	// ================================ THIRD WAVE ================================
	
	public void removeAll(int unwanted) {		
		int newSize = size;
		int numUnwanted = 0;
		for (int i = 0; i < newSize; i++) {
			if (data[i] == unwanted) {
				numUnwanted++;
				newSize--;
				for (int j = i; j < newSize-1; j++) {
					data[j] = data[j+1];
				}
				i--;
			}
		}
		size = newSize;
	}
	
	public void clear() {
		this.setAll(0);
	}
	
	public void setAll(int value) {
		for (int i = 0; i < size; i++) {
			this.set(i, value);
		}
	}
	
	public void reverse() {
		for (int i = 0; i < size/2; i++) {
			int temp = data[i];
			data[i] = data[size-1-i];
			data[size-1-i] = temp;
		}
	}
	
	public void swap(int index1, int index2) {
		if (index1 < size && index2 < size) {
			int temp = data[index1];
			data[index1] = data[index2];
			data[index2] = temp;
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}
	
	public ResizableArray subArray(int start, int end) {
		if (end < start || end > size || start > size) {
			throw new IllegalArgumentException("Invalid range");
		}
		ResizableArray sub = new ResizableArray();
		for (int i = 0; i <= (end-start); i++) {
			sub.add(data[start+i]);
		}
		return sub;
	}
	
	public void addAll(int[] data) {
		for (int value : data) {
			this.add(value);
		}
	}
	
	public void replace(int oldValue, int newValue) {
		int i = 0;
		boolean found = false;
		while (i < size && found == false) {
			if (data[i] == oldValue) {
				data[i] = newValue;
				found = true;
			}
			i++;
		}
	}
	
	public void replaceAll(int oldValue, int newValue) {
		if (newValue != oldValue) {
			for (int i = 0; i < size; i++) {
				if (this.get(i) == oldValue) {
					this.set(i, newValue);
				}
			}
		}
	}
	
	private void resize() {
		int[] newData = new int[data.length + INITIAL_LENGTH];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

}
