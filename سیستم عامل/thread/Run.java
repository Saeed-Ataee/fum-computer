//the main class of program, starts two thread, timer and file reader

public class Run {
    
    //t is defined for time, counted from zero at the begining of project
    public static int t;
    //a static final integer, which is the sleep time of every palse of time
    public static final int SLEEP_TIME = 50;
    //a static final integer, which defines for size of arrays in the project
    public static final int SIZE = 2000;
    
    public static void main(String[] args) {

        t = 0;
        FileIn fileIn = new FileIn("input.txt");
        Timer timer = new Timer();

        Thread threadOfFile = new Thread(fileIn);
        Thread threadOfTime = new Thread(timer);

        threadOfTime.start();
        threadOfFile.start();
        
    }
}
