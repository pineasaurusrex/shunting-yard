import java.util.*;

public class operators {

	//https://www.engr.mun.ca/~theo/Misc/exp_parsing.htm and http://andreinc.net/2010/10/05/converting-infix-to-rpn-shunting-yard-algorithm/
	//create hashmap array of valid operators and their respective precedence/associativity
	//for this task all operators are left associated therefore I didn't specify
	private static final Map<Character, Integer> OPERATOR = new HashMap<Character, Integer>();
	static {
		OPERATOR.put('+', 2); 
		OPERATOR.put('-', 2);
		OPERATOR.put('/', 3);
		OPERATOR.put('*', 3);
	}

	public static int compare(Character o1, Character o2) {
		return (OPERATOR.get(o1) - OPERATOR.get(o2));
	}

	//Test if input is an operator
	//param token The token to be tested
	//return True if token is an operator. Otherwise False
	public static boolean isOperator(Character token) {
		return OPERATOR.containsKey(token);
	}

}