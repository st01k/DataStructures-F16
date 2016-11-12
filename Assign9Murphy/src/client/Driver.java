package client;

import static java.lang.System.out;

import java.util.Scanner;
import queue.QueueRefBased;
import iterator.QueueIterator;

public class Driver {

	// constants
	private static final Scanner scan = new Scanner(System.in);
	
	// class variables
	private static String prompt;
	
	public static void main(String[] args) {

		prompt();
		scan.close();
		out.println("\n~ In Lak'ech Ala K'in ~\n");
	}
		/**
		 * Main prompt.
		 */
		static void prompt() {
			
			out.println("<<<< Generic Queue Implementation with Iterator >>>>");
			out.println("\t\tTest Suite v1.0");
			out.println("-------------------------------------------------");
			out.println("Type 'help' any time for a list of commands.");
			out.println();
			
			boolean cont = true;
			do {

				prompt = "main> ";
				out.print(prompt);
				String in = scan.nextLine();
				
				switch(in) {
				
				case ("exit") 	: cont = false;
					break;
				case ("help") 	: printHelp();
					break;
				case ("test") 	: test();
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
		private static void printHelp() {
			
			out.println();
			out.println("Available Commands: ");
			out.println("----------------------------------------------");
			out.println("help\tPrints this menu");
			out.println("test\tRuns unit tests");
			out.println("cls\tClear screen");
			out.println("exit\tExits current prompt");
			out.println();
		}
		
		/**
		 * Runs all associated class unit tests.
		 */
		public static void test() {
			
			out.println("\nInitiating class tests...\n");
			QueueRefBased.unitTest();
			QueueIterator.unitTest();
			out.println("Class testing complete.\n");
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