package queue;

import static java.lang.System.out;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Spliterator;


public class QueueListBased<E> implements Queue<E> {

	LinkedList<E> list = new LinkedList<E>();
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		return list.addAll(arg0);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	@Override
	public boolean remove(Object arg0) {
		return list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return list.retainAll(arg0);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Spliterator<E> spliterator() {
		return list.spliterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return list.toArray(arg0);
	}

	@Override
	public boolean add(E arg0) {
		return list.add(arg0);
	}

	@Override
	public E element() {
		return list.element();
	}

	@Override
	public boolean offer(E arg0) {
		return list.offer(arg0);
	}

	@Override
	public E peek() {
		return list.peek();
	}

	@Override
	public E poll() {
		return list.poll();
	}

	@Override
	public E remove() {
		return list.remove();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
	
	/**
	 * Unit test.
	 */
	public static void unitTest() {
		
		out.println("-------------------- Testing QueueListBased Class:\n");
		
		out.println("Creating New Reference Based Queue...");
		QueueListBased<String> qrb = new QueueListBased<String>();
		out.println("Queue empty? " + qrb.isEmpty());
		
		String[] testSequence = {"Matt", "Isaac", "Robert", "John"};
		
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
    	out.println("-------------------- QueueListBased Unit Test Complete.\n");
	}
}
