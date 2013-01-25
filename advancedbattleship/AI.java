package advancedbattleship;

public class AI extends Player {

	AI(String name, UI inter) {
		super(name, inter);
	}
	
		
	void putShips(Ship s) {
		int xcoord = (int) ((Math.random()*343)%7);
		int ycoord = (int) ((Math.random()*343)%7);
		boolean rotated = (int) ((Math.random()*1000)%2) == 1;
		if (!super.board.checkShip(s, xcoord, ycoord, rotated)) {
			putShips(s);
		} else {
			if (((xcoord + s.size()) > Board.boardSize) || ((ycoord + s.size()) > Board.boardSize)) {
				putShips(s);
			} else {
				super.board.addShip(s, xcoord, ycoord, rotated);
			}
		}
	}

	int[] aim() {
int[] xycoords = new int[2];
		xycoords[0] = (int) ((Math.random()*343)%7);
		xycoords[1]= (int) ((Math.random()*343)%7);
		return xycoords;

		
		
	}
}
