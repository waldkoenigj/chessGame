package engine;

public class Tile {
	private int row;
	private int col;
	boolean occupied;
	Piece piece;
	
	Tile(int x, int y) {
		this.row = x;
		this.col = y;
	}
	
	public boolean isOccupied() {
		return this.occupied;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		occupied = true;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void switchOccupied() {
		if(isOccupied())
			piece = null;
		occupied = !occupied;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
}
