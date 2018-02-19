package aiGame;

public class Node {

	// وضعیت فعلی تخته در گره
	private Board board;

	// نوبت در این گره متعلق به کیست
	private Turn turn;

	// هزینه ساخت گره تا کنون
	private int nodeCost;

	// پیش بینی هزینه تا هدف
	private int h;

	// گره سازنده این گره
	public Node parent;

	// مقدار آلفا یا بتای گره فعلی - بر اساس نوبت
	private int alfaBetaValue;

	// حرکتی که در پدر این گره انجام شده است و منجر به ساخت شده است
	private String lastMove;

	/**
	 * سازنده هر گره
	 * 
	 * @param b
	 *            وضعیت کنونی تخته
	 * @param c
	 *            مهره کامپیوتر
	 * @param u
	 *            مهره کاربر
	 * @param t
	 *            نوبت
	 * @param nc
	 *            هزینه ساخت گره
	 */
	public Node(Node p, Board b, Turn t, int nc, String lm) {
		this.parent = p;
		this.board = b;
		this.turn = t;
		lastMove = lm;

		// مقدار دهی اولیه
		alfaBetaValue = (t == Turn.Computer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		setNodeCost(nc);
		heuristicCalc();
	}

	/**
	 * هزینه مسیر را ثبت می کند
	 * 
	 * @param t
	 */
	public void setNodeCost(int t) {
		nodeCost = (t > 0) ? t : 0;
	}

	/**
	 * رشته متناظر با آخرین حرکت را بر می گرداند
	 * 
	 * @return String
	 */
	public String getLastMove() {
		return lastMove;
	}

	/**
	 * مقدار آلفا یا بتا را تنظیم می کند
	 * 
	 * @param x
	 */
	public void setAlfaBeta(int x) {
		if (turn == Turn.Computer) {
			alfaBetaValue = Math.max(alfaBetaValue, x);
		} else {
			alfaBetaValue = Math.min(alfaBetaValue, x);
		}
	}

	/**
	 * مقدار هزینه ساخت گره را بر می گرداند
	 * 
	 * @return int
	 */
	public int getNodeCost() {
		return nodeCost;
	}

	/**
	 * نوبت را تنظیم می کند
	 * 
	 * @param t
	 */
	public void setTurn(Turn t) {
		this.turn = t;
	}

	/**
	 * مقدار تخمین تا هدف این گره را محاسبه می کند
	 */
	private void heuristicCalc() {
		
		h = (Tile.MAX_X - board.getMax().getX()) + nodeCost - board.getMin().getX();
		
		Tile maxTile = board.getTile(board.getMax().getX(), board.getMax().getY());
		Tile minTile = board.getTile(board.getMin().getX(), board.getMin().getY());
		
		if(maxTile.getBottomSide() == Side.Wall) {
			h --;
		}
		if(minTile.getTopSide() == Side.Wall) {
			h ++;
		}
	}

	public int getAlfaBetaValue() {
		return alfaBetaValue;
	}

	/**
	 * تخمین تا هدف این گره را بر می گرداند
	 * 
	 * @return int
	 */
	public int getHeuristic() {
		return h;
	}

	/**
	 * تخته مربوط به این گره را بر می گرداند
	 * 
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * نوبت این گره را بر می گرداند
	 * 
	 * @return Turn
	 */
	public Turn getTurn() {
		return turn;
	}

}
