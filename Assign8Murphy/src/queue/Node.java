package queue;

/**
 * Basic node class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Node<T> {
	
	T data;
	Node<T> next;
	
	/**
	 * Creates node with specified value.
	 * @param newItem value
	 */
	public Node(T newItem) {
		this.data = newItem;
	}

	/**
	 * Creates node with a specified value
	 * and a link to the next node.
	 * @param newItem value
	 * @param nextNode next node reference
	 */
	public Node(T newItem, Node<T> nextNode) {
		
		this.data = newItem;
		this.next = nextNode;
	}
}
