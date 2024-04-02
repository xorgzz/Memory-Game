package memo;

public class Game {
	boolean isRunning = false;
	
	private GameBoard gameBoard;
	private int gameSize;
	private int machingTries = 0;
	private int score = 0;
	private int endScore;
	
	private Gui gui;
	
	public Game() {
		initGame(4);
	}
	
	public Game(int size) {
		if (size%2 == 0) {
			initGame(size);
		} else {
			System.out.println("Size of game board must be even !!");
		}
	}
	
	private void initGame(int size) {
		this.gameSize = size;
		this.isRunning = true;
		this.endScore = (gameSize*gameSize)/2;
		this.gameBoard = new GameBoard(gameSize);
		this.gui = new Gui(this.gameSize, this.gameBoard);
	}
	
	public void displayGameBoard() {
		this.gameBoard.displayGameBoard();
	}
	
	public boolean checkPair(int x1, int y1, int x2, int y2) {
		boolean outcome = this.gameBoard.checkPair(x1, y1, x2, y2);
		addMachingTries(1);
		if (outcome) {
			addScore(1);
			return true;
		}
		return false;
	}

	public void addScore(int addedScore) {
		this.score += addedScore;
	}

	public int getScore() {
		return this.score;
	}

	public void addMachingTries(int addedValue) {
		if (addedValue >= 1) {
			this.machingTries += addedValue;
		}
	}

	public int getMachingTries() {
		return this.machingTries;
	}
}
