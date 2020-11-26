#ifndef BST_H
#define BST_H

#include "BSTNode.h"
#include "LinkBasedQueue.h"

template <typename T>
class BST
{
private:
	BSTNode<T>* root;
	std::string processType;
	int numNodes;

public:
	/* This constructor creates an empty Binary Search Tree
		Pre:
		Post:
		Return:
	*/
	BST();

	/* This destructor destroys the BST, first clearing all its nodes
		Pre:
		Post:
		Return:
	*/
	~BST();

	/* This function adds a node with the specified data to the BST
		Pre: T& - The data of the node to be inserted
		Post:
		Return:
	*/
	void add(T&);

	/* This function removes a node with the specified data from the BST
		Pre: T& - The data of the node to be removed
		Post:
		Return:
	*/
	void remove(T&);

	/* This function checks whether the BST contains a node with the specified data
		Pre: T& - The data of the node to be checked
		Post:
		Return: true or false
	*/
	bool contains(T&);

	/* This function removes all nodes from the bst
		Pre:
		Post:
		Return:
	*/
	void clear();

	/* This function prints the BST to the specified output stream, in sorted order
		Pre: std::ostream& - The ouptut to stream to write output to
		Post:
		Return:
	*/
	void printSorted(std::ostream&);

	/* This function prints the BST to the specified output stream, based on the processType member variable
		Pre: std::ostream& - The ouptut to stream to write output to
		Post:
		Return:
	*/
	void print(std::ostream&);

	/* This function finds and returns to the user through the second param, a node with data of first param.
		Pre: T& - The data for which to find node
			 T& - to store the data of the node found goes in here
			 int& - to store the number of operations it took, to get efficiency
		Post: second parameter contains the data found. Third parameter contains number of operations it took.
		Return: true if found, false if not
	*/
	bool search(T&, T&, int&);

	/* This function changes the traversal of processing to specified input
		Pre: std::string - The traversal to set processing to.
		Post:
		Return:
	*/
	void setProcessType(std::string);

	/* This function gets the current traversal method for processing of the BST
		Pre:
		Post:
		Return: The current traversal method for processing
	*/
	std::string getProcessType();

	/* This function gets the current number of nodes in the BST
		Pre:
		Post:
		Return: the number of nodes in the BST
	*/
	int getNumNodes();

private:
	void add(T&, BSTNode<T>**);
	void remove(T&, BSTNode<T>**);
	bool contains(T&, BSTNode<T>*);
	void clear(BSTNode<T>**);
	void printInOrder(std::ostream& out, BSTNode<T>* currentNode);
	void print(std::ostream&, std::string, BSTNode<T>*, bool);
	bool search(T&, BSTNode<T>*, T&, int&);
};

template <typename T>
BST<T>::BST()
{
	root = nullptr;
	processType = "In Order";
	numNodes = 0;
}

template <typename T>
BST<T>::~BST()
{
	clear();
}

template <typename T>
void BST<T>::add(T& newEntry)
{
	add(newEntry, &root);
	numNodes++;
}

template <typename T>
void BST<T>::remove(T& anEntry)
{
	remove(anEntry, &root);
	numNodes--;
}

template <typename T>
bool BST<T>::contains(T& anEntry)
{
	return contains(anEntry, root);
}

template <typename T>
void BST<T>::clear()
{
	clear(&root);
	numNodes = 0;
}

template <typename T>
void BST<T>::printSorted(std::ostream& out)
{
	printInOrder(out, root);
}

template <typename T>
void BST<T>::print(std::ostream& out)
{
	if (root != nullptr)
	{
		if (root->getRight() != nullptr) {
			print(out, "", root->getRight(), true);
		}
		out << (root->getData()) << std::endl;
		if (root->getLeft() != nullptr) {
			print(out, "", root->getLeft(), false);
		}
	}
}

template <typename T>
bool BST<T>::search(T& compareValue, T& userValue, int& numComparisons)
{
	return search(compareValue, root, userValue, numComparisons);
}

template <typename T>
void BST<T>::setProcessType(std::string type)
{
	processType = type;
}

template <typename T>
std::string BST<T>::getProcessType()
{
	return processType;
}

