#include "Person.h"
#include <iostream>

void Person::parseBirthday() {
	if (this->birthday == "") {
		this->birthDate = 0;
		this->birthMonth = 0;
		this->birthYear = 0;
	}
	else {
		this->birthYear = stoi(this->birthday.substr(0, 4));
		this->birthMonth = stoi(this->birthday.substr(5, 7));
		this->birthDate = stoi(this->birthday.substr(8, 10));
	}
}

Person::Person() {
	this->name = "";
	this->birthday = "";
	parseBirthday();
}

Person::~Person() {}

Person::Person(string name, string birthday) {
	this->name = name;
	this->birthday = birthday;
	parseBirthday();
}

string Person::getName() {
	return this->name;
}

string Person::getBirthday() {
	return this->birthday;
}

void Person::setName(string name) {
	this->name = name;
}

void Person::setBirthday(string birthday) {
	this->birthday = birthday;
	parseBirthday();
}

std::ostream& operator << (std::ostream& os, Person* person) {
	os << person->getName() << string("\t") << person->getBirthday();
	return os;
}