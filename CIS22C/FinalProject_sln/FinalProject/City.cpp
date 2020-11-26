#include <string>
#include <iostream>
#include "City.h"

using namespace std;

std::string City::comparisonType = "name"; // Default sort type
std::string City::outputType = "full"; // Default output type

City::City()
{
	name = "";
	x = 0;
	y = 0;
	populationDensity = 0;
	livingIndex = 0;
	climate = "";
	language = "";
	activites = "";
	country = "";
}

City::City(std::string name, double x, double y, int popDens, double livingIndex, std::string climate, std::string language, std::string activities, std::string country)
{
	this->name = name;
	this->x = x;
	this->y = y;
	this->populationDensity = popDens;
	this->livingIndex = livingIndex;
	this->climate = climate;
	this->language = language;
	this->activites = activities;
	this->country = country;
}

int City::getX()
{
	return x;
}

void City::setX(int xx)
{
	x = xx;
}

int City::getY()
{
	return y;
}

void City::setY(int yy)
{
	y = yy;
}

void City::setName(std::string name)
{
	this->name = name;
}

std::string City::getName()
{
	return name;
}

int City::getPopulationDensity()
{
	return populationDensity;
}

void City::setPopulationDensity(int pDensity)
{
	populationDensity = pDensity;
}

double City::getLivingIndex()
{
	return livingIndex;
}

void City::setLivingIndex(double Lindex)
{
	livingIndex = Lindex;
}

std::string City::getClimate()
{
	return climate;
}

void City::setClimate(std::string c)
{
	climate = c;
}

std::string City::getLanguage()
{
	return language;
}

void City::setLanguage(std::string l)
{
	language = l;
}

std::string City::getActivities()
{
	return activites;
}

std::string City::getCountry()
{
	return country;
}

void City::setCountry(std::string c)
{
	country = c;
}

void City::setComparisonType(std::string compType)
{
	comparisonType = compType;
}

void City::setOutputType(std::string outType)
{
	outputType = outType;
}

bool operator>(City& c1, City& c2)
{
	if (c1.comparisonType == "name")
	{
		return c1.name > c2.name;
	}
	else if (c1.comparisonType == "populationDensity")
	{
		return c1.populationDensity > c2.populationDensity;
	}
	else if (c1.comparisonType == "climate")
	{
		return c1.climate > c2.climate;
	}
	else
	{
		throw "Error: Invalid comparison type";
	}
}

bool operator<(City& c1, City& c2)
{
	if (c1.comparisonType == "name")
	{
		return c1.name < c2.name;
	}
	else if (c1.comparisonType == "populationDensity")
	{
		return c1.populationDensity < c2.populationDensity;
	}
	else if (c1.comparisonType == "climate")
	{
		return c1.climate < c2.climate;
	}
	else
	{
		throw "Error: Invalid comparison type";
	}
}

bool operator==(City& c1, City& c2)
{
	if (c1.comparisonType == "name")
	{
		return c1.name == c2.name;
	}
	else if (c1.comparisonType == "populationDensity")
	{
		return c1.populationDensity == c2.populationDensity;
	}
	else if (c1.comparisonType == "climate")
	{
		return c1.climate == c2.climate;
	}
	else
	{
		throw "Error: Invalid comparison type";
	}
}

std::ostream& operator<<(std::ostream& out, City* c)
{
	if (City::outputType == "full")
	{
		out << c->getName() << std::endl;
		out << "Country: " << c->getCountry() << std::endl;
		out << "Activities: " << c->getActivities() << std::endl;
		out << "Languages: " << c->getLanguage() << std::endl;
		out << "Climate: " << c->getClimate() << std::endl;
		out << "x: " << c->getX() << " y: " << c->getY() << std::endl;
		out << "Population density: " << c->getPopulationDensity() << std::endl;
		out << "Cost of living index: " << c->getLivingIndex() << std::endl;
	}
	else if(City::outputType == "condensed")
	{
		out << c->getName();
	}
	else if (City::outputType == "toFile") {
		out << c->getName() << "\t" << c->getX() << "\t" << c->getY() << "\t" << c->getPopulationDensity() << "\t" << c->getLivingIndex() << "\t";
		out << ((c->getClimate() != "") ? c->getClimate() : " ") << "\t";
		out << ((c->getLanguage() != "") ? c->getLanguage() : " ") << "\t";
		out << ((c->getActivities() != "") ? c->getActivities() : " ") << "\t";
		out << ((c->getCountry() != "") ? c->getCountry() : " ");
	}
	else
	{
		throw "Error: Inavlid output type";
	}

	return out;
}

std::istream& operator>>(std::istream& in, City& city) {
	bool gotXCoord = false;
	double x;
	while (!gotXCoord) {
		try {
			cout << "Enter the x coordinate: ";
			string xCoord;
			getline(cin, xCoord);
			city.x = stod(xCoord);
			gotXCoord = true;
		}
		catch (invalid_argument e) {
			cout << "Invalid input, try again. ";
		}
	}

	bool gotYCoord = false;
	double y;
	while (!gotYCoord) {
		try {
			cout << "Enter the y coordinate: ";
			string yCoord;
			getline(cin, yCoord);
			city.y = stod(yCoord);
			gotYCoord = true;
		}
		catch (invalid_argument e) {
			cout << "Invalid input, try again. ";
		}
	}

	bool gotPopDens = false;
	int popDens;
	while (!gotPopDens) {
		try {
			cout << "Enter the population density: ";
			string popDens;
			getline(cin, popDens);
			city.populationDensity = stoi(popDens);
			gotPopDens = true;
		}
		catch (invalid_argument e) {
			cout << "Invalid input, try again. ";
		}
	}

	bool gotLivingIndex = false;
	double livingIndex;
	while (!gotLivingIndex) {
		try {
			cout << "Enter the living index: ";
			string livingIndex;
			getline(cin, livingIndex);
			city.livingIndex = stod(livingIndex);
			gotLivingIndex = true;
		}
		catch (invalid_argument e) {
			cout << "Invalid input, try again. ";
		}
	}

	cout << "Enter the climate: ";
	string climate;
	getline(cin, climate);
	city.climate = climate;

	cout << "Enter the main languages spoken: ";
	string language;
	getline(cin, language);
	city.language = language;

	cout << "Enter some popular activities, separated by commas: ";
	string activities;
	getline(cin, activities);
	city.activites = activities;

	cout << "Enter the country in which " << city.name << " is located: ";
	string country;
	getline(cin, country);
	city.country = country;

	return in;
}