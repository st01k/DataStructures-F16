import static java.lang.System.out;
import java.util.Scanner;

/**
 * Prefix class that validates and evaluates equations in prefix notation.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Prefix {

	// constants
	private final String identifierRegex = "[a-z]";
	private final String operatorRegex = "[-+*/%^]";
	private final String multiOperatorRegex = "[-+*/%^]+";
	private final String digitRegex = "^-?[0-9]+";
	private final String identifiers = "abcdefghijklmnopqrstuvwxyz";
	
	// class variables
	private boolean solvable;
	private String input;
	private String strExp = "";
	
	// only used for valued expression object
	private String solution;
	private Integer[] intExpAry;
	
	/**
	 * Default Null Prefix Object
	 */
	public Prefix () {
		
		solvable = false;
		strExp = null;
	}
	
	/**
	 * Prefix with an expression and a flag for whether
	 * it is a valued expression or a form only.
	 * Evaluate on construction, if possible.
	 * @param newExp new expression
	 * @param hasValues true if expression is made up of numbers; 
	 * 					false otherwise
	 */
	public Prefix(String newExp, boolean hasValues) {
		
		if (hasValues) {
			
			input = newExp;
			buildStrExp(newExp);
			setSolvable();
			solve();
		}
		else {
			
			input = newExp;
			strExp = input;
			solvable = false;
		}
	}
	
	// Accessors -------------------------------------------------------------------------------------------------------
	/**
	 * Returns the expression in prefix notation if possible.  If the
	 * expression cannot be converted properly, returns an invalid form.
	 * @return strExp
	 */
	public String getStrExp() {
		
		return strExp;
	}
	
	/**
	 * Returns the expression that was used to create the Prefix.
	 * @return
	 */
	public String getInput() {
		
		return input;
	}
	
	/**
	 * Returns the solution if one was able to be calculated.
	 * Returns null otherwise.
	 * @return solution
	 */
	public String getSolution() {
		
		if (solvable) return solution;
		return null;
	}
	
	/**
	 * Returns true if the expression is solvable.
	 * @return true if solvable; false otherwise
	 */
	public boolean getSolvable() {
		
		return solvable;
	}
	
	// Mutators --------------------------------------------------------------------------------------------------------
	/**
	 * Determines is initial expression is solvable and sets
	 * the Prefix object accordingly.
	 */
	private void setSolvable() {
		
		if (isPrefix()) solvable = true;
	}
	
	/**
	 * If the Prefix is solvable, evaluates it.
	 * Sets Prefix solution attribute with the evaluation result.
	 */
	private void solve() {
		
		if (solvable) {
		
			StringBuffer sb = new StringBuffer(strExp);
			Double temp = evalPrefix(sb);
			solution = temp.toString();
		} 
	}
	
	/**
	 * Builds strExp from a valued prefix equation
	 * to be used for prefix form validation.
	 * @param s expression
	 */
	private void buildStrExp(String s) {
		
		int count = 0;
		intExpAry = new Integer[s.toCharArray().length];
		Scanner scan = new Scanner(s);
		
		outerloop:
		while (scan.hasNext()) {
			
			String token = scan.next();
			
			// detects operators
			if (token.matches(multiOperatorRegex)) {
				strExp += token;
				continue;
			}
			// detects digits
			else if (token.matches(digitRegex)) {
				
				// detects repeat values, assigns to same identifier
				for (int i = 0; i < intExpAry.length; i++) {
					
					if (intExpAry[i] == null) break;
					if (Integer.parseInt(token) == intExpAry[i]) {
						
						strExp += identifiers.charAt(i);
						continue outerloop;
					}
				}
				
				// stores a unique value
				intExpAry[count] = Integer.parseInt(token);
				// assigns identifier to unique value
				strExp += identifiers.charAt(count);
				
				count++;
			}
		}
		
		scan.close();
	}

	// Overrides -------------------------------------------------------------------------------------------------------
	/**
	 * Returns prefix information as a string.
	 */
	public String toString() {
		
		String s = input + " | " + strExp;
		if (solvable) s += " | " + solution;
		return s;
	}
	
	/**
	 * Returns true if object contains identical 
	 * input and expression form values.
	 */
	public boolean equals(Object o) {
		
		if (o instanceof Prefix) {
		
			Prefix target = (Prefix) o;
			
			return input.equals(target.input) && 
				   strExp.equals(target.strExp);
				
		}
		return false;
	}
	
	// Prefix Functionality --------------------------------------------------------------------------------------------
	/**
	 * Determines if the expression is in valid prefix notation.
	 * @return true if expression is a valid prefix; false otherwise
	 */
	boolean isPrefix() {
		
		int size = strExp.length();
		int lastChar = endPrefix(0, size - 1);
		
		if (lastChar >= 0 && lastChar == size - 1) return true;
		else return false;
	}
	
	/**
	 * Recursively finds each prefix within a prefix expression.
	 * @param first index of first character in expression
	 * @param last index of last character in expression
	 * @return index of first character in expression; -1 if 
	 * expression is invalid 
	 */
	private int endPrefix(int first, int last) {
		
		// illegal indices
		if (first < 0 || first > last) return -1;
		
		Character ch = strExp.toLowerCase().charAt(first);
		
		if (ch.toString().matches(identifierRegex)) return first;
		else if (ch.toString().matches(operatorRegex)) {
			
			int firstEnd = endPrefix(first + 1, last);
			
			if (firstEnd > -1) return endPrefix(firstEnd + 1, last);
			else return -1;
		}
		else return -1;
	}
	
	/**
	 * Recursively evaluates validated, valued prefix equation.
	 * Allows for the +, -, *, /, %, and ^ operators.
	 * @throws DivByZeroException when division by zero is attempted
	 * @param s validated expression
	 * @return expression evaluation result
	 */
	private double evalPrefix(StringBuffer s) throws DivByZeroException {
		
		Character ch = s.charAt(0);
		s = s.deleteCharAt(0);
		
		if (ch.toString().matches(identifierRegex)) {
			
			int i = identifiers.indexOf(ch.toString());
			return intExpAry[i];
		}
		else if (ch.toString().matches(operatorRegex)) {
			
			Character op = ch.charValue();
			double operand1 = evalPrefix(s);
			double operand2 = evalPrefix(s);
			
			switch(op) {
			
			case '+' :
				return operand1 + operand2;
				
			case '-' :
				return operand1 - operand2;
				
			case '*' :
				return operand1 * operand2;
				
			case '/' :
				if (operand2 == 0) {
					
					throw new DivByZeroException("Division: Cannot divide by 0.");
				}				
				return operand1 / operand2;
				
			case '%' : 
				if (operand2 == 0) {
					
					throw new DivByZeroException("Modulo: Cannot divide by 0.");
				}				
				return operand1 % operand2;
				
			case '^' : 
				return Math.pow(operand1, operand2);
	
				
			default : 
				return 0;
			}
		}
		else return 0;
	}
	
	// Testing ---------------------------------------------------------------------------------------------------------
	/**
	 * Tests the Prefix class.
	 */
	public static void unitTest() {
		
		Prefix[] testSequence = {
				
				new Prefix("+ab", false),
				new Prefix("+/ab-cd", false),
				new Prefix("+ab+", false),
				new Prefix("-- 1 2 3", true),
				new Prefix("++ 0 0 3", true),
				new Prefix("% 10 9", true),
				new Prefix("-* 1 -2 / 3 4", true),
				new Prefix("*+ 7 ^* 6 2 5 - 4 / 8 3", true),
				new Prefix("^ 9 0", true)
		};
		
		out.println("-------------------------- Testing " + testSequence.length + " Prefixes");
		
		for (Prefix p : testSequence) {
			
			out.println("Input: " + p.input);
			out.println("Prefix Form: " + p.strExp);
			
			if (p.solvable) out.println("Solution: " + p.getSolution());
			else out.println("Is valid: " + p.isPrefix());
			
			out.println();
		}
		
		Prefix tester1 = new Prefix("^ 3 3", true);
		Prefix tester2 = new Prefix("^ 3 3", true);
		Prefix tester3 = new Prefix("^ 1 3", true);
		
		out.println("Comparisons:");
		out.println("------------");
		out.println("Same Prefix Object");
		out.println(tester1);
		out.println(tester1);
		out.println(tester1.equals(tester1) + "\n");
		
		out.println("Same Values, Different Prefix Objects");
		out.println(tester1);
		out.println(tester2);
		out.println(tester1.equals(tester2) + "\n");
		
		out.println("Different Prefix Objects");
		out.println(tester2);
		out.println(tester3);
		out.println(tester2.equals(tester3) + "\n");
	}
}
