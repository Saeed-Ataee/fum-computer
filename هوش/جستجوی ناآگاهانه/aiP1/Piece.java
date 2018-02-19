package aiP1;

import java.util.Comparator;

public abstract class Piece {

	/*
	 * اسم مهره را برمیگرداند
	 * 
	 * @return String
	 */
	public abstract String getName();

	/*
	 * مکان فعلی مهره را برمیگرداند
	 * 
	 * @return Position
	 */
	public abstract Position getPosition();

	/**
	 * مکان فعلی مهره را تنظیم می کند
	 * 
	 * @param x
	 *            شماره ستون مهره
	 * @param y
	 *            شماره ردیف مهره
	 */
	public abstract void setPosition(int x, int y);

	/**
	 * مهره مجاز به انجام حرکت پاس داده شده هست یا خیر
	 * 
	 * @param action
	 *            حرکت مورد نظر
	 * @param board
	 *            تخته بازی
	 * @return Boolean
	 */
	public abstract Boolean canDo(Action action, Board board);

	/**
	 * رشته مناسب با مهره را برمی گرداند
	 */
	public abstract String toString();

	/**
	 * مقایسه دو مهره با هم بر اساس نام و مکان قرار گیری در صفحه
	 * 
	 * @param p
	 * @return
	 */
	public int compareTo(Piece p) {
		if (this.getName().equals(p.getName())) {
			if (this.getPosition().x == p.getPosition().x) {
				if (this.getPosition().y == p.getPosition().y) {
					return 0;
				} else if (this.getPosition().y > p.getPosition().y) {
					return 1;
				} else {
					return -1;
				}
			} else if (this.getPosition().x > p.getPosition().x) {
				return 1;
			} else if (this.getPosition().x < p.getPosition().x) {
				return -1;
			}
		} else {
			return this.getName().compareTo(p.getName());
		}
		return 0;
	}

	/**
	 * تعریف یک مقایسه گر برای مرتب سازی مهره ها
	 */
	public static Comparator<Piece> PieceComparator = new Comparator<Piece>() {

		public int compare(Piece piece1, Piece piece2) {

			// ascending order
			return piece1.compareTo(piece2);

		}

	};
}
