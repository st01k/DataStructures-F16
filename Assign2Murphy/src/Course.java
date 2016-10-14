import java.io.File;
import java.util.Arrays;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;

import static java.lang.System.out;

/**
 * Course creation, modification, saving and retrieving.
 * @author Casey Murphy
 * 
 */
public class Course implements Serializable {
	
	// class constants
	private static final long serialVersionUID = 1L;
	private static final int MAX_SLOTS = 35;
	private static final int DEFAULT_REGCODE = 0;
	private static final String DEFAULT_ID = "0";
	private static final String SEP = System.getProperty("file.separator");
	private static final String PATH = buildPath();
	private static final String DEFAULT_COURSE_NAME = "Place Holder Course Name";
	
	// class variables
	private static int count = 0;
	
	private int regCode;
	private int numSlots;
	private String id;
	private String courseName;
	private Instructor instructor;
	private Student[] students;
	
	// Constructors ----------------------------------------------------------------------------------------------------
	/**
	 * Place Holder.  Course details are place holders, <br>
	 * students array populated with place holders.
	 */
	public Course() {
		
		setID(DEFAULT_ID);
		setRegCode(DEFAULT_REGCODE);
		setNumSlots(MAX_SLOTS);
		setCourseName(DEFAULT_COURSE_NAME);
		students = new Student[numSlots];
		popStudents();
		count++;
	}
	
	/**
	 * Creates specified course.  Respects MAXSLOTS constraints.<br>
	 * Students array populated with place holders.
	 * @param id Course Designation
	 * @param name Course Name
	 * @param code Course Registration Code
	 * @param max Maximum slots for Course
	 */
	public Course(String id, String name, int code, int max) {
		
		setID(id);
		setRegCode(code);
		setNumSlots(max);
		setCourseName(name);
		students = new Student[numSlots];
		popStudents();
		count++;
	}
	
	// Accessors -------------------------------------------------------------------------------------------------------
	/**
	 * Returns path to the user's working directory.
	 * @return user's working directory path
	 */
	public static String getPath() {
		
		return PATH;
	}
	
	/**
	 * Returns the OS specific file separator used.
	 * @return separator character as string
	 */
	public static String getSep() {
		
		return SEP;
	}
	
	/**
	 * Retrieves number of courses created in current session.
	 * @return number of instantiated courses
	 */
	public static int getCount() {
		
		return count;
	}

	/**
	 * Returns the maximum allowance for students in any course.
	 * @return max slots for any course
	 */
	public static int getMAX () {
		
		return MAX_SLOTS;
	}
	
	/**
	 * Returns the course designation ID (ex. CS101).
	 * @return course designation
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Returns the course registration code (ex. 1234).
	 * @return course registration code
	 */
	public int getRegCode() {
		
		return regCode;
	}
	
	/**
	 * Returns number of slots for the course.
	 * @return course slots total
	 */
	public int getNumSlots() {
		
		return numSlots;
	}
	
	/**
	 * Returns instructor object for course.
	 * @return course instructor object
	 */
	public Instructor getInstructor() {
		
		return instructor;
	}
	
	/**
	 * Returns name of course.
	 * @return course name
	 */
	public String getCourseName() {
		
		return courseName;
	}
	
	/**
	 * Returns a student in the course.
	 * @param _id id of student
	 * @return student object
	 */
	public Student getStudent(int _id) {
		
		for (int i = 0; i < students.length; i++) {
			if (_id == students[i].getID()) {
				return students[i];
			}
		}
		return null;
	}
	
	/**
	 * Returns all of the students in the course.
	 * @return array of student objects
	 */
	public Student[] getStudents() {
		
		return students;
	}
	
	/**
	 * Returns a count of all non-place holder students in the course.  <br>
	 * Those being students with an id other than 0.
	 * @return number of actual students in course
	 */
	public int getStudentCount() {
		
		int cnt = 0;
		
		for (Student s : students) {
			if (s.getID() != 0) {
				cnt++;
			}
		}
		
		return cnt;
	}
	
	// Mutators --------------------------------------------------------------------------------------------------------
	/**
	 * Sets the course registration code (ex. 1234).
	 * @param _regCode new registration code
	 */
	public void setRegCode(int _regCode) {
		
		regCode = _regCode;
	}
	
	/**
	 * Sets course designation id (ex. CS101).
	 * @param _id course designation
	 */
	public void setID(String _id) {
		
		id = _id;
	}
	
	/**
	 * Sets number of available slots in course.<br>
	 * Defaults to MAXSLOTS if input not in range.
	 * @param _numSlots course slots available
	 */
	public void setNumSlots(int _numSlots) {
		
		if (_numSlots < 0 || _numSlots > MAX_SLOTS) {
			_numSlots = MAX_SLOTS;
		}
		numSlots = _numSlots;
	}
	
	/**
	 * Sets course instructor.
	 * @param _instructor instructor object
	 */
	public void setInstructor(Instructor _instructor) {
		
		instructor = _instructor;
	}
	
	/**
	 * Sets course name.
	 * @param _courseName course name
	 */
	public void setCourseName(String _courseName) {
		
		courseName = _courseName;
	}
	
	// Overrides -------------------------------------------------------------------------------------------------------
	/**
	 * Course information to string.
	 */
	public String toString() {
		return id + "(" + regCode + ") - " + courseName + " | " + instructor.getName() + " | " + getStudentCount() + "/" + numSlots;
	}
	
