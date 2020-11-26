#include "HashTable.h"
#include "LinkedList.h"
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/* Lab 6 (Kishore Srinivas)
	the driver function that shows the HashTable in use
	@return 0 when the program is done with the demonstration
*/
int main() {
	LinkedList<Person*>* list = new LinkedList<Person*>();

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
	cout << "Loading data... ";
	while (!in.eof()) {
		getline(in, name);
		getline(in, birthday);
		Person* p = new Person(name, birthday);
		LinkedNode<Person*>* node = new LinkedNode<Person*>(p);
		list->insertNode(node, list->getNumNodes());
	}
	in.close();

	int numPersons = list->getNumNodes();
	HashTable* table = new HashTable(numPersons);
	for (int i = 0; i < numPersons; i++) {
		table->add(list->getNodeAtIndex(i)->getData());
	}
	cout << "Done" << endl;
	cout << "Entries loaded: " << table->getSize() << endl;
	cout << "Load factor: " << table->getLoadFactor() << endl;
	cout << "Collisions: " << table->getNumCollisions() << endl << endl;

	while (true) {
		string input;
		cout << "Enter a birthday to find or Q to quit: ";
		getline(cin, input);
		if (input == "Q" || input == "q") {
			cout << endl;
			system("pause");
			return 0;
		}
		cout << table->find(input) << endl << endl;
	}

	/*cout << "--------------" << endl;
	for (int i = 0; i < numPersons; i++) {
		cout << i << ":\t" << table->getItemAtIndex(i) << endl;
	}*/

	system("pause");
	return 0;
}
