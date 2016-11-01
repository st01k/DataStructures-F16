package queue;

import static java.lang.System.out;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class QueueRefBased<E> implements Queue<E> {

	private Node<E> lastNode;
	private int size;
	
	
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
	 * Clears entire queue.
	 */
	@Override
	public void clear() {
	
		lastNode = null;
		size = 0;
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
	 * Inserts the specified element into this queue.
	 * @return true upon success
	 */
	@Override
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
	 * Inserts the specified element into this queue.
	 * @return true upon success
	 */
	@Override
	public boolean offer(E newData) {
		
		add(newData);
		return true;
	}
	
	/**
	 * Retrieves but does not remove the head of this queue.
	 * @return item at the head of the queue
	 * @throws QueueException if the queue is empty
	 */
	@Override
	public E element() throws QueueException {
		
		E data = peek();
		if (data == null) throw new QueueException
			("QueueException: QueueRefBased.dequeue - queue underflow");;
		return data;
	}

	/**
	 * Retrieves but does not remove the head of this queue,
	 * or returns null if the queue is empty.
	 * @return item at the head of the queue
	 */
	@Override
	public E peek() {
		
		if (isEmpty()) return null;
			
		Node<E> firstNode = lastNode.next;
		return firstNode.data;
	}

	/**
	 * Retrieves and remove the head of this queue, or 
	 * returns null if the queue is empty.
	 * @return item at the head of the queue
	 */
	@Override
	public E poll() {

		if (isEmpty()) return null;
		Node<E> firstNode = lastNode.next;
		if (firstNode == lastNode) lastNode = null;
		else lastNode.next = firstNode.next;
		size--;
		return firstNode.data;
	}

	/**
	 * Retrieves and removes the head of this queue.
	 * @return item at the head of the queue
	 * @throws QueueException if the queue is empty
	 */
	@Override
	public E remove() throws QueueException {
		
		if (isEmpty()) throw new QueueException
			("QueueException: QueueRefBased.dequeue - queue underflow"); 
			
		Node<E> firstNode = lastNode.next;
		if (firstNode == lastNode) lastNode = null;
		else lastNode.next = firstNode.next;
		size--;
		return firstNode.data;
	}
	
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing QueueRefBased Class:\n");
		
		out.println("Creating New Reference Based Queue...");
		QueueRefBased<String> qrb = new QueueRefBased<String>();
		out.println("Queue empty? " + qrb.isEmpty());
		
		String[] testSequence = {"Casey", "Ryan", "Brandon", "Gordon"};
		
		out.println("\nAdding items to queue...");
		for (String s : testSequence) {
			
			qrb.add(s);
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
		
		out.println("\nDequeing all items...");
		qrb.clear();
		out.println("Queue empty? " + qrb.isEmpty());
		out.println(qrb);
		
		out.println();
    	out.println("-------------------- QueueRefBased Unit Test Complete.\n");
	}

	/**
	 * Not implemented.  The following overridden methods are not implemented.
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) { return false;	}
	@Override
	public boolean contains(Object arg0) { return false; }
	@Override
	public boolean containsAll(Collection<?> arg0) { return false; }
	@Override
	public Iterator<E> iterator() { return null; }
	@Override
	public Stream<E> parallelStream() { return null; }
	@Override
	public boolean removeAll(Collection<?> arg0) { return false; }
	@Override
	public boolean removeIf(Predicate<? super E> arg0) { return false; }
	@Override
	public boolean retainAll(Collection<?> arg0) { return false; }
	@Override
	public Spliterator<E> spliterator() { return null; }
	@Override
	public Stream<E> stream() { return null; }
	@Override
	public Object[] toArray() { return null; }
	@Override
	public <T> T[] toArray(T[] arg0) { return null; }
	@Override
	public void forEach(Consumer<? super E> arg0) {}
	@Override
	public boolean remove(Object arg0) { return false; }
}
