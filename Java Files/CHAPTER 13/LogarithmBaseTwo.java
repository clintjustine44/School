/* This program that inputs a number and outputs its base 2 logarithm.
 * Created by: Clint Justine A. Nepomuceno
 * Date created: 
 */

import java.util.Scanner;

public class LogarithmBaseTwo {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Input: receives value for x from the user.
        System.out.println("Enter a double:");
        double x = scan.nextDouble();

        // Process: calculates the base 2 logarithmic of a number(x).
        double log = Math.log(x) / Math.log(2);

        // Output: prints out the value of base 2 logarithmic of a number(x).
        System.out.println("Base 2 log of " + x + " is " + log); 

    }
}
