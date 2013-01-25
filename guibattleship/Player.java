package guibattleship;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

abstract class Player {

	private static String[] allFleet = {"lander", "cruiser", "sub", "battleship", "carrier"};
	Ship[] fleet;
	Ship lander;
	Ship cruiser;
	Ship sub;
	Ship battleship;
	Ship carrier;

	Board board;
	String control = "human";
	String _name;
	GuiGame _ui;

	void addUI(GuiGame g) {
		_ui = g;
	}

	Player(GuiGame g) {

		lander = new Ship(0, "lander", 2);
		cruiser = new Ship(1, "cruiser", 3);
		sub = new Ship(2, "sub", 3);
		battleship= new Ship(3, "battleship", 4);
		carrier = new Ship(4, "carrier", 5);
		fleet = new Ship[]{lander, cruiser, sub, battleship, carrier};
		board = new Board();

	}


	boolean setupShip (String coord, boolean isRotated, int shipType) {
		if (legalCoords(coord, isRotated, fleet[shipType].size())) {
			switch (shipType) {
			case 0:
				board.addShip(lander, coord, isRotated);

				return true;
			case 1:
				board.addShip(cruiser, coord, isRotated);
				return true;
			case 2:
				board.addShip(sub, coord, isRotated);
				return true;
			case 3:
				board.addShip(battleship, coord, isRotated);
				return true;
			case 4:
				board.addShip(carrier, coord, isRotated);
				return true;
			default:
				return true;
			}
		} else {
			return false;
		}
	}

	/**The player P who is getting shot at COORD.  Returns -1 for a hit, -2 for a miss, or the ship number for a sink.*/
	int shot(String coord) {
		int[] coords = Main.convertToNumer(coord);
		int rowcoord = coords[0];
		int colcoord = coords[1];
		int result = board.shot(rowcoord, colcoord);
		if (result >= 0) {
			fleet[result] = null;
		}
			return result;
		}




	boolean legalCoords(String coord, boolean rotated, int shipSize) {
		List<Character> alphas = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G');
		Ship[][] ships = getBoard().getShipBoard();
		if ((coord.length() == 2)) {
			char alphacoord = coord.charAt(0);
			int numbercoord = Character.getNumericValue((coord.charAt(1)));
			if ((alphas.contains(alphacoord)) && (numbercoord > 0) && (numbercoord <= 7)) {
				int alphaToNumer = Main.convertToNumer(alphacoord);				
				if (rotated) {				
					if (alphaToNumer+shipSize <= Board.boardSize) { 
						for (int i =  alphaToNumer; i < alphaToNumer+shipSize; i++) {				
							if (ships[i][numbercoord-1] != null) {
								return false; 
							}
						}
						return true;
					} else {
						return false;
					}		
				} else {				
					if ((numbercoord-1+shipSize <= Board.boardSize)) {
						for (int j = numbercoord-1;j < numbercoord-1+shipSize; j++) {
							if (ships[alphaToNumer][j] != null) {
								return false;
							}
						}
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


boolean isAlive() {
for (int i = 0; i < fleet.length; i++) {
	if (fleet[i] != null) {
		return true;
	}
}
	return false;
}


static String[] getAllFleet() {
	return allFleet;
}

Ship[] getFleet() {
	return fleet;
}


String name() {
	return _name;
}

GuiGame ui() {
	return _ui;
}

Board getBoard() {
	return board;
}
}
