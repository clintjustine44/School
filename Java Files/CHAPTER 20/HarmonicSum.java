import java.util.Scanner;

public class HarmonicSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter N");
        int n = scan.nextInt();

        int count = 1;
        double sum = 0;

        while(count <= n){
            sum += 1.0/count;
            count++;
        }

        System.out.print("Sum is: " + sum);

    }
}
