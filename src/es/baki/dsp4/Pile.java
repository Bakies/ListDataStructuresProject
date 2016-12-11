package es.baki.dsp4;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 *
 * @author Jon Bakies, Jared Conroy
 *
 */
public class Pile {
	private Card topCard, bottomCard;
	private int numCards;

	public Pile(Card topCard) {
		this.topCard = topCard;
		Card c = topCard;
		while (c.getBelow() != null) {
			numCards++;
			c = c.getBelow();
		}
		bottomCard = c;
	}

	public Pile() {
	}

	public Card contains(int face) {
		Card deckCard = topCard;
		while (deckCard != null) {
			if (deckCard.getFaceValue() == face)
				return deckCard;
			deckCard = deckCard.getBelow();
		}
		return null;
	}

	public void addToTop(Card c) {
		if (c == null)
			return;
		c.setAbove(null);
		c.setBelow(null);
		if (topCard == null) {
			c.setAbove(null);
			c.setBelow(null);
			bottomCard = c;
			topCard = c;
			numCards = 1;
			return;
		}
		topCard.setAbove(c);
		c.setBelow(topCard);
		c.setAbove(null);

		numCards++;
		topCard = topCard.getAbove();
	}

	public void addToBottom(Card c) {
		if (c == null)
			return;
		c.setAbove(null);
		c.setBelow(null);
		if (bottomCard == null) {
			c.setAbove(null);
			c.setBelow(null);
			bottomCard = c;
			topCard = c;
			numCards = 1;
			return;
		}
		bottomCard.setBelow(c);
		c.setAbove(bottomCard);
		c.setBelow(null);

		bottomCard = c;
		numCards++;
	}

	public Card getTopCard() {
		return topCard;
	}

	public Card drawCard() {
		Card c = topCard;
		topCard = topCard.getBelow();

		numCards--;

		return c;
	}

	public int getSize() {
		return numCards;
	}

	public Card getBottomCard() {
		return bottomCard;
	}

	public void addPileToBottom(Pile p) {
		Card c;
		while ((c = p.drawCard()) != null)
			this.addToBottom(c);
	}

	public void shuffle() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card c = topCard;
		while (c != null) {
			cards.add(c);
			c = c.getBelow();
		}
		Collections.shuffle(cards);
		topCard = cards.get(0);
		c = topCard;
		for (int x = 1; x < cards.size(); x++) {
			c.setBelow(cards.get(x));
			c = c.getBelow();
		}
	}

	/**
	 * returns true if a card was removed
	 *
	 * @param c
	 * @return
	 */
	public boolean remove(Card c) {
		Card deckCard = topCard;
		while (deckCard != null) {
			if (c.getFaceValue() == deckCard.getFaceValue() && c.getSuit().equals(deckCard.getSuit())) {

				Card above, below;
				above = deckCard.getAbove();
				below = deckCard.getBelow();

				if (deckCard == topCard)
					topCard = topCard.getBelow();
				if (deckCard == bottomCard)
					bottomCard = bottomCard.getAbove();

				if (below != null)
					below.setAbove(above);

				if (above != null)
					above.setBelow(below);
				numCards--;
				return true;
			}

			deckCard = deckCard.getBelow();
		}
		return false;

	}

	public boolean checkForBook() {
		for (int x = 1; x <= 13; x++)
			if (checkForBook(x))
				return true;
		return false;
	}

	public boolean checkForBook(int faceValue) {
		int count = 0;
		Card deckCard = topCard;
		while (deckCard != null) {
			if (deckCard.getFaceValue() == faceValue)
				count++;
			deckCard = deckCard.getBelow();
		}
		if (count == 4) {
			this.remove(this.contains(faceValue));
			this.remove(this.contains(faceValue));
			this.remove(this.contains(faceValue));
			this.remove(this.contains(faceValue));
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Card c = topCard;
		while (c != null) {
			sb.append(c);
			sb.append("\n");
			c = c.getBelow();

		}
		return sb.toString();
	}

	public static void main(String... strings) {
		Pile p = new Pile();

		Card c1, c2, c3;
		c1 = new Card(1, "Spades");
		c2 = new Card(10, "Diamonds");
		c3 = new Card(2, "Clubs");

		p.addToTop(c3);
		p.addToTop(c2);
		p.addToTop(c1);

		p.remove(new Card(1, "Spades"));

		System.out.println(p);
		System.out.println(c1 + " : " + c1.getAbove() + " : " + c1.getBelow());
		System.out.println(c2 + " : " + c2.getAbove() + " : " + c2.getBelow());
		System.out.println(c3 + " : " + c3.getAbove() + " : " + c3.getBelow());
	}

}
