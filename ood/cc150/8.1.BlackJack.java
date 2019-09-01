package ood;

/**
 * BlackJack
 *
 * 题目是设计纸牌的数据结构，并且实现blackjack游戏
 *
 * 首先确认设计为标准可拓展的cards
 */

public class BlackJack {
}
/**
 * 1 Suit 纸牌有四种类型
 */
enum Suit {
    Club(0),
    Diamon(1),
    Heart(2),
    Spade(3);

    private int value;
    private Suit(int v) {value = v;}
    public int getValue {return value;}

    public static Suit getSuitFromValue(int value) {
    	switch(value) {
    		case 0:
    			return Suit.Club;
    		case 1:
				return Suit.Diamon;
			case 2 :
				return Suit.Heart;
			case 3:
				return Suit.Spade;
			default:
				return null;
    	}
    }
}

/**
 * 2 Card 
 */
public abstract class Card {
	protected int faceValue;
	protected Suit suit;
	private boolean available = true;

	public Card(int c, Suit s) {
		faceValue = c;
		suit = s;
	}

	public abstract int value();
	public Suit suit() {return suit;}
	public boolean isAvailable() {return available;}
	public void markUnAvailable() {available = false;}
	public void markAvailable() {available = true;}
	public void print() {
		String[] faceValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		System.out.print(faceValues[faceValue - 1]);
		switch (suit) {
			case Club:
				System.out.print("c");
				break;
			case Heart:
				System.out.print("h");
				break;
			case Diamond:
				System.out.print("d");
				break;
			case Spade:
				System.out.print("s");
				break;			
		}
		System.out.print(" ");
	}
}

public class Deck<T extends Card> {
	private ArrayList<T> cards;
	private int dealtIndex = 0;

	public Deck(){}
	public void setDeckOfCards(ArrayList<T> deckOfCards) {
		cards = deckOfCards;
	}
	// 洗牌
	public void shuffle() {
		for (int i=0; i<cards.size(); i++) {
			int j = randomIntInRange(i, cards.size()-i-1);
			T card1 = cards.get(i);
			T card2 = cards.get(j);
			cards.set(i, card2);
			cards.set(j, card1);
		}
	}
	public int remainingCards() {
		return cards.size() - dealtIndex;
	}
	// 发牌
	public T[] dealHand(int number) {
		if (remainingCards() < number) return null;
		T[] hand = (T[]) new Card[number];
		int count = 0;
		while (count < number) {
			T card = dealCard();
			if (card != null) {
				hand[count] = card;
				count++;
			}
		}
		return hand;
	}
	public T dealCard() {
		if (remainingCards() == 0) return null;
		T card = cards.get(dealtIndex);
		card.markUnAvailable();
		dealtIndex++;
		return card;
	}

	public void print() {
		for (Card card : cards) {
			card.print();
		}
	}
	public static int randomInt(int n) {
		return (int)(Math.random()*n);
	}
	public static int randomIntInRange(int min, int max) {
		return randomInt(max+1-min) + min;
	}
}

// Card in Hand for player
public class Hand <T extends Card> {
	protected ArrayList<T> cards = new ArrayList<T>();

	public int score() {
		int score = 0;
		for (T card : cards) {
			score += card.value();
		}
		return score;
	}

	public void addCard(T card) {
		cards.add(card);
	}

	public void print() {
		for (Card card : cards) card.print();
	}
}

// for blackJack game face cards are 10 and ace is 11
public class BlackJackCard extends Card {
	public BlackJackCard(int c, Suit s) {
		super(c, s);
	}

	public int value() {
		if (isAce()) {
			return 1;
		} else if (faceValue >= 11 && faceValue <= 13) {
			return 10;
		} else {
			return faceValue;
		}
	}

	public int minValue() {
		if (isAce()) return 1;
		else return value();
	}

	public int maxValue() {
		if (isAce()) return 11;
		else return value();
	}

	public boolean isAce() {
		return faceValue == 1;
	}

	public boolean isFaceCard() {
		return faceValue>=11 && faceValue<=13;
	}
}

public class BlackJackHand extends Hand<BlackJackCard> {
	BlackJackHand() {}

