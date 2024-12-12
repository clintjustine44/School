// input

import java.util.Scanner;

public class OddEven {

    private int[] odd;
    private int[] even;
    private int cEven = 0, cOdd = 0;

    // Constructor to initialize the arrays with the user-defined size
    public OddEven(int size) {
        odd = new int[size];
        even = new int[size];
    }

    // Method to process input and classify numbers into odd and even arrays
    public void processNumbers(int input) {
        if (input % 2 == 0) {
            even[cEven] = input;
            cEven++;
        } else {
            odd[cOdd] = input;
            cOdd++;
        }
    }

    // Method to display numbers in "Even" and "Odd" order based on comparison
    public void displayNumbers() {
        int count = 0;

        if (cOdd < cEven) {
            System.out.println("Even\t\tOdd");
            while (count < cOdd) {
                System.out.println(even[count] + "\t\t" + odd[count]);
                count++;
            }

            // If there are more even numbers, print the rest of even numbers
            while (count < cEven) {
                System.out.println(even[count]);
                count++;
            }
        } else {
            System.out.println("Odd\t\tEven");
            while (count < cEven) {
                System.out.println(odd[count] + "\t\t" + even[count]);
                count++;
            }

            // If there are more odd numbers, print the rest of odd numbers
            while (count < cOdd) {
                System.out.println(odd[count]);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("How many numbers do you want to input? ");
        int size = scan.nextInt();

        // Create an instance of OddEven with the user-defined size
        OddEven oddEven = new OddEven(size);

        // Prompt user to enter the integers
        System.out.println("Please enter " + size + " integers:");

        for (int i = 0; i < size; i++) {
            int input = scan.nextInt();
            oddEven.processNumbers(input); // Process the input
        }

        // Display the categorized numbers
        System.out.println();
        oddEven.displayNumbers();
    }
}