#include "templateArray.h"

template <typename T>
TemplateArray<T>::TemplateArray(int len) {
	data = new T[len];
	length = len;
}

template <typename T>
TemplateArray<T>::~TemplateArray() {
	delete []data;
}

template <typename T>
void TemplateArray<T>::set(int index, T value) {
	data[index] = value;
}

template <typename T>
T& TemplateArray<T>::operator[](int index) {
	return isIndexValid(index) ? data[index] : data[0];
}

template <typename T>
int TemplateArray<T>::getLength() {
	return this->length;
}

template <typename T>
void TemplateArray<T>::swap(int index1, int index2) {
	if (isIndexValid(index1) && isIndexValid(index2)) {
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}

template <typename T>
bool TemplateArray<T>::isIndexValid(int index) {
	return (index >= 0 && index < this->length);
}

template <typename T>
std::ostream& operator<<(std::ostream& os, TemplateArray<T>& arr) {
	for (T* item : arr.data) {
		os << *item << " ";
	}
	/*for (int i = 0; i < this.length - 1; i++) {
		os << arr[i] << ", ";
	}
	os << arr[this.length - 1];*/
	return os;
}