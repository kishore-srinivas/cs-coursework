#include "BST.h"

void BST::insertNode(Node* toInsert, Node* toCheck) {
	string insertingData = (this->sortBy == NAME) ? toInsert->getData()->getName() : toInsert->getData()->getBirthday();
	string checkingData = (this->sortBy == NAME) ? toCheck->getData()->getName() : toCheck->getData()->getBirthday();

	if (insertingData < checkingData) {
		if (toCheck->hasLeftChild()) insertNode(toInsert, toCheck->getLeftChild());
		else {
			toCheck->setLeftChild(toInsert);
			this->numNodes++;
		}
	}
	else {
		if (toCheck->hasRightChild()) insertNode(toInsert, toCheck->getRightChild());
		else {
			toCheck->setRightChild(toInsert);
			this->numNodes++;
		}
	}
}

void BST::deleteNode(Node* node) {
	if (node->hasLeftChild()) {
		Node* largestLeftChild = node->getLeftChild();
		while (largestLeftChild->hasRightChild()) {
			largestLeftChild = largestLeftChild->getRightChild();
		}
		Person* temp = node->getData();
		node->setData(largestLeftChild->getData());
		largestLeftChild->setData(temp);
		delete largestLeftChild;
	}
	else if (node->hasRightChild()) {
		Node* largestRightChild = node->getRightChild();
		while (largestRightChild->hasLeftChild()) {
			largestRightChild = largestRightChild->getLeftChild();
		}
		Person* temp = node->getData();
		node->setData(largestRightChild->getData());
		largestRightChild->setData(temp);
		delete largestRightChild;
	}
	else {
		delete node;
	}
	this->numNodes--;
}

Node* BST::searchTree(Node* toStart, string data) {
	string nodeData = (this->sortBy == NAME) ? toStart->getData()->getName() : toStart->getData()->getBirthday();
	if (nodeData == data) return toStart;
	else {
		if (data < nodeData) {
			if (toStart->hasLeftChild()) return searchTree(toStart->getLeftChild(), data);
			else return nullptr;
		}
		else {
			if (toStart->hasRightChild()) return searchTree(toStart->getRightChild(), data);
			else return nullptr;
		}
	}
}

ostream& BST::inOrderTraversal(ostream& output, Node* toStart) {
	if (toStart->hasLeftChild()) {
		inOrderTraversal(output, toStart->getLeftChild());
	}
	output << toStart << endl;
	if (toStart->hasRightChild()) {
		inOrderTraversal(output, toStart->getRightChild());
	}
	return output;
}

ostream& BST::preOrderTraversal(ostream& output, Node* toStart) {
	output << toStart << endl;
	if (toStart->hasLeftChild()) {
		preOrderTraversal(output, toStart->getLeftChild());
	}
	if (toStart->hasRightChild()) {
		preOrderTraversal(output, toStart->getRightChild());
	}
	return output;
}

ostream& BST::postOrderTraversal(ostream& output, Node* toStart) {
	if (toStart->hasLeftChild()) {
		postOrderTraversal(output, toStart->getLeftChild());
	}
	if (toStart->hasRightChild()) {
		postOrderTraversal(output, toStart->getRightChild());
	}
	output << toStart << endl;
	return output;
}

ostream& BST::breadthFirstTraversal(ostream& output, BST* bst) {
	Queue<Node*> q;
	Node* root = bst->getRootNode();
	q.enqueue(root);
	while (!q.isEmpty()) {
		Node* node = q.dequeue();
		output << node;
		output << string(", ");
		if (node->hasLeftChild()) {
			Node* left = node->getLeftChild();
			q.enqueue(left);
		}
		if (node->hasRightChild()) {
			Node* right = node->getRightChild();
			q.enqueue(right);
		}
	}
	return output;
}

BST::BST() {
	this->numNodes = 0;
	this->rootNode = nullptr;
	this->sortBy = NAME;
}

BST::~BST() {}

BST::BST(TREE_TYPE type) {
	this->rootNode = nullptr;
	this->numNodes = 0;
	this->sortBy = type;
}

void BST::insert(Person* person) {
	Node* node = new Node(person);
	if (this->numNodes == 0) {
		this->setRootNode(node);
		this->numNodes++;
		return;
	}
	insertNode(node, this->rootNode);
}

void BST::deleteNode(string data) {
	deleteNode(findNode(data));
}

void BST::setRootNode(Node* node) {
	this->rootNode = node;
}

Node* BST::findNode(string data) {
	return searchTree(this->rootNode, data);
}

Node* BST::getRootNode() {
	return this->rootNode;
}

TREE_TYPE BST::getSortBy() {
	return this->sortBy;
}

ostream& BST::traverseInOrder(ostream& os) {
	return inOrderTraversal(os, this->getRootNode());
}

ostream& BST::traversePreOrder(ostream& os) {
	return preOrderTraversal(os, this->getRootNode());
}

ostream& BST::traversePostOrder(ostream& os) {
	return postOrderTraversal(os, this->getRootNode());
}

ostream& BST::traverseBreadthFirst(ostream& os) {
	return breadthFirstTraversal(os, this);
}