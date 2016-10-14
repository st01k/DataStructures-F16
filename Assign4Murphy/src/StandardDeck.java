import static java.lang.System.out;

/**
 * Simple deck class.
 * Based on a standard deck of 52 playing cards.
 * Excludes jokers.
 * @author Casey J. Murphy
 *
 */
public class StandardDeck implements Deck {
	
    // class constants
	private static final int SKIPPEDRANKS = 2;								// skips jokers and low ace
	private static final int SKIPPEDSUITS = 1;								// skips empty suit
    private static final int SUITS = StandardSuit.values().length - SKIPPEDSUITS;
    private static final int RANKS = StandardRank.values().length - SKIPPEDRANKS;
    private static final int SIZE = SUITS * RANKS;

    // class variables
    private static int deckCount;

    // instance variables
    private StandardCard[] deck;

// Constructors --------------------------------------------------------------------------------------------------------
    /**
     * Creates and initializes a new deck of 52 standard playing cards.
     */
    public StandardDeck() {
    	
    	deckCount = 0;
        deck = new StandardCard[SIZE];
        init();
    }

// Accessors -----------------------------------------------------------------------------------------------------------
    /**
     * Returns deck size.
     * @return deck size
     */
    public int getSize() {
    	
        return SIZE;
    }

    /**
     * Returns a card from the top of the deck.  If too few cards remain, reshuffles
     * deck, resets deckCount, and deals from the top.
     * @return top card
     */
    public StandardCard getCard () {
    	
        while (!(deckCount <= SIZE))
        {
            out.println("Not enough cards.  Reshuffling...");
            shuffle();
            deckCount = 0;
        }
        
        StandardCard temp = deck[deckCount];
        deckCount++;
        return temp;
    }
    
    /**
     * Adds card to deck.  Not implemented.
     */
    public void addCard() {
    	
    }

    /**
     * Returns number of cards in deck that have been used.
     * @return deck count
     */
    public int getDeckCount() {
    	
        return deckCount;
    }

// Operations ----------------------------------------------------------------------------------------------------------
    /**
     * Initializes a deck of cards with proper suits and ranks.
     */
    public void init() {
    	
        for (int i = 0; i < SIZE; i++)
        {
        	StandardRank r = StandardRank.values()[(i % RANKS) + SKIPPEDRANKS];
        	StandardSuit s = StandardSuit.values()[(i / RANKS) + SKIPPEDSUITS];
            deck[i] = new StandardCard(r, s);
        }
    }

    /**
     * Shuffles deck of cards.
     */
    public void shuffle() {
    	
        deckCount = 0;

        for (int i = 0; i < SIZE; i++)
        {
            int rand = i + (int) (Math.random() * (SIZE - i));
            StandardCard temp = deck[rand];
            deck[rand] = deck[i];
            deck[i] = temp;
        }
    }
    
    /**
     * Cuts deck in half.
     * Places bottom half on top.
     */
    public void cut() {
    	
    	int half = SIZE / 2;
    	
		StandardCard[] topHalf = new StandardCard[half];
		StandardCard[] botHalf = new StandardCard[half];

		for (int i = 0; i < deck.length; i++) {
			
			if (i < half) botHalf[i] = deck[i];
			else topHalf[i - half] = deck[i];
		}
		
		for (int i = 0; i < deck.length; i++) {
			
			if (i < half) deck[i] = topHalf[i];
			else deck[i] = botHalf[i - half];
		}
    }
    
    /**
     * Sorts deck.  Not implemented.
     */
    public void sort() {
    	
    }

    /**
     * Prints deck of cards face up in four rows.
     */
    public void print() {
    	
        for (int i = 0; i < SIZE; i++) {
            deck[i].showCard(true);
            out.print("  ");
            if ((i + 1) % 4 == 0) out.println();
        }
    }
}
