package stack;

/**
 * Basic node class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Node<E> {
	
	private E data;
	private Node<E> next;
	
	/**
	 * Creates default node with null values.
	 */
	public Node() {}
	
	/**
	 * Creates node with specified value.
	 * @param newItem value
	 */
	public Node(E newItem) {
		
		this.data = newItem;
		this.next = null;
	}

	/**
	 * Creates node with a specified value
	 * and a link to the next node.
	 * @param newItem value
	 * @param nextNode next node reference
	 */
	public Node(E newItem, Node<E> nextNode) {
		
		this.data = newItem;
		this.next = nextNode;
	}
	
	/**
	 * Sets node data.
	 * @param newData
	 */
	void setData(E newData) {
		
		this.data = newData;
	}
	
	/**
	 * Returns node data.
	 * @return node data
	 */
	E getData() {
		
		return this.data;
	}
	
	/**
	 * Sets next node reference.
	 * @param newNode next node
	 */
	void setNext(Node<E> newNode) {
		
		this.next = newNode;
	}
	
	/**
	 * Returns next node in a collection.
	 * @return next node
	 */
	Node<E> getNext() {
		
		return this.next;
	}
}
