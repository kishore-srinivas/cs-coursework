#include "HashTable.h"
#include "string"

void HashTable::calculateLoadFactor() {
	double sum = 0;
	for (int i = 0; i < this->size; i++) {
		if (getItemAtIndex(i)->getName() != "") sum++;
	}
	this->loadFactor = sum / this->size;
}

int HashTable::hash(string birthday) {
	int sum = 0;
	for (int i = 0; i < birthday.size(); i++) {
		try {
			string sub = birthday.substr(i, 1);
			int num = stoi(sub);
			sum += num;
		} catch ( ... ) {}
	}
	while (sum > 10) {
		string copy = to_string(sum);
		sum = 0;
		for (int i = 0; i < copy.size(); i++) {
			sum += stoi(copy.substr(i, 1));
		}
	}
	return sum;
}

int HashTable::quadraticProbe(int start, int n) {
	return (start + (n * n)) % this->size;
}

int HashTable::linearProbe(int start, int n) {
	return (start + n) % this->size;
}

HashTable::HashTable(int size) : TemplateArray<Person>(size) {
	this->size = size;
}

HashTable::~HashTable() {}

void HashTable::add(Person* item) {
	int index = hash(item->getBirthday());
	for (int n = 0; n < this->size; n++) {
		int quadIndex = quadraticProbe(index, n);
		string name = getItemAtIndex(quadIndex)->getName();
		if (name == "") {
			TemplateArray<Person>::set(quadIndex, *item);
			calculateLoadFactor();
			return;
		}
		else {
			this->numCollisions++;
		}
	}
	for (int n = 0; n < this->size; n++) {
		int linearIndex = linearProbe(index, n);
		string name = getItemAtIndex(linearIndex)->getName();
		if (name == "") {
			TemplateArray<Person>::set(linearIndex, *item);
			calculateLoadFactor();
			return;
		}
		else {
			this->numCollisions++;
		}
	}
	std::cout << "unable to add " << item << " at index " << index << endl;
}

void HashTable::remove(Person* item) {
	int index = hash(item->getBirthday());
	Person* nullPerson = new Person();
	TemplateArray<Person>::set(index, *nullPerson);
}

void HashTable::remove(string name) {
	int length = TemplateArray<Person>::getLength();
	for (int i = 0; i < length; i++) {
		if (getItemAtIndex(i)->getName() == name) {
			Person* nullPerson = new Person();
			TemplateArray<Person>::set(i, *nullPerson);
			calculateLoadFactor();
		}
	}
}

Person* HashTable::find(string birthday) {
	int hashedIndex = hash(birthday);
	for (int n = 0; n < this->size; n++) {
		int quadraticIndex = quadraticProbe(hashedIndex, n);
		Person* p = &(TemplateArray<Person>::operator[](quadraticIndex));
		if (p->getBirthday() == birthday) return p;
	}
	for (int n = 0; n < this->size; n++) {
		int linearIndex = linearProbe(hashedIndex, n);
		Person* p = &(TemplateArray<Person>::operator[](linearIndex));
		if (p->getBirthday() == birthday) return p;
	}
	return new Person("---Invalid Data---", "");
}

Person* HashTable::getItemAtIndex(int index) {
	Person* p = &(TemplateArray<Person>::operator[](index));
	return p;
}

int HashTable::getSize() {
	return this->size;
}

double HashTable::getLoadFactor() {
	return this->loadFactor;
}

int HashTable::getNumCollisions() {
	return this->numCollisions;
}