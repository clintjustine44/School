import java.util.*;

public class OrderChecker {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        // Initializing the variables and declaring the constants.
        int bolts, nuts, washers, totalCost;
        final int boltPrice = 5;
        final int nutPrice = 3;
        final int washersPrice = 1;
        
        // Input: Asking the user for the number of bolts, nuts, and washers.
        System.out.println("Number of bolts: ");
        bolts = scan.nextInt();
        System.out.println("Number of nuts: ");
        nuts = scan.nextInt();
        System.out.println("Number of washers: ");
        washers = scan.nextInt();

        // Process: Calculating total cost
        totalCost = bolts * boltPrice + nuts * nutPrice + washers * washersPrice;

        // Process: Checking the orders for errors.
        String errorMessage = "";
        if (nuts <= bolts)
            errorMessage += "\nCheck the Order: too few nuts";
        if (washers < 2 * bolts)
            errorMessage +="\nCheck the Order: too few washers";
        
        if (errorMessage.isEmpty())
            System.out.println("Order is OK.");
        else
            System.out.println(errorMessage);

        // Output: prints out the total cost.
        System.out.println("\nTotal cost: " + totalCost);

    }
}
