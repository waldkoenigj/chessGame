package engine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class UserInterface {	
	Controller control;
	
	UserInterface(Controller control) {
		this.control = control;
		JFrame window = new JFrame();
		JPanel board = new JPanel();
		
		board.setLayout(new GridLayout(8,8));
		createBoard(board);
		
		window.add(board);
		window.setSize(900,900);
		window.setTitle("Chess");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void createBoard(JPanel board) {
		JButton[][] buttons = new JButton[8][8];
		boolean squareColor = true;
		boolean offset = true;
		Color lightBrown = new Color(252, 210, 124);
		Color brown = new Color(146, 92, 72);
		
		for(int i=0; i<8; i++) {
			offset = !offset;
			if(offset) {
				for(int j=0; j<8; j++) {
					JButton square = new JButton();
					buttons[i][j] = square;
					final int row = i;
					final int col = j;
					square.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//TODO implement action listener
							control.click(buttons, row, col);
						}
					});
					if(squareColor) {
						square.setBackground(lightBrown);
						squareColor = false;
					} else {
						square.setBackground(brown);
						squareColor = true;
					}
					board.add(square);
				}
			} else {
				for(int j=0; j<8; j++) {
					JButton square = new JButton();
					buttons[i][j] = square;
					final int row = i;
					final int col = j;
					square.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//TODO implement action listener
							control.click(buttons, row, col);
						}
					});
					if(squareColor) {
						square.setBackground(brown);
						squareColor = false;
					} else {
						square.setBackground(lightBrown);
						squareColor = true;
					}
					board.add(square);
				}
			}
		}
		
		addImages(buttons);
	}
	
	public void addImages(JButton[][] buttons) {
		//TODO add Icons
		buttons[0][0].setText("B-Rook");
		
		buttons[0][1].setText("B-Knight");
		buttons[0][2].setText("B-Bishop");
		buttons[0][3].setText("B-Queen");
		buttons[0][4].setText("B-King");
		buttons[0][5].setText("B-Bishop");
		buttons[0][6].setText("B-Knight");
		buttons[0][7].setText("B-Rook");
		buttons[7][0].setText("W-Rook");
		buttons[7][0].setForeground(Color.WHITE);
		buttons[7][1].setText("W-Knight");
		buttons[7][1].setForeground(Color.WHITE);
		buttons[7][2].setText("W-Bishop");
		buttons[7][2].setForeground(Color.WHITE);
		buttons[7][3].setText("W-Queen");
		buttons[7][3].setForeground(Color.WHITE);
		buttons[7][4].setText("W-King");
		buttons[7][4].setForeground(Color.WHITE);
		buttons[7][5].setText("W-Bishop");
		buttons[7][5].setForeground(Color.WHITE);
		buttons[7][6].setText("W-Knight");
		buttons[7][6].setForeground(Color.WHITE);
		buttons[7][7].setText("W-Rook");
		buttons[7][7].setForeground(Color.WHITE);

		for(int i=0; i<buttons[0].length; i++) 
			buttons[1][i].setText("B-Pawn");
		for(int i=0; i<buttons[0].length; i++) {
			buttons[6][i].setText("W-Pawn");
			buttons[6][i].setForeground(Color.WHITE);
		}
			
	}
	
}
