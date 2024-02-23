/* This program that inputs two numbers (as floating point) and writes out
 * both the usual average (the arithmetic mean) and the harmonic mean. 
 * Created by: Clint Justine A. Nepomuceno
 * Date created: 
 */

import java.util.Scanner;

public class HarmonicMean {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Input: receives values for x and y from the user.
        System.out.println("Enter x: ");
        double x = scan.nextDouble();

        System.out.println("Enter y: ");
        double y = scan.nextDouble();

        // Process: it calculates the arithmetic mean and harmonic mean.
        double arithMean = (x + y) / 2.0;
        double harmMean = 2.0 / (1/x + 1/y);

        // Output: prints out the arithmetic & harmonic mean.
        System.out.println("Arithmetic mean: " + arithMean);
        System.out.println("Harmonic mean: " + harmMean);
    }
}
