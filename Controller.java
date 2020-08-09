package engine;

import java.awt.Color;

import javax.swing.JButton;

public class Controller {
	boolean whiteTurn = true; //Determines whether black or white is moving
	boolean firstClick = true; //Each move requires two clicks to execute. Boolean variable firstClick tracks this.
	boolean check = false; //indicates there is a check
	Tile current;
	Tile destination;
	Game match;
	String set; //holder variable for updates to button titles
	Piece selected;
	King blackKing;
	King whiteKing;
	
	Controller(Game match){
		this.match = match;
		blackKing = (King)match.gameBoard.square[0][4].getPiece();
		whiteKing = (King)match.gameBoard.square[7][4].getPiece();
		blackKing.spot = match.gameBoard.square[0][4];
		whiteKing.spot = match.gameBoard.square[7][4];
	}
	
	//function retrieves Tile from JButton pressed as well as logs who made the move, and updates the board
	public void click(JButton[][] buttons, int row, int col) {
		
		//the tile must be occupied on firstClick
		if(firstClick && !match.gameBoard.square[row][col].isOccupied()) {
			System.out.println("No piece was selected.");
			return;
		}
		
		//tile must be occupied.  set "current" variable, retrieve text of Jbutton, update text, update Tile and toggle switch
		if(firstClick && match.gameBoard.square[row][col].isOccupied()) {
			//designates what piece was selected and where on the board it is
			current = match.gameBoard.square[row][col];
			selected = current.getPiece();
			
			if(whiteTurn && match.white.rightColor(current)) {
				set = buttons[row][col].getText();
				buttons[row][col].setText("");
				switchClick();
				System.out.println("White " + selected.type + " was selected.");
				current.switchOccupied();
			} else if(!whiteTurn && match.black.rightColor(current)) {
				set = buttons[row][col].getText();
				buttons[row][col].setText("");
				switchClick();
				System.out.println("Black " + selected.type + " was selected.");
				current.switchOccupied();
			}	
		}
		else {
			//TODO if king moves update his spot
			//TODO if check moves can only be made to get out of check
			//TODO check for mate immediately after check is found
			
			//records the desired destination
			destination = match.gameBoard.square[row][col];
			
			//ensures legality of move
			if(selected.move(current, destination, match) && !check) {
				if(current == destination) {
					destination.setPiece(selected);
					buttons[row][col].setText(set);
					if(whiteTurn)
						buttons[row][col].setForeground(Color.WHITE);
					System.out.println("No move was made.");
					switchClick();
				}
				else {
					//destination is taken
					if(destination.isOccupied()) {
						Piece temp = destination.getPiece();
						destination.setPiece(selected);
						if(whiteTurn) {
							//white king is in check
							if(whiteKing.isCheck(whiteKing.spot, match)) {
								System.out.println("Illegal move. White is exposed to check.");
								destination.setPiece(temp);
								current.setPiece(selected);
								buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType());
								switchClick();
							}
							else {
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.WHITE);
								System.out.println("Black " + match.gameBoard.square[row][col].getPiece().type + " was captured by White " + selected.type);
								current.occupied = false;
								if(blackKing.isCheck(blackKing.spot, match)) {
									check = true;
									System.out.println("Black is in check.");
								}
								if(selected.type.equalsIgnoreCase("king"))
									whiteKing.spot = destination;
								System.out.println("White has made its move. It is now Black's turn.");
								switchClick();
								switchTurn();
							}
						}
						else { //black's turn
							//black king is in check
							if(blackKing.isCheck(blackKing.spot, match)) {
								System.out.println("Illegal move. Black is exposed to check.");
								destination.setPiece(temp);
								current.setPiece(selected);
								buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType());
								switchClick();
							}
							else {
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.BLACK);
								System.out.println("White " + match.gameBoard.square[row][col].getPiece().type + " was captured by Black " + selected.type);
								current.occupied = false;
								if(whiteKing.isCheck(whiteKing.spot, match)) {
									check = true;
									System.out.println("White is in check.");
								}
								if(selected.type.equalsIgnoreCase("king"))
									blackKing.spot = destination;
								System.out.println("Black has made its move. It is now White's turn.");
								switchClick();
								switchTurn();
							}
						}
					}
					else { //destination is not occupied
						if(whiteTurn) {
							if(whiteKing.isCheck(whiteKing.spot, match)) {
								System.out.println("Illegal move. White is exposed to check.");
								current.setPiece(selected);
								buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType());
								switchClick();
							}
							else {
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.WHITE);
								destination.setPiece(selected);
								current.occupied = false;
								if(blackKing.isCheck(blackKing.spot, match)) {
									check = true;
									System.out.println("Black is in check.");
								}
								System.out.println("White has made its move. It is now Black's turn.");
								switchClick();
								switchTurn();
							}
						}
						else { //black's turn
							if(blackKing.isCheck(blackKing.spot, match)) {
								System.out.println("Illegal move. Black is exposed to check.");
								current.setPiece(selected);
								buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType());
								switchClick();
							}
							else {
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.BLACK);
								destination.setPiece(selected);
								current.occupied = false;
								if(whiteKing.isCheck(whiteKing.spot, match)) {
									check = true;
									System.out.println("White is in check.");
								}
								System.out.println("Black has made its move. It is now White's turn.");
								switchClick();
								switchTurn();
							}
						}
					}	
				}
			}
			else if(check){
				if(whiteTurn) {
					if(selected.move(current, destination, match)) {
						Piece temp = null;
						if(destination.isOccupied())
							temp = destination.getPiece();
						destination.setPiece(selected);
						if(whiteKing.isCheck(whiteKing.spot, match)) {
							System.out.println("Illegal move. White is still in check.");
							if(temp != null)
								destination.setPiece(temp);
							else
								destination.switchOccupied();
							current.setPiece(selected);
							buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType()); 
							switchClick();
						}
						else {
							if(temp != null) {
								System.out.println("Black " + temp.type + " was captured by White " + selected.type);
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.WHITE);
								current.occupied = false;
								System.out.println("White has made its move. It is now Black's turn.");
							}
							else {
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.WHITE);
								current.occupied = false;
								System.out.println("White has made its move. It is now Black's turn.");
							}
							check = false;
							switchClick();
							switchTurn();
						}
							
					}
				}
				else {//black's turn
					if(selected.move(current, destination, match)) {
						Piece temp = null;
						if(destination.isOccupied())
							temp = destination.getPiece();
						destination.setPiece(selected);
						if(blackKing.isCheck(blackKing.spot, match)) {
							System.out.println("Illegal move. Black is still in check.");
							if(temp != null)
								destination.setPiece(temp);
							else
								destination.switchOccupied();
							current.setPiece(selected);
							buttons[current.getRow()][current.getCol()].setText(current.getPiece().getType());
							switchClick();
						}
						else {
							if(temp != null) {
								System.out.println("White " + temp.type + " was captured by Black " + selected.type);
								destination.setPiece(selected);
								buttons[row][col].setText(set);
								buttons[row][col].setForeground(Color.BLACK);
								current.occupied = false;
								System.out.println("Black has made its move. It is now White's turn.");
							}
							else {
								destination.setPiece(selected);
								buttons[row][col].setText(set);
								current.occupied = false;
								System.out.println("Black has made its move. It is now White's turn.");
							}
							check = false;
							switchClick();
							switchTurn();
						}
					}
					
				}
			}
			else {
				System.out.println("Illegal move. Please choose another destination.");
			}
		}
			
	}
	
	public void switchClick() {
		firstClick = !firstClick;
	}
	
	public void switchTurn() {
		whiteTurn = !whiteTurn;
	}
	
	
}
