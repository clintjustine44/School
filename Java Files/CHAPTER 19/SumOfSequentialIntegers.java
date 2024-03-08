import java.util.Scanner;

public class SumOfSequentialIntegers {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter N: ");
        int n = scan.nextInt();

        int count = 1;
        int sum = 1;

        while(count < n){
            count++;
            sum += count;
        }

        System.out.println("Sum = " + sum);

    }
}
