#ifndef HASHNODE_H
#define HASHNODE_H

template <typename T>
class HashNode
{
private:
	int key;
	T data;

public:
	/* This constructor creates an empty hash node
		Pre:
		Post:
		Return:
	*/
	HashNode();

	/* This constructor creates a node for a hash table with the specified key, and data
		Pre: std::string - The key to add to the node
			 T - The data to be added to the node
		Post:
		Return:
	*/
	HashNode(int, T);

	/* This function sets the key member variable to the specified input
		Pre: std::string - The key to set the key member variable to
		Post:
		Return:
	*/
	void setKey(int);

	/* This function sets the data member variable to the specified input
		Pre: T - The data to set the data member variable to
		Post:
		Return:
	*/
	void setData(T);

	/* This function returns the key of the hash node
		Pre:
		Post:
		Return: the key of the hash node
	*/
	double getKey();

	/* This function returns the data of the hash node, as a reference to allow for output
		Pre:
		Post:
		Return: a reference to the data of the hash node
	*/
	T& getData();

	/* This operator checks whether or not the left node is greater than the right node
		Pre: HashNode<T>& - The node to be compared with
			 HashNode<T>& - The node to check if greater than the other
		Post:
		Return: true or false
	*/
	template <typename T>
	friend bool operator<(HashNode<T>&, HashNode<T>&);

	/* This operator checks whether or not the left node is equal to the right node
		Pre: HashNode<T>& - The node to be compared with
			 HashNode<T>& - The node to check if equal to the other
		Post:
		Return: true or false
    */
	template <typename T>
	friend bool operator==(const HashNode<T>&, const HashNode<T>&);

	/* This operator allows the hash node to be written to an output stream, simply by using its name
		Pre: std::ostream& - The output stream to write the hash node to
			 HashTable<T>& - The hash node to write to the output
		Post:
		Return: the output stream
	*/
	template <typename T>
	friend std::ostream& operator<<(std::ostream&, HashNode<T>&);
};

template <typename T>
HashNode<T>::HashNode()
{
}

template <typename T>
HashNode<T>::HashNode(int key, T data)
{
	this->key = key;
	this->data = data;
}

template <typename T>
void HashNode<T>::setKey(int key)
{
	this->key = key;
}

template <typename T>
void HashNode<T>::setData(T data)
{
	this->data = data;
}

template <typename T>
double HashNode<T>::getKey()
{
	return key;
}

template <typename T>
T& HashNode<T>::getData()
{
	return data;
}

template <typename T>
bool operator<(HashNode<T>& n1, HashNode<T>& n2)
{
	return n1 < n2;
}

template <typename T>
bool operator==(const HashNode<T>& n1, const HashNode<T>& n2)
{
	return n1.data == n2.data;
}

template <typename T>
std::ostream& operator<<(std::ostream& out, HashNode<T>& node)
{
	out << (node.data);
	return out;
}

#endif // !HASHNODE_H
