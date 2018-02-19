package aiP1;

public class FileContentAnalyser {

	private Node initianNode = new Node(), goalNode = new Node();
	private int h, w;
	private String fileContent;

	public FileContentAnalyser(String fc) {
		fileContent = fc;
		analyser();
	}

	/**
	 * پردازش اطلاعات فایل و بیرون کشیدن مشخصات از آن
	 */
	public void analyser() {
		int i = 0;
		// گرفتن مشخصه اول تخته، تعداد ردیف های تخته
		while (fileContent.charAt(i) != ' ') {
			i++;
		}
		h = Integer.parseInt(fileContent.substring(0, i));

		// گرفتن مشخصه دوم تخته، تعداد ستون های تخته
		int j = i + 1;
		while (fileContent.charAt(j) != '\n') {
			j++;
		}
		w = Integer.parseInt(fileContent.substring(i + 1, j));

		i = j + 1;

		// گرفتن حالت اولیه تخته
		int row = 0, col = 0;
		for (int l = i; l < h * w + h + i; l++) {
			if (fileContent.charAt(l) == 'K' || fileContent.charAt(l) == 'k') {
				King king = new King();
				king.setPosition(row, col);
				initianNode.addPiece(king);
			} else if (fileContent.charAt(l) == 'P' || fileContent.charAt(l) == 'p') {
				Pawns pawns = new Pawns();
				pawns.setPosition(row, col);
				initianNode.addPiece(pawns);
			}
			col++;
			if (col == w + 1) {
				col = 0;
				row++;
			}
		}

		// گرفتن حالت نهایی تخته
		row = 0;
		col = 0;
		j = h * w + h + i + 1;
		for (int l = j; l < h * w + h + j; l++) {
			if (fileContent.charAt(l) == 'K' || fileContent.charAt(l) == 'k') {
				King king = new King();
				king.setPosition(row, col);
				goalNode.addPiece(king);
			} else if (fileContent.charAt(l) == 'P' || fileContent.charAt(l) == 'p') {
				Pawns pawns = new Pawns();
				pawns.setPosition(row, col);
				goalNode.addPiece(pawns);
			}
			col++;
			if (col == w + 1) {
				col = 0;
				row++;
			}
		}
	}
	
	/**
	 * برگرداندن حالت آغازین
	 * @return Node
	 */
	public Node getInitialNode() {
		return initianNode;
	}
	
	/**
	 * برگرداندن حالت نهایی
	 * @return Node
	 */
	public Node getGoalNode() {
		return goalNode;
	}
	
	/**
	 * برگرداندن تعداد ردیف های تخته
	 * @return int
	 */
	public int getHeight() {
		return h;
	}
	
	/**
	 * برگرداندن تعداد ستون های تخته
	 * @return int
	 */
	public int getWidth() {
		return w;
	}
}
