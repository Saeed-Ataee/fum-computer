//a line of input, which have some brackets, and an integer show the time
//named Data, in this class, we send each bracket to ProcessOneSet class
//and writes the result in right row of 2D array of class FileIn

public class ProcessOneData implements Runnable {

    
    private Data data;
    private int numberOfRow;
    
    //public static int[] ArrayOfSums = new int[ARRAY_SIZE];
    
    public ProcessOneData(Data d, int p) {
        numberOfRow = p;
        data = d;
    }

    @Override
    public void run() {
        Set[] set = data.getSet();
        int i;
        for ( i = 0; set[i] != null; i++) {
//            if (i != 0) {
//                fileWriting.setContent(",");
//            }
            ProcessOneSet process = new ProcessOneSet(set[i], i, numberOfRow);
            Thread th = new Thread(process);
            th.start();
            /*try {
                th.join();
            } catch (Exception e) {
                System.out.println("Error while joining the thread in ProcessOneData class!");
            }*/
            //fileWriting.setContent(String.valueOf(process.getSum()));
        }
        FileIn.Array[ numberOfRow ][ i ] = data.getTime();
        //fileWriting.setContent("," + String.valueOf(data.getTime()) + "\n");
    }
}