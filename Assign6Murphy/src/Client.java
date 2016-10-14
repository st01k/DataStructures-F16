import static java.lang.System.out;

import java.io.IOException;
import java.util.Scanner;

/**
 * Menu driven client for the Prefix class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Client {

	// constants
	static final Scanner scan = new Scanner(System.in);
	static final Scanner kb = new Scanner(System.in);
	static final int FFSZ = 40;
	
	/**
	 * Main - drives menu and cli interfaces.
	 * CLI options listed in README.txt
	 * @param args command line input
	 * @throws IOException for README.txt print
	 */
	public static void main(String[] args) throws IOException {
		
		if (args.length > 0) cliArgs(args);
		else menu();
		
		kb.close();
		scan.close();
		out.println("Program completed successfully.");
	}
	
	// Menu ------------------------------------------------------------------------------------------------------------
	/**
	 * Looped main menu.
	 * @throws IOException for README.txt print 
	 */
	public static void menu() throws IOException {
		
		formFeed();
		int menuOption = 0;
		do {
			
			out.println("\tValidate & Evaluate Prefix Notation");
			out.println("------------------------------------------------------");
			out.println("1. Verify & Evaluate a Prefix Expression");
			out.println("2. Verify Prefix Form");
			out.println("3. Test Prefix Class");
			out.println("4. Print README file");
			out.println("0. Exit");
			out.println();
			out.print("Enter Selection: ");
			menuOption = valNumbers(scan.next());
			out.println();
			
			switch(menuOption) {
			
			case 1 :
				valueDialog();
				pause();
				formFeed();
				break;
				
			case 2 : 
				formDialog();
				pause();
				formFeed();
				break;
				
			case 3 : 
				Prefix.unitTest();
				pause();
				formFeed();
				break;
				
			case 4 :
				printReadMe();
				pause();
				formFeed();
				break;
				
			case 0 :
				break;
				
			default :
				out.println("Invalid Selection");
				pause();
				formFeed();
				break;
			}
		} while (menuOption != 0);
	}
	
	/**
	 * Evaluates command line options and takes appropriate action for each.
	 * @param _args options from command line
	 * @throws IOException for README.txt print
	 */
	public static void cliArgs(String[] _args) throws IOException {
		
		// check for args
		if (_args.length > 0) {
			
			// convert args
			char[] options = _args[0].toCharArray();
			
			// run valid options
			if (options[0] == '-') {
				
				switch (options[1]) {
				
				case 't' : 
					Prefix.unitTest();
					break;
					
				case 'h' : 
					printReadMe();
					break;
					
				default :
					out.println("Invalid option");
					break;
				}
			}
		}
	}
	
	/**
	 * Prints README.txt file.
	 * @throws IOException for viewing associated read me text file
	 */
	public static void printReadMe() throws IOException {
		
		ReadMe.printReadMe();
	}
		
	// User Dialogs ----------------------------------------------------------------------------------------------------
	/**
	 * User dialog for entering a valued equation to solve.
	 */
	public static void valueDialog() {
		
		if (windows()) scan.useDelimiter("\r");
		else scan.useDelimiter("\n");
		
		out.println("Enter expression with spaces before and after");
		out.println("all numbers.  Use only whole numbers.");
		out.println("Operators Available: + - * / % ^");
		out.println("EXAMPLE: ++ 1 2 / 3 4");
		out.println();
		out.print("Enter Equation: ");
		String s = scan.next();
		if (windows()) s = s.trim();
		Prefix temp = new Prefix(s, true);
		
		out.println();
		out.println("Input: " + temp.getInput());
		out.println("Prefix Form: " + temp.getStrExp());
		
		if (temp.getSolvable()) {
			
			out.println("Solution: " + temp.getSolution());
		}
		else {
		
			out.println("Invalid Prefix Notation");
		}
	}
	
	/**
	 * User dialog for entering a prefix form to validate.
	 */
	public static void formDialog() {
		
		out.println("Enter expression form with neither spaces nor");
		out.println("numbers.  Use only one-letter identifiers.");
		out.println("Operators Available: + - * / % ^");
		out.println("EXAMPLE FORM: +/ab*cd");
		out.println();
		out.print("Enter Equation: ");
		String s = scan.next();
		if (windows()) s = s.trim();
		Prefix temp = new Prefix(s, false);
		
		out.println();
		out.println("Input: " + temp.getInput());
		
		if (temp.isPrefix()) {
			
			out.println("Valid Prefix Form");
		}
		else {
		
			out.println("Invalid Prefix Notation");
		}
	}
	
	// Validation ------------------------------------------------------------------------------------------------------
	/**
	 * Validates digit values of any length.
	 * Requests re-entry from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
        
		if (windows()) s = s.trim();
		
		// repeat until matched
		while (!s.matches("[0-4]"))
        {
            out.print("Re-enter Valid Number: ");
            s = scan.next();
            if (windows()) s = s.trim();
        }
		
        return Integer.parseInt(s);
    }
	
	/**
	 * Returns true if program is being run on any Windows OS.
	 * @return true if OS is Windows
	 */
	public static boolean windows() {
		
		if (System.getProperty("os.name").contains("Windows")) return true;
		return false;
	}
	
	// Interface Accessories -------------------------------------------------------------------------------------------
	/**
     * Pauses execution and prompts for user to hit enter.
     */
    public static void pause()
    {
     
        out.println();
    	out.print("Press Enter to Continue...");
    	kb.nextLine();
    }
	
	/**
	 * Prints a form feed to screen.  Scrolls up specified lines.
	 * Amount is specified with FFSZ constant.
	 */
	public static void formFeed() {
		
		for (int i = 0; i < FFSZ; i++) {
			out.println();
		}
	}
}
