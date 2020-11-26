#include <iostream>
#include "Wallet.h"
#include "Currency.h"

using namespace std;

Wallet wallet; //declares a Wallet object 

/* prints the contents of the wallet by currency
@post prints the names and monetary values of the currency objects to console
*/
void view() {
	cout << wallet << endl;
}

/* facilitates the addition of currencies to the wallet
@post the currency that was specified has its notes and coins increased by the user specified amount
*/
void add() {
	cout << "Enter the value you would like to add (in the format of Dollar,10,10,cent)" << endl;
	Currency a;
	cin >> a;
	if (a.getType() == "Dollar") {
		Currency* b = new Dollar(a.getNotes(), a.getCoins());
		wallet.add(*b);
	}
	if (a.getType() == "Euro") {
		Currency* b = new Euro(a.getNotes(), a.getCoins());
		wallet.add(*b);
	}
	if (a.getType() == "Yen") {
		Currency* b = new Yen(a.getNotes(), a.getCoins());
		wallet.add(*b);
	}
	if (a.getType() == "Rupee") {
		Currency* b = new Rupee(a.getNotes(), a.getCoins());
		wallet.add(*b);
	}
	if (a.getType() == "Yuan") {
		Currency* b = new Yuan(a.getNotes(), a.getCoins());
		wallet.add(*b);
	}
}

/* facilitates the removal of currencies from the wallet
@post the currency that was specified has its notes and coins decreased by the user specified amount
*/
void remove() {
	cout << "Enter the value you would like to remove (in the format of Dollar,10,10,cent)" << endl;
	Currency a;
	cin >> a;
	if (a.getType() == "Dollar") {
		Currency* b = new Dollar(a.getNotes(), a.getCoins());
		wallet.remove(*b);
	}
	if (a.getType() == "Euro") {
		Currency* b = new Euro(a.getNotes(), a.getCoins());
		wallet.remove(*b);
	}
	if (a.getType() == "Yen") {
		Currency* b = new Yen(a.getNotes(), a.getCoins());
		wallet.remove(*b);
	}
	if (a.getType() == "Rupee") {
		Currency* b = new Rupee(a.getNotes(), a.getCoins());
		wallet.remove(*b);
	}
	if (a.getType() == "Yuan") {
		Currency* b = new Yuan(a.getNotes(), a.getCoins());
		wallet.remove(*b);
	}
}

/* empties the wallet of all value in every currency 
@post all monetary values of evey currency are now zero
*/
void empty() {
	wallet.empty();
}

/* displays the value of the desired currency to console
@param s The currency name to be passed by the function call
@post the name and values of the currency are displayed to the user in the console
*/
void viewValue() {
	cout << "Enter the value you would like to view (Dollar, Euro, Yen, Rupee, Yuan)" << endl;
	string s;
	cin >> s;
	wallet.view(s);
}

/* prints the currencies in the wallet that have any value(nonzero)
@post all the names of currency objects that have a monetary value are printed to the console
*/
void findNonZero() {
	wallet.findAllNonZero();
}

/* the driver function that allows for the user to interact with the wallet, check its contents, and alter the amounts of currencies
@return 0 when user decides to exit the program
*/
int main() {
	//Wallet wallet;
	cout << "Welcome to your wallet!" << endl;
	cout << wallet << endl;

	bool finished = false;
	while (!finished) {
		int selection;
		cout << endl << "-------------------------------" << endl;
		cout << "What would you like to do next?" << endl;
		cout << "1. View wallet" << endl;
		cout << "2. Add value" << endl;
		cout << "3. Remove value" << endl;
		cout << "4. View value by currency type" << endl;
		cout << "5. Empty out wallet" << endl;
		cout << "6. Find all non-zero values in the wallet" << endl;
		cout << "7. Exit" << endl;
		cout << ">>> ";
		cin >> selection;
		switch (selection)
		{
		case(1):
			view();
			break;
		case(2):
			add();
			break;
		case(3):
			remove();
			break;
		case(4):
			viewValue();
			break;
		case(5):
			empty();
			break;
		case(6):
			findNonZero();
			break;
		case(7):
			system("pause");
			return 0;
		default:
			cout << "Enter valid selection" << endl;
			break;
		}
	}
}
