#ifndef CITY_H
#define CITY_H

#include <string>

class City
{
private:
	std::string name;
	double x;
	double y;
	int populationDensity;
	double livingIndex;
	std::string climate;
	std::string language;
	std::string activites;
	std::string country;

	static std::string comparisonType;
	static std::string outputType;

public:
	/* This constructor creates an city object
		Pre:
		Post:
		Return:
	*/
	City();

	/* This constructor creates an a city object with default parameters
		Pre: std::string - initial name
			 double - initial x
			 double - intial y
			 int - initial population density
			 double - initial cost of living index
			 std::string - initial climate
			 std::string initial languages
			 std::string - initial activities
			 std::string - initial country
		Post:
		Return:
	*/
	City(std::string, double, double, int, double, std::string, std::string, std::string, std::string);

	/* This function gets the x member variable
		Pre:
		Post:
		Return: the x member variable
	*/
	int getX();

	/* This function sets the x member variable
		Pre: int - the value to set the x member variable to
		Post:
		Return:
	*/
	void setX(int);

	/* This function gets the y member variable
		Pre:
		Post:
		Return: the x member variable
	*/
	int getY();

	/* This function sets the y member variable
		Pre: int - the value to set the y member variable to
		Post:
		Return:
	*/
	void setY(int);

	/* This function gets the name member variable
		Pre:
		Post:
		Return: the name member variable
	*/
	std::string getName();

	void setName(std::string);

	/* This function gets the population density member variable
		Pre:
		Post:
		Return: the population density member variable
	*/
	int getPopulationDensity();

	/* This function sets the population density member variable
		Pre: int - the value to set the popualtion density member variable to
		Post:
		Return:
	*/
	void setPopulationDensity(int);

	/* This function gets the living index member variable
		Pre:
		Post:
		Return: the living index member variable
	*/
	double getLivingIndex();

	/* This function sets the livingIndex member variable
		Pre: double - the value to set the livingIndex member variable to
		Post:
		Return:
	*/
	void setLivingIndex(double);

	/* This function gets the climate member variable
		Pre:
		Post:
		Return: the climate member variable
	*/
	std::string getClimate();

	/* This function sets the climate member variable
		Pre: int - the value to set the climate member variable to
		Post:
		Return:
	*/
	void setClimate(std::string);

	/* This function gets the language member variable
		Pre:
		Post:
		Return: the language member variable
	*/
	std::string getLanguage();

	/* This function sets the language member variable
		Pre: int - the value to set the language member variable to
		Post:
		Return:
	*/
	void setLanguage(std::string);

	/* This function gets the activities member variable
		Pre:
		Post:
		Return: the activities member variable
	*/
	std::string getActivities();

	/* This function gets the country member variable
		Pre:
		Post:
		Return: the country member variable
	*/
	std::string getCountry();

	/* This function sets the country member variable
		Pre: int - the value to set the country member variable to
		Post:
		Return:
	*/
	void setCountry(std::string);

	/* This function sets the type of data to compare City objects with
		Pre: std::string - name of the data member to compare city objects with
		Post:
		Return:
	*/
	static void setComparisonType(std::string);

	/* This function sets the type of output, condensed, full, or file
		Pre: std::string - name of the type to ouptut city objects as
		Post:
		Return:
	*/
	static void setOutputType(std::string);

	/* This operator returns true if the left city parameter is greater than the right, based on comparison type
		Pre: City& - The city to check if greater than the other
			 City& - The city to compare the first parameter to
		Post:
		Return: true or false
	*/
	friend bool operator>(City&, City&);

	/* This operator returns true if the right city parameter is greater than the left, based on comparison type
		Pre: City& - The city to compare second parameter to
			 City& - The city to check if greater than the other
		Post:
		Return: true or false
	*/
	friend bool operator<(City&, City&);
	
	/* This operator returns true if the left city parameter equal to the right, based on comparison type
		Pre: City& - The city to check if equal to the other
			 City& - The city to compare with paramter 1
		Post:
		Return: true or false
	*/
	friend bool operator==(City&, City&);

	/* This operator allows output of city pointers, by name
		Pre: std::ostream& - The output stream to send city's data to
			 City* - pointer to the city to output
		Post:
		Return: output stream
	*/
	friend std::ostream& operator<<(std::ostream&, City*);

	/* This operator allows input of a city, by name
		Pre: std::istream& - The input stream to get city data to
			 City& - The city to get input of
		Post: City& will contain user input
		Return: input stream
	*/
	friend std::istream& operator>>(std::istream&, City&);
};

#endif // !CITY_H