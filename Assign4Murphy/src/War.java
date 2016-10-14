import static java.lang.System.out;

/**
 * War card game logic.
 * @author Casey J. Murphy
 * @version 0.9
 */
public class War {

	private static final int NUMPLAYERS = 2;
	private static final int BCARDS = 5;
	
	private static int round = 0;
	private static int war = 0;
	
	private Player p1;
	private Player p2;
	private StandardCard[] holder1 = new StandardCard[BCARDS];
	private StandardCard[] holder2 = new StandardCard[BCARDS];
	private int lastIndexHolder;
	
	/**
	 * Creates War game with 2 default players.
	 */
	public War() {
		
		p1 = new Player();
		p2 = new Player();
	}
	
	/**
	 * Creates War game with 2 named players.
	 * @param name1 player 1 name
	 * @param name2 plater 2 name
	 */
	public War(String name1, String name2) {
		
		p1 = new Player(name1);
		p2 = new Player(name2);
	}
	
	/**
	 * Returns number of players.
	 * @return number of players
	 */
	public static int getNumPlayers() {
		
		return NUMPLAYERS;
	}
	
	/**
	 * Returns number of rounds played during game.
	 * @return round total number of rounds
	 */
	public static int getNumRounds() {
		
		return round;
	}
	/**
	 * Returns total number of battles ("wars") fought.
	 * @return war total number of wars
	 */
	public static int getNumWars() {
		
		return war;
	}
	
	/**
	 * Deals a game of War.
	 */
	public void deal() {
		
		int dealRounds = WarHand.getDefHandSize() / 2 + 1;
		StandardDeck d = new StandardDeck();
		d.shuffle();

		for (int player = 0; --dealRounds > 0; player = (1 - player)) {
		
			p1.addCardToHand(d.getCard());
			p2.addCardToHand(d.getCard());
		}
	}
	
	/**
	 * Drives a single round of War.
	 * @param demo true if in demo mode, false otherwise
	 */
	public void round(boolean demo) {
		
		round++;
		out.println();
		out.println(p1.getName() + "    \t" + p2.getName());
		out.println(p1.getHandSize() + " cards \t" + p2.getHandSize() + " cards");
		
		StandardCard temp1 = p1.flipCard();
		StandardCard temp2 = p2.flipCard();
		
		if (demo) {
			temp1.showCard(true);
			out.print("\t\t");
			temp2.showCard(true);
		}
		
		out.println();
		
		if (!demo) {
		
			StandardCard cardsInPlay[] = {temp1, temp2};
			WarHand.drawCardsPic(cardsInPlay);
		}
		
		compare(temp1, temp2);
	}
	
	/**
	 * Compares the cards in play.  Adds cards to winner if there is no tie.
	 * If there is a tie, calls battle.  Returns -1 if player 2 is higher, 
	 * 1 if player 1 is higher, and 0 if there is a tie.
	 * @param temp1 player 1 card
	 * @param temp2 player 2 card
	 * @return result of comparison
	 */
	public int compare(StandardCard temp1, StandardCard temp2) {
		
		switch (temp1.compareTo(temp2)) {
		
		case -1 :
			out.println();
			out.println(p2.getName() + " wins the round!");
			out.println("---");
			out.println();
			p2.addToBottom(temp1);
			p2.addToBottom(temp2);
			return -1;
			
		case 0 :
			out.println();
			battle(temp1, temp2, false);
			return 0;
			
		case 1 : 
			out.println();
			out.println(p1.getName() + " wins the round!");
			out.println("---");
			out.println();
			p1.addToBottom(temp1);
			p1.addToBottom(temp2);
			return 1;
			
		default :
			out.println("Unknown error.  Terminating...");
			break;
		}
		//TODO annotate
		return -999999;
	}
	
