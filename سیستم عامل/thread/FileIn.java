//this class reads the data from file, store them in a queue
//process the inputs and then writes output in file with FileWriting class

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileIn implements Runnable {

    public static final int[][] Array = new int[Run.SIZE][Run.SIZE];
    private String path;
    private File file;
    private Scanner scan;
    private Data data, lastInput;
    private Queue<Data> dataList;
    private int position;
    private FileWriting fileWriting;

    public FileIn(String s) {
        path = s;
        file = new File(path);
        dataList = new LinkedList<>();
        position = 0;

        fileWriting = new FileWriting("output.txt");

        try {
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println("An Error occured while reading the file!");
        }
    }

    //while the input file, has data, this method do these :
    //it read lines with equal time and save them in a queue
    //when the time of process is happened, it passes the data to the
    //thread of ProcessOneData, this thread process the data and save the result
    //in a static final array, defined in this class in right position
    @Override
    public void run() {
        boolean enableRead = true;
        String input;
        while (scan.hasNext()) {
            synchronized (dataList) {
                if (enableRead) {
                    input = scan.nextLine();
                    //System.out.println(input);
                    data = new Data(input);
                }
                if (dataList.isEmpty()) {
                    //positionChanger(1);
                    dataList.add(data);
                    lastInput = data;
                    data = null;
                    enableRead = true;
                } else if (lastInput.getTime() == data.getTime()) {
                    //positionChanger(1);
                    dataList.add(data);
                    lastInput = data;
                    data = null;
                    enableRead = true;
                } else if (data.getTime() <= Run.t) {
                    //positionChanger(1);
                    dataList.add(data);
                    lastInput = data;
                    data = null;
                    enableRead = true;
                } else {
                    enableRead = false;
                }
                if (!dataList.isEmpty()) {
                    if (dataList.element().getTime() <= Run.t) {
                        Data d = dataList.poll();
                        //System.out.println(d.getInput());
                    /*synchronized (dataList) {
                         for (int i = 0; i < position; i++) {
                         dataList[i] = dataList[i + 1];
                         }
                         positionChanger(-1);
                         }*/
                        ProcessOneData processOneData = new ProcessOneData(d, position++);
                        Thread t = new Thread(processOneData);
                        t.start();
                        /*
                         try{
                         t.join();
                         }catch( Exception e){
                         System.out.println("Error while joining thread in FileIn");
                         }*/

                        /*for (int i = 0; i < d.getCounter(); i++) {
                         if( i > 0 ){
                         fileWriting.setContent(",");
                         }
                         System.out.println(i + " : " + processOneData.ArrayOfSums[ i ]);
                         fileWriting.setContent(String.valueOf(processOneData.ArrayOfSums[ i ]));
                        
                         }
                         fileWriting.setContent(",");
                         fileWriting.setContent(String.valueOf(d.getTime()));
                         fileWriting.setContent("\n");*/
                    }
                }
            }
        }
        
        //if the last data, doesn't process, data has value, so we do process on it
        if (data != null) {
            ProcessOneData processOneData = new ProcessOneData(data, position++);
            Thread t = new Thread(processOneData);
            t.start();
        }
        
        //after all processes on input file, we write the output, sets 
        //in array of this class in output file
        for (int i = 0; i < position; i++) {
            for (int j = 0; Array[i][j] != 0; j++) {
                //System.out.print(Array[i][j] + " - ");
                if (j > 0) {
                    fileWriting.setContent(",");
                }
                fileWriting.setContent(String.valueOf(Array[i][j]));

            }
            fileWriting.setContent("\n");
        }
        
        //after processing the whole file, we kill the timer, because of
        //end of program
        Timer.killTime();
    }
}