	public int score() {
		ArrayList<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE;
		int minOver = Integer.MAX_VALUE;
		for (int score : scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}

	private ArrayList<Integer> possibleScores() {
		ArrayList<Integer> scores = new ArrayList<>();
		if (cards.size() == 0) return scores;
		for (BlackJackCard card : cards) {
			addCardToScoreList(card, scores);
		}
		return scores;
	}

	private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
		if (scores.size() == 0) {
			scores.add(0);
		}
		int length = scores.size()
		for (int i=0; i<length; i++) {
			int score = scores.get(i);
			scores.set(i, score + card.minValue());
			if (card.minValue() != card.maxValue()) {
				scores.add(score + card.maxValue());
			}
		}
	}

	public boolean busted() {return score()>21;}
	public boolean is21() {return score()==21;}
	public boolean isBlackJack() {
		if (cards.size() != 2) return false;
		BlackJackCard first = cards.get(0);
		BlackJackCard second = cards.get(1);
		return (first.isAce() && second.isFaceCard()) ||
			(second.isAce() && first.isFaceCard());
	}
}

/**
 * Core part: the game system
 */
public class BlackJackGameAutomator {
	private Deck<BlackJackCard> deck;
	private BlackJackHand[] hands;
	private static final int HIT_UNTIL = 16;

	public BlackJackGameAutomator(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for (int i=0; i<numPlayers; i++) {
			hands[i] = new BlackJackHand();
		}
	}

	public boolean dealInitial() {
		for (BlackJackHand hand : hands) {
			BlackJackCard card1 = deck.dealCard();
			BlackJackCard card2 = deck.dealCard();
			if (card1==null || card2==null) {
				return false;
			}
			hand.addCard(card1);
			hand.addCard(card2);
		}
		return true;
	}

	public ArrayList<Integer> getBlackJacks() {
		ArrayList<Integer> winners = new ArrayList<>();
		for (int i=0; i<hands.length; i++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}

	public boolean playHand(int i) {
		BlackJackHand hand = hands[i];
		return playHand(hand);
	}
	public boolean playHand(BlackJackHand hand) {
		while (hand.score() < HIT_UNTIL) {
			BlackJackCard card = deck.dealCard();
			if (card==null) return false;
			hand.addCard(card);
		}
		return true;
	}
	public boolean playAllHands() {
		for (BlackJackHand hand : hands) {
			if (!playHand(hand)) return false;
		}
		return true;
	}

	public ArrayList<Integer> getWinners() {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		int winningScore = 0;
		for (int i=0; i<hands.length; i++) {
			BlackJackHand hands = hands[i];
			if (!hand.busted()) {
				if (hand.score > winningScore) {
					winningScore = hand.score;
					winners.clear();
					winners.add(i);
				}
			} else if (hand.score == winningScore) {
				winners.add(i);
			}
		}
		return winners;
	}

	public void initializeDeck() {
		ArrayList<BlackJackCard> cards = new ArrayList<BlackJackCard>;

		for (int i=1; i<=13; i++) {
			for (int j=0; j<=3; j++) {
				Suit suit = Suit.getSuitFromValue(j);
				BlackJackCard card = new BlackJackCard(i, suit);
				cards.add(card);
			}
		}

		deck = new Deck<BlackJackCard>();
		deck.setDeckOfCards(cards);
		deck.shuffle();
	}

	public void printHandsAndSore() {
		for (int i=0; i<hands.length; i++) {
			System.out.println("Hand "+i+" ("+
				hand[i].score()+"): "+
				hands[i].print()
				);
			System.out.println("");
		}
	}
}


// test function
public static void main(String[] args) {
	int numHands = 5;

	BlackJackGameAutomator automator = new BlackJackGameAutomator(numHands);
	boolean success = automator.dealInitial();

	if (!success) {
		System.out.println("Error. Out of cards.");
	} else {
		System.out.println("-- Initial --");
		automator.printHandsAndSore();

		ArrayList<Integer> blackjacks = automator.getBlackJacks();
		if (blackjacks.size() > 0) {
			System.out.print("Blackjack at ");
            for (int i : blackjacks) {
                 System.out.print(i + ", ");
            }
            System.out.println("");
		} else {
			success = automator.playAllHands();
			if (!success) {
				 System.out.println("Error. Out of cards.");
			} else {
				System.out.println("\n-- Completed Game --");
				automator.printHandsAndSore();

				ArrayList<Integer> winners = automator.getWinners();
				if (winners.size() > 0) {
					System.out.print("Winners: ");
					for (int i : winners) {
						System.out.print(i + ", ");
					}
					System.out.println("");
				} else {
					System.out.println("Draw. All players have busted.");
				}
			}
		}
	}
}
