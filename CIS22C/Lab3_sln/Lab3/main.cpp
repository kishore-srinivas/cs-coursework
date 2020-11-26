#include <iostream>
#include "LinkedList.h"
//#include "Currency.h"
#include "Stack.h"
#include "Queue.h"

using namespace std;


void mainFor3A() {
	cout << "Ascending integer List: " << endl;
	LinkedNode<int>* ptr1 = new LinkedNode<int>(1);
	LinkedNode<int>* ptr2 = new LinkedNode<int>(5);
	LinkedNode<int>* ptr3 = new LinkedNode<int>(3);
	LinkedNode<int>* ptr4 = new LinkedNode<int>(2);
	LinkedNode<int>* ptr5 = new LinkedNode<int>(0);
	LinkedNode<int>* ptr6 = new LinkedNode<int>(7);
	LinkedNode<int>* ptr7 = new LinkedNode<int>(35);
	LinkedNode<int>* ptr8 = new LinkedNode<int>(-5);
	LinkedNode<int>* ptr9 = new LinkedNode<int>(23);
	LinkedNode<int>* ptr10 = new LinkedNode<int>(29);
	LinkedList<int> list1(0);
	list1.addNode(*ptr1);
	cout << list1;
	list1.addNode(*ptr2);
	list1.addNode(*ptr3);
	list1.addNode(*ptr4);
	list1.addNode(*ptr5);
	list1.addNode(*ptr6);
	cout << list1;
	list1.addNode(*ptr7);
	list1.addNode(*ptr8);
	list1.addNode(*ptr9);
	list1.addNode(*ptr10);
	cout << list1;
	cout << "Node of value " << 23 << " is at location " << list1.findNode(23) << endl;
	cout << "removed: " << list1.removeNode()->getData() << endl;
	cout << "removed: " << list1.removeNode(5)->getData() << endl;
	cout << list1;
	list1.emptyList();
	cout << list1;

	cout << endl << "Descending string List: " << endl;
	LinkedNode<string>* ptr11 = new LinkedNode<string>("abcd");
	LinkedNode<string>* ptr12 = new LinkedNode<string>("xyz");
	LinkedNode<string>* ptr13 = new LinkedNode<string>("wxyz");
	LinkedList<string> list2(1);
	list2.addNode(*ptr11);
	list2.addNode(*ptr12);
	list2.addNode(*ptr13);
	cout << list2;
	cout << list2.getNumNodes() << " elements in this list" << endl;
	LinkedNode<string>* ptr14 = new LinkedNode<string>("abcde");
	LinkedNode<string>* ptr15 = new LinkedNode<string>("xyzxyz");
	LinkedNode<string>* ptr16 = new LinkedNode<string>("tuvwxyz");
	LinkedNode<string>* ptr17 = new LinkedNode<string>("A");
	LinkedNode<string>* ptr18 = new LinkedNode<string>("XYZ");
	LinkedNode<string>* ptr19 = new LinkedNode<string>("AbCd");
	LinkedNode<string>* ptr20 = new LinkedNode<string>("aBcD");
	list2.addNode(*ptr14);
	list2.addNode(*ptr15);
	list2.addNode(*ptr16);
	list2.addNode(*ptr17);
	list2.addNode(*ptr18);
	list2.addNode(*ptr19);
	list2.addNode(*ptr20);
	cout << list2;
	cout << "removed: " << list2.removeNode("A")->getData() << endl;
	cout << "removed: " << list2.removeNode()->getData() << endl;
	cout << "removed: " << list2.removeNode()->getData() << endl;
	cout << list2 << endl;

	cout << endl << "Unsorted Rupee List: " << endl;
	LinkedNode<Currency>* ptr21 = new LinkedNode<Currency>(Rupee(10, 10));
	LinkedNode<Currency>* ptr22 = new LinkedNode<Currency>(Rupee(43, 50));
	LinkedNode<Currency>* ptr23 = new LinkedNode<Currency>(Rupee(83, 20));
	LinkedNode<Currency>* ptr24 = new LinkedNode<Currency>(Rupee(10, 15));
	LinkedNode<Currency>* ptr25 = new LinkedNode<Currency>(Rupee(17, 12));
	LinkedNode<Currency>* ptr26 = new LinkedNode<Currency>(Rupee(0, 10));
	LinkedNode<Currency>* ptr27 = new LinkedNode<Currency>(Rupee(10, 1));
	LinkedNode<Currency>* ptr28 = new LinkedNode<Currency>(Rupee(45, 29));
	LinkedNode<Currency>* ptr29 = new LinkedNode<Currency>(Rupee(13, 10));
	LinkedNode<Currency>* ptr30 = new LinkedNode<Currency>(Rupee(10, 27));
	LinkedList<Currency> list3(2);
	list3.addNode(*ptr21);
	cout << list3;
	list3.addNode(*ptr22);
	list3.addNode(*ptr23);
	list3.addNode(*ptr24);
	list3.addNode(*ptr25);
	list3.addNode(*ptr26);
	list3.addNode(*ptr27);
	cout << list3;
	list3.insertNode(*ptr28, 2);
	list3.insertNode(*ptr29, 2);
	list3.insertNode(*ptr30, 6);
	cout << list3;
	//cout << "removed: " << list3.removeNode(Rupee(45, 29))->getData() << endl;
	cout << "removed: " << list3.removeNode()->getData() << endl;
	cout << list3 << endl;
}

