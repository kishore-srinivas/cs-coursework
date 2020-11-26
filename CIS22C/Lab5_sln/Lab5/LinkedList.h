#pragma once
#include "LinkedNode.h"
#include <iostream>

template <typename T>
class LinkedList;

template <typename T>
std::ostream& operator<<(std::ostream& os, LinkedList<T> list);

template <typename T>
class LinkedList {
protected:
	int numNodes;
	LinkedNode<T>* startNode;
	LinkedNode<T>* endNode;
	int sortType;
public:
	LinkedList(int sortType);
	LinkedList(LinkedNode<T>* start, LinkedNode<T>* end, int sortType);
	~LinkedList();
	void setStartNode(LinkedNode<T>* start);
	void setEndNode(LinkedNode<T>* end);
	LinkedNode<T>* getStartNode();
	LinkedNode<T>* getEndNode();
	void addNode(LinkedNode<T>& newNode);
	void insertNode(LinkedNode<T>& newNode, int pos);
	LinkedNode<T>* removeNode(T data);
	LinkedNode<T>* removeNode();
	int findNode(T data);
	LinkedNode<T>* getNodeAtIndex(int index);
	int getNumNodes();
	void emptyList();
	friend std::ostream& operator<< <>(std::ostream& os, LinkedList<T> list);
};

/* constructor for the LinkedList class that creates a template list with a defined sortType
	@param sortType - the way the list has its data sorted (0 for ascending, 1 for descending, 2 for unsorted)
*/
template <typename T>
LinkedList<T>::LinkedList(int sortType) {
	numNodes = 0;
	startNode = nullptr;
	endNode = nullptr;
	this->sortType = sortType;
}

/* constructor for the LinkedList class that creates a template list with a defined sortType, number of nodes, starting node, and ending node
	@param start - a pointer to the first node in the list
	@param end - a pointer to the last node in the list
	@param sortType - the way the list has its data sorted (0 for ascending, 1 for descending, 2 for unsorted)
*/
template <typename T>
LinkedList<T>::LinkedList(LinkedNode<T>* start, LinkedNode<T>* end, int sortType) {
	this->numNodes = 2;
	this->startNode = start;
	this->endNode = end;
	this->startNode->setNext(this->endNode);
	this->sortType = sortType;
}

/* destructor for the LinkedList class*/
template <typename T>
LinkedList<T>::~LinkedList() {
	//this->emptyList();
	//delete this->numNodes;
	//delete this->startNode;
	//delete this->endNode;
	//delete this->sortType;
}

/* setter for the LinkedList class that sets the first node in the list
	@param the first node in the list
*/
template <typename T>
void LinkedList<T>::setStartNode(LinkedNode<T>* start) {
	this->startNode = start;
}

/* setter for the LinkedList class that sets the last node in the list
	@param the last node in the list
*/
template <typename T>
void LinkedList<T>::setEndNode(LinkedNode<T>* end) {
	this->endNode = end;
}

/* getter for the LinkedList class that returns the first node in the list
	@return the first node in the list
*/
template <typename T>
LinkedNode<T>* LinkedList<T>::getStartNode() {
	return this->startNode;
}

/* getter for the LinkedList class that returns the last node in the list
	@return the last node in the list
*/
template <typename T>
LinkedNode<T>* LinkedList<T>::getEndNode() {
	return this->endNode;
}

/* adds nodes into the list in the appropriate manner for how the list is ordered
	@param the node to be inserted
	@post the node is now in the list in the correct location
*/
template <typename T>
void LinkedList<T>::addNode(LinkedNode<T>& newNode) {
	if (numNodes == 0) {
		this->setStartNode(&newNode);
		this->setEndNode(&newNode);
		this->numNodes++;
	} else {
		if (this->sortType == 0) {
			LinkedNode<T>* pPre = nullptr;
			LinkedNode<T>* pCur = this->getStartNode();
			while (newNode.getData() > pCur->getData()) {
				if (pCur->hasNext()) {
					pPre = pCur;
					pCur = pCur->getNext();
				}
				else {
					pPre = this->getEndNode();
					pCur = nullptr;
					break;
				}
			}
			if (pPre == nullptr) {
				newNode.setNext(pCur);
				this->setStartNode(&newNode);
			}
			else if (pCur == nullptr) {
				pPre->setNext(&newNode);
				this->setEndNode(&newNode);
			}
			else {
				newNode.setNext(pCur);
				pPre->setNext(&newNode);
			}
			numNodes++;
		}
		else if (this->sortType == 1) {
			LinkedNode<T>* pPre = nullptr;
			LinkedNode<T>* pCur = this->getStartNode();
			while (newNode.getData() < pCur->getData()) {
				if (pCur->hasNext()) {
					pPre = pCur;
					pCur = pCur->getNext();
				}
				else {
					pPre = this->getEndNode();
					pCur = nullptr;
					break;
				}
			}
			if (pPre == nullptr) {
				newNode.setNext(pCur);
				this->setStartNode(&newNode);
			}
			else if (pCur == nullptr) {
				pPre->setNext(&newNode);
				this->setEndNode(&newNode);
			}
			else {
				newNode.setNext(pCur);
				pPre->setNext(&newNode);
			}
			numNodes++;
		}
		else {
			this->insertNode(newNode, 0);
		}
		this->setEndNode(this->getNodeAtIndex(numNodes - 1));
	}
}


