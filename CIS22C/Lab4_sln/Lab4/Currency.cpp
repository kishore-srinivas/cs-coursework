#include "Currency.h"
#include <iostream>

Currency::Currency() {}

Currency::Currency(std::string wholeParts, int notes, int coins, std::string fracParts) {
	this->wholeParts = wholeParts;
	this->notes = notes;
	this->coins = coins;
	this->fracParts = fracParts;
}

double Currency::convertToDouble() {
	return this->notes + (this->coins / 100.0);
}

std::string Currency::getType() {
	return "";
}

void Currency::setValue(double value) {
	this->notes = int(value);
	this->coins = (value - int(value)) * 100;
}

void Currency::setValue(int notes, int coins) {
	this->notes = notes;
	this->coins = coins;
}

int Currency::getNotes() {
	return this->notes;
}

int Currency::getCoins() {
	return this->coins;
}

Currency& operator + (Currency& curr1, Currency& curr2) {
	if (curr1.wholeParts != curr2.wholeParts) throw "Mismatched currency types: " + curr1.wholeParts + ", " + curr2.wholeParts;
	double first = curr1.convertToDouble();
	double second = curr2.convertToDouble();
	double total = first + second;
	int totalWhole = int(total);
	int totalParts = int(total * 100) % 100;
	Currency* a = new Currency(curr2.wholeParts, totalWhole, totalParts, curr2.fracParts);
	return *a;
}

Currency& operator - (Currency& curr1, Currency& curr2) {
	if (curr1.wholeParts != curr2.wholeParts) throw "Mismatched currency types: " + curr1.wholeParts + ", " + curr2.wholeParts;
	double first = curr1.convertToDouble();
	double second = curr2.convertToDouble();
	if (first < second) throw curr1;
	double total = first - second;
	int totalWhole = int(total);
	int totalParts = int(total * 100) % 100;
	Currency* a = new Currency(curr2.wholeParts, totalWhole, totalParts, curr2.fracParts);
	return *a;
}

bool operator < (Currency& curr1, Currency& curr2) {
	if (curr1.wholeParts != curr2.wholeParts) throw "Mismatched currency types: " + curr1.wholeParts + ", " + curr2.wholeParts;
	double first = curr1.convertToDouble();
	double second = curr2.convertToDouble();
	return (first < second);
}

bool operator > (Currency& curr1, Currency& curr2) {
	if (curr1.wholeParts != curr2.wholeParts) throw "Mismatched currency types: " + curr1.wholeParts + ", " + curr2.wholeParts;
	double first = curr1.convertToDouble();
	double second = curr2.convertToDouble();
	return (first > second);
}

std::ostream& operator<< (std::ostream& os, Currency& curr) {
	os << curr.notes << " " << curr.wholeParts << " " << curr.coins << " " << curr.fracParts;
	return os;
}

std::istream& operator>> (std::istream& is, Currency& curr) {
	std::string inputString;
	is >> inputString;
	std::string delimiter = ",";

	size_t p = inputString.find(delimiter);
	curr.wholeParts = inputString.substr(0, p);
	inputString.erase(0, p + delimiter.length());

	try {
		p = inputString.find(delimiter);
		curr.notes = std::stoi(inputString.substr(0, p));
		inputString.erase(0, p + delimiter.length());
	}
	catch (...) {
		curr.notes = 0;
	}	

	try {
		p = inputString.find(delimiter);
		curr.coins = std::stoi(inputString.substr(0, p));
		inputString.erase(0, p + delimiter.length());
	}
	catch (...) {
		curr.coins = 0;
	}	

	p = inputString.find(delimiter);
	curr.fracParts = inputString.substr(0, p);
	inputString.erase(0, p + delimiter.length());

	//is >> curr.wholeParts >> curr.notes >> curr.coins >> curr.fracParts;
	return is;
}

Dollar::Dollar(int notes, int coins):	
	Currency("Dollar", notes, coins, "cent") {}

Dollar::Dollar(double value) {
	Dollar(int(value), int(value * 100) % 100);
}

std::string Dollar::getType() {
	return "Dollar";
}

Euro::Euro(int notes, int coins):
	Currency("Euro", notes, coins, "cent") {}

Euro::Euro(double value) {
	Euro(int(value), int(value * 100) % 100);
}

std::string Euro::getType() {
	return "Euro";
}

Yen::Yen(int notes, int coins):
	Currency("Yen", notes, coins, "sen") {}

Yen::Yen(double value) {
	Yen(int(value), int(value * 100) % 100);
}

std::string Yen::getType() {
	return "Yen";
}

Rupee::Rupee(int notes, int coins):
	Currency("Rupee", notes, coins, "paise") {}

Rupee::Rupee(double value) {
	Rupee(int(value), int(value * 100) % 100);
}

std::string Rupee::getType() {
	return "Rupee";
}

Yuan::Yuan(int notes, int coins):
	Currency("Yuan", notes, coins, "fen") {}

Yuan::Yuan(double value) {
	Yuan(int(value), int(value * 100) % 100);
}

std::string Yuan::getType() {
	return "Yuan";
}
