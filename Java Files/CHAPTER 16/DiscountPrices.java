import java.util.*;

public class DiscountPrices {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        // Declaring the variables that will be used in the program.
        int cost, discounted;

        // Input: Asking the user for the amount of purchases.
        System.out.println("Enter amount of purchases:");
        cost = scan.nextInt();

        /* Process & Output: Checks out if the cost is more than 10, if it is then a 10% discount is applied. 
         * Then prints out the output. Else, it does nothing.
        */
        if (cost >= 10) {
            discounted = cost - (cost / 10);
            System.out.println("Discounted price: " + discounted);
        }
    }
}
