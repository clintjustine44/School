public class LargestSmallest {
   public void findLargeSmall(int x, int y, int z){
      int largest = x; // Assume x is the largest
      if (y > largest) largest = y;
      if (z > largest) largest = z;

      // Find the smallest number
      int smallest = x; // Assume x is the smallest
      if (y < smallest) smallest = y;
      if (z < smallest) smallest = z;

      // Output the largest and smallest numbers
      System.out.println("\nThe largest number is: " + largest);
      System.out.println("The smallest number is: " + smallest);
   }
}