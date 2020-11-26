#ifndef BSTNODE_H
#define BSTNODE_H

template <typename T>
class BSTNode
{
private:
	T data;
	BSTNode* left;
	BSTNode* right;

public:
	/* This constructor creates a node for a BST containing specified data
		Pre: T - the data for which to create the node of
		Post:
		Return:
	*/
	BSTNode(T);

	/* This function gets the pointer to the left node
		Pre:
		Post:
		Return: the pointer to the left node
	*/
	BSTNode*& getLeft();

	/* This function gets the pointer to the right node
		Pre:
		Post:
		Return: the pointer to the right node
	*/
	BSTNode*& getRight();

	/* This function gets the data of this node
		Pre:
		Post:
		Return: the data in this node
	*/
	T& getData();

	/* This function sets the pointer to the left node to a specified value
		Pre: BSTNode* - pointer to set the left node to
		Post:
		Return:
	*/
	void setLeft(BSTNode*);

	/* This function sets the pointer to the right node to a specified value
		Pre: BSTNode* - pointer to set the right node to
		Post:
		Return:
	*/
	void setRight(BSTNode*);

	/* This function sets the data of this node
		Pre: T - the value to set the data to
		Post:
		Return:
	*/
	void setData(T);
};

template <typename T>
BSTNode<T>::BSTNode(T defaultData)
{
	data = defaultData;
	left = nullptr;
	right = nullptr;
}

template <typename T>
BSTNode<T>*& BSTNode<T>::getLeft()
{
	return left;
}

template <typename T>
BSTNode<T>*& BSTNode<T>::getRight()
{
	return right;
}

template <typename T>
T& BSTNode<T>::getData()
{
	return data;
}

template <typename T>
void BSTNode<T>::setLeft(BSTNode<T>* nPtr)
{
	left = nPtr;
}

template <typename T>
void BSTNode<T>::setRight(BSTNode<T>* nPtr)
{
	right = nPtr;
}

template <typename T>
void BSTNode<T>::setData(T dt)
{
	data = dt;
}
#endif // !BSTNODE_H