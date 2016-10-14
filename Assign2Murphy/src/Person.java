import java.io.Serializable;

/**
 * Person creation and modification.
 * @author Casey Murphy
 *
 */
public class Person implements Serializable {
	
	// class constants
	private static final int DEFAULT_ID = 0;
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_NAME = "Place Holder Name";

	// class variables
	private static int count = 0;
	
	private int id;
	private String name;
	
	// Constructors ----------------------------------------------------------------------------------------------------
	/**
	 * Creates place holder person.  ID set to 0.
	 */
	public Person() {
		
		setID(DEFAULT_ID);
		setName(DEFAULT_NAME);
		count++;
	}
	
	/**
	 * Creates specified person.
	 * @param i id
	 * @param n name
	 */
	public Person(int i, String n) {
		
		setID(i);
		setName(n);
		count++;
	}
	
	// Accessors -------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves a count of persons created in current session.
	 * @return person count
	 */
	public static int getCount() {
		
		return count;
	}
	
	/**
	 * Retrieves id of person.
	 * @return id
	 */
	public int getID() {
		
		return id;
	}
	
	/**
	 * Retrieves name of person.
	 * @return name
	 */
	public String getName() {
		
		return name;
	}
	
	// Mutators --------------------------------------------------------------------------------------------------------
	/**
	 * Sets id of person.
	 * @param _id
	 */
	public void setID(int _id) {
		
		id = _id;
	}
	
	/**
	 * Sets person name.
	 * @param _name
	 */
	public void setName(String _name) {
		
		name = _name;
	}
	
	// Overrides ---------------------------------------------------------------------------------------------------------
	/**
	 * Checks equality of a person object.
	 */
	public boolean equals(Object o) {
		
		if (!(o instanceof Person)) {
            return false;
        }
        else
        {
            Person s = (Person) o;
            if (getID() == s.getID())
            {
                return true;
            }
            return false;
        }
	}
	
	/**
	 * Person details to string.
	 */
	public String toString() {
		
		return getID() + " | " + getName();
	}
}
