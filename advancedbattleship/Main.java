package advancedbattleship;
public class Main {

	static boolean wantPlayGame = true;
	
	public static void main(String[] args) {


		
		while (wantPlayGame) {
			Game helper = new Game();
			helper.play();
		}
	}

	static void stopGame() {
		wantPlayGame = false;
	}

}
