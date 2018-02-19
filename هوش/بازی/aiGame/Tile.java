package aiGame;

public class Tile {

	public static final int MAX_X = 9;

	public static final int MAX_Y = 9;

	// محل قرارگیری خانه در تخته - شماره ردیف
	private int x;

	// محل قرارگیری خانه در تخته - شماره ستون
	private int y;

	// محتوای خانه که اسم بازیکنان می شود
	private char name;

	// سمت بالای خانه
	private Side top;

	// سمت چپ خانه
	private Side left;

	// سمت پایین خانه
	private Side bottom;

	// سمت راست خانه
	private Side right;

	public Tile() {

	}

	public Tile(int x, int y) {
		setX(x);
		setY(y);

		// مقداردهی پیشفرض اطراف خانه
		this.right = Side.Nothing;
		this.bottom = Side.Nothing;
		this.left = Side.Nothing;
		this.top = Side.Nothing;
	}

	public Tile(int x, int y, Side t, Side l, Side b, Side r) {
		setX(x);
		setY(y);

		// مقداردهی اطراف خانه
		this.right = r;
		this.bottom = b;
		this.left = l;
		this.top = t;
	}

	/**
	 * تنظیم مقدار شماره ردیف خانه
	 * 
	 * @param x
	 */
	public void setX(int x) {
		if (x > -1 && x < MAX_X) {
			this.x = x;
		} else {
			this.x = 0;
		}
	}

	/**
	 * تنظیم مقدار شماره ستون خانه
	 * 
	 * @param y
	 */
	public void setY(int y) {
		if (y > -1 && y < MAX_Y) {
			this.y = y;
		} else {
			this.y = 0;
		}
	}

	/**
	 * شماره ردیف خانه را بر می گرداند
	 * 
	 * @return int
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * شماره ستون خانه را بر می گرداند
	 * 
	 * @return int
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * سمت بالای خانه را بر می گرداند
	 * 
	 * @return Side
	 */
	public Side getTopSide() {
		return this.top;
	}

	/**
	 * سمت چپ خانه را بر می گرداند
	 * 
	 * @return Side
	 */
	public Side getLeftSide() {
		return this.left;
	}

	/**
	 * سمت پایین خانه را بر می گرداند
	 * 
	 * @return Side
	 */
	public Side getBottomSide() {
		return this.bottom;
	}

	/**
	 * سمت راست خانه را بر می گرداند
	 * 
	 * @return Side
	 */
	public Side getRightSide() {
		return this.right;
	}

	/**
	 * تنظیم مقدار بالای خانه
	 * 
	 * @param t
	 */
	public void setTopSide(Side t) {
		this.top = t;
	}

	/**
	 * تنظیم مقدار چپ خانه
	 * 
	 * @param t
	 */
	public void setLeftSide(Side t) {
		this.left = t;
	}

	/**
	 * تنظیم مقدار پایین خانه
	 * 
	 * @param t
	 */
	public void setBottomSide(Side t) {
		this.bottom = t;
	}

	/**
	 * تنظیم مقدار راست خانه
	 * 
	 * @param t
	 */
	public void setRightSide(Side t) {
		this.right = t;
	}

	/**
	 * تنظیم محتوای خانه
	 * 
	 * @param c
	 */
	public void setContent(char c) {
		this.name = c;
	}

	/**
	 * محتوای خانه را بر می گرداند
	 * 
	 * @return String
	 */
	public char getContent() {
		return name;
	}

	/**
	 * رشته مناسب خانه را بر می گرداند
	 */
	public String toString() {
		return "Tile: " + name + " at " + this.x + ", " + this.y + ". top: " + getTopSide() + ", left: " + getLeftSide()
				+ ", bottom: " + getBottomSide() + ", right: " + getRightSide();
	}

	public void clone(Tile tile) {
		x = tile.x;
		y = tile.y;
		name = tile.name;
		top = tile.top;
		left = tile.left;
		bottom = tile.bottom;
		right = tile.right;
	}
}
