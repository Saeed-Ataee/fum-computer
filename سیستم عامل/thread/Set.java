//each bracket of input is named a set which have
//an array of numbers
//this class make the array and count the number of numbers
//in one bracket

public class Set{
    
    
    private int[] array;
    private String input;
    private int counter;
    
    //the constructor of a set, which get a string that come from the data analyser
    //this also set the private variables of each set
    public Set( String s ){
        counter = 0;
        input = s;
        array = new int[Run.SIZE];
        analyser();
    }
    
    //this method analyse the string passed into this class
    //and create the arraye of numbers of each set
    private void analyser(){
        int j;
        int number;
        for( int i = 1; i < input.length() - 1; i ++ ){
            j = i;
            number = 0;
            while(input.charAt(j) != ','){
                if( input.charAt(j) == ']'){
                    break;
                }
                //System.out.println("char at position " + j + " is " + input.charAt(j));
                number = number * 10 + input.charAt(j) - '0';
                j ++;
            }
            //System.out.println(number);
            //System.out.println(input.substring(i, j - i + 1));
            array[counter ++] = number;
            i = j;
        }
    }
    
    //this public methods get the private attributes of a set :
    public int getCounter(){
        return counter;
    }
    
    public int[] getArray(){
        return array;
    }
    
    public boolean isSet(){
        return (counter > 0);
    }
}
