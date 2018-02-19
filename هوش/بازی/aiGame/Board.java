package aiGame;

public class Board {

	private Tile[][] tiles = new Tile[Tile.MAX_X][Tile.MAX_Y];

	private Piece max, min;

	/**
	 * ساخت تخته به صورت اولیه
	 */
	public void createDefaultBoard() {
		int i, j;

		// ساختن ردیف اول تخته
		for (i = 0; i < Tile.MAX_Y; i++) {

			tiles[0][i] = new Tile(0, i, Side.Border, Side.Nothing, Side.Nothing, Side.Nothing);

			// اگر خانه در ستون شماره یک باشد سمت چپ آن مرز است
			if (i == 0) {
				tiles[0][i].setLeftSide(Side.Border);
			}

			// اگر خانه در ستون آخر باشد سمت راست آن مرز است
			else if (i == Tile.MAX_Y - 1) {
				tiles[0][i].setRightSide(Side.Border);
			}
		}

		// ساخت خانه های میانی تخته
		for (i = 1; i < Tile.MAX_X - 1; i++) {
			for (j = 0; j < Tile.MAX_Y; j++) {

				tiles[i][j] = new Tile(i, j, Side.Nothing, Side.Nothing, Side.Nothing, Side.Nothing);

				// اگر خانه در ستون شماره یک باشد سمت چپ آن مرز است
				if (j == 0) {
					tiles[i][j].setLeftSide(Side.Border);
				}

				// اگر خانه در ستون آخر باشد سمت راست آن مرز است
				else if (j == Tile.MAX_Y - 1) {
					tiles[i][j].setRightSide(Side.Border);
				}
			}
		}

		// ساختن ردیف آخر تخته
		for (i = 0; i < Tile.MAX_Y; i++) {

			tiles[Tile.MAX_X - 1][i] = new Tile(Tile.MAX_X - 1, i, Side.Nothing, Side.Nothing, Side.Border,
					Side.Nothing);

			// اگر خانه در ستون شماره یک باشد سمت چپ آن مرز است
			if (i == 0) {
				tiles[Tile.MAX_X - 1][i].setLeftSide(Side.Border);
			}

			// اگر خانه در ستون آخر باشد سمت راست آن مرز است
			else if (i == Tile.MAX_Y - 1) {
				tiles[Tile.MAX_X - 1][i].setRightSide(Side.Border);
			}
		}
	}

	public Board() {
		max = new Piece('C', 0, 4);
		min = new Piece('P', Tile.MAX_X - 1, 4);
	}

	public void setMaxPosition(int x, int y) {
		max.setX(x);
		max.setY(y);
		setPieces();
	}

	public void setMinPosition(int x, int y) {
		min.setX(x);
		min.setY(y);
		setPieces();
	}

	/**
	 * ایجاد کردن دیوار افقی بین ردیف های وارد شده و در مقابل ستون های داده شده
	 * 
	 * range:1 - MAX_Y-1
	 * 
	 * top-down
	 * 
	 * @param rowNumTop
	 * @param rowNumBottom
	 * @param colNum1
	 * @param colNum2
	 * 
	 * @return Boolean
	 */
	public Boolean setHorizontalWall(int rowNumTop, int rowNumBottom, int colNum1, int colNum2) {
		if (rowNumTop < 0 || rowNumTop > Tile.MAX_X - 1 || rowNumBottom < 0 || rowNumBottom > Tile.MAX_X - 1) {
			return false;
		}
		if (colNum1 < 0 || colNum1 > Tile.MAX_Y - 1 || colNum2 < 0 || colNum2 > Tile.MAX_Y - 1) {
			return false;
		}
		if (Math.abs(rowNumTop - rowNumBottom) != 1) {
			return false;
		}
		if (Math.abs(colNum1 - colNum2) != 1) {
			return false;
		}
		if (tiles[rowNumTop][colNum1].getBottomSide() != Side.Nothing
				|| tiles[rowNumTop][colNum2].getBottomSide() != Side.Nothing
				|| tiles[rowNumBottom][colNum1].getTopSide() != Side.Nothing
				|| tiles[rowNumBottom][colNum2].getTopSide() != Side.Nothing) {
			return false;
		}
		tiles[rowNumTop][colNum1].setBottomSide(Side.Wall);
		tiles[rowNumTop][colNum2].setBottomSide(Side.Wall);
		tiles[rowNumBottom][colNum1].setTopSide(Side.Wall);
		tiles[rowNumBottom][colNum2].setTopSide(Side.Wall);

		return true;
	}

