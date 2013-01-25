package guibattleship;

public class AI extends Player {

	AI(GuiGame g) {
	super(g);
	}
	
	void setupShips(Ship s) {
		/*String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
		String xcoord = letters[(int) ((Math.random()*343)%7)];
		int ycoord = (int) ((Math.random()*343)%7);
		String coords = xcoord + "" + ycoord;
		boolean rotated = (int) ((Math.random()*1000)%2) == 1;
		if (!super.getBoard().checkShip(s, coords, rotated)) {
			putShips(s);
		} else {
			if (((xcoord + s.size()) > Board.boardSize) || ((ycoord + s.size()) > Board.boardSize)) {
				putShips(s);
			} else {
				super.getBoard().addShip(s, coords, rotated);
			}*/
		}
	

	int[] aim() {
int[] xycoords = new int[2];
		xycoords[0] = (int) ((Math.random()*343)%7);
		xycoords[1]= (int) ((Math.random()*343)%7);
		return xycoords;

		
		
	}
}