	// Business --------------------------------------------------------------------------------------------------------
	/**
	 * Populates constructed student array with placeholder students.
	 */
	public void popStudents() {
		
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
		}
	}
	
	/**
	 * Searches students for a specific student ID.
	 * @param id student id
	 * @return true if student is found, false otherwise
	 */
	public boolean searchID(int id) {
		
		for (int i = 0; i < students.length; i++) {
			if (id == students[i].getID()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a student to the course.
	 * @param s student to add
	 * @return true if successful, false otherwise
	 */
	public boolean addStudent(Student s) {
		
		try {
			
			// if class is full
			if (getStudentCount() >= MAX_SLOTS) throw new StudentException();
			
			// while class is not full
			while (getStudentCount() < numSlots) {
				
				// unnecessary but left for future development
				if (searchID(s.getID())) break;
				
				// creates student in first available place holder
				for (int i = 0; i < students.length; i++) {
					if (students[i].getID() == 0) {
						students[i] = new Student(s.getID(), s.getName(), s.getCredits(), s.getTotGP());
						break;
					}
				}
				
				out.println("Added: " + s.toString());
				return true;
			}
			
			out.println("Duplicate student ID found.  Student not added.");
			return false;
		}
		
		catch (StudentException e) {
			out.println("Student not added.  Class is full.");
			return false;
		}
	}
	
	/**
	 * Removes a student from the course.
	 * @param s student to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeStudent(Student s) {
		
		try {
			
			while (students.length > 0 ) {
				
				if (!searchID(s.getID())) throw new StudentException();
				
				// copy students array to temp array
				Student[] temp = new Student[students.length];
				for (int i = 0; i < temp.length;  i++) {	
									
					Student t = students[i];
					
					// replaces deleted student with place holder
					if (t.getID() == 0 || t.getID() == s.getID()) {
						temp[i] = new Student();	
					}
					else {
						temp[i] = t;
					}			
				}
				
				// copies temp array back to students
				for (int i = 0; i < students.length; i++) {
					students[i] = temp[i];
				}
				
				return true;
			}
		}
		
		catch (StudentException e) {
			
			out.println("Student not found.");
			return false;
		}
		
		return false;
	}
	
	/**
	 * Prints current course's roster, includes course and instructor info.
	 */
	public void printRoster() {
		
		out.println();
		out.println("Course: " + getRegCode() + " - " + getCourseName());
		out.println("Instructor: " + getInstructor().getName());
		out.println("---------------------------------------------------");
		
		int[] temp = new int[students.length];
		int i = 0;
		
		for (Student s : students) {
			
			if (s.getID() != 0) {
				temp[++i] = s.getID();
			}
		}
		
		Arrays.sort(temp);
		
		for (int j : temp) {
			
			if (getStudent(j).getID() == 0) continue;
			out.println(getStudent(j).toString() + " | GPA: " + getStudent(j).calcGPA());
		}
		
		out.println("\nList Complete");
	}
	
	// File Operations -------------------------------------------------------------------------------------------------
	
	/**
	 * Builds path to user's working directory.
	 * @return path
	 */
	public static String buildPath() {
		
		String p = System.getProperty("user.dir"); 
		p += getSep() + "registrarData";
		return p;
	}
	
	/**
	 * Provides overwrite verification if course file already exists.
	 * @param fileName file name as course registration code
	 * @return true to continue overwrite operation, false if not
	 */
	public static boolean overwrite(String fileName) {
		
		File f = new File(PATH + getSep() + fileName);
		if (f.exists() && !f.isDirectory()) { 
			
			out.print("File already exists!  Overwrite (Y/N)? ");
			String s = Driver.scan.next();
			s = s.trim();	// for windows
			
			while (!s.matches("[yYnN]")) {
					out.print("Re-enter Answer: ");
					s = Driver.scan.next();
					s = s.trim();	// for windows
			}
			
			if (s.matches("[nN]")) return false;
			return true;
		}
		
		return true;
	}
	
	/**
	 * Saves a serialized course object to file.  Filename matches the course <br>
	 * number.  Checks that course is not empty, and if it already exists.
	 * @param c course being saved
	 */
	public void toFile(Course c) {
		
		
		if (c.getRegCode() == 0) {
			out.println("Warning: Cannot save empty course.");
			return;
		}
		
		boolean flag = false;	// for finally print
		
		String fname = Integer.toString(getRegCode());
		fname = fname.trim();	// for windows
		
		if (!overwrite(fname)) {
			
			out.println("File not saved.");
			return;
		}
		
		try {
			
			// make directory if it does not exist
			new File(PATH).mkdirs();
			
			// write object to file
			FileOutputStream fos = new FileOutputStream (PATH + SEP + fname, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		}
		
		catch (FileNotFoundException fnfe) {
			out.println("Unable to write " + fname);
			flag = true;
		}
		
		catch (IOException ioe) {
			ioe.printStackTrace();
			flag = true;
		}
		
		finally {
			
			if (flag) {
				out.println(c.getRegCode() + " not saved.");
				return;
			}
			
			out.println(c.getRegCode() + " saved.");
		}
	}
	
	/**
	 * Retrieves the de-serialized course object from a file.
	 * @param _regCode course registration code
	 * @return course object
	 */
	public static Course fromFile(String _regCode) {
		
		try {
			
			FileInputStream fis = new FileInputStream(PATH + SEP + _regCode);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try {
				Course temp = (Course) ois.readObject();
				return temp;
			}
			
			catch (EOFException eofe) {
				out.println("\nEnd of file reached.");
				return null;
			}
			
			catch (ClassNotFoundException cnfe) {
				out.println(cnfe.getMessage());
				return null;
			}
			
			finally {
				out.println("Course " + _regCode + " Loaded");
				ois.close();
			}
		}
		
		catch (FileNotFoundException fnfe) {
			out.println("Unknown course registration.");
			return null;
		}
		
		catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
}
