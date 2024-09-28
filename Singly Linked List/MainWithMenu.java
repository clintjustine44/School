import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        StackList s1 = new StackList();
        String data;
        int choice;

        do{
            System.out.println("\nSingly Linked List Management\n1. Push a value\n2. Pop a value\n3. Peek the top value\n4. Get first data");
            System.out.println("5. Get count\n6. Display Stack\n7. Exit");
            System.out.println("\nSelect a choice: ");
            choice = scan.nextInt();
            scan.nextLine();
                
            switch(choice){
                case 1:
                    System.out.println("Enter the value: ");
                    data = scan.nextLine();
                    s1.push(data);
                    System.out.println("Value pushed successfully.");
                    break;
                case 2:
                    System.out.println(s1.getTop() + " is popped.");
                    s1.pop();
                    break;
                case 3:
                    System.out.println("Top: " + s1.peek());
                    break;
                case 4:
                    System.out.println("Head: " + s1.getHead());
                    break;
                case 5:
                    System.out.println("Count: " + s1.getCount());
                    break;
                case 6:
                    s1.display();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("\nInvalid input. Please select a choice.");
                    break;
            }
        }while(choice != 7);

        System.out.println("\nProgram closing...");
    }
}
