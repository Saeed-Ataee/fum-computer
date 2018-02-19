package aiGame;

public class AiAction {

	// حرکت بعدی کامپیوتر را نگهداری می کند
	public static String maxAction = "";

	public static int MinMax(Node n) {
/*
		String iooo = "";

		for (int i = 0; i < n.getNodeCost(); i++) {
			iooo += '-';
		}
		iooo += n.getLastMove();
		Console.log(iooo);
*/
		Boolean isJump;
		Turn t = n.getTurn();

		// اگر در عمق مجاز برای پیمایش باشد
		if (n.getNodeCost() == GameHandler.gameLevel) {
			return n.getHeuristic();
		}

		if (t == Turn.Computer) {

			Boolean status;
			Board bb = copyBoard(n.getBoard());

			// move piece
			// --------------------------------------------------------------------------------------top
			isJump = false;
			if (bb.getMax().getX() - 1 == bb.getMin().getX() && bb.getMax().getY() == bb.getMin().getY()) {
				status = Action.jumpPiece(bb, t, 't');
				isJump = true;
			} else {
				status = Action.moveTop(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "jumpTop");
				} else {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "top");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------left
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getY() - 1 == bb.getMin().getY() && bb.getMax().getX() == bb.getMin().getX()) {
				status = Action.jumpPiece(bb, t, 'l');
				isJump = true;
			} else {
				status = Action.moveLeft(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "jumpLeft");
				} else {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "left");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------bottom
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getX() + 1 == bb.getMin().getX() && bb.getMax().getY() == bb.getMin().getY()) {
				status = Action.jumpPiece(bb, t, 'b');
				isJump = true;
			} else {
				status = Action.moveBottom(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "jumpBottom");
				} else {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "bottom");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------right
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getY() + 1 == bb.getMin().getY() && bb.getMax().getX() == bb.getMin().getX()) {
				status = Action.jumpPiece(bb, t, 'r');
				isJump = true;
			} else {
				status = Action.moveRight(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "jumpRight");
				} else {
					newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1, "right");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------

			// insert wall
			// --------------------------------------------------------------------------------------
			for (int i = 0; i < Tile.MAX_X - 1; i++) {
				for (int j = 0; j < Tile.MAX_Y - 1; j++) {
					bb = copyBoard(n.getBoard());
					status = Action.setHorizontalWall(bb, i, i + 1, j, j + 1);
					if (status) {
						Node newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1,
								"h-" + i + "-" + (i + 1) + "-" + j + "-" + (j + 1));
						n.setAlfaBeta(MinMax(newNode));

						if (doPruning(n)) {
							return n.getAlfaBetaValue();
						}

						if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
							maxAction = newNode.getLastMove();
						}
					}
					// **********************************
					bb = copyBoard(n.getBoard());
					status = Action.setVerticalWall(bb, i, i + 1, j, j + 1);
					if (status) {
						Node newNode = new Node(n, bb, Turn.User, n.getNodeCost() + 1,
								"v-" + i + "-" + (i + 1) + "-" + j + "-" + (j + 1));
						n.setAlfaBeta(MinMax(newNode));

						if (doPruning(n)) {
							return n.getAlfaBetaValue();
						}

						if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
							maxAction = newNode.getLastMove();
						}
					}
				}
			}
			// --------------------------------------------------------------------------------------

		} else {

			Boolean status;
			Board bb = copyBoard(n.getBoard());

			// move piece
			// --------------------------------------------------------------------------------------top
			isJump = false;
			if (bb.getMax().getX() - 1 == bb.getMin().getX() && bb.getMax().getY() == bb.getMin().getY()) {
				status = Action.jumpPiece(bb, t, 't');
				isJump = true;
			} else {
				status = Action.moveTop(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "jumpTop");
				} else {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "top");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------left
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getY() - 1 == bb.getMin().getY() && bb.getMax().getX() == bb.getMin().getX()) {
				status = Action.jumpPiece(bb, t, 'l');
				isJump = true;
			} else {
				status = Action.moveLeft(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "jumpLeft");
				} else {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "left");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}
				
				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------bottom
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getX() + 1 == bb.getMin().getX() && bb.getMax().getY() == bb.getMin().getY()) {
				status = Action.jumpPiece(bb, t, 'b');
				isJump = true;
			} else {
				status = Action.moveBottom(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "jumpBottom");
				} else {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "bottom");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------right
			isJump = false;
			bb = copyBoard(n.getBoard());
			if (bb.getMax().getY() + 1 == bb.getMin().getY() && bb.getMax().getX() == bb.getMin().getX()) {
				status = Action.jumpPiece(bb, t, 'r');
				isJump = true;
			} else {
				status = Action.moveRight(bb, t);
			}
			if (status) {
				Node newNode;
				if (isJump) {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "jumpRight");
				} else {
					newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1, "right");
				}
				n.setAlfaBeta(MinMax(newNode));

				if (doPruning(n)) {
					return n.getAlfaBetaValue();
				}

				if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
					maxAction = newNode.getLastMove();
				}
			}
			// --------------------------------------------------------------------------------------

			// insert wall
			// --------------------------------------------------------------------------------------
			for (int i = 0; i < Tile.MAX_X - 1; i++) {
				for (int j = 0; j < Tile.MAX_Y - 1; j++) {
					bb = copyBoard(n.getBoard());
					status = Action.setHorizontalWall(bb, i, i + 1, j, j + 1);
					if (status) {
						Node newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1,
								"h-" + i + "-" + (i + 1) + "-" + j + "-" + (j + 1));
						n.setAlfaBeta(MinMax(newNode));
						
						if (doPruning(n)) {
							return n.getAlfaBetaValue();
						}

						if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
							maxAction = newNode.getLastMove();
						}
					}
					// **********************************
					bb = copyBoard(n.getBoard());
					status = Action.setVerticalWall(bb, i, i + 1, j, j + 1);
					if (status) {
						Node newNode = new Node(n, bb, Turn.Computer, n.getNodeCost() + 1,
								"v-" + i + "-" + (i + 1) + "-" + j + "-" + (j + 1));
						n.setAlfaBeta(MinMax(newNode));

						if (doPruning(n)) {
							return n.getAlfaBetaValue();
						}

						if (n.getNodeCost() == 0 && n.getAlfaBetaValue() == newNode.getAlfaBetaValue()) {
							maxAction = newNode.getLastMove();
						}
					}
				}
			}
			// --------------------------------------------------------------------------------------

		}
		return n.getAlfaBetaValue();
	}

	/**
	 * یک کپی از تخته پاس داده شده را می سازد
	 * 
	 * @param b
	 * @return Board
	 */
	public static Board copyBoard(Board b) {
		Board bb = new Board();

		bb.createDefaultBoard();

		bb.setMaxPosition(b.getMax().getX(), b.getMax().getY());
		bb.setMinPosition(b.getMin().getX(), b.getMin().getY());

		Tile[][] bbTiles = bb.getTiles();
		Tile[][] bTiles = b.getTiles();

		for (int i = 0; i < Tile.MAX_X; i++) {
			for (int j = 0; j < Tile.MAX_Y; j++) {
				bbTiles[i][j].clone(bTiles[i][j]);
			}
		}
		return bb;
	}

	/**
	 * هرس آلفا-بتا را انجام می دهد اگر گره مکس باشد و یکی از پدران مقدار بتای کمتری
	 * از آلفا داشته باشد باید هرس شود اگر گره مین باشد و یکی از پدران دارای آلفای
	 * بیشتری از بتای این گره باشد باید هرس شود
	 * 
	 * @param n
	 * @return Boolean
	 */
	public static Boolean doPruning(Node n) {
		Node x = n.parent;
		if (n.getTurn() == Turn.Computer) {
			while (x != null) {
				if (x.getTurn() == Turn.User && x.getAlfaBetaValue() <= n.getAlfaBetaValue()) {
					return true;
				}
				x = x.parent;
			}
		} else {
			while (x != null) {
				if (x.getTurn() == Turn.Computer && x.getAlfaBetaValue() >= n.getAlfaBetaValue()) {
					return true;
				}
				x = x.parent;
			}
		}
		return false;
	}
}
