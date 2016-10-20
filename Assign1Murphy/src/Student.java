import java.text.DecimalFormat;

/**
 * Student creation and modification.
 * @author Casey Murphy
 *
 */
public class Student extends Person{
	
	// class constants
	private static final long serialVersionUID = 1L;
	
	// class variables
	private int credits;
	private int totGP;
	
	// Constructors ----------------------------------------------------------------------------------------------------
	/**
	 *	Place holder student.  ID set to 0. 
	 */
	public Student() {
		super();
		credits = 0;
		totGP = 0;
	}
	
	/**
	 * Creates new student.
	 * @param i id
	 * @param n name
	 */
	public Student(int i, String n) {
		super(i, n);
		setCredits(0);
		setTotGP(0);
	}
	
	/**
	 * Creates student with grade points and credits.
	 * @param i id
	 * @param n name
	 * @param c credits
	 * @param g grade points
	 */
	public Student(int i, String n, int c, int g) {
		super(i, n);
		setCredits(c);
		setTotGP(g);
	}
	
	// Accessors ---------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves student's credits.
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Retrieves student's grade points. 
	 * @return grade points
	 */
	public int getTotGP() {
		return totGP;
	}
	
	// Mutators-- --------------------------------------------------------------------------------------------------------
	/**
	 * Sets student's credits.  Only positive numbers allowed.
	 * @param _credits
	 */
	public void setCredits(int _credits) {
		
		if (_credits > 0) {
			credits = _credits;
			return;
		}
		
		credits = 0;
	}
	
	/**
	 * Sets student's total grade points.  Only positive numbers allowed.
	 * @param _totGP
	 */
	public void setTotGP(int _totGP) {
		
		if (_totGP > 0) {
			totGP = _totGP;
			return;
		}
		
		totGP = 0;
	}
	
	// Overrides ---------------------------------------------------------------------------------------------------------
	/**
	 * Student details to string.
	 */
	public String toString() {
		
		return getID() + " - " + getName() + " | GP: " + getTotGP() + " | Credits: " + getCredits();
	}
	
	// Calculations ------------------------------------------------------------------------------------------------------
	/**
	 * Calculates and returns student's GPA.  Protects against division by 0.
	 * @return .XX precision GPA
	 */
	public String calcGPA() {
		
		DecimalFormat df = new DecimalFormat(".00");
		if (credits > 0) {
			float i = (float) totGP / credits; 
			return df.format(i);
		}
		
		return "-1";
	}
	
	// Functionality -----------------------------------------------------------------------------------------------------
	/**
	 * Modifies all student attributes.
	 * @param n name
	 * @param i id
	 * @param g total grade points
	 * @param c credits
	 */
	public void modify(String n, int i, int g, int c) {
		
		super.setName(n);
		super.setID(i);
		totGP = g;
		credits = c;
	}
	
}
