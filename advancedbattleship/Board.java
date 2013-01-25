package advancedbattleship;
public class Board {

	Ship[][] playingBoard;
	static int boardSize = 7;

	/**Construct a Board.*/
	public Board() {
		playingBoard = new Ship[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				playingBoard[i][j] = null; 	
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
	void addShip(Ship s, int xcoord, int ycoord, boolean rotated) {
		int size = s.size();
		if (rotated) {
			for (int i = 0; i < size; i++) {
				playingBoard[ycoord + i][xcoord] = s;
			}
		} else {
			for (int j = 0; j < size; j++) {
				playingBoard[ycoord][xcoord + j] = s;
			}
		}
	}

	boolean checkShip(Ship s, int xcoord, int ycoord, boolean rotated) {
		int size = s.size();
		if (rotated) {
			for (int i = 0; i < size; i++) {
				if (playingBoard[ycoord + i][xcoord] != null) {
					return false;
				}
			}
			return true;
		} else {
			for (int j = 0; j < size; j++) {
				if (playingBoard[ycoord][xcoord + j] != null) {
					return false;
				}
			}
			return true;
		}
	}


	String shot(int xcoord, int ycoord) {
		Ship currentShot = playingBoard[ycoord][xcoord];
		if (currentShot != null) {
			currentShot.hit();
			playingBoard[ycoord][xcoord] = null;
			if (currentShot.checkSink()) {
				return "sunk a " + currentShot.type() + "!";
			} else {
				return "hit!";
			}
		} else {
			return "miss";
		}
	}

Ship[][] boardToArray() {
	return playingBoard;
}
}

//0 means empty
//1 means ship there
//2 means hit and sink