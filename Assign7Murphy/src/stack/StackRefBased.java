package stack;

import static java.lang.System.out;

/**
 * Reference-based stack implementation.
 * @author Casey J. Murphy
 * @version 1.0
 * @param <E> Generic Object
 */
public class StackRefBased<E> implements StackInterface<E> {

	private Node<E> top;
	private int size;
	
	/**
	 * Creates a reference-based stack.
	 */
	public StackRefBased() {
		
		top = null;
	}
	
	/**
	 * Returns stack size.
	 * @return stack size
	 */
	public int size() {
		
		return size;
	}
	
	/**
	 * Returns true if stack is empty.
	 * @return true if stack is empty.
	 */
	@Override
	public boolean isEmpty() {

		return top == null;
	}

	/**
	 * Clears stack.
	 */
	@Override
	public void popAll() {

		top = null;
		size = 0;
	}

	/**
	 * Adds object to top of stack.
	 * @param newItem object to add
	 */
	@Override
	public void push(E newItem) {
		
		top = new Node<E>(newItem, top);
		size++;
	}

	/**
	 * Removes top object from stack.
	 * @throws StackException if stack is empty
	 * @return object that was removed
	 */
	@Override
	public E pop() throws StackException {

		if (!isEmpty()) {
			
			Node<E> temp = top;
			top = top.getNext();
			size--;
			return temp.getData();
		}
		else {
			
			throw new StackException("StackException on pop: stack empty");
		}
	}

	/**
	 * Returns the data contained in the top item of the stack.
	 * Does not remove item.
	 * @throws StackException if stack is empty
	 * @return data of the top item in stack
	 */
	@Override
	public E peek() throws StackException {

		if (!isEmpty()) return top.getData();
		else {
			
			throw new StackException("StackException on peek: stack empty");
		}
	}
	
	/**
	 * Returns a string of the stack items.
	 * @return stack contents
	 */
	@Override
	public String toString() {
		
		String s = "[";
		for (Node<E> curr = top; curr != null; curr = curr.getNext()) {
			
			s += curr.getData();
			if (curr.getNext() != null) s += ", ";
		}
		s += "]";
		return s;
	}
	
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing StackRefBased Class:\n");
		
		out.println("Creating New Reference Based Stack...");
		StackRefBased<Integer> srb = new StackRefBased<Integer>();
		out.println("Stack empty? " + srb.isEmpty());
		
		Integer[] testSequence = {1,0,-1,9,93,25960,9};
		
		out.println("\nPushing items onto stack...");
		for (Integer i : testSequence) srb.push(i);
		out.println("Stack empty? " + srb.isEmpty());
		out.println(srb);
		out.println("Number of items in stack: " + srb.size());
		
		out.println("\nPopping top item...");
		out.println("Popped: " + srb.pop());
		out.println(srb);
		out.println("Number of items in stack: " + srb.size());
		
		out.println("\nPeeking at top item...");
		out.println("Top item: " + srb.peek());
		out.println(srb);
		
		out.println("\nPopping all items...");
		srb.popAll();
		out.println("Stack empty? " + srb.isEmpty());
		out.println(srb);
		
    	out.println("\n-------------------- StackRefBased Unit Test Complete.\n");
	}
}
