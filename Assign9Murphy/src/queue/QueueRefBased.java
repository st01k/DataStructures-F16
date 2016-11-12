package queue;

import static java.lang.System.out;
import iterator.QueueIterator;

/**
 * Circular reference-based queue with iterator.
 * Default constructor creates an empty queue.
 * @author Casey J. Murphy
 * @version 1.0
 * @param <E> data type of objects in queue
 */
public class QueueRefBased<E> {

	private Node<E> lastNode;
	private int size;
		
	// queue information ------------------------------------------------------
	/**
	 * Returns true if the queue is empty.
	 */
	public boolean isEmpty() {
		return lastNode == null;
	}
	
	/**
	 * Returns the number of elements in this queue.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a string of the queue items.
	 * @return queue contents
	 */
	@Override
	public String toString() {
		
		int cnt = 0;
		String s = "[";
		
		if (cnt < size) {
			for (Node<E> curr = lastNode.next; 
				curr != null && cnt < size; 
				curr = curr.next) {
				
					s += curr.data;
					if (cnt <= size - 2) s += ", ";
					cnt++;
			}
		}
		
		s += "]";
		return s;
	}
	
	/**
	 * Returns an iterator for this queue.
	 * @return
	 */
	public QueueIterator<E> iterator() {
		
		return new QueueIterator<E>(this);
	}
	
	// queue manipulation -----------------------------------------------------
	/**
	 * Clears entire queue.
	 */
	public void clear() {
	
		lastNode = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue.
	 * @return true upon success
	 */
	public boolean add(E newData) {
		
		Node<E> newNode = new Node<E>(newData);
		
		if (isEmpty()) newNode.next = newNode;
		else {
			newNode.next = lastNode.next;
			lastNode.next = newNode;
		}
		size++;
		lastNode = newNode;
		return true;
	}
	
	/**
	 * Retrieves and removes the head of this queue.
	 * @return item at the head of the queue
	 * @throws QueueException if the queue is empty
	 */
	public E remove() throws QueueException {
		
		if (isEmpty()) throw new QueueException
			("QueueException: QueueRefBased.remove - queue underflow"); 
			
		Node<E> firstNode = lastNode.next;
		if (firstNode == lastNode) lastNode = null;
		else lastNode.next = firstNode.next;
		size--;
		return firstNode.data;
	}

	/**
	 * Retrieves and remove the head of this queue, or 
	 * returns null if the queue is empty.
	 * @return item at the head of the queue
	 */
	public E poll() {

		if (isEmpty()) return null;
		Node<E> firstNode = lastNode.next;
		if (firstNode == lastNode) lastNode = null;
		else lastNode.next = firstNode.next;
		size--;
		return firstNode.data;
	}
	
	// queue retrival ---------------------------------------------------------
	/**
	 * Retrieves but does not remove the head of this queue.
	 * @return item at the head of the queue
	 * @throws QueueException if the queue is empty
	 */
	public E element() throws QueueException {
		
		E data = peek();
		if (data == null) throw new QueueException
			("QueueException: QueueRefBased.element - empty queue");;
		return data;
	}

	/**
	 * Retrieves but does not remove the head of this 
	 * queue, or returns null if the queue is empty.
	 * @return item at the head of the queue
	 */
	public E peek() {
		
		if (isEmpty()) return null;
			
		Node<E> firstNode = lastNode.next;
		return firstNode.data;
	}
	
	/**
	 * Retrieves but does not remove the item
	 * in the queue at a specified index.
	 * @param index index of specified item
	 * @return item at specified index
	 */
	public E peekAtIndex(int index) {
		
		int i = 0;
		E item = null;
		for (Node<E> curr = lastNode.next; i <= index; curr = curr.next) {
			
			item = curr.data;
			i++;
		}
		return item;
	}

	// testing ----------------------------------------------------------------
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing QueueRefBased Class:\n");
		
		out.println("Creating New Reference Based Queue...");
		QueueRefBased<Integer> qrb = new QueueRefBased<Integer>();
		out.println("Queue empty? " + qrb.isEmpty());
		
		Integer[] testSequence = { -1, 0, 1, 9, 72, 2160 };
		
		out.println("\nAdding items to queue...");
		for (Integer i : testSequence) {
			
			qrb.add(i);
			out.println(qrb);
		}
		
		out.println();
		out.println("Queue empty? " + qrb.isEmpty());
		out.println(qrb);
		out.println("Number of items in queue: " + qrb.size());
		
		out.println("\nRemoving first item...");
		out.println("Removed: " + qrb.remove());
		out.println(qrb);
		out.println("Number of items in queue: " + qrb.size());
		

		out.println("\nPeeking at first item...");
		out.println("First item: " + qrb.peek());
		out.println(qrb);
		
		out.println();
		for (int i = 0; i < testSequence.length - 1; i++) {
			
			out.println("Peeking at item " + (i + 1) + 
						" (index: " + i + "): " + 
						qrb.peekAtIndex(i));
		}
		
		out.println("\nClearing queue...");
		qrb.clear();
		out.println("Queue empty? " + qrb.isEmpty());
		out.println(qrb);
		
		out.println();
    	out.println("-------------------- QueueRefBased Unit Test Complete.\n");
	}
}
