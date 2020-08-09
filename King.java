package engine;

public class King extends Piece {
	Tile spot;

	King(boolean isWhite) {
		this.white = isWhite;
		type = "King";
	}

	public boolean move(Tile current, Tile dest, Game match) {
		/*
		 * basic movement
		 * can't move into check
		 * castle
		 * opposing king spacing
		 * 
		 */
		// TODO restrict movement to illegal tiles
		int rowDest = dest.getRow();
		int colDest = dest.getCol();
		int rowCurr = current.getRow();
		int colCurr = current.getCol();
		
		if(current == dest)
			return true;
		
		//can not capture piece of the same color
		if(white) {
			if(dest.isOccupied() && dest.getPiece().white)
				return false;
		}
		else {
			if(dest.isOccupied() && !dest.getPiece().white)
				return false;
		}
		
		//can move 1 space in any direction
		if(rowDest == rowCurr+1 && colDest == colCurr) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr && colDest == colCurr+1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		} 
		else if(rowDest == rowCurr && colDest == colCurr-1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr-1 && colDest == colCurr) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr+1 && colDest == colCurr+1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr-1 && colDest == colCurr+1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr-1 && colDest == colCurr-1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else if(rowDest == rowCurr+1 && colDest == colCurr-1) {
			if(isCheck(dest, match))
				return false;
			spot = dest;
			return true;
		}
		else
			return false;
	}
	
	//checks if a square will be check for the King
	public boolean isCheck(Tile current, Game match) {
		// READ AGAIN: are both piece colors accounted for in checking open paths?
		int row = current.getRow();
		int col = current.getCol();
		int index = 1;
		boolean openPath = true;
		
		//south
		while((row+index) < 8 && openPath) {
			if(match.gameBoard.square[row+index][col].isOccupied()) {
				if(match.gameBoard.square[row+index][col].getPiece().type.equalsIgnoreCase("Rook")
						|| match.gameBoard.square[row+index][col].getPiece().type.equalsIgnoreCase("Queen"))
					if(oppositeColors(match.gameBoard.square[row+index][col].getPiece().isWhite()))
						return true;
				openPath = false;
			}
			index++;
		}
		index = 1;
		openPath = true;
		
		//east
		while((col+index) < 8 && openPath) {
			if(match.gameBoard.square[row][col+index].isOccupied()) {
				if(match.gameBoard.square[row][col+index].getPiece().type.equalsIgnoreCase("Rook") 
						|| match.gameBoard.square[row][col+index].getPiece().type.equalsIgnoreCase("Queen"))
					if(oppositeColors(match.gameBoard.square[row][col+index].getPiece().isWhite()))
						return true;
				openPath = false;
			}
			index++;
		}
		index = 1;
		openPath = true;
		
		//north
		while((row-index) > -1) {
			if(match.gameBoard.square[row-index][col].isOccupied())
				if(match.gameBoard.square[row-index][col].getPiece().type.equalsIgnoreCase("Rook") 
						|| match.gameBoard.square[row-index][col].getPiece().type.equalsIgnoreCase("Queen"))
					if(oppositeColors(match.gameBoard.square[row-index][col].getPiece().isWhite()))
						return true;
			openPath = false;
			index++;
		}
		index = 1;
		openPath = true;
		
		//west
		while((col-index) > -1) {
			if(match.gameBoard.square[row][col-index].isOccupied())
				if(match.gameBoard.square[row][col-index].getPiece().type.equalsIgnoreCase("Rook") 
						|| match.gameBoard.square[row][col-index].getPiece().type.equalsIgnoreCase("Queen")) 
					if(oppositeColors(match.gameBoard.square[row][col-index].getPiece().isWhite()))
						return true;
			openPath = false;
			index++;
		}
		index = 1;
		openPath = true;
		
		
		// southEast
		while ((row + index) < 8 && (col + index) < 8 && openPath) {
			if (match.gameBoard.square[row + index][col + index].isOccupied()) 
				// indicates pawn should be checked
				if (index == 1) {
					if (match.gameBoard.square[row + index][col + index].getPiece().type.equalsIgnoreCase("Pawn")) {
						// king is black
						if (!white)
							// pawn is white
							if (match.gameBoard.square[row + index][col + index].getPiece().isWhite())
								return true;
						openPath = false;
					}
					else if (match.gameBoard.square[row + index][col + index].getPiece().type.equalsIgnoreCase("Bishop")
							|| match.gameBoard.square[row + index][col + index].getPiece().type.equalsIgnoreCase("Queen")) {
							if(oppositeColors(match.gameBoard.square[row + index][col + index].getPiece().isWhite()))
								return true;
							openPath = false;
					}
				} else {
					if (match.gameBoard.square[row + index][col + index].getPiece().type.equalsIgnoreCase("Bishop")
							|| match.gameBoard.square[row + index][col + index].getPiece().type.equalsIgnoreCase("Queen")) {
						if(oppositeColors(match.gameBoard.square[row + index][col + index].getPiece().isWhite()))
							return true;
						openPath = false;
					}
				}
			index++;
		}
	index= 1;
	openPath = true;
	
	
	//northEast 
	while((row-index) > -1 && (col+index) < 8 && openPath) {
		if (match.gameBoard.square[row - index][col + index].isOccupied()) 
			if (index == 1) {
				if (match.gameBoard.square[row - index][col + index].getPiece().type.equalsIgnoreCase("Pawn")) {
					if (white)
						if (!match.gameBoard.square[row - index][col + index].getPiece().isWhite())
							return true;
					openPath = false;
				}
				else if (match.gameBoard.square[row - index][col + index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row - index][col + index].getPiece().type.equalsIgnoreCase("Queen")) {
						if(oppositeColors(match.gameBoard.square[row - index][col + index].getPiece().isWhite()))
							return true;
						openPath = false;
				}
			} else {
				if (match.gameBoard.square[row - index][col + index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row - index][col + index].getPiece().type.equalsIgnoreCase("Queen")) {
					if(oppositeColors(match.gameBoard.square[row - index][col + index].getPiece().isWhite()))
						return true;
					openPath = false;
				}
			}
		index++;
	}
	index= 1;
	openPath = true;
	
	//northWest
	while((row - index) > -1 && (col - index) > -1 && openPath) {
		if (match.gameBoard.square[row - index][col - index].isOccupied()) 
			// indicates pawn should be checked
			if (index == 1) {
				if (match.gameBoard.square[row - index][col - index].getPiece().type.equalsIgnoreCase("Pawn")) {
					// king is black
					if (!white)
						// pawn is white
						if (match.gameBoard.square[row - index][col - index].getPiece().isWhite())
							return true;
					openPath = false;
				}
				else if (match.gameBoard.square[row - index][col - index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row - index][col - index].getPiece().type.equalsIgnoreCase("Queen")) {
						if(oppositeColors(match.gameBoard.square[row - index][col - index].getPiece().isWhite()))
							return true;
						openPath = false;
				}
			} else {
				if (match.gameBoard.square[row - index][col - index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row - index][col - index].getPiece().type.equalsIgnoreCase("Queen")) {
					if(oppositeColors(match.gameBoard.square[row - index][col - index].getPiece().isWhite()))
						return true;
					openPath = false;
				}
			}
		index++;
	}
	index= 1;
	openPath = true;
	
	//southWest
	while((row + index) < 8 && (col - index) > -1 && openPath) {
		if (match.gameBoard.square[row + index][col - index].isOccupied()) 
			// indicates pawn should be checked
			if (index == 1) {
				if (match.gameBoard.square[row + index][col - index].getPiece().type.equalsIgnoreCase("Pawn")) {
					// king is black
					if (!white)
						// pawn is white
						if (match.gameBoard.square[row + index][col - index].getPiece().isWhite())
							return true;
					openPath = false;
				}
				else if (match.gameBoard.square[row + index][col - index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row + index][col - index].getPiece().type.equalsIgnoreCase("Queen")) {
						if(oppositeColors(match.gameBoard.square[row + index][col - index].getPiece().isWhite()))
							return true;
						openPath = false;
				}
			} else {
				if (match.gameBoard.square[row + index][col - index].getPiece().type.equalsIgnoreCase("Bishop")
						|| match.gameBoard.square[row + index][col - index].getPiece().type.equalsIgnoreCase("Queen")) {
					if(oppositeColors(match.gameBoard.square[row + index][col - index].getPiece().isWhite()))
						return true;
					openPath = false;
				}
			}
		index++;
	}
	index= 1;
	openPath = true;
	 
	//knights
	//south
	if((row + 2) < 8 && (col - 1) > -1) {
		if(match.gameBoard.square[row + 2][col - 1].isOccupied()) {
			if(match.gameBoard.square[row + 2][col - 1].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row + 2][col - 1].getPiece().isWhite()))
					return true;
		}
	}
	if((row + 2) < 8 && (col + 1) > 8) {
		if(match.gameBoard.square[row + 2][col + 1].isOccupied()) {
			if(match.gameBoard.square[row + 2][col + 1].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row + 2][col + 1].getPiece().isWhite()))
					return true;
		}
	}
	
	//east
	if((row + 1) < 8 && (col + 2) < 8){
		if(match.gameBoard.square[row + 1][col + 2].isOccupied()) {
			if(match.gameBoard.square[row + 1][col + 2].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row + 1][col + 2].getPiece().isWhite()))
					return true;
		}
	}
	if((row - 1) > -1 && (col + 2) < 8) {
		if(match.gameBoard.square[row - 1][col + 2].isOccupied()) {
			if(match.gameBoard.square[row - 1][col + 2].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row - 1][col + 2].getPiece().isWhite()))
					return true;
		}
	}
	
	//west
	if((row + 1) < 8 && (col - 2) > -1) {
		if(match.gameBoard.square[row + 1][col - 2].isOccupied()) {
			if(match.gameBoard.square[row + 1][col - 2].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row + 1][col - 2].getPiece().isWhite()))
					return true;
		}
	}
	if((row - 1) > -1 && (col - 2) > -1) {
		if(match.gameBoard.square[row - 1][col - 2].isOccupied()) {
			if(match.gameBoard.square[row - 1][col - 2].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row - 1][col - 2].getPiece().isWhite()))
					return true;
		}
	}
	
	//north
	if((row - 2) > -1 && (col - 1) > -1) {
		if(match.gameBoard.square[row - 2][col - 1].isOccupied()) {
			if(match.gameBoard.square[row - 2][col - 1].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row - 2][col - 1].getPiece().isWhite()))
					return true;
		}
	}
	if((row - 2) > -1 && (col + 1) < 8) {
		if(match.gameBoard.square[row - 2][col + 1].isOccupied()) {
			if(match.gameBoard.square[row - 2][col + 1].getPiece().type.equalsIgnoreCase("Knight"))
				if(oppositeColors(match.gameBoard.square[row - 2][col + 1].getPiece().isWhite()))
					return true;
		}
	}
		return false;
	}
	
	//helper method
	public boolean oppositeColors(boolean otherIsWhite) {
		//king is white, piece is black
		if(white && !otherIsWhite) {
			System.out.println("Check.");
			return true;
		}
		//king is black, piece is white
		else if(!white && otherIsWhite) {
			System.out.println("Check.");
			return true;
		}
		//king is same color as piece
		else
			return false;
	}
	
	public boolean isMate(Tile current, Game match) {
		int checkPaths = 1;
		int rowCurr = current.getRow();
		int colCurr = current.getCol();
		
		//only check mate when you know there is check
		if(!isCheck(current, match))
			return false;

		//after it is known there are multiple paths
		//or it is known the one path can not be blocked
		if(move(current, match.gameBoard.square[rowCurr-1][colCurr-1], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr-1][colCurr], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr-1][colCurr+1], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr][colCurr-1], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr][colCurr+1], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr+1][colCurr-1], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr+1][colCurr], match))
			return false;
		else if(move(current, match.gameBoard.square[rowCurr+1][colCurr+1], match))
			return false;
		else
			return true;

		//maybe check all the ways the king would be in check and add to the total number of checks its in.
		
		//if there is one check path
		//CHECK: kings options for movement probably need to be checked again
		//see if any piece can block the path
		
		//if it is more than one and the king can't move then it is mate
		//if it is more than one and the king can move then it isn't
		
		//if(checkPaths > 1) {
			
	}
}
