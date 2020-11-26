#pragma once
#include "Node.h"
#include "Queue.h"

using namespace std;

enum TREE_TYPE { NAME, BIRTHDAY };

class BST {
private:
	Node* rootNode;
	int numNodes;
	TREE_TYPE sortBy;
	void insertNode(Node* toInsert, Node* toCheck);
	void deleteNode(Node* node);
	Node* searchTree(Node* toStart, string data);
	ostream& inOrderTraversal(ostream& output, Node* toStart);
	ostream& preOrderTraversal(ostream& os, Node* toStart);
	ostream& postOrderTraversal(ostream& os, Node* toStart);
	ostream& breadthFirstTraversal(ostream& os, BST* bst);
public:
	/* default constructor for the BST class that creates a BST sorted by the name of the person objects*/
	BST();

	/* default destructor that deletes a BST*/
	~BST();

	/* constructor for the BST class that creates a BST sorted by the specified type
		@param type - the specifier for how the tree should be sorted
	*/
	BST(TREE_TYPE type);

	/* adds a node to the BST with a person object as root if empty, calls the other insert function if not
		@param person - the person object to be inserted
	*/
	void insert(Person* person);

	/* deletes a node from the BST
		@param name - the name within the node of the person object that is being deleted
	*/
	void deleteNode(string name);

	/* sets a new root node for the BST
		@param node - the node in the BST that is going to be the new root node 
	*/
	void setRootNode(Node* node);

	/* finds a specific node within the BST
		@param name - the name within the person object in the node that is going to be found
		@return a pointer to the node that was found
	*/
	Node* findNode(string name);

	/* returns the root node of the BST
		@return a pointer to the root node
	*/
	Node* getRootNode();

	/* returns the BST sort type
		@return the sort type of the BST
	*/
	TREE_TYPE getSortBy();

	/* returns an ostream object containing a the contents of the BST in an Inorder manner
		@return an ostream object with the contents of the BST in it
	*/
	ostream& traverseInOrder(ostream& os);

	/* returns an ostream object containing a the contents of the BST in an Preorder manner
		@return an ostream object with the contents of the BST in it
	*/
	ostream& traversePreOrder(ostream& os);

	/* returns an ostream object containing a the contents of the BST in an Postorder manner
		@return an ostream object with the contents of the BST in it
	*/
	ostream& traversePostOrder(ostream& os);

	/* returns an ostream object containing a the contents of the BST in an BreadthFirst manner
		@return an ostream object with the contents of the BST in it
	*/
	ostream& traverseBreadthFirst(ostream& os);
};
