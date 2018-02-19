import java.util.Random;

public class Dice {
    
    private int value;
    
    public Dice(){
        RandomValue();
    }
    
    private void RandomValue(){
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
    }
    
    public int getValue(){
        return value;
    }
}
