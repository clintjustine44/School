/* This program calculates the annual cost of running an appliance.
 * The program asks the user for the cost per kilowatt-hour and the number of kilowatt-hours the appliance uses in a year: 
 * Created by: Clint Justine A. Nepomuceno
 * Date created: 
 */

import java.util.Scanner;

public class AnnualApplianceCost {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        /* Input: receives values of cost per killowatt-hour in cents &
         * kilowatt-hours used per year.
         */
        System.out.println("Enter cost per kilowatt-hour in cents");
        double costInCents = scan.nextDouble();

        System.out.println("Enter kilowatt-hours used per year");
        int kilowattHrs = scan.nextInt();

        // Process: calculates the annual cost.
        double annualCost = (costInCents * kilowattHrs) / 100;

        // Output: prints out the annual cost.
        System.out.printf("Annual cost: %.4f", annualCost);

    }
}
