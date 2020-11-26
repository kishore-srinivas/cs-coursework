#ifndef CURRENCY
#define CURRENCY
#include <string>

class Currency {
protected:
	int notes;
	std::string wholeParts;
	std::string fracParts;
	int coins;
public:
	/* default constructor for Currency */
	Currency();

	/* constructs a Currency object with the given fields
	@param	wholeParts - the name of the currency (ie. Dollar, Rupee, etc.)
			notes - the number of notes
			coins - the number of coins
			fracParts - the name of the fractional part of the currency (ie. cent, paise, etc.)
	*/
	Currency(std::string wholeParts, int notes, int coins, std::string fracParts);

	/* adds two currency objects together
	@pre two currency objects must be initialized with values
	@return a new Currency object with value equal to the sum of the two input objects
	*/
	friend Currency& operator + (Currency& curr1, Currency& curr2);

	/* subtracts two currency objects from each other
	@pre two currency objects must be initialized with values
	@return if curr2 is less than curr1, a new Currency object with value equal to the difference of the two input objects
			if not, throws curr1
	*/
	friend Currency& operator - (Currency& curr1, Currency& curr2);

	/* compares the value of two currency objects
	@pre two currency objects must be initialized with values
	@return true if curr1's value is less than curr2's value
			false if not
	*/
	friend bool operator < (Currency& curr1, Currency& curr2);

	/* compares the value of two currency objects
	@pre two currency objects must be initialized with values
	@return true if curr1's value is greater than curr2's value
			false if not
	*/
	friend bool operator > (Currency& curr1, Currency& curr2);

	/* prints out the value of a currency object
	@pre currency object initialized with value
	@return an ostream object containing a formatted string of the currency's value
	*/
	friend std::ostream& operator<< (std::ostream& os, Currency& curr);

	/* takes in values from the user and initializes a Currency object
	@return a Currency object with the values given by the user
	*/
	friend std::istream& operator>> (std::istream& is, Currency& curr);

	/* converts the value of the currency into a double
	@pre a Currency object with a nonzero value for notes and coins
	@return a double representing the total value of the Currency object
	*/
	double convertToDouble();

	/* sets the value of the currency 
	@pre a double representing the value to be set
	*/
	void setValue(double value);

	/* sets the value of the currency
	@pre an int for the number of notes and an int for the number of coins
	*/
	void setValue(int notes, int coins);

	/* returns the number of notes 
	@return an integer of the number of notes
	*/
	int getNotes();

	/* returns the number of coins 
	@return an integer of the number of coins
	*/
	int getCoins();

	/* returns the type of the currency object
	@return a string of the type of currency (ie. Dollar, Rupee, etc.)
	*/
	virtual std::string getType();
};

class Dollar : public Currency {
public:
	// creates an object of type Dollar with values for notes and coins
	Dollar(int notes, int coins);

	// creates an object of type Dollar with the value given, converted into notes and coins
	Dollar(double);

	// returns a string of the type of the object (ie. Dollar)
	std::string getType();
};

class Euro : public Currency {
public:
	// creates an object of type Euro with values for notes and coins
	Euro(int, int);

	// creates an object of type Euro with the value given, converted into notes and coins
	Euro(double);

	// returns a string of the type of the object (ie. Euro)
	std::string getType();
};

class Yen : public Currency {
public:
	// creates an object of type Yen with values for notes and coins
	Yen(int, int);
	
	// creates an object of type Yen with the value given, converted into notes and coins
	Yen(double);
	
	// returns a string of the type of the object (ie. Yen)
	std::string getType();
};

class Rupee : public Currency {
public:
	// creates an object of type Rupee with values for notes and coins
	Rupee(int, int);
	
	// creates an object of type Rupee with the value given, converted into notes and coins
	Rupee(double);
	
	// returns a string of the type of the object (ie. Rupee)
	std::string getType();
};

class Yuan : public Currency {
public:
	// creates an object of type Yuan with values for notes and coins
	Yuan(int, int);
	
	// creates an object of type Yuan with the value given, converted into notes and coins
	Yuan(double);
	
	// returns a string of the type of the object (ie. Yuan)
	std::string getType();
};

#endif