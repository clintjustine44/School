import java.util.Scanner;

public class SumOfARangeFfSequentialIntegers {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter low:");
        int low = scan.nextInt();

        System.out.println("Enter high:");
        int high = scan.nextInt();

        int sum = 0;
        while(low <= high){
            sum += low;
            low++;
        }

        System.out.println("Sum = " + sum);

    }
}
