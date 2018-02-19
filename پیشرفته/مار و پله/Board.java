import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

public class Board extends JPanel {

    private int numberOfCells;
    private int root;
    private Cell[] board;
    private int height, width;
    private int gapFromBorders = 2;
    private int widthOfPanel, heightOfPanel;

    public Board() {
        this.setBackground(Color.yellow);
        board = new Cell[200];
        WidthHeightCalculator();
        this.setSize(widthOfPanel, heightOfPanel);
        drawBoard();
    }

    public Board(int n, int ww, int hh) {
        this.setBackground(Color.yellow);
        setWidthHeight(ww, hh);
        setNumberOfCells(n);
        this.setSize(widthOfPanel, heightOfPanel);
        WidthHeightCalculator();
        board = new Cell[200];
        drawBoard();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                WidthHeightCalculator();
                removeAll();
                drawBoard();
            }
        });
    }

    public void setWidthHeight(int ww, int hh) {
        widthOfPanel = ww;
        heightOfPanel = hh;
    }

    public void setNumberOfCells(int n) {
        if (n > 0) {
            numberOfCells = n;
        } else {
            numberOfCells = 0;
        }
        double r = Math.sqrt((double) numberOfCells);
        root = (int) r;
        this.setLayout(new GridLayout(root, root));
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void drawBoard() {
        int num = numberOfCells;
        for (int i = 0; i < root; i++) {
            int nn = 0;
            for (int j = 0; j < root; j++) {
                if (i % 2 == 1) {
                    if (nn == 0) {
                        nn = num - root + 1;
                    }
                    num--;
                    board[i] = new Cell(width, height, nn++);
                } else {
                    board[i] = new Cell(width, height, num--);
                }
                this.add(board[i]);
            }
        }
    }

    public void WidthHeightCalculator() {
        width = (this.getWidth() - 2 * gapFromBorders) / root;
        height = (this.getHeight() - 2 * gapFromBorders) / root;
    }

}
