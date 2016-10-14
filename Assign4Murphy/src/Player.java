/**
 * Basic player class.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class Player {
	
	private static int id = 0;
	private static String DEFAULTNAME = "Player ";
	
	private int _id;
	private String name;
	private WarHand hand;
	
	/**
	 * Creates default player.
	 * Name is Player #.
	 */
	public Player() {
		
		setId();
		setName(DEFAULTNAME);
		hand = new WarHand();
	}
	
	/**
	 * Creates named player.
	 * @param n name
	 */
	public Player(String n) {

		setId();
		setName(n);
		hand = new WarHand();
	}
	
	/**
	 * Sets player ID based on static count.
	 */
	private void setId() {
		
		id++;
		_id = id;
	}
	
	/**
	 * Returns player ID.
	 * @return player id
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Sets player's name.
	 * @param newName player name
	 */
	private void setName(String newName) {
		
		if (newName.equals(DEFAULTNAME)) {
			
			name = newName + _id;
		}
		
		else name = newName;
	}
	
	/**
	 * Returns player's name.
	 * @return player name
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Returns size of player's hand.
	 * @return size of player hand
	 */
	public int getHandSize() {
		
		return hand.size();
	}
	
	/**
	 * Adds card to top of player's hand.
	 * @param c card to add
	 */
	public void addCardToHand(StandardCard c) {
		
		hand.addCard(c);
	}
	
	/**
	 * Places a card from the player's hand into play.
	 * Removes card from the player's hand via WarHand.
	 * @return card to be played
	 */
	public StandardCard flipCard() {
		
		return hand.playCard();
	}
	
	/**
	 * Adds card to bottom of player's hand.
	 * @param c card to be added
	 */
	public void addToBottom(StandardCard c) {
		
		hand.addCardToBottom(c);
	}
	
	/**
	 * Prints player's hand.
	 * Used for testing.
	 */
	public void printHand() {
		
		hand.printHand(true);
	}
	
	/**
	 * Checks if player only has one card left.
	 * @return true if only one card, false otherwise
	 */
	public boolean lastCard() {
		
		if (hand.lastCard()) return true;
		return false;
	}
}

