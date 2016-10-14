/**
 * Basic Integer node class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class IntegerNode {
	
	Integer item;
	IntegerNode next;
	
	/**
	 * Creates default node.
	 */
	public IntegerNode() {
		
		item = null;
		next = null;
	}
	
	/**
	 * Creates node with specified value.
	 * @param newItem value
	 */
	public IntegerNode(Integer newItem) {
		
		item = newItem;
		next = null;
	}

	/**
	 * Creates node with a specified value
	 * and a link to the next node.
	 * @param newItem value
	 * @param nextNode next node reference
	 */
	public IntegerNode(Integer newItem, IntegerNode nextNode) {
		
		item = newItem;
		next = nextNode;
	}
}
