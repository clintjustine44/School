import java.util.Scanner;

public class GroundBeefValueCalculator {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        double priceA, priceB, costPerPoundA, costPerPoundB;
        int leanA, leanB;

        System.out.println("Price per pound package A: ");
        priceA = scan.nextDouble();
        System.out.println("Percent lean package A: ");
        leanA = scan.nextInt();
        System.out.println("Price per pound package B: ");
        priceB = scan.nextDouble();
        System.out.println("Percent lean package A: ");
        leanB = scan.nextInt();

        costPerPoundA = priceA / (leanA / 100.0);
        costPerPoundB = priceB / (leanB / 100.0);

        System.out.printf("Package A cost per pound of lean: %.6f", costPerPoundA);
        System.out.printf("Package B cost per pound of lean: %.6f", costPerPoundB);
    }
}
