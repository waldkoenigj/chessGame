package engine;

public abstract class Piece {
	boolean white;
	boolean dead = false;
	String type;
	//Tile spot;
	
	public abstract boolean move(Tile current, Tile destination, Game match);
	//public abstract Tile check();
	
	public boolean isCaptured() {
		return dead == true;
	}
	
	public void setDead() {
		dead = true;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isWhite() {
		return white;
	}
}
