package aiGame;

import javax.swing.JOptionPane;

public class GameHandler {
	public static int EASY_DEPTH = 2;
	public static int MEDIUM_DEPTH = 4;
	public static int HARD_DEPTH = 6;

	public static int gameLevel;

	Board b;

	Turn t;

	Node initialNode;

	UserInterface ui;

	public GameHandler() {
		b = new Board();

		b.createDefaultBoard();

		t = Turn.User;
		initialNode = new Node(null, b, Turn.Computer, 0, "");

		ui = new UserInterface();
		b.setPieces();
		b.drawBoard();

		ui.getLevel();

		start();
	}

	private void start() {
		String task;
		Boolean status = false;
		while (true) {
			if (t == Turn.User) {
				task = ui.getCommand();
				switch (task) {
				case "moveTop":
					if (b.getMax().getX() + 1 == b.getMin().getX() && b.getMax().getY() == b.getMin().getY()) {
						status = Action.jumpPiece(b, t, 't');
					} else {
						status = Action.moveTop(b, t);
					}
					break;
				case "moveLeft":
					if (b.getMax().getY() + 1 == b.getMin().getY() && b.getMax().getX() == b.getMin().getX()) {
						status = Action.jumpPiece(b, t, 'l');
					} else {
						status = Action.moveLeft(b, t);
					}
					break;
				case "moveBottom":
					if (b.getMax().getX() - 1 == b.getMin().getX() && b.getMax().getY() == b.getMin().getY()) {
						status = Action.jumpPiece(b, t, 'b');
					} else {
						status = Action.moveBottom(b, t);
					}
					break;
				case "moveRight":
					if (b.getMax().getY() - 1 == b.getMin().getY() && b.getMax().getX() == b.getMin().getX()) {
						status = Action.jumpPiece(b, t, 'r');
					} else {
						status = Action.moveRight(b, t);
					}
					break;
				default:
					int[] arr = stringParser(task);
					// vertical
					if (arr[0] == 7) {
						status = Action.setVerticalWall(b, arr[1], arr[2], arr[3], arr[4]);
					}
					// horizontal
					else {
						status = Action.setHorizontalWall(b, arr[1], arr[2], arr[3], arr[4]);
					}
					break;
				}
				if (status) {
					if (b.isGoal()) {
						Console.log("You Win!");
						break;
					} else {
						initialNode.setTurn(Turn.Computer);
						t = Turn.Computer;
						b.drawBoard();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Command! Try Again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				AiAction.MinMax(initialNode);
				doAction(initialNode, AiAction.maxAction);
				if (b.isGoal()) {
					Console.log("Computer Win!");
					break;
				} else {
					initialNode.setTurn(Turn.User);
					t = Turn.User;
					b.drawBoard();
				}
			}
		}
		b.drawBoard();
	}

	/**
	 * یک رشته از اعداد که با - از هم جدا شده اند را دریافت می کند و آرایه ای از
	 * اجزا را بر می گرداند
	 * 
	 * @param s
	 * @return int[]
	 */
	public int[] stringParser(String s) {
		String[] splited = s.split("-");
		int[] output = new int[5];
		try {
			for (int i = 0; i < splited.length; i++) {
				output[i] = Integer.parseInt(splited[i]);
			}
			return output;
		} catch (Exception e) {
			return null;
		}
	}

	public void doAction(Node n, String s) {
		Board b = n.getBoard();
		if (s.equals("top")) {
			b.setMaxPosition(b.getMax().getX() - 1, b.getMax().getY());
		} else if (s.equals("left")) {
			b.setMaxPosition(b.getMax().getX(), b.getMax().getY() - 1);
		} else if (s.equals("bottom")) {
			b.setMaxPosition(b.getMax().getX() + 1, b.getMax().getY());
		} else if (s.equals("right")) {
			b.setMaxPosition(b.getMax().getX(), b.getMax().getY() + 1);
		} else if (s.equals("jumpTop")) {
			b.setMaxPosition(b.getMax().getX() - 2, b.getMax().getY());
		} else if (s.equals("jumpLeft")) {
			b.setMaxPosition(b.getMax().getX(), b.getMax().getY() - 2);
		} else if (s.equals("jumpBottom")) {
			b.setMaxPosition(b.getMax().getX() + 2, b.getMax().getY());
		} else if (s.equals("jumpRight")) {
			b.setMaxPosition(b.getMax().getX(), b.getMax().getY() + 2);
		} else {
			int[] output = new int[4];
			String[] ss = s.split("-");
			if (ss[0] == "h") {
				for (int i = 1; i < ss.length; i++) {
					output[i] = Integer.parseInt(ss[i]);
				}
				b.setHorizontalWall(output[0], output[1], output[2], output[3]);
			} else {
				for (int i = 1; i < ss.length; i++) {
					output[i - 1] = Integer.parseInt(ss[i]);
				}
				b.setVerticalWall(output[0], output[1], output[2], output[3]);
			}
		}
	}
}
