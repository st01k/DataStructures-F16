/**
 * Interface for any kind of a card based deck.
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface Deck {

	/**
	 * Initializes deck.
	 */
	public void init();
	
	/**
	 * Shuffles deck.
	 */
	public void shuffle();
	
	/**
	 * Adds card into deck.
	 */
	public void addCard();
	
	/**
	 * Returns card from deck.
	 * @return card
	 */
	public Object getCard();
	
	/**
	 * Sorts deck.
	 */
	public void sort();
	
	/**
	 * Cuts deck in half.
	 * Places bottom half on top.
	 */
	public void cut();
	
	/**
	 * Prints deck.
	 */
	public void print();
}
