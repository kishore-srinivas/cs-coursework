#ifndef SINGLYLINKEDLIST_H
#define SINGLYLINKEDLIST_H

#include <string>
#include "LinkNode.h"

template <typename T>
class SinglyLinkedList
{
private:
	int count;
	LinkNode<T>* head;
	LinkNode<T>* tail;
	std::string sortType;

public:
	/* This constructor creates an empty singly linked list
		Pre:
		Post:
		Return:
	*/
	SinglyLinkedList();

	/* This constructor creates an empty singly linked list with a specific sort type
		Pre: std::string - the type (ascending, descending, or unsorted) for which to base insertion off of
		Post:
		Return:
	*/
	SinglyLinkedList(std::string);

	/* This destructor destroys the singly linked list, first clearing all its nodes
		Pre:
		Post:
		Return:
	*/
	~SinglyLinkedList();

	/* This function adds a node to the singly linked list based on the sortType parameter
		Pre: const T& - The data of the node to be inserted
		Post:
		Return:
	*/
	void add(const T&);

	/* This function adds a node to an unsorted singly linked list, at a specific index
		Pre: const T& - The data of the node to be inserted
			 int - the index at which to insert the data
		Post:
		Return: true for successful addition, false for unsuccessful
	*/
	bool add(const T&, int);

	/* This function removes a node with certain data from a singly linked list
		Pre: const T& - The data of the node to be removed
		Post:
		Return:
	*/
	void remove(const T&);

	/* This function removes the node at the specified index
		Pre: int - the index of the node to be removed
		Post:
		Return:
	*/
	void removeFromPos(int);

	/* This function removes all nodes from a singly linked list
		Pre:
		Post:
		Return:
	*/
	void clear();

	/* This function checks whether the list has no nodes
		Pre:
		Post:
		Return: true or false
	*/
	bool isEmpty() const;

	/* This function checks whether the list has a node with specific data
		Pre: const T& - The data for which to find the node of
		Post:
		Return: true or false
	*/
	bool contains(const T&) const;

	/* This function gets the number of occurences of nodes with a certain data value
		Pre: const T& - The data for which to accumulate occurences of
		Post:
		Return: the number of occurences of the data value
	*/
	int getFrequencyOf(const T&) const;

	/* This function gets the number nodes in the list
		Pre:
		Post:
		Return: the number of nodes in the list
	*/
	int getSize() const;

	/* This function gets an item with data of the first paramter, and puts it in the second parameter, tracking number of compares
		Pre: T& - The data to search
			 T& - The data searched
			 int& - the number of comparisons during the process
		Post: second parameter will contain the searched data, and the third will contain the number of comparisons
		Return: true or false
	*/
	bool search(T&, T&, int&);

	/* This function gets the head of the linked list
		Pre:
		Post:
		Return: the head of the linked list
	*/
	LinkNode<T>* getHead();

	/* This operator allows singly linked lists to be written to output using their names
		Pre: std::ostream& - output stream
			 SinglyLinkedList<T>& - the singly linked list to write to the output stream
		Post:
		Return: the output stream
	*/
	template<typename T>
	friend std::ostream& operator<<(std::ostream&, SinglyLinkedList<T>&);

protected:
	LinkNode<T>* getHead() const;
	LinkNode<T>* getTail() const;
};

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList()
{
	count = 0;
	head = nullptr;
	tail = nullptr;
	sortType = "unsorted";
}

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList(std::string sType)
{
	count = 0;
	head = nullptr;
	tail = nullptr;

	if (sType == "ascending" || sType == "descending")
	{
		sortType = sType;
	}
	else
	{
		sortType = "unsorted";
	}
}

template <typename T>
SinglyLinkedList<T>::~SinglyLinkedList()
{
	clear();
}

template <typename T>
void SinglyLinkedList<T>::add(const T& newEntry)
{
	LinkNode<T>* nEntry = new LinkNode<T>(newEntry);
	LinkNode<T>* curPtr = head;

	if (count == 0)
	{
		head = nEntry;
		tail = nEntry;
	}
	else
	{
		if (sortType == "ascending")
		{
			if (nEntry->getData() < curPtr->getData())
			{
				nEntry->setNext(curPtr);
				head = nEntry;
			}
			else
			{
				while (curPtr != tail && !(nEntry->getData() < curPtr->getNext()->getData()))
				{
					curPtr = curPtr->getNext();
				}

				if (curPtr == tail)
				{
					curPtr->setNext(nEntry);
					tail = nEntry;
				}
				else
				{
					nEntry->setNext(curPtr->getNext());
					curPtr->setNext(nEntry);
				}
			}
		}
		else if (sortType == "descending")
		{
			if (!(nEntry->getData() < curPtr->getData()))
			{
				nEntry->setNext(curPtr);
				head = nEntry;
			}
			else
			{
				while (curPtr != tail && nEntry->getData() < curPtr->getNext()->getData())
				{
					curPtr = curPtr->getNext();
				}

				if (curPtr == tail)
				{
					curPtr->setNext(nEntry);
					tail = nEntry;
				}
				else
				{
					nEntry->setNext(curPtr->getNext());
					curPtr->setNext(nEntry);
				}
			}
		}
		else
		{
			tail->setNext(nEntry);
			tail = nEntry;
		}
	}

	count++;
}

