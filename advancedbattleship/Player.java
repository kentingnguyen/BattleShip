package advancedbattleship;

abstract class Player {

	Ship[] fleet;
	Board board;
	String control = "human";
	String _name;
	UI _ui;

	abstract int[] aim();
	abstract void putShips(Ship s);

	Player(String name, UI inter) {
		_name = name;
		_ui = inter;
	}

	void initializeBoard() {

		Ship lander = new Ship("lander", 2);
		Ship cruiser = new Ship("cruiser", 3);
		Ship sub = new Ship("sub", 3);
		Ship battleship= new Ship("battleship", 4);
		Ship carrier = new Ship("carrier", 5);
		fleet = new Ship[]{lander, cruiser, sub, battleship, carrier};
		board = new Board();
		_ui.reportBoard(this);
		putShips(lander);
		_ui.reportBoard(this);
		putShips(cruiser);
		_ui.reportBoard(this);
		putShips(sub);
		_ui.reportBoard(this);
		putShips(battleship);
		_ui.reportBoard(this);
		putShips(carrier);
		_ui.reportBoard(this);
	}

	String shot(int xcoord, int ycoord) {
		return board.shot(xcoord, ycoord);
	}

	boolean isAlive() {
		return fleet.length != 0;
	}

	String name() {
		return _name;
	}

	UI ui() {
		return _ui;
	}
	
	Board getBoard() {
		return board;
	}
	}
