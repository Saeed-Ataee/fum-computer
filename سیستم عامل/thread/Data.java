//each line of input named data, each data contains some sets and a number which is
//the time of processing, processing means calculating the sum of each set
//this class implements Comparable<Data>, because of Queue in FileIn class

public class Data implements Comparable<Data> {


    private int time;
    private Set[] set;
    private int counter;
    private String input;

    //the constructor which gets a line of input and analyse it into an array of
    //sets and an integer which shows the time
    public Data(String s) {
        input = s;
        set = new Set[Run.SIZE];
        counter = 0;
        analyser();
    }
    
    //a method for testing the input file passed into this class
    public String getInput(){
        return input;
    }
    
    //this method analyse input string
    private void analyser() {
        int j, t = 0;
        for (int i = 1; i < input.length() - 1; i++) {
            if (input.charAt(i) == ',') {
                i++;
            }
            if (input.charAt(i) == '[') {
                i++;
                j = i;
                while (input.charAt(j) != ']') {
                    j++;
                }
                set[counter++] = new Set(input.substring(i - 1, j + 1));
            }
        }
        for (j = input.length() - 1; input.charAt(j) != ','; j --);
        time = Integer.parseInt(input.substring(j + 1, input.length() - 1));
        //System.out.println(time);
    }

    
    //some methods for returning the private attributes of each data :
    Set[] getSet() {
        return set;
    }

    public int getTime(){
        return time;
    }

    public int getCounter(){
        return counter;
    }
    
    @Override
    public int compareTo(Data o) {
        if( this.time < o.time ){
            return -1;
        } else if( this.time > o.time ){
            return 1;
        }
        return 0;
    }
    
}
