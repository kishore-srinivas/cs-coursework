#include <iostream>
#include "array.h"
#include "Wallet.h"
#include "Currency.h"
#include <string>
#include <fstream>

using namespace std;

/*	checks if the number of elements is acceptable and ensures that it is before allowing the user to continue
@return the acceptable number of elements to be input
*/
int verifyInput()
{
	int num;
	bool foundValidNum = false;
	while (!foundValidNum) {
		cout << "\nNote: The number of elements you can sort can be no larger than 16.\n";
		cout << "Enter in the quantity of elements you want to have sorted: ";
		cin >> num;
		if (num > 16)
		{
			cout << "\nInvalid number of elements. Please enter in another number that is no greater than 16.\n";
			continue;
		}
		if (num < 1)
		{
			cout << "\nInvalid number of elements. Please enter in a positive number.\n";
			continue;
		}
		foundValidNum = true;
	}
	return num;
}

/*	recursively sorts the TemplateArray using selection sort
@param  arr - the TemplateArray object that will have its array sorted
		size - the number of elements within the array
		of - file stream object to allow for a print of every call
		index - where the sort will start on this call, initially 0

@post prints to both console and an output file each call of the sort function
*/
template <typename T>
void recurSelectionSort(TemplateArray<T>& arr, int size, ofstream& of, int index = 0 ) {
	
	if (index == size - 1)
	{
		return;
	}
	int maxPos = index;
	int originalIndex = index;
	for (index; index < size; index++)
	{
		if (arr[index] > arr[maxPos])
		{
			maxPos = index;
		}
	}
	arr.swap(originalIndex, maxPos);

	cout <<"Function call #" << originalIndex + 1 << ":   " <<  arr;
	of << "Function call #" << originalIndex + 1 << ":   " << arr;

	recurSelectionSort(arr, size, of, originalIndex + 1);

}


/* Lab 2(Jnana Uhuru & Kishore Srinivas)
	the driver function that allows for the user to fill an array with data of their chosing that is then sorted and displayed to the user as it happens
	@return 0 when the program is done sorting and displaying after user input
*/
int main() {
	ofstream write;
	write.open("sortProj.txt");
	write << "Lab 2(Templates and Recursive Sorting): Kishore Srinivas and Jnana Uhuru" << endl << endl;
	int choice;
	cout << "What type of data would you like to have sorted?" << endl;
	cout << "1) Integers" << endl;
	cout << "2) Strings" << endl;
	cout << "3) Dollars" << endl;
	cout << "From the above list, enter in the number corresponding to the data type you intend to sort in DECREASING order: ";
	cin >> choice;
	switch (choice)
	{

	case (1):
	{
		TemplateArray<int> Iarr(verifyInput());
		Iarr.inputarray();
		cout << "\nGiven: " << Iarr;
		write << "\nGiven: " << Iarr;
		recurSelectionSort(Iarr, Iarr.getLength(), write);
		cout << "Sorted: " << Iarr;
		write << "Sorted: " << Iarr;
		break;
	}
	case (2):
	{
		TemplateArray<string> Sarr(verifyInput());
		Sarr.inputarray();
		cout << "\nGiven: " << Sarr;
		write << "\nGiven: " << Sarr;
		recurSelectionSort(Sarr, Sarr.getLength(), write);
		cout << "Sorted: " << Sarr;
		write << "Sorted: " << Sarr;
		break;
	}
	case (3):
	{
		TemplateArray<Currency> Carr(verifyInput());
		cout << "*** Be sure to enter items in the following format: Dollar,10,10,cent ***" << endl;
		Carr.inputarray();
		cout << "\nGiven: " << Carr;
		write << "\nGiven: " << Carr;
		recurSelectionSort(Carr, Carr.getLength(), write);
		cout << "Sorted: " << Carr;
		write << "Sorted: " << Carr;
		break;
	}
	default:
		cout << "Invalid Input" << endl;
		break;

	}
	write.close();
	system("pause");
	return 0;
}
