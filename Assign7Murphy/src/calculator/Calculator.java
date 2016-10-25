package calculator;

import static java.lang.System.out;
import fixes.Infix;
import fixes.Postfix;

/**
 * Infix expression calculator. 
 * Converts expression to postfix and evaluates.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Calculator {
		
	private static boolean debug = false;
	
	private String exp;

	/**
	 * Creates calculator object.
	 * @param in expression
	 */
	public Calculator(String in) {
		
		exp = in;
	}
	
	/**
	 * Returns input expression.
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
	 * Converts expression from infix to postfix
	 * and evaluates the expression.
	 * @return evaluation result
	 */
	public Double evaluate() {
		
		if (debug) out.println("---Calculator.evaluate--- ");
		Infix input = new Infix(exp);
		Postfix postfix = input.toPostfix();
		return postfix.evaluate();
	}
	
	/**
	 * Returns user input in postfix form.
	 * If user input is invalid infix form, this will still
	 * convert it.  There is no guarantee it will evaluate.
	 * @return unevaluated postfix string 
	 */
	public String getPostfix() {
		
		if (debug) out.println("---Calculator.getPostfix--- ");
		Infix temp = new Infix(exp);
		return temp.toPostfix().toString();
	}
	
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing Calculator Class:\n");
		
		Calculator[] testSequence = {
				
				new Calculator("3*(2+10)"),
				new Calculator("2^(1-2)"),
				new Calculator("((25920/72)/2)/(573/10)"),
				new Calculator("(3+2*3)-(2*(3+2*3))"),
				new Calculator("9^(1/2)"),
				new Calculator("5^(1/2)*(1/2)+(1/2)")
		};
		
		for (Calculator c : testSequence) {
			
			out.println("Input: " + c);
			out.println("Postfix Form: " + c.getPostfix());
			out.println("Evaluation: " + c.evaluate());
			out.println();
		}
		
    	out.println("-------------------- Calculator Unit Test Complete.\n");
	}
}