template <typename T>
bool SinglyLinkedList<T>::add(const T& newEntry, int index)
{
	if (sortType == "unsorted")
	{
		LinkNode<T>* nEntry = new LinkNode<T>(newEntry);
		LinkNode<T>* curPtr = head;
		int currentPosition = 0;

		if (count == 0)
		{
			head = nEntry;
			tail = nEntry;
		}
		else
		{
			if (index <= currentPosition)
			{
				nEntry->setNext(head);
				head = nEntry;
			}
			else
			{
				while (currentPosition != index - 1 && curPtr != tail)
				{
					curPtr = curPtr->getNext();
					currentPosition++;
				}

				if (curPtr == tail)
				{
					curPtr->setNext(nEntry);
					tail = nEntry;
				}
				else
				{
					nEntry->setNext(curPtr->getNext());
					curPtr->setNext(nEntry);
				}
			}
		}

		count++;
		return true;
	}
	else
	{
		return false;
	}
}

template <typename T>
void SinglyLinkedList<T>::remove(const T& anEntry)
{
	LinkNode<T>* curPtr = head;
	LinkNode<T>* prePtr = nullptr;

	while (curPtr != nullptr)
	{
		if (curPtr->getData() == anEntry)
		{
			if (curPtr == head)
			{
				head = curPtr->getNext();
				delete curPtr;
				curPtr = head;
			}
			else
			{
				if (curPtr == tail)
				{
					tail = prePtr;
				}

				prePtr->setNext(curPtr->getNext());
				delete curPtr;
				curPtr = prePtr->getNext();
			}

			count--;
		}
		else
		{
			prePtr = curPtr;
			curPtr = curPtr->getNext();
		}
	}
}

template <typename T>
void SinglyLinkedList<T>::removeFromPos(int index)
{
	LinkNode<T>* curPtr = head;
	LinkNode<T>* prePtr = nullptr;
	int currentPosition = 0;

	if (index >= currentPosition && index < count)
	{
		while (currentPosition < index && curPtr != tail)
		{
			prePtr = curPtr;
			curPtr = curPtr->getNext();
			currentPosition++;
		}

		if (curPtr == head)
		{
			head = curPtr->getNext();
			delete curPtr;
		}
		else
		{
			if (curPtr == tail)
			{
				tail = prePtr;
			}

			prePtr->setNext(curPtr->getNext());
			delete curPtr;
		}

		count--;
	}
}

template <typename T>
void SinglyLinkedList<T>::clear()
{
	LinkNode<T>* curPtr = head;
	LinkNode<T>* tempPtr = curPtr;

	while (curPtr != nullptr)
	{
		tempPtr = curPtr;
		curPtr = curPtr->getNext();
		delete tempPtr;
	}

	head = nullptr;
	tail = nullptr;
	count = 0;
}

template <typename T>
bool SinglyLinkedList<T>::isEmpty() const
{
	return count == 0;
}

template <typename T>
bool SinglyLinkedList<T>::contains(const T& anEntry) const
{
	LinkNode<T>* curPtr = head;

	while (curPtr != nullptr)
	{
		if (curPtr->getData() == anEntry)
		{
			return true;
		}
		curPtr = curPtr->getNext();
	}

	return false;
}

template <typename T>
int SinglyLinkedList<T>::getFrequencyOf(const T& anEntry) const
{
	LinkNode<T>* curPtr = head;
	int freq = 0;

	while (curPtr != nullptr)
	{
		if (curPtr->getData() == anEntry)
		{
			freq++;
		}
		curPtr = curPtr->getNext();
	}

	return freq;
}

template <typename T>
int SinglyLinkedList<T>::getSize() const
{
	return count;
}

template <typename T>
bool SinglyLinkedList<T>::search(T& compareValue, T& userValue, int& numComparisons)
{
	LinkNode<T>* curPtr = head;

	while (curPtr != nullptr)
	{
		numComparisons++;
		if (*(curPtr->getData()) == *compareValue)
		{
			userValue = curPtr->getData();
			return true;
		}
		curPtr = curPtr->getNext();
	}

	return false;
}

template <typename T>
LinkNode<T>* SinglyLinkedList<T>::getHead()
{
	return head;
}

template <typename T>
std::ostream& operator<<(std::ostream& out, SinglyLinkedList<T>& list)
{
	LinkNode<T>* curPtr = list.getHead();

	while (curPtr != nullptr)
	{
		if (curPtr != list.getTail())
		{
			out << curPtr->getData() << "\n";
			curPtr = curPtr->getNext();
		}
		else
		{
			out << curPtr->getData();
			curPtr = curPtr->getNext();
		}
	}

	return out;
}

template <typename T>
LinkNode<T>* SinglyLinkedList<T>::getHead() const
{
	return head;
}

template <typename T>
LinkNode<T>* SinglyLinkedList<T>::getTail() const
{
	return tail;
}

#endif // !SINGLYLINKEDLIST_H