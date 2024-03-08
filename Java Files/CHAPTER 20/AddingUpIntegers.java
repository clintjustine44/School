import java.util.Scanner;

public class AddingUpIntegers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("How many integers will be added: ");
        int times = scan.nextInt();

        int sum = 0, input = 0;
        int count = 1;
        while(count <= times){
            System.out.println("Enter an integer:");
            input = scan.nextInt();
            sum += input;
            
            count++;
        }

        System.out.print("The sum is " + sum);

    }
}
