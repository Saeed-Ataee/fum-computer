package aiP2;

import java.util.*;
import java.util.ArrayList;

public class Node {

	// لیست مهره های درون گره
	public List<Piece> piecesList = new ArrayList<Piece>();

	// گرهی که این گره از تغییر آن بدست آمده است
	public Node previousNode;

	// مهره ای از گره قبلی که تغییر مکان داده است
	public Piece pieceToMove;

	// مهره ای از گره قبلی که تغییر کرده است
	public Piece movedPiece;

	// میزان تغییری که مهره انجام داده است
	public Action action;

	// هزینه ساخته شدن گره
	public int creationCost;

	@Override
	public boolean equals(Object obj) {
		return nodeCompare((Node) obj);
	}

	@Override
	public int hashCode() {
		return 237 + Arrays.deepHashCode(piecesList.toArray());
	}

	public int compareTo(Node node) {
		return this.creationCost - node.creationCost;
	}

	/**
	 * اضافه کردن مهره به گره
	 * 
	 * @param p
	 */
	public void addPiece(Piece p) {
		piecesList.add(p);
		piecesList.sort(Piece.PieceComparator);
	}

	/**
	 * حذف کردن مهره شماره پاس داده شده از گره
	 * 
	 * @param index
	 */
	public Piece removePiece(int index) {
		return piecesList.remove(index);
	}

	/**
	 * تغییر اطلاعات مهره خانه ایندکس از لیست مهره ها
	 * 
	 * @param index
	 *            شماره خانه مهره
	 * @param x
	 *            مختصات جدید، شماره ستون
	 * @param y
	 *            مختصات جدید، شماره ردیف
	 */
	public void changePiece(int index, int x, int y) {
		piecesList.get(index).setPosition(x, y);
	}

	/**
	 * مهره خانه ایندکس از گره را بر میگرداند
	 * 
	 * @param index
	 * @return Piece
	 */
	public Piece getPiece(int index) {
		return piecesList.get(index);
	}

	/**
	 * برگرداندن لیست مهره های گره
	 * 
	 * @return
	 */
	public List<Piece> getList() {
		return piecesList;
	}

	/**
	 * برگرداندن رشته ای مناسب برای نمایش مشخصات گره
	 */
	public String toString() {
		String output = "";
		for (Piece pi : piecesList) {
			output += pi.getName() + " : (" + pi.getPosition().x + " , " + pi.getPosition().y + ")\n";
		}
		return output;
	}

	/**
	 * مقایسه گره فعلی با یک گره دیگر
	 * 
	 * @param n
	 * @return Boolean
	 */
	public Boolean nodeCompare(Node n) {
		for (Piece pi : piecesList) {
			int sw = 0;
			for (Piece np : n.getList()) {
				if (np.getName().equals(pi.getName())) {
					if (np.getPosition().x == pi.getPosition().x && np.getPosition().y == pi.getPosition().y) {
						sw = 1;
					}
				}
				if (sw == 1) {
					break;
				}
			}
			if (sw == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * چک می کند گره از لحاظ موقعیت قرارگیری مهره ها درست است یا خیر
	 * 
	 * @return Boolean
	 */
	public Boolean nodeCorrectness() {
		for (int i = 0; i < piecesList.size() - 1; i++) {
			for (int j = i + 1; j < piecesList.size(); j++) {

				// اگر مهره اول شاه باشد
				if (piecesList.get(i).getName().equals(new King().getName())) {

					// اگر مهره دوم شاه باشد
					if (piecesList.get(j).getName().equals(new King().getName())) {

						// اگر اختلاف عمودی ۲ شاه کمتر از ۲ باشد، یعنی ۰ یا ۱ باشد
						if (Math.abs(piecesList.get(i).getPosition().x - piecesList.get(j).getPosition().x) < 2) {

							// اگر اختلاف افقی ۲ شاه کمتر از ۲ باشد، یعنی ۰ یا ۱ باشد
							if (Math.abs(piecesList.get(i).getPosition().y - piecesList.get(j).getPosition().y) < 2) {
								return false;
							}
						}
					}

					// اگر مهره دوم شاه نباشد
					else {

						// اگر ۲ مهره در یک مکان باشند
						if (Math.abs(piecesList.get(i).getPosition().x - piecesList.get(j).getPosition().x) < 1 && Math
								.abs(piecesList.get(i).getPosition().y - piecesList.get(j).getPosition().y) < 1) {
							return false;
						}
					}
				}

				// اگر مهره اول شاه نباشد
				else {

					// اگر ۲ مهره در یک مکان باشند
					if (Math.abs(piecesList.get(i).getPosition().x - piecesList.get(j).getPosition().x) < 1
							&& Math.abs(piecesList.get(i).getPosition().y - piecesList.get(j).getPosition().y) < 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * محاسبه فاصله گره فعلی با گره پاس داده شده
	 * 
	 * @param node
	 * @return int
	 */
	public int distanceCalc(Node node) {
		int distance = 0;
		for (int i = 0; i < piecesList.size(); i++) {
			
			// اگر مکان مهره فعلی در دو گره یکسان باشد
			if (piecesList.get(i).getPosition().x == node.getPiece(i).getPosition().x
					&& piecesList.get(i).getPosition().y == node.getPiece(i).getPosition().y) {

			}
			
			// اگر مهره فعلی شاه باشد
			if (piecesList.get(i) instanceof King) {
				distance++;
			} 
			
			// اگر مهره فعلی سرباز باشد
			else {
				distance += (piecesList.get(i).getPosition().x * 2);
			}
		}
		return distance;
	}
}
