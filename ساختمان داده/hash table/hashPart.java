import java.io.File;
import java.util.Scanner;

public class hashPart {

    private final int SIZE = 100;
    private int[] table = new int[SIZE];

    //get the name of file and insert all file datas in table
    int fileIn(String s){
        File file = new File(s);
        try{
        Scanner scan = new Scanner(file);
        for (int i = 0; i < SIZE; i++) {
            insert(scan.nextInt());
        }
        }catch(Exception e ){
            return 1;
        }
        return 0;
    }

    //insert the integer into right position in table
    void insert(int a) {
        int j = hashFunction(a);
        int k = j;
        while (table[j] != 0) {
            j++;
            if (j == SIZE) {
                j = 0;
            }
            if (j == k) {
                System.out.println("full");
                break;
            }
        }
        table[j] = a;
    }

    //start from the right position of input and after finding the first right value, it delete it and re-assign the table
    void delete(int a) {
        int j = hashFunction(a);
        if (table[j] == 0) {
            System.out.println("not found");
            return;
        } else {
            while (table[j] != a) {
                j++;
            }
            table[j] = 0;
        }
        re_assign(j);
    }

    
    //search and get the first position of input
    void search(int a) {
        int j = hashFunction(a);
        while (table[j] != a) {
            j++;
            if (j == SIZE) {
                j = 0;
            }
            if (j == hashFunction(a)) {
                System.out.println("not found");
            }
        }
        System.out.println("position of the first right value is : " + j);
    }

    //the hashFunction that returns the input%size
    int hashFunction(int a) {
        return a % SIZE;
    }
    
    //start from the position and re-assign the table
    void re_assign(int pos) {
        int i = pos + 1;
        int empty = pos;

        while (table[i] != 0 && i != pos) {
            if (hashFunction(table[i]) <= empty && empty >= pos && hashFunction(table[i]) != i) {
                table[empty] = table[i];
                table[i] = 0;
                empty = i;
            } else if (hashFunction(table[i]) >= empty && empty < pos && hashFunction(table[i]) != i) {
                table[empty] = table[i];
                table[i] = 0;
                empty = i;
            }
            i++;
            if (i == SIZE) {
                i = 0;
            }
        }
    }
    
    //print all datas of table
    void PrintTable() {
        System.out.println("");
        for (int i = 0; i < SIZE; i++) {
            if (table[i] != 0) {
                System.out.println("Table[ " + i + " ] = " + table[i]);
            } else {
                System.out.println("Table[ " + i + " ] is empty");
            }
        }
    }
}
