

import static java.lang.System.out;

import java.util.Scanner;

/**
 * Client for War card game.
 * @author Casey J. Murphy
 * @version 0.9
 */
public class WarClient {
	
	private static final Scanner scan = new Scanner(System.in);
	private static final Scanner kb = new Scanner(System.in);
	private static final int FFSZ = 10;
	
	/**
	 * Main, takes command line options.
	 * @param args command line options
	 */
	public static void main (String[] args) {
		
		
		if (args.length > 0) {
			
			cliArgs(args);
			scan.close();
			kb.close();
			return;
		}
		
		// added 2 Oct 16 to add demo mode option without command line arguments
		out.println("Welcome to WAR");
		out.print("Enter 1 for demo mode or 2 for the full game: ");
		int option = valNumbers(scan.next());
		if (option == 1) demo();
		else if (option == 2) fullGame();
		else out.println("Invalid selection.  Terminating...");
		out.println();
		
		scan.close();
		kb.close();
	}
	
	// CLI Functionality -----------------------------------------------------------------------------------------------
	/**
	 * Evaluates command line options and takes appropriate action for each.
	 * @param _args options from command line
	 */
	public static void cliArgs(String[] _args) {
		
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
				default :
					out.println("Invalid options");
					break;
				}
			}
		}
	}
	
	/**
	 * Initiates game in turn-by-turn interactive mode.
	 */
	public static void fullGame() {
		
		out.println();
		out.println("WARNING: This game can be VERY long.");
		out.println("To run a faster interactive verion, use demo");
		out.println("mode... or just hold down the enter key.");
		out.println();
		out.print("Enter name for Player 1 (no spaces): ");
		String n1 = scan.next();
		String n2 = "Computer";
		War game = new War(n1, n2);
		game.deal();
		
		while (!game.winCheck()) {
			game.round(false);
			pause();
		}
		
		out.println("Game Complete");
		printResults();
	}
	
	/**
	 * Initiates demo.  Partially interactive.
	 */
	public static void demo() {
		
		War game = new War();
		game.deal();
		int rounds = 0;
		
		do {
			out.println();
			out.print("Enter number of rounds to auto-play (0 to quit): ");
			rounds = valNumbers(scan.next());
			
			for (int i = 0; i < rounds; i++) {
			
				if (game.winCheck()) {
					rounds = 0;
					break;
				}
				game.round(true);
			}
		} while (rounds != 0);
		
		out.println("Demo Complete");
		printResults();
	}
	
	/**
	 * Initiates tester.
	 */
	public static void test() {
		
		Tester.test();
	}
	
	/**
	 * Prints a form feed to screen.  Scrolls up 50 lines.
	 */
	public static void formFeed() {
		
		for (int i = 0; i < FFSZ; i++) {
			out.println();
		}
	}
	
	/**
     * Pauses execution and prompts for user to hit enter.
     */
    public static void pause()
    {
     
        out.println();
    	out.print("Press Enter to Continue...");
        kb.nextLine();
        formFeed();
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
		out.println("java -jar Asign4Murphy.jar <options>");
		out.println("java -jar Asign4Murphy.jar <args>");
		out.println();
		out.println("*NIX - Run from the command line with either: ");
		out.println("./Asign4Murphy.jar <options>");
		out.println("./Asign4Murphy.jar <args>");
		out.println("--------------------------------------------------");
		out.println("OPTIONS:");
		out.println("-d\tRuns program as an interactive demo, then exits.");
		out.println("-t\tRuns program in test mode, then exits.");
		out.println("-h\tHelp.  Prints this list.");
		out.println();
		out.println("ARGUMENTS:");
		out.println("none\tNo argruments are applicable to this game.");
		out.println();
	}
	
	/**
	 * Validates digit values of any length.<br>
	 * Requests re-entry from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
        
		// repeat until matched
		while (!s.matches("[0-9]+"))
        {
            out.print("Re-enter Valid Number: ");
            s = scan.next();
        }
		
        return Integer.parseInt(s);
    }
	
	public static void printResults() {
		
		out.println();
		out.println("Rounds Played: " + War.getNumRounds());
		out.println("Wars Had: " + War.getNumWars());
	}
}
