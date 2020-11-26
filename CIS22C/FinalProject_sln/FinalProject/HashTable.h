#ifndef HASHTABLE_H
#define HASHTABLE_H

#include "HashNode.h"
#include "SinglyLinkedList.h"
#include "LinkNode.h"

template <typename T>
class HashTable
{
private:
	int size;
	SinglyLinkedList<HashNode<T>>* buckets;
	int numItems;
	int numCollisions;

public:
	/* This constructor creates an empty hash table with the specified size
		Pre: int - The size of the hash table to be created
		Post:
		Return:
	*/
	HashTable(int);

	/* This destructor frees dynamically allocated memory in the hash table when destroying it
		Pre:
		Post:
		Return:
	*/
	~HashTable();

	/* This function adds a data item with the specified key to the hash table
		Pre: std::string - The key to use for determining data's index
			 T& - The data to be added to the hash table
		Post:
		Return:
	*/
	void add(int, T);

	/* This function puts the data item with the specified key from the hash table into the specified reference
		Pre: std::string - The key to use for determining data's location
			 T& - the item to add the data in the hash table to
		Post: T& will contain the data found with the key in the hash table
		Return: true if successfully found an item with the key, false if not
	*/
	bool retrieve(int, T&, int&);

	/* This function removes a node from the hash table
		Pre: int - the key of the data to be removed
			 T - the data to be removed
		Post:
		Return:
	*/
	void remove(int, T);

	/* This function gets the number of items currently in the hash table
		Pre:
		Post:
		Return: the number of items currently in the hash table
	*/
	int getNumItems();

	/* This function calculates and returns the load factor of the hash table
		Pre:
		Post:
		Return: the load factor of the hash table
	*/
	double getLoadFactor();

	/* This function gets the number of collisions that occured in the process of adding data
		Pre:
		Post:
		Return: the number of collisions that occured during the process of adding data
	*/
	int getNumCollisions();

	/* This function gets the average number of nodes per list
		Pre:
		Post:
		Return: the average number of nodes per list
	*/
	double getAverageLists();

	/* This function gets the number of nodes in the longest linked list
		Pre:
		Post:
		Return: the number of nodes in the longest  linked list
	*/
	int getNumLongestLinkedList();

	/* This operator allows the hash table to be written to an output stream, simply by using its name
		Pre: std::ostream& - The output stream to write the hash table to
			 HashTable<T>& - The hash table to write to the output
		Post:
		Return: the output stream
	*/
	template <typename T>
	friend std::ostream& operator<<(std::ostream&, HashTable<T>&);

private:
	/* Hashes the key
		Pre: the key to be hashed
		Post:
		Return: a hash of the key
	*/
	int hash(int);
	
	/* Rehashes the keys and increases the table size if the table becomes too filled
		Pre:
		Post: The hashtable is resized and rehashed 
		Return:
	*/
	void rehash();
};

template <typename T>
HashTable<T>::HashTable(int size)
{
	this->size = size;
	buckets = new SinglyLinkedList<HashNode<T>>[size];

	numItems = 0;
	numCollisions = 0;
}

template <typename T>
HashTable<T>::~HashTable()
{
	for (int i = 0; i < size; i++)
	{
		buckets[i].clear();
	}

	delete[] buckets;
}

template <typename T>
void HashTable<T>::add(int key, T newEntry)
{
	if (getLoadFactor() > 0.5)
	{
		rehash();
	}

	int index = hash(key);
	HashNode<T>* newItem = new HashNode<T>(key, newEntry);

	if (buckets[index].getHead() == nullptr)
	{
		numItems++;
	}

	City::setComparisonType("populationDensity");
	buckets[index].add(*newItem);
	City::setComparisonType("name");
}

template <typename T>
bool HashTable<T>::retrieve(int key, T& userValue, int& numComparisons)
{
	int index = hash(key);

	LinkNode<HashNode<T>>* curptr = buckets[index].getHead();

	if (curptr == nullptr)
	{
		numComparisons++;
	}

	while (curptr != nullptr)
	{
		numComparisons++;
		if (curptr->getData().getKey() == key)
		{
			userValue = curptr->getData().getData();
			return true;
		}
		curptr = curptr->getNext();
	}

	return false;
}

template <typename T>
void HashTable<T>::remove(int key, T entry) {
	T result;
	int numCompares = 0;
	bool found = retrieve(key, result, numCompares);
	if (found) {
		int index = hash(key);

		HashNode<T> toBeRemoved(key, entry);
		buckets[index].remove(toBeRemoved);
	}
}

template <typename T>
int HashTable<T>::getNumItems()
{
	return numItems;
}


template <typename T>
double HashTable<T>::getLoadFactor()
{
	return static_cast<double>(numItems) / size;
}

template <typename T>
int HashTable<T>::getNumCollisions()
{
	return numCollisions;
}

template <typename T>
double HashTable<T>::getAverageLists()
{
	double sum = 0;
	for (int i = 0; i < size; i++)
	{
		sum += buckets[i].getSize();
	}

	return sum / size;
}

template <typename T>
int HashTable<T>::getNumLongestLinkedList()
{
	int currentLargest = buckets[0].getSize();
	for (int i = 1; i < size; i++)
	{
		if (buckets[i].getSize() > currentLargest)
		{
			currentLargest = buckets[i].getSize();
		}
	}

	return currentLargest;
}

template <typename T>
std::ostream& operator<<(std::ostream& out, HashTable<T>& table)
{
	for (int i = 0; i < table.size; i++)
	{
		if (!table.buckets[i].isEmpty()) out << table.buckets[i] << std::endl;
	}
	return out;
}

template <typename T>
int HashTable<T>::hash(int key)
{
	int tempKey = key;
	while (tempKey % 10 == 0)
	{
		tempKey /= 10;
	}

	int index = key + tempKey;

	std::string str = std::to_string(index);
	int len = str.length();

	index += len;
	index = index % size;

	return index;
}

template <typename T>
void HashTable<T>::rehash()
{
	SinglyLinkedList<HashNode<T>>* temp = buckets;
	int tempSize = size;

	size *= 2;
	buckets = new SinglyLinkedList<HashNode<T>>[size];

	for (int i = 0; i < tempSize; i++)
	{
		temp[i].getHead();
		while (temp[i].getHead() != nullptr)
		{
			add(temp[i].getHead()->getData().getKey(), temp[i].getHead()->getData().getData());
			temp[i].remove(temp[i].getHead()->getData());
		}
	}

	delete[] temp;
}

#endif // !HASHTABLE_H
