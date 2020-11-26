#include "CityDatabase.h"
#include <iostream>
#include <fstream>

using namespace std;

City* CityDatabase::createCity(std::string line) {
	int pos = line.find("\t");
	string name = line.substr(0, pos);
	line.erase(0, pos + 1);

	pos = line.find("\t");
	double x = 0;
	try {
		x = stod(line.substr(0, pos));
	} catch (invalid_argument e) {}
	line.erase(0, pos + 1);

	pos = line.find("\t");
	double y = 0;
	try {
		y = stod(line.substr(0, pos));
	}
	catch (invalid_argument e) {}
	line.erase(0, pos + 1);

	pos = line.find("\t");
	int popDens = 0;
	try {
		popDens = stoi(line.substr(0, pos));
	}
	catch (invalid_argument e) {}
	line.erase(0, pos + 1);

	pos = line.find("\t");
	double livingIndex = 0;
	try {
		livingIndex = stod(line.substr(0, pos));
	}
	catch (invalid_argument e) {}
	line.erase(0, pos + 1);

	pos = line.find("\t");
	string weather = line.substr(0, pos);
	line.erase(0, pos + 1);

	pos = line.find("\t");
	string language = line.substr(0, pos);
	line.erase(0, pos + 1);

	pos = line.find("\t");
	string activities = line.substr(0, pos);
	line.erase(0, pos + 1);

	pos = line.find("\t");
	string country = line.substr(0, pos);

	City* city = new City(name, x, y, popDens, livingIndex, weather, language, activities, country);
	return city;
}

void CityDatabase::populateHashTable() {
	for (int i = 0; i < this->numCities; i++) {
		City* city = this->data.front();
		this->data.dequeue();
		this->data.enqueue(city); // refill the queue after taking the front element
		this->hashtable->add(city->getPopulationDensity(), city);
	}
}

void CityDatabase::populateBST() {
	City::setComparisonType("name");
	for (int i = 0; i < this->numCities; i++) {
		City* city = this->data.front();
		this->data.dequeue();
		this->data.enqueue(city); // refill the queue after taking the front element
		this->bst->add(city);
	}
}

void CityDatabase::populateLinkedList() {
	for (int i = 0; i < this->numCities; i++) {
		City* city = this->data.front();
		this->data.dequeue();
		this->data.enqueue(city); // refill the queue after taking the front element
		this->linkedlist->add(city);
	}
}
CityDatabase::CityDatabase(std::ifstream& file) {
	while (!file.eof()) {
		string line;
		getline(file, line);
		City* city = createCity(line);
		this->data.enqueue(city);
		HitCounter* hc = new HitCounter(city);
		this->hitCounters.enqueue(hc);
	}
	this->numCities = this->data.getSize();
	this->hashtable = new HashTable<City*>(this->numCities);
	populateHashTable();
	this->bst = new BST<City*>();
	populateBST();
	this->linkedlist = new SinglyLinkedList<City*>();
	populateLinkedList();
}

BST<City*>* CityDatabase::getBST() {
	return this->bst;
}

HashTable<City*>* CityDatabase::getHashTable() {
	return this->hashtable;
}

SinglyLinkedList<City*>* CityDatabase::getLinkedList() {
	return this->linkedlist;
}

City* CityDatabase::findByName(std::string name)
{
	City::setComparisonType("name");

	City compareCity;
	compareCity.setName(name);
	City* compareCityPtr = &compareCity;
	City* searched;
	int numOperations = 0;
	bool found = this->bst->search(compareCityPtr, searched, numOperations);

	if (found)
		return searched;
	else
		return nullptr;
}

City* CityDatabase::findByPopulationDensity(int popDens)
{
	City::setComparisonType("populationDensity");

	City* searched;
	int numComparisons = 0;
	bool found = this->hashtable->retrieve(popDens, searched, numComparisons);

	if (found)
		return searched;
	else
		return nullptr;
}

City* CityDatabase::findByClimate(std::string climate)
{
	City::setComparisonType("climate");

	City compareCity;
	compareCity.setClimate(climate);
	City* compareCityPtr = &compareCity;
	City* searched;
	int numComparisons = 0;
	bool found = this->linkedlist->search(compareCityPtr, searched, numComparisons);

	if (found)
		return searched;
	else
		return nullptr;
}

void CityDatabase::printSorted(std::ostream& out)
{
	this->bst->printSorted(out); 
}

void CityDatabase::print(std::ostream& out)
{
	City::setOutputType("condensed");
	this->bst->print(out);
	City::setOutputType("full");
}

void CityDatabase::addCity(City* city) {
	this->data.enqueue(city);
	this->bst->add(city);
	this->hashtable->add(city->getPopulationDensity(), city);
	this->linkedlist->add(city);
}

void CityDatabase::removeCity(City* city) {
	this->bst->remove(city);
	this->linkedlist->remove(city);
	this->hashtable->remove(city->getPopulationDensity(), city);
	this->data.remove(city);
}

