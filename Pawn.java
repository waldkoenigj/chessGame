package engine;

public class Pawn extends Piece{
	boolean moved = false;

	Pawn(boolean isWhite) {
		white = isWhite;
		type = "Pawn";
	}

	public boolean move(Tile current, Tile dest, Game match) {
		/*
		 * pawn moves forward one space
		 * pawn moves forward two spaces off start
		 * pawn moves one space diagonally to capture 
		 * 
		 */
		int rowDest = dest.getRow();
		int colDest = dest.getCol();
		int rowCurr = current.getRow();
		int colCurr = current.getCol();
		
		if(current == dest)
			return true;
		
		if(white) {
			//can not capture same color
			if(dest.isOccupied() && dest.getPiece().white)
				return false;
			
			//System.out.println("Good.");
			//pawn moves forward one space
			if(rowDest == rowCurr-1 && colDest == colCurr) {
				//System.out.println("ok");
				if(dest.isOccupied())
					return false;
				else
					return true;
			}
				
			//pawn moves forward two spaces off start
			if(rowDest == rowCurr-2 && colDest == colCurr)
				//TODO don't allow pawn to jump over piece
				if(rowCurr < 6)
					return false;
				else if(dest.isOccupied() || match.gameBoard.square[rowCurr-1][colCurr].isOccupied())
					return false;
				else
					return true;
						
			//pawn moves one space diagonally to capture
			if(rowDest == rowCurr-1 && (colDest == colCurr-1 || colDest == colCurr+1))
				if(dest.isOccupied())
					//TODO deal with captures
					return true;
				
		} else {
			//can not capture same color
			if(dest.isOccupied() && !dest.getPiece().white)
				return false;
			
			//pawn moves forward one space
			if(rowDest == rowCurr+1 && colDest == colCurr) {
				if(dest.isOccupied())
					return false;
				else
					return true;
			}
				
			//pawn moves forward two spaces off start
			if(rowDest == rowCurr+2 && colDest == colCurr)
				//TODO don't allow pawn to jump over piece
				if(rowCurr > 2)
					return false;
				else if(dest.isOccupied() || match.gameBoard.square[rowCurr+1][colCurr].isOccupied())
					return false;
				else
					return true;
						
			//pawn moves one space diagonally to capture
			if(rowDest == rowCurr+1 && (colDest == colCurr-1 || colDest == colCurr+1))
				if(dest.isOccupied())
					//TODO deal with captures
					return true;
					
		}
		return false;
	}
}
