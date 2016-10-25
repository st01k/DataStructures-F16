package fixes;

import static java.lang.System.out;

import stack.StackRefBased;

/**
 * Postfix notation (RPN) object
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Postfix implements FixInterface {

	private static final String err1 = "Invalid postfix form";
	private static final String err2 = "Cannot divide by 0";
	
	private static boolean debug = false;
	
	private String exp;
	
	/**
	 * Creates a postfix object.
	 * @param in valid postfix expression
	 */
	public Postfix(String in) {
		
		exp = in;
	}
	
	/**
	 * Returns postfix expression.
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
	 * Evaluates operation, and returns the result.
	 * @param a operand 1
	 * @param b operand 2
	 * @param op operator
	 * @return result of operation
	 * @throws IllegalStateException with invalid input
	 * @throws DivByZeroException with attempted div by zero
	 */
	private static Double operation(Double a, Double b, Character op) 
			throws IllegalStateException, DivByZeroException {
		
		switch(op) {
		
		case '+' : return a + b;
		case '-' : return a - b;
		case '*' : return a * b;
		case '/' :
			if (b == 0) throw new DivByZeroException("div: " + err2);
			return a / b;
		case '%' :
			if (b == 0) throw new DivByZeroException("mod: " + err2);
			return a % b;
		case '^' : return Math.pow(a, b);
		default : throw new IllegalStateException("evaluate: " + err1);
		}
	}
	
	/**
	 * Evaluates postfix expression.
	 * @return evaluation result
	 */
	public Double evaluate() {
		
		StackRefBased<String> stk = new StackRefBased<String>();
		String[] temp = exp.split(" ");
		Double result = 0.0;
		
		if (debug) out.println("---Postfix.evaluate--- ");
		
		for (int i = 0; i < temp.length; i++) {
			
			String token = temp[i];
			
			if (token.matches(digitRegex)) stk.push(token);
			else {
				
				Double opd2 = Double.valueOf(stk.pop());
				Double opd1 = Double.valueOf(stk.pop());
				Character op = token.charAt(0);
				result = operation(opd1, opd2, op);
				stk.push(result.toString());
				
				if (debug) out.println(opd1+" "+op+" "+opd2+" = "+stk.peek());
			}
		}
		return Double.valueOf(stk.peek());
	}
			
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing Postfix Class:\n");
		
		Postfix[] testSequence = {
				
				new Postfix("2 3 -"),
				new Postfix("2 3 + 0 *"),
				new Postfix("1 3 3 - +")
		};
		
		for (Postfix p : testSequence) {
		
			out.println("Input: " + p.toString());
			out.println("Evaluation: " + p.evaluate());
			out.println();
		}
		
    	out.println("-------------------- Postfix Unit Test Complete.\n");
	}
}