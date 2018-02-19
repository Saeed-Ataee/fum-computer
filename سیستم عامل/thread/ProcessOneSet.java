//this class gets a set and calculate the sum of the set array
//this class implements Runnable, because of concurency of this action( sum )

public class ProcessOneSet implements Runnable {
    
    private Set set;
    private int sum;
    private int positionInArray, numberOfRow;
    
    
    //the constructor receive a set, a number wich is the number of row of 2D array in FileIn class
    //and a number which is the column of set that should be placed in 2D array
    public ProcessOneSet(Set s, int i, int j){
        sum = 0;
        set = s;
        positionInArray = i;
        numberOfRow = j;
    }
    
    
    //this method has a loop for calculating the sum of array datas
    //after calculating, it call the write sum to save the result in 2D array
    @Override
    public void run() {
        int[] a = set.getArray();
        int sum = 0;
        for (int i = 0; i < set.getCounter(); i++) {
            //System.out.print(" + " + a[ i ]);
            sum += a[ i ];
        }
        this.sum = sum;
        writeSum();
        //System.out.println(" = " + this.sum);
    }
    
    
    //this method writes the result of this process in a cell of 2D array of FileIn class
    public void writeSum(){
        //System.out.println("sum of index" + positionInArray + " is " + sum);
        FileIn.Array[ numberOfRow ][ positionInArray ] = sum;
        //System.out.println(ProcessOneData.ArrayOfSums[ positionInArray ]);
    }
}
