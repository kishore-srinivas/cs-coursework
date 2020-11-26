#include "Node.h"

Node::Node() {
	this->person = new Person();
	this->leftChild = nullptr;
	this->rightChild = nullptr;
}

Node::~Node() {}

Node::Node(Person* person) {
	this->person = person;
	this->leftChild = nullptr;
	this->rightChild = nullptr;
}

void Node::setData(Person* person) {
	this->person = person;
}

void Node::setLeftChild(Node* node) {
	this->leftChild = node;
}

void Node::setRightChild(Node* node) {
	this->rightChild = node;
}

bool Node::hasLeftChild() {
	return this->leftChild != nullptr;
}

bool Node::hasRightChild() {
	return this->rightChild != nullptr;
}

Person* Node::getData() {
	return this->person;
}

Node* Node::getLeftChild() {
	return this->leftChild;
}

Node* Node::getRightChild() {
	return this->rightChild;
}

std::ostream& operator << (std::ostream& os, Node* node) {
	os << node->person;
	return os;
}
