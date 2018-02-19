package aiP1;

public class Action {
	// مقدار تغییر در راستای محور افقی
	public int deltaX;
	// مقدار تغییر در راستای محور عمودی
	public int deltaY;
	
	public Action(int x, int y) {
		deltaX = x;
		deltaY = y;
	}
	
	/**
	 * رشته مناسب با حرکت را برمیگرداند
	 */
	public String toString() {
		return "(" + deltaX + " , " + deltaY + ")";
	}
}
