package guibattleship;

 interface UI {

	String start();
	String next();
	void report(String s);
	void reportError(String s);
	int[] queryShipCoords(Ship s);
	int[] queryShotCoords();	
	void reportBoard(Player p);
	
}