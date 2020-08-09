package engine;

public class Queen extends Piece {

	Queen(boolean isWhite) {
		this.white = isWhite;
		this.type = "Queen";
	}

	public boolean move(Tile current, Tile dest, Game match) {
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
		
		//destination is on a perpendicular line to the current
		if(rowDest == rowCurr) {
			if(colDest > colCurr) {
				for(int i=1; i<Math.abs(colDest - colCurr); i++) {
					if(match.gameBoard.square[rowDest][colCurr+i].isOccupied())
						return false;
				}
				return true;
			}
			else {
				for(int i=1; i<Math.abs(colDest - colCurr); i++) {
					if(match.gameBoard.square[rowDest][colCurr-i].isOccupied())
						return false;
				}
				return true;
			}
				
		}
		else if(colDest == colCurr) {
			if(rowDest > rowCurr) {
				for(int i=1; i<Math.abs(colDest - colCurr); i++) {
					if(match.gameBoard.square[rowCurr+i][colDest].isOccupied())
						return false;
				}
				return true;
			}
			else {
				for(int i=1; i<Math.abs(colDest - colCurr); i++) {
					if(match.gameBoard.square[rowDest-i][colDest].isOccupied())
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