void mainFor3B() {
	LinkedNode<int> a(4);
	LinkedNode<int> b(5);
	LinkedNode<int> c(-10);
	LinkedNode<int> d(2);
	LinkedNode<int> e(4);
	LinkedNode<int> f(95);
	LinkedNode<int> g(3);

	Stack<int> intStack;
	cout << "--- STACK of INTEGERS ---" << endl;
	cout << "PUSH: " << a.getData() << endl << endl;
	intStack.push(a);
	cout << "peek: " << intStack.peek() << endl << endl;
	cout << "PUSH: " << b.getData() << endl;
	cout << "PUSH: " << c.getData() << endl;
	cout << "PUSH: " << d.getData() << endl << endl;
	intStack.push(b);
	intStack.push(c);
	intStack.push(d);
	cout << "peek: " << intStack.peek() << endl << endl;
	cout << "pop: " << intStack.pop() << endl;
	cout << "pop: " << intStack.pop() << endl << endl;
	cout << "peek: " << intStack.peek() << endl << endl;
	cout << intStack;
	cout << "emptying stack..." << endl;
	intStack.empty();
	cout << intStack << endl;

	LinkedNode<string> s1("A");
	LinkedNode<string> s2("abc");
	LinkedNode<string> s3("xyz");
	LinkedNode<string> s4("B");
	LinkedNode<string> s5("abcdef");
	LinkedNode<string> s6("XYZ");
	LinkedNode<string> s7("C");

	Stack<string> stringStack;
	cout << "--- STACK of STRINGS ---" << endl;
	cout << "PUSH: " << a.getData() << endl << endl;
	stringStack.push(s1);
	cout << "peek: " << stringStack.peek() << endl << endl;
	cout << "PUSH: " << s2.getData() << endl;
	cout << "PUSH: " << s3.getData() << endl;
	cout << "PUSH: " << s4.getData() << endl << endl;
	stringStack.push(s2);
	stringStack.push(s3);
	stringStack.push(s4);
	cout << "peek: " << stringStack.peek() << endl << endl;
	cout << "pop: " << stringStack.pop() << endl;
	cout << "pop: " << stringStack.pop() << endl << endl;
	cout << "peek: " << stringStack.peek() << endl << endl;
	cout << stringStack;
	cout << "emptying stack..." << endl;
	stringStack.empty();
	cout << stringStack << endl;

	LinkedNode<Currency>* ptr1 = new LinkedNode<Currency>(Rupee(10, 10));
	LinkedNode<Currency>* ptr2 = new LinkedNode<Currency>(Rupee(43, 50));
	LinkedNode<Currency>* ptr3 = new LinkedNode<Currency>(Rupee(83, 20));
	LinkedNode<Currency>* ptr4 = new LinkedNode<Currency>(Rupee(10, 15));
	LinkedNode<Currency>* ptr5 = new LinkedNode<Currency>(Rupee(17, 12));
	LinkedNode<Currency>* ptr6 = new LinkedNode<Currency>(Rupee(0, 10));
	LinkedNode<Currency>* ptr7 = new LinkedNode<Currency>(Rupee(10, 1));

	cout << "--- QUEUE of RUPEES ---" << endl;
	Queue<Currency> currQueue;
	cout << "ENQUEUE: " << ptr1->getData() << endl;
	cout << "ENQUEUE: " << ptr2->getData() << endl;
	cout << "ENQUEUE: " << ptr3->getData() << endl << endl;
	currQueue.enqueue(*ptr1);
	currQueue.enqueue(*ptr2);
	currQueue.enqueue(*ptr3);
	cout << "front: " << currQueue.getFront() << endl;
	cout << "rear: " << currQueue.getRear() << endl << endl;
	cout << "ENQUEUE: " << ptr4->getData() << endl;
	cout << "ENQUEUE: " << ptr5->getData() << endl;
	cout << "ENQUEUE: " << ptr6->getData() << endl;
	cout << "ENQUEUE: " << ptr7->getData() << endl << endl;
	currQueue.enqueue(*ptr4);
	currQueue.enqueue(*ptr5);
	currQueue.enqueue(*ptr6);
	currQueue.enqueue(*ptr7);
	cout << "front: " << currQueue.getFront() << endl;
	cout << "rear: " << currQueue.getRear() << endl << endl;
	cout << "dequeue: " << currQueue.dequeue() << endl;
	cout << "dequeue: " << currQueue.dequeue() << endl << endl;
	cout << "front: " << currQueue.getFront() << endl;
	cout << "rear: " << currQueue.getRear() << endl << endl;
	cout << currQueue;
	cout << "emptying queue..." << endl;
	currQueue.empty();
	cout << currQueue << endl;
}

/* Lab 3B(Kishore Srinivas & Jnana Uhuru)
	the driver function that shows the stacks and queue in use
	@return 0 when the program is done with the demonstration
*/
int main() {
	//mainFor3A();
	mainFor3B();

	system("pause");
	return 0;
}