void CityDatabase::displayEfficiency(std::ostream& out)
{
	City compare = City();
	compare.setName("Gotham City");
	compare.setClimate("Dark");
	City* comparePtr = &compare;
	int numComparisons;
	City* empty = nullptr;

	numComparisons = 0;
	City::setComparisonType("name");
	this->bst->search(comparePtr, empty, numComparisons);
	out << "Binary Search Tree comparisons: " << numComparisons << " Num nodes: " << this->bst->getNumNodes() << "	approaches O(log(N)) if tree gets more balanced" << endl << endl;

	City::setComparisonType("climate");
	numComparisons = 0;
	this->linkedlist->search(comparePtr, empty, numComparisons);
	out << "Linked List comparisons: " << numComparisons << " Num nodes: " << this->linkedlist->getSize() << "	O(N)" << endl << endl;

	numComparisons = 0;
	this->hashtable->retrieve(1111111, empty, numComparisons);
	this->hashtable->getAverageLists();
	out << "Hash Table comparisons: " << numComparisons << "	O(1)" << endl;
	out << "Load factor: " << this->hashtable->getLoadFactor() << endl;
	out << "Longest chain of nodes: " << this->hashtable->getNumLongestLinkedList() << endl;
	out << "Average nodes per list: " << this->hashtable->getAverageLists() << endl;
}

LinkBasedQueue<City*>* CityDatabase::recommendCities(int numRecommendations, std::string currentCity, double curX, double curY, double distance, int popDens, double livingIndex, std::string climate, std::string language, std::string activities, std::string country) {
	int len = this->numCities;
	for (int i = 0; i < len; i++) {
		HitCounter* hc = this->hitCounters.front();
		City* city = hc->getCity();
		this->hitCounters.dequeue();
		this->hitCounters.enqueue(hc);
		if (city->getName() == currentCity) continue;

		if (distance > 0) {
			const double PI = 3.14159265;
			double LENGTH_OF_ONE_LONGITUDE = cos(abs(city->getY()) * PI / 180.0) * 69.172;
			double LENGTH_OF_ONE_LATITUDE = 69;
			double deltaX = (city->getX() - curX) * LENGTH_OF_ONE_LONGITUDE;
			double deltaY = (city->getY() - curY) * LENGTH_OF_ONE_LATITUDE;
			double calculatedDistance = sqrt((deltaX * deltaX) + (deltaY * deltaY));
			if (calculatedDistance < distance) hc->incrementCounter();
		}
		if (popDens > 0.00005 && abs(city->getPopulationDensity() - popDens) < 500) hc->incrementCounter();
		if (livingIndex > 0.00005 && abs(city->getLivingIndex() - livingIndex) < 2.5) hc->incrementCounter();
		if (climate != "" && city->getClimate() == climate) hc->incrementCounter();
		if (language != "" && city->getLanguage().find(language) != std::string::npos) hc->incrementCounter();
		if (country != "" && city->getCountry().find(country) != std::string::npos) hc->incrementCounter();

		if (activities != "") {
			std::string cityActivities = city->getActivities();
			int pos = cityActivities.find(",");
			std::string first = cityActivities.substr(0, pos);
			cityActivities.erase(0, pos + 2);
			pos = cityActivities.find(",");
			std::string second = cityActivities.substr(0, pos);
			cityActivities.erase(0, pos + 2);
			std::string third = cityActivities.substr(0, cityActivities.size());
			if (activities.find(first) != std::string::npos) hc->incrementCounter();
			if (activities.find(second) != std::string::npos) hc->incrementCounter();
			if (activities.find(third) != std::string::npos) hc->incrementCounter();
		}
	}

	int maxHits = 0;
	for (int i = 0; i < len; i++) {
		HitCounter* hc = this->hitCounters.front();
		this->hitCounters.dequeue();
		this->hitCounters.enqueue(hc);
		if (hc->getCounter() > maxHits) maxHits = hc->getCounter();
	}

	LinkBasedQueue<City*>* result = new LinkBasedQueue<City*>;
	int target = maxHits;
	numRecommendations = (numRecommendations > this->numCities) ? this->numCities : numRecommendations;
	while (result->getSize() < numRecommendations && target >= 0) {
		for (int i = 0; i < len; i++) {
			HitCounter* hc = this->hitCounters.front();
			this->hitCounters.dequeue();
			this->hitCounters.enqueue(hc);
			if (result->getSize() < numRecommendations && hc->getCounter() == target) result->enqueue(hc->getCity());
		}
		target--;
	}
	return result;
}

void CityDatabase::writeToFile(std::ofstream& out) {
	int len = this->data.getSize();
	City::setOutputType("toFile");
	for (int i = 0; i < len; i++) {
		City* c = this->data.front();
		this->data.dequeue();
		this->data.enqueue(c);

		out << c;

		if (i != len - 1)
			out << endl;
	}
	City::setOutputType("full");
}