/**
 * Instructor creation and modification.
 * @author Casey Murphy
 *
 */
public class Instructor extends Person {
	
	// class constants
	private static final long serialVersionUID = 1L;

	// class variables
	private static int count = 0;
	
	private String dept;
	
	// Constructors ----------------------------------------------------------------------------------------------------
	/**
	 * Place holder instructor.  ID set to 0.
	 */
	public Instructor() {
		super();
		setDept("Place Holder Department");
		count++;
	}
	
	/**
	 * Creates specified instructor with no department.
	 * @param i id
	 * @param n name
	 */
	public Instructor(int i, String n) {
		
		super(i, n);
		setDept("Place Holder Department");
		count++;
	}
	
	/**
	 * Creates specified instructor with department.
	 * @param i id
	 * @param n name
	 * @param d department
	 */
	public Instructor(int i, String n, String d) {
		super(i, n);
		setDept(d);
		count++;
	}
	
	// Accessors -------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves number of instructors in current session.
	 * @return instructor count
	 */
	public static int getICount() {
		
		return count;
	}
	
	/**
	 * Retrieves instructor's department.
	 * @return department
	 */
	public String getDept() {
		return dept;
	}
	
	// Mutators --------------------------------------------------------------------------------------------------------
	/**
	 * Sets instructor's department.
	 * @param _dept
	 */
	public void setDept(String _dept) {
		dept = _dept;
	}
	
	// Overrides -------------------------------------------------------------------------------------------------------
	/**
	 * Instructor details to string.
	 */
	public String toString() {
		
		return getID() + " - " + getName() + " | " + getDept();
	}
	
	// Business --------------------------------------------------------------------------------------------------------
	/**
	 * Modifies all instructor attributes.
	 * @param n new name
	 * @param d new department
	 * @param i new id
	 */
	public void modify(int i, String n, String d) {
		
		super.setName(n);
		super.setID(i);
		dept = d;
	}
}
