#include <iostream>
#include <fstream>
#include "CityDatabase.h"
#include "LinkBasedQueue.h"
using namespace std;

const int MAX_CHOICE = 9;
const int MIN_CHOICE = 0;

/* Displays the menu to the user
	Pre: 
	Post: The menu is shown to the user
	Return:
*/
void displayMenu()
{
	cout << endl;
	cout << "Select from the following menu: " << endl;
	cout << "1. Add city" << endl;
	cout << "2. Modify city" << endl;
	cout << "3. Remove city" << endl;
	cout << "4. Display city" << endl;
	cout << "5. List cities (Hash Table sequence)" << endl;
	cout << "6. List cities (sorted)" << endl;
	cout << "7. Print indented tree" << endl;
	cout << "8. Display efficiency" << endl;
	cout << "9. Recommend destination" << endl;
	cout << "0. Quit" << endl;
}

/* Verifies the user's input 
	Pre: the int the user entered into the prompt from main
	Post: an int that is verified to be within bounds
	Return:
*/
void getUserChoice(int& userChoice)
{
	cout << "Enter your choice: ";
	do
	{
		while (!(cin >> userChoice)) // Error check for letters or symbols
		{
			cout << "Invalid input please try again: ";
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
		}
		if (userChoice < MIN_CHOICE || userChoice > MAX_CHOICE) // Error check for range
			cout << "Make sure the number is within the 0 to 9 range. Try again: ";
	} while (userChoice < MIN_CHOICE || userChoice > MAX_CHOICE);
	cin.get(); // removes the \n character present in the stream
}

/* Facilitates the addition of a city to the database by the user
	Pre: a pointer to the initialized cities
	Post: a city with a unique name within the database is created and passed to the database for further development 
	Return:
*/
void addCity(CityDatabase& cities)
{
	cout << endl << "Enter the name of the city you would like to add: ";
	string name;
	getline(cin, name);
	City* res = cities.findByName(name);
	if (res != nullptr) {
		cout << name << " already exists in database. " << endl;
		return;
	}
	City* c = new City();
	c->setName(name);
	cin >> *c;
	cities.addCity(c);
}

/* Allows user to modify a city in the database 
	Pre: a pointer to the initialized cities
	Post: the city is modified accordingly
	Return:
*/
void modifyCity(CityDatabase& cities) {
	cout << endl << "Enter the name of the city you would like to modify: ";
	string name;
	getline(cin, name);
	City* city = cities.findByName(name);
	if (city == nullptr) {
		cout << name << " does not exist in database. " << endl;
		return;
	}
	bool continueModifying = true;
	while (continueModifying) {
		string toModify;
		cout << "What would you like to modify? (q to quit) ";
		getline(cin, toModify);
		if (toModify == "q" || toModify == "Q") {
			continueModifying = false;
			continue;
		}
		else if (toModify == "x") {
			string input;
			cout << "Enter the new x coordinate: ";
			getline(cin, input);
			double value = -9999;
			try {
				value = stod(input);
			}
			catch (invalid_argument) {}
			if (value != -9999) city->setX(value);
		}
		else if (toModify == "y") {
			string input;
			cout << "Enter the new y coordinate: ";
			getline(cin, input);
			double value = -9999;
			try {
				value = stod(input);
			}
			catch (invalid_argument) {}
			if (value != -9999) city->setY(value);
		}
		else if (toModify == "population density") {
			string input;
			cout << "Enter the new population density: ";
			getline(cin, input);
			int value = -9999;
			try {
				value = stoi(input);
			}
			catch (invalid_argument) {}
			if (value != -9999) city->setPopulationDensity(value);
		}
		else if (toModify == "living index") {
			string input;
			cout << "Enter the new living index: ";
			getline(cin, input);
			double value = -9999;
			try {
				value = stod(input);
			}
			catch (invalid_argument) {}
			if (value != -9999) city->setLivingIndex(value);
		}
		else if (toModify == "climate") {
			string input;
			cout << "Enter the new climate: ";
			getline(cin, input);
			if (input != "") city->setClimate(input);
		}
		else if (toModify == "languages") {
			string input;
			cout << "Enter the new languages: ";
			getline(cin, input);
			if (input != "") city->setClimate(input);
		}
		else if (toModify == "activities") {
			string input;
			cout << "Enter the new activities: ";
			getline(cin, input);
			if (input != "") city->setClimate(input);
		}
		else if (toModify == "country") {
			string input;
			cout << "Enter the new country: ";
			getline(cin, input);
			if (input != "") city->setClimate(input);
		}
		else {
			cout << "Invalid input, please try again." << endl;
		}
	}
}

