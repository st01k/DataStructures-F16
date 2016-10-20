import static java.lang.System.out;

/**
 * Drives demonstration mode for program client.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Demo {
	
	private static final String NOLISTMSG = "No List Loaded";
	
	/**
	 * Menu for demo mode.
	 */
	public static void menu() {
		
		formFeed();
		SortedList temp = null;
		int menuOption = 0;
		do {
			String ms = genModeString(temp);
			if (listLoaded(temp) && !temp.isRecursive()) {
				out.println("WARNING: ITERATIVE MODE IN BETA");
				out.println("NOT SORTING CORRECTLY");
				out.println();
			}
			out.println("DEMO - " + ms);
			out.println("----------------------------------");
			out.println("1. Create List");
			out.println("2. Add Item");
			out.println("3. Remove Item");
			out.println("4. Print List");
			out.println("5. Search List");
			out.println("6. Clear List");
			out.println("7. Switch Modes");
			out.println("0. Exit");
			out.println();
			out.print("Enter Selection: ");
			menuOption = 
					valNumbers(SortedLinkedListClient.scan.next());
			
			switch(menuOption) {
			
			case 1 :
				temp = new SortedList();
				out.println("List Created");
				pause();
				formFeed();
				break;
				
			case 2 : 
				if (listLoaded(temp)) {
					out.print("Enter a whole number to add: ");
					Integer addItem = 
							valNumbers(SortedLinkedListClient.scan.next());
					temp.add(addItem);
				}
				else out.println(NOLISTMSG);
				
				pause();
				formFeed();
				break;
				
			case 3 : 
				if (listLoaded(temp)) {
					out.print("Enter a whole number to remove: ");
					Integer removeItem = 
							valNumbers(SortedLinkedListClient.scan.next());
					temp.remove(removeItem);
				}
				else out.println(NOLISTMSG);
				
				pause();
				formFeed();
				break;
				
			case 4 : 
				if (listLoaded(temp)) temp.printList();
				else out.println(NOLISTMSG);
				pause();
				formFeed();
				break;
				
			case 5 :
				if (listLoaded(temp)) {
					out.print("Enter a whole number to search for: ");
					Integer searchItem = 
							valNumbers(SortedLinkedListClient.scan.next());
					if (temp.contains(searchItem)) out.println("Found");
					else out.println("Not Found");
				}
				else out.println(NOLISTMSG);
				
				pause();
				formFeed();
				break;
				
			case 6 :
				if (listLoaded(temp)) temp.clear();
				pause();
				formFeed();
				break;
				
			case 7 :
				if (listLoaded(temp))temp.toggleMode();
				else out.println(NOLISTMSG);
				// TODO clear list - remove when iterative fixed
				temp.clear();
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
	 * Generates dynamic mode string depending on the list mode.
	 * Used as a live status in demo menu.
	 * @param sl list
	 * @return string stating list mode
	 */
	private static String genModeString(SortedList sl) {
		
		if (sl == null) return NOLISTMSG;
		String s;
		if (sl.isRecursive()) s = "Recursive Mode";
		else s = "Iterative Mode";
		return s + " - Size: " + sl.size();
	}
	
	/**
	 * Queries if a list is loaded in the demo menu.
	 * @param sl list
	 * @return true if a list is loaded; false otherwise
	 */
	private static boolean listLoaded(SortedList sl) {
		
		if (sl != null) return true;
		return false;
	}
	
	/**
	 * Validates digit values of any length.
	 * Requests re-entry from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
        
		// repeat until matched
		while (!s.matches("^-?[0-9]+"))
        {
            out.print("Re-enter Valid Number: ");
            s = SortedLinkedListClient.scan.next();
        }
		
        return Integer.parseInt(s);
    }
	
	/**
     * Pauses execution and prompts for user to hit enter.
     */
    public static void pause()
    {
     
        out.println();
    	out.print("Press Enter to Continue...");
    	SortedLinkedListClient.kb.nextLine();
    }
	
	/**
	 * Prints a form feed to screen.  Scrolls up specified lines.
	 * Amount is specified with FFSZ constant.
	 */
	public static void formFeed() {
		
		for (int i = 0; i < SortedLinkedListClient.FFSZ; i++) {
			out.println();
		}
	}
}
