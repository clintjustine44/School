import java.util.Scanner;

public class LastChanceGas {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int gallons, percent, miles, result;

        System.out.printf("Tank capacity: ");
        gallons = scan.nextInt();

        System.out.printf("Gauge Reading: ");
        percent = scan.nextInt();

        System.out.printf("Miles per gallon: ");
        miles = scan.nextInt();

        result = (gallons * (percent/100)) * miles;
        if (result <= 200)
            System.out.printf("Get Gas!");
        else
            System.out.printf("Safe to Proceed");
            
    }
}
