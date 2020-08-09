package engine;

public class Bishop extends Piece {

	Bishop(boolean isWhite) {
		this.white = isWhite;
		this.type = "Bishop";
	}

	public boolean move(Tile current, Tile dest, Game match) {
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
				
		//destination is on a diagonal
		if(Math.abs(rowDest - rowCurr) == Math.abs(colDest - colCurr)) {
			//loops through diagonal tiles subsequent of destination
			if(rowDest > rowCurr) {
				if(colDest > colCurr) {
					for(int i=1; i<Math.abs(rowDest - rowCurr); i++) {
						if(match.gameBoard.square[rowCurr+i][colCurr+i].isOccupied())
							return false;
					}
					return true;
				}
				else {
					for(int i=1; i<Math.abs(rowDest - rowCurr); i++) {
						if(match.gameBoard.square[rowCurr+i][colCurr-i].isOccupied())
							return false;
					}
					return true;
				}
			}
			else {
				if(colDest > colCurr) {
					for(int i=1; i<Math.abs(rowDest - rowCurr); i++) {
						if(match.gameBoard.square[rowCurr-i][colCurr+i].isOccupied())
							return false;
					}
					return true;
				}
				else {
					for(int i=1; i<Math.abs(rowDest - rowCurr); i++) {
						if(match.gameBoard.square[rowCurr-i][colCurr-i].isOccupied())
							return false;
					}
					return true;
				}
			}
			
		}
		return false;
	}
}
