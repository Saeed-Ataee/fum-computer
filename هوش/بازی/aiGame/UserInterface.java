package aiGame;

import javax.swing.JOptionPane;

public class UserInterface {

	/**
	 * سطح بازی را از کاربر می گیرد
	 */
	public void getLevel() {
		String uCommand = JOptionPane.showInputDialog("Choose Game Level:\n1-Easy\n2-Medium\n3-Hard");
		switch (uCommand) {
		case "1":
			GameHandler.gameLevel = GameHandler.EASY_DEPTH;
			break;
		case "2":
			GameHandler.gameLevel = GameHandler.MEDIUM_DEPTH;
			break;
		case "3":
			GameHandler.gameLevel = GameHandler.HARD_DEPTH;
			break;
		default:
			JOptionPane.showMessageDialog(null, "Wrong Command! Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
			getLevel();
		}
	}

	/**
	 * دستور اصلی را از کاربر می گیرد
	 * 
	 * @return String
	 */
	public String getCommand() {
		String uCommand = JOptionPane.showInputDialog("Choose your option:\n1-move piece\n2-insert wall");
		switch (uCommand) {
		case "1":
			return getDirection();
		case "2":
			return getWallType();
		default:
			JOptionPane.showMessageDialog(null, "Wrong Command! Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
			return getCommand();
		}
	}

	/**
	 * جهت حرکت مهره را از بازیکن می گیرد
	 * 
	 * @return String
	 */
	public String getDirection() {
		String direction = JOptionPane.showInputDialog("Choose your direction:\n1-Top\n2-Left\n3-Bottom\n4-Right");

		switch (direction) {
		case "1":
			return "moveTop";
		case "2":
			return "moveLeft";
		case "3":
			return "moveBottom";
		case "4":
			return "moveRight";
		default:
			JOptionPane.showMessageDialog(null, "Wrong Command! Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
			return getDirection();
		}
	}

	/**
	 * نوع دیوار مد نظر کاربر از لحاظ عمودی یا افقی بودن را از کاربر می گیرد
	 * 
	 * @return String
	 */
	private String getWallType() {
		String type = JOptionPane.showInputDialog("Choose the wall type:\n1-Horizontal\n2-Vertical");
		switch (type) {
		case "1":
			return getHorizontalWallCoordinates();
		case "2":
			return getVerticalWallCoordinates();
		default:
			JOptionPane.showMessageDialog(null, "Wrong Command! Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
			return getWallType();
		}
	}

	/**
	 * محل قرارگیری دیوار افقی را از کاربر می گیرد
	 * 
	 * 11=H --> H
	 * 
	 * @return String
	 */
	private String getHorizontalWallCoordinates() {
		String rows = JOptionPane.showInputDialog("Insert row numbers with a dash between them:");

		String cols = JOptionPane.showInputDialog("Insert column numbers with a dash between them:");

		return "11-" + rows + "-" + cols;

	}

	/**
	 * محل قرارگیری دیوار عمودی را از کاربر می گیرد
	 * 
	 * 7=۷ ---> V
	 * 
	 * @return String
	 */
	private String getVerticalWallCoordinates() {
		String cols = JOptionPane.showInputDialog("Insert column numbers with a dash between them:");

		String rows = JOptionPane.showInputDialog("Insert row numbers with a dash between them:");

		return "7-" + cols + "-" + rows;

	}
}
