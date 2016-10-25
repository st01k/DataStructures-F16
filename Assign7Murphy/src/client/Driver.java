package client;

import static java.lang.System.out;

import java.util.Scanner;

import stack.StackRefBased;
import stack.StackException;
import calculator.Calculator;
import fixes.DivByZeroException;
import fixes.IllegalStateException;
import fixes.Infix;
import fixes.Postfix;

public class Driver {

	private static final Scanner scan = new Scanner(System.in);
	private static final String dbgStr = "[debug]";
	private static final String err1 = "ERROR: Invalid input";
	private static final String err2 = "ERROR: Cannot divide by 0";
	
	private static boolean debug = false;
	private static String prompt;
	
	/**
	 * Main.  Takes no arguments.
	 * @param args command line input
	 */
	public static void main(String[] args) {
		
		calc();
		scan.close();
		out.println("~ Namaste ~");
	}
	
	/**
	 * Calculator prompt - main interface.
	 */
	public static void calc() {
		
		//TODO active mode, method for answer retention/manipulation
		out.println("<<<< RPN Powered >>>>");
		out.println("Infix Calculator v1.0");
		out.println(">>>>>>>>>>=<<<<<<<<<<");
		out.println("Type 'help' any time.");
		out.println();
		
		boolean cont = true;
		do {

			prompt = "calc> ";
			if (debug) prompt = dbgStr + "calc> ";
			out.print(prompt);
			String in = scan.nextLine();
			
			switch(in) {
			
			case ("exit") 	: cont = false;
				break;
			case ("help") 	: printHelp();
				break;
			case ("test") 	: test();
				break;
			case ("debug") 	: toggleDebug();
				break;
			case ("post") 	: post();
				break;
			case ("eval") 	: eval();
				break;
			case ("cls") 	: formFeed();
				break; 
			default 		:  
				out.println("Invalid Command.  Type 'help' for a list of commands.");
			}
		} while (cont);
	}
	
	/**
	 * Prints help.
	 */
	public static void printHelp() {
		
		out.println();
		out.println("Available Commands: ");
		out.println("----------------------------------------------");
		out.println("help\tPrints this menu");
		out.println("test\tRuns unit tests");
		out.println("debug\tToggles debug mode");
		out.println("cls\tClear screen");
		out.println("post\tConvert infix to postfix form");
		out.println("eval\tEvaluate an infix expression");		
		out.println("exit\tExits current prompt");
		out.println();
		
		out.println("Formatting Rules:");
		out.println("----------------------------------------------");
		out.println("-Use only whole positive numbers.");
		out.println("-Multiplication: the operator must be present.");
		out.println(" Implied multiplication is not available.");
		out.println("-Example: 10/(2+3)*(4%3)");
		out.println();
		
		out.println("Available Operations:");
		out.println("---------------------");
		out.println("addition\t (+)");
		out.println("subtraction\t (-)");
		out.println("multiplication\t (*)");
		out.println("division\t (/)");
		out.println("modulation\t (%)");
		out.println("exponentiation\t (^)");
		out.println();
	}
	
	/**
	 * Runs unit tests.
	 */
	public static void test() {
		
		StackRefBased.unitTest();
		Infix.unitTest();
		Postfix.unitTest();
		Calculator.unitTest();
	}
	
	/**
	 * Toggles debug mode.
	 */
	public static void toggleDebug() {
		
		debug = !debug;
		Infix.toggleDebug();
		Postfix.toggleDebug();
		Calculator.toggleDebug();
	}
	
	/**
	 * Evaluator sub-prompt.
	 */
	public static void eval() {
		
		boolean exit = false;
		out.println();
		
		do {
			
			prompt = "calc.eval> ";
			if (debug) prompt = dbgStr + "calc.eval> ";
			out.print(prompt);
			String exp = scan.nextLine();
			
			switch (exp) {
			
			case ("help") 	: printHelp();
				break;
			case ("test") 	: test();
				break;
			case ("debug") 	: toggleDebug();
				break;
			case ("cls") 	: formFeed();
				break;
			case ("post") 	:
				out.println("Not available in this mode.");
				out.println("Returning to calc prompt.");			
			case ("exit") 	: exit = true;
				break;
			default 		:
				try {
					
					Calculator calc = new Calculator(exp);
					out.println(calc.evaluate());
				}
				catch (DivByZeroException dbze) {out.println(err2);}
				catch (IllegalStateException ise) {out.println(err1);}
				catch (StackException se) {out.println(err1);}
				out.println();
			}
		} while (!exit);
		out.println();		
	}
	
	/**
	 * Postfix converter sub-prompt.
	 */
	public static void post() {
		
		boolean exit = false;
		out.println();
		
		do {
			
			prompt = "calc.post> ";
			if (debug) prompt = dbgStr + "calc.post> ";
			out.print(prompt);
			String exp = scan.nextLine();
			
			switch (exp) {
			
			case ("help") 	: printHelp();
				break;
			case ("test") 	: test();
				break;
			case ("debug") 	: toggleDebug();
				break;
			case ("cls") 	: formFeed();
				break;
			case ("eval") 	:
				out.println("Not available in this mode.");
				out.println("Returning to calc prompt.");
			case ("exit") 	: exit = true;
				break;
			default 		:
				try {
					
					Calculator calc = new Calculator(exp);
					out.println("Postfix Form: " + calc.getPostfix());
				}
				catch (DivByZeroException dbze) {out.println(err2);}
				catch (IllegalStateException ise) {out.println(err1);}
				catch (StackException se) {out.println(err1);}
				out.println();
			}
		} while (!exit);
		out.println();
	}
	
	/**
	 * Prints a form feed to screen.  Scrolls up specified lines.
	 * Amount is specified with FFSZ constant.
	 */
	private static void formFeed() {
		
		final int FFSZ = 50;
		for (int i = 0; i < FFSZ; i++) out.println();
	}
}