	/**
	 * ایجاد کردن دیوار عمودی بین ستون های وارد شده و در کنار ردیف های داده شده
	 * 
	 * range:1 - MAX_Y-1
	 * 
	 * left-right
	 * 
	 * @param colNumLeft
	 * @param colNumRight
	 * @param rowNum1
	 * @param rowNum2
	 * 
	 * @return Boolean
	 */
	public Boolean setVerticalWall(int colNumLeft, int colNumRight, int rowNum1, int rowNum2) {
		if (colNumLeft < 0 || colNumLeft > Tile.MAX_Y - 1 || colNumRight < 0 || colNumRight > Tile.MAX_Y - 1) {
			return false;
		}
		if (rowNum1 < 0 || rowNum1 > Tile.MAX_X - 1 || rowNum2 < 0 || rowNum2 > Tile.MAX_X - 1) {
			return false;
		}
		if (Math.abs(colNumLeft - colNumRight) != 1) {
			return false;
		}
		if (Math.abs(rowNum1 - rowNum2) != 1) {
			return false;
		}
		if (tiles[rowNum1][colNumLeft].getRightSide() != Side.Nothing
				|| tiles[rowNum1][colNumRight].getLeftSide() != Side.Nothing
				|| tiles[rowNum2][colNumLeft].getRightSide() != Side.Nothing
				|| tiles[rowNum2][colNumRight].getLeftSide() != Side.Nothing) {
			return false;
		}
		tiles[rowNum1][colNumLeft].setRightSide(Side.Wall);
		tiles[rowNum1][colNumRight].setLeftSide(Side.Wall);
		tiles[rowNum2][colNumLeft].setRightSide(Side.Wall);
		tiles[rowNum2][colNumRight].setLeftSide(Side.Wall);

		return true;
	}

	/**
	 * نام مهره ها را در خانه درست جدول قرار می دهد
	 * 
	 * @param p1
	 * @param p2
	 */
	public void setPieces() {
		for (int i = 0; i < Tile.MAX_X; i++) {
			for (int j = 0; j < Tile.MAX_Y; j++) {
				tiles[i][j].setContent(' ');
			}
		}
		tiles[max.getX()][max.getY()].setContent(max.getName());
		tiles[min.getX()][min.getY()].setContent(min.getName());
	}

	/**
	 * نقشه بازی را رسم می کند
	 */
	public void drawBoard() {
		String o = "";
		for (int i = 0; i < Tile.MAX_X; i++) {
			// سمت بالای ردیف
			for (int j = 0; j < Tile.MAX_Y; j++) {

				Tile t = tiles[i][j];

				// سمت بالای خانه
				if (t.getTopSide() == Side.Wall) {
					o += " === ";
				} else if (t.getTopSide() == Side.Border) {
					o += " *" + j + "* ";
				} else {
					o += " --- ";
				}

			} // پایان رسم سمت بالا
			o += '\n';

			// سمت چپ ردیف
			for (int j = 0; j < Tile.MAX_Y; j++) {

				Tile t = tiles[i][j];

				if (t.getLeftSide() == Side.Wall) {
					o += "| " + t.getContent() + "  ";
				} else if (t.getLeftSide() == Side.Border) {
					o += i + " " + t.getContent() + "  ";
				} else {
					o += "' " + t.getContent() + "  ";
				}

			} // پایان رسم سمت چپ خانه

			o += "*\n";

			// سمت چپ ردیف
			for (int j = 0; j < Tile.MAX_Y; j++) {

				Tile t = tiles[i][j];

				if (t.getLeftSide() == Side.Wall) {
					o += "|    ";
				} else if (t.getLeftSide() == Side.Border) {
					o += "*    ";
				} else {
					o += "'    ";
				}

			} // پایان رسم سمت چپ خانه

			o += "*\n";
		}

		for (int j = 0; j < Tile.MAX_Y; j++) {
			o += " *** ";
		}

		System.out.println(o);
	}

	/**
	 * آرایه خانه های تخته را بر می گرداند
	 * 
	 * @return ArrayList<Tile>
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * مهره مربوط به کامپیوتر را بر می گرداند
	 * 
	 * @return Piece
	 */
	public Piece getMax() {
		return max;
	}

	/**
	 * مهره مربوط به کاربر را بر می گرداند
	 * 
	 * @return Piece
	 */
	public Piece getMin() {
		return min;
	}

	/**
	 * چک می کند این وضعیت تخته برنده دارد یا خیر
	 * 
	 * @return Boolean
	 */
	public Boolean isGoal() {
		if (max.getX() == Tile.MAX_X - 1) {
			return true;
		} else if (min.getX() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * یک خانه از جدول را بر می گرداند
	 * 
	 * @param x
	 * @param y
	 * @return Tile
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

}
