import java.util.Scanner;

public class AverageAndStandardDeviationOfNNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter N");
        int n = scan.nextInt();

        double num = 0, avgSquare = 0, sum = 0, sum2= 0, avg = 0, avg2 = 0;
        int count = 1;
        while(count <= n){
            System.out.println("Enter floating point number: ");
            num = scan.nextDouble();
            sum += num;
            sum2 += num * num;
            count++;
        }

        avg = sum / n;
        avg2 = avg * avg;

        avgSquare = sum2 / n;

        double sd = Math.sqrt(avgSquare - avg2);

        System.out.println("The average is " + avg);
        System.out.println("The standard deviation is " + sd);



    }
}
