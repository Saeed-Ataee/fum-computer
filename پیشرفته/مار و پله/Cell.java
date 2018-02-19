import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Cell extends JPanel {

    private int h, w;
    private int number;
            
    public Cell( int width, int height, int n ){
        this.h = height;
        this.w = width;
        this.setSize(w, h);
        number = n;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawRect(0, 0, w, h);
        g.drawString(String.valueOf(number), (int)(w / 2), (int)(h / 2));
        //g.fillRect(0, 0, w, h);
    }
    
    public int getNumber(){
        return number;
    }
    
}
