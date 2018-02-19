package aiP2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import aiP2.Node;

public class IDAStar {

	private List<Action> actionsList = new ArrayList<Action>();
	private List<Node> visited = new ArrayList<Node>();
	private Stack<Node> fringeList = new Stack<Node>();
	Board board;
	private Node initialNode = new Node();
	private Node goalNode = new Node();
	private int cutoff, newCutOff, minCutOff;
	private Boolean searchComplete = false;

	// تعداد گره های تولید شده
	private int nodesCreated = 0;

	// بیشترین تعداد نود در رم
	private int maxNodesInRam = 0;

	public IDAStar(Board b, List<Action> a, Node i, Node g) {
		board = b;
		actionsList = a;
		initialNode = i;
		goalNode = g;

		// مقدار دهی اولیه برش به اندازه فاصله تا هدف + هزینه ساخت که صفر است
		newCutOff = initialNode.distanceCalc(goalNode);
		while (!searchComplete) {
			fringeList.push(initialNode);
			cutoff = newCutOff;
			minCutOff = Integer.MAX_VALUE;
			visited = new ArrayList<Node>();
			search();
		}
		System.out.println("IDAStar Search Completely Done!");
		System.out.println("Number of CutOff: " + cutoff);
		System.out.println("Number of created nodes: " + nodesCreated);
		System.out.println("Maximum nodes in RAM: " + maxNodesInRam);
	}

	// تابع جستجو
	public void search() {
		do {
			Node curr = fringeList.pop();
			if (goalTest(goalNode, curr)) {
				printTrace(curr);
				searchComplete = true;
				break;
			} else {
				maxNodesInRam = Math.max(maxNodesInRam, visited.size() + fringeList.size());
				Successor(curr);
			}
		} while (fringeList.size() != 0);
		newCutOff = minCutOff;
	}

	/**
	 * گره فعلی را دریافت می کند و تمام گره های قابل دسترس از آن را در صورت دیده
	 * نشدنشان به لیست حاشیه اضافه می کند
	 * 
	 * @param currentStatus
	 */
	public void Successor(Node currentStatus) {
		if (!visited.contains(currentStatus)) {
			List<Piece> list = currentStatus.getList();
			visited.add(currentStatus);
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < actionsList.size(); j++) {

					if (list.get(i).canDo(actionsList.get(j), board)) {

						// ------------------------------ساخت گره جدید از روی
						// قبلی-----------------------------
						Node newNode = new Node();
						for (int it = 0; it < list.size(); it++) {
							newNode.addPiece(list.get(it));
						}

						// تعیین گرهی که گره جدید از روی آن ایجاد شده است
						newNode.previousNode = currentStatus;

						// ثبت مهره حرکت کرده از گره قبلی
						newNode.movedPiece = currentStatus.getPiece(i);

						// ثبت حرکتی که موجب تغییر گره قبلی شده است
						newNode.action = actionsList.get(j);

						newNode.creationCost = currentStatus.creationCost + newNode.movedPiece.getCost();
						// ------------------------------------------------------------------------------------

						// -------------------------------گرفتن مهره قابل
						// حرکت---------------------------------
						Piece movedPiece;

						if (currentStatus.getPiece(i) instanceof King) {
							movedPiece = new King();
							movedPiece.setPosition(
									currentStatus.getPiece(i).getPosition().x + actionsList.get(j).deltaX,
									currentStatus.getPiece(i).getPosition().y + actionsList.get(j).deltaY);
						} else {
							movedPiece = new Pawns();
							movedPiece.setPosition(
									currentStatus.getPiece(i).getPosition().x + actionsList.get(j).deltaX,
									currentStatus.getPiece(i).getPosition().y + actionsList.get(j).deltaY);
						}
						// افزودن مهره حرکت کرده
						newNode.removePiece(i);
						newNode.addPiece(movedPiece);
						newNode.pieceToMove = movedPiece;
						// ----------------------------------------------------------------------------------

						if (newNode.nodeCorrectness() && !visited.contains(newNode) && newNode.creationCost <= cutoff) {
							if (newNode.distanceCalc(goalNode) + newNode.creationCost > cutoff && newNode.distanceCalc(goalNode) + newNode.creationCost < minCutOff) {
								minCutOff = newNode.distanceCalc(goalNode) + newNode.creationCost;
							}
							fringeList.push(newNode);
							nodesCreated++;
						}
					}
				}
			}
		}
	}

	/**
	 * مقایسه یک گره با گره نهایی
	 * 
	 * @param goal
	 * @param n
	 * @return Boolean
	 */
	public Boolean goalTest(Node goal, Node n) {
		return goal.nodeCompare(n);
	}

	/**
	 * چاپ توالی حرکت مهره ها برای رسیدن به هدف
	 * 
	 * @param un
	 */
	public void printTrace(Node un) {
		Stack<String> acts = new Stack<>();
		Node un2 = un;
		int counter = 1;
		while (un2.previousNode != null) {
			acts.push("-" + un2.movedPiece + " Moved With " + un2.action + " To " + un2.pieceToMove);
			un2 = un2.previousNode;
		}
		while (!acts.isEmpty()) {
			System.out.println(counter + acts.pop());
			counter++;
		}
	}
}
