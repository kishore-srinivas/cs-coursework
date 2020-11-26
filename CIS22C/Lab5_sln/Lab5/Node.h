#pragma once
#include "Person.h"

class Node {
private:
	Person* person;
	Node* leftChild;
	Node* rightChild;
public:
	/* default constructor for the Node class that creates a Node with a Person object in it that will be inserted to the BST*/
	Node();

	/* default destructor that deletes a Node */
	~Node();

	/* constructor for Node objects that creates a Node with a Person object that was passed to the constructor
		@param a pointer to a Person object
	*/
	Node(Person* person);

	/* sets the data member of the Node to a different Person object 
		@param a pointer to a Person object
	*/
	void setData(Person* person);

	/* sets the left child of the Node to another Node
		@param a pointer to the node that will become the left child
	*/
	void setLeftChild(Node* node);

	/* sets the right child of the Node to another Node
		@param a pointer to the node that will become the right child
	*/
	void setRightChild(Node* node);

	/* checks if the Node has a left child
		@return true if the Node has a left child, false otherwise
	*/
	bool hasLeftChild();

	/* checks if the Node has a right child
		@return true if the Node has a right child, false otherwise
	*/
	bool hasRightChild();

	/* returns the data within the Node
		@return a pointer to the Person object within the Node
	*/
	Person* getData();

	/* returns a pointer to the Node of the left child
		@return a pointer to the left child Node
	*/
	Node* getLeftChild();

	/* returns a pointer to the Node of the right child
		@return a pointer to the right child Node
	*/
	Node* getRightChild();

	/* overloads the insertion operator so that it can be used to print Node data
		@return an ostream object
	*/
	friend std::ostream& operator << (std::ostream& os, Node* node);
};