template <typename T>
int BST<T>::getNumNodes()
{
	return numNodes;
}

template <typename T>
void BST<T>::add(T& newEntry, BSTNode<T>** currentNode)
{
	if (*currentNode == nullptr)
	{
		BSTNode<T>* newNode = new BSTNode<T>(newEntry);
		*currentNode = newNode;
	}
	else if (*((*currentNode)->getData()) > * newEntry)
	{
		add(newEntry, &(*currentNode)->getLeft());
	}
	else
	{
		add(newEntry, &(*currentNode)->getRight());
	}
}

template <typename T>
void BST<T>::remove(T& anEntry, BSTNode<T>** currentNode)
{
	if (*currentNode != nullptr)
	{
		if (*(*currentNode)->getData() > * anEntry)
		{
			remove(anEntry, &(*currentNode)->getLeft());
		}
		else if (*(*currentNode)->getData() < *anEntry)
		{
			remove(anEntry, &(*currentNode)->getRight());
		}
		else
		{
			if ((*currentNode)->getLeft() == nullptr)
			{
				BSTNode<T>* childPtr = (*currentNode)->getRight();
				delete* currentNode;
				*currentNode = childPtr;
			}
			else if ((*currentNode)->getRight() == nullptr)
			{
				BSTNode<T>* childPtr = (*currentNode)->getLeft();
				delete* currentNode;
				*currentNode = childPtr;
			}
			else
			{
				BSTNode<T>* deletePtr = *currentNode;
				BSTNode<T>* largestPtr = deletePtr->getLeft();

				while (largestPtr->getRight() != nullptr)
				{
					largestPtr = largestPtr->getRight();
				}

				deletePtr->setData(largestPtr->getData());
				remove(largestPtr->getData(), &deletePtr->getLeft());
			}

		}
	}
}

template <typename T>
bool BST<T>::contains(T& anEntry, BSTNode<T>* currentNode)
{
	if (currentNode != nullptr)
	{
		if (currentNode->getData() > anEntry)
		{
			return contains(anEntry, currentNode->getLeft());
		}
		else if (currentNode->getData() < anEntry)
		{
			return contains(anEntry, currentNode->getRight());
		}
		else
		{
			return true;
		}
	}
	else
	{
		return false;
	}
}

template <typename T>
void BST<T>::clear(BSTNode<T>** currentNode)
{
	if (*currentNode != nullptr)
	{
		clear(&(*currentNode)->getLeft());
		clear(&(*currentNode)->getRight());
		delete* currentNode;
		*currentNode = nullptr;
	}
}

template <typename T>
void BST<T>::printInOrder(std::ostream& out, BSTNode<T>* currentNode)
{
	if (currentNode != nullptr)
	{
		printInOrder(out, currentNode->getLeft());
		out << (currentNode->getData()) << "\n";
		printInOrder(out, currentNode->getRight());
	}
}

template <typename T>
void BST<T>::print(std::ostream& out, std::string first, BSTNode<T>* currentNode, bool right)
{
	if (currentNode != nullptr)
	{
		if (currentNode->getRight() != nullptr)
		{
			print(out, first + (right ? "        " : " |      "), currentNode->getRight(), true);
		}
		out << first;
		if (right)
		{
			out << " /";
		}
		else
		{
			out << " \\";
		}
		out << "---- -";
		out << (currentNode->getData()) << std::endl;
		if (currentNode->getLeft() != nullptr)
		{
			print(out, first + (right ? " |      " : "        "), currentNode->getLeft(), false);
		}
	}
}

template <typename T>
bool BST<T>::search(T& compareValue, BSTNode<T>* currentNode, T& userValue, int& numComparisons)
{
	if (currentNode != nullptr)
	{
		if (*(currentNode->getData()) > *compareValue)
		{
			numComparisons++;
			return search(compareValue, currentNode->getLeft(), userValue, numComparisons);
		}
		else if (*(currentNode->getData()) < *compareValue)
		{
			numComparisons++;
			return search(compareValue, currentNode->getRight(), userValue, numComparisons);
		}
		else
		{
			userValue = currentNode->getData();
			return true;
		}
	}
	else
	{
		return false;
	}
}

#endif // !BST_H