package es.baki.dsp4;

/**
 *
 *
 * @author Jon Bakies, Jared Conroy
 *
 */
public class Card implements Comparable<Card> {
	private String face, suit;
	private Card above, below;
	private int faceValue;

	public Card(String face, String suit) {
		this.face = face;
		this.suit = suit;

		this.faceValue = faceToInt(face);
	}

	public Card(int face, String suit) {
		this.faceValue = face;
		this.suit = suit;
		this.face = intToFace(face);
	}

	public Card getAbove() {
		return above;
	}

	public void setAbove(Card above) {
		this.above = above;
	}

	public Card getBelow() {
		return below;
	}

	public void setBelow(Card below) {
		this.below = below;
	}

	public int getFaceValue() {
		return faceValue;
	}

	@Override
	public String toString() {
		return String.format("%s of %s", face, suit);
	}

	@Override
	public int compareTo(Card o) {
		return this.faceValue - o.faceValue;
	}

	public static String intToFace(int face) {
		switch (face) {
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		}
		return null;
	}

	public static int faceToInt(String face) {
		int faceValue;
		if (face.equalsIgnoreCase("Ace"))
			faceValue = 1;
		else if (face.equalsIgnoreCase("Two"))
			faceValue = 2;
		else if (face.equalsIgnoreCase("Three"))
			faceValue = 3;
		else if (face.equalsIgnoreCase("Four"))
			faceValue = 4;
		else if (face.equalsIgnoreCase("Five"))
			faceValue = 5;
		else if (face.equalsIgnoreCase("Six"))
			faceValue = 6;
		else if (face.equalsIgnoreCase("Seven"))
			faceValue = 7;
		else if (face.equalsIgnoreCase("Eight"))
			faceValue = 8;
		else if (face.equalsIgnoreCase("Nine"))
			faceValue = 9;
		else if (face.equalsIgnoreCase("Ten"))
			faceValue = 10;
		else if (face.equalsIgnoreCase("Jack"))
			faceValue = 11;
		else if (face.equalsIgnoreCase("Queen"))
			faceValue = 12;
		else if (face.equalsIgnoreCase("King"))
			faceValue = 13;
		else
			faceValue = -1;

		return faceValue;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getFace() {
		return this.face;
	}

}
