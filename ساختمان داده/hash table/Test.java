import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        hashPart hash = new hashPart();

        System.out.println("Print what plan of input you have");
        System.out.println("1 - file");
        System.out.println("2 - keyboard");

        int answer = scan.nextInt();
        if (answer == 1) {
            int a = hash.fileIn("test.txt");
            if (a == 1) {
                System.out.println("Error while reading from file");
            }
            answer = 2;
        }
        if (answer == 2) {
            boolean sw = true;

            System.out.println("Input the number of operation :");
            System.out.println("1 - add a number");
            System.out.println("2 - delete a number");
            System.out.println("3 - search for a number");
            System.out.println("4 - print the table");
            System.out.println("5 - show the commands");
            System.out.println("6 - exit");

            while (sw) {

                int operator = scan.nextInt();
                int x;

                switch (operator) {
                    case 1:
                        System.out.print("enter the number :");
                        x = scan.nextInt();
                        hash.insert(x);
                        break;
                    case 2:
                        System.out.print("enter the number :");
                        x = scan.nextInt();
                        hash.delete(x);
                        break;
                    case 3:
                        System.out.print("enter the number :");
                        x = scan.nextInt();
                        hash.search(x);
                        break;
                    case 4:
                        hash.PrintTable();
                        break;
                    case 5:
                        System.out.println("Input the number of operation :");
                        System.out.println("1 - add a number");
                        System.out.println("2 - delete a number");
                        System.out.println("3 - search for a number");
                        System.out.println("4 - print the table");
                        System.out.println("5 - show the commands");
                        System.out.println("6 - exit");
                        break;
                    case 6:
                        sw = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
    }
}
