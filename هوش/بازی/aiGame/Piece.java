package aiGame;

import javax.swing.JOptionPane;

public class Piece {

	public static final int MAX_WALL_NUMBER = 10;

	// شماره ردیف مهره
	private int x;

	// شماره ستون مهره
	private int y;

	// جهت هدف مهره
	private int target;

	// نام مهره
	private char name;

	// تعداد دیوار هایی که هر بازیکن در اختیار دارد
	private int wallNumber;

	public Piece() {
		this.name = ' ';
		this.x = 0;
		this.y = 0;
		this.wallNumber = MAX_WALL_NUMBER;
	}

	public Piece(char n, int x, int y) {
		setName(n);
		setX(x);
		setY(y);
	}

	/**
	 * تنظیم کردن شماره ردیف مهره
	 * 
	 * @param x
	 */
	public void setX(int x) {
		if (x > -1 && x < Tile.MAX_X) {
			this.x = x;
		} else {
			this.x = 0;
		}
	}

	/**
	 * تنظیم کردن شماره ستون مهره
	 * 
	 * @param y
	 */
	public void setY(int y) {
		if (y > -1 && y < Tile.MAX_Y) {
			this.y = y;
		} else {
			this.y = 0;
		}
	}

	/**
	 * تنظیم کردن هدف مهره که به سمت بالا (0) حرکت کند یا پایین
	 * 
	 * @param t
	 */
	public void setTarget(int t) {
		if (t == 0) {
			target = 0;
		} else {
			target = Tile.MAX_X;
		}
	}

	/**
	 * تعداد دیوار های در اختیار بازیکن را یک عدد کاهش می دهد
	 */
	public void decreaseWallNumber() {
		if (this.wallNumber > 0) {
			this.wallNumber--;
		} else {
			JOptionPane.showMessageDialog(null, "You can't insert more walls!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * شماره ردیف مهره را بر می گرداند
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * شماره ستون مهره را بر می گرداند
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * هدف مهره را بر می گرداند
	 * 
	 * @return int
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * نام مهره را تنظیم می کند
	 * 
	 * @param n
	 */
	public void setName(char n) {
		this.name = n;
	}

	/**
	 * نام مهره را بر می گرداند
	 * 
	 * @return char
	 */
	public char getName() {
		return name;
	}

	/**
	 * تعداد دیوار های موجود برای کاربر را بر می گرداند
	 * 
	 * @return int
	 */
	public int getWallNumber() {
		return wallNumber;
	}
}
