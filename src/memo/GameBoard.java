package memo;

import java.util.Random;

public class GameBoard {
	private int[][] gameBoard;
	private int gameSize;
	
	public GameBoard(int size) {
		this.gameSize = size;
		generateGameBoard();
	}
		
	private void shuffleArray(int[] array) {
		Random rand = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			int tmp = array[index];
			array[index] = array[i];
			array[i] = tmp;
		}
	}
	
	private void generateGameBoard() {
		this.gameBoard = new int[this.gameSize][this.gameSize];
		int totalPairs = (gameSize*gameSize)/2;
		int[] numbers = new int[totalPairs * 2];
		for (int i = 0; i < totalPairs * 2; i += 2) {
			numbers[i] = i / 2 + 1;
			numbers[i + 1] = numbers[i];
		}
		
		shuffleArray(numbers);

		int index = 0;
		for (int i = 0; i < this.gameSize; i++) {
			for (int j = 0; j < this.gameSize; j++) {
				this.gameBoard[i][j] = numbers[index++];
			}
		}
	}
	
	public void displayGameBoard() {
		for (int i=0; i<this.gameSize; i++) {
			for (int j=0; j<this.gameSize; j++) {
				System.out.print(this.gameBoard[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
	public boolean checkPair(int x1, int y1, int x2, int y2) {
		if (this.gameBoard[y1][x1] == this.gameBoard[y2][x2]) {
			this.gameBoard[y1][x1] = 0;
			this.gameBoard[y2][x2] = 0;
			return true;
		}
		return false;
	}
	
	public int getValue(int x, int y) {
		return gameBoard[y][x];
	}
}
