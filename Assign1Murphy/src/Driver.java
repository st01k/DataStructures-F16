import static java.lang.System.out;

import java.util.Scanner;

/**
 * Provides main interface and command line options for Mini-Registrar.
 * @author Casey Murphy
 * @version 1.1
 */
public class Driver {
	
	// class constants
	protected static final Scanner scan = new Scanner(System.in);	// protected for use in other classes
	private static final Scanner kb = new Scanner(System.in);		// for pause functionality
	
	// class variables
	private static Course holder = new Course();					// temp holder for active course
	
	public static void main (String[] args) {
		
		if (args.length > 0) {
			cliOptions(args);
			return;
		}
		
		// no command line options entered starts here
		scan.useDelimiter("\n");
		menu();
		printHelp();
		scan.close();
		kb.close();
		out.println();
		out.println("Program exited successfully.");
	}
	
	/**
	 * Checks for, validates, and implements any command line options.
	 * @param _args all arguments passed in from the command line
	 */
	public static void cliOptions(String[] _args) {
		
		if (_args.length > 0) {
			
			// options must be preceded by '-'
			char[] options = _args[0].toCharArray();
			while (options[0] != '-') {
				System.err.println("Invalid options.  Terminating.");
				return;
			}
			
			// check for valid options
			for (int i = 1; i < options.length; i++) {
				if (options[i] == 't') {
					test();
					return;
				}
				
				if (options[i] == 'h') {
					printHelp();
					return;
				}
			}
		}
	}
	
	/**
	 * Test sequence.  Ran from either the menu or command line option '-t'.
	 */
	public static void test() {
		
		formFeed();
		
		// init persons
		Student a = new Student();
		Student b = new Student(111, "Johnny Ray");
		Student c = new Student(222, "Casey Murphy");
		Student d = new Student(333, "Dirk Diggler", 4, 66);
		Student e = new Student(444, "Hermes Trismegistus", 3, 12);
		Student f = new Student(444, "Hermes Trismegistus", 3, 12);
		Instructor y = new Instructor(9999, "Paul Cheng");
		Instructor z = new Instructor(8888, "Gary Thompson", "Computer Science");
		
		// test student functionality
		out.println("Default Student: " + a.getName());
		out.println("Student 111: " + b.getID());
		out.println("Different Students Equal? " + a.equals(b));
		out.println("Same Students Equal? " + e.equals(f));
		c.setCredits(20);
		out.println(c.getName() + "'s credits set to 20: " + c.getCredits());
		d.setTotGP(55);
		out.println(d.getName() + "'s grade points set to 55: " + d.getTotGP());
		out.println(e.getName() + "'s GPA " + e.calcGPA());
		
		// test instructor functionality
		y.setDept("Chemistry");
		out.println(y.getName() + "'s department set to Chemistry: " + y.getDept());
		
		// init courses
		Course cs221 = new Course("CS221", "Data Structures", 4534, 18);
		
		// test course functionality
		out.println();
		out. println("Course: " + cs221.getCourseName() + " created");
		cs221.setInstructor(z);
		out.println("Instructor: " + cs221.getInstructor().toString());
		cs221.addStudent(b);
		cs221.addStudent(c);
		cs221.addStudent(d);
		cs221.addStudent(e);
		cs221.addStudent(f);
		out.println("Total Students: " + cs221.getStudentCount());
		out.println();
		out.println("Roster: ");
		cs221.printRoster();
		out.println();
		if (cs221.removeStudent(d)) {
			out.println("Removed: " + d.toString());
		}
		out.println("Total Students: " + cs221.getStudentCount());

		out.println();
		out.println("Course Details:");
		out.println(cs221.toString());
		
		out.println("Data directory: " + Course.getPath());
		out.println();
		out.print("Test sequence successful.");
		pause();
	}
	
