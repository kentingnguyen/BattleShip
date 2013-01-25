package advancedbattleship;

public class Human extends Player {

	Human(String name, UI inter){
		super(name, inter);
		
	}
	

	void putShips(Ship s) {
		int[] xycoords = ui().queryShipCoords(s);
		int xcoord = xycoords[0];
		int ycoord = xycoords[1];
		boolean rotated = (xycoords[2] == 1);
		
		if ((((xcoord + s.size()) > Board.boardSize) && !rotated) || (((ycoord + s.size()) > Board.boardSize) && rotated)) {
			ui().reportError("Invalid placement of ship, try again");
			putShips(s);
		} else {		
			if (!super.board.checkShip(s, xcoord, ycoord, rotated)) {
				ui().reportError("A ship already exists in that area, try again");
				putShips(s);
			} else {
			super.board.addShip(s, xcoord, ycoord, rotated);
		}
	}
}

int[] aim() {
	return ui().queryShotCoords();
}

}


