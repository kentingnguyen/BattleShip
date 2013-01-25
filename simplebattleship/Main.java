package simplebattleship;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();
		SimpleDotCom theDotCom = new SimpleDotCom();
		int randomNum = (int) (Math.random() * 5);
		ArrayList<String> locations = 
				new ArrayList<String>();

		locations.add(Integer.toString(randomNum));
		locations.add(Integer.toString(randomNum + 1));
		locations.add(Integer.toString(randomNum + 2));
		System.out.println(locations);
		theDotCom.setLocation(locations);
		boolean isAlive = true;
while (isAlive) {
	String guess = helper.getUserInput("enter a number");
	String result = theDotCom.checkYourself(guess);
	System.out.println(result);
	numOfGuesses += 1;
	if (result.equals("kill")) {
		isAlive = false;
	}
}
System.out.println("you took " + numOfGuesses + " guesses");	
}
	}

