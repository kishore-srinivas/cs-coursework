#pragma once
#include "LinkedList.h"
#include <iostream>

template <typename T>
class Queue;

template <typename T>
std::ostream& operator<<(std::ostream& os, Queue<T> queue);

template <typename T>
class Queue : private LinkedList<T> {
public:
	Queue(int sortType = 2) : LinkedList<T>(sortType) {}
	void enqueue(LinkedNode<T>& node);
	T& dequeue();
	T& getFront();
	T& getRear();
	void empty();
	friend std::ostream& operator<< <>(std::ostream& os, Queue<T> list);
};

/* adds nodes to the front of the queue
	@pre a pointer to a LinkedNode
	@post the queue now has that node at the front of it
*/
template <typename T>
void Queue<T>::enqueue(LinkedNode<T>& node) {
	LinkedList<T>::insertNode(node, LinkedList<T>::getNumNodes());
}

/* removes nodes from the rear of the queue
	@post the last node in the queue is removed
	@return the data in the node that was dequeued
*/
template <typename T>
T& Queue<T>::dequeue() {
	return LinkedList<T>::removeNode()->getData();
}

/* returns the value of the front node
	@return the data in the front node
*/
template <typename T>
T& Queue<T>::getFront() {
	return LinkedList<T>::getStartNode()->getData();
}

/* returns the value of the rear node
	@return the data in the rear node
*/
template <typename T>
T& Queue<T>::getRear() {
	return LinkedList<T>::getEndNode()->getData();

}

/* removes all data from the whole queue
	@post there is no longer data in the queue
*/
template <typename T>
void Queue<T>::empty() {
	LinkedList<T>::emptyList();
}

/* overloads the insertion operator so that it can print out the queue
	@pre Stack object initialized with values in its queue
	@return an ostream object containing streams with the queue values
*/
template <typename T>
std::ostream& operator<< <>(std::ostream& os, Queue<T> queue) {
	const LinkedList<T> list(queue);
	os << list;
	return os;
}
