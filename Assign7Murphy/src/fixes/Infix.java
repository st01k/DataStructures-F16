package fixes;

import static java.lang.System.out;

import stack.StackRefBased;

/**
 * Infix notation object
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Infix implements FixInterface {

	private static final String err1 = "Invalid form";
	
	private static boolean debug = false;
	
	private String exp;
	
	/**
	 * Creates infix object.
	 * @param in expression
	 */
	public Infix(String in) {
		
		exp = in;
	}
	
	/**
	 * Returns infix expression.
	 */
	@Override
	public String toString() {
		
		return exp;
	}
	
	/**
	 * Toggles debug mode.
	 */
	public static void toggleDebug() {
		
		debug = !debug;
	}
	
	/**
	 * Converts infix object to postfix object.
	 * @return postfix conversion
	 */
	public Postfix toPostfix() {
		
		StackRefBased<Character> opStk = new StackRefBased<Character>();
		String holder = "(" + exp + ")";
		String pStr = "";
		
		if (debug) out.println("---Infix.toPostfix---");
		if (debug) out.println("expression parenthesis added");
		
		for (int i = 0; i < holder.length(); i++) {
			
			Character token = holder.charAt(i);
			String s = token.toString();
			// catches numbers
			if (s.matches(digitRegex)) {
				
				pStr += token;
				if (debug) out.println(pStr);
			}
			// catches operators
			else if (s.matches(operatorRegex)) {
				
				while (precedence(token) <= precedence(opStk.peek())) {
					
					pStr += " " + opStk.pop();
					if (debug) out.println(pStr);
				}
				opStk.push(token);
				pStr += " ";
			}
			// catches parentheses
			else if (s.matches(parenRegex)) {
				
				if (debug) out.println("paren");
				if (s.matches("[(]")) {
					
					opStk.push(token);
				}
				if (s.matches("[)]")) {
					
					char top;
					while ((top = opStk.peek()) != '(') {
							
						pStr += " " + top;
						if (debug) out.println(pStr);
						opStk.pop();
					}
					opStk.pop();
				}
			}
			else throw new IllegalStateException("convertToPostfix: " + err1);
		}		
		return new Postfix(pStr);
	}
	
	/**
	 * Assigns operator precedence where 3 is
	 * the highest and 0 is the lowest.
	 * Used in conversion.
	 * @param c operator
	 * @return precedence level
	 */
	private int precedence(char c) {
		
		switch(c) {
		
		case '^' : return 3;
		case '/' :
		case '%' :
		case '*' : return 2;
		case '+' :
		case '-' : return 1;
		default  : return 0;
		}
	}
	
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing Infix Class:\n");
		
		Infix[] testSequence = {
				
				new Infix("1+1"),
				new Infix("3*(2+2)"),
				new Infix("1-5"),
				new Infix("5%3^(1/2)")
		};
		
		for (Infix i : testSequence) {
			
			out.println("Input: " + i.exp);
			out.println("Postfix Form: " + i.toPostfix());
			out.println();
		}
		
    	out.println("-------------------- Infix Unit Test Complete.\n");
	}
}
