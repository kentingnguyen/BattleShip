package guibattleship;

import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class GuiGame {


	static final int SHIPNUM = 5; 
	JFrame gameFrame;
	JButton endGame;
	Player _p1;
	Player _p2;
	JLabel reports;

	JCheckBox _rotated;
	JPanel p1Panel;
	JPanel p2Panel;

	/**Game status, 1 = player 1 setup, 2 = player 2 setup, 3 = player 1's turn, 4 = player 2's turn, 0 = endgame.*/
	short gameStatus = 1;


	/**Ship type.  0 =.*/ 
	private int shipType = 0;
	JButton[][] p1Buttons; 
	JButton[][] p2Buttons;

	GuiGame(boolean p1h, boolean p2h) {
		if (p1h) {
			_p1 = new Human(this);
		} else {
			_p1 = new AI(this);
		}
		if (p2h) {
			_p2 = new Human(this);
		} else {
			_p2 = new AI(this);
		}
		this.go();
	}

	void playerSwitch() {
		switch (gameStatus) {
		case 1:
			gameStatus = 2;
			break;
		case 2:
			gameStatus = 1;
			break;
		case 3:
			gameStatus = 4;
			break;
		case 4:
			gameStatus = 3;
			break;
		default:
			break;
		}
	}

	Player oppositePlayer(Player p) {
		if (p.equals(_p1)) {
			return _p2;
		} else {
			return _p1;
		}
	}

	String playerName(Player p) {
		if (p.equals(_p1)) {
			return "Player 1";
		} else {
			return "Player 2";
		}
	}

	void reportNext(Player p) {
		String oppositePlayerName = playerName(oppositePlayer(p));
		if (gameStatus == 1 || gameStatus == 2) {
			Ship currentShip = p.getFleet()[shipType];

			report(oppositePlayerName + ", place your ships on the board.  " + currentShip.type() + ": size " + currentShip.size() + ".  ");
		} else {
			report(oppositePlayerName + ", time to Shoot!");
		}
	}


	JButton[][] getButtons(Player p) {
		if (p.equals(_p1)) {
			return p1Buttons;
		} else {
			return p2Buttons;
		}
	}



	void report(String msg) {
		reports.setText(msg);
	}

	void reportAdd(String msg) {
		reports.setText(reports.getText() + " " + msg);
	}


	void reportShot(int result) {
		if (result == -1) {
			report("hit!");

		} else if (result == -2) {
			report("miss!");
		} else {
			String sunkShip = Player.getAllFleet()[result];
			report ("Sunk a " + sunkShip + "!");
		}

	}

	void go() {
		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(1000, 500);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Battleship");
		reports = new JLabel();

		report("Insert a coordinate.  Eg: A2");

		topPanel.add(title);
		topPanel.add(reports);


		JPanel botPanel = new JPanel();
		endGame = new JButton("End Game");
		endGame.addActionListener(new ExitListener());
		botPanel.add(endGame);


		p1Panel = new JPanel();
		p2Panel = new JPanel();
		JPanel p1Panelhelper = new JPanel();
		JPanel p2Panelhelper = new JPanel();
		int area = Board.boardSize;
		p1Buttons = new JButton[area][area];
		p2Buttons = new JButton[area][area];
		GridLayout oceanGrid = new GridLayout(area, area, 2, 2);

		p1Panel.setLayout(new BoxLayout(p1Panel, BoxLayout.Y_AXIS));
		p2Panel.setLayout(new BoxLayout(p2Panel, BoxLayout.Y_AXIS));
		p1Panelhelper.setLayout(oceanGrid);
		p2Panelhelper.setLayout(oceanGrid);

		p1Panel.add(new JLabel("Player 1"));
		p1Panel.add(p1Panelhelper);
		p2Panel.add(new JLabel("Player 2"));
		p2Panel.add(p2Panelhelper);


		for (int i = 0; i < (area); i++) {
			for (int j = 0; j < (area); j++) {
				JButton parcel1 = new JButton(Main.convertToAlpha(i) + "" + (j+1));
				JButton parcel2 = new JButton(Main.convertToAlpha(i) + "" + (j+1));
				parcel1.setActionCommand(Main.convertToAlpha(i) + "" + (j+1));
				parcel2.setActionCommand(Main.convertToAlpha(i) + "" + (j+1));
				p1Panelhelper.add(parcel1);
				p2Panelhelper.add(parcel2);
				parcel1.addActionListener(new gridListener());
				parcel2.addActionListener(new gridListener());
				p1Buttons[i][j] = parcel1;
				p2Buttons[i][j] = parcel2;
			}
		}

		JPanel centerPanel = new JPanel();
		_rotated = new JCheckBox("Place Ship Vertically");
		centerPanel.add(_rotated);

		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		botPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		p1Panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 0));
		p2Panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 40));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


		gameFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
		gameFrame.getContentPane().add(BorderLayout.SOUTH, botPanel);
		gameFrame.getContentPane().add(BorderLayout.WEST, p1Panel);
		gameFrame.getContentPane().add(BorderLayout.EAST, p2Panel);
		gameFrame.getContentPane().add(BorderLayout.CENTER, centerPanel);

		p2Panel.setVisible(false);
		gameFrame.setVisible(true);

		report("Player 1, please put pieces on the board.  Lander: size 2.  ");
	}

	/**Updates the GUI Panel for the Player P.*/
	void updateShip(Player p) {
		Ship[][] board= p.getBoard().getShipBoard();
		JButton[][] buttons = getButtons(p);
		for (int i = 0; i < Board.boardSize; i++) {
			for (int j = 0; j < Board.boardSize; j++) {
				if (board[i][j] != null) {
					//buttons[i][j].setText("Ship");
					buttons[i][j].setEnabled(false);
				} else {
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}

	/**Updates the GUI Panel for the Player P.*/
	void updateShots(Player p) {
		boolean[][] board= p.getBoard().getShotBoard();
		JButton[][] buttons = getButtons(p);
		for (int i = 0; i < Board.boardSize; i++) {
			for (int j = 0; j < Board.boardSize; j++) {
				if (board[i][j]) {
					buttons[i][j].setText("x");
					buttons[i][j].setEnabled(false);
				} else {
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}


	//needs to change according to which ship is being placed
	//needs to reset
	class gridListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String coord = event.getActionCommand();
			boolean isRotated = _rotated.isSelected();
			switch (gameStatus) {
			case 0:
				break;
			case 1:
				if (_p1.setupShip(coord, isRotated, shipType)) {
					updateShip(_p1);
					_rotated.setSelected(false);
					reportNext(_p1);
					Main.wait(1);
					playerSwitch();
					p1Panel.setVisible(false);
					p2Panel.setVisible(true); 
				} else {
					reportAdd("Error, " + coord + " is an invalid location, try again!");
				}
				break;
			case 2:
				if (_p2.setupShip(coord, isRotated, shipType)) {
					if (shipType < SHIPNUM - 1) { 
						updateShip(_p2);
						_rotated.setSelected(false);
						shipType++;
						reportNext(_p2);
						Main.wait(1);
						playerSwitch();
						p1Panel.setVisible(true);
						p2Panel.setVisible(false);
					} else {
						updateShots(_p1);
						updateShots(_p2);
						_rotated.setVisible(false);
						p1Panel.setVisible(false);
						p2Panel.setVisible(true);
						p1Panel.setEnabled(false);
						p2Panel.setEnabled(true);
						report("Player 1, select a panel to shoot.");
						gameStatus++;

					}
				} else {
					reportAdd("Error, " + coord + " is an invalid location, try again!");
				}
				break;
			case 3:
				int result = _p2.shot(coord);
				reportShot(result);
				if (result == -2) {
					updateShots(_p1);
					Main.wait(1);
					reportAdd("Player 2, select a panel to shoot.");
					playerSwitch();
					updateShots(_p1);
					p1Panel.setVisible(true);
					p2Panel.setVisible(false);
				} else if (!_p2.isAlive()) {
					updateShots(_p2);
					report("Player 1 has Won!");
					gameStatus = 0;
					p1Panel.setVisible(true);
					p2Panel.setVisible(true);
					p1Panel.setEnabled(false);
					p2Panel.setEnabled(false);
				} else {
					updateShots(_p2);
				}
				break;
			case 4:
				result = _p1.shot(coord);
				reportShot(result);
				if (result == -2) {
					updateShots(_p2);
					Main.wait(1);
					reportAdd("Player 1, select a panel to shoot.");
					playerSwitch();
					p1Panel.setVisible(false);
					p2Panel.setVisible(true);
				} else if (!_p1.isAlive()) {
					updateShots(_p1);
					report("Player 2 has Won!");
					gameStatus = 0;
					p1Panel.setEnabled(false);
					p2Panel.setEnabled(false);
					p1Panel.setVisible(true);
					p2Panel.setVisible(true);
				} else {
					updateShots(_p1);
				}
				break;
			default:
				break;
			}


		}
	}

	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Main.stopGame();
			gameFrame.setVisible(false);
		}

	}
}

