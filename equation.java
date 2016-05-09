import java.util.*;

public class equation {

	//using ArrayDeque here since it does everything and is flexible. Could do a linkedList though..?
	private ArrayDeque inputQueue; //input queue. not sure if i really need this
	private ArrayList outputQueue;//output queue
	private ArrayDeque operatorStack; //operator stack


	public equation(String sEquationLine) {
		inputQueue = new ArrayDeque<Character>();
		outputQueue = new ArrayList<Character>();
		operatorStack = new ArrayDeque<Character>(); //Treat as LIFO

		//populate input queue
		for (int i = 0; i < sEquationLine.length(); i++) {
			inputQueue.add(sEquationLine.charAt(i));
		}
	}

	//interpret line and do the thing
	public String convertToRPN() {

		while (inputQueue.isEmpty() != true) {
			//deal with parenthesis here
			//left parentheses
			if (inputQueue.peekFirst().equals('(')) {
				//simply push onto output for left
				operatorStack.addLast(inputQueue.pollFirst());

			//right parentheses
			} else if (inputQueue.peekFirst().equals(')')) {
				while (!operatorStack.peekLast().equals('(')) {
					outputQueue.add(operatorStack.pollLast());
				}
				operatorStack.pollLast(); //remove parentheses from stack so it isn't added to output
				inputQueue.removeFirst();

			//check if operator, if so add to operatorStack, if not assume it is an (int)operand
			} else if (operators.isOperator((Character) inputQueue.peekFirst())) { //i don't understand why i have to cast this a Character (object)??

				if (operatorStack.isEmpty() || operatorStack.peekLast().equals('(') ) {
					operatorStack.addLast(inputQueue.pollFirst());

				//while there is an operator on the operatorStack with greater precedence than the current pop that one
				} else if (!operatorStack.peekLast().equals('(') && operators.compare((Character) inputQueue.peekFirst(), (Character)operatorStack.peekLast()) <= 0) {
					outputQueue.add(operatorStack.pollLast());
					continue; //need to restart loop since may be operatorStack.size() > 1
				} else {
					//otherwise add operator to the operatorStack
					operatorStack.addLast(inputQueue.pollFirst());
				}

			//maybe remove spaces
			} else {
				//add operand to output stack
				//System.out.println("Add operand to outputStack: " + inputQueue.peekFirst());
				outputQueue.add(inputQueue.pollFirst());
			}
		}
		//once inputQueue is empty need to finish draining the operators
		while (operatorStack.isEmpty() != true) {
			//check for parentheses mismatch
			if (operatorStack.peekLast().equals('(') || operatorStack.peekLast().equals(')')) {
				//If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses.
				throw new Error("parenthesis mismatched! check the input function is valid");
			} else {
				//pop the operator onto the outputStack
				outputQueue.add(operatorStack.pollLast());
			}
		}
		return (outputQueue.toString());
	}
}