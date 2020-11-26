#pragma once

template <typename T>
class LinkedNode {
private:
	T data;
	LinkedNode* ptrNext;
public:
	LinkedNode();
	LinkedNode(T data);
	LinkedNode(T data, LinkedNode* ptrNext);
	~LinkedNode();
	T& getData();
	bool hasNext();
	LinkedNode* getNext();
	void setNext(LinkedNode* ptrNew);
	void setData(T dataNew);
};

/* default constructor for the LinkedNode class that creates a template node*/
template <typename T>
LinkedNode<T>::LinkedNode() {
	this->data = nullptr;
	this->ptrNext = nullptr;
}

/* constructor for the LinkedNode class that creates a template node with data
	@param data - the "information" that the node will contain
*/
template <typename T>
LinkedNode<T>::LinkedNode(T data) {
	this->data = data;
	this->ptrNext = nullptr;
}

/* constructor for the LinkedNode class that creates a template node with data and a pointer to the next node
	@param data - the "information" that the node will contain
	@param ptrNext - the pointer to the next node in the list
*/
template <typename T>
LinkedNode<T>::LinkedNode(T data, LinkedNode* ptrNext) {
	this->data = data;
	this->ptrNext = ptrNext;
}

/* default destructor for the LinkedNode class that stops memory leaks*/
template <typename T>
LinkedNode<T>::~LinkedNode() {
	//delete this->data;
	//delete this->ptrNext;
}

/* getter for the LinkedNode that returns the data member within the node
	@return the "information" that the node contains
*/
template <typename T>
T& LinkedNode<T>::getData() {
	return this->data;
}

/* returns if the node points to a viable next node
	@return true if the node points to a initialized node
*/
template <typename T>
bool LinkedNode<T>::hasNext() {
	return this->getNext() != nullptr;
}

/* returns the next node in relation to the current one
	@return a pointer to the next node in the list
*/
template <typename T>
LinkedNode<T>* LinkedNode<T>::getNext() {
	//if (this->ptrNext == nullptr) return nullptr;
	return this->ptrNext;
}

/* setter for the data member of the node
	@pre a data value to be set
	@post the node's data member is now equivalent to the parameter
*/
template <typename T>
void LinkedNode<T>::setData(T dataNew) {
	this->data = dataNew;
}

/* setter for the pointer member of the node
	@pre a pointer to a LinkedNode
	@post the node's next pointer is now equivalent to the parameter
*/
template <typename T>
void LinkedNode<T>::setNext(LinkedNode* ptrNew) {
	this->ptrNext = ptrNew;
}
