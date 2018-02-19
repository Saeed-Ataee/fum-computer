
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class Run {
    
    public static final int MAX_CELL = 36;
    public static final int MIN_CELL = 1;
    
    public static void main(String[] args) {
        
        int height, width;
        width = height = 500;
        
        Board b = new Board(36, (int)(0.8 * width),(int)(0.8 * height));
        ButtonPanel bp = new ButtonPanel(60, 60);
        JFrame frame = new JFrame("Snake And Ladder Game");
        frame.setLayout(new GridLayout(2, 2));
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.add(b);
        frame.add(bp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
