/**
 * Hand interface
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface Hand {

	/**
	 * Add card to hand.
	 * @param c card to be added
	 */
	public void addCard(StandardCard c);
	
	/**
	 * Remove card from hand.
	 * @param c card to be removed
	 */
	public void removeCard(StandardCard c);
	
	/**
	 * Sorts hand.
	 */
	public void sort();
	
	/**
	 * Puts a card in play.
	 * @return card to be played
	 */
	public StandardCard playCard();
	
	/**
	 * Prints hand.
	 * @param showFace true if card visible, false otherwise
	 */
	public void printHand(boolean visible);
}
