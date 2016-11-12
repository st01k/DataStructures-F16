package iterator;

import static java.lang.System.out;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import queue.QueueRefBased;

/**
 * Queue-based iterator.
 * @author Casey J. Murphy
 * @version 1.0
 * @param <E> data type of queue
 */
public class QueueIterator<E> implements ListIterator<E> {

	private QueueRefBased<E> q;
	private int cursor;
	
	/**
	 * Creates a queue-specific iterator.
	 * @param generic queue
	 */
	public QueueIterator(QueueRefBased<E> q) {
		
		this.q = q;
		cursor = 0;
	}
	
	/**
	 * Resets the iterator to before the first item in the same queue.
	 * @return new iterator
	 */
	public QueueIterator<E> reset() {
		
		return new QueueIterator<E>(q);
	}
	
	// queue manipulation -----------------------------------------------------
	/**
	 * Adds an item to the queue, updates iterator.
	 * @param item to be added
	 */
	@Override
	public void add(E item) {
		
		q.add(item);
		cursor++;
	}
	
	/**
	 * Unsupported Operation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Unsupported Operation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void set(E item) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	// queue information ------------------------------------------------------
	/**
	 * Returns true if queue has a next item.
	 * @return true if next item exists
	 */
	@Override
	public boolean hasNext() {
		return (cursor < q.size());
	}
	
	/**
	 * Returns true if queue has a previous item.
	 * @return true if previous item exists
	 */
	@Override
	public boolean hasPrevious() {
		return (cursor > 0);
	}
	
	/**
	 * Returns the index of the next item.
	 * @return next item index number
	 */
	@Override
	public int nextIndex() {
		return cursor;
	}
	
	/**
	 * Returns the index of the previous item.
	 * @return previous item index number
	 */
	@Override
	public int previousIndex() {
		return cursor - 1;
	}
	
	/**
	 * Retrieves but does not remove next item in queue.
	 * @return next item in queue
	 */
	@Override
	public E next() throws NoSuchElementException {
		
		try {
			
			E item = q.peekAtIndex(cursor);
			cursor++;
			return item;
		}
		catch (IndexOutOfBoundsException e) 
				{ throw new NoSuchElementException(); }
	}
	
	/**
	 * Retrieves but does not remove previous item in queue.
	 * @return previous item in queue
	 */
	@Override
	public E previous() throws NoSuchElementException {
		
		try {
			
			E item = q.peekAtIndex(cursor - 1);
			cursor--;
			return item;
		}
		catch (IndexOutOfBoundsException e) 
				{ throw new NoSuchElementException(); }
	}
	
	// testing ----------------------------------------------------------------
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing QueueIterator Class:\n");
		
		out.println("creating test queue...");
		QueueRefBased<Integer> qrb = new QueueRefBased<Integer>();
		
		Integer[] testSeq = { -1, 0, 1, 9, 72, 2160 };
		
		QueueIterator<Integer> iterator = qrb.iterator();
		
		out.println("populating queue with objects...");
		for (Integer i : testSeq) {
			iterator.add(i);
			out.println("added: " + i);
		}
		out.println("\nAdded " + (iterator.previousIndex() + 1) + " items.");
		
		out.println("\niterator has next? " + iterator.hasNext());
		out.println("iterating backward...");
		while (iterator.hasPrevious()) {
			out.print("prev index: " + iterator.previousIndex());
			out.println(" | item: " + iterator.previous());
		}
		
		out.println("\niterator has previous? " + iterator.hasPrevious());
		out.println("iterating forward...");
		while (iterator.hasNext()) {
			out.print("next index: " + iterator.nextIndex());
			out.println(" | item: " + iterator.next());
		}
		
		out.println();
    	out.println("-------------------- QueueIterator Unit Test Complete.\n");
	}
}
