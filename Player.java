package engine;

public class Player {
	boolean human;
	boolean white;
	
	Player(boolean isHuman, boolean isWhite) {
		this.human = isHuman;
		this.white = isWhite;
	}
	
	//function ensures player can only move its own pieces
	public boolean rightColor(Tile current) {
		Piece chosen = current.getPiece();
		
		if(white && !chosen.isWhite()) {
			System.out.println("White's turn. Piece chosen not white.");
			return false;
		}
		else if(!white && chosen.isWhite()) {
			System.out.println("Black's turn. Piece chosen not black.");
			return false;
		}
		else
			return true;
	}
	
	public boolean humanStatus() {
		return human;
	}
	
	public boolean colorWhiteStatus() {
		return white;
	}
	
}
