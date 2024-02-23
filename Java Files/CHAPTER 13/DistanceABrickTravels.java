/* This program calculates program that asks the user for the
 * number of seconds and then prints out the distance traveled. 
 * Created by: Clint Justine A. Nepomuceno
 * Date created: 
 */

import java.util.Scanner;

public class DistanceABrickTravels{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Input: receives the value of time (in seconds).
        System.out.println("Enter the number of seconds:");
        double time = scan.nextDouble();

        // Process: calculates the distance with a formula.
        double g = 32.174;
        double distance = (1/2.0) * g * time * time;
        
        // Output: prints out the calculated distance.
        System.out.println("Distance: " + distance);
    }
}