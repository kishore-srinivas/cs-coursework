#ifndef LINKNODE_H
#define LINKNODE_H

template <typename T>
class LinkNode
{
private:
	T data;
	LinkNode* nextPtr;

public:
	/* This constructor creates a link node containing specified data
		Pre: T - the data for which to create the node of
		Post:
		Return:
	*/
	LinkNode(T);

	/* This function gets the pointer to the next node
		Pre:
		Post:
		Return: the pointer to the next node
	*/
	LinkNode* getNext() const;

	/* This function gets the data of this node
		Pre:
		Post:
		Return: a reference to the data in this node
	*/
	T& getData();

	/* This function sets the pointer to the next node to a specified value
		Pre: LinkNode* - pointer to set the next the next node to
		Post:
		Return:
	*/
	void setNext(LinkNode*);

	/* This function sets the data of this node
		Pre: T - the value to set the data to
		Post:
		Return:
	*/
	void setData(T);
};

template <typename T>
LinkNode<T>::LinkNode(T defaultData)
{
	data = defaultData;
	nextPtr = nullptr;
}

template <typename T>
LinkNode<T>* LinkNode<T>::getNext() const
{
	return nextPtr;
}

template <typename T>
T& LinkNode<T>::getData()
{
	return data;
}

template <typename T>
void LinkNode<T>::setNext(LinkNode<T>* nPtr)
{
	nextPtr = nPtr;
}

template <typename T>
void LinkNode<T>::setData(T dt)
{
	data = dt;
}
#endif // !LINKNODE_H