import static java.lang.System.out;

/**
 * Recursive implementation of the list ADT using a sorted list. 
 * @author Casey J. Murphy
 * @version 1.1
 */
public class SortedList implements LinkedListInterface {
	
	private boolean recursive;
	private int size;
	private IntegerNode head;
	private IntegerNode tail;
	
	/**
	 * Creates an empty list with default values.
	 * Recursive mode ON by default.
	 */
	public SortedList() {
		
		recursive = true;
		size = 0;
		head = null;
	}
	
	/**
	 * Creates empty list in specified mode.
	 * @param recurse true recursive, false iterative
	 */
	public SortedList(boolean recurse) {
		
		recursive = recurse ? true : false;
		size = 0;
		head = null;
	}

	// List Information -------------------------------------------------------
	/**
	 * Returns the number of elements in the list.
	 * @return number of list elements
	 */
	public int size() {
		
		return size;
	}
		
	/**
	 * Returns true if this list contains no elements.
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	/**
	 * Returns true if list is in recursive mode; false if list is
	 * in iterative mode.
	 * @return true for recursive; false for iterative
	 */
	public boolean isRecursive() {
		
		return recursive;
	}
		
	// List Manipulation ------------------------------------------------------
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		
		// recursive solution
		if (recursive) {
			clearRecursive(head);
			size = 0;
		}
		// iterative solution
		else {
			head = null;
			size = 0;
		}
	}
	
	/**
	 * Clears list recursively.
	 * @param headNode
	 * @return head node of cleared list
	 */
	private IntegerNode clearRecursive(IntegerNode headNode) {
		
		if (headNode == null) head = null;
		else clearRecursive(headNode.next);
		return headNode;
	}
	
	/**
	 * Toggles recursive/iterative mode.
	 */
	public void toggleMode() {
		
		recursive = recursive ? false : true;
	}
	
	/**
	 * Assigns tail reference to tail node.
	 */
	public void assignTail() {
		
		if (recursive) {
			
			tail = getTailRecursive(head);
		}
		else {
			
			tail = find(size - 2);
		}
	}
	
	/**
	 * Recursively returns tail node.
	 * @param nodeHead list head node
	 * @return list tail node
	 */
	private IntegerNode getTailRecursive(IntegerNode nodeHead) {

		if (nodeHead == null || nodeHead.next == null) return nodeHead;
		return getTailRecursive(nodeHead.next);
	}
	
	// Item Information -------------------------------------------------------
	/**
	 * Returns true if this list contains the specified element.
	 * @param o target element
	 * @return true if target is in this list
	 */
	public boolean contains(Object o) {
		
		if (!(o instanceof Integer)) return false;
		Integer i = (Integer) o;
		
		// recursive solution
		if (recursive) {
			return containsRecursive(head, i);
		}
		// iterative solution
		else {
			
			for (int j = 0; j < size; j++) {
				if (find(j).item.equals(i)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Recursively returns true if this list contains the specified element.
	 * @param headNode head of list
	 * @param i target sought
	 * @return true if target is in list; false otherwise
	 */
	private boolean containsRecursive(IntegerNode headNode, Integer i) {
		
		if (headNode == null) return false;
		if (headNode.item.equals(i)) return true;
		return containsRecursive(headNode.next, i);
	}
		
	/**
	 * Compares the target object with this list (source object) for equality.
	 * @param o target object
	 * @return true if target is equal to source object (this list)
	 */
	public boolean equals(Object o) {
		
		if (!(o instanceof SortedList)) return false;
		
		SortedList target = (SortedList) o;
		
		if (size != target.size) return false;
		else {
			// recursive solution
			if (recursive) {
				
				return equalsRecursive(head, target.head);
			}
			// iterative solution
			else {
				
				IntegerNode sourceNode = head;
				IntegerNode targetNode = target.head;
				while (sourceNode != null && targetNode != null) {
					
					if (!sourceNode.item.equals(targetNode.item)) return false;
					sourceNode = sourceNode.next;
					targetNode = targetNode.next;
				}
				
				return (sourceNode == null && targetNode == null);
			}
		}
	}
	
	/**
	 * Recursively compares the target nodes with the source list nodes.
	 * @param sourceHead list head
	 * @param targetHead target list head
	 * @return true if lists are equal; false otherwise
	 */
	private boolean equalsRecursive(IntegerNode sourceHead, IntegerNode targetHead) {
		
		if (sourceHead == null && targetHead == null) return true;
		
		if (sourceHead != null && targetHead != null)
			return (sourceHead.item.equals(targetHead.item)) && 
					equalsRecursive(sourceHead.next, targetHead.next);
		
		return false;
	}
	
	// Item Manipulation ------------------------------------------------------
	/**
	 * Adds object to list.
	 * @param o object to add
	 */
	public void add(Object o) {
		
		Integer newItem = (Integer) o;
		
		if (recursive) {
			
			head = insertRecursive(newItem, head);
		}
		// iterative solution
		// TODO fix me
		else {	
			
			IntegerNode curr = findSortedInsertionPoint(newItem);
			// insert beginning
			if (size == 0 || getSortedPrev(curr) == null) {
				IntegerNode newNode = new IntegerNode(newItem, head);
				head = newNode;
			}
			// insert middle
			else if (getSortedPrev(curr) != null && curr != null) {
				
				IntegerNode prev = getSortedPrev(curr);
				IntegerNode newNode = new IntegerNode(newItem, prev.next);
				prev.next = newNode;
			}
			// insert last
			else if (curr == null) {
				
				IntegerNode prev = getSortedPrev(curr);
				IntegerNode newNode = new IntegerNode(newItem);
				prev.next = newNode;
			}
		}
		
		assignTail();
		size++;
	}
	
	/**
	 * Recursively inserts the specified element into its proper position within the list.
	 * Returns true if the list was modified.
	 * @param newItem value to add
	 * @return head node of modified list
	 */
	private IntegerNode insertRecursive(Integer newItem, IntegerNode headNode) {
		
		if (headNode == null || newItem < headNode.item) {
			return new IntegerNode(newItem, headNode);
		}
		else {
			headNode.next = insertRecursive(newItem, headNode.next);
			return headNode;
		}
	}
	
	/**
	 * Removes specified item from list.
	 * @param o object to remove
	 */
	public void remove(Object o) {
		
		Integer removeData = (Integer) o;
		if (contains(removeData)) {
			
			// recursive solution
			if (recursive) {
				
				head = removeRecursive(removeData, head);
			}
			// iterative solution
			// TODO fix me
			else {
				
				if (size == 0) {
					head = head.next;
				}
				else {
					IntegerNode temp = find(indexOf(removeData) - 1);
					IntegerNode prev = getSortedPrev(temp);
					
					if (prev == null) {
						head = head.next;
					}
					else {
						IntegerNode curr = prev.next;
						prev.next = curr.next;
					}
				}
			}
			
			assignTail();
			size--;
		}
		else {
			
			out.println(removeData + " Not Found");
		}
	}
	
	/**
	 * Recursively removes the first occurrence of the specified element from this list. 
	 * @param removeItem item to remove
	 * @param headNode head of list
	 * @return head of modified list
	 */
	private IntegerNode removeRecursive(Integer removeItem, IntegerNode headNode) {
		
		if (headNode == null) return headNode;
		else if (headNode.item.equals(removeItem)) return headNode.next;
		else {
			headNode.next = removeRecursive(removeItem, headNode.next);
			return headNode;
		}
	}
	
	// Item Retrieval ---------------------------------------------------------
	/**
	 * Iteratively finds an item based on index.
	 * @param index item to retrieve
	 * @return item
	 */
	public IntegerNode find(int index) {
		
		IntegerNode curr = head;
		
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		return curr;
	}
	
	/**
	 * Iteratively finds insertion point for item in sorted list.
	 * @param value item value to find
	 * @return item
	 */
	public IntegerNode findSortedInsertionPoint (int value) {
		
		for (IntegerNode curr = head; curr != null; curr = curr.next) {
			
			
			if (curr.next != null && value <= curr.item) return curr;
		}
		return null;
		
	}
	
	/**
	 * Iteratively finds the index of an item with a specified value.
	 * @param value index to find
	 * @return index number
	 */
	public int indexOf(int value) {
		
		int cnt = 0;
		for (IntegerNode curr = head; curr != null; curr = curr.next) {
			cnt++;
			if (curr.item.equals(value)) return cnt;
		}
		return -1;
	}
	
	/**
	 * Iteratively returns the previous node in a sorted list.
	 * @param curr current node
	 * @return previous node; if curr is null, returns null
	 */
	public IntegerNode getSortedPrev(IntegerNode curr) {
		
		if (curr == null) return null;
		int index = indexOf(curr.item);
		return find(index);
	}
	
	/**
	 * Prints list.
	 */
	public void printList() {
		
		out.println();
		out.println("Integer List: " + size + " items");
		out.println("------------------------");
		
		// recursive solution
		if (recursive) printListRecursive(head);
		// iterative solution
		else {
			
			for (int i = 0; i < size; i++) {
				out.println(find(i).item);
			}
		}
		
		out.println("------------------------");
		out.println();
	}
	
	/**
	 * Prints list recursively.
	 * @param headNode head of list
	 */
	private void printListRecursive(IntegerNode headNode) {
		
		if (headNode != null) {
			out.println(headNode.item);
			printListRecursive(headNode.next);
		}
	}
	
	/**
	 * Unit tests the SortedList data type.
	 * @param recurse if true, recursive mode; iterative otherwise
	 */
	public static void unitTest(boolean recurse) {
		
		out.println();
		if (recurse) out.println("----------------------------------- Recursive");
		else {
			out.println("WARNING: ITERATIVE MODE NOT SORTING CORRECTLY");
			out.println("----------------------------------- Iterative");
		}
		
		SortedList sl = new SortedList(recurse);
		if (sl != null) out.println("Created new list.");
		out.println("New list is Empty? " + sl.isEmpty());
		sl.printList();
		out.println("Tail: " + sl.tail);
		out.println();
		
		out.println("Populating List...");
		Integer[] testSeq = {3, 5, 18, 2, 18, 99, -5, 0, 72};
		for (int i = 0; i < testSeq.length; i++) {
			sl.add(testSeq[i]);
			out.println("added: " + testSeq[i]);
		}
		
		out.println();
		out.println("Populated List is Empty? " + sl.isEmpty());
		sl.printList();
		out.println("Tail: " + sl.tail);
		out.println();
		
		out.println("removing: " + testSeq[0]);
		sl.remove(testSeq[0]);
		sl.printList();
		out.println("Tail: " + sl.tail);
		out.println();

		out.println("removing: " + testSeq[5]);
		sl.remove(testSeq[5]);
		sl.printList();
		out.println("Tail: " + sl.tail);
		out.println();

		out.println("Comparing identical lists...");
		String compString = "";
		if (!sl.equals(sl)) compString = " NOT";
		out.println("Original list is" + compString + " the same as Original list.");
		out.println();
		
		
		out.println("Creating new list for comparison...");
		SortedList slTemp = new SortedList(recurse);
		if (!sl.equals(slTemp)) compString = " NOT";
		out.println("Original list is" + compString + " the same as Temp list.");
		out.println();
		
		out.println("Clearing Original List... ");
		sl.clear();
		out.println("Cleared List is Empty? " + sl.isEmpty());
		sl.printList();
		
		out.println();
	}
}
