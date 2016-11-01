package client;

import static java.lang.System.out;

import java.util.Scanner;

import queue.QueueListBased;
import queue.QueueRefBased;

public class Driver {

	// constants
	private static final Scanner scan = new Scanner(System.in);
	private static final String dbgStr = "[debug]";
	private static final int FFSZ = 50;	// form feed size
	
	// class variables
	private static boolean debug = false;
	private static String prompt;
	
	public static void main(String[] args) {

		prompt();
		scan.close();
	}
		/**
		 * Main prompt.
		 */
		static void prompt() {
			
			out.println("<<<< Queue Implementation Test Suite v1.0 >>>>");
			out.println("Type 'help' any time.");
			out.println();
			
			boolean cont = true;
			do {

				prompt = "main> ";
				if (debug) prompt = dbgStr + "main> ";
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
			
			QueueRefBased.unitTest();
			QueueListBased.unitTest();
		}

		/**
		 * Prints a form feed to screen.  Scrolls up specified lines.
		 * Amount is specified with FFSZ constant.
		 */
		private static void formFeed() {
			for (int i = 0; i < FFSZ; i++) out.println();
		}
	}