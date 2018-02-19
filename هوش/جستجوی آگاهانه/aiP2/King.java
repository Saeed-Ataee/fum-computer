package aiP2;

public class King extends Piece {

	// global variables
	private String name = "King";
	private Position position = new Position();

	/*
	 * اسم مهره را برمیگرداند
	 * 
	 * @return String
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * مکان فعلی مهره را برمیگرداند
	 * 
	 * @return Position
	 */
	@Override
	public Position getPosition() {
		return position;
	}

	/**
	 * مکان فعلی مهره را تنظیم می کند
	 * 
	 * @param x
	 *            شماره ستون مهره
	 * @param y
	 *            شماره ردیف مهره
	 */
	@Override
	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}

	/**
	 * مهره مجاز به انجام حرکت پاس داده شده هست یا خیر
	 * 
	 * @param action
	 *            حرکت مورد نظر
	 * @param board
	 *            تخته بازی
	 * @return Boolean
	 */
	@Override
	public Boolean canDo(Action action, Board board) {
		if (action.deltaX > 1) {
			return false;
		} else if (action.deltaX < -1) {
			return false;
		}

		if (action.deltaY > 1) {
			return false;
		} else if (action.deltaY < -1) {
			return false;
		}

		int newX = position.x + action.deltaX, newY = position.y + action.deltaY;

		if (newX < 0) {
			return false;
		} else if (newX >= board.getWidth()) {
			return false;
		}

		if (newY < 0) {
			return false;
		} else if (newY >= board.getHeight()) {
			return false;
		}

		return true;
	}

	/**
	 * رشته مناسب با مهره را برمی گرداند
	 */
	@Override
	public String toString() {
		return name + " At (" + position.x + " , " + position.y + ")";
	}

	/**
	 * هزینه حرکت مهره را بر می گرداند
	 * 
	 * @return int
	 */
	@Override
	public int getCost() {
		return 1;
	}

}
