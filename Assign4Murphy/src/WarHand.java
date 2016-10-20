/**
 * Creates a hand for the game of War.
 * @author Casey J. Murphy
 * @version 0.9
 */
public class WarHand implements Hand {
	
	private static final int DEF_HAND_SZ = 52;
	
	private StandardCard[] hand;
	private int cardsInHand;
	
	/**
	 * Creates War hand.
	 */
	public WarHand() {
		
		hand = new StandardCard[DEF_HAND_SZ];
		cardsInHand = 0;
	}
	
	/**
	 * Returns default War hand size (26 cards).
	 * @return default war hand
	 */
	public static int getDefHandSize() {
		
		return DEF_HAND_SZ;
	}
	
	/**
	 * Returns size of War hand.
	 * @return size of war hand
	 */
	public int size() {

		int size = 0;
		for (StandardCard c : hand) {
			
			if (c == null) continue;
			size++;
		}
		
		return size;
	}
	
	/**
	 * Adds card to top of War hand.
	 * @param c card to add
	 */
	public void addCard(StandardCard c) {
		
		hand[cardsInHand] = c;
		cardsInHand++;
		
		//TODO look at me
	}
	
	/**
	 * Adds card to bottom of War hand.
	 * @param c card to add
	 */
	public void addCardToBottom(StandardCard c) {
		
		// increase temp array by 1
		StandardCard[] temp = new StandardCard[cardsInHand + 1];

		// copy new card to first element of temp
		temp[0] = c;
		// copy rest of hand to temp
		for (int i = 1; i < temp.length; i++) {
			
			// hand decrement bc loop init is 1
			if (hand[i - 1] == null) continue;
			temp[i] = hand[i - 1];
		}
		
		// copy temp back into hand
		for (int i = 0; i < hand.length; i++) {
			
			// any index more than new size is null
			if (i >= temp.length) hand[i] = null;
			else hand[i] = temp[i];
		}
		
		cardsInHand++;
	}
	
	/**
	 * Removes card from War hand
	 * @param c card to remove
	 */
	public void removeCard(StandardCard c) {
		
		hand[size() - 1] = null;
		cardsInHand--;
	}
	
	/**
	 * Returns card to put into play.
	 * Removes card from the War hand.
	 */
	public StandardCard playCard() {
		
		try {
			
			StandardCard temp = hand[cardsInHand - 1];
			removeCard(hand[cardsInHand - 1]);
			return temp;
		}
		
		catch (Exception e) {
			
			System.out.println("Beta version error.  Please re-run program.  This happens sometimes.");
			System.out.println("Program Terminating...");
			System.exit(1);
		}
		
		return null;
	}
	
	/**
	 * Not applicable to War.
	 */
	public void sort() {}
	
	/**
	 * Returns last card status.
	 * @return true if hand's last card, false otherwise
	 */
	public boolean lastCard() {
		
		if (cardsInHand == 1) return true;
		return false;
	}
	
	// Printing ------------------------------------------------------------------------------------------------------------
    /**
     * Prints player's hand on one line.
     */
    public void printHand(boolean show)
    {
    	
        for (int i = 0; i < hand.length; i++)
        {
        	if (hand[i] == null) continue;
        	if (show) hand[i].showCard(true);
        	else hand[i].showCard(false);
        }
        
        System.out.println();
    }
    
    /**
     * Prints a number of card pictures, specified by parameter.
     * @param cardsToShow number of cards to be shown
     */
    public static void drawCardsPic(StandardCard[] cards)
    {
        System.out.println(buildPic(cards));
    }

    /**
     * Builds picture of cards to be printed, line by line.  Calls to
     * line methods located in the StandardCard class for dynamic representation
     * of the specified card in player's hand.
     * @param c number of cards to be printed
     * @return the picture as a printable string
     */
    private static String buildPic(StandardCard[] _hand) {
        String l1 = "";
        String l2 = "";
        String l3 = "";
        String l4 = "";
        String l5 = "";
        String l6 = "";

        for (int i = 0; i < _hand.length; i++)
        {
            l1 += _hand[i].line1();
            l2 += _hand[i].line2();
            l3 += _hand[i].line3();
            l4 += _hand[i].line4();
            l5 += _hand[i].line5();
            l6 += _hand[i].line6();
        }

        l1 += "\n";
        l2 += "\n";
        l3 += "\n";
        l4 += "\n";
        l5 += "\n";

        return l1 + l2 + l3 + l4 + l5 + l6;
    }
}
