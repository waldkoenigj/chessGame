package engine;

public class Board {
	Tile[][] square = new Tile[8][8];

	Board() {
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++) 
				square[i][j] = new Tile(i, j);
		fill();
	}
	
	public void fill() {
		for(int i=0; i<8; i++)
			square[1][i].setPiece(new Pawn(false));
		
		for(int i=0; i<8; i++)
			square[6][i].setPiece(new Pawn(true));

		square[0][0].setPiece(new Rook(false));
		square[0][1].setPiece(new Knight(false));
		square[0][2].setPiece(new Bishop(false));
		square[0][3].setPiece(new Queen(false));
		square[0][4].setPiece(new King(false));
		square[0][5].setPiece(new Bishop(false));
		square[0][6].setPiece(new Knight(false));
		square[0][7].setPiece(new Rook(false));
		
		square[7][0].setPiece(new Rook(true));
		square[7][1].setPiece(new Knight(true));
		square[7][2].setPiece(new Bishop(true));
		square[7][3].setPiece(new Queen(true));
		square[7][4].setPiece(new King(true));
		square[7][5].setPiece(new Bishop(true));
		square[7][6].setPiece(new Knight(true));
		square[7][7].setPiece(new Rook(true));
	}

}
