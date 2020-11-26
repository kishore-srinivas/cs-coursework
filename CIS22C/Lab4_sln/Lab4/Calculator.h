#include "Stack.h"
#include "Queue.h"

using namespace std;

class Calculator {
public:
	/* creates a Calculator object*/
	Calculator();

	/* destroys the Calculator object */
	~Calculator();

	/* calculates the result of the given infix expression
	@pre infix - a Queue<string> object where each element is either an operator or operand
	@post none
	@return the value of the evaluated expression, as a double */
	double calculate(Queue<string> infix);

	/* converts the given infix expression to prefix
	@pre infix - a Queue<string> object where each element is either an operator or operand
	@post none
	@return a Queue<string> object that represents the prefix version of the expression */
	Queue<string> convertToPrefix(Queue<string> infix);

	/* converts the given infix expression to postfix
	@pre infix - a Queue<string> object where each element is either an operator or operand
	@post none
	@return a Queue<string> object that represents the postfix version of the expression */
	Queue<string> convertToPostfix(Queue<string> infix);

	/* evaluates a specific operation 
	@pre first - the left-hand operand
		 second - the right-hand operand
		 operation - the operation to perform on first and second
	@post none
	@return a double value of the result of the operation */
	double performOperation(double first, double second, string operation);

	/* evalutes the given prefix expression
	@pre prefix - a Queue<string> object representing a prefix expression
	@post none
	@return a double value of the result of evaluating the expression */
	double evaluatePrefix(Queue<string> prefix);

	/* evalutes the given postfix expression
	@pre prefix - a Queue<string> object representing a postfix expression
	@post none
	@return a double value of the result of evaluating the expression */
	double evaluatePostfix(Queue<string> postfix);

	/* gets the priority of a given operation
	@pre operation - an operation (+, -, *, /, %) 
	@post none
	@return an int representing the priority of the operation, such that + and - have lower priority than *, /, and % */
	int getPriority(string operation);

	/* checks whether the item passed in is an operator
	@pre item - a string object
	@post none
	@return true if item is +, -, *, /, or %; false otherwise */
	bool isOperator(string item);
};