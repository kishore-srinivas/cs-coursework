#pragma once
#include <string>

using namespace std;

class Person {
private:
	string name;
	string birthday;
	int birthYear;
	int birthMonth;
	int birthDate;
	void parseBirthday();
public:
	/* default constructor for Person objects*/
	Person();

	/* default destructor for Person objects*/
	~Person();

	/* constructor for Node objects that creates a Node that contains the person object with specific data
		@param name - the name of the person
		@param birthday - the person's birthday
	*/
	Person(string name, string birthday);

	/* returns the name of the person
		@return a string containing the name of the person object
	*/
	string getName();

	/* returns the birthday of the person
		@return a string containing the birthday of the person object
	*/
	string getBirthday();

	/* sets the name of the person object
		@param name - the string that will be used to change the name of the object
	*/
	void setName(string name);

	/* sets the birthday of the person object
		@param birthday - the string that will be used to change the birthday of the object
	*/
	void setBirthday(string birthday);

	/* overloads the insertion operator so that it can be used to print person data
		@return an ostream object
	*/
	friend std::ostream& operator << (std::ostream& os, Person* person);
};
