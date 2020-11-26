#include "Calculator.h"
#include <iostream>
#include <string>

using namespace std;

/* checks whether the given infix expression is valid
@pre expression - an expression as a string
@post none
@return a Queue<string> representation of the expression if valid; string exception otherwise */
Queue<string> verifyInfixExpression(string expression) {
	int operandCount = 0;
	int operatorCount = 0;
	int openParenthesesCount = 0;
	int closeParenthesesCount = 0;
	Queue<string> infix;
	string item = "";

	for (int i = 0; i < expression.size(); i++) {
		string c = expression.substr(i, 1);
		if (i == expression.size() - 1 && c != " ") item = item + c;
		if (c == " " || i == expression.size() - 1) {
			try {
				if (item == "(") openParenthesesCount++;
				else if (item == ")") closeParenthesesCount++;
				else if (item == "*" || item == "+" || item == "-" || item == "/" || item == "%") operatorCount++;
				else if (to_string(stoi(item)) == item) operandCount++;
			}
			catch (invalid_argument e) {
				throw string("ERROR: Invalid symbol: " + item);
			}
			LinkedNode<string>* node = new LinkedNode<string>(item);
			infix.enqueue(*node);
			item = "";
		}
		else {
			item = item + c;
		}
	}

	if (openParenthesesCount != closeParenthesesCount) throw string("ERROR: Parentheses are mismatched");
	if (operandCount != operatorCount + 1) throw string("ERROR: Operators and operands are mismatched");

	return infix;
}

/* Lab 4 - Kishore Srinivas and Jnana Uhuru */
int main() {
	while (true) {
		bool receivedValidExpression = false;
		Queue<string> expression;
		while (!receivedValidExpression) {
			cout << "Enter an infix expression or Q/q to quit: ";
			string input;
			getline(cin, input);
			if (input == "Q" || input == "q") {
				cout << endl;
				system("pause");
				return 0;
			}
			try {
				expression = verifyInfixExpression(input);
			}
			catch (string e) {
				cout << e << endl;
				continue;
			}
			catch (...) {
				cout << "generic exception caught" << endl;
			}
			receivedValidExpression = true;
			cout << "You entered: " << input << endl;
		}

		Calculator calc;
		double result = calc.calculate(expression);
		cout << "Result: " << result << endl;
		cout << "==============================" << endl << endl;
	}
	return 0;
}