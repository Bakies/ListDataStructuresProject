package es.baki.dsp4;

/**
 *
 *
 * @author Jon Bakies, Jared Conroy
 *
 */
public class Player {
	private Pile hand;
	private int id;
	private String name;
	private int score;

	public Player(String pName, int pId) {
		hand = new Pile();
		id = pId;
		name = pName;
		score = 0;
	}

	public Pile getHand() {
		return hand;
	}

	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public void incScore() {
		score++;
	}

}
