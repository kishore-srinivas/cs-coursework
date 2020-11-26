#include "city.h"

class HitCounter {
private:
	int counter;
	City* city;
public:
	/* Constructor for the object that will manage how many matches are made by each city to the user's input
		Pre: a city object to tie itself to
		Post: a hit counter object is made with an initial hit amount of zero
		Return:
	*/
	HitCounter(City* city);

	/* Default destructor for the HitCounter objects
		Pre: a HitCounter object to delete
		Post: the object is deleted
		Return:
	*/
	~HitCounter();

	/* Increments the counter when called
		Pre: a HitCounter object to increment
		Post: the Hitcounter is incremented
		Return:
	*/
	void incrementCounter();

	/* returns the city the counter is associated with
		Pre: a HitCounter object
		Post:
		Return: the city object of the counter
	*/
	City* getCity();

	/* returns the value of the counter
		Pre: a HitCounter object
		Post:
		Return: an int that has the value of the number of matches of the counter
	*/
	int getCounter();
};
