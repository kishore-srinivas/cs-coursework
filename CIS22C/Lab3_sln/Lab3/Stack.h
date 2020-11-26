#pragma once
#include "LinkedList.h"
#include <iostream>

template <typename T>
class Stack;

template <typename T>
std::ostream& operator<<(std::ostream& os, Stack<T> stack);

template <typename T>
class Stack : private LinkedList<T> {
public:
	Stack(int sortType = 2) : LinkedList<T>(sortType) {}
	void push(LinkedNode<T>& node);
	T& pop();
	T& peek();
	void empty();
	friend std::ostream& operator<< <>(std::ostream& os, Stack<T> stack);
};

/* adds a node to the top of the stack
	@pre a pointer to a LinkedNode
	@post the stack now has that node at the top of it
*/
template <typename T>
void Stack<T>::push(LinkedNode<T>& node) {
	LinkedList<T>::addNode(node);
}

/* removes nodes from the top of the stack
	@post the top node in the stack is removed
	@return the data in the node that was popped
*/
template <typename T>
T& Stack<T>::pop() {
	return LinkedList<T>::removeNode()->getData();
}

/* returns data from the top of the stack
	@return the data in the top node of the stack
*/
template <typename T>
T& Stack<T>::peek() {
	return LinkedList<T>::getStartNode()->getData();
	//return data;
}

/* removes all data from the whole stack
	@post there is no longer data in the stack
*/
template <typename T>
void Stack<T>::empty() {
	LinkedList<T>::emptyList();
}

/* overloads the insertion operator so that it can print out the stack
	@pre Stack object initialized with values in its stack
	@return an ostream object containing streams with the stack values
*/
template <typename T>
std::ostream& operator<< <>(std::ostream& os, Stack<T> stack) {
	const LinkedList<T> list(stack);
	os << list;
	return os;
}
