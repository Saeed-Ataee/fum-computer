package aiP1;

public class Board {

	// global variables
	private int height = 0, width = 0;

	// constructor of board
	public Board(int h, int w) {
		setBoardSize(h, w);
	}

	/**
	 * setting the size of chess board
	 * 
	 * @param x
	 *            maximum number of board in X-axis
	 * @param y
	 *            maximum number of board in Y-axis
	 */
	public void setBoardSize(int x, int y) {
		if (x > 0 && y > 0) {
			height = x;
			width = y;
		}
	}

	/**
	 * height of the board
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * width of the board
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

}