	/**
	 * Initiates battle for a War tie.  Checks for last card.
	 * NOTE:  This is a mess, and needs further analysis and revision.
	 * Bugs out and crashes sometimes.
	 * @param t1 player 1 card
	 * @param t2 player 2 card
	 * @param rCall true for recursive call from battleResults, false otherwise
	 */
	public void battle(StandardCard t1, StandardCard t2, boolean rCall) {
		
		war++;
		out.println("---");
		out.println("TIE: This means WAR!  Let the battle commence...\n");
		
		StandardCard[] p1Cards = new StandardCard[BCARDS];
		StandardCard[] p2Cards = new StandardCard[BCARDS];
		
		int lastCardIndex = 0;
		boolean lastCardFlag = false;
		
		p1Cards[0] = t1;
		p2Cards[0] = t2;
		
		for (int i = 1; i < BCARDS; i++) {
		
			lastCardIndex = i;
			
			// TODO check for last card
			if (p1.lastCard() || p2.lastCard()) {
				
				lastCardFlag = true;
				out.println("---------------------------");
				if (p1.lastCard()) out.println(p1.getName() + "'s last card!");
				else out.println(p2.getName() + "'s last card!");
				out.println("---------------------------");
				
				p1Cards[i] = p1.flipCard();
				p2Cards[i] = p2.flipCard();
				p1Cards[i].showCard(true);
				out.print("\t\t");
				p2Cards[i].showCard(true);
				out.print("\n");
				break;
			}
						
			p1Cards[i] = p1.flipCard();
			p2Cards[i] = p2.flipCard();
			
			// print face down
			if (i < BCARDS - 1) {
				
				p1Cards[i].showCard(false);
				out.print("\t\t");
				p2Cards[i].showCard(false);
				out.print("\n");
			}
			
			// print face up
			else if (i == BCARDS - 1) {
				
				p1Cards[i].showCard(true);
				out.print("\t\t");
				p2Cards[i].showCard(true);
				out.print("\n");
			}
		}
		
		holder1 = StandardCard.copy(p1Cards);
		holder2 = StandardCard.copy(p2Cards);
		lastIndexHolder = lastCardIndex;
		
		int result = compare(p1Cards[lastCardIndex], p2Cards[lastCardIndex]);
		StandardCard[] spoils = combineBattleCards(lastCardIndex, p1Cards, p2Cards);
		battleResult(result, spoils, p1Cards[lastCardIndex], p2Cards[lastCardIndex]);
		
		if (rCall && !(lastCardFlag)) {
			
			spoils = combineBattleCards(lastIndexHolder, holder1, holder2);
			battleResult(result, spoils, holder1[lastIndexHolder], holder2[lastIndexHolder]);
		}
	}
	
	/**
	 * Combines all battle cards from both players' stacks that have 
	 * not already been added.  Returns those cards.
	 * NOTE: problem from battle may be in here.
	 * @param lastIndex count from battle of the last index that has a card
	 * @param t1 stack from player 1
	 * @param t2 stack from player 2
	 * @return combined cards
	 */
	public StandardCard[] combineBattleCards(int lastIndex, StandardCard[] t1, StandardCard[] t2) {
		
		StandardCard[] cardsWon = new StandardCard[lastIndex * 2];
		
		for (int i = 0; i < t1.length - 1; i++) {
			if (t1[i] == null) continue;
			cardsWon[i] = t1[i];
		}
		
		for (int i = t1.length - 1; i < cardsWon.length; i++) {
			if (t2[i - (t1.length - 1)] == null) continue;
			cardsWon[i] = t2[i - (t1.length - 1)];			
		}
		
		return cardsWon;
	}
	
	/**
	 * Adds the won stack to winning player.  Checks for another battle.
	 * If there is another battle, calls battle.
	 * NOTE: problem from battle may be in here.
	 * @param result result from calling battle
	 * @param spoils cards to be won
	 * @param t1 player 1 card
	 * @param t2 player 2 card
	 */
	public void battleResult(int result, StandardCard[] spoils, StandardCard t1, StandardCard t2) {
		
		switch(result) {
		
		case -1 :
			for (int i = 0; i < spoils.length; i++) {
				if (spoils[i] == null) continue;
				p2.addToBottom(spoils[i]);
			}
			break;
			
		case 0 : 
			// recursive call
			battle(t1, t2, true);
			break;
			
		case 1 :
			for (int i = 0; i < spoils.length; i++) {
				if (spoils[i] == null) continue;
				p1.addToBottom(spoils[i]);
			}
			break;
			
		default : 
			out.println("Unknown error.  Terminating...");
			//TODO annotate
			System.exit(2);
		}
	}
	
	/**
	 * Checks for winner by calling getWinner.  
	 * Prints notification if there is one.
	 * @return true if there is one, false otherwise
	 */
	public boolean winCheck() {
		
		Player winner = getWinner();
		if (winner == null) return false;
		out.println(winner.getName() + " WINS THE GAME!!!");
		return true;
	}
	
	/**
	 * Checks for winner.  Returns the winner if there is one.
	 * Returns null if there is not.
	 * @return winning player or null if there is not one
	 */
	public Player getWinner() {
		
		if (p2.getHandSize() == 0) return p1;
		else if (p1.getHandSize() == 0) return p2;
		else return null;
	}
}
