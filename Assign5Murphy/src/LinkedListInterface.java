/**
 * Basic interface for a sorted linked list.
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface LinkedListInterface {

	// List Information -------------------------------------------------------
	/**
	 * Returns the number of elements in the list.
	 * @return number of list elements
	 */
	int size();
		
	/**
	 * Returns true if this list contains no elements.
	 * @return true if list is empty
	 */
	boolean isEmpty();
		
	// List Manipulation ------------------------------------------------------
	/**
	 * Removes all of the elements from this list.
	 */
	void clear();
	
	// Item Information -------------------------------------------------------
	/**
	 * Returns true if this list contains the specified element.
	 * @param o target element
	 * @return true if target is in this list
	 */
	boolean contains(Object o);
		
	/**
	 * Compares the target object with this list (source object) for equality.
	 * @param o target object
	 * @return true if target is equal to source object (this list)
	 */
	boolean equals(Object o);
	
	// Item Manipulation ------------------------------------------------------
	/**
	 * Inserts the specified element into its proper position within the list.
	 * Returns true if the list was modified.
	 * @param o element to add
	 * @return true if the list was modified
	 */
	void add(Object o);
	
	/**
	 * Removes the first occurrence of the specified element from this list, 
	 * if it is present.  Returns true if the list was modified.
	 * @param o element to remove
	 * @return true if the list was modified
	 */
	void remove(Object o);	
}
