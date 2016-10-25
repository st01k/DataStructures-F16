package stack;

/**
 * Interface for a stack.
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface StackInterface<E> {

	/**
	 * Returns true if stack is empty.
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Clears stack.
	 */
	void popAll();
	
	/**
	 * Adds object to top of stack.
	 * @param newItem object to add
	 */
	void push(E newItem);
	
	/**
	 * Removes top object from stack.
	 * @throws StackException if stack is empty
	 * @return object that was removed
	 */
	E pop() throws StackException;
	
	/**
	 * Returns the data contained in the top item of the stack.
	 * Does not remove item.
	 * @throws StackException if stack is empty
	 * @return data of the top item in stack
	 */
	E peek() throws StackException;
}
