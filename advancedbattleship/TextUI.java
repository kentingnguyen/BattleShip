package advancedbattleship;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.StringBuilder;
class TextUI implements UI {


	/**The scanner that reads from input.*/
	private Scanner in = new Scanner(System.in);



	public String start() {
		System.out.println("Welcome to Battleship.  Please choose either human or ai for player 1.");
		String humanAI = in.nextLine();
		return humanAI;

	}

	public String next() {
		System.out.println("Now please choose either human or ai for player 2.");
		String humanAI = in.nextLine();
		return humanAI;
	}

	public void report(String msg) {
		System.out.println(msg);
	}

	public void reportError(String msg) {
		System.err.println(msg);
	}


	public int[] queryShipCoords(Ship s) {
		final List<Character> alphas = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g');
		int[] shipCoords = new int[3];
		System.out.println("Placing a size " + s.size() + " " + s.type());  
		System.out.println("Please insert an alphabetic letter A-G corresponding to the row," +
				"a number from 1-7 for the column you want to put the ship at," +
				"an r for rotation.");
		String coord = in.nextLine();
		if (coord.length() < 2) {
			reportError("Invalid coordinate, try again");
			return queryShipCoords(s);
		} else {
			char alphacoord = Character.toLowerCase(coord.charAt(0));
			int numbercoord = Character.getNumericValue(coord.charAt(1));
			System.out.println(" you printed " + alphacoord + " " + numbercoord);
			if ((!alphas.contains(alphacoord)) || (numbercoord < 1) || (numbercoord > 7) || (coord.length() < 2)) {
				reportError("invalid coordinate, try again");
				return queryShipCoords(s);
			} else {
				if (coord.length() > 2) {  
					shipCoords[2] = 1;
				} else {
					shipCoords[2] = 0;
				}
				shipCoords[0] = numbercoord - 1;
				switch(alphacoord) {
				case 'a':
					shipCoords[1] = 0;
					break;

				case 'b':
					shipCoords[1] = 1;
					break;
				case 'c':
					shipCoords[1] = 2;
					break;

				case 'd':
					shipCoords[1] = 3;
					break;

				case 'e':
					shipCoords[1] = 4;
					break;

				case 'f':
					shipCoords[1] = 5;
					break;

				case 'g':
					shipCoords[1] = 6;
					break;
				}

				return shipCoords;	
			}
		}
	}

	public int[] queryShotCoords() {
		final List<Character> alphas = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g'); 
		int[] xycoords = new int[2];
		System.out.println("Please insert an alphabetic letter A-G corresponding to the row," +
				"and a number from 1-7 for the column you want to shoot.");
		String coord = in.nextLine();
		char alphacoord = Character.toLowerCase(coord.charAt(0));
		int numbercoord = Character.getNumericValue(coord.charAt(1));
		if ((!alphas.contains(alphacoord)) || (numbercoord < 1) || (numbercoord > 7) || (coord.length() < 2)) {
			reportError("invalid coordinate, try again");

		}
		xycoords[0] = numbercoord - 1;
		switch(alphacoord) {
		case 'a':
			xycoords[1] = 0;
			break;

		case 'b':
			xycoords[1] = 1;
			break;
		case 'c':
			xycoords[1] = 2;
			break;

		case 'd':
			xycoords[1] = 3;
			break;

		case 'e':
			xycoords[1] = 4;
			break;

		case 'f':
			xycoords[1] = 5;
			break;

		case 'g':
			xycoords[1] = 6;
			break;


		}

		return xycoords;	
	}

	public void reportBoard(Player p) {
		Ship[][] ocean = p.getBoard().boardToArray();

		for(int i = 0; i < Board.boardSize; i++) {
			StringBuilder currentLine = new StringBuilder();
			for (int j = 0; j < Board.boardSize; j++) {
				if (ocean[i][j] == null) {
					currentLine.append(0);
				} else {
					//					switch (ocean[i][j].type()) {
					currentLine.append("s");

				}
			}
			System.out.println(currentLine.toString());
		}
	}
}

