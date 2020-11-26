#ifndef WALLET
#define WALLET
#include "Currency.h"
#include <iostream>

class Wallet {
private:
	static const int NUM_CURRENCIES = 5;
	Currency* currencies[NUM_CURRENCIES];
	int nonZero = 0; //sets the accumulator to 0 for later use
public:
	/* the default constructor for the Wallet class*/
	Wallet();
	
	/* overloads the subrscipt operator for Currency objects
	@pre an int that is used as the index for the array 
	@return a Currency object of the specified kind
	*/
	Currency& operator[](int);
	
	/* allows for a currency name to return a Currency object 
	@pre a string with a currency name(Dollar, Euro, Yen...)
	@return a Currency object of that kind
	*/
	Currency& getCurrency(std::string);
	
	/* checks if a Currency has any value in the Wallet
	@pre a string with a currency name(Dollar, Euro, Yen...)
	@return true if there is a monetary value not equal to zero in that object. False if zero
	*/
	bool isNonZero(std::string);
	
	/* prints the currencies in the wallet that have any value(nonzero)
	@post all the names of currency objects that have a monetary value are printed to the console
	*/
	void findAllNonZero();
	
	/* prints the contents of the specified currency
	@pre a string with a currency name(Dollar, Euro, Yen...)
	@post prints the name and monetary values of the currency object to console
	*/
	void view(std::string);
	
	/* allows for a count to be made of how many currencies have a value(nonzero) 
	@return the number of Currency objects that are not zero in the wallet
	*/
	int numNonZero();
	
	/* adds two Currency objects together and prints the new value of the currency
	@pre a currency object to be added to its own currency within the wallet 
	@post prints the new amount of the currency in the wallet after the addition
	*/
	void add(Currency&);
	
	/* simulates removing money from the wallet for payment
	@pre a currency object to be subtracted from its own currency type within the wallet
	@post prints the new amount of the currency in the wallet after the removal
	*/
	void remove(Currency&);
	
	/* sets the value of all the currencies in the wallet to zero
	@post prints an intemized statement of all the currencies that are now empty
	*/
	void empty();
	
	/* checks if the whole wallet has no value in any currency
	@return true if the whole wallet is empty. False if any currencies have a value
	*/
	bool isEmpty();
	
	/* overloads the insertion operator for Wallet objects
	@pre an object of the ostream class and a Wallet object 
	@return the ostream object with the currency information from the Wallet object
	*/
	friend std::ostream& operator<< (std::ostream&, Wallet&);
};

#endif
