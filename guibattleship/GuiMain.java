package guibattleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**Creates the main menu GUI.*/
public class GuiMain implements ActionListener {
	JFrame mainFrame;
	ButtonGroup p1buttons;
	ButtonGroup p2buttons;
	JRadioButton p1H;
	JRadioButton p1A;
	JRadioButton p2H;
	JRadioButton p2A;
	JButton start;

	GuiMain() {
		this.menu();
	}

	/**Implements the main menu interface, with options for human or AI.*/
	void menu() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 40));
		JLabel intro = new JLabel("BattleShip");
		topPanel.add(intro);


		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 100, 0, 100));
		JLabel player1 = new JLabel("Player 1:");
		JLabel player2 = new JLabel("Player 2:");
		p1H = new JRadioButton("Human");
		p1A = new JRadioButton("AI");
		p2H = new JRadioButton("Human");
		p2A = new JRadioButton("AI");

		p1buttons = new ButtonGroup();
		p1buttons.add(p1H);
		p1buttons.add(p1A);
		p1H.setSelected(true);

		p2buttons = new ButtonGroup();
		p2buttons.add(p2H);
		p2buttons.add(p2A);
		p2H.setSelected(true);

		centerPanel.add(player1);
		centerPanel.add(p1H);
		centerPanel.add(p1A);

		centerPanel.add(player2);
		centerPanel.add(p2H);
		centerPanel.add(p2A);



		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		start = new JButton("Set Sail!");
		start.addActionListener(this);
		bottomPanel.add(start);

		mainFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
		mainFrame.setSize(400, 400);
		mainFrame.setVisible(true);
	}


	public void actionPerformed(ActionEvent event) {
		boolean p1 = true;
		boolean p2 = true;
		if (p1A.isSelected()) {
			p1 = false;
		}
		if (p2A.isSelected()) {
			p2 = false;
		}

		GuiGame gameFrame = new GuiGame(p1, p2);

		mainFrame.setVisible(false);
	}


	}


