import static java.lang.System.out;

/**
 * Test driver for War
 * @author Casey J. Murphy
 * @version 0.9
 */
public class Tester {

	/**
	 * Drives test.
	 */
	public static void test() {
		
		out.println("Test");
		out.println();
		testDeck();
		testCard();
		testHand();
	}
	
	/**
	 * Tests deck.
	 */
	public static void testDeck() {
		
		testHeading("Deck interface, StandardDeck class");
		Deck d = new StandardDeck();
		out.println("Deck initialized:");
		d.print();
		out.println();
		
		d.cut();
		out.println("Deck Cut:");
		d.print();
		out.println();
		
		d.shuffle();
		out.println("Deck shuffled:");
		d.print();
		out.println();
	}
	
	/**
	 * Tests card.
	 */
	public static void testCard() {
		
		testHeading("Card interface, StandardCard class");
		out.println("First 3 Cards, Initialized:");
		out.println();
		StandardDeck d = new StandardDeck();
		StandardCard c1 = d.getCard();
		StandardCard c2 = d.getCard();
		StandardCard c3 = d.getCard();
		c1.print();
		c2.print();
		c3.print();
		out.println();
		out.println("First 3 Cards, Shuffled:");
		out.println();
		d.shuffle();
		StandardCard d1 = d.getCard();
		StandardCard d2 = d. getCard();
		StandardCard d3 = d.getCard();
		d1.print();
		d2.print();
		d3.print();
	}
	
	/**
	 * Tests hand.
	 */
	public static void testHand() {
		
		testHeading("Hand interface, WarHand class");
		StandardDeck d = new StandardDeck();
		d.shuffle();
		WarHand p1 = new WarHand();
		WarHand p2 = new WarHand();
		int dealRounds = WarHand.getDefHandSize() / 2 + 1;

		for (int player = 0; --dealRounds > 0; player = (1 - player)) {
		
			p1.addCard(d.getCard());
			p2.addCard(d.getCard());
		}
		
		out.println("Player 1 Hand: " + p1.size());
		p1.printHand(true);
		out.println();
		out.println("Player 2 Hand: " + p2.size());
		p2.printHand(true);
		out.println();
		
		out.println("");
		StandardCard cP1 = p1.playCard();
		StandardCard cP2 = p2.playCard();

		if (cP1.getRank() > cP2.getRank()) {
			out.println("Player 1: " + cP1.faceValue() + " is higher.");
			p1.addCardToBottom(cP1);
			p1.addCardToBottom(cP2);
		}
		
		if (cP1.faceValue() < cP2.faceValue()) {
			out.println("Player 2: " + cP2.faceValue() + " is higher.");
			p2.addCardToBottom(cP1);
			p2.addCardToBottom(cP2);
		}
		
		if (cP1.faceValue() == cP2.faceValue()) {
			out.println("Face values are equal.");
		}
		
		out.println();
		out.println("Player 1 Hand: " + p1.size());
		p1.printHand(true);
		out.println();
		out.println("Player 2 Hand: " + p2.size());
		p2.printHand(true);
		out.println();
	}
	
	/**
	 * Tests player.
	 */
	public static void testPlayer() {
		
		testHeading("Player interface");
		
	}
	
	/**
	 * Prints test heading.
	 * @param s heading text
	 */
	public static void testHeading(String s) {
		
		out.println();
		out.println("-----------------------------------------------");
		out.println("Testing " + s + "...");
		out.println("-----------------------------------------------");
		out.println();
	}
}
