import static java.lang.System.out;

import java.io.IOException;
import java.util.Scanner;

public class SortedLinkedListClient {

	static final Scanner scan = new Scanner(System.in);
	static final Scanner kb = new Scanner(System.in);
	static final int FFSZ = 40;
	
	/**
	 * Drives sorted linked list implementation.
	 * @param args command line arguments
	 * @throws IOException for viewing associated read me text file
	 */
	public static void main(String[] args) throws IOException {
		
		if (args.length > 0) cliArgs(args);
		else menu();
		
		out.println("Program completed successfully.");
		scan.close();
		kb.close();
	}
	
	// CLI Functionality -----------------------------------------------------------------------------------------------
	/**
	 * Evaluates command line options and takes appropriate action for each.
	 * @param _args options from command line
	 * @throws IOException for viewing associated read me text file
	 */
	public static void cliArgs(String[] _args) throws IOException {
		
		// check for args
		if (_args.length > 0) {
			
			// convert args
			char[] options = _args[0].toCharArray();
			
			// run valid options
			if (options[0] == '-') {
				
				switch (options[1]) {
				
				case 'd' : 
					demo();
					break;
				case 't' : 
					test();
					break;
				case 'h' : 
					printHelp();
					break;
				case 'r' : 
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
	 * Main menu.
	 * @throws IOException for viewing associated read me text file
	 */
	public static void menu() throws IOException {
		
		int menuOption = 0;
		do {
			
			out.println("Sorted Integer Linked List Demo");
			out.println("-------------------------------");
			out.println("1. Interactive Demo");
			out.println("2. Run Unit Tests");
			out.println("3. Print Help");
			out.println("4. Print ReadMe File");
			out.println("0. Exit");
			out.println();
			out.print("Enter Selection: ");
			menuOption = valNumbers(scan.next());
			
			switch(menuOption) {
			
			case 1 :
				demo();
				formFeed();
				break;
			case 2 : 
				test();
				pause();
				formFeed();
				break;
			case 3 : 
				printHelp();
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
				formFeed();
				break;
			}
		} while (menuOption != 0);
	}
	
	/**
	 * Runs demonstration mode.
	 */
	public static void demo() {
		Demo.menu();
	}
	
	/**
	 * Runs test mode.
	 */
	public static void test() {
		
		// tests iterative SortedList methods
		SortedList.unitTest(false);
		// tests recursive SortedList methods
		SortedList.unitTest(true);
	}
	
	/**
	 * Displays help information and command line options.
	 */
	public static void printHelp() {
		
		out.println();
		out.println("Command Line Options:");
		out.println("--------------------------------------------------");
		out.println("Run from the command line with: ");
		out.println("java -jar Asign5Murphy.jar <OPTION>");
		out.println("--------------------------------------------------");
		out.println("OPTIONS:");
		out.println("-d\tRuns an interactive demo, then exits.");
		out.println("-t\tRuns unit tests on a sorted list, then exits.");
		out.println("-h\tHelp.  Prints this list.");
		out.println("-r\tPrints README.txt.");
		out.println();
		out.println("ARGUMENTS:");
		out.println("none\tNo argruments are applicable to this program.");
		out.println();
	}
	
	/**
	 * Prints README.txt file.
	 * @throws IOException for viewing associated read me text file
	 */
	public static void printReadMe() throws IOException {
		
		ReadMe.printReadMe();
	}
	
	/**
	 * Validates digit values of any length.  
	 * Requests re-entry from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
        
		// repeat until matched
		while (!s.matches("[0-4]"))
        {
            out.print("Re-enter Valid Number: ");
            s = scan.next();
        }
		
        return Integer.parseInt(s);
    }
	
	/**
     * Pauses execution and prompts for user to press enter.
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
