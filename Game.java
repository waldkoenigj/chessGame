package engine;

public class Game {
	Player white;
	Player black;
	Board gameBoard;
	
	Game() {
		white = new Player(true, true);
		black = new Player(true, false);
		gameBoard = new Board();
	}
	
	public static void main(String args[]) {
		Game match = new Game();
		Controller control = new Controller(match);
		new UserInterface(control);
	}
}
