package aiP1;

import java.util.*;

public class Index {

	private static List<Action> actionsList = new ArrayList<Action>();
	private static Board board;

	public static void main(String[] args) {

		// تعریف گره های آغازین و نهایی
		Node initialNode, goalNode;

		// محتوای فایل ورودی
		String fileContent;

		// خواندن اطلاعات فایل
		FileReader fReader = new FileReader("inp2.txt");
		fileContent = fReader.getFileContent();

		// پردازش محتوای فایل و گرفتن اطلاعات مهره ها و ابعاد تخته از آن
		FileContentAnalyser fAnalyser = new FileContentAnalyser(fileContent);

		// تعیین ابعاد تخته و ایجاد آن
		board = new Board(fAnalyser.getHeight(), fAnalyser.getWidth());

		// تعیین مقادیر گره آغازین و پایانی
		initialNode = fAnalyser.getInitialNode();
		initialNode.creationCost = 0;
		goalNode = fAnalyser.getGoalNode();
		goalNode.creationCost = 0;

		// پر کردن لیست عملیات مجاز مهره ها
		actionsList.add(new Action(0, 1));
		actionsList.add(new Action(0, -1));
		actionsList.add(new Action(1, 0));
		actionsList.add(new Action(1, 1));
		actionsList.add(new Action(1, -1));
		actionsList.add(new Action(-1, 0));
		actionsList.add(new Action(-1, 1));
		actionsList.add(new Action(-1, -1));

		long startTime = System.currentTimeMillis();
		// BfsSearch bfs = new BfsSearch(board, actionsList, initialNode, goalNode);
		// DfsSearch dfs = new DfsSearch(board, actionsList, initialNode, goalNode);
		IdsSearch ids = new IdsSearch(board, actionsList, initialNode, goalNode);
		// UcsSearch ucs = new UcsSearch(board, actionsList, initialNode, goalNode);
		long lastTime = System.currentTimeMillis();
		System.out.println("Execution Time:");
		System.out.println(lastTime - startTime);
	}

}
