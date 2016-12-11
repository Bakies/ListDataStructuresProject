package es.baki.dsp4;

import java.util.Scanner;

/**
 *
 *
 * @author Jon Bakies, Jared Conroy
 *
 */
public class Game {
	private Player p1;
	private Player p2;
	private Deck d = Deck.newShuffeledDeck();
	private boolean current; // true for p1, false for p2
	private static Scanner s = new Scanner(System.in);;

	public static void main(String[] args) {
		System.out.println("Let's play GO FISH!");
		System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%n", "Four cards required to make a book (earn a point)",
				"Ask for any card", "Play until the deck is gone", "Get another turn if you correctly fish a card",
				"Highest Score Wins", "Have fun!");
		System.out.print("Enter Player 1's Name: ");
		String p1name = s.nextLine();
		System.out.print("Enter Player 2's Name: ");
		String p2name = s.nextLine();
		System.out.println("\n\nLet's play!");
		Game g = new Game(new Player(p1name, 1), new Player(p2name, 2));
		g.startGame();

		System.out.println("Final Scores: ");
		System.out.printf("%s got %d points%n", g.p1.getName(), g.p1.getScore());
		System.out.printf("%s got %d points%n", g.p2.getName(), g.p2.getScore());

	}

	public Game(Player player1, Player player2) {
		p1 = player1;
		p2 = player2;
		d = Deck.newShuffeledDeck();
		deal();
		current = true;
	}

	public void startGame() {
		while (d.getSize() > 0)
			turn();
	}

	public void deal() {
		while (p1.getHand().getSize() < 7)
			p1.getHand().addToTop(d.drawCard());

		while (p2.getHand().getSize() < 7)
			p2.getHand().addToTop(d.drawCard());
	}

	public void turn() {
		boolean turn = true;
		for (int x = 0; x < 50; x++)
			System.out.println();
		Player p, o;
		if (current) {
			p = p1;
			o = p2;
		} else {
			p = p2;
			o = p1;
		}
		if (p.getHand().getSize() == 0)
			for (int x = 0; x < 7; x++) {
				Card draw = d.drawCard();
				System.out.println("Received a " + draw);
				p.getHand().addToTop(draw);

			}

		System.out.println(p.getName() + "'s turn. Your score: " + p.getScore());
		System.out.println("Your hand:\n" + p.getHand());
		while (turn) {
			boolean validInput = false;
			int digit = -1;
			while (!validInput) {
				System.out.print("What card do you want to ask for? ");
				String input = s.nextLine();
				try {
					digit = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					digit = Card.faceToInt(input);
				}
				if (digit <= 0 || digit >= 14)
					System.out.println("Couldnt understand, try 7 or seven");
				else
					validInput = true;

			}

			Card temp = o.getHand().contains(digit);
			if (temp == null) {
				System.out.println("Go Fish!");
				Card r = d.drawCard();
				System.out.println("Received a " + r);
				p.getHand().addToTop(r);
				turn = false;
			} else
				while (temp != null) {
					System.out.println("Received " + temp + " from " + o.getName());
					o.getHand().remove(temp);
					p.getHand().addToTop(temp);
					temp = o.getHand().contains(digit);
				}
			if (p.getHand().checkForBook()) {
				System.out.println("Made a book!");
				p.incScore();
				System.out.println("Your score is now " + p.getScore());
			}

		}
		System.out.println();
		System.out.println(o.getName() + "'s turn will start in 5 seconds");
		try

		{
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		current = !current;
	}
}