/* Allows the user to delete a city object from the database
	Pre: a pointer to the initialized cities
	Post: the city object that should be deleted is found and passed to the database for deletion
	Return:
*/
void removeCity(CityDatabase& cities)
{
	cout << endl << "Enter the name of the city you would like to remove: ";
	string name;
	getline(cin, name);
	City* res = cities.findByName(name);
	if (res == nullptr) {
		cout << name << " does not exist in database. " << endl;
		return;
	}
	cities.removeCity(res);
}

/* Allows the menu to print out a full city object
	Pre: a pointer to the initialized cities
	Post: searches for the city object based on the choice of the user and displays the searched city
	Return:
*/
void displayCity(CityDatabase& cities)
{
	string searchType;
	cout << "Enter the category by which to search for a city: ";
	getline(cin, searchType);

	City* searchedCity;
	if (searchType == "name")
	{
		string name;
		cout << "Enter the name to search by: ";
		getline(cin, name);

		searchedCity = cities.findByName(name);
	}
	else if (searchType == "population density")
	{
		int popDens;
		cout << "Enter the population density to search by: ";
		cin >> popDens;

		searchedCity = cities.findByPopulationDensity(popDens);
	}
	else if (searchType == "climate")
	{
		string climate;
		cout << "Enter the climate type to search by: ";
		cin >> climate;

		searchedCity = cities.findByClimate(climate);
	}
	else
	{
		cout << "Invalid search category" << endl;
		searchedCity = nullptr;
	}

	cout << endl;
	if (searchedCity != nullptr)
	{
		cout << searchedCity << endl;
	}
	else
	{
		cout << "No city found" << endl;
	}
}

/* Allows the menu to call a print function in the database class based in order of appearance in the hash function
	Pre: a pointer to the initialized cities
	Post: sets the output to either a full or condensed form
	Return:
*/
void listCitiesHashTable(CityDatabase& cities)
{
	string form;
	cout << "Would you like to see a full or condensed form (f/c)? ";
	getline(cin, form);
	if (form == "c" || form == "C") City::setOutputType("condensed");

	cout << *(cities.getHashTable());

	City::setOutputType("full");
}

/* Allows the menu to call an alphabetically sorted print function in the database class 
	Pre: a pointer to the initialized cities
	Post: sets the output to either a full or condensed form
	Return:
*/
void listCitiesSorted(CityDatabase& cities)
{
	string form;
	cout << "Would you like to see a full or condensed form (f/c)? ";
	getline(cin, form);
	if (form == "c" || form == "C") City::setOutputType("condensed");

	cities.printSorted(cout);
	cout << endl;

	City::setOutputType("full");

}

/* Allows the menu to call a print function within the database class
	Pre: a pointer to the initialized cities
	Post:
	Return:
*/
void printTree(CityDatabase& cities)
{
	cities.print(cout);
	cout << endl;
}

/* Allows the menu to call an efficiency function within the database class
	Pre: a pointer to the initialized cities
	Post: 
	Return:
*/
void displayEfficiency(CityDatabase& cities)
{
	cout << "Attempting to display Gotham City, a city which does not exist..." << endl;
	cities.displayEfficiency(cout);
}