/* adds nodes into an unordered list to the position that is specified
	@param the node to be inserted
	@param an int that corresponds to the location in which the node should be placed
	@post the node is now in the list in the desired location
*/
template <typename T>
void LinkedList<T>::insertNode(LinkedNode<T>& newNode, int pos) {
	if (this->sortType == 2) {
		if (numNodes == 0) {
			this->setStartNode(&newNode);
			this->setEndNode(&newNode);
			this->numNodes++;
			return;
		}
		if (pos == numNodes) {
			this->getEndNode()->setNext(&newNode);
			this->setEndNode(&newNode);
			this->numNodes++;
			return;
		}
		if (pos == 0) {
			newNode.setNext(this->getStartNode());
			this->setStartNode(&newNode);
			this->numNodes++;
			return;
		}
		LinkedNode<T>* pPre = this->getNodeAtIndex(pos - 1);
		LinkedNode<T>* pCur = pPre->getNext();
		newNode.setNext(pCur);
		pPre->setNext(&newNode);
		this->numNodes = numNodes++;
	}
	else {
		std::cout << "Error: You cannot decide to place this element into the position you desire.\n";
		std::cout << "This is a sorted list please try again and allow the program to insert it into the correct position for you.";
	}
}

/* removes and deletes nodes from the list when given the value of the data the node contains
	@param the data within the node to be deleted
	@return the node that has been deleted
*/
template <typename T>
LinkedNode<T>* LinkedList<T>::removeNode(T data) {
	int loc = this->findNode(data);
	if (loc > 0)
	{
		LinkedNode<T>* pPre = this->getNodeAtIndex(loc - 1);
		LinkedNode<T>* pCur = pPre->getNext();
		LinkedNode<T>* pTemp = pCur;
		pPre->setNext(pCur->getNext());
		//delete pCur;
		this->numNodes--;
		return pTemp;
	}
	else if (loc == 0)
	{
		this->removeNode();
	}
	else if (loc  == this->getNumNodes()-1)
	{
		LinkedNode<T>* pCur = this->getEndNode();
		LinkedNode<T>* pTemp = pCur;
		this->setEndNode(this->getNodeAtIndex(loc - 1));
		//delete pCur;
		this->numNodes--;
		return pTemp;
	}
}

/* removes and deletes the first node in the list
	@return the node that has been deleted
*/
template <typename T>
LinkedNode<T>* LinkedList<T>::removeNode() {
	LinkedNode<T>* pCur = this->getStartNode();
	LinkedNode<T>* pTemp = pCur;
	this->setStartNode(pCur->getNext());
	//delete pCur;
	this->numNodes--;
	if (this->numNodes == 0) this->setEndNode(nullptr);
	return pTemp;
}

/* locates the node in the list that contains the given data
	@param the data within the node that is being located
	@return the location of the node that was specified
*/
template <typename T>
int LinkedList<T>::findNode(T data) {
	int numChecked = 0;
	LinkedNode<T>* current = this->getStartNode();
	if (current->getData() == data) return numChecked;
	bool found = false;
	while (!found) {
		if (current->getData() == data) return numChecked;
		if (current->hasNext()) {
			current = current->getNext();
			numChecked++;
		}
		else {
			return -1;
		}
	}
}

/* returns the node in the list at the given index
	@param the location as an index of the node
	@return the node at that location
*/
template <typename T>
LinkedNode<T>* LinkedList<T>::getNodeAtIndex(int index) {
	if (index >= this->getNumNodes()) return this->getStartNode();
	LinkedNode<T>* pNode = this->getStartNode();
	for (int i = 0; i < index; i++) {
		pNode = pNode->getNext();
	}
	return pNode;
}

/* getter for the LinkedList class that returns the number of nodes in the list
	@return the last node in the list
*/
template <typename T>
int LinkedList<T>::getNumNodes() {
	return this->numNodes; 
}

/* empties the list of nodes
	@post the list no longer contains linked nodes
*/
template <typename T>
void LinkedList<T>::emptyList() {
	while (this->getNumNodes() != 0)
	{
		this->removeNode();
	}
}

/* overloads the insertion operator so that it can print out the list in the linked list object
	@pre LinkedList object initialized with values in its list
	@return an ostream object containing print statements with the list values
*/
template <typename T>
std::ostream& operator<< <>(std::ostream& os, LinkedList<T> list) {
	os << "List contents: ";
	if (list.numNodes == 0) {
		os << "EMPTY LIST" << std::endl << std::endl;
		return os;
	}
	LinkedNode<T>* ptr = list.startNode;
	while (ptr->hasNext()) {
		os << ptr->getData() << ", ";
		ptr = ptr->getNext();
	}
	ptr = list.endNode;
	os << ptr->getData() << std::endl;
	//ptr = nullptr;
	return os;
}
