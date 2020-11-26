#include "Person.h"
#include <iostream>

Person::Person() {
	this->name = "";
	this->birthday = "";
}

Person::~Person() {}

Person::Person(string name, string birthday) {
	this->name = name;
	this->birthday = birthday;
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
}

std::ostream& operator << (std::ostream& os, Person* person) {
	os << person->getName() << string("\t") << person->getBirthday();
	return os;
}