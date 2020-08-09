package engine;

public class Knight extends Piece {

	Knight(boolean isWhite) {
		this.white = isWhite;
		this.type = "Knight";
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
		
		//four cases in which the knight starts by moving 2 spaces in a direction
		if(rowDest == rowCurr-2) {
			//knight then moves 1 space perpendicular to its previous movement
			if(Math.abs(colDest - colCurr) == 1)
				return true;
		}
		else if(rowDest == rowCurr+2) {
			if(Math.abs(colDest - colCurr) == 1)
				return true;
		}
		else if(colDest == colCurr-2) {
			if(Math.abs(rowDest - rowCurr) == 1)
				return true;
		}
		else if(colDest == colCurr+2) {
			if(Math.abs(rowDest - rowCurr) == 1)
				return true;
		}
		return false;
	}
}
