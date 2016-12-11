package es.baki.dsp4;

/**
 *
 *
 * @author Jon Bakies, Jared Conroy
 *
 */
public class Deck extends Pile {

	private Deck() {
	}

	public void Deal(Deck d, int cards) {
		for (int x = 0; x < cards; x++)
			d.addToBottom(this.getTopCard());
	}

	public void split() {
		Card c = getTopCard();
		for (int x = 0; x <= this.getSize(); x++)
			c = c.getBelow();
		c.setAbove(null);
		getTopCard().setAbove(getBottomCard());

	}

	public static Deck newSortedDeck() {
		Deck d = new Deck();
		for (int x = 1; x <= 13; x++)
			d.addToBottom(new Card(x, "Spades"));
		for (int x = 1; x <= 13; x++)
			d.addToBottom(new Card(x, "Diamonds"));
		for (int x = 1; x <= 13; x++)
			d.addToBottom(new Card(x, "Clubs"));
		for (int x = 1; x <= 13; x++)
			d.addToBottom(new Card(x, "Hearts"));

		return d;
	}

	public static Deck newShuffeledDeck() {
		Deck d = newSortedDeck();
		d.shuffle();
		return d;
	}

}
