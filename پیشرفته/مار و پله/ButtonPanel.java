import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

    private int widthOfPanel, heightOfPanel;
    private JButton diceButton;
    private Dice dice;
    private int numberOfDice;
    private Cell diceNumber;

    public ButtonPanel(int w, int h) {
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(4, 2));
        diceNumber = new Cell(50, 50, numberOfDice);
        dice = new Dice();
        numberOfDice = dice.getValue();
        diceButton = new JButton("Dice");
        diceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dice = new Dice();
                numberOfDice = dice.getValue();
                drawDicePanel();
            }
        });
        setWidthHeight(w, h);
        this.add(diceButton);
        this.add(new Label("Number Of Dice"));
        drawDicePanel();
    }

    public ButtonPanel() {
        this.setBackground(Color.red);
        setLayout(new GridBagLayout());
        dice = new Dice();
        numberOfDice = dice.getValue();
        diceButton = new JButton("Dice");
        diceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dice = new Dice();
                numberOfDice = dice.getValue();
                drawDicePanel();
            }
        });
        this.add(new Label("Number Of Dice"));
        drawDicePanel();
        this.add(diceButton);
    }

    public void setWidthHeight(int w, int h) {
        widthOfPanel = w;
        heightOfPanel = h;
        this.setSize(widthOfPanel, heightOfPanel);
    }

    private void drawDicePanel() {
        try{
            remove(diceNumber);
        }catch(Exception e){}
        diceNumber = new Cell(60, 60, numberOfDice);
        this.add(diceNumber);
        revalidate();
    }

}
