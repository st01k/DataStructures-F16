/**
 * Interface for a card.
 * @author Casey J. Murphy
 * @version 1.0
 */
public interface Card {

	/**
	 * Returns rank integer value.
	 * @return rank int
	 */
	public int getRank();
	
	/**
	 * Returns suit integer value.
	 * @return suit int
	 */
	public int getSuit();
	
	/**
	 * Returns value of the card.
	 * @return value int
	 */
	public int valueOf();
	
	/**
	 * Sets visibility on the card.
	 */
	public void showCard(boolean b);
	
	/**
	 * Returns card rank and suit.
	 * @return rank and suit string
	 */
	public String faceUp();
	
	/**
	 * Returns symbol(s) used to signify a face down card.
	 * @return faceDown string
	 */
	public String faceDown();
	
	/**
	 * Prints card.
	 */
	public void print();
	
}
