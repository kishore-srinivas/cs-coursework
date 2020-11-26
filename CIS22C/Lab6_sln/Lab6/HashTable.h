#include "Person.h"
#include "array.h"

class HashTable : public TemplateArray<Person> {
private:
	int size;
	int numCollisions;
	double loadFactor;
	void calculateLoadFactor();
	int hash(string birthday);
	int quadraticProbe(int start, int n);
	int linearProbe(int start, int n);
public:
	/* constructor for HashTable
	pre: size - the number of elements to be stored in the hash table
	*/
	HashTable(int size);
	
	/* destructor for HashTable */
	~HashTable();
	
	/* adds a Person* object to the hash table
	pre: item - the Person* object to be added
	post: hash table now contains item at its appropriate location
	*/
	void add(Person* item);
	
	/* removes a Person* object from the hash table
	pre: item - the Person* object to be removed
	post: hash table no longer contains that item
	*/
	void remove(Person* item);
	
	/* removes a Person* object from the hash table, given by the name
	pre: name - the name attribute of the Person* object to be removed
	post: hash table no longer contains the Person* object specified by name
	*/
	void remove(string name);
	
	/* finds the Person* object with the given birthday
	pre: birthday - a string in the format yyyy-mm-dd
	return: the Person* object with the given birthday if it exists, nullptr otherwise
	*/
	Person* find(string birthday);
	
	/* gets the item at the specified index
	pre: index - the index to look at
	return: the Person* object at that index
	*/
	Person* getItemAtIndex(int index);
	
	/* returns the size of the hash table
	return: an int for the size of the hash table
	*/
	int getSize();
	
	/* returns the load factor of the hash table
	return: a double for the load factor
	*/
	double getLoadFactor();
	
	/* returns the number of collisions
	return: an int for the number of collisions
	*/
	int getNumCollisions();
};
