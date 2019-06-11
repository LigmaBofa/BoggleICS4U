import java.util.Random;

public class BoggleDice {

	public int orientation;
	public int position;
	public String letters;

	public char getOrientation() {	
		System.out.println("orien: " +orientation);
		return letters.charAt(orientation);
	}

	public void setOrientation(int newOrientation) {
		orientation = newOrientation;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int newPosition) {
		position = newPosition;
	}

	public void rollDie() {
		Random rand = new Random();
		this.setOrientation(rand.nextInt(6));
	}

	public BoggleDice(String possibleLetters, int defaultOrientation) {
		letters = possibleLetters;
	//	position;
		orientation = defaultOrientation;
	}
	public BoggleDice() {
		letters = "AAAAAA";
		orientation = 0;
	}

}