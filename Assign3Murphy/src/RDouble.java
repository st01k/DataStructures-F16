import static java.lang.System.out;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Calculates the amount of money made each day in a month if on Day 1 <br>
 * $0.01 is made, and each subsequent day doubles the previous days' earnings.<br>
 * @author Casey J. Murphy
 * @version 1.2
 */
public class RDouble {
	
	// class constant objects
	private static final Scanner scan = new Scanner(System.in);
	private static final DecimalFormat NPAT = new DecimalFormat("###,###,###,###");
	private static final DecimalFormat DPAT = new DecimalFormat("$###,###,##0.00");
	
	// class constant primitives
	private static final int BASE = 2;
	private static final int MIN_DAY = 1;
	private static final int MAX_DAY = 31;
	
	// class variables
	private static long resultPennies = 0;
	private static double resultDollars = 0;
	
	/**
	 * Interactive driver.  Queries user for input.
	 * @param args arguments passed in from command line
	 */
	public static void main(String[] args) {
		
		if (args.length > 0) {
			
			// invokes non-interactive execution
			cliArgs(args);
			scan.close();
			return;
		}
		
		printGreeting();
		// loop menu until user break
		do {
			out.print("Enter day number (0 to quit): ");
			int day = valDay(scan.next());
			if (day == 0) break;
			calc(day);
			printResult(day);
		} while(true);
		
		out.println();
		out.print("View entire payment schedule (y/n)? ");
		String ans = scan.next();
		if (ans.matches("[Yy]")) test();
		
		scan.close();
		out.println();
		out.println("Progam Completed Successfully.");
	}
	
	// Operations ------------------------------------------------------------------------------------------------------
	/**
	 * Sets class variables according to user input.<br>
	 * Calls recursive doubling method.
	 * @param _day day of month
	 */
	public static void calc(int _day) {
		
		// passes day - 1 to count base case doubling (^0 = 1)
		// because minimum user input is day 1
		resultPennies = rDouble(_day - 1);
		resultDollars = (double) resultPennies / 100;
	}
	
	/**
	 * Recursively calculates constant BASE to exponent passed in.
	 * @param e exponent
	 * @return BASE^(i)
	 */
	public static long rDouble(int e) {
		
		if (e == 0) {
			return 1;
		}
		else if (e == 1) {
			return BASE;
		}
		else {
			return BASE * rDouble(--e);
		}
	}
	
	/**
	 * Calculates cumulative total of money earned for a specific day.
	 * @param d day
	 * @return	total amount earned to date
	 */
	public static double rDoubleSum(int d) {
		
		double toDate = 0;
		
		for (int i = MIN_DAY; i <= d; i++) {
			
			// passes i(day) - 1 to count base case doubling (^0 = 1)
			// because minimum user input is day 1
			long pTemp = rDouble(i - 1);
			double dTemp = (double) pTemp / 100;
			
			toDate += dTemp;
		}
		
		return toDate;	
	}
	
	// Print Functions -------------------------------------------------------------------------------------------------
	/**
	 * Prints greeting message in normal run (no cli arguments).
	 */
	public static void printGreeting() {
		
		out.println("--------------------------------------------------");
		out.println("\tBest Job Ever Payment Calculator");
		out.println("--------------------------------------------------");
		out.println("See what you will earn on any day of the month.");
		out.println();
	}
	
	/**
	 * Prints formatted version of user's calculated result.
	 * @param _day day of month
	 */
	public static void printResult(int _day) {
		
		out.println();
		out.print("Day " + _day + ": ");
		out.print(NPAT.format(resultPennies) + " cents = ");
		out.print(DPAT.format(resultDollars) + " | ");
		out.println("To Date: " + DPAT.format(rDoubleSum(_day)));
		out.println();

	}
	
