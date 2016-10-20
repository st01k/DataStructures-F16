import static java.lang.System.out;

/**
 * Simple playing card class.
 * Model based on cards used in a standard deck of playing cards.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class StandardCard implements Card {
	
    // class constants
    private static final StandardRank rDEFAULT = StandardRank.values()[0];
    private static final StandardSuit sDEFAULT = StandardSuit.values()[0];

    // instance variables
    private StandardRank rank;
    private StandardSuit suit;
    private String name;
    private String sRank;
    private String sSuit;
    private char symbol;

    // Constructors --------------------------------------------------------------------------------------------------------
    /**
     * Default card is the two of hearts.
     */
    public StandardCard() {
    	
        setRank(rDEFAULT);
        setSuit(sDEFAULT);
        setName();
    }

    /**
     * Specific card Constructor<br>
     * Creates a playing card based on rank and suit as integers.  
     * Remaining instance variables are dynamically generated.
     * @param r
     * @param s
     */
    public StandardCard(StandardRank r, StandardSuit s) {
    	
        setRank(r);
        setSuit(s);
        setName();
    }

    // Accessors -----------------------------------------------------------------------------------------------------------
    /**
     * Returns rank as an integer.
     * @return rank integer
     */
    public int getRank() {
    	
        return rank.getValue();
    }

    /**
     * Returns suit as an integer.
     * @return suit integer
     */
    public int getSuit() {
    	
        return suit.getValue();
    }

    /**
     * Returns rank as a proper playing card rank (2 - A).
     * @return rank string
     */
    public String getRankString() {
    	
        return sRank;
    }

    /**
     * Returns suit as a sting (i.e. diamonds).
     * @return suit string
     */
    public String getSuitString() {
    	
        return sSuit;
    }

    /**
     * Returns suit as a unicode symbol.
     * @return suit symbol
     */
    public char getSuitSym() {
    	
    	// optimally, check system encoding and return either
    	// default or unicode symbol based on encoding type
        return symbol;
    }

    // Mutators ------------------------------------------------------------------------------------------------------------
    /**
     * Sets card rank.
     * Sets rank string.
     * @param newRank new card rank as integer
     */
    private void setRank(StandardRank newRank) {
    	
         rank = newRank;
         sRank = newRank.getName();
    }

    /**
     * Sets card suit.
     * Sets suit string.
     * Sets suit symbol.
     * @param newSuit new card suit as integer
     */
    private void setSuit(StandardSuit newSuit) {
    	
        suit = newSuit;
        sSuit = newSuit.getName();
        symbol = newSuit.getUniSymbol();
    }

    /**
     * Generates card name as text based on array definitions.
     */
    private void setName() {
    	
        name = sRank + " of " + sSuit;
    }
    
    // Overrides -----------------------------------------------------------------------------------------------------------
    /**
     * Returns string with proper rank and suit in as text.
     * @return name string
     */
    public String toString() {
    	
        return name;
    }
    
    /**
     * Checks if two cards have the same suit and rank.
     * @param c card to check against
     * @return true if they are the same, false otherwise
     */
    public boolean equals(StandardCard c) {
    	
    	if (rank.getValue() == c.getRank() && 
    		suit.getValue() == c.getSuit()) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Compares two card face values.  Returns -1 if source is smaller,
     * 1 if source is bigger, and 0 if they are equal.
     * @param c target card
     * @return result of comparison
     */
    public int compareTo(StandardCard c) {
    	
    	if (rank.getValue() < c.getRank()) return -1;
    	if (rank.getValue() > c.getRank()) return 1;
    	return 0;
    }
    
    // Operations ----------------------------------------------------------------------------------------------------------
    /**
     * Returns value of card based on 100 point ranges for 
     * each suit plus rank (face value).
     */
    public int valueOf() {
    	
    	int value = 0;
    	
    	value = suit.getValue() * 100;
    	value += rank.getValue();
    	
    	return value;
    }
    
    /**
     * Calls getRank.  Returns face value of card.
     * @return face value
     */
    public int faceValue() {
    	
    	return getRank();
    }
    
    /**
     * If visible is true, prints card rank and suit symbols.
     * Otherwise, prints symbols signifying face down.
     * Does not print trailing new line.
     */
    public void showCard(boolean visible) {
    	
    	if (visible) out.print(faceUp());
    	else out.print(faceDown());
    }
    
    /**
     * Returns card's face up string.
     */
    public String faceUp() {
    	//TODO make a OS dependent switcher for unicode or figure out how to export with it
    	//String s = String.format("%2s%s ", rank.getSymString(), suit.getUniSymbol());
    	String s = String.format("%2s%s ", rank.getSymString(), suit.getDefSymbol());
    	return s;
    }
    
    /**
     * Returns card's face down string.
     */
    public String faceDown() {
    	
    	return " []";
    }

    // Printing and Card Building ------------------------------------------------------------------------------------------
    // Each of the six lines should be printed in sequence for one card.  
    // If multiple cards are to be printed, print them line by line, not card by card.

    /**
     * Builds first line of a card.
     * @return first line of card graphic
     */
    public String line1() {
    	
        return ".------. ";
    }

    /**
     * Builds second line of a card.  
     * Dynamically generates card rank and suit.
     * Rank 10 cards are drawn using a 0 instead of 10.
     * @return second line of card graphic
     */
    public String line2() {
    	
        String r = rank.getSymString();
        if (r == "10") r = "T";
        int s = getSuit();

        switch (s)
        {
            case 1 :
            	return String.format("|%s_  _ | ", r);
            case 2 :
                return String.format("|%s /\\  | ", r);
            case 3 :
            	return String.format("|%s  _  | ", r);
            case 4 :
                return String.format("|%s  .  | ", r);
            default:
                return null;
        }
    }

    /**
     * Builds third line of a card.  
     * Dynamically generates card suit.
     * @return third line of card graphic
     */
    public String line3() {
    	
    	int s = getSuit();

        switch (s)
        {
            case 1 :
                return "|( \\/ )| ";
            case 2 :
                return "| /  \\ | ";
            case 3 :
            	return "|  ( ) | ";
            case 4 :
                return "|  / \\ | ";
            default:
                return null;
        }
    }

    /**
     * Builds fourth line of a card.  
     * Dynamically generates card suit.
     * @return fourth line of card graphic
     */
    public String line4() {
    	
        int s = getSuit();

        switch (s)
        {
            case 1 :
                return "| \\  / | ";
            case 2 :
                return "| \\  / | ";
            case 3 :
            	return "| (_x_)| ";
            case 4 :
                return "| (_,_)| ";
            default:
                return null;
        }
    }

    /**
     * Builds fifth line of a card.  
     * Dynamically generates card rank and suit.
     * Rank 10 cards are drawn using a 0 instead of 10.
     * @return fifth line of card graphic
     */
    public String line5() {
    	
    	String r = rank.getSymString();
        if (r == "10") r = "T";
        int s = getSuit();

        switch (s)
        {
            case 1 :
                return String.format("|  \\/ %s| ", r);
            case 2 :
                return String.format("|  \\/ %s| ", r);
            case 3 :
            	return String.format("|   Y %s| ", r);
            case 4 :
                return String.format("|   I %s| ", r);
            default:
                return null;
        }
    }

    /**
     * Builds sixth line of a card.
     * @return sixth line of card graphic
     */
    public String line6() {
    	
        return "`------' ";
    }
    
    /**
     * Prints single card with vertical space buffer.
     */
    public void print() {
    	
    	out.println();
    	out.println(line1());
    	out.println(line2());
    	out.println(line3());
    	out.println(line4());
    	out.println(line5());
    	out.println(line6());
    	out.println();
    }
    
    /**
     * Copies a stack of cards.  Returns copy.
     * @param in stack to be copied
     * @return copied stack
     */
    public static StandardCard[] copy(StandardCard[] in) {
    	
    	StandardCard[] temp = new StandardCard[in.length];
    	
    	for (int i = 0; i < in.length; i++) {
    		
    		temp[i] = in[i];
    	}
    	
    	return temp;
    }
}
