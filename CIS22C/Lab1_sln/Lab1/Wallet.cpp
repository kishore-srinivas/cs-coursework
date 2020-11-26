#include "Wallet.h"
#include "Currency.h"
#include <iostream>

using namespace std;

Wallet::Wallet()
{
	this->currencies[0] = new Dollar(0, 0);
	this->currencies[1] = new Euro(0, 0);
	this->currencies[2] = new Yen(0, 0);
	this->currencies[3] = new Rupee(0, 0);
	this->currencies[4] = new Yuan(0, 0);
}

Currency& Wallet::operator[](int index)
{
	int i = index % (sizeof(this->currencies) / sizeof(this->currencies[0]));
	return *this->currencies[i];
}

Currency& Wallet::getCurrency(string type) {
	if (type == "Dollar") {
		return *this->currencies[0];
	}
	if (type == "Euro") {
		return *this->currencies[1];
	}
	if (type == "Yen") {
		return *this->currencies[2];
	}
	if (type == "Rupee") {
		return *this->currencies[3];
	}
	if (type == "Yuan") {
		return *this->currencies[4];
	}
	throw type + " not found";
}

bool Wallet::isNonZero(string type) {
	return getCurrency(type).convertToDouble() != 0;
}

void Wallet::findAllNonZero() {
	for (Currency* c : this->currencies) {
		if (isNonZero(c->getType())) cout << *c << endl;
	}
}

void Wallet::view(string type) {
	cout << getCurrency(type) << endl;
}

int Wallet::numNonZero() {
	int result = 0;
	for (int i = 0; i < (sizeof(this->currencies) / sizeof(this->currencies[0])); i++) {
		if (this->currencies[i]->convertToDouble() != 0) {
			result++;
		}
	}
	return result;
}

void Wallet::add(Currency& curr) {
	try {
		getCurrency(curr.getType()) = getCurrency(curr.getType()) + curr;
	}
	catch (string s) {
		cout << s << endl;
	}
	cout << "You now have " << getCurrency(curr.getType()) << endl;
}

void Wallet::remove(Currency& curr) {
	try {
		getCurrency(curr.getType()) = getCurrency(curr.getType()) - curr;
	}
	catch (Currency& c) {
		cout << "Cannot remove more than " << c << " from wallet at this time." << endl;
	}
	catch (string s) {
		cout << s << endl;
	}
	cout << "You now have " << getCurrency(curr.getType()) << endl;
}

void Wallet::empty() {
	for (Currency* c : this->currencies) {
		std::cout << "Emptied " << *c << " from wallet." << endl;
		c->setValue(0);
	}
}

bool Wallet::isEmpty() {
	return (numNonZero() == 0);
}

std::ostream& operator<< (std::ostream& os, Wallet& wallet) {
	os << "======================" << endl;
	os << "Your wallet contains: " << endl;
	for (Currency* c : wallet.currencies) {
		os << *c << endl;
	}
	os << "======================" << endl;
	return os;
}
