package aiGame;

public class Action {

	/**
	 * ایجاد کردن دیوار افقی بین ردیف های وارد شده و در مقابل ستون های داده شده
	 * 
	 * @param b
	 * @param rowNum1
	 * @param rowNum2
	 * @param colNum1
	 * @param colNum2
	 * @return Boolean
	 */
	public static Boolean setHorizontalWall(Board b, int rowNum1, int rowNum2, int colNum1, int colNum2) {
		Boolean state = b.setHorizontalWall(rowNum1, rowNum2, colNum1, colNum2);
		if (!state) {
			return false;
		}
		return true;
	}

	/**
	 * ایجاد کردن دیوار عمودی بین ستون های وارد شده و در کنار ردیف های داده شده
	 * 
	 * @param b
	 * @param colNum1
	 * @param colNum2
	 * @param rowNum1
	 * @param rowNum2
	 * @return Boolean
	 */
	public static Boolean setVerticalWall(Board b, int colNum1, int colNum2, int rowNum1, int rowNum2) {
		Boolean state = b.setVerticalWall(colNum1, colNum2, rowNum1, rowNum2);
		if (!state) {
			return false;
		}
		return true;
	}

	/**
	 * مهره وارد شده را در صورت امکان یک خانه به بالا حرکت می دهد
	 * 
	 * @param b
	 * @param t
	 * @return Boolean
	 */
	public static Boolean moveTop(Board b, Turn t) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();
		Tile[][] ts = b.getTiles();
		if (ts[x][y].getTopSide() != Side.Nothing) {
			return false;
		} else {
			if (t == Turn.User) {
				b.setMinPosition(x - 1, y);
			} else {
				b.setMaxPosition(x - 1, y);
			}
		}
		return true;
	}

	/**
	 * مهره وارد شده را در صورت امکان یک خانه به سمت چپ حرکت می دهد
	 * 
	 * @param b
	 * @param t
	 * @return Boolean
	 */
	public static Boolean moveLeft(Board b, Turn t) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();
		Tile[][] ts = b.getTiles();
		if (ts[x][y].getLeftSide() != Side.Nothing) {
			return false;
		} else {
			if (t == Turn.User) {
				b.setMinPosition(x, y - 1);
			} else {
				b.setMaxPosition(x, y - 1);
			}
		}
		return true;
	}

	/**
	 * مهره وارد شده را در صورت امکان یک خانه به سمت پایین حرکت می دهد
	 * 
	 * @param b
	 * @param t
	 * @return Boolean
	 */
	public static Boolean moveBottom(Board b, Turn t) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();
		Tile[][] ts = b.getTiles();
		if (ts[x][y].getBottomSide() != Side.Nothing) {
			return false;
		} else {
			if (t == Turn.User) {
				b.setMinPosition(x + 1, y);
			} else {
				b.setMaxPosition(x + 1, y);
			}
		}
		return true;
	}

	/**
	 * مهره وارد شده را در صورت امکان یک خانه به سمت راست حرکت می دهد
	 * 
	 * @param b
	 * @param t
	 * @return Boolean
	 */
	public static Boolean moveRight(Board b, Turn t) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();
		Tile[][] ts = b.getTiles();
		if (ts[x][y].getRightSide() != Side.Nothing) {
			return false;
		} else {
			if (t == Turn.User) {
				b.setMinPosition(x, y + 1);
			} else {
				b.setMaxPosition(x, y + 1);
			}
		}
		return true;
	}

	/**
	 * حرکت قطری مهره را انجام می دهد
	 * 
	 * @param b
	 * @param t
	 * @param side
	 *            = {r | l | t | everything else as bottom}
	 * @return Boolean
	 */
	public static Boolean moveDiagonal(Board b, Turn t, char side) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();

		Tile[][] ts = b.getTiles();
		if (side == 'r') {
			if (ts[x][y].getRightSide() != Side.Nothing) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x, y + 1);
				} else {
					b.setMaxPosition(x, y + 1);
				}
			}
		} else if (side == 'l') {
			if (ts[x][y].getLeftSide() != Side.Nothing) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x, y - 1);
				} else {
					b.setMaxPosition(x, y - 1);
				}
			}
		} else if (side == 't') {
			if (ts[x][y].getTopSide() != Side.Nothing) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x - 1, y);
				} else {
					b.setMaxPosition(x - 1, y);
				}
			}
		} else {
			if (ts[x][y].getBottomSide() != Side.Nothing) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x + 1, y);
				} else {
					b.setMaxPosition(x + 1, y);
				}
			}
		}
		return true;
	}

	/**
	 * مهره را دو خانه به سمت خواسته شده حرکت می دهد
	 * 
	 * @param b
	 * @param p
	 * @param side
	 *            = {t | l | b | everything else for r}
	 * @return Boolean
	 */
	public static Boolean jumpPiece(Board b, Turn t, char side) {
		Piece p;
		p = (t == Turn.User) ? b.getMin() : b.getMax();

		int x = p.getX(), y = p.getY();
		Tile[][] ts = b.getTiles();
		if (side == 't') {
			if (ts[x][y].getTopSide() != Side.Nothing || x - 2 < 0) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x - 2, y);
				} else {
					b.setMaxPosition(x - 2, y);
				}
			}
		} else if (side == 'l') {
			if (ts[x][y].getLeftSide() != Side.Nothing || y - 2 < 0) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x, y - 2);
				} else {
					b.setMaxPosition(x, y - 2);
				}
			}
		} else if (side == 'b') {
			if (ts[x][y].getBottomSide() != Side.Nothing || x + 2 > Tile.MAX_X - 1) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x + 2, y);
				} else {
					b.setMaxPosition(x + 2, y);
				}
			}
		} else {
			if (ts[x][y].getRightSide() != Side.Nothing || y + 2 > Tile.MAX_Y - 1) {
				return false;
			} else {
				if (t == Turn.User) {
					b.setMinPosition(x, y + 2);
				} else {
					b.setMaxPosition(x, y + 2);
				}
			}
		}
		return true;
	}
}
