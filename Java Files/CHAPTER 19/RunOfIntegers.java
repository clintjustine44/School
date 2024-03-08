import java.util.Scanner;

public class RunOfIntegers{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.err.println("Enter Start: ");
        int start = scan.nextInt();
        System.out.println("Enter End: ");
        int end = scan.nextInt();

        while(start <= end){
            System.out.printf("\n%d", start);
            start++;
        }

    }
}