#ifndef LINKBASEDQUEUE_H
#define LINKBASEDQUEUE_H

#include "SinglyLinkedList.h"

template <typename T>
class LinkBasedQueue : protected SinglyLinkedList<T>
{
public:
	/* This function adds a specified data to the rear of the queue
		Pre: T& - The data to be added to the queue
		Post:
		Return:
	*/
	void enqueue(const T&);

	/* This function removes the item at the front of the queue
		Pre:
		Post:
		Return:
	*/
	void dequeue();

	/* This function removes the item from the queue
		Pre: T& - the item to be removed from the queue
		Post:
		Return:
	*/
	void remove(const T&);

	/* This function gets the data of the front of the queue
		Pre:
		Post:
		Return: The data of the front of the queue
	*/
	T& front() const;

	/* This function gets the data of the rear of the queue
		Pre:
		Post:
		Return: The data of the front of the queue
	*/
	T& rear() const;

	/* This function gets rid of all items in the queue
		Pre:
		Post:
		Return:
	*/
	void empty();

	/* This function gets the number of items currently in the queue
		Pre:
		Post:
		Return: the number of items in the queue
	*/
	int getSize() const;

	/* This operator allows the queue to be written to output using its name
		Pre: std::ostream& - the output stream
			 LinkBasedQueue<T>& - the queue to be written to output
		Post:
		Return: the output stream
	*/
	template <typename T>
	friend std::ostream& operator<<(std::ostream&, LinkBasedQueue<T>&);
};

template <typename T>
void LinkBasedQueue<T>::enqueue(const T& newEntry)
{
	SinglyLinkedList<T>::add(newEntry, getSize());
}

template <typename T>
void LinkBasedQueue<T>::dequeue()
{
	SinglyLinkedList<T>::removeFromPos(0);
}

template <typename T>
void LinkBasedQueue<T>::remove(const T& item)
{
	SinglyLinkedList<T>::remove(item);
}

template <typename T>
T& LinkBasedQueue<T>::front() const
{
	if (getSize() != 0)
	{
		return SinglyLinkedList<T>::getHead()->getData();
	}
	else
	{
		throw "Error: Cannot get the front of an empty queue";
	}
}

template <typename T>
T& LinkBasedQueue<T>::rear() const
{
	if (getSize() != 0)
	{
		return SinglyLinkedList<T>::getTail()->getData();
	}
	else
	{
		throw "Error: Cannot get the rear of an empty queue";
	}
}

template <typename T>
void LinkBasedQueue<T>::empty()
{
	SinglyLinkedList<T>::clear();
}

template <typename T>
int LinkBasedQueue<T>::getSize() const
{
	return SinglyLinkedList<T>::getSize();
}

template <typename T>
std::ostream& operator<<(std::ostream& out, LinkBasedQueue<T>& queue)
{
	LinkNode<T>* curPtr = queue.getHead();

	while (curPtr != nullptr)
	{
		if (curPtr != queue.getTail())
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

#endif // !LINKBASEDQUEUE_H