/* This function collects user input and passes the values to the database class for further processing
	Pre: a pointer to the initialized cities
	Post: passes the user's values to the queue of city objects
	Return: 
*/
void recommendLocation(CityDatabase& cities)
{
	City* searchedCity;
	string name;
	string useCoordinates;
	double x, y;
	bool foundLocation = false;

	while (!foundLocation)
	{
		cout << "Enter the city you are currently in: ";
		getline(cin, name);
		searchedCity = cities.findByName(name);

		if (searchedCity != nullptr)
		{
			x = searchedCity->getX();
			y = searchedCity->getY();

			foundLocation = true;
		}
		else
		{
			cout << "City could not be found in the database, would you like to use coordinates? (Y/N): ";
			cin >> useCoordinates;
			cin.ignore();

			if (useCoordinates == "Y")
			{
				cout << "Enter the x coordinate: ";
				cin >> x;
				cout << "Enter the y coordinate: ";
				cin >> y;

				foundLocation = true;
				cin.ignore();
			}
		}
	}

	string dist;
	cout << "Enter the maximum distance in miles you are willing to travel: ";
	getline(cin, dist);
	int distance = 0;
	try {
		distance = stoi(dist);
	}
	catch (invalid_argument) {}

	string density;
	cout << "Enter the preferred population density: ";
	getline(cin, density);
	int popDens = 0;
	try {
		popDens = stoi(density);
	}
	catch (invalid_argument) {}

	string lIndex;
	cout << "Enter the preferred cost of living, based on the cost of living index: ";
	getline(cin, lIndex);
	double livingIndex = 0;
	try {
		livingIndex = stod(lIndex);
	}
	catch (invalid_argument) {}

	string climate;
	cout << "Enter the preferred climate: ";
	getline(cin, climate);

	string languages;
	cout << "Enter the preferred languages, seperated by commas: ";
	getline(cin, languages);

	string activities;
	cout << "Enter any attractions you would like to see, seperated by commas: ";
	getline(cin, activities);

	string country;
	cout << "Enter the preferred country: ";
	getline(cin, country);

	LinkBasedQueue<City*>* results = cities.recommendCities(5, name, x, y, distance, popDens, livingIndex, climate, languages, activities, country);

	cout << endl << "--- RESULTS ---" << endl;
	cout << *results << endl;
}

/* International Travel Guide(Kishore Srinivas, Jnana Uhuru, Ajeet Kotturu) Project Coordinator: Philipp Droste
	the driver function that calls methods to allow the user to interact with the travel guide/resource program
	@return 0 when the program is done with the demonstration
*/
int main()
{
	ifstream inputFile;
	int userChoice;

	inputFile.open("..\\FinalProject\\Cities.txt");
	CityDatabase cities(inputFile);
	inputFile.close();

	cout << "International Cities Travel Guide" << endl;

	do
	{
		displayMenu();
		getUserChoice(userChoice);

		switch (userChoice)
		{
		case 1:
			addCity(cities);
			break;
		case 2:
			modifyCity(cities);
			break;
		case 3:
			removeCity(cities);
			break;
		case 4:
			displayCity(cities);
			break;
		case 5:
			listCitiesHashTable(cities);
			break;
		case 6:
			listCitiesSorted(cities);
			break;
		case 7:
			printTree(cities);
			break;
		case 8:
			displayEfficiency(cities);
			break;
		case 9:
			recommendLocation(cities);
		default:
			break;
		}
	} while (userChoice != 0); // Add global constants later

	cout << endl << "Writing back to file... ";

	ofstream outFile;
	outFile.open("..\\FinalProject\\Cities.txt");
	cities.writeToFile(outFile);
	outFile.close();

	City::setOutputType("full");
	ofstream hashTableOut;
	hashTableOut.open("..\\FinalProject\\HashtableOutput.txt");
	hashTableOut << *(cities.getHashTable());
	hashTableOut.close();

	cout << "Done." << endl;

	system("pause");
	return 0;
}
