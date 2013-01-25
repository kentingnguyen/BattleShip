package advancedbattleship;

import java.util.Arrays;
import java.util.List;

class Game {
	Player player1;
	Player player2;
	int numOfGuesses = 0;
	UI ui;

	Game() {
		List<String> beingHuman = Arrays.asList("h", "H", "human", "Human");
		ui = new TextUI();
		String p1control = ui.start();
		Player p1; 
		Player p2; 
		if (beingHuman.contains(p1control)) {
			p1 = new Human("player 1", ui);
		} else {
			p1 = new AI("player 1", ui);
		}
		
		String p2control = ui.next();
		if (beingHuman.contains(p2control)) {
			p2 = new Human("player 2", ui);
		} else {
			p2 = new AI("player 2", ui);
		}

		player1 = p1;
		player2 = p2;
	}

	void play() {
		ui.report("Player 1's turn to put ships in play");
		player1.initializeBoard();
		ui.report("Player 2's turn to put ships in play");
		player2.initializeBoard();
		Player currentplayer = player1;
		while (player1.isAlive() && player2.isAlive()) {
			ui.report(currentplayer.name() + "'s turn");
			takeTurn(currentplayer);
			numOfGuesses++;
			currentplayer = opposite(currentplayer);
		}
	}

	Player opposite(Player p) {
		if (p.equals(player1)) {
			return player2;
		} else {
			return player1;
		}
	}

	void takeTurn(Player p) {
		int[] coords = p.aim();
		ui.report(opposite(p).shot(coords[0], coords[1]));

	}


	void stopGame(){
		Main.stopGame();
	}

}


