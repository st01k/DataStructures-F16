import static java.lang.System.out;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Tests two quicksort methods.
 * One pivots on the first element of the array being sorted.
 * The other pivots on the middle element of the array being sorted.
 * @author Casey Murphy
 * @version 1.0
 */
public class Driver {

	private static final Scanner scan = new Scanner(System.in);
	private static String prompt;
	
	public static void main(String[] args) {
		
		prompt();
		scan.close();
		out.println("\n~ Program complete ~\n");
	}
	
	/**
	 * Main prompt.
	 */
	static void prompt() {
		
		out.println("<<<< Quick Sort Analyzer v1.0 >>>>");
		out.println("----------------------------------");
		out.println("Type 'help' for a list of commands.");
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
				
			case ("test")	:
				
				out.println("\nEnter the array size to test.");
				prompt = "main.test> ";
				out.print(prompt);
				String test = scan.nextLine();
				if (test.matches("^-?[0-9]+")) test(Integer.parseInt(test));
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
		out.println("------------------------------");
		out.println("help\tPrints this menu");
		out.println("test\tRuns analysis");
		out.println("cls\tClear screen");
		out.println("exit\tExits current prompt");
		out.println();
	}
	
	/**
	 * Generates an Integer array of requested size
	 * with the worst case scenario.  Data is in
	 * descending order.
	 * @param size size of array
	 * @return generated array
	 */
	private static Integer[] genWorst(int size) {
		
		Integer[] temp = new Integer[size];
		
		for (int i = 0; i < size; i++) {
			temp[i] = (Integer) ((int) size - i);
		}
		
		return temp;
	}
	
	/**
	 * Generates an Integer array of requested size
	 * with randomly generated numbers.
	 * @param size size of array
	 * @return generated array
	 */
	private static Integer[] genRand(int size) {
		
		Integer[] temp = new Integer[size];
		
		int min = -10;
		int max = 100;
		
		for (int i = 0; i < size; i++) {
			temp[i] = (Integer) ((int) (Math.random() * max) + min);
		}
		
		return temp;
	}
	
	/**
	 * Tests randomly generated values and worst case values.
	 * @param size size of test array
	 */
	private static void test(int size) {
		
		Integer[] testSeqA = genRand(size);
		Integer[] testSeqB = testSeqA.clone();
		
		Integer[] testSeqC = genWorst(size);
		Integer[] testSeqD = testSeqC.clone();
				
		int cntA, cntB, cntC, cntD = 0;		
		
		out.println();
		out.println("Unsorted Random, size: " + size);
		out.println(Arrays.toString(testSeqA));
		
		cntA = QuickSort.sortA(testSeqA, 0, testSeqA.length - 1);
		cntB = QuickSort.sortB(testSeqB, 0, testSeqB.length - 1);
		
		out.println("\nSorted Random---------");
		out.println("First Pivot: \n" + Arrays.toString(testSeqA) + "\ncomparison count: " + cntA);
		out.println("Middle Pivot: \n" + Arrays.toString(testSeqB) + "\ncomparison count: " + cntB);
		
		out.println("-------------------------------------------------------");
		out.println("Unsorted Worst-case, size: " + size); 
		out.println(Arrays.toString(testSeqC));
		
		cntC = QuickSort.sortA(testSeqC, 0, testSeqC.length - 1);
		cntD = QuickSort.sortB(testSeqD, 0, testSeqD.length - 1);
		
		out.println("\nSorted Worst-case---------");
		out.println("First Pivot: \n" + Arrays.toString(testSeqC) + "\ncomparison count: " + cntC);
		out.println("Middle Pivot: \n" + Arrays.toString(testSeqD) + "\ncomparison count: " + cntD);
		
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
