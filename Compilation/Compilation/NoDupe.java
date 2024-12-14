import java.util.*;

public class NoDupe {
    // Method to ask the user for the size of the array
    public int getArraySize(Scanner input) {
        System.out.print("Enter the size of the array: ");
        int size = input.nextInt();

        // Ensure size is positive
        while (size <= 0) {
            System.out.print("Size must be positive. Enter again: ");
            size = input.nextInt();
        }

        return size;
    }

    // Method to check if a number already exists in the array
    public boolean isExisting(int x, int arrCount, int[] arr) {
        for (int i = 0; i < arrCount; i++) {
            if (x == arr[i]) {
                return true;
            }
        }
        return false;
    }
}