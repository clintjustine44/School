import java.util.Scanner;

public class SumAndProductCalculator {

    // Method to calculate the sum of positive integers
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            if (num > 0) {
                sum += num;
            }
        }
        return sum;
    }

    // Method to calculate the product of positive integers
    public int calculateProduct(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0; // No array or empty array, product is 0
        }
    
        int product = 1;
        boolean hasNonZero = false;
    
        for (int num : numbers) {
            if (num != 0) {
                product *= num;
                hasNonZero = true;
            }
        }
    
        return hasNonZero ? product : 0; // If all are zero, product is 0
    }
}