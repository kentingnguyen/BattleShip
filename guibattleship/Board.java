package guibattleship;
public class Board {

	Ship[][] shipBoard;
	boolean[][] shotBoard;
	static int boardSize = 7;

	/**Construct a Board.*/
	public Board() {
		shipBoard = new Ship[boardSize][boardSize];
		shotBoard = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				shipBoard[i][j] = null; 	
				shotBoard[i][j] = false;
			}
		}
	}


	/**Assuming that all parameters are correct, insert a ship of SIZE, starting at XCOORD, YCOORD.  
	 * Default is horizontal, else if ROTATED, vertical.
	 * @param xcoord
	 * @param ycoord
	 * @param size
	 * @param rotated
	 */
	void addShip(Ship s, String coord, boolean rotated) {
		int size = s.size();
		int[] coords = Main.convertToNumer(coord);
		int rowcoord = coords[0];
		int colcoord = coords[1];
		if (rotated) {
			for (int i = 0; i < size; i++) {
				shipBoard[rowcoord + i][colcoord] = s;
			}
		} else {
			for (int j = 0; j < size; j++) {
				shipBoard[rowcoord][colcoord + j] = s;
			}
		}
	}

	boolean checkShip(Ship s, int xcoord, int ycoord, boolean rotated) {
		int size = s.size();
		if (rotated) {
			for (int i = 0; i < size; i++) {
				if (shipBoard[ycoord + i][xcoord] != null) {
					return false;
				}
			}
			return true;
		} else {
			for (int j = 0; j < size; j++) {
				if (shipBoard[ycoord][xcoord + j] != null) {
					return false;
				}
			}
			return true;
		}
	}


	int shot(int rowcoord, int colcoord) {
		shotBoard[rowcoord][colcoord] = true;
		Ship currentShot = shipBoard[rowcoord][colcoord];
		if (currentShot != null) {
			currentShot.hit();
			shipBoard[rowcoord][colcoord] = null;
			if (currentShot.checkSink()) {
				return currentShot.id();
			} else {
				return -1;
			}
		} else {
			return -2;
		}
	}

Ship[][] getShipBoard() {
	return shipBoard;
}
boolean[][] getShotBoard() {
	return shotBoard;
}

}

//0 means empty
//1 means ship there
//2 means hit and sink