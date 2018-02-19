//this class increase a static integer, in Run class that considered for time
//after each increament, this thread sleeps for SLEEP_TIME ->a final static integer for time

import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer implements Runnable{

    public static boolean endOfProgram = false;
    
    public static void killTime(){
        endOfProgram = true;
    }
    
    @Override
    public synchronized void run(){
        while (!endOfProgram) {
            Run.t++;
            //System.out.println("time : " + Run.t);
            try {
                Thread.currentThread().sleep(Run.SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
