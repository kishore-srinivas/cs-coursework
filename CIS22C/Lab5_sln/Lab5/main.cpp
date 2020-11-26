#include "BST.h"
#include <iostream>
#include <fstream>

using namespace std;

/* Lab 5(Kishore Srinivas & Jnana Uhuru)
	the driver function that shows the BST in use and prints the tree by name and birthday sort types and in different orderings
	@return 0 when the program is done with the demonstration
*/
int main() {
	BST* nameTree = new BST(NAME);
	BST* birthdayTree = new BST(BIRTHDAY);

	ifstream in;
	string name;
	string birthday;

	cout << "Enter the directory in which InputData.txt is stored: ";
	string dir;
	getline(cin, dir);

	in.open(dir + "\\InputData.txt");
	while (!in) {
		cerr << "Could not open the input file " << dir << "\\InputData.txt" << endl << endl;
		cout << "Enter the directory in which InputData.txt is stored: ";
		getline(cin, dir);
		in.open(dir + "\\InputData.txt");
	}
	while (!in.eof()) {
		getline(in, name);
		getline(in, birthday);
		Person* p = new Person(name, birthday);
		Node* node = new Node(p);
		nameTree->insert(p);
		birthdayTree->insert(p);
	}
	in.close();

	ofstream namesOut;
	ofstream birthdaysOut;
	namesOut.open("NamesOutput.txt");
	birthdaysOut.open("BirthdaysOutput.txt");

	cout << "--- Names preorder ---" << endl;
	nameTree->traversePreOrder(cout);
	cout << endl;
	cout << "--- Names inorder ---" << endl;
	nameTree->traverseInOrder(cout);
	cout << endl;
	cout << "--- Names postorder ---" << endl;
	nameTree->traversePostOrder(cout);
	cout << endl << endl;
	if (namesOut) {
		namesOut << "--- Names preorder ---" << endl;
		nameTree->traversePreOrder(namesOut);
		namesOut << endl;
		namesOut << "--- Names inorder ---" << endl;
		nameTree->traverseInOrder(namesOut);
		namesOut << endl;
		namesOut << "--- Names postorder ---" << endl;
		nameTree->traversePostOrder(namesOut);
		namesOut << endl << endl;
	}
	else {
		cout << "Name output file not opened successfully" << endl;
	}

	cout << "--- Birthdays preorder ---" << endl;
	birthdayTree->traversePreOrder(cout);
	cout << endl;
	cout << "--- Birthdays inorder ---" << endl;
	birthdayTree->traverseInOrder(cout);
	cout << endl;
	cout << "--- Birthdays postorder ---" << endl;
	birthdayTree->traversePostOrder(cout);
	cout << endl;
	if (birthdaysOut) {
		birthdaysOut << "--- Birthdays preorder ---" << endl;
		birthdayTree->traversePreOrder(birthdaysOut);
		birthdaysOut << endl;
		birthdaysOut << "--- Birthdays inorder ---" << endl;
		birthdayTree->traverseInOrder(birthdaysOut);
		birthdaysOut << endl;
		birthdaysOut << "--- Birthdays postorder ---" << endl;
		birthdayTree->traversePostOrder(birthdaysOut);
		birthdaysOut << endl;
	}
	else {
		cout << "Birthday output file not opened successfully" << endl;
	}

	namesOut.close();
	birthdaysOut.close();

	system("pause");
	return 0;
}