	/**
	 * Menu system.  Loops until user exits.<br>  
	 * Calls user dialog methods.
	 */
	public static void menu() {
		
		int menuOption = -1;
		
		do {
			
			formFeed();
			
			// builds active course readout
			int temp = holder.getRegCode();
			String sTemp = "\t   " + String.valueOf(temp) + 
					"\n\n\t" + holder.getStudentCount() + " Enrolled of " + holder.getNumSlots();
			if (temp == 0) {
				sTemp = "N/A";
			}
			
			// menu
			out.println("\tMini-Registrar");
			out.println("--------------------------------");
			out.println("Current Course: " + sTemp);
			out.println("--------------------------------\n");
			out.println("1.  Create a Course");
			out.println("2.  Modify Course Details");
			out.println("3.  Add Student to Course");
			out.println("4.  Remove Student from Course");
			out.println("5.  Check Student Registration");
			out.println("6.  Print Course Roster");
			out.println("7.  Save Course Details");
			out.println("8.  Open Course from File");
			out.println("9.  Load and Run Test Sequence");
			out.println("0.  Exit Program");
			out.println();
			out.print("Enter Your Selection: ");
			
			menuOption = valNumbers(scan.next());
			switch (menuOption) {
			
			case 1 : 
				createCourseDialog();
				break;
			
			case 2 :
				modifyDialog();
				break;
				
			case 3 :
				addStudentDialog();
				break;
				
			case 4 : 
				removeStudentDialog();
				break;
				
			case 5 :
				checkStudentDialog();
				break;
				
			case 6 :
				formFeed();
				if (!courseActive()) continue;
				holder.printRoster();
				pause();
				break;
				
			case 7 : 
				saveCourse();
				break;
				
			case 8 : 
				loadCourseDialog();
				break;
				
			case 9 :
				test();
				break;
				
			default :
				out.println("Unknown Error");
				break;
			
			}
			formFeed();
			
		} while (menuOption != 0);
	}
	
// Functionality ---------------------------------------------------------------------------------------------------
	/**
	 * Trims white space off of string passed in.<br>
	 * Added for Windows OS compatibility.  Without<br>
	 * this, the new line is captured at the end of <br>
	 * the string in Windows (not Linux).
	 * @param s string to be trimmed
	 * @return trimmed string
	 */
	public static String windowsTrim(String s) {
		
		return s.trim();		
	}
	
	/**
	 * Validates digit values of any length.  <br>
	 * Requests re-submit from user until matched.
	 * @param s string to be checked
	 * @return validated integer value
	 */
	public static int valNumbers(String s) {
		
		s = windowsTrim(s);
        
		while (!s.matches("[0-9]+"))
        {
            out.print("Re-enter Selection: ");
            s = windowsTrim(scan.next());
        }
		
        return Integer.parseInt(s);
    }
	
	/**
	 * Saves active course to file based on course ID.
	 */
	public static void saveCourse() {
	
		formFeed();
		holder.toFile(holder);
		pause();
	}
	
