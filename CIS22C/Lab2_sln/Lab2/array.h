#ifndef TEMPLATEARRAY
#define TEMPLATEARRAY
#include <iostream>
#include <fstream>

template <typename T>
class TemplateArray;

template <typename T>
std::ostream& operator<< (std::ostream& os, const TemplateArray<T>& L);

template <typename T>
class TemplateArray {
private:
	int length;
	T* data;
public:
	TemplateArray(int len);
	void inputarray();
	~TemplateArray();
	void set(int index, T value);
	T& operator[](int index);
	int getLength();
	void swap(int index1, int index2);
	bool isIndexValid(int index);
	friend std::ostream& operator<< <>(std::ostream&, TemplateArray&);
};

#endif

	/* constructor of the TemplateArray class that can create a template array of the size of the param
	@param  len - the length of the array that the user wants to make
	*/
	template <typename T>
	TemplateArray<T>::TemplateArray(int len) {
		data = new T[len];
		length = len;
	}

	/* allows for the user to fill the initialized array with the data type they input
	@pre an initialized template array of a given size
	@post the array now has values in it from the user
	*/
	template <typename T>
	void TemplateArray<T>::inputarray() {
		std::cout << "\nGo ahead and enter in " << length << " elements of the type you specified and look how they are sorted!\n";
		for (int i = 0; i < length; i++) {
			std::cout << "Element #" << i + 1 << ":";
			std::cin >> data[i];
		}
	}

	/*default destructor*/
	template <typename T>
	TemplateArray<T>::~TemplateArray() {
		delete[]data;
	}

	/*	can be used to set values within the TemplateArray
	@pre the index that the user wants to set and the value
	@post the specified index now has that value in it
	*/
	template <typename T>
	void TemplateArray<T>::set(int index, T value) {
		data[index] = value;
	}

	/*	overloads the subscript operator so that it can be used on TemplateArray objects to access the data within them
	@pre the index to be accessed
	@return the value within that index of the data array of the TA object
	*/
	template <typename T>
	T& TemplateArray<T>::operator[](int index) {
		return isIndexValid(index) ? data[index] : data[0];
	}

	/*	returns the length of the array of the TemplateArray object
	@pre the initialized object
	@return the data member called length of type int
	*/
	template <typename T>
	int TemplateArray<T>::getLength() {
		return this->length;
	}


	/*	swaps values between indices of the data array of a TemplateArray object
	@pre a TA object and the two indices to be swapped
	@post the values of the two indices are now switched with each other
	*/
	template <typename T>
	void TemplateArray<T>::swap(int index1, int index2) {
		if (isIndexValid(index1) && isIndexValid(index2)) {
			T temp = data[index1];
			data[index1] = data[index2];
			data[index2] = temp;
		}
	}

	/*	checks to make sure that the index is within bounds of the TA oject's array
	@pre an index to be checked
	@return false if the index is out of bounds, true if within the array
	*/
	template <typename T>
	bool TemplateArray<T>::isIndexValid(int index) {
		return (index >= 0 && index < this->length);
	}

	/*	overloads the insertion operator so that it can print out the array within the TemplateArray object
	@pre TemplateArray object initialized with values in its array
	@return an ostream object containing the values of the array
	*/
	template <typename T>
	std::ostream& operator<< <>(std::ostream& os, TemplateArray<T>& arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			os << arr[i] << ", ";
		}
		os << arr[arr.length - 1] << std::endl;
		return os;
	}