	// CLI Functionality -----------------------------------------------------------------------------------------------
	/**
	 * Evaluates command line arguments from options and takes appropriate action for each.
	 * @param _args arguments from command line
	 */
	public static void cliArgs(String[] _args) {
		
		// check for args
		if (_args.length > 0) {
			
			// convert args
			char[] options = _args[0].toCharArray();
			
			// if no options, run args
			if (options[0] != '-') {
				
				int arg = 0;
				//validate input type
				if (_args[0].matches("[0-9]+")) {
					
					arg = Integer.parseInt(_args[0]);
					
					// validate input range
					if (MIN_DAY <= arg && arg <= MAX_DAY) {
						
						calc(arg);
						printResult(arg);
						return;
					}
				}
				out.println("Invalid arguments");
			}
			
			// run valid options
			if (options[0] == '-') {
				
				switch (options[1]) {
				
				case 't' : 
					test();
					break;
				case 'h' : 
					printHelp();
					break;
				default :
					out.println("One or more invalid options");
					break;
				}
			}
		}
	}
	
	/**
	 * Prints list of all days and corresponding money earned.<br>
	 * Prints cumulative totals for each day.<br>
	 * Can only be run from command line with '-t' option.
	 */
	public static void test() {
		
		double total = 0;
		
		// prints list of all days and earnings
		out.println();
		out.println("Day:\tDaily Payment:\t Cumulative:");
		out.println("--------------------------------------");
		for (int i = MIN_DAY; i <= MAX_DAY; i++) {
			
			long pTemp = rDouble(i - 1);
			double dTemp = (double) pTemp / 100;
			
			total += dTemp;
			
			out.format("%2d: ", i);
			out.format("\t$%12.2f\t$%12.2f%n", dTemp, total);
		}
		out.println();
	}

	/**
	 * Displays help information and command line options.<br>
	 * Can only be run from command line with '-h' option.
	 */
	public static void printHelp() {
		
		out.println();
		out.println("Command Line Options:");
		out.println("--------------------------------------------------");
		out.println("WINDOWS - Run from the command line with either: ");
		out.println("java -jar Asign3Murphy.jar <options>");
		out.println("java -jar Asign3Murphy.jar <args>");
		out.println();
		out.println("*NIX - Run from the command line with either: ");
		out.println("./Asign3Murphy.jar <options>");
		out.println("./Asign3Murphy.jar <args>");
		out.println("--------------------------------------------------");
		out.println("OPTIONS:");
		out.println("-t\tRuns program in test mode, then exits.");
		out.println("-h\tHelp.  Prints this list.");
		out.println();
		out.println("ARGUMENTS:");
		out.println("n\tDay of month as an integer.");
		out.println("RANGE:\t" + MIN_DAY + " - " + MAX_DAY + ", used to calculate daily payment.");
		out.println();
	}
	
	// Validation ------------------------------------------------------------------------------------------------------
	/**
	 * Trims white space off of string passed in.<br>
	 * Added for Windows OS compatibility.
	 * @param s string to be trimmed
	 * @return trimmed string
	 */
	public static String windowsTrim(String s) {
		
		return s.trim();		
	}
	
	/**
	 * Validates digit values of any length.<br>
	 * Requests re-submit from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
		
		s = windowsTrim(s);
        
		// repeat until matched
		while (!s.matches("[0-9]+"))
        {
            out.print("Re-enter Number: ");
            s = windowsTrim(scan.next());
        }
		
        return Integer.parseInt(s);
    }
	
	/**
	 * Validates that the number passed in falls within the acceptable range.<br>
	 * Range defined by MIN_DAY and MAX_DAY constants.
	 * @param s day as a string
	 * @return integer value of validated string
	 */
	public static int valDay(String s) {
		
		int i = valNumbers(s);
		
		// repeat until in range
		while (!(i == 0 || (MIN_DAY <= i && i <= MAX_DAY))) {
			out.print("Re-enter day within range (" + MIN_DAY + "-" + MAX_DAY + "): ");
			i = valNumbers(scan.next());
		}
		
		return i;
	}
}
