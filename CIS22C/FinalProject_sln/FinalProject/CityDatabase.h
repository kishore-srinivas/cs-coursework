#ifndef CITYDATABASE_H
#define CITYDATABASE_H

#include <iostream>
#include <fstream>
#include "city.h"
#include "LinkBasedQueue.h"
#include "BST.h"
#include "HashTable.h"
#include "HitCounter.h"

class CityDatabase
{
private:
	LinkBasedQueue<City*> data;
	LinkBasedQueue<HitCounter*> hitCounters;
	BST<City*>* bst;
	HashTable<City*>* hashtable;
	SinglyLinkedList<City*>* linkedlist;
	int numCities;

public:
	/* This constructor creates a city database with values of an input file
		Pre: ifstream&- the file to read data from
		Post:
		Return:
	*/
	CityDatabase(std::ifstream& input);

	/* This function returns the bst member variable
		Pre:
		Post:
		Return: the bst member varibale
	*/
	BST<City*>* getBST();

	/* This function returns the hashtable member variable
		Pre:
		Post:
		Return: the hashtable member varibale
	*/
	HashTable<City*>* getHashTable();

	/* This function returns the linkedlist member variable
		Pre:
		Post:
		Return: the linkedlist member varibale
	*/
	SinglyLinkedList<City*>* getLinkedList();

	/* This function returns a city from the bst, based on an input name
		Pre: std::string - the name for which to compare city names to
		Post:
		Return: the city found, nullptr if not
	*/
	City* findByName(std::string name);

	/* This function returns a city from the hash table, based on an input population density
		Pre: int - the population density to find in the hash table
		Post:
		Return: the city found, nullptr if not
	*/
	City* findByPopulationDensity(int popDens);

	/* This function returns a city from the linkedlist, based on an input climate
		Pre: std::string - the climate for which to compare cities in the linked list to
		Post:
		Return: the city found, nullptr if not
	*/
	City* findByClimate(std::string climate);

	/* This function prints the database in sorted order using the bst
		Pre: std::ostream& - the output stream to write output to
		Post:
		Return:
	*/
	void printSorted(std::ostream&);

	/* This function prints the database in a tree-like fashion
		Pre: std::ostream& - the output stream to tree to
		Post:
		Return:
	*/
	void print(std::ostream&);

	/* This function adds a city to the database updating all member structures
		Pre: City* - the city to be added to the database
		Post:
		Return:
	*/
	void addCity(City* city);

	/* This function removes a city from the database updating all member structures
		Pre: City* - the city to be removed to the database
		Post:
		Return:
	*/
	void removeCity(City* city);

	/* This function displays the efficiency of all the structures
		Pre: std::ostream& - the output stream to write efficiency to
		Post:
		Return:
	*/
	void displayEfficiency(std::ostream&);

	/* This function returns a queue of places recommended by the database based on the gives criteria
		Pre: int - the number of recommendations to get
			 double - the current x value of the user
			 double - the current y value of the user
			 double - the distance of the user
			 int - the population density the user would like
			 double - the living index the user would like
			 std::string - the climate the user would like
			 std::string - the languages the user would like
			 std::string - the activities the user would like
			 std::string - the country the user would like
		Post:
		Return: A queue of recommended cities
	*/
	LinkBasedQueue<City*>* recommendCities(int numRecommendations, std::string currentCity, double curX, double curY, double distance = 0, int popDens = 0, double livingIndex = 0, std::string climate = "", std::string language = "", std::string activities = "", std::string country = "");

	/* This function writes the database to a file, no particular order
		Pre: std::ofstream& - the output file to write data to
		Post:
		Return:
	*/
	void writeToFile(std::ofstream& output);

private:
	City* createCity(std::string line);
	void populateBST();
	void populateHashTable();
	void populateLinkedList();
};

#endif // !CITYDATABASE_H