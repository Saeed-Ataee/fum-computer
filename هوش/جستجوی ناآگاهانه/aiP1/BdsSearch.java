package aiP1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BdsSearch {
	private List<Action> actionsList = new ArrayList<Action>();
	private List<Node> visited = new ArrayList<Node>();
	private Queue<Node> forwardFringeList = new LinkedList<Node>();
	private Queue<Node> backwardFringeList = new LinkedList<Node>();
	private String turn = "FORWARD";
	Board board;
	private Node initialNode = new Node();
	private Node goalNode = new Node();

	// تعداد گره های تولید شده
	private int nodesCreated = 0;

	// بیشترین تعداد نود در رم
	private int maxNodesInRam = 0;

	public BdsSearch(Board b, List<Action> a, Node i, Node g) {
		board = b;
		actionsList = a;
		initialNode = i;
		goalNode = g;
		forwardFringeList.add(initialNode);
		search();
		System.out.println("Bds Search Completely Done!");
		System.out.println("Number of created nodes: " + nodesCreated);
		System.out.println("Maximum nodes in RAM: " + maxNodesInRam);
	}

	// تابع جستجوی سطح اول
	public void search() {
		do {
			if (turn.equals("FORWARD")) {
				Node curr = forwardFringeList.poll();
				if (goalTest(goalNode, curr)) {
					printTrace(curr);
					break;
				} else {
					maxNodesInRam = Math.max(maxNodesInRam,
							visited.size() + forwardFringeList.size() + backwardFringeList.size());
					Successor(curr);
				}
			} else if (turn.equals("BACKWARD")) {
				Node curr = backwardFringeList.poll();
				if (goalTest(goalNode, curr)) {
					printTrace(curr);
					break;
				} else {
					maxNodesInRam = Math.max(maxNodesInRam,
							visited.size() + forwardFringeList.size() + backwardFringeList.size());
					Successor(curr);
				}
			}
		} while (forwardFringeList.size() != 0);
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
						newNode.creationCost = currentStatus.creationCost + 1;

						// تعیین گرهی که گره جدید از روی آن ایجاد شده است
						newNode.previousNode = currentStatus;

						// ثبت مهره حرکت کرده از گره قبلی
						newNode.movedPiece = currentStatus.getPiece(i);

						// ثبت حرکتی که موجب تغییر گره قبلی شده است
						newNode.action = actionsList.get(j);
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

						if (newNode.nodeCorrectness() && !visited.contains(newNode)) {
							nodesCreated++;
							forwardFringeList.add(newNode);
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
