import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nMain Menu\n1. Queue Node\n2. Queue Array\n3. Node List\n4. Infix To Postfix\n5. Sum and Product\n6. Odd and Even Divider\n7. Stack Array\n8. Stack List\n9. No Dupe\n10. Largest and Smallest\nX. Exit");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    queueNodeMenu(scan);
                    break;
                case "2":
                    queueArrayMenu(scan);
                    break;
                case "3":
                    nodeListMenu(scan);
                    break;
                case "4":
                    infixToPostfixMenu(scan);
                    break;
                case "5":
                    sumAndProductMenu(scan);
                    break;
                case "6":
                    oddEvenDividerMenu(scan);
                    break;
                case "7":
                    stackArrayMenu(scan);
                    break;
                case "8":
                    stackListMenu(scan);
                    break;
                case "9":
                    noDupeMenu(scan);
                    break;
                case "10":
                    largestSmallestMenu(scan);
                    break;
                case "X":
                    System.out.println("Exiting program...\nThank you for using the program!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void queueNodeMenu(Scanner scan) {
        QueueNode qn = new QueueNode();
        String choice;
        do {
            System.out.println("\nQueue Node\n1. Enqueue\n2. Dequeue\n3. Peek\n4. Display\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    Object item;

                    System.out.print("Enter an item: ");
                    item = scan.nextLine().trim();
                    while(item.equals("")){
                        System.out.print("Input cannot be empty. Please enter an item: ");
                        item = scan.nextLine().trim();
                    }
                    System.out.println(qn.enqueue(item) ? "Item enqueued successfully" : "");
                    break;
                case "2":
                    System.out.println(qn.dequeue() ? "Item dequeued successfully" : "");
                    break;
                case "3":
                    System.out.println("First: " + qn.peek());
                    break;
                case "4":
                    qn.display();
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void queueArrayMenu(Scanner scan) {
        QueueArray qa = new QueueArray();
        String choice;
        int size;
        do {
            System.out.println("\nQueue Array\n1. Enter array size\n2. Enqueue\n3. Dequeue\n4. Peek\n5. Display\n6. Get count\nX. Back to Main Menu");
            System.out.print("Choose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    System.out.print("Enter array size: ");
                    while (true) {
                        try {
                            size = scan.nextInt();
                            scan.nextLine(); // Clears the input buffer
                            if (size <= 0) {
                                System.out.println("The size of the array was set to 5 (default).");
                                size = 5;
                            } else {
                                System.out.println("The size of the array was set to " + size + ".");
                            }
                            qa = new QueueArray(size); // Initialize QueueArray with the size
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Invalid input. Please enter a valid integer: ");
                            scan.next(); // Clears the invalid input
                        }
                    }
                    break;
                case "2":
                    Object item;

                    System.out.print("Enter an item: ");
                    item = scan.nextLine().trim();
                    while(item.equals("")){
                        System.out.print("Input cannot be empty. Please enter an item: ");
                        item = scan.nextLine().trim();
                    }

                    System.out.println(qa.enqueue(item) ? "Item enqueued successfully" : "Queue is already full.");
                    break;
                case "3":
                    System.out.println(qa.dequeue() ? "Dequeued successfully" : "Dequeue failed");
                    break;
                case "4":
                    System.out.println("First: " + qa.peek());
                    break;
                case "5":
                    qa.display();
                    break;
                case "6":
                    System.out.println("Count: " + qa.getCount());
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void nodeListMenu(Scanner scan) {
        NodeList nl = new NodeList();
        String choice;
        int location;
        do {
            System.out.println("\nQueue Node\n1. Add\n2. Delete\n3. Peek\n4. Display\n5. Get count\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    Object item;

                    System.out.print("Enter an item: ");
                    item = scan.nextLine().trim();
                    while(item.equals("")){
                        System.out.print("Input cannot be empty. Please enter an item: ");
                        item = scan.nextLine().trim();
                    }

                    System.out.println(nl.add(new Node(item)) ? "Item enqueued successfully" : "Queue is already full.");
                    break;
                case "2":
                    boolean validInput = false;
                
                    while (!validInput) {
                        System.out.print("Enter item's location to delete: ");
                        try {
                            location = scan.nextInt();
                            scan.nextLine();
                            System.out.println(nl.delete(location) ? "Item deleted successfully" : "Item not found");
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scan.nextLine();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Top: " + nl.peek());
                    break;
                case "4":
                    nl.display();
                    break;
                case "5":
                    System.out.println("Count: " + nl.getCount());
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void infixToPostfixMenu(Scanner scan) {
        InfixToPostfix itp = new InfixToPostfix();
        String choice, infix, postfix;
        do {
            System.out.println("\nInfix To Postfix\n1. Convert Infix to Postfix\n2. Evaluate Postfix\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();
    
            switch (choice) {
                case "1":
                    System.out.print("Enter an infix expression: ");
                    infix = scan.nextLine().trim();
                    while (infix.isEmpty() || !infix.matches("[0-9+*/()-]*")) {
                        if (infix.isEmpty()) {
                            System.out.print("Expression cannot be empty. Please enter an expression: ");
                        } else {
                            System.out.print("Expression contains invalid characters. Please enter a valid expression: ");
                        }
                        infix = scan.nextLine().trim();
                    }
                    System.out.println("Postfix: " + itp.convertInfix(infix));
                    break;
                case "2":
                    System.out.print("Enter a postfix expression: ");
                    postfix = scan.nextLine().trim();
                    while (postfix.isEmpty() || !postfix.matches("[0-9+*/()-]*")) {
                        if (postfix.isEmpty()) {
                            System.out.print("Expression cannot be empty. Please enter an expression: ");
                        } else {
                            System.out.print("Expression contains invalid characters. Please enter a valid expression: ");
                        }
                        postfix = scan.nextLine().trim();
                    }
                    // Assuming evaluatePostfix can handle empty or invalid expressions
                    try {
                        System.out.println("Result: " + itp.evaluatePostfix(postfix));
                    } catch (Exception e) {
                        System.out.println("Error evaluating postfix expression: " + e.getMessage());
                    }
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void sumAndProductMenu(Scanner scan) {
        SumAndProductCalculator calc = new SumAndProductCalculator();
        String choice;
        int[] numbers = new int[5];
        int n;
    
        do {
            System.out.println("\nSum and Product\n1. Input numbers\n2. Calculate Sum\n3. Calculate Product\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();
    
            switch (choice) {
                case "1":
                    System.out.print("How many positive numbers do you want to input? ");
                    while (true) {
                        try {
                            n = Integer.parseInt(scan.nextLine());
                            if (n <= 0) {
                                System.out.println("Invalid size. Default size of 5 will be used.");
                                n = 5;
                            }
                            numbers = new int[n];
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid input. Please enter a valid positive number: ");
                        }
                    }
    
                    System.out.println("Enter " + n + " positive numbers:");
                    for (int i = 0; i < n; i++) {
                        while (true) {
                            System.out.print("Enter number " + (i + 1) + ": ");
                            try {
                                numbers[i] = scan.nextInt();
                                if (numbers[i] < 0) {
                                    System.out.println("Please enter a positive number.");
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scan.next();
                            }
                        }
                    }
                    scan.nextLine();
                    System.out.println("The numbers were successfully inserted in the array.");
                    break;
    
                case "2":
                    System.out.println("Sum of positive numbers: " + calc.calculateSum(numbers));
                    break;
    
                case "3":
                    System.out.println("Product of positive numbers: " + calc.calculateProduct(numbers));
                    break;
    
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }
    

    private static void oddEvenDividerMenu(Scanner scan) {
        OddEven oddEven = new OddEven(10); // Default size
        String choice;
        int n;
    
        do {
            System.out.println("\nOdd and Even Divider\n1. Input\n2. Display Numbers\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();
    
            switch (choice) {
                case "1":
                    System.out.print("How many numbers do you want to input? ");
                    while (true) {
                        try {
                            n = Integer.parseInt(scan.nextLine());
                            if (n <= 0) {
                                System.out.println("Invalid size. Default size of 5 will be used.");
                                n = 5;
                            }
                            oddEven = new OddEven(n); // Initialize OddEven with specified size
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid input. Please enter a valid number: ");
                        }
                    }
    
                    System.out.println("Enter " + n + " numbers:");
                    for (int i = 0; i < n; i++) {
                        while (true) {
                            System.out.print("Enter number " + (i + 1) + ": ");
                            try {
                                int input = scan.nextInt();
                                oddEven.processNumbers(input); // Process each number
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scan.next(); // Clears invalid input
                            }
                        }
                    }
                    scan.nextLine(); // Clears buffer
                    System.out.println("The numbers were successfully inserted in the array.");
                    break;
    
                case "2":
                    oddEven.displayNumbers();
                    break;
    
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void stackArrayMenu(Scanner scan) {
        StackArray sa = new StackArray();
        String choice;
        int size;
        Object item;
        do {
            System.out.println("\nStack Array\n1. Enter array size\n2. Push\n3. Pop\n4. Peek\n5. Display\n6. Get count\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    System.out.print("Enter array size: ");
                    while (true) {
                        try {
                            size = scan.nextInt();
                            scan.nextLine(); // Clears the input buffer
                            if (size <= 0) {
                                System.out.println("The size of the array was set to 5 (default).");
                                size = 5;
                            } else {
                                System.out.println("The size of the array was set to " + size + ".");
                            }
                            sa = new StackArray(size); // Initialize StackArray with the size
                            break; // Exit the loop after successful input
                        } catch (InputMismatchException e) {
                            System.out.print("Invalid input. Please enter a valid integer: ");
                            scan.next(); // Clears the invalid input
                        }
                    }
                    break;
                case "2":
                item = scan.nextLine().trim();
                    while(item.equals("")){
                        System.out.print("Input cannot be empty. Please enter an item: ");
                        item = scan.nextLine().trim();
                    }

                    System.out.println(sa.push(item) ? "Item pushed successfully" : "Queue is already full.");
                    break;
                case "3":
                    System.out.println(sa.pop() ? "Popped successfully" : "Popped failed");
                    break;
                case "4":
                    System.out.println("Top: " + sa.peek());
                    break;
                case "5":
                    sa.display();
                    break;
                case "6":
                    System.out.println("Count: " + sa.getCount());
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void stackListMenu(Scanner scan) {
        StackList sl = new StackList();
        Object item;
        String choice;
        do {
            System.out.println("\nStack List\n1. Push\n2. Pop\n3. Peek\n4. Display\n5. Get count\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    item = scan.nextLine().trim();

                    while(item.equals("")){
                        System.out.print("Input cannot be empty. Please enter an item: ");
                        item = scan.nextLine().trim();
                    }

                System.out.println(sl.push(item) ? "Item pushed successfully" : "Queue is already full.");
                    break;
                case "2":
                    System.out.println(sl.pop() ? "Popped successfully" : "Popped failed");
                    break;
                case "3":
                    System.out.println("Top: " + sl.peek());
                    break;
                case "4":
                    sl.display();
                    break;
                case "5":
                    System.out.println("Count: " + sl.getCount());
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void noDupeMenu(Scanner scan) {
        int[] numbers = new int[10];
        NoDupe nd = new NoDupe();
        String choice, result;
        int size = 10, arrCount;
    
        do {
            System.out.println("\nNo Dupe\n1. Enter array size\n2. Enter integer numbers\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();
    
            switch (choice) {
                case "1":
                    // Set array size
                    while (true) {
                        System.out.print("Enter array size: ");
                        try {
                            size = Integer.parseInt(scan.nextLine());
                            if (size > 0) {
                                numbers = new int[size]; // Resize the array
                                System.out.println("Array size set to " + size + ".");
                                break;
                            } else {
                                System.out.println("Size must be positive. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a positive integer.");
                        }
                    }
                    break;
    
                case "2":
                    // Input numbers into the array
                    result = "";
                    arrCount = 0;
                    System.out.println("Enter " + size + " numbers:");
                    for (int count = 0; count < size; count++) {
                        while (true) {
                            System.out.print("Enter number " + (count + 1) + ": ");
                            try {
                                int x = Integer.parseInt(scan.nextLine());
                                // Add only if it's not a duplicate
                                if (!nd.isExisting(x, arrCount, numbers)) {
                                    numbers[arrCount++] = x;
                                    result += x + "\n";
                                } else {
                                    System.out.println("Duplicate detected. Number not added.");
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                    }
                    System.out.println("\nResult:\n" + result);
                    break;
    
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }
    

    private static void largestSmallestMenu(Scanner scan) {
        LargestSmallest ls = new LargestSmallest();
        String choice;
        int x = 0, y = 0, z = 0; // Initialize variables
        do {
            System.out.println("\nLargest and Smallest\n1. Enter 3 numbers\nX. Back to Main Menu");
            System.out.print("\nChoose an option: ");
            choice = scan.nextLine().trim().toUpperCase();
    
            switch (choice) {
                case "1":
                    System.out.println("Enter 3 numbers:");
                    // Input for Number 1
                    while (true) {
                        System.out.print("Number 1: ");
                        String input = scan.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Input cannot be empty. Please enter a valid integer.");
                        } else {
                            try {
                                x = Integer.parseInt(input);
                                break; // Exit the loop if input is valid
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                    }
                    // Input for Number 2
                    while (true) {
                        System.out.print("Number 2: ");
                        String input = scan.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Input cannot be empty. Please enter a valid integer.");
                        } else {
                            try {
                                y = Integer.parseInt(input);
                                break; // Exit the loop if input is valid
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                    }
                    // Input for Number 3
                    while (true) {
                        System.out.print("Number 3: ");
                        String input = scan.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Input cannot be empty. Please enter a valid integer.");
                        } else {
                            try {
                                z = Integer.parseInt(input);
                                break; // Exit the loop if input is valid
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                    }
                    // Call the method to find the largest and smallest
                    ls.findLargeSmall(x, y, z);
                    break;
                case "X":
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("X"));
    }
}
