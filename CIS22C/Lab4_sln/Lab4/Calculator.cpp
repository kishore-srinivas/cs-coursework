#include "Calculator.h"

Calculator::Calculator() {}

Calculator::~Calculator() {}

double Calculator::calculate(Queue<string> infix) {
	Queue<string> postfix = convertToPostfix(infix);
	Queue<string> prefix = convertToPrefix(infix);
	cout << "postfix: " << postfix;
	cout << "prefix: " << prefix << endl;
	double resultPostfix = evaluatePostfix(postfix);
	cout << "postfix result: " << resultPostfix << endl;
	double resultPrefix = evaluatePrefix(prefix);
	cout << "prefix result: " << resultPrefix << endl;
	if (resultPostfix - resultPrefix > -0.005 && resultPostfix - resultPrefix < 0.005) return resultPostfix;
	else return -9999;
}

Queue<string> Calculator::convertToPostfix(Queue<string> infix) {
	Queue<string> postfix;
	Stack<string> operators;
	while (!infix.isEmpty()) {
		string item = infix.dequeue();
		if (item == "(") {
			operators.push(item);
		}
		else if (item == ")") {
			while (!operators.isEmpty() && operators.peek() != "(") {
				postfix.enqueue(operators.pop());
			}
			if (!operators.isEmpty() && operators.peek() == "(") operators.pop();
		}
		else if (isOperator(item)) {
			if (operators.isEmpty()) {
				operators.push(item);
			}
			else {
				while (!operators.isEmpty()
					&& operators.peek() != "("
					&& getPriority(operators.peek()) >= getPriority(item)) {
					postfix.enqueue(operators.pop());
				}
				operators.push(item);
			}
		}
		else {
			postfix.enqueue(item);
		}
	}
	while (!operators.isEmpty()) {
		postfix.enqueue(operators.pop());
	}
	return postfix;
}

Queue<string> Calculator::convertToPrefix(Queue<string> infix) {
	Stack<string> stack;
	while (!infix.isEmpty()) {
		stack.push(infix.dequeue());
	}
	Queue<string> reversedInfix;
	while (!stack.isEmpty()) {
		string item = stack.pop();
		if (item == "(") item = ")";
		else if (item == ")") item = "(";
		reversedInfix.enqueue(item);
	}

	Queue<string> postfixOutput = convertToPostfix(reversedInfix);

	Stack<string> stack2;
	while (!postfixOutput.isEmpty()) {
		stack2.push(postfixOutput.dequeue());
	}
	Queue<string> prefix;
	while (!stack2.isEmpty()) {
		prefix.enqueue(stack2.pop());
	}
	return prefix;
}

double Calculator::performOperation(double first, double second, string operation) {
	if (operation == "+") return first + second;
	if (operation == "-") return first - second;
	if (operation == "*") return first * second;
	if (operation == "/") return first / second;
	if (operation == "%") return int(first) % int(second);
}

double Calculator::evaluatePostfix(Queue<string> postfix) {
	Stack<double> stack;
	while (!postfix.isEmpty()) {
		string item = postfix.dequeue();
		if (isOperator(item)) {
			double second = stack.pop();
			double first = stack.pop();
			double result = performOperation(first, second, item);
			stack.push(result);
		}
		else {
			double d = stod(item);
			stack.push(d);
		}
	}
	return stack.pop();
}

double Calculator::evaluatePrefix(Queue<string> prefix) {
	double result = 0;
	Stack<string> stack;
	while (!prefix.isEmpty()) {
		string item = prefix.dequeue();
		if (!isOperator(item) && !stack.isEmpty() && !isOperator(stack.peek())) {
			double second = stod(item);
			double first = stod(stack.pop());
			string operation = stack.pop();
			string res = to_string(performOperation(first, second, operation));
			while (!stack.isEmpty() && !isOperator(stack.peek())) {
				double second = stod(res);
				double first = stod(stack.pop());
				string operation = stack.pop();
				res = to_string(performOperation(first, second, operation));
			}
			stack.push(res);
		}
		else {
			stack.push(item);
		}
	}
	return stod(stack.pop());
}

int Calculator::getPriority(string operation) {
	if (operation == "*" || operation == "/" || operation == "%") return -1;
	if (operation == "+" || operation == "-") return -2;
	else {
		std::cout << "OPERATION " << operation << " is not defined" << endl;
		return -10;
	}
}

bool Calculator::isOperator(string item) {
	return (item == "+" || item == "-" || item == "*" || item == "/" || item == "%");
}