	/**
	 * Prints a form feed to screen.  Scrolls up 50 lines.
	 */
	public static void formFeed() {
		
		for (int i = 0; i < 40; i++) {
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
     * Checks if there is an active course being worked on.
     * @return true if there is, false otherwise
     */
    public static boolean courseActive() {
    	
    	if (holder.getRegCode() != 0) return true;
    	out.println("No active course.  Create or load one first.");
    	pause();
    	return false;
    }
    
    /**
	 * Checks students and instructor for duplicate IDs.
	 * @param i id
	 * @return true if duplicate is found, false otherwise
	 */
	public static boolean dupeID(int i) {
		
		if (holder.searchID(i) || holder.getInstructor().getID() == i) {
			
			out.println("Duplicate ID found.");
			pause();
			return true;
		}
		
		return false;
	}
    
// Dialogs ---------------------------------------------------------------------------------------------------------
    /**
     * Prompts user for necessary new course information.
     */
	public static void createCourseDialog() {
		
		formFeed();
		
		out.print("Enter Course Identifier (ex. CS101): ");
		String _id = windowsTrim(scan.next());
		out.print("Enter Course Name: ");
		String _course = windowsTrim(scan.next());
		out.print("Enter Registration Code: ");
		int _code = valNumbers(scan.next());
		out.print("Enter Slots Available (MX:" + Course.getMAX() + "): ");
		int _maxStu = valNumbers(scan.next());
		out.print("Enter Instructor: ");
		String _inst = windowsTrim(scan.next());
		out.print("Enter Instructor ID: ");
		int _iID = valNumbers(scan.next());
		out.print("Enter Instructor Department: ");
		String _iDept = windowsTrim(scan.next());
		
		holder = new Course(_id, _course, _code, _maxStu);
		
		Instructor iTemp = new Instructor(_iID, _inst, _iDept);
		holder.setInstructor(iTemp);
		
		out.println("\nCreated: " + holder.toString());
		pause();
	}
	
	/**
	 * Prompts user for necessary new student information.
	 */
	public static void addStudentDialog() {
		
		formFeed();
		if (!courseActive()) return;
		
		if (holder.getStudentCount() >= holder.getNumSlots()) {
			out.println("Class is full.");
			pause();
			return;
		}
		
		out.print("Enter Student ID: ");
		int _id = valNumbers(scan.next());
		
		if (dupeID(_id)) {
			return;
		}
		
		out.print("Enter Student Name: ");
		String _name = windowsTrim(scan.next());
		out.print("Enter Total Credits: ");
		int _creds = valNumbers(scan.next());
		out.print("Enter Total Grade Points: ");
		int _totGP = valNumbers(scan.next());
		
		Student temp = new Student(_id, _name, _creds, _totGP);
		holder.addStudent(temp);
		pause();
	}
	
	/**
	 * Prompts user for student ID to remove.
	 */
	public static void removeStudentDialog() {
		
		formFeed();
		if (!courseActive()) return;
		
		out.print("Enter Student ID to remove: ");
		int temp = valNumbers(scan.next());
		Student s = holder.getStudent(temp);
		if (s != null) {
			holder.removeStudent(s);
			out.println("Removed: " + s.toString());
			pause();
			return;
		}
		out.println("Student is not enrolled in this course.");
		pause();
	}
	
	/**
	 * Prompts user for student ID to check registration in a course.
	 */
	public static void checkStudentDialog() {
		
		formFeed();
		if (!courseActive()) return;
		
		out.print("Enter Student ID to check: ");
		int temp = valNumbers(scan.next());
		if (holder.searchID(temp)) {
			out.println("Student is enrolled in this course.");
			pause();
			return;
		}
		
		out.println("Student is not enrolled in this course.");
		pause();
	}  
	
	/**
	 * Prompts user for course number to load.
	 */
	public static void loadCourseDialog() {
		
		formFeed();
		out.print("Enter the Registration Code: ");
		String _code = windowsTrim(scan.next());
		out.println();
		Course c = Course.fromFile(_code);
		if (c != null) {
			holder = c;
		}
		
		pause();		
	}
	
	/**
	 * Prompts user for course/instructor modification details.
	 */
	public static void modifyDialog() {
		
		formFeed();
		if (!courseActive()) return;
		
		// print current course info
		out.println("Current Course Information: ");
		out.println();
		out.println(holder.toString());
		out.println(holder.getInstructor().toString());
		out.println();
		
		// get new course/instructor info
		out.print("Enter New Course Identifier (ex. CS101): ");
		String _id = windowsTrim(scan.next());
		out.print("Enter New Course Name: ");
		String _course = windowsTrim(scan.next());
		out.print("Enter New Registration Code: ");
		int _code = valNumbers(scan.next());
		out.print("Enter New Slots Available (MX:" + Course.getMAX() + "): ");
		int _maxStu = valNumbers(scan.next());
		out.print("Enter New Instructor's Name: ");
		String _inst = windowsTrim(scan.next());
		out.print("Enter New Instructor's ID: ");
		int _iID = valNumbers(scan.next());
		out.print("Enter New Instructor's Department: ");
		String _iDept = windowsTrim(scan.next());
		
		// make changes
		holder.setID(_id);
		holder.setCourseName(_course);
		holder.setRegCode(_code);
		holder.setNumSlots(_maxStu);
		holder.getInstructor().modify(_iID, _inst, _iDept);
		
		out.println("\nModified: " + holder.toString());
		pause();
	}
	
	/**
	 * Displays command line options.
	 */
	public static void printHelp() {
		
		formFeed();
		out.println("Command Line Options:");
		out.println();
		out.println("-t	Runs program in test mode.");
		out.println("	only runs the test sequence,");
		out.println("	then exits.");
		out.println();
		out.println("-h	Help.  Prints this list.");
	}
}
